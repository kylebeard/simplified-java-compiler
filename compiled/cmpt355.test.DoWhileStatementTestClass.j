.version 49 0
.class public super cmpt355/test/DoWhileStatementTestClass
.super java/lang/Object
.field private test Lcmpt355/project/codegen/DoWhileStatementTest;
.method public <init> : (Lcmpt355/project/codegen/DoWhileStatementTest;)V
	.code stack 3 locals 2
		aload_0
		invokespecial Method java/lang/Object <init> ()V
		aload_0
		aload_1
		checkcast cmpt355/project/codegen/DoWhileStatementTest
		dup_x1
		putfield Field cmpt355/test/DoWhileStatementTestClass test Lcmpt355/project/codegen/DoWhileStatementTest;
		pop
		return
	.end code
.end method

.method public loopOnce : ()V
	.code stack 1 locals 1
		L0_dowhile:	aload_0
		getfield Field cmpt355/test/DoWhileStatementTestClass test Lcmpt355/project/codegen/DoWhileStatementTest;
		invokevirtual Method cmpt355/project/codegen/DoWhileStatementTest tick ()V
		L3_dowhile_condition:	iconst_0
		ifne L0_dowhile
		L5_end_dowhile:	return
	.end code
.end method

.method public loop : (I)V
	.code stack 2 locals 2
		L0_dowhile:	aload_0
		getfield Field cmpt355/test/DoWhileStatementTestClass test Lcmpt355/project/codegen/DoWhileStatementTest;
		invokevirtual Method cmpt355/project/codegen/DoWhileStatementTest tick ()V
		L3_dowhile_condition:	iload_1
		iconst_1
		isub
		dup
		istore_1
		iconst_0
		if_icmpgt L12_cmp_true
		iconst_0
		goto L13_cmp_end
		L12_cmp_true:	iconst_1
		L13_cmp_end:	ifne L0_dowhile
		L14_end_dowhile:	return
	.end code
.end method

.method public loopLoop : (II)V
	.code stack 2 locals 3
		L0_dowhile:	aload_0
		getfield Field cmpt355/test/DoWhileStatementTestClass test Lcmpt355/project/codegen/DoWhileStatementTest;
		invokevirtual Method cmpt355/project/codegen/DoWhileStatementTest tick ()V
		L3_dowhile_condition:	iload_1
		iconst_1
		isub
		dup
		istore_1
		iconst_0
		if_icmpgt L12_cmp_true
		iconst_0
		goto L13_cmp_end
		L12_cmp_true:	iconst_1
		L13_cmp_end:	ifne L0_dowhile
		L14_end_dowhile_dowhile:	aload_0
		getfield Field cmpt355/test/DoWhileStatementTestClass test Lcmpt355/project/codegen/DoWhileStatementTest;
		invokevirtual Method cmpt355/project/codegen/DoWhileStatementTest tick2 ()V
		L17_dowhile_condition:	iload_2
		iconst_1
		isub
		dup
		istore_2
		iconst_0
		if_icmpgt L26_cmp_true
		iconst_0
		goto L27_cmp_end
		L26_cmp_true:	iconst_1
		L27_cmp_end:	ifne L14_end_dowhile_dowhile
		L28_end_dowhile:	return
	.end code
.end method

.method public nestedLoop : (II)V
	.code stack 3 locals 4
		iload_2
		dup
		istore_3
		pop
		L4_dowhile:	aload_0
		getfield Field cmpt355/test/DoWhileStatementTestClass test Lcmpt355/project/codegen/DoWhileStatementTest;
		invokevirtual Method cmpt355/project/codegen/DoWhileStatementTest outerBodyStart ()V
		iload_3
		dup
		iconst_1
		isub
		istore_3
		dup
		istore_2
		pop
		L15_dowhile:	aload_0
		getfield Field cmpt355/test/DoWhileStatementTestClass test Lcmpt355/project/codegen/DoWhileStatementTest;
		invokevirtual Method cmpt355/project/codegen/DoWhileStatementTest inner ()V
		L18_dowhile_condition:	iload_2
		iconst_1
		isub
		dup
		istore_2
		iconst_0
		if_icmpgt L27_cmp_true
		iconst_0
		goto L28_cmp_end
		L27_cmp_true:	iconst_1
		L28_cmp_end:	ifne L15_dowhile
		L29_end_dowhile:	aload_0
		getfield Field cmpt355/test/DoWhileStatementTestClass test Lcmpt355/project/codegen/DoWhileStatementTest;
		invokevirtual Method cmpt355/project/codegen/DoWhileStatementTest outerBodyEnd ()V
		L32_dowhile_condition:	iload_1
		iconst_1
		isub
		dup
		istore_1
		iconst_0
		if_icmpgt L41_cmp_true
		iconst_0
		goto L42_cmp_end
		L41_cmp_true:	iconst_1
		L42_cmp_end:	ifne L4_dowhile
		L43_end_dowhile:	return
	.end code
.end method

.end class
