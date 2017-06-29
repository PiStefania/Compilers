.class public Grace
.super java/lang/Object
.method public <init>()V
 aload_0
invokenonvirtual java/lang/Object/<init>()V
return
.end method
.method f()V
.limit stack 20
.limit locals 100
0 : aload_0 
1 : ldc 10
2 : newarray int
3 : astore 1
4 : aload 1
5 : ldc 1
6 : ldc 5
7 : iastore 
8 : aload 1
9 : ldc 1
10 : aload 1
11 : ldc 1
12 : iaload 
13 : ldc 3
14 : if_icmplt 16
15 : goto 25
16 : ldc 2
17 : istore 3
18 : aload 1
19 : ldc 2
20 : iload 3
21 : iastore 
22 : ldc 20
23 : istore 3
24 : goto 26
25 : return
.end method
.method public static main([Ljava/lang/String;)V
.limit stack 20
.limit locals 100
0 : aload_0 
1 : new Grace
2 : dup
3 : invokespecial Grace/<init>()V
4 : invokespecial Grace/f()V
5 : return
.end method
