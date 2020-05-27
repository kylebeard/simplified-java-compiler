.version 49 0
.class public super cmpt355/test/ArraySubscriptTestClass
.super java/lang/Object
.method public byteArray : ()B
	.code stack 5 locals 2
		iconst_5
		newarray byte
		checkcast [B
		dup
		astore_1
		pop
		aload_1
		iconst_0
		bipush 27
		i2b
		dup_x2
		bastore
		pop
		aload_1
		iconst_1
		bipush 7
		i2b
		dup_x2
		bastore
		pop
		aload_1
		iconst_2
		aload_1
		iconst_0
		baload
		aload_1
		iconst_1
		baload
		ishl
		i2b
		dup_x2
		bastore
		pop
		aload_1
		iconst_3
		aload_1
		iconst_2
		baload
		aload_1
		iconst_0
		baload
		ior
		i2b
		dup_x2
		bastore
		pop
		aload_1
		iconst_3
		baload
		ireturn
	.end code
.end method

.method public shortArray : ()S
	.code stack 5 locals 2
		iconst_5
		newarray short
		checkcast [S
		dup
		astore_1
		pop
		aload_1
		iconst_0
		bipush 27
		i2s
		dup_x2
		sastore
		pop
		aload_1
		iconst_1
		bipush 7
		i2s
		dup_x2
		sastore
		pop
		aload_1
		iconst_2
		aload_1
		iconst_0
		saload
		aload_1
		iconst_1
		saload
		ishl
		i2s
		dup_x2
		sastore
		pop
		aload_1
		iconst_3
		aload_1
		iconst_2
		saload
		aload_1
		iconst_0
		saload
		ior
		i2s
		dup_x2
		sastore
		pop
		aload_1
		iconst_3
		saload
		ireturn
	.end code
.end method

.method public charArray : ()C
	.code stack 5 locals 2
		iconst_5
		newarray char
		checkcast [C
		dup
		astore_1
		pop
		aload_1
		iconst_0
		bipush 27
		i2c
		dup_x2
		castore
		pop
		aload_1
		iconst_1
		bipush 7
		i2c
		dup_x2
		castore
		pop
		aload_1
		iconst_2
		aload_1
		iconst_0
		caload
		aload_1
		iconst_1
		caload
		ishl
		i2c
		dup_x2
		castore
		pop
		aload_1
		iconst_3
		aload_1
		iconst_2
		caload
		aload_1
		iconst_0
		caload
		ior
		i2c
		dup_x2
		castore
		pop
		aload_1
		iconst_3
		caload
		ireturn
	.end code
.end method

.method public intArray : ()I
	.code stack 5 locals 2
		iconst_5
		newarray int
		checkcast [I
		dup
		astore_1
		pop
		aload_1
		iconst_0
		bipush 27
		dup_x2
		iastore
		pop
		aload_1
		iconst_1
		bipush 7
		dup_x2
		iastore
		pop
		aload_1
		iconst_2
		aload_1
		iconst_0
		iaload
		aload_1
		iconst_1
		iaload
		ishl
		dup_x2
		iastore
		pop
		aload_1
		iconst_3
		aload_1
		iconst_2
		iaload
		aload_1
		iconst_0
		iaload
		ior
		dup_x2
		iastore
		pop
		aload_1
		iconst_3
		iaload
		ireturn
	.end code
.end method

.method public longArray : ()J
	.code stack 6 locals 2
		iconst_5
		newarray long
		checkcast [J
		dup
		astore_1
		pop
		aload_1
		iconst_0
		bipush 27
		i2l
		dup2_x2
		lastore
		pop2
		aload_1
		iconst_1
		bipush 7
		i2l
		dup2_x2
		lastore
		pop2
		aload_1
		iconst_2
		aload_1
		iconst_0
		laload
		aload_1
		iconst_1
		laload
		l2i
		lshl
		dup2_x2
		lastore
		pop2
		aload_1
		iconst_3
		aload_1
		iconst_2
		laload
		aload_1
		iconst_0
		laload
		lor
		dup2_x2
		lastore
		pop2
		aload_1
		iconst_3
		laload
		lreturn
	.end code
.end method

.method public floatArray : ()F
	.code stack 5 locals 2
		iconst_5
		newarray float
		checkcast [F
		dup
		astore_1
		pop
		aload_1
		iconst_0
		ldc 8287.384F
		fneg
		dup_x2
		fastore
		pop
		aload_1
		iconst_1
		ldc 2273.3845F
		dup_x2
		fastore
		pop
		aload_1
		iconst_2
		aload_1
		iconst_0
		faload
		f2i
		aload_1
		iconst_1
		faload
		f2i
		ixor
		i2f
		dup_x2
		fastore
		pop
		aload_1
		iconst_3
		aload_1
		iconst_2
		faload
		f2i
		bipush 11
		iushr
		i2f
		dup_x2
		fastore
		pop
		aload_1
		iconst_3
		faload
		freturn
	.end code
.end method

.method public doubleArray : ()D
	.code stack 6 locals 2
		iconst_5
		newarray double
		checkcast [D
		dup
		astore_1
		pop
		aload_1
		iconst_0
		ldc2_w 8287.384
		dneg
		dup2_x2
		dastore
		pop2
		aload_1
		iconst_1
		ldc2_w 2273.38445
		dup2_x2
		dastore
		pop2
		aload_1
		iconst_2
		aload_1
		iconst_0
		daload
		d2i
		aload_1
		iconst_1
		daload
		d2i
		ixor
		i2d
		dup2_x2
		dastore
		pop2
		aload_1
		iconst_3
		aload_1
		iconst_2
		daload
		d2i
		bipush 11
		iushr
		i2d
		dup2_x2
		dastore
		pop2
		aload_1
		iconst_3
		daload
		dreturn
	.end code
.end method

.method public booleanArray : ()Z
	.code stack 4 locals 2
		iconst_3
		newarray boolean
		checkcast [Z
		dup
		astore_1
		pop
		aload_1
		iconst_0
		iconst_1
		dup_x2
		bastore
		pop
		aload_1
		iconst_1
		aload_1
		iconst_0
		baload
		iconst_0
		ior
		dup_x2
		bastore
		pop
		aload_1
		iconst_2
		aload_1
		iconst_1
		baload
		iconst_1
		ixor
		dup_x2
		bastore
		pop
		aload_1
		iconst_2
		baload
		ireturn
	.end code
.end method

.method public stringArray : ()Ljava/lang/String;
	.code stack 5 locals 2
		iconst_5
		anewarray java/lang/String
		checkcast [Ljava/lang/String;
		dup
		astore_1
		pop
		aload_1
		iconst_0
		ldc "placebos"
		checkcast java/lang/String
		dup_x2
		aastore
		pop
		aload_1
		iconst_1
		ldc "weatherproof "
		dup
		ifnull L20_ifnull
		invokevirtual Method java/lang/Object toString ()Ljava/lang/String;
		goto L22_endifnull
		L20_ifnull:	pop
		ldc "null"
		L22_endifnull:	aload_1
		iconst_0
		aaload
		dup
		ifnull L29_ifnull
		invokevirtual Method java/lang/Object toString ()Ljava/lang/String;
		goto L31_endifnull
		L29_ifnull:	pop
		ldc "null"
		L31_endifnull:	invokevirtual Method java/lang/String concat (Ljava/lang/String;)Ljava/lang/String;
		checkcast java/lang/String
		dup_x2
		aastore
		pop
		aload_1
		iconst_2
		aload_1
		iconst_1
		aaload
		dup
		ifnull L45_ifnull
		invokevirtual Method java/lang/Object toString ()Ljava/lang/String;
		goto L47_endifnull
		L45_ifnull:	pop
		ldc "null"
		L47_endifnull:	ldc " "
		dup
		ifnull L52_ifnull
		invokevirtual Method java/lang/Object toString ()Ljava/lang/String;
		goto L54_endifnull
		L52_ifnull:	pop
		ldc "null"
		L54_endifnull:	invokevirtual Method java/lang/String concat (Ljava/lang/String;)Ljava/lang/String;
		dup
		ifnull L59_ifnull
		invokevirtual Method java/lang/Object toString ()Ljava/lang/String;
		goto L61_endifnull
		L59_ifnull:	pop
		ldc "null"
		L61_endifnull:	aload_1
		iconst_0
		aaload
		dup
		ifnull L68_ifnull
		invokevirtual Method java/lang/Object toString ()Ljava/lang/String;
		goto L70_endifnull
		L68_ifnull:	pop
		ldc "null"
		L70_endifnull:	invokevirtual Method java/lang/String concat (Ljava/lang/String;)Ljava/lang/String;
		dup
		ifnull L75_ifnull
		invokevirtual Method java/lang/Object toString ()Ljava/lang/String;
		goto L77_endifnull
		L75_ifnull:	pop
		ldc "null"
		L77_endifnull:	ldc " standee"
		dup
		ifnull L82_ifnull
		invokevirtual Method java/lang/Object toString ()Ljava/lang/String;
		goto L84_endifnull
		L82_ifnull:	pop
		ldc "null"
		L84_endifnull:	invokevirtual Method java/lang/String concat (Ljava/lang/String;)Ljava/lang/String;
		checkcast java/lang/String
		dup_x2
		aastore
		pop
		aload_1
		iconst_3
		ldc "gentility's "
		dup
		ifnull L96_ifnull
		invokevirtual Method java/lang/Object toString ()Ljava/lang/String;
		goto L98_endifnull
		L96_ifnull:	pop
		ldc "null"
		L98_endifnull:	aload_1
		iconst_2
		aaload
		dup
		ifnull L105_ifnull
		invokevirtual Method java/lang/Object toString ()Ljava/lang/String;
		goto L107_endifnull
		L105_ifnull:	pop
		ldc "null"
		L107_endifnull:	invokevirtual Method java/lang/String concat (Ljava/lang/String;)Ljava/lang/String;
		dup
		ifnull L112_ifnull
		invokevirtual Method java/lang/Object toString ()Ljava/lang/String;
		goto L114_endifnull
		L112_ifnull:	pop
		ldc "null"
		L114_endifnull:	aload_1
		iconst_0
		aaload
		dup
		ifnull L121_ifnull
		invokevirtual Method java/lang/Object toString ()Ljava/lang/String;
		goto L123_endifnull
		L121_ifnull:	pop
		ldc "null"
		L123_endifnull:	invokevirtual Method java/lang/String concat (Ljava/lang/String;)Ljava/lang/String;
		checkcast java/lang/String
		dup_x2
		aastore
		pop
		aload_1
		iconst_3
		aaload
		checkcast java/lang/String
		areturn
	.end code
.end method

.method public mixedArray : ()Ljava/lang/String;
	.code stack 6 locals 2
		bipush 7
		newarray double
		checkcast [D
		dup
		astore_1
		pop
		aload_1
		iconst_0
		iconst_5
		i2b
		i2d
		dup2_x2
		dastore
		pop2
		aload_1
		iconst_1
		bipush 11
		i2s
		i2d
		dup2_x2
		dastore
		pop2
		aload_1
		iconst_2
		bipush 45
		i2d
		dup2_x2
		dastore
		pop2
		aload_1
		iconst_3
		bipush 67
		i2l
		lneg
		l2d
		dup2_x2
		dastore
		pop2
		aload_1
		iconst_4
		ldc 15.8F
		f2d
		dup2_x2
		dastore
		pop2
		aload_1
		iconst_5
		getstatic Field java/lang/Math PI D
		dup2_x2
		dastore
		pop2
		aload_1
		bipush 6
		bipush 90
		i2d
		dup2_x2
		dastore
		pop2
		ldc ""
		dup
		ifnull L63_ifnull
		invokevirtual Method java/lang/Object toString ()Ljava/lang/String;
		goto L65_endifnull
		L63_ifnull:	pop
		ldc "null"
		L65_endifnull:	aload_1
		iconst_0
		daload
		invokestatic Method java/lang/Double toString (D)Ljava/lang/String;
		invokevirtual Method java/lang/String concat (Ljava/lang/String;)Ljava/lang/String;
		dup
		ifnull L74_ifnull
		invokevirtual Method java/lang/Object toString ()Ljava/lang/String;
		goto L76_endifnull
		L74_ifnull:	pop
		ldc "null"
		L76_endifnull:	aload_1
		iconst_1
		daload
		invokestatic Method java/lang/Double toString (D)Ljava/lang/String;
		invokevirtual Method java/lang/String concat (Ljava/lang/String;)Ljava/lang/String;
		dup
		ifnull L85_ifnull
		invokevirtual Method java/lang/Object toString ()Ljava/lang/String;
		goto L87_endifnull
		L85_ifnull:	pop
		ldc "null"
		L87_endifnull:	aload_1
		iconst_2
		daload
		invokestatic Method java/lang/Double toString (D)Ljava/lang/String;
		invokevirtual Method java/lang/String concat (Ljava/lang/String;)Ljava/lang/String;
		dup
		ifnull L96_ifnull
		invokevirtual Method java/lang/Object toString ()Ljava/lang/String;
		goto L98_endifnull
		L96_ifnull:	pop
		ldc "null"
		L98_endifnull:	aload_1
		iconst_3
		daload
		invokestatic Method java/lang/Double toString (D)Ljava/lang/String;
		invokevirtual Method java/lang/String concat (Ljava/lang/String;)Ljava/lang/String;
		dup
		ifnull L107_ifnull
		invokevirtual Method java/lang/Object toString ()Ljava/lang/String;
		goto L109_endifnull
		L107_ifnull:	pop
		ldc "null"
		L109_endifnull:	aload_1
		iconst_4
		daload
		invokestatic Method java/lang/Double toString (D)Ljava/lang/String;
		invokevirtual Method java/lang/String concat (Ljava/lang/String;)Ljava/lang/String;
		dup
		ifnull L118_ifnull
		invokevirtual Method java/lang/Object toString ()Ljava/lang/String;
		goto L120_endifnull
		L118_ifnull:	pop
		ldc "null"
		L120_endifnull:	aload_1
		iconst_5
		daload
		invokestatic Method java/lang/Double toString (D)Ljava/lang/String;
		invokevirtual Method java/lang/String concat (Ljava/lang/String;)Ljava/lang/String;
		dup
		ifnull L129_ifnull
		invokevirtual Method java/lang/Object toString ()Ljava/lang/String;
		goto L131_endifnull
		L129_ifnull:	pop
		ldc "null"
		L131_endifnull:	aload_1
		bipush 6
		daload
		invokestatic Method java/lang/Double toString (D)Ljava/lang/String;
		invokevirtual Method java/lang/String concat (Ljava/lang/String;)Ljava/lang/String;
		checkcast java/lang/String
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
