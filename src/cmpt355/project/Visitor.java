package cmpt355.project;

import cmpt355.project.ast.QualifiedIdentifier;
import cmpt355.project.ast.node.*;
import cmpt355.project.ast.node.expression.*;
import cmpt355.project.ast.node.statement.*;
import cmpt355.project.language.*;
import cmpt355.project.util.Lists;

import java.util.ArrayList;
import java.util.List;

/*
 * For IP4, it is very important that you *not* change any code involved in generating the AST!
 */
class Visitor extends JavaBaseVisitor<AstNode> {

    // We want to be able to throw a SyntaxException from the visit methods, but since they are overriding methods that
    // do not throw SyntaxExceptions (specified in the ANTLR-generated Visitor interface), we aren't allowed to.
    // Solution: we will throw a WrapException instead (which, since it extends RuntimeException, is unchecked) with
    // the desired SyntaxException as cause, then unwrap it in generateAst() (which, not being inherited, can be
    // declared to throw any exception we like).
    private final class WrapException extends RuntimeException {
        public WrapException(SyntaxException cause) {
            super(cause);
        }

        SyntaxException getSyntaxException() {
            return (SyntaxException)getCause();
        }
    }

    // Declaring error() to return a value (even though it's just going to throw) lets us use it in switch expressions.
    // Making it a generic return means that the compiler can choose a return type so that it will fit into a switch
    // expression regardless of what type it is determining.
    private <T> T error(SyntaxException e) {
        throw new WrapException(e);
    }

    private <T> T error(String message) {
        return error(new SyntaxException(message));
    }

    public JavaFile generateAst(JavaParser.JavaFileContext ctx) throws SyntaxException {
        try {
            return visitJavaFile(ctx);
        } catch (WrapException ex) {
            throw ex.getSyntaxException();
        }
    }

    @Override
    public JavaFile visitJavaFile(JavaParser.JavaFileContext ctx) {
        var imports = Lists.map(ctx.importStatement(), this::visitImportStatement);
        var classDefinitions = Lists.map(ctx.classDefinition(), this::visitClassDefinition);

        QualifiedIdentifier pkg = null;
        if (ctx.packageStatement() != null) {
            var id = ctx.packageStatement().id;
            var thisPkg = QualifiedIdentifier.from(id.getText());
            pkg = thisPkg;
            classDefinitions.forEach(classDef -> classDef.setPackage(thisPkg));
        }

        return new JavaFile(pkg, imports, classDefinitions);
    }

    private Import visitImportStatement(JavaParser.ImportStatementContext ctx) {
        if (ctx instanceof JavaParser.ImportClassContext)
            return new Import(QualifiedIdentifier.from(((JavaParser.ImportClassContext) ctx).id.getText()), false);
        else
            return new Import(QualifiedIdentifier.from(((JavaParser.ImportWildcardContext)ctx).id.getText()), true);
    }

    @Override
    public ClassDefinition visitClassDefinition(JavaParser.ClassDefinitionContext ctx) {
        var builder = ClassDefinition.builder();

        builder.type(switch (ctx.type.getText()) {
            case "class" -> ClassDefinition.Type.CLASS;
            case "interface" -> ClassDefinition.Type.INTERFACE;
            default -> error("Blah");
        });

        ctx.mods.forEach(m -> builder.modifier(Modifier.fromString(m.getText())));

        builder.name(ctx.id.getText());

        ctx.extending.forEach(i -> builder.extending(new UnresolvedClassType(QualifiedIdentifier.from(i.components))));
        ctx.implementing.forEach(i -> builder.implementing(new UnresolvedClassType(QualifiedIdentifier.from(i.components))));

        ctx.body.members.forEach(m -> {
            if (m instanceof JavaParser.ClassFieldContext)
                visitFields(((JavaParser.ClassFieldContext)m).field())
                        .forEach(builder::field);
            else if (m instanceof JavaParser.ClassMethodContext)
                builder.method(visitMethod(((JavaParser.ClassMethodContext)m).method()));
            else if (m instanceof JavaParser.ClassMethodStubContext)
                builder.method(visitMethodStub(((JavaParser.ClassMethodStubContext)m).methodStub()));
            else
                throw new InternalParserException("Unhandled class member: " + ctx.getText());
        });

        return builder.build();
    }

