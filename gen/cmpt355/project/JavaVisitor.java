// Generated from /home/kathy/IdeaProjects/2020spring_cmpt355_ip5/grammar/Java.g4 by ANTLR 4.8
package cmpt355.project;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link JavaParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface JavaVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link JavaParser#qualifiedIdentifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQualifiedIdentifier(JavaParser.QualifiedIdentifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaParser#accessModifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAccessModifier(JavaParser.AccessModifierContext ctx);
	/**
	 * Visit a parse tree produced by the {@code primitiveType}
	 * labeled alternative in {@link JavaParser#scalarType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimitiveType(JavaParser.PrimitiveTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code namedType}
	 * labeled alternative in {@link JavaParser#scalarType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNamedType(JavaParser.NamedTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code scalarDataType}
	 * labeled alternative in {@link JavaParser#dataType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitScalarDataType(JavaParser.ScalarDataTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code arrayType}
	 * labeled alternative in {@link JavaParser#dataType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayType(JavaParser.ArrayTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaParser#returnType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnType(JavaParser.ReturnTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaParser#varNameInitialization}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarNameInitialization(JavaParser.VarNameInitializationContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaParser#multVarDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultVarDeclaration(JavaParser.MultVarDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaParser#varDeclarationNoInit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDeclarationNoInit(JavaParser.VarDeclarationNoInitContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaParser#initialization}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInitialization(JavaParser.InitializationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code thisFieldLValue}
	 * labeled alternative in {@link JavaParser#lvalue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitThisFieldLValue(JavaParser.ThisFieldLValueContext ctx);
	/**
	 * Visit a parse tree produced by the {@code parenthesizedLValue}
	 * labeled alternative in {@link JavaParser#lvalue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenthesizedLValue(JavaParser.ParenthesizedLValueContext ctx);
	/**
	 * Visit a parse tree produced by the {@code fieldRefLValue}
	 * labeled alternative in {@link JavaParser#lvalue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFieldRefLValue(JavaParser.FieldRefLValueContext ctx);
	/**
	 * Visit a parse tree produced by the {@code subscriptLvalue}
	 * labeled alternative in {@link JavaParser#lvalue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubscriptLvalue(JavaParser.SubscriptLvalueContext ctx);
	/**
	 * Visit a parse tree produced by the {@code nameLvalue}
	 * labeled alternative in {@link JavaParser#lvalue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNameLvalue(JavaParser.NameLvalueContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaParser#javaFile}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJavaFile(JavaParser.JavaFileContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaParser#packageStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPackageStatement(JavaParser.PackageStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code importClass}
	 * labeled alternative in {@link JavaParser#importStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImportClass(JavaParser.ImportClassContext ctx);
	/**
	 * Visit a parse tree produced by the {@code importWildcard}
	 * labeled alternative in {@link JavaParser#importStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImportWildcard(JavaParser.ImportWildcardContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaParser#classDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassDefinition(JavaParser.ClassDefinitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaParser#classModifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassModifier(JavaParser.ClassModifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaParser#classBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassBody(JavaParser.ClassBodyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code classField}
	 * labeled alternative in {@link JavaParser#classMember}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassField(JavaParser.ClassFieldContext ctx);
	/**
	 * Visit a parse tree produced by the {@code classMethod}
	 * labeled alternative in {@link JavaParser#classMember}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassMethod(JavaParser.ClassMethodContext ctx);
	/**
	 * Visit a parse tree produced by the {@code classMethodStub}
	 * labeled alternative in {@link JavaParser#classMember}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassMethodStub(JavaParser.ClassMethodStubContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaParser#classInitializer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassInitializer(JavaParser.ClassInitializerContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaParser#field}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitField(JavaParser.FieldContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaParser#fieldModifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFieldModifier(JavaParser.FieldModifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaParser#methodHeader}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethodHeader(JavaParser.MethodHeaderContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaParser#methodModifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethodModifier(JavaParser.MethodModifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaParser#methodParameter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethodParameter(JavaParser.MethodParameterContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaParser#parameterModifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameterModifier(JavaParser.ParameterModifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaParser#method}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethod(JavaParser.MethodContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaParser#methodStub}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethodStub(JavaParser.MethodStubContext ctx);
	/**
	 * Visit a parse tree produced by the {@code blockStatement}
	 * labeled alternative in {@link JavaParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlockStatement(JavaParser.BlockStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code emptyStatement}
	 * labeled alternative in {@link JavaParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEmptyStatement(JavaParser.EmptyStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code leaf}
	 * labeled alternative in {@link JavaParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLeaf(JavaParser.LeafContext ctx);
	/**
	 * Visit a parse tree produced by the {@code returnStatement}
	 * labeled alternative in {@link JavaParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnStatement(JavaParser.ReturnStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ifStatement}
	 * labeled alternative in {@link JavaParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStatement(JavaParser.IfStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ifElseStatement}
	 * labeled alternative in {@link JavaParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfElseStatement(JavaParser.IfElseStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code whileLoop}
	 * labeled alternative in {@link JavaParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileLoop(JavaParser.WhileLoopContext ctx);
	/**
	 * Visit a parse tree produced by the {@code doWhileLoop}
	 * labeled alternative in {@link JavaParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDoWhileLoop(JavaParser.DoWhileLoopContext ctx);
	/**
	 * Visit a parse tree produced by the {@code forLoop}
	 * labeled alternative in {@link JavaParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForLoop(JavaParser.ForLoopContext ctx);
	/**
	 * Visit a parse tree produced by the {@code enhancedForLoop}
	 * labeled alternative in {@link JavaParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEnhancedForLoop(JavaParser.EnhancedForLoopContext ctx);
	/**
	 * Visit a parse tree produced by the {@code tryStatement}
	 * labeled alternative in {@link JavaParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTryStatement(JavaParser.TryStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code synchronizedBlock}
	 * labeled alternative in {@link JavaParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSynchronizedBlock(JavaParser.SynchronizedBlockContext ctx);
	/**
	 * Visit a parse tree produced by the {@code synchronizedMonitorBlock}
	 * labeled alternative in {@link JavaParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSynchronizedMonitorBlock(JavaParser.SynchronizedMonitorBlockContext ctx);
	/**
	 * Visit a parse tree produced by the {@code breakStatement}
	 * labeled alternative in {@link JavaParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBreakStatement(JavaParser.BreakStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code continueStatement}
	 * labeled alternative in {@link JavaParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContinueStatement(JavaParser.ContinueStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code switchStatement}
	 * labeled alternative in {@link JavaParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSwitchStatement(JavaParser.SwitchStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code assertStatement}
	 * labeled alternative in {@link JavaParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssertStatement(JavaParser.AssertStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(JavaParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by the {@code declarationStatement}
	 * labeled alternative in {@link JavaParser#leafStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclarationStatement(JavaParser.DeclarationStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code combinedAssignmentStatement}
	 * labeled alternative in {@link JavaParser#leafStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCombinedAssignmentStatement(JavaParser.CombinedAssignmentStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code preIncrementStatement}
	 * labeled alternative in {@link JavaParser#leafStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPreIncrementStatement(JavaParser.PreIncrementStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code postIncrementStatement}
	 * labeled alternative in {@link JavaParser#leafStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPostIncrementStatement(JavaParser.PostIncrementStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code superMethodCallStatement}
	 * labeled alternative in {@link JavaParser#leafStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSuperMethodCallStatement(JavaParser.SuperMethodCallStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code thisMethodCallStatement}
	 * labeled alternative in {@link JavaParser#leafStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitThisMethodCallStatement(JavaParser.ThisMethodCallStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code methodCallStatement}
	 * labeled alternative in {@link JavaParser#leafStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethodCallStatement(JavaParser.MethodCallStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code superConstructorCallStatement}
	 * labeled alternative in {@link JavaParser#leafStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSuperConstructorCallStatement(JavaParser.SuperConstructorCallStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code instantiationStatement}
	 * labeled alternative in {@link JavaParser#leafStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstantiationStatement(JavaParser.InstantiationStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code throwStatement}
	 * labeled alternative in {@link JavaParser#leafStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitThrowStatement(JavaParser.ThrowStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaParser#localVariableModifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLocalVariableModifier(JavaParser.LocalVariableModifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaParser#switchBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSwitchBody(JavaParser.SwitchBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaParser#caseLabel}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCaseLabel(JavaParser.CaseLabelContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaParser#defaultLabel}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefaultLabel(JavaParser.DefaultLabelContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaParser#localVarDeclarationNoInit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLocalVarDeclarationNoInit(JavaParser.LocalVarDeclarationNoInitContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaParser#catchBlock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCatchBlock(JavaParser.CatchBlockContext ctx);
	/**
	 * Visit a parse tree produced by the {@code unaryOperator}
	 * labeled alternative in {@link JavaParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryOperator(JavaParser.UnaryOperatorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code combinedAssignmentExpression}
	 * labeled alternative in {@link JavaParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCombinedAssignmentExpression(JavaParser.CombinedAssignmentExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code methodCallExpression}
	 * labeled alternative in {@link JavaParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethodCallExpression(JavaParser.MethodCallExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code arraySubscript}
	 * labeled alternative in {@link JavaParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArraySubscript(JavaParser.ArraySubscriptContext ctx);
	/**
	 * Visit a parse tree produced by the {@code cast}
	 * labeled alternative in {@link JavaParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCast(JavaParser.CastContext ctx);
	/**
	 * Visit a parse tree produced by the {@code parenthesizedExpression}
	 * labeled alternative in {@link JavaParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenthesizedExpression(JavaParser.ParenthesizedExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code arrayInstantiationWithInitializer}
	 * labeled alternative in {@link JavaParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayInstantiationWithInitializer(JavaParser.ArrayInstantiationWithInitializerContext ctx);
	/**
	 * Visit a parse tree produced by the {@code instantiationExpression}
	 * labeled alternative in {@link JavaParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstantiationExpression(JavaParser.InstantiationExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code floatLiteral}
	 * labeled alternative in {@link JavaParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFloatLiteral(JavaParser.FloatLiteralContext ctx);
	/**
	 * Visit a parse tree produced by the {@code fieldReference}
	 * labeled alternative in {@link JavaParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFieldReference(JavaParser.FieldReferenceContext ctx);
	/**
	 * Visit a parse tree produced by the {@code thisMethodCallExpression}
	 * labeled alternative in {@link JavaParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitThisMethodCallExpression(JavaParser.ThisMethodCallExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code preIncrementExpression}
	 * labeled alternative in {@link JavaParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPreIncrementExpression(JavaParser.PreIncrementExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code booleanLiteral}
	 * labeled alternative in {@link JavaParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooleanLiteral(JavaParser.BooleanLiteralContext ctx);
	/**
	 * Visit a parse tree produced by the {@code nameExpression}
	 * labeled alternative in {@link JavaParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNameExpression(JavaParser.NameExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code conditionalExpression}
	 * labeled alternative in {@link JavaParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConditionalExpression(JavaParser.ConditionalExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code intLiteral}
	 * labeled alternative in {@link JavaParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntLiteral(JavaParser.IntLiteralContext ctx);
	/**
	 * Visit a parse tree produced by the {@code classLiteral}
	 * labeled alternative in {@link JavaParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassLiteral(JavaParser.ClassLiteralContext ctx);
	/**
	 * Visit a parse tree produced by the {@code charLiteral}
	 * labeled alternative in {@link JavaParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCharLiteral(JavaParser.CharLiteralContext ctx);
	/**
	 * Visit a parse tree produced by the {@code superMethodCallExpression}
	 * labeled alternative in {@link JavaParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSuperMethodCallExpression(JavaParser.SuperMethodCallExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code arrayInstantiationNoInitializer}
	 * labeled alternative in {@link JavaParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayInstantiationNoInitializer(JavaParser.ArrayInstantiationNoInitializerContext ctx);
	/**
	 * Visit a parse tree produced by the {@code thisReference}
	 * labeled alternative in {@link JavaParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitThisReference(JavaParser.ThisReferenceContext ctx);
	/**
	 * Visit a parse tree produced by the {@code instanceof}
	 * labeled alternative in {@link JavaParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstanceof(JavaParser.InstanceofContext ctx);
	/**
	 * Visit a parse tree produced by the {@code null}
	 * labeled alternative in {@link JavaParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNull(JavaParser.NullContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stringLiteral}
	 * labeled alternative in {@link JavaParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringLiteral(JavaParser.StringLiteralContext ctx);
	/**
	 * Visit a parse tree produced by the {@code binaryOperator}
	 * labeled alternative in {@link JavaParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinaryOperator(JavaParser.BinaryOperatorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code doubleLiteral}
	 * labeled alternative in {@link JavaParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDoubleLiteral(JavaParser.DoubleLiteralContext ctx);
	/**
	 * Visit a parse tree produced by the {@code postIncrementExpression}
	 * labeled alternative in {@link JavaParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPostIncrementExpression(JavaParser.PostIncrementExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code longLiteral}
	 * labeled alternative in {@link JavaParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLongLiteral(JavaParser.LongLiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaParser#arguments}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArguments(JavaParser.ArgumentsContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaParser#instantiation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstantiation(JavaParser.InstantiationContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaParser#postIncrement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPostIncrement(JavaParser.PostIncrementContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaParser#preIncrement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPreIncrement(JavaParser.PreIncrementContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaParser#superMethodCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSuperMethodCall(JavaParser.SuperMethodCallContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaParser#thisMethodCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitThisMethodCall(JavaParser.ThisMethodCallContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaParser#methodCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethodCall(JavaParser.MethodCallContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaParser#combinedAssignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCombinedAssignment(JavaParser.CombinedAssignmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaParser#arrayInitializer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayInitializer(JavaParser.ArrayInitializerContext ctx);
	/**
	 * Visit a parse tree produced by {@link JavaParser#arrayInitializerItem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayInitializerItem(JavaParser.ArrayInitializerItemContext ctx);
}