package compiler;

import compiler.analysis.DepthFirstAdapter;
import compiler.node.*;
import java.util.ArrayList;
import java.util.List;
import java.util.*;
import compiler.SymbolTable;
import compiler.ScopeObject;
import sun.reflect.generics.scope.Scope;


public class PrinterAST extends DepthFirstAdapter{

    SymbolTable table = new SymbolTable();

    int indentation = 0;
    private void addIndentationLevel() {
        indentation++;
    }

    private void removeIndentationLevel() {
        indentation--;
    }

    private void printIndentation() {
        // create a list with n copies
        List list = Collections.nCopies(indentation, " ");

        // create an iterator
        Iterator itr = list.iterator();

        while (itr.hasNext()){
            System.out.print(itr.next());
        }
    }



    @Override
    public void inAAllFuncDef(AAllFuncDef node)
    {
        addIndentationLevel();
        printIndentation();
        System.out.println("(Function Definition:");




        List<String> myList = new ArrayList<String>(Arrays.asList(node.getL().toString().split(" ")));


        List<String> listParam = new ArrayList<String>(myList);


        listParam.remove(0);
        listParam.remove(listParam.size()-1);

        List<String> listParamHelp = new ArrayList<String>(listParam);



        List<String> firstList = new ArrayList<String>();
        List<String> keyWords = new ArrayList<String>();

        int j = 0;

        while(j<listParam.size()) {


            if (!listParam.get(j).equals("int") && !listParam.get(j).equals("char") && !listParam.get(j).contains("int[") && !listParam.get(j).contains("char[")) {

                firstList.add(listParam.get(j));
                listParam.remove(j);
            }
            else if(listParam.get(j).equals("int")){

                j++;
            }
            else if(listParam.get(j).equals("char")){

                j++;
            }
            else{

                j++;
            }

        }



        System.out.println("ALL PARAMETERS: " + listParamHelp + "ONLY PAR: " +firstList);


        List<String> parList = new ArrayList<String>();
        Map<String,List> parType = new HashMap<String,List>();
        int i=0;
        int k=0;
        while(i<listParamHelp.size() && k<firstList.size()){
            if(k==firstList.size()-1){
                if(k==0){
                    //teleutaio par
                    parList.add(listParamHelp.get(i));
                    i++;
                    List previousList ;
                    if(!parType.isEmpty()){
                        if(parType.containsKey(listParamHelp.get(i)))
                        {
                            previousList = parType.get(listParamHelp.get(i));
                            for(int m=0;m<parList.size();m++)
                            {
                                previousList.add(parList.get(m));
                            }
                            parType.put(listParamHelp.get(i),previousList);
                        }
                    }

                    else{
                        parType.put(listParamHelp.get(i),parList);
                    }
                    break;
                }
                if(listParamHelp.get(i).equals(firstList.get(k))){
                    parList.add(listParamHelp.get(i));
                    i++;
                    k++;
                }
                else{
                    List previousList ;
                    if(parType.containsKey(listParamHelp.get(i)))
                    {
                        previousList = parType.get(listParamHelp.get(i));

                        for(int m=0;m<parList.size();m++)
                        {
                            previousList.add(parList.get(m));
                        }
                        parType.put(listParamHelp.get(i),previousList);
                    }
                    else{
                        parType.put(listParamHelp.get(i),parList);
                    }
                    parList = new ArrayList<String>();
                    i++;
                }
                //teleutaio par
                List previousList ;
                int key = i+1;

                if(listParamHelp.size()-1 == i){
                    if(parType.containsKey(listParamHelp.get(i)))
                    {
                        previousList = parType.get(listParamHelp.get(i));
                        for(int m=0;m<parList.size();m++)
                        {
                            previousList.add(parList.get(m));
                        }
                        parType.put(listParamHelp.get(i),previousList);
                    }
                    else{
                        parType.put(listParamHelp.get(i),parList);
                    }
                }
                else if(listParamHelp.size()-2 == i){
                    parList.add(listParamHelp.get(i));
                    if(parType.containsKey(listParamHelp.get(key))){
                        previousList = parType.get(listParamHelp.get(key));
                        for(int m=0;m<parList.size();m++)
                        {
                            previousList.add(parList.get(m));
                        }
                        parType.put(listParamHelp.get(key),previousList);
                    }
                    else{
                        parType.put(listParamHelp.get(key),parList);
                    }
                }
                break;
            }

            if(listParamHelp.get(i).equals(firstList.get(k)))
            {
                parList.add(listParamHelp.get(i));
                i++;
                k++;
            }
            else{

                List previousList ;
                if(parType.containsKey(listParamHelp.get(i)))
                {
                    previousList = parType.get(listParamHelp.get(i));
                    for(int m=0;m<parList.size();m++)
                    {
                        previousList.add(parList.get(m));
                    }
                    parType.put(listParamHelp.get(i),previousList);
                }
                else{
                    parType.put(listParamHelp.get(i),parList);
                }
                parList = new ArrayList<String>();
                i++;
            }
        }

        System.out.println("FULL DICTIONARY: " + parType.entrySet());


        ScopeObject obj =   new ScopeObject(myList.get(0).trim(),myList.get(myList.size()-1).trim(),"func") ;
        table.enter(obj);

        //insert to funcStack

        FuncScope funcObj = new FuncScope(myList.get(0).trim(),table.getPosition(),parType,myList.get(myList.size()-1).trim(),firstList.size());

        table.insertFuncStack(funcObj);

        table.printFuncStack();

        Set list = table.getMap().entrySet();
        System.out.println("MAPPINGS" + list + obj.getName());
        table.print();




    }


