package compiler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anton_000 on 30/5/2017.
 */
import compiler.Quad;
public class Intermediate {

    private int count;
    List<Quad> quadList;
    List<InterReg> regList;

    private OpCode opCode;


    public Intermediate(){
        this.count=0;
        this.quadList = new ArrayList<Quad>();
        this.opCode = new OpCode();
        this.regList = new ArrayList<InterReg>();
    }

    public int getCount() {return count;}

    public List<Quad> getQuadList() {return quadList;}

    public OpCode getOpCode() {return opCode;}

    public void genQuad(Object op, Object arg1, Object arg2, Object arg3) {
        String forPrint ="";
        if (op != null)
            if(op.getClass().getSimpleName().equals("operator")){
                forPrint += " " + ((operator) op).getName().toString();
            }
            else{
                forPrint += " " + op.toString();
            }
        if (arg1 != null)
            forPrint += " " +arg1.toString();
        if (arg2 != null)
            forPrint += " " +arg2.toString();
        if (arg3 != null)
            forPrint += " " +arg3.toString();

        //System.out.println(count + ":" +forPrint);
        count++;
        Quad myquad = new Quad(op,arg1,arg2,arg3);
        this.quadList.add(myquad);


    }

    public int nextQuad() {
        return count;
    }

    /*
    public <t> t newTemp(Object t){
        Object myTemp = new Object();
        return (t) myTemp;
    }*/

    public Object newTemp(String nameType){

        //Object w1 = new Object();
        if(nameType.equals("String")){
            String w = new String();
            return w;
        }
        else if(nameType.equals("Integer")){
            int w = -1;
            return w;
        }
        else if(nameType.equals("Boolean")){
            boolean w = false;
            return w;
        }
        else if(nameType.equals(("Char"))){
            char w = '\u0000';
            return w;
        }
        return 1;

    }

    public List <Integer> emptyList(){
        List <Integer> myList = new ArrayList<Integer>();
        return myList;
    }

    public List <Integer> makeList(Object x){
        List <Integer> oneElementList = new ArrayList<Integer>();
        for (int i=0; i <quadList.size(); i++){
            if (quadList.get(i).oneElement(x))
                oneElementList.add(i);
        }
        return oneElementList;
    }

    public int Place(String name,SymbolTable table){
        return table.FindVariablePosition(name);
    }

    public String Type(String name, SymbolTable table){
        return table.FindVariableType(name);
    }


    public List<Integer> nextList(){

        List<Integer> tags = new ArrayList<Integer>();
        List<String> ops = new ArrayList<String>();

        //ops.add(opCode.getRelop());
        //ops.add(opCode.getIfb());
        ops.add(opCode.getJump());
        ops.add(opCode.getJumpl());

        for(int i=0; i< quadList.size();i++){
            for(int j=0;j<ops.size();j++){
                if(quadList.get(i).getOp().equals(ops.get(j))){
                    tags.add(i);
                }
            }

        }
        return tags;
    }

    public List<Integer> trueList(){

        List<Integer> tags = new ArrayList<Integer>();

        for(int i=0; i< quadList.size();i++){

            if(quadList.get(i).getOp().getClass().getSimpleName().equals("operator")) {
                operator p = (operator) quadList.get(i).getOp();
                if (p.getName().equals("relop")) {
                    if (quadList.get(i).relopTrueFalse()) {
                        tags.add(i);
                    }
                }
            }
            else {
                String p = (String) quadList.get(i).getOp();
                if (p.equals(opCode.getIfb())) {
                    Boolean x = (Boolean) quadList.get(i).getArg1();
                    if (x) {
                        tags.add(i);
                    }
                }
            }


        }
        return tags;
    }

    public List<Integer> falseList(){

        List<Integer> tags = new ArrayList<Integer>();


        for(int i=0; i< quadList.size();i++){

            if(quadList.get(i).getOp().getClass().getSimpleName().equals("operator")) {
                operator p = (operator) quadList.get(i).getOp();
                if (p.getName().equals("relop")) {
                    if (!quadList.get(i).relopTrueFalse()) {
                        tags.add(i);
                    }
                }
            }
            else {
                String p = (String) quadList.get(i).getOp();
                if (p.equals(opCode.getIfb())) {
                    Boolean x = (Boolean) quadList.get(i).getArg1();
                    if (!x) {
                        tags.add(i);
                    }
                }
            }

        }
        return tags;
    }

    public  List<Integer> merge(List<Integer>... lists){

        List<Integer> mergedList = new ArrayList<Integer>();

        for(int i=0;i<lists.length;i++){
            mergedList.addAll(lists[i]);
        }
        return mergedList;
    }

    public void backpatch(int l,int z){

        for(int i=0; i<quadList.size();i++){
            if(quadList.get(i).getArg3().equals(l)){
                quadList.get(i).setArg3(z);
            }
        }

    }

    public void print(){            //print list
        //System.out.println("printing list");
        for (int i=0; i<this.quadList.size();i++){
            System.out.println(i + ": " + this.quadList.get(i).getOp() + ", " + this.quadList.get(i).getArg1() + ", " + this.quadList.get(i).getArg2() + ", " + this.quadList.get(i).getArg3());
        }
        //System.out.println("end of printing list");
    }

    public void insertReg(InterReg reg){
        this.regList.add(reg);
    }

    public void printReg(){            //print list

        for (int i=0; i<this.regList.size();i++){
            System.out.println("W: " + regList.get(i).getW() + " of tag: " + regList.get(i).getTag() + " of call: " + regList.get(i).getCall() + " of type: " + this.regList.get(i).getType());
        }

    }


}