    // What is labeled as a memberVariable in the grammar could be multiple member variables
    // (e.g., "public final String first, middle, last;"). Overriding listMemberVariable() would require us to return
    // an AstNode, when really we need multiple AstNodes (one per variable), so we define this method instead.
    private List<FieldDeclaration> visitFields(JavaParser.FieldContext ctx) {
        var builder = FieldDeclaration.builder();

        ctx.modifiers.forEach(m -> builder.modifier(Modifier.fromString(m.getText())));

        builder.type(visitDataType(ctx.multVarDeclaration().type).getType());

        List<FieldDeclaration> vars = new ArrayList<>();

        ctx.multVarDeclaration().nameInits.forEach(nameInit -> {
            var b = builder.duplicate()
                    .name(nameInit.id.getText());
            if (nameInit.init != null && nameInit.init.rhs != null)
                b.initializer(visitExpression(nameInit.init.rhs));
            else if (nameInit.init != null && nameInit.init.arrayInitializer() != null)
                throw new UnsupportedOperationException("Array instantiation with initializer not supported");
            vars.add(b.build());
        });

        return vars;
    }

    private DataTypeNode visitDataType(JavaParser.DataTypeContext ctx) {
        return (DataTypeNode)visit(ctx);
    }

    @Override
    public DataTypeNode visitScalarDataType(JavaParser.ScalarDataTypeContext ctx) {
        return (DataTypeNode)visit(ctx.scalarType());
    }

    @Override
    public DataTypeNode visitArrayType(JavaParser.ArrayTypeContext ctx) {
        var scalarTypeNode = (DataTypeNode)visit(ctx.scalarType());
        return new DataTypeNode(DataType.arrayOf(scalarTypeNode.getType(), ctx.getChildCount() >> 1));
    }

    @Override
    public DataTypeNode visitPrimitiveType(JavaParser.PrimitiveTypeContext ctx) {
        return new DataTypeNode(PrimitiveType.fromString(ctx.getText()));
    }

    @Override
    public DataTypeNode visitNamedType(JavaParser.NamedTypeContext ctx) {
        var typeId = QualifiedIdentifier.from(ctx.qualifiedIdentifier().components);
        return new DataTypeNode(new UnresolvedClassType(typeId));
    }

    @Override
    public MethodDefinition visitMethod(JavaParser.MethodContext ctx) {
        return buildMethodHeader(ctx.header)
                .body(visitBlock(ctx.body))
                .build();
    }

    private MethodDefinition.Builder buildMethodHeader(JavaParser.MethodHeaderContext ctx) {
        var builder = MethodDefinition.builder();

        ctx.modifiers.forEach(m -> builder.modifier(Modifier.fromString(m.getText())));

        if (ctx.returnType() == null || "void".equals(ctx.returnType().getText()))
            builder.returnType(new DataTypeNode(DataType.VOID));
        else if (ctx.returnType() != null)
            builder.returnType(visitReturnType(ctx.returnType()));

        if (ctx.returnType() == null)
            builder.name(Method.CONSTRUCTOR_NAME);
        else
            builder.name(ctx.id.getText());

        ctx.params.forEach(p -> builder.parameter(visitParameter(p)));

        return builder;
    }

    @Override
    public DataTypeNode visitReturnType(JavaParser.ReturnTypeContext ctx) {
        return "void".equals(ctx.getText())
                ? new DataTypeNode(DataType.VOID)
                : visitDataType(ctx.dataType());
    }

    private Parameter visitParameter(JavaParser.MethodParameterContext ctx) {
        var builder = Parameter.builder()
                .name(ctx.id.getText())
                .type(visitDataType(ctx.type).getType());
        ctx.mods.forEach(m -> builder.modifier(Modifier.fromString(m.getText())));
        return builder.build();
    }

    @Override
    public MethodDefinition visitMethodStub(JavaParser.MethodStubContext ctx) {
        return buildMethodHeader(ctx.header)
                .build();
    }