    @Override
    public void outAAllFuncDef(AAllFuncDef node)            //exit functions
    {
        System.out.println(")" );
        table.exit();
        Set list = table.getMap().entrySet();
    }


    @Override
    public void inAFparDefFuncDef(AFparDefFuncDef node)
    {

        System.out.println("(FPar Definition With Ref ");

        String type = node.getR().toString();
        List<String> myList = new ArrayList<String>(Arrays.asList(node.getL().toString().split(" ")));
        System.out.print("Printing List "+ myList);
        for(int i=0;i<myList.size();i++){
            ScopeObject obj =   new ScopeObject(myList.get(i).trim(),type.trim(),"par") ;
            table.insert(obj);
            Set list = table.getMap().entrySet();
            System.out.println("MAPPINGS " + list+ obj.getName());
        }

        table.print();


    }



    @Override
    public void inAFparDefNoRefFuncDef(AFparDefNoRefFuncDef node)
    {
       // addIndentationLevel();
       // printIndentation();
        System.out.println("(FPar Definition No Ref ");

        String type = node.getR().toString();
        List<String> myList = new ArrayList<String>(Arrays.asList(node.getL().toString().split(" ")));
        System.out.print("List "+ myList);
        for(int i=0;i<myList.size();i++){
            ScopeObject obj =   new ScopeObject(myList.get(i).trim(),type.trim(),"par") ;
            table.insert(obj);
            Set list = table.getMap().entrySet();
            System.out.println("MAPPINGS" + list+ obj.getName());
        }

        table.print();




    }



    @Override
    public void inAVarDefFuncDef(AVarDefFuncDef node)
    {
        //addIndentationLevel();
       // printIndentation();

        String type = node.getR().toString();
        List<String> myList = new ArrayList<String>(Arrays.asList(node.getL().toString().split(" ")));
        for(int i=0;i<myList.size();i++){
            ScopeObject obj =   new ScopeObject(myList.get(i).trim(),type.trim(),"var") ;
            System.out.println("List "+ myList.get(i));
            table.insert(obj);
            Set list = table.getMap().entrySet();
            System.out.println("MAPPINGS" + list+ obj.getName());
        }
        table.print();

    }

    @Override
    public void inAFuncDeclFuncDef(AFuncDeclFuncDef node)
    {

        System.out.println("FUNCTION DECLARATION" + node.getFuncDef().toString() );
        List<String> myList = new ArrayList<String>(Arrays.asList(node.getFuncDef().toString().split(" ")));
        System.out.println("LISTA DECL"+myList);

        ScopeObject obj = new ScopeObject(myList.get(0).toString().trim(),myList.get(myList.size()-1).toString().trim(),"decl");
        table.insert(obj);

        Set list = table.getMap().entrySet();
        System.out.println("MAPPINGS" + list+ obj.getName());
    }


