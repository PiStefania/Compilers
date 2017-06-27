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
1 : ldc 5
2 : newarray int
3 : astore 1
4 : aload 1
5 : ldc 3
6 : ldc 5
7 : iastore 
8 : aload 1
9 : ldc 2
10 : ldc 7
11 : iastore 
12 : aload 1
13 : ldc 3
14 : iaload 
16 : ldc 2
17 : irem 
18 : istore 4
19 : aload 1
20 : ldc 2
21 : iaload 
22 : iload 4
23 : imul 
24 : istore 5
25 : aload 1
26 : ldc 2
27 : iaload 
28 : ldc 10
29 : isub 
30 : istore 6
31 : iload 5
32 : iload 6
33 : idiv 
34 : istore 7
35 : aload 1
36 : ldc 3
37 : iaload 
38 : iload 7
39 : iadd 
40 : istore 8
41 : iload 8
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
