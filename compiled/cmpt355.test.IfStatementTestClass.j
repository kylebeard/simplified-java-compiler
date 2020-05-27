.version 49 0
.class public super cmpt355/test/IfStatementTestClass
.super java/lang/Object
.field private test Lcmpt355/project/codegen/IfStatementTest;
.method public <init> : (Lcmpt355/project/codegen/IfStatementTest;)V
	.code stack 3 locals 2
		aload_0
		invokespecial Method java/lang/Object <init> ()V
		aload_0
		aload_1
		checkcast cmpt355/project/codegen/IfStatementTest
		dup_x1
		putfield Field cmpt355/test/IfStatementTestClass test Lcmpt355/project/codegen/IfStatementTest;
		pop
		return
	.end code
.end method

.method public simpleIf : ()V
	.code stack 1 locals 1
		iconst_1
		ifeq L5_endif
		aload_0
		getfield Field cmpt355/test/IfStatementTestClass test Lcmpt355/project/codegen/IfStatementTest;
		invokevirtual Method cmpt355/project/codegen/IfStatementTest f ()V
		L5_endif:	iconst_0
		ifeq L10_endif
		aload_0
		getfield Field cmpt355/test/IfStatementTestClass test Lcmpt355/project/codegen/IfStatementTest;
		invokevirtual Method cmpt355/project/codegen/IfStatementTest g ()V
		L10_endif:	return
	.end code
.end method

.method public ifElse : (I)V
	.code stack 2 locals 2
		iload_1
		iconst_0
		if_icmpgt L5_cmp_true
		iconst_0
		goto L6_cmp_end
		L5_cmp_true:	iconst_1
		L6_cmp_end:	ifeq L11_falseBlock
		aload_0
		getfield Field cmpt355/test/IfStatementTestClass test Lcmpt355/project/codegen/IfStatementTest;
		invokevirtual Method cmpt355/project/codegen/IfStatementTest f ()V
		goto L14_endif
		L11_falseBlock:	aload_0
		getfield Field cmpt355/test/IfStatementTestClass test Lcmpt355/project/codegen/IfStatementTest;
		invokevirtual Method cmpt355/project/codegen/IfStatementTest g ()V
		L14_endif:	return
	.end code
.end method

.method public ifIfElse : (II)V
	.code stack 2 locals 3
		iload_1
		iconst_0
		if_icmpgt L5_cmp_true
		iconst_0
		goto L6_cmp_end
		L5_cmp_true:	iconst_1
		L6_cmp_end:	ifeq L10_endif
		aload_0
		getfield Field cmpt355/test/IfStatementTestClass test Lcmpt355/project/codegen/IfStatementTest;
		invokevirtual Method cmpt355/project/codegen/IfStatementTest f ()V
		L10_endif:	iload_2
		iconst_0
		if_icmpgt L15_cmp_true
		iconst_0
		goto L16_cmp_end
		L15_cmp_true:	iconst_1
		L16_cmp_end:	ifeq L21_falseBlock
		aload_0
		getfield Field cmpt355/test/IfStatementTestClass test Lcmpt355/project/codegen/IfStatementTest;
		invokevirtual Method cmpt355/project/codegen/IfStatementTest g ()V
		goto L24_endif
		L21_falseBlock:	aload_0
		getfield Field cmpt355/test/IfStatementTestClass test Lcmpt355/project/codegen/IfStatementTest;
		invokevirtual Method cmpt355/project/codegen/IfStatementTest h ()V
		L24_endif:	return
	.end code
.end method

.method public ifElseIfElse : (II)V
	.code stack 2 locals 3
		iload_1
		iconst_0
		if_icmpgt L5_cmp_true
		iconst_0
		goto L6_cmp_end
		L5_cmp_true:	iconst_1
		L6_cmp_end:	ifeq L11_falseBlock
		aload_0
		getfield Field cmpt355/test/IfStatementTestClass test Lcmpt355/project/codegen/IfStatementTest;
		invokevirtual Method cmpt355/project/codegen/IfStatementTest f ()V
		goto L25_endif_endif
		L11_falseBlock:	iload_2
		iconst_0
		if_icmpgt L16_cmp_true
		iconst_0
		goto L17_cmp_end
		L16_cmp_true:	iconst_1
		L17_cmp_end:	ifeq L22_falseBlock
		aload_0
		getfield Field cmpt355/test/IfStatementTestClass test Lcmpt355/project/codegen/IfStatementTest;
		invokevirtual Method cmpt355/project/codegen/IfStatementTest g ()V
		goto L25_endif_endif
		L22_falseBlock:	aload_0
		getfield Field cmpt355/test/IfStatementTestClass test Lcmpt355/project/codegen/IfStatementTest;
		invokevirtual Method cmpt355/project/codegen/IfStatementTest h ()V
		L25_endif_endif:	return
	.end code
.end method

.method public nested : (II)V
	.code stack 2 locals 3
		iload_1
		iload_2
		if_icmpgt L5_cmp_true
		iconst_0
		goto L6_cmp_end
		L5_cmp_true:	iconst_1
		L6_cmp_end:	ifeq L22_falseBlock
		iload_2
		iconst_0
		if_icmpgt L12_cmp_true
		iconst_0
		goto L13_cmp_end
		L12_cmp_true:	iconst_1
		L13_cmp_end:	ifeq L18_falseBlock
		aload_0
		getfield Field cmpt355/test/IfStatementTestClass test Lcmpt355/project/codegen/IfStatementTest;
		invokevirtual Method cmpt355/project/codegen/IfStatementTest f ()V
		goto L21_endif
		L18_falseBlock:	aload_0
		getfield Field cmpt355/test/IfStatementTestClass test Lcmpt355/project/codegen/IfStatementTest;
		invokevirtual Method cmpt355/project/codegen/IfStatementTest g ()V
		L21_endif:	goto L25_endif
		L22_falseBlock:	aload_0
		getfield Field cmpt355/test/IfStatementTestClass test Lcmpt355/project/codegen/IfStatementTest;
		invokevirtual Method cmpt355/project/codegen/IfStatementTest h ()V
		L25_endif:	return
	.end code
.end method

.end class