    @Override
    public void inAExpressionStmt(AExpressionStmt node)
    {
        addIndentationLevel();
        printIndentation();
        System.out.print("(STMT: Left child " + node.getL().toString() + " Right child " +node.getR().toString() +"!");
        System.out.println(node.getR().getClass().getSimpleName());

        String name=node.getL().toString().trim();
        name = name.replace("[","");
        name = name.replace("]","");
        name = name.trim();
        String type;//="";
        String str=node.getR().toString().trim();
        //checking for value
        if (str.contains("\"")){
            //not sure about this one
            int length=str.length()-1;
            type = "char[" +length+"]";
        }
        else if (node.getR().getClass().getSimpleName().equals("ALetterAllExpr")) {
            System.out.println("CHAR");
            type= "char";
        }
        else if (node.getR().getClass().getSimpleName().equals("AConstantAllExpr")){
            System.out.println("CONSTANT");
            type= "int";
        }
        else if (node.getR().getClass().getSimpleName().equals("AAddSubAllExpr")){
            System.out.println("AAddSubAllExpr");
            type= "int";
        }
        else if (node.getR().getClass().getSimpleName().equals("ARestSignsAllExpr")){
            System.out.println("ARestSignsAllExpr");
            type= "int";
        }
        else if (node.getR().getClass().getSimpleName().equals("AWithPlminAllExpr")){
            System.out.println("AWithPlminAllExpr");
            type= "int";
        }
        else {
            System.out.println("VARIABLE");
            //find variables type
            type = table.FindVariableType(str);
            if (type==null){
                System.err.println("ERROR! A VARIABLE WITH NO TYPE");
            }
            //find second variable type
        }


        //WHAT ABOUT ARRAYS


        ScopeObject obj = new ScopeObject(name,type,"var");
        if (table.lookupVarAndType(obj)) {
            //incorrect type
            //throw error
            System.err.println("ERROR INCORECT TYPE OF VARIABLE");

        }
        else {
            System.out.println("CORRECT STMT");
        }

    }


    @Override
    public void inAVarStmt(AVarStmt node)
    {
        addIndentationLevel();
        printIndentation();
        System.out.print("(Variable name : " + node.getVarName().toString());

        if(table.FindVariableType(node.getVarName().toString().trim())==null)
            System.err.println("found variable not declared!!!!");


    }



    @Override
    public void inAAddSubAllExpr(AAddSubAllExpr node)
    {
        addIndentationLevel();
        printIndentation();
        System.out.print("(ADD- SUB EXPRESSION left child: " + node.getL().toString() + "right child"+node.getR().toString()+"!");
        String ournode = node.getL().toString() + node.getR().toString();

        List<String> myList = new ArrayList<String>(Arrays.asList(ournode.split(" ")));
        System.out.print("MYLIST "+ myList);
        for(int i=0;i<myList.size();i++) {

            if (myList.get(i).equals("+") || myList.get(i).equals("-")) {
                myList.remove(myList.get(i));
            }
            if(myList.get(i).contains("'")){
                System.err.println("Given char in numerical expression");
            }

        }
        for(int i=0;i<myList.size();i++) {

            String type = table.FindVariableType(myList.get(i).trim());
            System.out.println("Type is " + type);
            if (type != null) {
                if (!type.equals("int")) {
                    System.err.println("error wrong expr type");
                } else System.out.println("ok type");
            }
            else System.out.println("null type");
        }


        System.out.print("MYLIST IS "+ myList);


    }


    @Override
    public void inARestSignsAllExpr(ARestSignsAllExpr node)
    {
        addIndentationLevel();
        printIndentation();
        System.out.print("(Rest_signs expr: " + node.getL().toString() + " right child "+node.getR().toString());


        addIndentationLevel();
        printIndentation();
        String ournode = node.getL().toString() + node.getR().toString();

        List<String> myList = new ArrayList<String>(Arrays.asList(ournode.split(" ")));
        System.out.print("MYLIST "+ myList);
        for(int i=0;i<myList.size();i++) {

            if (myList.get(i).equals("*")  || myList.get(i).equals("mod") || myList.get(i).equals("div")) {
                myList.remove(myList.get(i));
            }

            if(myList.get(i).contains("'")){
                System.err.println("Given char in numerical expression");
            }

        }
        for(int i=0;i<myList.size();i++) {

            String type = table.FindVariableType(myList.get(i).trim());
            System.out.println("Type is " + type);
            if (type != null) {
                if (!type.equals("int")) {
                    System.err.println("error wrong expr type");
                } else System.out.println("ok type");
            }
            else System.out.println("null type");
        }




        System.out.print("MYLIST IS "+ myList);

    }


