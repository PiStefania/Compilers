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
1 : ldc 2
2 : ldc 3
3 : if_icmplt 6
4 : jsr 11
5 : ldc 2
6 : istore 1
7 : ldc 3
8 : istore 1
9 : ldc 5
10 : istore 1
11 : ldc 10
12 : newarray int
13 : astore 2
14 : aload 2
15 : ldc 1
16 : iload 1
17 : iastore 
18 : ldc 209
19 : istore 1
20 : ldc 209
21 : istore 1
22 : return
.end method
.method public static main([Ljava/lang/String;)V
.limit stack 100
.limit locals 2
0 : aload_0 
1 : new Grace
2 : dup
3 : invokespecial Grace/<init>()V
4 : invokespecial Grace/f()V
5 : return
.end method
