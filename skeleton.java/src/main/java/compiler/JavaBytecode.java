package compiler;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by anton_000 on 24/6/2017.
 */
public class JavaBytecode {
    private List<Quad> quadList;
    private List<String> bytecodeList;
    private int counter;

    public JavaBytecode(List<Quad> quadList){
        this.quadList=new ArrayList<Quad>(quadList);
        this.bytecodeList = new ArrayList<String>();
        this.counter = 0;
    }

    public List<Quad> getQuadList() {
        return quadList;
    }

    public void produceJavaBytecode(){

        this.bytecodeList.add(".class public Grace");
        this.bytecodeList.add(".super java/lang/Object");
        boolean mainExists = true;

        for (int i=0; i<this.quadList.size(); i++){     //for each element of quadlist produce bytecode
            //System.out.println(quadList.get(i).getOp() + " " + quadList.get(i).getArg1() + " " + quadList.get(i).getArg2() + " " + quadList.get(i).getArg3());

            operator p;
            //System.out.println("OPP: " + this.quadList.get(i).getOp().getClass().getSimpleName());
            if(this.quadList.get(i).getOp().getClass().getSimpleName().equals("operator"))
            {
                p = (operator) quadList.get(i).getOp();
                if (p.getName().equals("relop")){

                    String value = p.getValue();

                    if(value.equals("<")) {
                        this.bytecodeList.add(this.counter + " : aload " + quadList.get(i).getArg1());
                        this.counter++;
                        this.bytecodeList.add(this.counter + " : aload " + quadList.get(i).getArg2());
                        this.counter++;
                        this.bytecodeList.add(this.counter + " : if_icmpge " + quadList.get(i).getArg3());
                        this.counter++;
                    }
                    else if (value.equals(">")){
                        bytecodeList.add(this.counter + " : aload " + quadList.get(i).getArg1());
                        this.counter++;
                        bytecodeList.add(this.counter + " : aload " + quadList.get(i).getArg2());
                        this.counter++;
                        bytecodeList.add(this.counter + " : if_icmple " + quadList.get(i).getArg3());
                        this.counter++;

                    }
                    else if (value.equals("<=")){
                        bytecodeList.add(this.counter + " : aload " + quadList.get(i).getArg1());
                        this.counter++;
                        bytecodeList.add(this.counter + " : aload " + quadList.get(i).getArg2());
                        this.counter++;
                        bytecodeList.add(this.counter + " : if_icmpgt " + quadList.get(i).getArg3());
                        this.counter++;
                    }
                    else if (value.equals(">=")){
                        bytecodeList.add(this.counter + " : aload " + quadList.get(i).getArg1());
                        this.counter++;
                        bytecodeList.add(this.counter + " : aload " + quadList.get(i).getArg2());
                        this.counter++;
                        bytecodeList.add(this.counter + " : if_icmplt " + quadList.get(i).getArg3());
                        this.counter++;
                    }
                    else if (value.equals("=")){
                        bytecodeList.add(this.counter + " : aload " + quadList.get(i).getArg1());
                        this.counter++;
                        bytecodeList.add(this.counter + " : aload " + quadList.get(i).getArg2());
                        this.counter++;
                        bytecodeList.add(this.counter + " : if_icmpne " + quadList.get(i).getArg3());
                        this.counter++;
                    }
                    else if (value.equals("#")){
                        bytecodeList.add(this.counter + " : aload " + quadList.get(i).getArg1());
                        this.counter++;
                        bytecodeList.add(this.counter + " : aload " + quadList.get(i).getArg2());
                        this.counter++;
                        bytecodeList.add(this.counter + " : if_icmpeq " + quadList.get(i).getArg3());
                        this.counter++;
                    }

                }
                else if (p.getName().equals("op")){

                    String value = p.getValue();

                    if(value.equals("+")) {
                        this.bytecodeList.add(this.counter + " : aload " + quadList.get(i).getArg1());
                        this.counter++;
                        this.bytecodeList.add(this.counter + " : aload " + quadList.get(i).getArg2());
                        this.counter++;
                        this.bytecodeList.add(this.counter + " : iadd ");
                        this.counter++;
                    }
                    else if (value.equals("-")){
                        bytecodeList.add(this.counter + " : aload " + quadList.get(i).getArg1());
                        this.counter++;
                        bytecodeList.add(this.counter + " : aload " + quadList.get(i).getArg2());
                        this.counter++;
                        bytecodeList.add(this.counter + " : isub " + quadList.get(i).getArg3());
                        this.counter++;

                    }
                    else if (value.equals("*")){
                        bytecodeList.add(this.counter + " : aload " + quadList.get(i).getArg1());
                        this.counter++;
                        bytecodeList.add(this.counter + " : aload " + quadList.get(i).getArg2());
                        this.counter++;
                        bytecodeList.add(this.counter + " : imul " + quadList.get(i).getArg3());
                        this.counter++;
                    }
                    else if (value.equals("/")){
                        bytecodeList.add(this.counter + " : aload " + quadList.get(i).getArg1());
                        this.counter++;
                        bytecodeList.add(this.counter + " : aload " + quadList.get(i).getArg2());
                        this.counter++;
                        bytecodeList.add(this.counter + " : idiv " + quadList.get(i).getArg3());
                        this.counter++;
                    }
                    else if (value.equals("mod")){
                        bytecodeList.add(this.counter + " : aload " + quadList.get(i).getArg1());
                        this.counter++;
                        bytecodeList.add(this.counter + " : aload " + quadList.get(i).getArg2());
                        this.counter++;
                        bytecodeList.add(this.counter + " : irem " + quadList.get(i).getArg3());
                        this.counter++;
                    }

                }
            }
            else{
                if (quadList.get(i).getOp().equals("unit")){

                    String method;
                    String methodName = (String) quadList.get(i).getArg1();
                    if(getMostNestedMethod(methodName)==-1){


                    }

                    if(i==0 && !quadList.get(i).getArg1().equals("main"))
                    {
                        mainExists = false;
                        method = ".method public static main";
                        method += "([Ljava/lang/String;)V";
                        this.bytecodeList.add(method);
                        this.bytecodeList.add(".limit stack 2");

                        String invokedMethod = "invokevirtual " + quadList.get(i).getArg1();
                        invokedMethod += "()V";
                        this.bytecodeList.add(invokedMethod);

                    }
                    else if(quadList.get(i).getArg1().equals("main")){
                        mainExists = true;
                        method = ".method public static " + quadList.get(i).getArg1();
                        method += "([Ljava/lang/String;)V";
                        this.bytecodeList.add(method);
                        this.bytecodeList.add(".limit stack 2");
                    }
                    else if (i!=0){
                        method = ".method" + quadList.get(i).getArg1();
                        method += "()V";
                        this.bytecodeList.add(method);
                        this.bytecodeList.add(".limit stack 2");
                    }
                }
                else if (quadList.get(i).getOp().equals("par")){

                }
                else if (quadList.get(i).getOp().equals("call")){

                }
                else if (quadList.get(i).getOp().equals("endu")){

                    String method;
                    if(!mainExists){

                        this.bytecodeList.add("return");
                        this.bytecodeList.add(".end method");

                        method = ".method " + quadList.get(i).getArg1();
                        method += "()V";
                        this.bytecodeList.add(method);
                        this.bytecodeList.add(".limit stack 2");


                        this.bytecodeList.add("return");
                        this.bytecodeList.add(".end method");
                    }
                    else {
                        this.bytecodeList.add("return");
                        this.bytecodeList.add(".end method");
                    }

                }
            }

        }
        try {
            PrintWriter writer = new PrintWriter("bytecode.j", "ASCII");
            for (int j = 0; j < this.bytecodeList.size(); j++) {
                writer.println(this.bytecodeList.get(j));
            }
            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int nestedMethods(String name){
        int i = 0;
        while(i<quadList.size())
        {
            if(quadList.get(i).getOp().equals("endu") && quadList.get(i).getArg1().equals(name))
                return -1;
            if(quadList.get(i).getOp().equals("unit"))
            {
               return i;
            }
            i++;
        }
        return -1;
    }

    public int getMostNestedMethod(String name){
        int flag=nestedMethods(name);
        int flagHelper = flag;
        while(flag!=-1){
            flagHelper = flag;
            name = (String) quadList.get(flagHelper).getArg1();
            flag= nestedMethods(name);
        }
        return flagHelper;
    }

}
