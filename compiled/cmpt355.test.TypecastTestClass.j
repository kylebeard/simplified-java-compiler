.version 49 0
.class public super cmpt355/test/TypecastTestClass
.super java/lang/Object
.field public i I
.field public l J
.field public f F
.field public d D
.field public c C
.field public s Ljava/lang/String;
.field public o Ljava/lang/Object;
.field public s2 Ljava/lang/String;
.method public i2i : ()I
	.code stack 1 locals 1
		aload_0
		getfield Field cmpt355/test/TypecastTestClass i I
		ireturn
	.end code
.end method

.method public i2l : ()J
	.code stack 2 locals 1
		aload_0
		getfield Field cmpt355/test/TypecastTestClass i I
		i2l
		lreturn
	.end code
.end method

.method public i2f : ()F
	.code stack 1 locals 1
		aload_0
		getfield Field cmpt355/test/TypecastTestClass i I
		i2f
		freturn
	.end code
.end method

.method public i2d : ()D
	.code stack 2 locals 1
		aload_0
		getfield Field cmpt355/test/TypecastTestClass i I
		i2d
		dreturn
	.end code
.end method

.method public i2b : ()B
	.code stack 1 locals 1
		aload_0
		getfield Field cmpt355/test/TypecastTestClass i I
		i2b
		ireturn
	.end code
.end method

.method public i2s : ()S
	.code stack 1 locals 1
		aload_0
		getfield Field cmpt355/test/TypecastTestClass i I
		i2s
		ireturn
	.end code
.end method

.method public i2c : ()C
	.code stack 1 locals 1
		aload_0
		getfield Field cmpt355/test/TypecastTestClass i I
		i2c
		ireturn
	.end code
.end method

.method public l2i : ()I
	.code stack 2 locals 1
		aload_0
		getfield Field cmpt355/test/TypecastTestClass l J
		l2i
		ireturn
	.end code
.end method

.method public f2i : ()I
	.code stack 1 locals 1
		aload_0
		getfield Field cmpt355/test/TypecastTestClass f F
		f2i
		ireturn
	.end code
.end method

.method public d2i : ()I
	.code stack 2 locals 1
		aload_0
		getfield Field cmpt355/test/TypecastTestClass d D
		d2i
		ireturn
	.end code
.end method

.method public c2i : ()I
	.code stack 1 locals 1
		aload_0
		getfield Field cmpt355/test/TypecastTestClass c C
		ireturn
	.end code
.end method

.method public l2f : ()F
	.code stack 2 locals 1
		aload_0
		getfield Field cmpt355/test/TypecastTestClass l J
		l2f
		freturn
	.end code
.end method

.method public d2l : ()J
	.code stack 2 locals 1
		aload_0
		getfield Field cmpt355/test/TypecastTestClass d D
		d2l
		lreturn
	.end code
.end method

.method public stringToObject : ()Ljava/lang/Object;
	.code stack 1 locals 1
		aload_0
		getfield Field cmpt355/test/TypecastTestClass s Ljava/lang/String;
		checkcast java/lang/Object
		checkcast java/lang/Object
		areturn
	.end code
.end method

.method public objectToString : ()V
	.code stack 4 locals 1
		aload_0
		ldc ""
		dup
		ifnull L6_ifnull
		invokevirtual Method java/lang/Object toString ()Ljava/lang/String;
		goto L8_endifnull
		L6_ifnull:	pop
		ldc "null"
		L8_endifnull:	aload_0
		getfield Field cmpt355/test/TypecastTestClass o Ljava/lang/Object;
		checkcast java/lang/String
		dup
		ifnull L15_ifnull
		invokevirtual Method java/lang/Object toString ()Ljava/lang/String;
		goto L17_endifnull
		L15_ifnull:	pop
		ldc "null"
		L17_endifnull:	invokevirtual Method java/lang/String concat (Ljava/lang/String;)Ljava/lang/String;
		checkcast java/lang/String
		dup_x1
		putfield Field cmpt355/test/TypecastTestClass s2 Ljava/lang/String;
		pop
		return
	.end code
.end method

.method public static main : ([Ljava/lang/String;)V
	.code stack 0 locals 1
		return
	.end code
.end method

.method public <init> : ()V
	.code stack 5 locals 1
		aload_0
		invokespecial Method java/lang/Object <init> ()V
		aload_0
		bipush 20
		dup_x1
		putfield Field cmpt355/test/TypecastTestClass i I
		pop
		aload_0
		ldc 101010
		i2l
		dup2_x1
		putfield Field cmpt355/test/TypecastTestClass l J
		pop2
		aload_0
		ldc 0.123456F
		dup_x1
		putfield Field cmpt355/test/TypecastTestClass f F
		pop
		aload_0
		ldc2_w 9.87654321
		dneg
		dup2_x1
		putfield Field cmpt355/test/TypecastTestClass d D
		pop2
		aload_0
		bipush 65
		dup_x1
		putfield Field cmpt355/test/TypecastTestClass c C
		pop
		aload_0
		ldc "cheese"
		checkcast java/lang/String
		dup_x1
		putfield Field cmpt355/test/TypecastTestClass s Ljava/lang/String;
		pop
		aload_0
		new java/lang/Object
		dup
		invokespecial Method java/lang/Object <init> ()V
		checkcast java/lang/Object
		dup_x1
		putfield Field cmpt355/test/TypecastTestClass o Ljava/lang/Object;
		pop
		return
	.end code
.end method

.end class
