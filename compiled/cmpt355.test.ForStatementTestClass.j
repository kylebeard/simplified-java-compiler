.version 49 0
.class public super cmpt355/test/ForStatementTestClass
.super java/lang/Object
.field private test Lcmpt355/project/codegen/ForStatementTest;
.method public <init> : (Lcmpt355/project/codegen/ForStatementTest;)V
	.code stack 3 locals 2
		aload_0
		invokespecial Method java/lang/Object <init> ()V
		aload_0
		aload_1
		checkcast cmpt355/project/codegen/ForStatementTest
		dup_x1
		putfield Field cmpt355/test/ForStatementTestClass test Lcmpt355/project/codegen/ForStatementTest;
		pop
		return
	.end code
.end method

.method public neverLoop : ()V
	.code stack 1 locals 1
		L0_forloop:	iconst_0
		ifeq L6_endfor
		aload_0
		getfield Field cmpt355/test/ForStatementTestClass test Lcmpt355/project/codegen/ForStatementTest;
		invokevirtual Method cmpt355/project/codegen/ForStatementTest tick ()V
		L5_update:	goto L0_forloop
		L6_endfor:	return
	.end code
.end method

.method public countingLoop : (I)V
	.code stack 2 locals 3
		iconst_0
		dup
		istore_2
		pop
		L4_forloop:	iload_2
		iload_1
		if_icmplt L9_cmp_true
		iconst_0
		goto L10_cmp_end
		L9_cmp_true:	iconst_1
		L10_cmp_end:	ifeq L21_endfor
		aload_0
		getfield Field cmpt355/test/ForStatementTestClass test Lcmpt355/project/codegen/ForStatementTest;
		invokevirtual Method cmpt355/project/codegen/ForStatementTest tick ()V
		L14_update:	iload_2
		iconst_1
		iadd
		dup
		istore_2
		pop
		goto L4_forloop
		L21_endfor:	return
	.end code
.end method

.method public noConditionLoop : ()V
	.code stack 1 locals 1
		L0_forloop:	aload_0
		getfield Field cmpt355/test/ForStatementTestClass test Lcmpt355/project/codegen/ForStatementTest;
		invokevirtual Method cmpt355/project/codegen/ForStatementTest maybeThrow ()V
		L3_update:	goto L0_forloop
		L4_endfor:	return
	.end code
.end method

.method public basicLoop : (I)V
	.code stack 2 locals 3
		aload_0
		getfield Field cmpt355/test/ForStatementTestClass test Lcmpt355/project/codegen/ForStatementTest;
		invokevirtual Method cmpt355/project/codegen/ForStatementTest startLoop ()V
		iconst_0
		dup
		istore_2
		pop
		L7_forloop:	iload_2
		iload_1
		if_icmplt L12_cmp_true
		iconst_0
		goto L13_cmp_end
		L12_cmp_true:	iconst_1
		L13_cmp_end:	ifeq L30_endfor
		aload_0
		getfield Field cmpt355/test/ForStatementTestClass test Lcmpt355/project/codegen/ForStatementTest;
		invokevirtual Method cmpt355/project/codegen/ForStatementTest outerBodyStart ()V
		aload_0
		getfield Field cmpt355/test/ForStatementTestClass test Lcmpt355/project/codegen/ForStatementTest;
		invokevirtual Method cmpt355/project/codegen/ForStatementTest inner ()V
		L20_update:	iload_2
		iconst_1
		iadd
		dup
		istore_2
		pop
		aload_0
		getfield Field cmpt355/test/ForStatementTestClass test Lcmpt355/project/codegen/ForStatementTest;
		invokevirtual Method cmpt355/project/codegen/ForStatementTest outerBodyEnd ()V
		goto L7_forloop
		L30_endfor:	return
	.end code
.end method

.method public nestedLoop : (II)V
	.code stack 3 locals 6
		iload_2
		dup
		istore_3
		pop
		aload_0
		getfield Field cmpt355/test/ForStatementTestClass test Lcmpt355/project/codegen/ForStatementTest;
		invokevirtual Method cmpt355/project/codegen/ForStatementTest startLoop ()V
		iconst_0
		dup
		istore 4
		pop
		L11_forloop:	iload 4
		iload_1
		if_icmplt L16_cmp_true
		iconst_0
		goto L17_cmp_end
		L16_cmp_true:	iconst_1
		L17_cmp_end:	ifeq L60_endfor
		aload_0
		getfield Field cmpt355/test/ForStatementTestClass test Lcmpt355/project/codegen/ForStatementTest;
		invokevirtual Method cmpt355/project/codegen/ForStatementTest outerBodyStart ()V
		iload_3
		dup
		iconst_1
		isub
		istore_3
		dup
		istore_2
		pop
		iconst_0
		dup
		istore 5
		pop
		L33_forloop:	iload 5
		iload_2
		if_icmplt L38_cmp_true
		iconst_0
		goto L39_cmp_end
		L38_cmp_true:	iconst_1
		L39_cmp_end:	ifeq L50_update_endfor
		aload_0
		getfield Field cmpt355/test/ForStatementTestClass test Lcmpt355/project/codegen/ForStatementTest;
		invokevirtual Method cmpt355/project/codegen/ForStatementTest inner ()V
		L43_update:	iload 5
		iconst_1
		iadd
		dup
		istore 5
		pop
		goto L33_forloop
		L50_update_endfor:	iload 4
		iconst_1
		iadd
		dup
		istore 4
		pop
		aload_0
		getfield Field cmpt355/test/ForStatementTestClass test Lcmpt355/project/codegen/ForStatementTest;
		invokevirtual Method cmpt355/project/codegen/ForStatementTest outerBodyEnd ()V
		goto L11_forloop
		L60_endfor:	return
	.end code
.end method

.end class
