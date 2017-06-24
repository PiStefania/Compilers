package compiler;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * Created by anton_000 on 24/6/2017.
 */
public class JavaBytecode {
    private List<Quad> InitialQuadList;
    private List<Quad> quadList;
    private List<String> bytecodeList;
    private int counter;
    private List <funcBeggining> funcScope;
    private List <FuncScopeHelper> myFuncScopeHelperList;
    private Stack<FuncScope> funcStack;         //stack of obj:FuncScope

    public JavaBytecode(List<Quad> quadList,Stack<FuncScope> funcStack ){
        this.quadList=new ArrayList<Quad>(quadList);
        this.bytecodeList = new ArrayList<String>();
        this.counter = 0;
        this.InitialQuadList = new ArrayList<Quad>(quadList);
        this.funcStack = new Stack<FuncScope>();


        for ( int i=0; i<funcStack.size(); i++){

            this.funcStack.push(funcStack.get(i));
        }
    }

    public List<Quad> getQuadList() {
        return quadList;
    }

    public void produceJavaBytecode(){


        this.bytecodeList.add(".class public Grace");
        this.bytecodeList.add(".super java/lang/Object");

        this.createFuncScope();
        this.findFunctionScope();

        for (int i=0; i<this.funcScope.size(); i++){     //for each function
            String method;
            //entolh first

            int first = funcScope.get(i).getFirst();

            //unit
            if (first==0){
                if (!quadList.get(first).getArg1().equals("main")){     //must create main

                }
                else{
                    method = ".method public static " + quadList.get(first).getArg1();
                    method += "([Ljava/lang/String;)V";
                    this.bytecodeList.add(method);
                    this.bytecodeList.add(".limit stack 2");
                }
            }
            else{
                method = ".method " + quadList.get(first).getArg1();
                method += this.setMethodPar((String) quadList.get(first).getArg1()) + this.setMethodRet((String) quadList.get(first).getArg1()) ;
                this.bytecodeList.add(method);
                this.bytecodeList.add(".limit stack 2");
            }


            int second = funcScope.get(i).getSecond();
            int last = funcScope.get(i).getLast();

            for (int counter=second; counter<last; counter++){
                //entoles
                Quad command = new Quad(quadList.get(counter));
            }

            //endu
            String retType = setMethodRet((String) quadList.get(first).getArg1());
            if(retType.equals("V")){
                this.bytecodeList.add("return");
            }
            else if(retType.equals("I")){
                this.bytecodeList.add("ireturn");
            }
            else if(retType.equals("C"))
            {
                this.bytecodeList.add("return");
            }

            this.bytecodeList.add(".end method");



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


    public void changeQuadList(){
        if (quadList.get(0).getOp().equals("unit") && !quadList.get(0).getArg1().equals("main")){
            //must change quadlist
            Quad q1 = new Quad("unit", "main", null, null);
            Quad q2 = new Quad("call", null, null, quadList.get(0).getArg1());
            Quad q3 = new Quad("endu", "main", null, null);

            quadList.add(0,q1);
            quadList.add(q2);
            quadList.add(q3);

            this.setQuadListLabels();

            this.printQuadList();


        }
    }

    public void setQuadListLabels(){
        for (int i=0; i<quadList.size();i++){
            if (quadList.get(i).getOp().equals("jump")){
                Integer temp = (Integer) quadList.get(i).getArg3()+1;
                quadList.get(i).setArg3(temp);
            }
            else if (quadList.get(i).getOp().equals("jumpl")){
                Integer temp = (Integer) quadList.get(i).getArg3()+1;
                quadList.get(i).setArg3(temp);
            }
            else if (quadList.get(i).getOp().equals("label")){
                Integer temp = (Integer) quadList.get(i).getArg1()+1;
                quadList.get(i).setArg1(temp);
            }
            else if (quadList.get(i).getOp().equals("ifb")){
                Integer temp = (Integer) quadList.get(i).getArg3()+1;
                quadList.get(i).setArg3(temp);
            }
            else {
                if (quadList.get(i).getOp().getClass().getSimpleName().equals("operator")) {
                    operator p = (operator) quadList.get(i).getOp();
                    if (p.getName().equals("relop")) {
                        Integer temp = (Integer) quadList.get(i).getArg3() + 1;
                        quadList.get(i).setArg3(temp);
                    }
                }
            }

        }
    }


    public void printQuadList(){            //print list
        System.out.println();
        for (int i=0; i<this.quadList.size();i++){
            if(this.quadList.get(i).getOp().getClass().getSimpleName().equals("operator")){
                operator myOp = (operator) this.quadList.get(i).getOp();
                System.out.println(i + ": " +myOp.getValue() + ", " + this.quadList.get(i).getArg1() + ", " + this.quadList.get(i).getArg2() + ", " + this.quadList.get(i).getArg3());

            }
            else
                System.out.println(i + ": " + this.quadList.get(i).getOp() + ", " + this.quadList.get(i).getArg1() + ", " + this.quadList.get(i).getArg2() + ", " + this.quadList.get(i).getArg3());
        }
    }




    public void createFuncScope(){
        this.funcScope= new ArrayList <funcBeggining>();

        this.changeQuadList();

        for (int i=0; i<this.quadList.size(); i++){ //get unit

            if(quadList.get(i).getOp().equals("unit")){     //c
                int first = i;
                String name = (String) quadList.get(i).getArg1();
                int j=i;
                while(j<quadList.size()){
                    if(quadList.get(j).getOp().equals("endu") && quadList.get(j).getArg1().equals(name))
                        break;
                    j++;
                }
                int last=j;
                int k = last;
                while (k > first){
                    if (quadList.get(k).getOp().equals("endu") && !quadList.get(k).getArg1().equals(name))
                        break;
                    k--;
                }
                int second= k+1;
                funcBeggining obj = new funcBeggining(name, first, second, last);
                obj.print();

                funcScope.add(obj);
            }
        }


        this.orderFuncScope();

    }

    public void orderFuncScope(){

        System.out.println("ORDERING");
        int n = funcScope.size();
        int temp = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {

                if (funcScope.get(j-1).getLast() > funcScope.get(j).getLast()) {
                    Collections.swap(funcScope, j-1,j);
                }

            }
        }

        for (int i=0; i<n; i++){
            funcScope.get(i).print();
        }

    }

    public void handleQuad(Quad q) {
        //System.out.println(quadList.get(i).getOp() + " " + quadList.get(i).getArg1() + " " + quadList.get(i).getArg2() + " " + quadList.get(i).getArg3());

        operator p;
        //System.out.println("OPP: " + this.quadList.get(i).getOp().getClass().getSimpleName());
        if (q.getOp().getClass().getSimpleName().equals("operator")) {
            p = (operator) q.getOp();
            if (p.getName().equals("relop")) {

                String value = p.getValue();

                if (value.equals("<")) {
                    this.bytecodeList.add(this.counter + " : aload " + q.getArg1());
                    this.counter++;
                    this.bytecodeList.add(this.counter + " : aload " + q.getArg2());
                    this.counter++;
                    this.bytecodeList.add(this.counter + " : if_icmpge " + q.getArg3());
                    this.counter++;
                } else if (value.equals(">")) {
                    bytecodeList.add(this.counter + " : aload " + q.getArg1());
                    this.counter++;
                    bytecodeList.add(this.counter + " : aload " + q.getArg2());
                    this.counter++;
                    bytecodeList.add(this.counter + " : if_icmple " + q.getArg3());
                    this.counter++;

                } else if (value.equals("<=")) {
                    bytecodeList.add(this.counter + " : aload " + q.getArg1());
                    this.counter++;
                    bytecodeList.add(this.counter + " : aload " + q.getArg2());
                    this.counter++;
                    bytecodeList.add(this.counter + " : if_icmpgt " + q.getArg3());
                    this.counter++;
                } else if (value.equals(">=")) {
                    bytecodeList.add(this.counter + " : aload " + q.getArg1());
                    this.counter++;
                    bytecodeList.add(this.counter + " : aload " + q.getArg2());
                    this.counter++;
                    bytecodeList.add(this.counter + " : if_icmplt " + q.getArg3());
                    this.counter++;
                } else if (value.equals("=")) {
                    bytecodeList.add(this.counter + " : aload " + q.getArg1());
                    this.counter++;
                    bytecodeList.add(this.counter + " : aload " + q.getArg2());
                    this.counter++;
                    bytecodeList.add(this.counter + " : if_icmpne " + q.getArg3());
                    this.counter++;
                } else if (value.equals("#")) {
                    bytecodeList.add(this.counter + " : aload " + q.getArg1());
                    this.counter++;
                    bytecodeList.add(this.counter + " : aload " + q.getArg2());
                    this.counter++;
                    bytecodeList.add(this.counter + " : if_icmpeq " + q.getArg3());
                    this.counter++;
                }

            } else if (p.getName().equals("op")) {

                String value = p.getValue();

                if (value.equals("+")) {
                    this.bytecodeList.add(this.counter + " : aload " + q.getArg1());
                    this.counter++;
                    this.bytecodeList.add(this.counter + " : aload " + q.getArg2());
                    this.counter++;
                    this.bytecodeList.add(this.counter + " : iadd ");
                    this.counter++;
                } else if (value.equals("-")) {
                    bytecodeList.add(this.counter + " : aload " + q.getArg1());
                    this.counter++;
                    bytecodeList.add(this.counter + " : aload " + q.getArg2());
                    this.counter++;
                    bytecodeList.add(this.counter + " : isub " + q.getArg3());
                    this.counter++;

                } else if (value.equals("*")) {
                    bytecodeList.add(this.counter + " : aload " + q.getArg1());
                    this.counter++;
                    bytecodeList.add(this.counter + " : aload " + q.getArg2());
                    this.counter++;
                    bytecodeList.add(this.counter + " : imul " + q.getArg3());
                    this.counter++;
                } else if (value.equals("/")) {
                    bytecodeList.add(this.counter + " : aload " + q.getArg1());
                    this.counter++;
                    bytecodeList.add(this.counter + " : aload " + q.getArg2());
                    this.counter++;
                    bytecodeList.add(this.counter + " : idiv " + q.getArg3());
                    this.counter++;
                } else if (value.equals("mod")) {
                    bytecodeList.add(this.counter + " : aload " + q.getArg1());
                    this.counter++;
                    bytecodeList.add(this.counter + " : aload " + q.getArg2());
                    this.counter++;
                    bytecodeList.add(this.counter + " : irem " + q.getArg3());
                    this.counter++;
                }

            }
        } else {

            if (q.getOp().equals("par")) {              //load parametrous analoga me tupo

            }
            else if (q.getOp().equals("call")) {        //invoke function

            }
            else if(q.getOp().equals(":=")){            //store

            }
            else if(q.getOp().equals("array")){         //store se arg3 to arg1[arg2]

            }
            else if(q.getOp().equals("ifb")){       //isos dn xreiazetai

            }
            else if(q.getOp().equals("ret")){       // ireturn/return

            }

        }
    }

    public void findFunctionScope(){
        int scope = 0;
        myFuncScopeHelperList = new ArrayList<FuncScopeHelper>();

        for(int i=0;i<this.InitialQuadList.size();i++){
            if (InitialQuadList.get(i).getOp().equals("unit")){
                FuncScopeHelper helper = new FuncScopeHelper((String )InitialQuadList.get(i).getArg1(), scope);
                scope++;
                myFuncScopeHelperList.add(helper);
            }
            if (InitialQuadList.get(i).getOp().equals("endu")){
                scope--;
            }
        }

        this.printFuncScopeHelper();

    }

    public String setMethodRet(String name){
        int scope=0;
        for(int i=0;i<this.myFuncScopeHelperList.size();i++){
            if(this.myFuncScopeHelperList.get(i).getFuncName().equals(name)){
                scope = myFuncScopeHelperList.get(i).getScope();
            }
        }
        String type = "";
        for(int i=0;i<funcStack.size();i++){
            if(funcStack.get(i).getFuncName().equals(name) && funcStack.get(i).getScope()==scope){
                type = funcStack.get(i).getType();
            }
        }

        System.out.println(type);

        if(type.equals("int")){
            return "I";
        }
        else if(type.equals("char")){
            return "C";
        }
        else
            return "V";
    }

    public String setMethodPar(String name){
        int scope = 0;
        for(int i=0;i<this.myFuncScopeHelperList.size();i++){
            if(this.myFuncScopeHelperList.get(i).getFuncName().equals(name)){
                scope = myFuncScopeHelperList.get(i).getScope();
            }
        }
        List<String> types = new ArrayList<String>();
        for(int i=0;i<funcStack.size();i++){
            if(funcStack.get(i).getFuncName().equals(name) && funcStack.get(i).getScope()==scope){
                types = funcStack.get(i).getTypesOfPatameters();
            }
        }
        String parameters = "(";
        for(int i=0;i<types.size();i++){
            int counterBr = 0;
            if (types.get(i).contains("[")){
                int j = 0;
                char c;

                while(j<types.get(i).length()){
                    c = types.get(i).charAt(j);
                    if(c=='['){
                        counterBr++;
                    }
                }
            }

            if(types.get(i).equals("int")){
              parameters += "I";
            }
            else if(types.get(i).equals("char")){
              parameters += "C";
            }
            else if(types.get(i).contains("int[")){
                for(int k=0;k<counterBr;k++)
                {
                    parameters += "[";
                }
                parameters += "I";
            }
            else if(types.get(i).contains("char[")){
                for(int k=0;k<counterBr;k++)
                {
                    parameters += "[";
                }
                parameters += "C";
            }
        }
        parameters += ")";
        return parameters;

    }


    public void printFuncScopeHelper(){
        for(int i=0;i<myFuncScopeHelperList.size();i++){
            System.out.println("Name: " + myFuncScopeHelperList.get(i).getFuncName() + " scope: " + myFuncScopeHelperList.get(i).getScope());
        }
    }

}
