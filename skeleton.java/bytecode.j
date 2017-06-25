.class public Grace
.super java/lang/Object
.method public <init>()V
 aload_0
invokenonvirtual java/lang/Object/<init>()V
return
.end method
.method f()V
.limit stack 100
.limit locals 10
0 : aload_0 
1 : ldc 3
2 : istore 1
3 : ldc 3
4 : newarray int
5 : istore 2
6 : ldc 2
7 : istore 3
8 : ldc 3
9 : newarray int
10 : istore 4
11 : istore 5
return
.end method
.method public static main([Ljava/lang/String;)V
.limit stack 100
.limit locals 2
0 : aload_0 
1 : new Grace
2 : dup
3 : invokespecial Grace/<init>()V
4 : invokespecial Grace/f()V
return
.end method
