.version 49 0
.class public super cmpt355/test/LocalVariableTestClass
.super java/lang/Object
.method public f : ()I
	.code stack 2 locals 2
		bipush 9
		dup
		istore_1
		pop
		iload_1
		iconst_3
		ishl
		ireturn
	.end code
.end method

.method public g : ()Ljava/lang/String;
	.code stack 3 locals 2
		ldc "I'm writing unit tests"
		checkcast java/lang/String
		dup
		astore_1
		pop
		aload_1
		dup
		ifnull L10_ifnull
		invokevirtual Method java/lang/Object toString ()Ljava/lang/String;
		goto L12_endifnull
		L10_ifnull:	pop
		ldc "null"
		L12_endifnull:	ldc " for the glory of the Empire"
		dup
		ifnull L17_ifnull
		invokevirtual Method java/lang/Object toString ()Ljava/lang/String;
		goto L19_endifnull
		L17_ifnull:	pop
		ldc "null"
		L19_endifnull:	invokevirtual Method java/lang/String concat (Ljava/lang/String;)Ljava/lang/String;
		checkcast java/lang/String
		areturn
	.end code
.end method

.method public h : ()Ljava/lang/Object;
	.code stack 2 locals 2
		aload_0
		checkcast java/lang/Object
		dup
		astore_1
		pop
		aload_1
		checkcast java/lang/Object
		areturn
	.end code
.end method

.method public i : ()I
	.code stack 2 locals 2
		bipush 9
		dup
		istore_1
		pop
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

.method public j : ()Ljava/lang/String;
	.code stack 3 locals 2
		ldc "I'm writing unit tests"
		checkcast java/lang/String
		dup
		astore_1
		pop
		aload_1
		dup
		ifnull L10_ifnull
		invokevirtual Method java/lang/Object toString ()Ljava/lang/String;
		goto L12_endifnull
		L10_ifnull:	pop
		ldc "null"
		L12_endifnull:	ldc " on the couch"
		dup
		ifnull L17_ifnull
		invokevirtual Method java/lang/Object toString ()Ljava/lang/String;
		goto L19_endifnull
		L17_ifnull:	pop
		ldc "null"
		L19_endifnull:	invokevirtual Method java/lang/String concat (Ljava/lang/String;)Ljava/lang/String;
		checkcast java/lang/String
		dup
		astore_1
		pop
		aload_1
		checkcast java/lang/String
		areturn
	.end code
.end method

.method public k : ()Ljava/lang/Object;
	.code stack 2 locals 2
		aload_0
		checkcast java/lang/Object
		dup
		astore_1
		pop
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