    @Override
    public AstNode visitEmptyStatement(JavaParser.EmptyStatementContext ctx) {
        return new EmptyStatement();
    }

    @Override
    public Block visitBlock(JavaParser.BlockContext ctx) {
        return new Block(Lists.map(ctx.statements, this::visitStatement));
    }

    private Statement visitStatement(JavaParser.StatementContext ctx) {
        var stmt = (Statement)visit(ctx);
        if (stmt == null)
            throw new RuntimeException("Unimplemented statement: " + ctx.getText());
        return (Statement)visit(ctx);
    }

    @Override
    public Statement visitLeaf(JavaParser.LeafContext ctx) {
        return (Statement)super.visit(ctx.leafStatement());
    }

    @Override
    public IfStatement visitIfElseStatement(JavaParser.IfElseStatementContext ctx) {
        return new IfStatement(
                visitExpression(ctx.cond),
                visitStatement(ctx.trueBlock),
                visitStatement(ctx.falseBlock)
        );
    }

    @Override
    public IfStatement visitIfStatement(JavaParser.IfStatementContext ctx) {
        return new IfStatement(
                visitExpression(ctx.cond),
                visitStatement(ctx.trueBlock)
        );
    }

    @Override
    public WhileStatement visitWhileLoop(JavaParser.WhileLoopContext ctx) {
        return new WhileStatement(
                visitExpression(ctx.cond),
                visitStatement(ctx.body)
        );
    }

    @Override
    public DoWhileStatement visitDoWhileLoop(JavaParser.DoWhileLoopContext ctx) {
        return new DoWhileStatement(
                visitExpression(ctx.cond),
                visitStatement(ctx.body)
        );
    }

    @Override
    public ForStatement visitForLoop(JavaParser.ForLoopContext ctx) {
        var init = Lists.map(ctx.init, i -> (Statement)this.visit(i));
        var cond = (ctx.cond != null) ? visitExpression(ctx.cond) : null;
        var update = Lists.map(ctx.update, i -> (Statement)this.visit(i));

        return new ForStatement(
                init,
                cond,
                update,
                visitStatement(ctx.body)
        );
    }

    @Override
    public AstNode visitEnhancedForLoop(JavaParser.EnhancedForLoopContext ctx) {
        throw new UnsupportedOperationException("Enhanced for unsupported");
    }

    @Override
    public AstNode visitTryStatement(JavaParser.TryStatementContext ctx) {
        throw new UnsupportedOperationException("Try statements unsupported");
    }

    @Override
    public AstNode visitSynchronizedBlock(JavaParser.SynchronizedBlockContext ctx) {
        throw new UnsupportedOperationException("Synchronized blocks unsupported");
    }

    @Override
    public AstNode visitSynchronizedMonitorBlock(JavaParser.SynchronizedMonitorBlockContext ctx) {
        throw new UnsupportedOperationException("Synchronized blocks unsupported");
    }

    @Override
    public Statement visitSuperMethodCallStatement(JavaParser.SuperMethodCallStatementContext ctx) {
        return new ExpressionStatement(
                visitSuperMethodCall(ctx.superMethodCall())
        );
    }

    @Override
    public Statement visitThisMethodCallStatement(JavaParser.ThisMethodCallStatementContext ctx) {
        return new ExpressionStatement(
                visitThisMethodCall(ctx.thisMethodCall())
        );
    }

    @Override
    public Statement visitInstantiationStatement(JavaParser.InstantiationStatementContext ctx) {
        return new ExpressionStatement(
                visitInstantiation(ctx.instantiation())
        );
    }

    @Override
    public SuperMethodCallExpression visitSuperMethodCall(JavaParser.SuperMethodCallContext ctx) {
        return new SuperMethodCallExpression(ctx.name.getText(), Lists.map(ctx.arguments().args, this::visitExpression));
    }

    @Override
    public MethodCallExpression visitThisMethodCall(JavaParser.ThisMethodCallContext ctx) {
        if (ctx.thisRef != null)
            return new MethodCallExpression(new ThisExpression(), ctx.name.getText(),
                    Lists.map(ctx.arguments().args, this::visitExpression));
        else
            return new MethodCallExpression(null, ctx.name.getText(),
                    Lists.map(ctx.arguments().args, this::visitExpression));
    }

