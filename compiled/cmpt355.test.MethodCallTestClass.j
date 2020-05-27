.version 49 0
.class public super cmpt355/test/MethodCallTestClass
.super java/lang/Object
.field private test Lcmpt355/project/codegen/MethodCallTest;
.method public f : ()V
	.code stack 1 locals 1
		aload_0
		getfield Field cmpt355/test/MethodCallTestClass test Lcmpt355/project/codegen/MethodCallTest;
		invokevirtual Method cmpt355/project/codegen/MethodCallTest f ()V
		return
	.end code
.end method

.method public g : (I)V
	.code stack 2 locals 2
		aload_0
		getfield Field cmpt355/test/MethodCallTestClass test Lcmpt355/project/codegen/MethodCallTest;
		iload_1
		invokevirtual Method cmpt355/project/codegen/MethodCallTest g (I)V
		return
	.end code
.end method

.method public h : (C)V
	.code stack 2 locals 2
		aload_0
		getfield Field cmpt355/test/MethodCallTestClass test Lcmpt355/project/codegen/MethodCallTest;
		iload_1
		invokevirtual Method cmpt355/project/codegen/MethodCallTest h (C)V
		return
	.end code
.end method

.method public i : (BJ)V
	.code stack 4 locals 4
		aload_0
		getfield Field cmpt355/test/MethodCallTestClass test Lcmpt355/project/codegen/MethodCallTest;
		iload_1
		lload_2
		invokevirtual Method cmpt355/project/codegen/MethodCallTest i (BJ)V
		return
	.end code
.end method

.method public j : (DS)V
	.code stack 4 locals 4
		aload_0
		getfield Field cmpt355/test/MethodCallTestClass test Lcmpt355/project/codegen/MethodCallTest;
		dload_1
		iload_3
		invokevirtual Method cmpt355/project/codegen/MethodCallTest j (DS)V
		return
	.end code
.end method

.method public k : (Lcmpt355/project/codegen/MethodCallTest;Z)V
	.code stack 2 locals 3
		aload_0
		getfield Field cmpt355/test/MethodCallTestClass test Lcmpt355/project/codegen/MethodCallTest;
		pop
		aload_1
		checkcast cmpt355/project/codegen/MethodCallTest
		iload_2
		invokestatic Method cmpt355/project/codegen/MethodCallTest k (Lcmpt355/project/codegen/MethodCallTest;Z)V
		return
	.end code
.end method

.method public <init> : ()V
	.code stack 3 locals 1
		aload_0
		invokespecial Method java/lang/Object <init> ()V
		aload_0
		new cmpt355/project/codegen/MethodCallTest
		dup
		invokespecial Method cmpt355/project/codegen/MethodCallTest <init> ()V
		checkcast cmpt355/project/codegen/MethodCallTest
		dup_x1
		putfield Field cmpt355/test/MethodCallTestClass test Lcmpt355/project/codegen/MethodCallTest;
		pop
		return
	.end code
.end method

.end class
