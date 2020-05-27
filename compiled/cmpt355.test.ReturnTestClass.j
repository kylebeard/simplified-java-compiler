.version 49 0
.class public super cmpt355/test/ReturnTestClass
.super java/lang/Object
.field public x I
.method public f : ()I
	.code stack 1 locals 1
		sipush 997
		ireturn
	.end code
.end method

.method public g : ()Ljava/lang/String;
	.code stack 1 locals 1
		ldc "OK"
		checkcast java/lang/String
		areturn
	.end code
.end method

.method public h : ()Lcmpt355/test/ReturnTestClass;
	.code stack 1 locals 1
		aload_0
		checkcast cmpt355/test/ReturnTestClass
		areturn
	.end code
.end method

.method public i : ()V
	.code stack 3 locals 1
		return
		aload_0
		iconst_5
		dup_x1
		putfield Field cmpt355/test/ReturnTestClass x I
		pop
		return
	.end code
.end method

.method public <init> : ()V
	.code stack 3 locals 1
		aload_0
		invokespecial Method java/lang/Object <init> ()V
		aload_0
		iconst_0
		dup_x1
		putfield Field cmpt355/test/ReturnTestClass x I
		pop
		return
	.end code
.end method

.end class
