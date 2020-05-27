.version 49 0
.class public super cmpt355/test/ParameterTestClass
.super java/lang/Object
.method public f : (I)I
	.code stack 2 locals 2
		iload_1
		iconst_3
		ishl
		ireturn
	.end code
.end method

.method public g : (Ljava/lang/String;)Ljava/lang/String;
	.code stack 3 locals 2
		aload_1
		dup
		ifnull L5_ifnull
		invokevirtual Method java/lang/Object toString ()Ljava/lang/String;
		goto L7_endifnull
		L5_ifnull:	pop
		ldc "null"
		L7_endifnull:	ldc " for the glory of the Empire"
		dup
		ifnull L12_ifnull
		invokevirtual Method java/lang/Object toString ()Ljava/lang/String;
		goto L14_endifnull
		L12_ifnull:	pop
		ldc "null"
		L14_endifnull:	invokevirtual Method java/lang/String concat (Ljava/lang/String;)Ljava/lang/String;
		checkcast java/lang/String
		areturn
	.end code
.end method

.method public h : (Ljava/lang/Object;)Ljava/lang/Object;
	.code stack 1 locals 2
		aload_1
		checkcast java/lang/Object
		areturn
	.end code
.end method

.method public i : (I)I
	.code stack 2 locals 2
		iload_1
		iconst_5
		iand
		dup
		istore_1
		pop
		iload_1
		iconst_1
		ishl
		ireturn
	.end code
.end method

.method public j : (Ljava/lang/String;)Ljava/lang/String;
	.code stack 3 locals 2
		aload_1
		dup
		ifnull L5_ifnull
		invokevirtual Method java/lang/Object toString ()Ljava/lang/String;
		goto L7_endifnull
		L5_ifnull:	pop
		ldc "null"
		L7_endifnull:	ldc " on the couch"
		dup
		ifnull L12_ifnull
		invokevirtual Method java/lang/Object toString ()Ljava/lang/String;
		goto L14_endifnull
		L12_ifnull:	pop
		ldc "null"
		L14_endifnull:	invokevirtual Method java/lang/String concat (Ljava/lang/String;)Ljava/lang/String;
		checkcast java/lang/String
		dup
		astore_1
		pop
		aload_1
		checkcast java/lang/String
		areturn
	.end code
.end method

.method public k : (Ljava/lang/Object;)Ljava/lang/Object;
	.code stack 2 locals 2
		aconst_null
		checkcast java/lang/Object
		dup
		astore_1
		pop
		aload_1
		checkcast java/lang/Object
		areturn
	.end code
.end method

.method public <init> : ()V
	.code stack 1 locals 1
		aload_0
		invokespecial Method java/lang/Object <init> ()V
		return
	.end code
.end method

.end class