    @Override
    public void inAWithPlminAllExpr(AWithPlminAllExpr node)
    {
        addIndentationLevel();
        printIndentation();
        System.out.print("(PROSIMO expr: Left child " + node.getL().toString() + " right child "+ node.getR().toString()+"!");
        if(node.getR().toString().contains("\"") || node.getR().toString().contains("'")){
            System.err.println("Given string in numerical expression");
        }

        String type = table.FindVariableType(node.getR().toString().trim());
        if(type != null){
            if (!type.equals("int")) {
                System.err.println("error, wrong type expression");
            }
            else
                System.out.println("ok");
        }
        else
            System.out.println("null");
    }


    @Override
    public void inAExprsignsCond(AExprsignsCond node)
    {
        addIndentationLevel();
        printIndentation();
        System.out.print("(EXPRESSION CONDITION: left child " + node.getL().toString() + " right child "+node.getR().toString() + "!");



        String ournode = node.getL().toString() + node.getR().toString();

        List<String> myListLeft = new ArrayList<String>(Arrays.asList(node.getL().toString().split(" ")));
        List<String> myListRight = new ArrayList<String>(Arrays.asList(node.getR().toString().split(" ")));
        for(int i=0;i<myListLeft.size();i++) {

            if (myListLeft.get(i).equals("=") || myListLeft.get(i).equals("#")  || myListLeft.get(i).equals("<") || myListLeft.get(i).equals(">") || myListLeft.get(i).equals("<=") || myListLeft.get(i).equals(">=")) {
                myListLeft.remove(myListLeft.get(i));
            }

        }

        if (myListLeft.size()==1){
            String name = myListLeft.get(0).trim();
            String type = table.FindVariableType(name);
            System.out.println("Type is " + type);
            if (type != null) {
                if (!type.equals("int")) {
                    System.err.println("error wrong cond LEFT type");
                } else System.out.println("ok type");
            }
            else System.out.println("null type");
        }
        if (myListRight.size()==1){
            String name = myListRight.get(0).trim();
            String type = table.FindVariableType(name);
            System.out.println("Type is " + type);
            if (type != null) {
                if (!type.equals("int")) {
                    System.err.println("error wrong cond  RIGHT type");
                } else System.out.println("ok type");
            }
            else{
                System.out.println("null type");

            }
        }


        List<String> listFinal = new ArrayList<String>();
        listFinal.addAll(myListLeft);
        listFinal.addAll(myListRight);


        System.out.print("MY LIST final IS "+ listFinal);

        for(int i=0;i<listFinal.size();i++) {

            if(listFinal.get(i).contains("\"") || listFinal.get(i).contains("'")){
                System.err.println("GIVEN STRING IN NUMERICAL CONDITION");
            }

        }


    }

    @Override
    public void inAFuncCallWithoutStmt(AFuncCallWithoutStmt node){
        System.out.println("FUNC CALL WITHOUT PAR: " + node.getVarName().toString().trim() + "!");
        String funcName = node.getVarName().toString().trim();

        try{
            if(!table.checkScopeWithout(funcName,table.getPosition())){
                throw new MyException("CANNOT CALL FUNCTION WITHOUT PREVIOUSLY STATED.CHECK NAME AND SCOPE ACCORDINGLY.");
            }
        }catch (MyException e){
            throw new IllegalStateException("CANNOT CALL FUNCTION WITHOUT PREVIOUSLY STATED.CHECK NAME AND SCOPE ACCORDINGLY.");
        }
    }

    @Override
    public void inAFuncCallWithStmt(AFuncCallWithStmt node){
        System.out.println("FUNC CALL WITH PAR: " + node.getL().toString() + "!" + node.getR().toString() + "!");

        String funcName = node.getL().toString().trim();
        List<String> parameters = new ArrayList<String>(Arrays.asList(node.getR().toString().split(" ")));

        System.out.println(parameters + "!");


        try{
            if(!table.checkScopeWith(funcName,table.getPosition(),parameters)){
                throw new MyException("CANNOT CALL FUNCTION WITHOUT PREVIOUSLY STATED.\nCHECK NAME,SCOPE AND NUMBER OF PARAMETERS ACCORDINGLY.");
            }
        }catch (MyException e){
            throw new IllegalStateException("CANNOT CALL FUNCTION WITHOUT PREVIOUSLY STATED.\nCHECK NAME,SCOPE AND NUMBER OF PARAMETERS ACCORDINGLY.");
        }
    }





}