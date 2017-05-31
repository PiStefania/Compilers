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

    private OpCode opCode;


    public Intermediate(){
        this.count=0;
        this.quadList = new ArrayList<Quad>();
        this.opCode = new OpCode();
    }

    public int getCount() {return count;}

    public List<Quad> getQuadList() {return quadList;}

    public OpCode getOpCode() {return opCode;}

    public void genQuad(Object op, Object arg1, Object arg2, Object arg3) {
        String forPrint ="";
        if (op != null)
            forPrint += " " + op.toString();
        if (arg1 != null)
            forPrint += " " +arg1.toString();
        if (arg2 != null)
            forPrint += " " +arg2.toString();
        if (arg3 != null)
            forPrint += " " +arg3.toString();

        System.out.println(count + ":" +forPrint);
        count++;
        Quad myquad = new Quad(op,arg1,arg2,arg3);
        this.quadList.add(myquad);


    }

    public int nextQuad() {
        return count;
    }

    public <t> t newTemp(Object t){
        Object myTemp = new Object();
        return (t) myTemp;
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

            operator p = (operator) quadList.get(i).getOp();
            System.out.println("quadList.get(i).getOp() "+ p.getName() + p.getValue());
            //System.out.println("opCode.getRelop().getName() "+opCode.getRelop().getName());

            if (p.getName().equals("relop")) {
                //System.out.println("AAAAAAA");
                if (quadList.get(i).relopTrueFalse()) {
                    tags.add(i);
                }
            }
            else if (p.equals(opCode.getIfb())) {
                Boolean x = (Boolean) quadList.get(i).getArg1();
                System.out.println("BB");
                if(x){
                    tags.add(i);
                }
            }


        }
        return tags;
    }

    public List<Integer> falseList(){

        List<Integer> tags = new ArrayList<Integer>();


        for(int i=0; i< quadList.size();i++){

            if (quadList.get(i).getOp().equals(opCode.getRelop().getName())) {
                if (!quadList.get(i).relopTrueFalse()) {
                    tags.add(i);
                }
            }
            else if (quadList.get(i).getOp().equals(opCode.getIfb())) {
                Boolean x = (Boolean) quadList.get(i).getArg1();
                if(!x){
                    tags.add(i);
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

    public void print(){            //print stack
        //System.out.println("printing stack");
        for (int i=0; i<this.quadList.size();i++){
            System.out.println(this.quadList.get(i).getOp() + " " +this.quadList.get(i).getArg3());
        }
        //System.out.println("end of printing stack");
    }


}