    @Override
    public InstantiationExpression visitInstantiation(JavaParser.InstantiationContext ctx) {
        return new InstantiationExpression(new DataTypeNode(new UnresolvedClassType(QualifiedIdentifier.from(ctx.type.getText()))),
                Lists.map(ctx.arguments().args, this::visitExpression));
    }

    @Override
    public Statement visitSuperConstructorCallStatement(JavaParser.SuperConstructorCallStatementContext ctx) {
        return new ExpressionStatement(
                new SuperMethodCallExpression(Method.CONSTRUCTOR_NAME,
                        Lists.map(ctx.args, this::visitExpression))
        );
    }

    @Override
    public Statement visitMethodCallStatement(JavaParser.MethodCallStatementContext ctx) {
        return new ExpressionStatement(visitMethodCall(ctx.methodCall()));
    }

    @Override
    public MethodCallExpression visitMethodCall(JavaParser.MethodCallContext ctx) {
        return new MethodCallExpression(
                visitExpression(ctx.expression()),
                ctx.name.getText(),
                Lists.map(ctx.arguments().args, this::visitExpression)
        );
    }

    @Override
    public Statement visitCombinedAssignmentStatement(JavaParser.CombinedAssignmentStatementContext ctx) {
        return new ExpressionStatement(visitCombinedAssignment(ctx.combinedAssignment()));
    }

    @Override
    public Statement visitDeclarationStatement(JavaParser.DeclarationStatementContext ctx) {
        var builder = LocalVariableDeclaration.builder();
        ctx.modifiers.forEach(m -> builder.modifier(Modifier.fromString(m.getText())));

        builder.type(visitDataType(ctx.multVarDeclaration().type).getType());

        var declarationList = Lists.map(ctx.multVarDeclaration().nameInits, nameInit -> {
            var b = builder.duplicate().name(nameInit.id.getText());
            if (nameInit.init != null)
                b.init((Expression)visit(nameInit.init));
            return b.build();
        });

        if (declarationList.size() == 1)
            return declarationList.get(0);
        else
            return new LocalVariableDeclarationList(declarationList);
    }

    @Override
    public Statement visitPostIncrementStatement(JavaParser.PostIncrementStatementContext ctx) {
        return new ExpressionStatement(visitPostIncrement(ctx.postIncrement()));
    }

    @Override
    public Statement visitPreIncrementStatement(JavaParser.PreIncrementStatementContext ctx) {
        return new ExpressionStatement(visitPreIncrement(ctx.preIncrement()));
    }

    @Override
    public AstNode visitThrowStatement(JavaParser.ThrowStatementContext ctx) {
        throw new UnsupportedOperationException("Throw statements unsupported");
    }

    @Override
    public ReturnStatement visitReturnStatement(JavaParser.ReturnStatementContext ctx) {
        var exprCtx = ctx.expression();
        if (exprCtx != null)
            return new ReturnStatement(visitExpression(exprCtx));
        else
            return new ReturnStatement();
    }

    @Override
    public BreakStatement visitBreakStatement(JavaParser.BreakStatementContext ctx) {
        return new BreakStatement();
    }

    @Override
    public ContinueStatement visitContinueStatement(JavaParser.ContinueStatementContext ctx) {
        return new ContinueStatement();
    }

    @Override
    public AstNode visitSwitchStatement(JavaParser.SwitchStatementContext ctx) {
        throw new UnsupportedOperationException("Switch statements unsupported");
    }

    @Override
    public AssignmentExpression visitCombinedAssignment(JavaParser.CombinedAssignmentContext ctx) {
        String op = ctx.op.getText();
        if ("=".equals(op))
            return new AssignmentExpression((LValue)visit(ctx.lvalue()), visitExpression(ctx.rhs));
        else {
            op = op.substring(0, op.length() - 1); // e.g. >>= becomes >>
            return new AssignmentExpression((LValue)visit(ctx.lvalue()),
                    BinaryOp.of(op,
                            (LValue)visit(ctx.lvalue()),
                            visitExpression(ctx.rhs)));
        }
    }

