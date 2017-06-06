package compiler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by anton_000 on 30/5/2017.
 */
import compiler.Quad;
public class Intermediate {

    private int count;
    List<Quad> quadList;
    List<InterReg> regList;
    List<placeHelper> helpList;

    private OpCode opCode;


    public Intermediate(){
        this.count=0;
        this.quadList = new ArrayList<Quad>();
        this.opCode = new OpCode();
        this.regList = new ArrayList<InterReg>();
        this.helpList = new ArrayList<placeHelper>();
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

        System.out.println(count + ":" +forPrint);
        count++;
        Quad myquad = new Quad(op,arg1,arg2,arg3);
        this.quadList.add(myquad);


    }

    public int nextQuad() {
        return count;
    }


    public Object newTemp(String nameType){

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

    public String Place(String expr){

        for(int i=0;i<helpList.size();i++){
            if(helpList.get(i).getExpr().replaceAll("\\s+","").equals(expr.replaceAll("\\s+",""))){
                return helpList.get(i).getPosition();
            }
        }
        return null;
    }

    public String Type(String name, SymbolTable table){
        return table.FindVariableType(name);
    }


    public List<Integer> nextList(){

        List<Integer> tags = new ArrayList<Integer>();
        List<String> ops = new ArrayList<String>();

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

    public void backpatch(String tag,int z){

        for(int i=0; i<quadList.size() ;i++){
            String str =String.valueOf(quadList.get(i).getArg3());
            if(str.equals(tag)){
                quadList.get(i).setArg3(z);
            }
        }
    }

    public void print(){            //print list
        System.out.println();
        for (int i=0; i<this.quadList.size();i++){
            if(this.quadList.get(i).getOp().getClass().getSimpleName().equals("operator")){
                operator myOp = (operator) this.quadList.get(i).getOp();
                System.out.println(i + ": " +myOp.getValue() + ", " + this.quadList.get(i).getArg1() + ", " + this.quadList.get(i).getArg2() + ", " + this.quadList.get(i).getArg3());

            }
            else
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

    public void insertRet(){
        //System.err.println("WE ARE INSIDE insertRet");
        int counter = getCount()-2;

        if (this.quadList.get(getCount()-1).getOp().equals(this.getOpCode().getCall())){
            //System.err.println("WE ARE INSIDE CALL");
            while(counter>=0){
                if (this.quadList.get(counter).getOp().equals(this.getOpCode().getPar())){
                    //System.err.println("WE ARE INSIDE PAR");
                    this.quadList.get(counter).setArg2("RET");
                }
                else
                    break;
                counter--;
            }
        }
    }

    public boolean insertRef(String funcName,SymbolTable table){
        //System.err.println("WE ARE INSIDE insertRef");
        int counter = getCount()-1;
        //System.err.println("counter " +counter);
        Map<String,List> refs = new HashMap<String, List>(table.getRefPar());
        List<String> keys = new ArrayList<String>(refs.keySet());

        if(refs.containsKey(funcName))
        {
            List<String> values =  refs.get(funcName);
            //System.err.println("aaaaaaaaa "+ values);

            while(counter>=0){
                if (this.quadList.get(counter).getOp().equals(this.getOpCode().getPar())){
                    //System.err.println("MUST CHECK PARAMETER");
                    if(values.contains(this.quadList.get(counter).getArg1())){
                        this.quadList.get(counter).setArg2("R");
                    }
                }
                else
                    break;
                counter--;
            }
            return true;
        }
        else{
            return false;
        }


    }

    public int whileJump(){
        //System.err.println("WE ARE INSIDE insertRet");

        int counter = getCount()-1;

        if (this.quadList.get(getCount()-1).getOp().equals(this.getOpCode().getJump())){
            System.err.println("WE ARE INSIDE jump");
            while(counter>=0){
                if (this.quadList.get(counter).getOp().equals(this.getOpCode().getJump())){
                    System.err.println("WE ARE INSIDE set jump");
                    //this.quadList.get(counter).setArg2("RET");
                }
                else
                    break;
                counter--;
            }
            return counter;
        }
        return -1;
    }




    public void insertPlaceHelper(String expr,String position){
        placeHelper obj = new placeHelper(expr,position);
        this.helpList.add(obj);
    }

    public void printPlace(){
        System.out.println();
        for (int i=0; i<this.helpList.size();i++){
            System.out.println("EXPR: \"" + helpList.get(i).getExpr() + "\" is in variable: \"" + helpList.get(i).getPosition() +"\"");
        }
    }


}
