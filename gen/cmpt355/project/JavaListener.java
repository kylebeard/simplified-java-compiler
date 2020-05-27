// Generated from /home/kathy/IdeaProjects/2020spring_cmpt355_ip5/grammar/Java.g4 by ANTLR 4.8
package cmpt355.project;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link JavaParser}.
 */
public interface JavaListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link JavaParser#qualifiedIdentifier}.
	 * @param ctx the parse tree
	 */
	void enterQualifiedIdentifier(JavaParser.QualifiedIdentifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#qualifiedIdentifier}.
	 * @param ctx the parse tree
	 */
	void exitQualifiedIdentifier(JavaParser.QualifiedIdentifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#accessModifier}.
	 * @param ctx the parse tree
	 */
	void enterAccessModifier(JavaParser.AccessModifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#accessModifier}.
	 * @param ctx the parse tree
	 */
	void exitAccessModifier(JavaParser.AccessModifierContext ctx);
	/**
	 * Enter a parse tree produced by the {@code primitiveType}
	 * labeled alternative in {@link JavaParser#scalarType}.
	 * @param ctx the parse tree
	 */
	void enterPrimitiveType(JavaParser.PrimitiveTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code primitiveType}
	 * labeled alternative in {@link JavaParser#scalarType}.
	 * @param ctx the parse tree
	 */
	void exitPrimitiveType(JavaParser.PrimitiveTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code namedType}
	 * labeled alternative in {@link JavaParser#scalarType}.
	 * @param ctx the parse tree
	 */
	void enterNamedType(JavaParser.NamedTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code namedType}
	 * labeled alternative in {@link JavaParser#scalarType}.
	 * @param ctx the parse tree
	 */
	void exitNamedType(JavaParser.NamedTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code scalarDataType}
	 * labeled alternative in {@link JavaParser#dataType}.
	 * @param ctx the parse tree
	 */
	void enterScalarDataType(JavaParser.ScalarDataTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code scalarDataType}
	 * labeled alternative in {@link JavaParser#dataType}.
	 * @param ctx the parse tree
	 */
	void exitScalarDataType(JavaParser.ScalarDataTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code arrayType}
	 * labeled alternative in {@link JavaParser#dataType}.
	 * @param ctx the parse tree
	 */
	void enterArrayType(JavaParser.ArrayTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code arrayType}
	 * labeled alternative in {@link JavaParser#dataType}.
	 * @param ctx the parse tree
	 */
	void exitArrayType(JavaParser.ArrayTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#returnType}.
	 * @param ctx the parse tree
	 */
	void enterReturnType(JavaParser.ReturnTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#returnType}.
	 * @param ctx the parse tree
	 */
	void exitReturnType(JavaParser.ReturnTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#varNameInitialization}.
	 * @param ctx the parse tree
	 */
	void enterVarNameInitialization(JavaParser.VarNameInitializationContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#varNameInitialization}.
	 * @param ctx the parse tree
	 */
	void exitVarNameInitialization(JavaParser.VarNameInitializationContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#multVarDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterMultVarDeclaration(JavaParser.MultVarDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#multVarDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitMultVarDeclaration(JavaParser.MultVarDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#varDeclarationNoInit}.
	 * @param ctx the parse tree
	 */
	void enterVarDeclarationNoInit(JavaParser.VarDeclarationNoInitContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#varDeclarationNoInit}.
	 * @param ctx the parse tree
	 */
	void exitVarDeclarationNoInit(JavaParser.VarDeclarationNoInitContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#initialization}.
	 * @param ctx the parse tree
	 */
	void enterInitialization(JavaParser.InitializationContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#initialization}.
	 * @param ctx the parse tree
	 */
	void exitInitialization(JavaParser.InitializationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code thisFieldLValue}
	 * labeled alternative in {@link JavaParser#lvalue}.
	 * @param ctx the parse tree
	 */
	void enterThisFieldLValue(JavaParser.ThisFieldLValueContext ctx);
	/**
	 * Exit a parse tree produced by the {@code thisFieldLValue}
	 * labeled alternative in {@link JavaParser#lvalue}.
	 * @param ctx the parse tree
	 */
	void exitThisFieldLValue(JavaParser.ThisFieldLValueContext ctx);
	/**
	 * Enter a parse tree produced by the {@code parenthesizedLValue}
	 * labeled alternative in {@link JavaParser#lvalue}.
	 * @param ctx the parse tree
	 */
	void enterParenthesizedLValue(JavaParser.ParenthesizedLValueContext ctx);
	/**
	 * Exit a parse tree produced by the {@code parenthesizedLValue}
	 * labeled alternative in {@link JavaParser#lvalue}.
	 * @param ctx the parse tree
	 */
	void exitParenthesizedLValue(JavaParser.ParenthesizedLValueContext ctx);
	/**
	 * Enter a parse tree produced by the {@code fieldRefLValue}
	 * labeled alternative in {@link JavaParser#lvalue}.
	 * @param ctx the parse tree
	 */
	void enterFieldRefLValue(JavaParser.FieldRefLValueContext ctx);
	/**
	 * Exit a parse tree produced by the {@code fieldRefLValue}
	 * labeled alternative in {@link JavaParser#lvalue}.
	 * @param ctx the parse tree
	 */
	void exitFieldRefLValue(JavaParser.FieldRefLValueContext ctx);
	/**
	 * Enter a parse tree produced by the {@code subscriptLvalue}
	 * labeled alternative in {@link JavaParser#lvalue}.
	 * @param ctx the parse tree
	 */
	void enterSubscriptLvalue(JavaParser.SubscriptLvalueContext ctx);
	/**
	 * Exit a parse tree produced by the {@code subscriptLvalue}
	 * labeled alternative in {@link JavaParser#lvalue}.
	 * @param ctx the parse tree
	 */
	void exitSubscriptLvalue(JavaParser.SubscriptLvalueContext ctx);
	/**
	 * Enter a parse tree produced by the {@code nameLvalue}
	 * labeled alternative in {@link JavaParser#lvalue}.
	 * @param ctx the parse tree
	 */
	void enterNameLvalue(JavaParser.NameLvalueContext ctx);
	/**
	 * Exit a parse tree produced by the {@code nameLvalue}
	 * labeled alternative in {@link JavaParser#lvalue}.
	 * @param ctx the parse tree
	 */
	void exitNameLvalue(JavaParser.NameLvalueContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#javaFile}.
	 * @param ctx the parse tree
	 */
	void enterJavaFile(JavaParser.JavaFileContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#javaFile}.
	 * @param ctx the parse tree
	 */
	void exitJavaFile(JavaParser.JavaFileContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#packageStatement}.
	 * @param ctx the parse tree
	 */
	void enterPackageStatement(JavaParser.PackageStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#packageStatement}.
	 * @param ctx the parse tree
	 */
	void exitPackageStatement(JavaParser.PackageStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code importClass}
	 * labeled alternative in {@link JavaParser#importStatement}.
	 * @param ctx the parse tree
	 */
	void enterImportClass(JavaParser.ImportClassContext ctx);
	/**
	 * Exit a parse tree produced by the {@code importClass}
	 * labeled alternative in {@link JavaParser#importStatement}.
	 * @param ctx the parse tree
	 */
	void exitImportClass(JavaParser.ImportClassContext ctx);
	/**
	 * Enter a parse tree produced by the {@code importWildcard}
	 * labeled alternative in {@link JavaParser#importStatement}.
	 * @param ctx the parse tree
	 */
	void enterImportWildcard(JavaParser.ImportWildcardContext ctx);
	/**
	 * Exit a parse tree produced by the {@code importWildcard}
	 * labeled alternative in {@link JavaParser#importStatement}.
	 * @param ctx the parse tree
	 */
	void exitImportWildcard(JavaParser.ImportWildcardContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#classDefinition}.
	 * @param ctx the parse tree
	 */
	void enterClassDefinition(JavaParser.ClassDefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#classDefinition}.
	 * @param ctx the parse tree
	 */
	void exitClassDefinition(JavaParser.ClassDefinitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#classModifier}.
	 * @param ctx the parse tree
	 */
	void enterClassModifier(JavaParser.ClassModifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#classModifier}.
	 * @param ctx the parse tree
	 */
	void exitClassModifier(JavaParser.ClassModifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#classBody}.
	 * @param ctx the parse tree
	 */
	void enterClassBody(JavaParser.ClassBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#classBody}.
	 * @param ctx the parse tree
	 */
	void exitClassBody(JavaParser.ClassBodyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code classField}
	 * labeled alternative in {@link JavaParser#classMember}.
	 * @param ctx the parse tree
	 */
	void enterClassField(JavaParser.ClassFieldContext ctx);
	/**
	 * Exit a parse tree produced by the {@code classField}
	 * labeled alternative in {@link JavaParser#classMember}.
	 * @param ctx the parse tree
	 */
	void exitClassField(JavaParser.ClassFieldContext ctx);
	/**
	 * Enter a parse tree produced by the {@code classMethod}
	 * labeled alternative in {@link JavaParser#classMember}.
	 * @param ctx the parse tree
	 */
	void enterClassMethod(JavaParser.ClassMethodContext ctx);
	/**
	 * Exit a parse tree produced by the {@code classMethod}
	 * labeled alternative in {@link JavaParser#classMember}.
	 * @param ctx the parse tree
	 */
	void exitClassMethod(JavaParser.ClassMethodContext ctx);
	/**
	 * Enter a parse tree produced by the {@code classMethodStub}
	 * labeled alternative in {@link JavaParser#classMember}.
	 * @param ctx the parse tree
	 */
	void enterClassMethodStub(JavaParser.ClassMethodStubContext ctx);
	/**
	 * Exit a parse tree produced by the {@code classMethodStub}
	 * labeled alternative in {@link JavaParser#classMember}.
	 * @param ctx the parse tree
	 */
	void exitClassMethodStub(JavaParser.ClassMethodStubContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#classInitializer}.
	 * @param ctx the parse tree
	 */
	void enterClassInitializer(JavaParser.ClassInitializerContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#classInitializer}.
	 * @param ctx the parse tree
	 */
	void exitClassInitializer(JavaParser.ClassInitializerContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#field}.
	 * @param ctx the parse tree
	 */
	void enterField(JavaParser.FieldContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#field}.
	 * @param ctx the parse tree
	 */
	void exitField(JavaParser.FieldContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#fieldModifier}.
	 * @param ctx the parse tree
	 */
	void enterFieldModifier(JavaParser.FieldModifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#fieldModifier}.
	 * @param ctx the parse tree
	 */
	void exitFieldModifier(JavaParser.FieldModifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#methodHeader}.
	 * @param ctx the parse tree
	 */
	void enterMethodHeader(JavaParser.MethodHeaderContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#methodHeader}.
	 * @param ctx the parse tree
	 */
	void exitMethodHeader(JavaParser.MethodHeaderContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#methodModifier}.
	 * @param ctx the parse tree
	 */
	void enterMethodModifier(JavaParser.MethodModifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#methodModifier}.
	 * @param ctx the parse tree
	 */
	void exitMethodModifier(JavaParser.MethodModifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#methodParameter}.
	 * @param ctx the parse tree
	 */
	void enterMethodParameter(JavaParser.MethodParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#methodParameter}.
	 * @param ctx the parse tree
	 */
	void exitMethodParameter(JavaParser.MethodParameterContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#parameterModifier}.
	 * @param ctx the parse tree
	 */
	void enterParameterModifier(JavaParser.ParameterModifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#parameterModifier}.
	 * @param ctx the parse tree
	 */
	void exitParameterModifier(JavaParser.ParameterModifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#method}.
	 * @param ctx the parse tree
	 */
	void enterMethod(JavaParser.MethodContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#method}.
	 * @param ctx the parse tree
	 */
	void exitMethod(JavaParser.MethodContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#methodStub}.
	 * @param ctx the parse tree
	 */
	void enterMethodStub(JavaParser.MethodStubContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#methodStub}.
	 * @param ctx the parse tree
	 */
	void exitMethodStub(JavaParser.MethodStubContext ctx);
	/**
	 * Enter a parse tree produced by the {@code blockStatement}
	 * labeled alternative in {@link JavaParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterBlockStatement(JavaParser.BlockStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code blockStatement}
	 * labeled alternative in {@link JavaParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitBlockStatement(JavaParser.BlockStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code emptyStatement}
	 * labeled alternative in {@link JavaParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterEmptyStatement(JavaParser.EmptyStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code emptyStatement}
	 * labeled alternative in {@link JavaParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitEmptyStatement(JavaParser.EmptyStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code leaf}
	 * labeled alternative in {@link JavaParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterLeaf(JavaParser.LeafContext ctx);
	/**
	 * Exit a parse tree produced by the {@code leaf}
	 * labeled alternative in {@link JavaParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitLeaf(JavaParser.LeafContext ctx);
	/**
	 * Enter a parse tree produced by the {@code returnStatement}
	 * labeled alternative in {@link JavaParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterReturnStatement(JavaParser.ReturnStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code returnStatement}
	 * labeled alternative in {@link JavaParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitReturnStatement(JavaParser.ReturnStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ifStatement}
	 * labeled alternative in {@link JavaParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterIfStatement(JavaParser.IfStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ifStatement}
	 * labeled alternative in {@link JavaParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitIfStatement(JavaParser.IfStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ifElseStatement}
	 * labeled alternative in {@link JavaParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterIfElseStatement(JavaParser.IfElseStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ifElseStatement}
	 * labeled alternative in {@link JavaParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitIfElseStatement(JavaParser.IfElseStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code whileLoop}
	 * labeled alternative in {@link JavaParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterWhileLoop(JavaParser.WhileLoopContext ctx);
	/**
	 * Exit a parse tree produced by the {@code whileLoop}
	 * labeled alternative in {@link JavaParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitWhileLoop(JavaParser.WhileLoopContext ctx);
	/**
	 * Enter a parse tree produced by the {@code doWhileLoop}
	 * labeled alternative in {@link JavaParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterDoWhileLoop(JavaParser.DoWhileLoopContext ctx);
	/**
	 * Exit a parse tree produced by the {@code doWhileLoop}
	 * labeled alternative in {@link JavaParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitDoWhileLoop(JavaParser.DoWhileLoopContext ctx);
	/**
	 * Enter a parse tree produced by the {@code forLoop}
	 * labeled alternative in {@link JavaParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterForLoop(JavaParser.ForLoopContext ctx);
	/**
	 * Exit a parse tree produced by the {@code forLoop}
	 * labeled alternative in {@link JavaParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitForLoop(JavaParser.ForLoopContext ctx);
	/**
	 * Enter a parse tree produced by the {@code enhancedForLoop}
	 * labeled alternative in {@link JavaParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterEnhancedForLoop(JavaParser.EnhancedForLoopContext ctx);
	/**
	 * Exit a parse tree produced by the {@code enhancedForLoop}
	 * labeled alternative in {@link JavaParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitEnhancedForLoop(JavaParser.EnhancedForLoopContext ctx);
	/**
	 * Enter a parse tree produced by the {@code tryStatement}
	 * labeled alternative in {@link JavaParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterTryStatement(JavaParser.TryStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code tryStatement}
	 * labeled alternative in {@link JavaParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitTryStatement(JavaParser.TryStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code synchronizedBlock}
	 * labeled alternative in {@link JavaParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterSynchronizedBlock(JavaParser.SynchronizedBlockContext ctx);
	/**
	 * Exit a parse tree produced by the {@code synchronizedBlock}
	 * labeled alternative in {@link JavaParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitSynchronizedBlock(JavaParser.SynchronizedBlockContext ctx);
	/**
	 * Enter a parse tree produced by the {@code synchronizedMonitorBlock}
	 * labeled alternative in {@link JavaParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterSynchronizedMonitorBlock(JavaParser.SynchronizedMonitorBlockContext ctx);
	/**
	 * Exit a parse tree produced by the {@code synchronizedMonitorBlock}
	 * labeled alternative in {@link JavaParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitSynchronizedMonitorBlock(JavaParser.SynchronizedMonitorBlockContext ctx);
	/**
	 * Enter a parse tree produced by the {@code breakStatement}
	 * labeled alternative in {@link JavaParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterBreakStatement(JavaParser.BreakStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code breakStatement}
	 * labeled alternative in {@link JavaParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitBreakStatement(JavaParser.BreakStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code continueStatement}
	 * labeled alternative in {@link JavaParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterContinueStatement(JavaParser.ContinueStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code continueStatement}
	 * labeled alternative in {@link JavaParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitContinueStatement(JavaParser.ContinueStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code switchStatement}
	 * labeled alternative in {@link JavaParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterSwitchStatement(JavaParser.SwitchStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code switchStatement}
	 * labeled alternative in {@link JavaParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitSwitchStatement(JavaParser.SwitchStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code assertStatement}
	 * labeled alternative in {@link JavaParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterAssertStatement(JavaParser.AssertStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code assertStatement}
	 * labeled alternative in {@link JavaParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitAssertStatement(JavaParser.AssertStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(JavaParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(JavaParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by the {@code declarationStatement}
	 * labeled alternative in {@link JavaParser#leafStatement}.
	 * @param ctx the parse tree
	 */
	void enterDeclarationStatement(JavaParser.DeclarationStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code declarationStatement}
	 * labeled alternative in {@link JavaParser#leafStatement}.
	 * @param ctx the parse tree
	 */
	void exitDeclarationStatement(JavaParser.DeclarationStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code combinedAssignmentStatement}
	 * labeled alternative in {@link JavaParser#leafStatement}.
	 * @param ctx the parse tree
	 */
	void enterCombinedAssignmentStatement(JavaParser.CombinedAssignmentStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code combinedAssignmentStatement}
	 * labeled alternative in {@link JavaParser#leafStatement}.
	 * @param ctx the parse tree
	 */
	void exitCombinedAssignmentStatement(JavaParser.CombinedAssignmentStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code preIncrementStatement}
	 * labeled alternative in {@link JavaParser#leafStatement}.
	 * @param ctx the parse tree
	 */
	void enterPreIncrementStatement(JavaParser.PreIncrementStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code preIncrementStatement}
	 * labeled alternative in {@link JavaParser#leafStatement}.
	 * @param ctx the parse tree
	 */
	void exitPreIncrementStatement(JavaParser.PreIncrementStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code postIncrementStatement}
	 * labeled alternative in {@link JavaParser#leafStatement}.
	 * @param ctx the parse tree
	 */
	void enterPostIncrementStatement(JavaParser.PostIncrementStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code postIncrementStatement}
	 * labeled alternative in {@link JavaParser#leafStatement}.
	 * @param ctx the parse tree
	 */
	void exitPostIncrementStatement(JavaParser.PostIncrementStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code superMethodCallStatement}
	 * labeled alternative in {@link JavaParser#leafStatement}.
	 * @param ctx the parse tree
	 */
	void enterSuperMethodCallStatement(JavaParser.SuperMethodCallStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code superMethodCallStatement}
	 * labeled alternative in {@link JavaParser#leafStatement}.
	 * @param ctx the parse tree
	 */
	void exitSuperMethodCallStatement(JavaParser.SuperMethodCallStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code thisMethodCallStatement}
	 * labeled alternative in {@link JavaParser#leafStatement}.
	 * @param ctx the parse tree
	 */
	void enterThisMethodCallStatement(JavaParser.ThisMethodCallStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code thisMethodCallStatement}
	 * labeled alternative in {@link JavaParser#leafStatement}.
	 * @param ctx the parse tree
	 */
	void exitThisMethodCallStatement(JavaParser.ThisMethodCallStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code methodCallStatement}
	 * labeled alternative in {@link JavaParser#leafStatement}.
	 * @param ctx the parse tree
	 */
	void enterMethodCallStatement(JavaParser.MethodCallStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code methodCallStatement}
	 * labeled alternative in {@link JavaParser#leafStatement}.
	 * @param ctx the parse tree
	 */
	void exitMethodCallStatement(JavaParser.MethodCallStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code superConstructorCallStatement}
	 * labeled alternative in {@link JavaParser#leafStatement}.
	 * @param ctx the parse tree
	 */
	void enterSuperConstructorCallStatement(JavaParser.SuperConstructorCallStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code superConstructorCallStatement}
	 * labeled alternative in {@link JavaParser#leafStatement}.
	 * @param ctx the parse tree
	 */
	void exitSuperConstructorCallStatement(JavaParser.SuperConstructorCallStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code instantiationStatement}
	 * labeled alternative in {@link JavaParser#leafStatement}.
	 * @param ctx the parse tree
	 */
	void enterInstantiationStatement(JavaParser.InstantiationStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code instantiationStatement}
	 * labeled alternative in {@link JavaParser#leafStatement}.
	 * @param ctx the parse tree
	 */
	void exitInstantiationStatement(JavaParser.InstantiationStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code throwStatement}
	 * labeled alternative in {@link JavaParser#leafStatement}.
	 * @param ctx the parse tree
	 */
	void enterThrowStatement(JavaParser.ThrowStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code throwStatement}
	 * labeled alternative in {@link JavaParser#leafStatement}.
	 * @param ctx the parse tree
	 */
	void exitThrowStatement(JavaParser.ThrowStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#localVariableModifier}.
	 * @param ctx the parse tree
	 */
	void enterLocalVariableModifier(JavaParser.LocalVariableModifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#localVariableModifier}.
	 * @param ctx the parse tree
	 */
	void exitLocalVariableModifier(JavaParser.LocalVariableModifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#switchBody}.
	 * @param ctx the parse tree
	 */
	void enterSwitchBody(JavaParser.SwitchBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#switchBody}.
	 * @param ctx the parse tree
	 */
	void exitSwitchBody(JavaParser.SwitchBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#caseLabel}.
	 * @param ctx the parse tree
	 */
	void enterCaseLabel(JavaParser.CaseLabelContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#caseLabel}.
	 * @param ctx the parse tree
	 */
	void exitCaseLabel(JavaParser.CaseLabelContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#defaultLabel}.
	 * @param ctx the parse tree
	 */
	void enterDefaultLabel(JavaParser.DefaultLabelContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#defaultLabel}.
	 * @param ctx the parse tree
	 */
	void exitDefaultLabel(JavaParser.DefaultLabelContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#localVarDeclarationNoInit}.
	 * @param ctx the parse tree
	 */
	void enterLocalVarDeclarationNoInit(JavaParser.LocalVarDeclarationNoInitContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#localVarDeclarationNoInit}.
	 * @param ctx the parse tree
	 */
	void exitLocalVarDeclarationNoInit(JavaParser.LocalVarDeclarationNoInitContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#catchBlock}.
	 * @param ctx the parse tree
	 */
	void enterCatchBlock(JavaParser.CatchBlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#catchBlock}.
	 * @param ctx the parse tree
	 */
	void exitCatchBlock(JavaParser.CatchBlockContext ctx);
	/**
	 * Enter a parse tree produced by the {@code unaryOperator}
	 * labeled alternative in {@link JavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterUnaryOperator(JavaParser.UnaryOperatorContext ctx);
	/**
	 * Exit a parse tree produced by the {@code unaryOperator}
	 * labeled alternative in {@link JavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitUnaryOperator(JavaParser.UnaryOperatorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code combinedAssignmentExpression}
	 * labeled alternative in {@link JavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterCombinedAssignmentExpression(JavaParser.CombinedAssignmentExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code combinedAssignmentExpression}
	 * labeled alternative in {@link JavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitCombinedAssignmentExpression(JavaParser.CombinedAssignmentExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code methodCallExpression}
	 * labeled alternative in {@link JavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterMethodCallExpression(JavaParser.MethodCallExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code methodCallExpression}
	 * labeled alternative in {@link JavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitMethodCallExpression(JavaParser.MethodCallExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code arraySubscript}
	 * labeled alternative in {@link JavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterArraySubscript(JavaParser.ArraySubscriptContext ctx);
	/**
	 * Exit a parse tree produced by the {@code arraySubscript}
	 * labeled alternative in {@link JavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitArraySubscript(JavaParser.ArraySubscriptContext ctx);
	/**
	 * Enter a parse tree produced by the {@code cast}
	 * labeled alternative in {@link JavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterCast(JavaParser.CastContext ctx);
	/**
	 * Exit a parse tree produced by the {@code cast}
	 * labeled alternative in {@link JavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitCast(JavaParser.CastContext ctx);
	/**
	 * Enter a parse tree produced by the {@code parenthesizedExpression}
	 * labeled alternative in {@link JavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterParenthesizedExpression(JavaParser.ParenthesizedExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code parenthesizedExpression}
	 * labeled alternative in {@link JavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitParenthesizedExpression(JavaParser.ParenthesizedExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code arrayInstantiationWithInitializer}
	 * labeled alternative in {@link JavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterArrayInstantiationWithInitializer(JavaParser.ArrayInstantiationWithInitializerContext ctx);
	/**
	 * Exit a parse tree produced by the {@code arrayInstantiationWithInitializer}
	 * labeled alternative in {@link JavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitArrayInstantiationWithInitializer(JavaParser.ArrayInstantiationWithInitializerContext ctx);
	/**
	 * Enter a parse tree produced by the {@code instantiationExpression}
	 * labeled alternative in {@link JavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterInstantiationExpression(JavaParser.InstantiationExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code instantiationExpression}
	 * labeled alternative in {@link JavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitInstantiationExpression(JavaParser.InstantiationExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code floatLiteral}
	 * labeled alternative in {@link JavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterFloatLiteral(JavaParser.FloatLiteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code floatLiteral}
	 * labeled alternative in {@link JavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitFloatLiteral(JavaParser.FloatLiteralContext ctx);
	/**
	 * Enter a parse tree produced by the {@code fieldReference}
	 * labeled alternative in {@link JavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterFieldReference(JavaParser.FieldReferenceContext ctx);
	/**
	 * Exit a parse tree produced by the {@code fieldReference}
	 * labeled alternative in {@link JavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitFieldReference(JavaParser.FieldReferenceContext ctx);
	/**
	 * Enter a parse tree produced by the {@code thisMethodCallExpression}
	 * labeled alternative in {@link JavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterThisMethodCallExpression(JavaParser.ThisMethodCallExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code thisMethodCallExpression}
	 * labeled alternative in {@link JavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitThisMethodCallExpression(JavaParser.ThisMethodCallExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code preIncrementExpression}
	 * labeled alternative in {@link JavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterPreIncrementExpression(JavaParser.PreIncrementExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code preIncrementExpression}
	 * labeled alternative in {@link JavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitPreIncrementExpression(JavaParser.PreIncrementExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code booleanLiteral}
	 * labeled alternative in {@link JavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterBooleanLiteral(JavaParser.BooleanLiteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code booleanLiteral}
	 * labeled alternative in {@link JavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitBooleanLiteral(JavaParser.BooleanLiteralContext ctx);
	/**
	 * Enter a parse tree produced by the {@code nameExpression}
	 * labeled alternative in {@link JavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterNameExpression(JavaParser.NameExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code nameExpression}
	 * labeled alternative in {@link JavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitNameExpression(JavaParser.NameExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code conditionalExpression}
	 * labeled alternative in {@link JavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterConditionalExpression(JavaParser.ConditionalExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code conditionalExpression}
	 * labeled alternative in {@link JavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitConditionalExpression(JavaParser.ConditionalExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code intLiteral}
	 * labeled alternative in {@link JavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterIntLiteral(JavaParser.IntLiteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code intLiteral}
	 * labeled alternative in {@link JavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitIntLiteral(JavaParser.IntLiteralContext ctx);
	/**
	 * Enter a parse tree produced by the {@code classLiteral}
	 * labeled alternative in {@link JavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterClassLiteral(JavaParser.ClassLiteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code classLiteral}
	 * labeled alternative in {@link JavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitClassLiteral(JavaParser.ClassLiteralContext ctx);
	/**
	 * Enter a parse tree produced by the {@code charLiteral}
	 * labeled alternative in {@link JavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterCharLiteral(JavaParser.CharLiteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code charLiteral}
	 * labeled alternative in {@link JavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitCharLiteral(JavaParser.CharLiteralContext ctx);
	/**
	 * Enter a parse tree produced by the {@code superMethodCallExpression}
	 * labeled alternative in {@link JavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterSuperMethodCallExpression(JavaParser.SuperMethodCallExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code superMethodCallExpression}
	 * labeled alternative in {@link JavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitSuperMethodCallExpression(JavaParser.SuperMethodCallExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code arrayInstantiationNoInitializer}
	 * labeled alternative in {@link JavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterArrayInstantiationNoInitializer(JavaParser.ArrayInstantiationNoInitializerContext ctx);
	/**
	 * Exit a parse tree produced by the {@code arrayInstantiationNoInitializer}
	 * labeled alternative in {@link JavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitArrayInstantiationNoInitializer(JavaParser.ArrayInstantiationNoInitializerContext ctx);
	/**
	 * Enter a parse tree produced by the {@code thisReference}
	 * labeled alternative in {@link JavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterThisReference(JavaParser.ThisReferenceContext ctx);
	/**
	 * Exit a parse tree produced by the {@code thisReference}
	 * labeled alternative in {@link JavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitThisReference(JavaParser.ThisReferenceContext ctx);
	/**
	 * Enter a parse tree produced by the {@code instanceof}
	 * labeled alternative in {@link JavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterInstanceof(JavaParser.InstanceofContext ctx);
	/**
	 * Exit a parse tree produced by the {@code instanceof}
	 * labeled alternative in {@link JavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitInstanceof(JavaParser.InstanceofContext ctx);
	/**
	 * Enter a parse tree produced by the {@code null}
	 * labeled alternative in {@link JavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterNull(JavaParser.NullContext ctx);
	/**
	 * Exit a parse tree produced by the {@code null}
	 * labeled alternative in {@link JavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitNull(JavaParser.NullContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stringLiteral}
	 * labeled alternative in {@link JavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterStringLiteral(JavaParser.StringLiteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stringLiteral}
	 * labeled alternative in {@link JavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitStringLiteral(JavaParser.StringLiteralContext ctx);
	/**
	 * Enter a parse tree produced by the {@code binaryOperator}
	 * labeled alternative in {@link JavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterBinaryOperator(JavaParser.BinaryOperatorContext ctx);
	/**
	 * Exit a parse tree produced by the {@code binaryOperator}
	 * labeled alternative in {@link JavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitBinaryOperator(JavaParser.BinaryOperatorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code doubleLiteral}
	 * labeled alternative in {@link JavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterDoubleLiteral(JavaParser.DoubleLiteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code doubleLiteral}
	 * labeled alternative in {@link JavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitDoubleLiteral(JavaParser.DoubleLiteralContext ctx);
	/**
	 * Enter a parse tree produced by the {@code postIncrementExpression}
	 * labeled alternative in {@link JavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterPostIncrementExpression(JavaParser.PostIncrementExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code postIncrementExpression}
	 * labeled alternative in {@link JavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitPostIncrementExpression(JavaParser.PostIncrementExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code longLiteral}
	 * labeled alternative in {@link JavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterLongLiteral(JavaParser.LongLiteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code longLiteral}
	 * labeled alternative in {@link JavaParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitLongLiteral(JavaParser.LongLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#arguments}.
	 * @param ctx the parse tree
	 */
	void enterArguments(JavaParser.ArgumentsContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#arguments}.
	 * @param ctx the parse tree
	 */
	void exitArguments(JavaParser.ArgumentsContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#instantiation}.
	 * @param ctx the parse tree
	 */
	void enterInstantiation(JavaParser.InstantiationContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#instantiation}.
	 * @param ctx the parse tree
	 */
	void exitInstantiation(JavaParser.InstantiationContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#postIncrement}.
	 * @param ctx the parse tree
	 */
	void enterPostIncrement(JavaParser.PostIncrementContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#postIncrement}.
	 * @param ctx the parse tree
	 */
	void exitPostIncrement(JavaParser.PostIncrementContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#preIncrement}.
	 * @param ctx the parse tree
	 */
	void enterPreIncrement(JavaParser.PreIncrementContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#preIncrement}.
	 * @param ctx the parse tree
	 */
	void exitPreIncrement(JavaParser.PreIncrementContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#superMethodCall}.
	 * @param ctx the parse tree
	 */
	void enterSuperMethodCall(JavaParser.SuperMethodCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#superMethodCall}.
	 * @param ctx the parse tree
	 */
	void exitSuperMethodCall(JavaParser.SuperMethodCallContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#thisMethodCall}.
	 * @param ctx the parse tree
	 */
	void enterThisMethodCall(JavaParser.ThisMethodCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#thisMethodCall}.
	 * @param ctx the parse tree
	 */
	void exitThisMethodCall(JavaParser.ThisMethodCallContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#methodCall}.
	 * @param ctx the parse tree
	 */
	void enterMethodCall(JavaParser.MethodCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#methodCall}.
	 * @param ctx the parse tree
	 */
	void exitMethodCall(JavaParser.MethodCallContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#combinedAssignment}.
	 * @param ctx the parse tree
	 */
	void enterCombinedAssignment(JavaParser.CombinedAssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#combinedAssignment}.
	 * @param ctx the parse tree
	 */
	void exitCombinedAssignment(JavaParser.CombinedAssignmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#arrayInitializer}.
	 * @param ctx the parse tree
	 */
	void enterArrayInitializer(JavaParser.ArrayInitializerContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#arrayInitializer}.
	 * @param ctx the parse tree
	 */
	void exitArrayInitializer(JavaParser.ArrayInitializerContext ctx);
	/**
	 * Enter a parse tree produced by {@link JavaParser#arrayInitializerItem}.
	 * @param ctx the parse tree
	 */
	void enterArrayInitializerItem(JavaParser.ArrayInitializerItemContext ctx);
	/**
	 * Exit a parse tree produced by {@link JavaParser#arrayInitializerItem}.
	 * @param ctx the parse tree
	 */
	void exitArrayInitializerItem(JavaParser.ArrayInitializerItemContext ctx);
}