    @Override
    public PostIncrementExpression visitPostIncrement(JavaParser.PostIncrementContext ctx) {
        return new PostIncrementExpression("--".equals(ctx.op.getText()), (LValue)visit(ctx.lvalue()));
    }

    @Override
    public PreIncrementExpression visitPreIncrement(JavaParser.PreIncrementContext ctx) {
        return new PreIncrementExpression("--".equals(ctx.op.getText()), (LValue)visit(ctx.lvalue()));
    }

    @Override
    public LValue visitNameLvalue(JavaParser.NameLvalueContext ctx) {
        return new NameExpression(QualifiedIdentifier.from(ctx.id.getText()));
    }

    @Override
    public ArraySubscript visitSubscriptLvalue(JavaParser.SubscriptLvalueContext ctx) {
        return new ArraySubscript(
                (LValue)visit(ctx.lvalue()),
                visitExpression(ctx.index)
        );
    }

    @Override
    public LValue visitParenthesizedLValue(JavaParser.ParenthesizedLValueContext ctx) {
        return (LValue)visit(ctx.lvalue());
    }

    @Override
    public FieldReference visitFieldRefLValue(JavaParser.FieldRefLValueContext ctx) {
        return new FieldReference(
                (LValue)visit(ctx.lvalue()),
                ctx.fieldName.getText()
        );
    }

    @Override
    public FieldReference visitThisFieldLValue(JavaParser.ThisFieldLValueContext ctx) {
        return new FieldReference(new ThisExpression(), ctx.fieldName.getText());
    }

    private Expression visitExpression(JavaParser.ExpressionContext ctx) {
        var expr = (Expression)visit(ctx);
        if (expr == null)
            throw new RuntimeException("Unimplemented expression: " + ctx.getText());
        return (Expression)visit(ctx);
    }

    @Override
    public ArraySubscript visitArraySubscript(JavaParser.ArraySubscriptContext ctx) {
        return new ArraySubscript(visitExpression(ctx.array), visitExpression(ctx.index));
    }

    @Override
    public PostIncrementExpression visitPostIncrementExpression(JavaParser.PostIncrementExpressionContext ctx) {
        return visitPostIncrement(ctx.postIncrement());
    }

    @Override
    public UnaryOp visitUnaryOperator(JavaParser.UnaryOperatorContext ctx) {
        return UnaryOp.of(ctx.op.getText(), visitExpression(ctx.expr));
    }

    @Override
    public BinaryOp visitBinaryOperator(JavaParser.BinaryOperatorContext ctx) {
        return BinaryOp.of(ctx.op.getText(), visitExpression(ctx.left), visitExpression(ctx.right));
    }

    @Override
    public InstanceofExpression visitInstanceof(JavaParser.InstanceofContext ctx) {
        return new InstanceofExpression(visitExpression(ctx.expr), visitDataType(ctx.type));
    }

    @Override
    public AstNode visitConditionalExpression(JavaParser.ConditionalExpressionContext ctx) {
        throw new UnsupportedOperationException("Conditional expressions not supported");
    }

    @Override
    public AssignmentExpression visitCombinedAssignmentExpression(JavaParser.CombinedAssignmentExpressionContext ctx) {
        return visitCombinedAssignment(ctx.combinedAssignment());
    }

    @Override
    public MethodCallExpression visitMethodCallExpression(JavaParser.MethodCallExpressionContext ctx) {
        return new MethodCallExpression(
                visitExpression(ctx.expression()),
                ctx.name.getText(),
                Lists.map(ctx.arguments().args, this::visitExpression)
        );
    }

    @Override
    public InstantiationExpression visitInstantiationExpression(JavaParser.InstantiationExpressionContext ctx) {
        return new InstantiationExpression(
                new DataTypeNode(new UnresolvedClassType(QualifiedIdentifier.from(ctx.instantiation().type.getText()))),
                Lists.map(ctx.instantiation().arguments().args, this::visitExpression)
        );
    }

