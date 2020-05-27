.version 49 0
.class public super cmpt355/test/ArithmeticOperatorTestClass
.super java/lang/Object
.field public i I
.field public l J
.field public f F
.field public d D
.method public plus : ()V
	.code stack 6 locals 1
		aload_0
		sipush 3483
		ldc 99922
		iadd
		dup_x1
		putfield Field cmpt355/test/ArithmeticOperatorTestClass i I
		pop
		aload_0
		sipush 3483
		i2l
		ldc 99922
		i2l
		ladd
		dup2_x1
		putfield Field cmpt355/test/ArithmeticOperatorTestClass l J
		pop2
		aload_0
		ldc 3483.0F
		ldc 99922.0F
		fadd
		dup_x1
		putfield Field cmpt355/test/ArithmeticOperatorTestClass f F
		pop
		aload_0
		ldc2_w 3483.0
		ldc2_w 99922.0
		dadd
		dup2_x1
		putfield Field cmpt355/test/ArithmeticOperatorTestClass d D
		pop2
		return
	.end code
.end method

.method public minus : ()V
	.code stack 5 locals 1
		aload_0
		sipush 20280
		ldc 833421
		isub
		dup_x1
		putfield Field cmpt355/test/ArithmeticOperatorTestClass i I
		pop
		aload_0
		sipush 20280
		i2l
		ldc 833421
		i2l
		lsub
		dup2_x1
		putfield Field cmpt355/test/ArithmeticOperatorTestClass l J
		pop2
		aload_0
		ldc 20280.0F
		ldc 833421
		i2l
		l2f
		fsub
		dup_x1
		putfield Field cmpt355/test/ArithmeticOperatorTestClass f F
		pop
		aload_0
		ldc2_w 20280.0
		ldc2_w 833421.0
		dsub
		dup2_x1
		putfield Field cmpt355/test/ArithmeticOperatorTestClass d D
		pop2
		return
	.end code
.end method

.method public times : ()V
	.code stack 5 locals 1
		aload_0
		ldc 253251
		ldc 525221
		imul
		dup_x1
		putfield Field cmpt355/test/ArithmeticOperatorTestClass i I
		pop
		aload_0
		ldc 253251
		i2l
		ldc 525221
		i2l
		lmul
		dup2_x1
		putfield Field cmpt355/test/ArithmeticOperatorTestClass l J
		pop2
		aload_0
		ldc 253251.0F
		ldc 525221.0F
		fmul
		dup_x1
		putfield Field cmpt355/test/ArithmeticOperatorTestClass f F
		pop
		aload_0
		ldc2_w 253251.0
		ldc2_w 525221.0
		dmul
		dup2_x1
		putfield Field cmpt355/test/ArithmeticOperatorTestClass d D
		pop2
		return
	.end code
.end method

.method public divide : ()V
	.code stack 5 locals 1
		aload_0
		ldc 159221
		sipush 362
		idiv
		dup_x1
		putfield Field cmpt355/test/ArithmeticOperatorTestClass i I
		pop
		aload_0
		ldc 159221
		i2l
		sipush 362
		i2l
		ldiv
		dup2_x1
		putfield Field cmpt355/test/ArithmeticOperatorTestClass l J
		pop2
		aload_0
		ldc 159221.0F
		ldc 362.0F
		fdiv
		dup_x1
		putfield Field cmpt355/test/ArithmeticOperatorTestClass f F
		pop
		aload_0
		ldc2_w 159221.0
		ldc2_w 362.0
		ddiv
		dup2_x1
		putfield Field cmpt355/test/ArithmeticOperatorTestClass d D
		pop2
		return
	.end code
.end method

.method public mod : ()V
	.code stack 5 locals 1
		aload_0
		ldc 934175
		sipush 8488
		irem
		dup_x1
		putfield Field cmpt355/test/ArithmeticOperatorTestClass i I
		pop
		aload_0
		ldc 934175
		i2l
		sipush 8488
		i2l
		lrem
		dup2_x1
		putfield Field cmpt355/test/ArithmeticOperatorTestClass l J
		pop2
		aload_0
		ldc 934175.0F
		ldc 8488.0F
		frem
		dup_x1
		putfield Field cmpt355/test/ArithmeticOperatorTestClass f F
		pop
		aload_0
		ldc2_w 934175.0
		ldc2_w 8488.0
		drem
		dup2_x1
		putfield Field cmpt355/test/ArithmeticOperatorTestClass d D
		pop2
		return
	.end code
.end method

.method public mixed : ()V
	.code stack 5 locals 1
		aload_0
		sipush 9786
		bipush 42
		imul
		dup_x1
		putfield Field cmpt355/test/ArithmeticOperatorTestClass i I
		pop
		aload_0
		getstatic Field java/lang/Integer MAX_VALUE I
		bipush 11
		i2b
		idiv
		i2l
		dup2_x1
		putfield Field cmpt355/test/ArithmeticOperatorTestClass l J
		pop2
		aload_0
		ldc 383.38745F
		sipush 7838
		i2f
		fdiv
		dup_x1
		putfield Field cmpt355/test/ArithmeticOperatorTestClass f F
		pop
		aload_0
		ldc 9.993388E7F
		f2d
		getstatic Field java/lang/Math PI D
		drem
		dup2_x1
		putfield Field cmpt355/test/ArithmeticOperatorTestClass d D
		pop2
		return
	.end code
.end method

.method public compound : ()V
	.code stack 5 locals 1
		aload_0
		sipush 29823
		bipush 112
		isub
		ldc 38385
		ineg
		bipush 99
		idiv
		iadd
		ldc 38387487
		isub
		dup_x1
		putfield Field cmpt355/test/ArithmeticOperatorTestClass i I
		pop
		aload_0
		getstatic Field java/lang/Long MAX_VALUE J
		ldc 39783723
		ldc 9999667
		irem
		ldc 987877
		irem
		sipush 653
		irem
		i2l
		lsub
		dup2_x1
		putfield Field cmpt355/test/ArithmeticOperatorTestClass l J
		pop2
		aload_0
		ldc2_w 92835398347L
		l2f
		ldc 3747753.0F
		fdiv
		ldc 1.91919191E15F
		fsub
		ldc 9.9887768E10F
		bipush 100
		i2f
		fmul
		fsub
		dup_x1
		putfield Field cmpt355/test/ArithmeticOperatorTestClass f F
		pop
		aload_0
		ldc2_w 3.87383723253993E10
		ldc 87283475
		i2d
		drem
		ldc 9299299
		i2d
		dadd
		ldc 15.0F
		f2d
		dadd
		ldc 39939
		i2d
		dsub
		dup2_x1
		putfield Field cmpt355/test/ArithmeticOperatorTestClass d D
		pop2
		return
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
