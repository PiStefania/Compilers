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
1 : ldc 7
2 : istore 1
3 : ldc 3
4 : istore 2
5 : iload 2
6 : istore 1
7 : ldc 5
8 : newarray int
9 : astore 3
10 : aload 3
11 : ldc 3
12 : ldc 2
13 : iastore 
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