    @Override
    public Expression visitParenthesizedExpression(JavaParser.ParenthesizedExpressionContext ctx) {
        return visitExpression(ctx.expression());
    }

    @Override
    public ArrayInstantiationExpression visitArrayInstantiationNoInitializer(JavaParser.ArrayInstantiationNoInitializerContext ctx) {
        int dimensions = ctx.dims.size() + ctx.emptyDims.size();
        var baseType = (DataTypeNode)visit(ctx.type);
        var arrayType = new DataTypeNode(DataType.arrayOf(baseType.getType(), dimensions));
        return new ArrayInstantiationExpression(arrayType, Lists.map(ctx.dims, this::visitExpression));
    }

    @Override
    public ArrayInstantiationExpression visitArrayInstantiationWithInitializer(JavaParser.ArrayInstantiationWithInitializerContext ctx) {
        throw new UnsupportedOperationException("Array instantiation with initializer not supported");
    }

    @Override
    public NameExpression visitNameExpression(JavaParser.NameExpressionContext ctx) {
        return new NameExpression(QualifiedIdentifier.from(ctx.getText()));
    }

    @Override
    public FieldReference visitFieldReference(JavaParser.FieldReferenceContext ctx) {
        return new FieldReference(
                visitExpression(ctx.expression()),
                ctx.fieldName.getText()
        );
    }

    @Override
    public Literal visitIntLiteral(JavaParser.IntLiteralContext ctx) {
        return new Literal(new DataTypeNode(PrimitiveType.INT), ctx.getText());
    }

    @Override
    public Literal visitLongLiteral(JavaParser.LongLiteralContext ctx) {
        return new Literal(new DataTypeNode(PrimitiveType.LONG), ctx.getText());
    }

    @Override
    public Literal visitDoubleLiteral(JavaParser.DoubleLiteralContext ctx) {
        return new Literal(new DataTypeNode(PrimitiveType.DOUBLE), ctx.getText());
    }

    @Override
    public Literal visitFloatLiteral(JavaParser.FloatLiteralContext ctx) {
        return new Literal(new DataTypeNode(PrimitiveType.FLOAT), ctx.getText());
    }

    @Override
    public Literal visitBooleanLiteral(JavaParser.BooleanLiteralContext ctx) {
        return new Literal(new DataTypeNode(PrimitiveType.BOOLEAN), ctx.getText());
    }

    @Override
    public Literal visitCharLiteral(JavaParser.CharLiteralContext ctx) {
        return new Literal(new DataTypeNode(PrimitiveType.CHAR), ctx.getText());
    }

    @Override
    public Literal visitStringLiteral(JavaParser.StringLiteralContext ctx) {
        return new Literal(new DataTypeNode(ExternalClassType.STRING), ctx.getText());
    }

    @Override
    public Literal visitNull(JavaParser.NullContext ctx) {
        return new Literal(new DataTypeNode(DataType.NULL), ctx.getText());
    }

    @Override
    public ThisExpression visitThisReference(JavaParser.ThisReferenceContext ctx) {
        return new ThisExpression();
    }

    @Override
    public AstNode visitArrayInitializer(JavaParser.ArrayInitializerContext ctx) {
        throw new UnsupportedOperationException("Array initializer unsupported");
    }

    @Override
    public AstNode visitArrayInitializerItem(JavaParser.ArrayInitializerItemContext ctx) {
        if (ctx.expression() != null)
            return visitExpression(ctx.expression());
        else if (ctx.arrayInitializer() != null)
            return visitArrayInitializer(ctx.arrayInitializer());
        else
            throw new InternalParserException("Unhandled array initializer item: " + ctx.getText());
    }

    @Override
    public CastExpression visitCast(JavaParser.CastContext ctx) {
        return new CastExpression(
                visitDataType(ctx.dataType()),
                visitExpression(ctx.expression())
        );
    }

    @Override
    public ClassLiteral visitClassLiteral(JavaParser.ClassLiteralContext ctx) {
        return new ClassLiteral(QualifiedIdentifier.from(ctx.qualifiedIdentifier().getText()));
    }
}
