package compiler;

import compiler.analysis.DepthFirstAdapter;
import compiler.node.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import compiler.SymbolTable;
import compiler.ScopeObject;
import jdk.nashorn.internal.runtime.ListAdapter;
import sun.reflect.generics.scope.Scope;


public class PrinterAST extends DepthFirstAdapter{

    SymbolTable table = new SymbolTable();

    Intermediate im = new Intermediate();


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
          //  System.out.print(itr.next());
        }
    }


    @Override
    public void inAProgram(AProgram node){
        //insert sunarthseis vivliothikhs

        //fun puti (n: int):nothing;
        Map<String,List> parType = new HashMap<String,List>();
        List<String> par = new ArrayList<String>();
        par.add("n");
        parType.put("int",par);
        FuncScope funcObj = new FuncScope("puti",parType,"nothing",par.size());
        table.insertFuncStack(funcObj);


        //fun putc (c: char):nothing;
        parType = new HashMap<String,List>();
        par = new ArrayList<String>();
        par.add("c");
        parType.put("char",par);
        funcObj = new FuncScope("putc",parType,"nothing",par.size());
        table.insertFuncStack(funcObj);


        //fun puts (ref s: char[]):nothing;
        parType = new HashMap<String,List>();
        par = new ArrayList<String>();
        par.add("s");
        parType.put("char[]",par);
        funcObj = new FuncScope("puts",parType,"nothing",par.size());
        table.insertFuncStack(funcObj);


        //fun geti ():int;
        parType = new HashMap<String,List>();
        par = new ArrayList<String>();
        funcObj = new FuncScope("geti",parType,"int",par.size());
        table.insertFuncStack(funcObj);


        //fun getc ():char;
        parType = new HashMap<String,List>();
        par = new ArrayList<String>();
        funcObj = new FuncScope("getc",parType,"char",par.size());
        table.insertFuncStack(funcObj);


        //fun gets (n:int ; ref s:char[]):nothing;
        parType = new HashMap<String,List>();
        par = new ArrayList<String>();
        par.add("n");
        parType.put("int",par);
        par.remove("n");
        par.add("s");
        parType.put("char[]",par);
        funcObj = new FuncScope("gets",parType,"nothing",par.size());
        table.insertFuncStack(funcObj);


        //fun abs (n : int):int;
        parType = new HashMap<String,List>();
        par = new ArrayList<String>();
        par.add("n");
        parType.put("int",par);
        funcObj = new FuncScope("abs",parType,"int",par.size());
        table.insertFuncStack(funcObj);


        //fun ord (c: char):int;
        parType = new HashMap<String,List>();
        par = new ArrayList<String>();
        par.add("c");
        parType.put("char",par);
        funcObj = new FuncScope("ord",parType,"int",par.size());
        table.insertFuncStack(funcObj);


        //fun chr (n : int):char;
        parType = new HashMap<String,List>();
        par = new ArrayList<String>();
        par.add("n");
        parType.put("int",par);
        funcObj = new FuncScope("chr",parType,"char",par.size());
        table.insertFuncStack(funcObj);


        //fun strlen (ref s:char[]):int;
        parType = new HashMap<String,List>();
        par = new ArrayList<String>();
        par.add("s");
        parType.put("char[]",par);
        funcObj = new FuncScope("strlen",parType,"int",par.size());
        table.insertFuncStack(funcObj);


        //fun strcmp (ref s1, s2 :char[]):int;
        parType = new HashMap<String,List>();
        par = new ArrayList<String>();
        par.add("s1");
        par.add("s2");
        parType.put("char[]",par);
        funcObj = new FuncScope("strcmp",parType,"int",par.size());
        table.insertFuncStack(funcObj);


        //fun strcpy (ref trg ,src :char[]):nothing;
        parType = new HashMap<String,List>();
        par = new ArrayList<String>();
        par.add("trg");
        par.add("src");
        parType.put("char[]",par);
        funcObj = new FuncScope("strcpy",parType,"nothing",par.size());
        table.insertFuncStack(funcObj);


        //fun strcat (ref trg ,src :char[]):nothing;
        parType = new HashMap<String,List>();
        par = new ArrayList<String>();
        par.add("trg");
        par.add("src");
        parType.put("char[]",par);
        funcObj = new FuncScope("strcat",parType,"nothing",par.size());
        table.insertFuncStack(funcObj);




    }

    @Override
    public void outAProgram(AProgram node){
        //exit stoiva sunarthsewn
        table.deleteFuncStack();

        System.out.println("Successful compilation!");

        im.print();

    }



    @Override
    public void inAAllFuncDef(AAllFuncDef node)
    {

       // System.out.println("(Function Definition:");




        List<String> myList = new ArrayList<String>(Arrays.asList(node.getL().toString().split(" ")));


        List<String> listParam = new ArrayList<String>(myList);

        while(listParam.contains("ref"))
            listParam.remove("ref");


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



       //
        System.out.println("ALL PARAMETERS ALL: " + listParamHelp + "ONLY PAR: " +firstList);




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

        FuncScope funcObj = new FuncScope(myList.get(0).trim(),parType,myList.get(myList.size()-1).trim(),firstList.size());

        table.insertFuncStack(funcObj);





        Set list = table.getMap().entrySet();
        //System.out.println("MAPPINGS" + list + obj.getName());
        //table.print();



        im.genQuad(im.getOpCode().getUnit(),myList.get(0).trim(),null,null);

    }


    @Override
    public void outAAllFuncDef(AAllFuncDef node)            //exit functions
    {
        List<String> myList = new ArrayList<String>(Arrays.asList(node.getL().toString().split(" ")));
       // System.out.println(")" );
        table.exit();
        Set list = table.getMap().entrySet();
        im.genQuad(im.getOpCode().getEndu(),myList.get(0).trim(),null,null);

    }


    @Override
    public void inAFparDefFuncDef(AFparDefFuncDef node)
    {

        //System.out.println("(FPar Definition With Ref ");

        String type = node.getR().toString();
        List<String> myList = new ArrayList<String>(Arrays.asList(node.getL().toString().split(" ")));

        while(myList.contains("ref"))
            myList.remove("ref");
       // System.out.print("Printing List "+ myList);
        for(int i=0;i<myList.size();i++){
            ScopeObject obj =   new ScopeObject(myList.get(i).trim(),type.trim(),"par") ;
            table.insert(obj);
            Set list = table.getMap().entrySet();
          //  System.out.println("MAPPINGS " + list+ obj.getName());
        }

       // table.print();


    }



    @Override
    public void inAFparDefNoRefFuncDef(AFparDefNoRefFuncDef node)
    {
       // addIndentationLevel();
       // printIndentation();
        //System.out.println("(FPar Definition No Ref ");

        String type = node.getR().toString();
        List<String> myList = new ArrayList<String>(Arrays.asList(node.getL().toString().split(" ")));
      //  System.out.print("List "+ myList);
        for(int i=0;i<myList.size();i++){
            ScopeObject obj =   new ScopeObject(myList.get(i).trim(),type.trim(),"par") ;
            table.insert(obj);
            Set list = table.getMap().entrySet();
        //    System.out.println("MAPPINGS" + list+ obj.getName());
        }

       // table.print();




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
          //  System.out.println("List "+ myList.get(i));
            table.insert(obj);
            Set list = table.getMap().entrySet();
          //  System.out.println("MAPPINGS" + list+ obj.getName());
           // System.err.println(myList.get(i).trim() + " "+  table.FindVariablePosition(myList.get(i).trim()) + " "+ table.FindVariableType(myList.get(i).trim()) );
        }
       // table.print();




    }

    @Override
    public void inAFuncDeclFuncDef(AFuncDeclFuncDef node)
    {

      //  System.out.println("FUNCTION DECLARATION" + node.getFuncDef().toString() );
        List<String> myList = new ArrayList<String>(Arrays.asList(node.getFuncDef().toString().split(" ")));
       // System.out.println("LISTA DECL"+myList);

        ScopeObject obj = new ScopeObject(myList.get(0).toString().trim(),myList.get(myList.size()-1).toString().trim(),"decl");
        table.insert(obj);

        Set list = table.getMap().entrySet();
      //  System.out.println("MAPPINGS" + list+ obj.getName());
    }


    @Override
    public void inAExpressionStmt(AExpressionStmt node)
    {

        //System.err.println("(STMT: Left child " + node.getL().toString() + " Right child " +node.getR().toString() +"!");
      //  System.out.println(node.getR().getClass().getSimpleName());
        List<?> newl = (List) node.getL();
        //List<?> newr = (List) node.getL();


        //List<String> leftList = new ArrayList<String>(Arrays.asList(node.getL().toString().trim().split(" ")));
        //List<String> rightList = new ArrayList<String>(Arrays.asList(node.getR().toString().trim().split(" ")));



        //String name=leftList.get(0).trim();
        //name = name.replace("[","");
        //name = name.replace("]","");
        String name = newl.get(0).toString();

        String initialName=name;


        if (name.contains("[")){


            int i=0;
            String finalS="";
            String numb="";
            while(i<name.length()){
                char c = name.charAt(i);
                if(c == '['){
                   while(i<name.length()){
                       i++;
                       c = name.charAt(i);
                       if (c == ']')
                           break;

                       numb+=c;

                   }
                   break;
                }
                finalS += c;
                i++;
            }
            finalS=finalS.trim();
            numb=numb.trim();
            System.err.println("finalS "+finalS);
            System.err.println("numb "+numb);

            String type = table.FindVariableType(finalS);
            try{
                if(type==null){
                    throw new MyException("ERROR! VARIABLE DOESN'T EXIST");
                }
            }catch (MyException e){
                throw new IllegalStateException("ERROR! VARIABLE DOESN'T EXIST");
            }

            Object w = im.newTemp(type);
            String name2 = "$" + im.getCount();

            InterReg reg = new InterReg(w,im.getCount(),im.getOpCode().getArray(),w.getClass().getSimpleName(),name2);
            im.insertReg(reg);

            im.genQuad(im.getOpCode().getArray(),finalS,numb, "$" + im.getCount());
            name=finalS;
        }

        String type;
        String str=node.getR().toString();
        //str = str.replace("[","");
        //str = str.replace("]","");


        //checking for value

        String reg=name;
        if (initialName.contains("[")){
            reg="$" + (im.getCount()-1);
        }



        if (str.contains("\"")){

            //not sure about this one
            int length=str.length()-1;
            type = "char[" +length+"]";

            im.genQuad(im.getOpCode().getAssignment(),str,null,reg);

        }
        else if (node.getR().getClass().getSimpleName().equals("AFuncAllExpr")) {

         //   System.out.println("FUNCTION CALL");
            List<String> myList = new ArrayList<String>(Arrays.asList(node.getR().toString().split(" ")));
            String funcName = myList.get(0);
            type= table.getFuncType(funcName);
            try{
                if(type==null){
                    throw new MyException("ERROR! A FUNCTION THAT WASN'T DECLARED");
                }
            }catch (MyException e){
                throw new IllegalStateException("ERROR! A FUNCTION THAT WASN'T DECLARED");
            }


            //im.genQuad(im.getOpCode().getAssignment(),str,null,reg);

        }
        else if (node.getR().getClass().getSimpleName().equals("ALetterAllExpr")) {

         //   System.out.println("CHAR");
            type= "char";
            im.genQuad(im.getOpCode().getAssignment(),str,null,reg);
        }
        else if (node.getR().getClass().getSimpleName().equals("AConstantAllExpr")){

            //   System.out.println("CONSTANT");
            type= "int";
            im.genQuad(im.getOpCode().getAssignment(),str,null,reg);
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


          //  System.out.println("AWithPlminAllExpr");
            type= "int";
            im.genQuad(im.getOpCode().getAssignment(),str,null,reg);
        }
        else if (node.getR().toString().contains("[")){

            System.err.println("right "+node.getR().toString() + " "+ str);

            int i=0;
            String finalS="";
            String numb="";
            while(i<str.length()){
                char c = str.charAt(i);
                if(c == '['){
                    while(i<str.length()){
                        i++;
                        c = str.charAt(i);
                        if (c == ']')
                            break;

                        numb+=c;

                    }
                    break;
                }
                finalS += c;
                i++;
            }
            finalS=finalS.trim();
            numb=numb.trim();
            System.err.println("finalS "+finalS);
            System.err.println("numb "+numb);

            type = table.FindVariableType(finalS);
            try{
                if(type==null){
                    throw new MyException("ERROR! VARIABLE DOESN'T EXIST");
                }
            }catch (MyException e){
                throw new IllegalStateException("ERROR! VARIABLE DOESN'T EXIST");
            }

           /* Object w = im.newTemp(type);
            String name2 = "$" + im.getCount();

            InterReg reg2 = new InterReg(w,im.getCount(),im.getOpCode().getArray(),w.getClass().getSimpleName(),name2);
            im.insertReg(reg2);

            im.genQuad(im.getOpCode().getArray(),finalS,numb, "$" + im.getCount());

            im.genQuad(im.getOpCode().getAssignment(),"$" + (im.getCount()-1),null,name);
*/
        }
        else {
           // System.err.println("VARIABLE");


            //find variables type
            type = table.FindVariableType(str);
            try{
                if(type==null){
            //        System.err.println("name "+str);
                    throw new MyException("ERROR! A VARIABLE WITH NO TYPE");
                }
            }catch (MyException e){
                throw new IllegalStateException("ERROR! A VARIABLE WITH NO TYPE");
            }
            //find second variable type

            im.genQuad(im.getOpCode().getAssignment(),str,null,reg);
        }



        ScopeObject obj = new ScopeObject(name,type,"var");
        try{
            if (table.lookupVarAndType(obj)){
                throw new MyException("ERROR INCORECT TYPE OF VARIABLE");
            }
        }
        catch (MyException e){
            throw new IllegalStateException("ERROR INCORECT TYPE OF VARIABLE");
        }


    }

    @Override
    public void outAExpressionStmt(AExpressionStmt node)
    {

        List<?> newl = (List) node.getL();

        String name = newl.get(0).toString();

        String initialName=name;




        if (name.contains("[")){

            String numb="";
            int i=0;
            String finalS="";

            while(i<name.length()){
                char c = name.charAt(i);
                if(c == '['){
                    while(i<name.length()){
                        i++;
                        c = name.charAt(i);
                        if (c == ']')
                            break;

                        numb+=c;

                    }
                    break;
                }
                finalS += c;
                i++;
            }
            finalS=finalS.trim();
            numb=numb.trim();

            String type = table.FindVariableType(finalS);
            try{
                if(type==null){
                    throw new MyException("ERROR! VARIABLE DOESN'T EXIST");
                }
            }catch (MyException e){
                throw new IllegalStateException("ERROR! VARIABLE DOESN'T EXIST");
            }

            Object w = im.newTemp(type);
            String name2 = "$" + im.getCount();

            InterReg reg = new InterReg(w,im.getCount(),im.getOpCode().getArray(),w.getClass().getSimpleName(),name2);
            im.insertReg(reg);

            im.genQuad(im.getOpCode().getArray(),finalS,numb, "$" + im.getCount());
            name=finalS;
        }

        String type;
        String str=node.getR().toString();
        List myString = new ArrayList(Arrays.asList(str.split(" ")));

        //System.err.println(myString);


        //checking for value

        String reg=name;
        if (initialName.contains("[")){
            reg="$" + (im.getCount()-1);
        }


        if (node.getR().getClass().getSimpleName().equals("AFuncAllExpr")) {
            //System.err.println("(STMT: Left child " + node.getL().toString() + " Right child " +node.getR().toString() +"!");

            im.genQuad(im.getOpCode().getAssignment(),myString.get(0),null,reg);
        }
        else if (node.getR().getClass().getSimpleName().equals("AAddSubAllExpr")){

            im.genQuad(im.getOpCode().getAssignment(),"$" + (im.getCount()-1),null,reg);

        }

        else if (node.getR().getClass().getSimpleName().equals("ARestSignsAllExpr")){

            im.genQuad(im.getOpCode().getAssignment(),"$" + (im.getCount()-1),null,reg);

        }
        else if (node.getR().toString().contains("[")) {
            String tempString = node.getR().toString().trim();

            List tempList = new ArrayList(Arrays.asList(tempString.split(" ")));


            if (tempString.contains("+")|| tempString.contains("-") || tempString.contains("*") || tempString.contains("/") || tempString.contains("mod")){
                Object w = im.newTemp("Integer");
                String name2 = "$" + im.getCount();

                InterReg reg2 = new InterReg(w,im.getCount(),im.getOpCode().getAssignment(),w.getClass().getSimpleName(),name2);
                im.insertReg(reg2);
                im.genQuad(im.getOpCode().getArray(),tempList.get(0),"$" + (im.getCount()-1),name2);
                im.genQuad(im.getOpCode().getAssignment(),"$" + (im.getCount()-1),null,name);
            }
            else{
                String tempString2 = tempString;
                while (tempString2.contains("[") || tempString2.contains("]")){
                    tempString2 = tempString2.replace("[","");
                    tempString2 = tempString2.replace("]","");
                }
                List tempList2 = new ArrayList(Arrays.asList(tempString2.split("\\s+")));

                Object w = im.newTemp("Integer");
                String name2 = "$" + im.getCount();

                InterReg reg2 = new InterReg(w,im.getCount(),im.getOpCode().getAssignment(),w.getClass().getSimpleName(),name2);
                im.insertReg(reg2);
                im.genQuad(im.getOpCode().getArray(),tempList.get(0),tempList2.get(tempList2.size()-1),name2);
                im.genQuad(im.getOpCode().getAssignment(),"$" + (im.getCount()-1),null,name);
            }

        }

    }



        @Override
    public void inAVarStmt(AVarStmt node)
    {

        //System.out.print("(Variable name : " + node.getVarName().toString());

        try{
            if(table.FindVariableType(node.getVarName().toString().trim())==null){
                throw new MyException("FOUND VARIABLE NOT DECLARED");
            }
        }
        catch (MyException e){
            throw new IllegalStateException("FOUND VARIABLE NOT DECLARED");
        }

    }



    @Override
    public void inAAddSubAllExpr(AAddSubAllExpr node)
    {

        //System.out.println("(ADD- SUB EXPRESSION left child: " + node.getL().toString() + "right child"+node.getR().toString()+"!");
        String ournode = node.getL().toString() + node.getR().toString();

        List<String> myList = new ArrayList<String>(Arrays.asList(ournode.split(" ")));
        //System.out.print("MYLIST "+ myList);
        for(int i=0;i<myList.size();i++) {

            if (myList.get(i).equals("+") || myList.get(i).equals("-")) {
                myList.remove(myList.get(i));
            }
            try{
                if(myList.get(i).contains("'") || myList.get(i).contains("\"")){
                    throw new MyException("GIVEN STRING IN NUMERICAL EXPRESSION");
                }
            }
            catch (MyException e){
                throw new IllegalStateException("GIVEN STRING IN NUMERICAL EXPRESSION");
            }


        }
        for(int i=0;i<myList.size();i++) {

            String type = table.FindVariableType(myList.get(i).trim());
           // System.out.println("Type is " + type);
            if (type != null) {
                try{
                    if (!type.equals("int")){
                        throw new MyException("ERROR WRONG EXPR TYPE");
                    }
                }
                catch (MyException e){
                    throw new IllegalStateException("ERROR WRONG EXPR TYPE");
                }
            }
          //  else System.out.println("null type");
        }


     //   System.out.print("MYLIST IS "+ myList);


    }

    @Override
    public void outAAddSubAllExpr(AAddSubAllExpr node)
    {
        System.out.println("(ADD- SUB EXPRESSION left child: " + node.getL().toString() + "right child"+node.getR().toString()+"!");
        List<String> myList = new ArrayList(Arrays.asList(node.getL().toString().trim().split("\\s+")));
        System.err.println("myList "+myList);
        String op = myList.get(myList.size()-1);
       // System.err.println("op "+op);
        String rightNumb = node.getR().toString().trim();
        //System.err.println("rightNumb "+rightNumb);
        operator myOp = new operator("op",op);
        Object w = im.newTemp("Integer");
        String name2 = "$" + im.getCount();

        InterReg reg = new InterReg(w,im.getCount(),im.getOpCode().getAssignment(),w.getClass().getSimpleName(),name2);
        im.insertReg(reg);

        if (myList.size()==2){
            im.genQuad(myOp,myList.get(0),rightNumb,name2);
        }
        else{
            im.genQuad(myOp,"$"+(im.getCount()-1),rightNumb,name2);
        }
    }
    @Override
    public void outARestSignsAllExpr(ARestSignsAllExpr node)
    {
        System.out.println("(Rest_signs expr: " + node.getL().toString() + " right child "+node.getR().toString());
        List<String> myList = new ArrayList(Arrays.asList(node.getL().toString().trim().split("\\s+")));
        System.err.println("Rest myList "+myList);
        String op = myList.get(myList.size()-1);
        //System.err.println("op "+op);
        String rightNumb = node.getR().toString().trim();
       // System.err.println("rightNumb "+rightNumb);
        operator myOp = new operator("op",op);
        Object w = im.newTemp("Integer");
        String name2 = "$" + im.getCount();

        InterReg reg = new InterReg(w,im.getCount(),im.getOpCode().getAssignment(),w.getClass().getSimpleName(),name2);
        im.insertReg(reg);

        if (myList.size()==2){
            im.genQuad(myOp,myList.get(0),rightNumb,name2);
        }
        else{
            im.genQuad(myOp,"$"+(im.getCount()-1),rightNumb,name2);
        }
    }


    @Override
    public void inARestSignsAllExpr(ARestSignsAllExpr node)
    {

        //System.out.println("(Rest_signs expr: " + node.getL().toString() + " right child "+node.getR().toString());



        String ournode = node.getL().toString() + node.getR().toString();

        List<String> myList = new ArrayList<String>(Arrays.asList(ournode.split(" ")));
       // System.out.print("MYLIST "+ myList);
        for(int i=0;i<myList.size();i++) {

            if (myList.get(i).equals("*")  || myList.get(i).equals("mod") || myList.get(i).equals("div")) {
                myList.remove(myList.get(i));
            }

            try{
                if(myList.get(i).contains("'") || myList.get(i).contains("\"")){
                    throw new MyException("GIVEN STRING IN NUMERICAL EXPRESSION");
                }
            }
            catch (MyException e){
                throw new IllegalStateException("GIVEN STRING IN NUMERICAL EXPRESSION");
            }


        }
        for(int i=0;i<myList.size();i++) {

            String type = table.FindVariableType(myList.get(i).trim());
         //   System.out.println("Type is " + type);
            if (type != null) {
                try{
                    if (!type.equals("int")){
                        throw new MyException("ERROR WRONG EXPR TYPE");
                    }
                }
                catch (MyException e){
                    throw new IllegalStateException("ERROR WRONG EXPR TYPE");
                }
            }
          //  else System.out.println("null type");
        }




        //System.out.print("MYLIST IS "+ myList);

    }


    @Override
    public void inAWithPlminAllExpr(AWithPlminAllExpr node)
    {

       // System.out.print("(PROSIMO expr: Left child " + node.getL().toString() + " right child "+ node.getR().toString()+"!");
        try{
            if(node.getR().toString().contains("\"") || node.getR().toString().contains("'")){
                throw new MyException("GIVEN STRING IN NUMERICAL EXPRESSION");
            }
        }
        catch (MyException e){
            throw new IllegalStateException("GIVEN STRING IN NUMERICAL EXPRESSION");
        }



        String type = table.FindVariableType(node.getR().toString().trim());
        if(type != null){
            try{
                if (!type.equals("int")){
                    throw new MyException("ERROR WRONG EXPR TYPE");
                }
            }
            catch (MyException e){
                throw new IllegalStateException("ERROR WRONG EXPR TYPE");
            }
        }
       // else
          //  System.out.println("null");
    }


    @Override
    public void inAExprsignsCond(AExprsignsCond node)
    {

        //System.out.print("(EXPRESSION CONDITION: left child " + node.getL().toString() + " right child "+node.getR().toString() + "!");



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
          //  System.out.println("Type is " + type);
            if (type != null) {
                try{
                    if (!type.equals("int")){
                        throw new MyException("ERROR WRONG EXPR TYPE");
                    }
                }
                catch (MyException e){
                    throw new IllegalStateException("ERROR WRONG EXPR TYPE");
                }
            }
          //  else System.out.println("null type");
        }
        if (myListRight.size()==1){
            String name = myListRight.get(0).trim();
            String type = table.FindVariableType(name);
          //  System.out.println("Type is " + type);
            if (type != null) {
                try{
                    if (!type.equals("int")){
                        throw new MyException("ERROR WRONG EXPR TYPE");
                    }
                }
                catch (MyException e){
                    throw new IllegalStateException("ERROR WRONG EXPR TYPE");
                }
            }
          //  else{
          //      System.out.println("null type");

           // }
        }


        List<String> listFinal = new ArrayList<String>();
        listFinal.addAll(myListLeft);
        listFinal.addAll(myListRight);


       // System.out.print("MY LIST final IS "+ listFinal);

        for(int i=0;i<listFinal.size();i++) {

            try{
                if(listFinal.get(i).contains("\"") || listFinal.get(i).contains("'")){
                    throw new MyException("GIVEN STRING IN NUMERICAL EXPRESSION");
                }
            }
            catch (MyException e){
                throw new IllegalStateException("GIVEN STRING IN NUMERICAL EXPRESSION");
            }

        }


    }

    @Override
    public void inAFuncCallWithoutStmt(AFuncCallWithoutStmt node){
        //System.err.println("FUNC CALL WITHOUT PAR: " + node.getVarName().toString().trim() + "!");
        String funcName = node.getVarName().toString().trim();

        try{
            if(!table.checkScopeWithout(funcName)){
                throw new MyException("CANNOT CALL FUNCTION WITHOUT PREVIOUSLY STATED.CHECK NAME ACCORDINGLY.");
            }
        }catch (MyException e){
            throw new IllegalStateException("CANNOT CALL FUNCTION WITHOUT PREVIOUSLY STATED.CHECK NAME ACCORDINGLY.");
        }

        im.genQuad(im.getOpCode().getCall(),null,null,funcName);
    }

    @Override
    public void inAFuncCallWithStmt(AFuncCallWithStmt node){
       // System.err.println("FUNC CALL WITH PAR: " + node.getL().toString() + "!" + node.getR().toString() + "!");

        String funcName = node.getL().toString().trim();

        String nodeRight = node.getR().toString().trim();


        List<String> parameters = new ArrayList<String>(Arrays.asList(nodeRight.split(" ")));

        try{
            if(!table.checkScopeWith(funcName,parameters)){
                throw new MyException("CANNOT CALL FUNCTION WITHOUT PREVIOUSLY STATED.\nCHECK NAME AND NUMBER OF PARAMETERS ACCORDINGLY.");
            }
        }catch (MyException e){
            throw new IllegalStateException("CANNOT CALL FUNCTION WITHOUT PREVIOUSLY STATED.\nCHECK NAME AND NUMBER OF PARAMETERS ACCORDINGLY.");
        }



    }


    @Override
    public void outAFuncCallWithStmt(AFuncCallWithStmt node){
        String funcName = node.getL().toString().trim();
        im.genQuad(im.getOpCode().getCall(),null,null,funcName);
    }

    @Override
    public void inAParametersAllExpr(AParametersAllExpr node){
       // System.err.println("ALL PARAMETERS: left " + node.getL().toString() +" right " + node.getR().toString());

        List <String> parameters = new ArrayList<String>();
        parameters.add(node.getL().toString());

        List<String> newl = (List) node.getR();




        for (int i=0; i<newl.size();i++){
            parameters.add(newl.get(i));
        }



        for (int i=0; i<parameters.size();i++) {

            im.genQuad(im.getOpCode().getPar(), parameters.get(i),"V",null);
        }


    }



    @Override
    public void inAReturnStmt(AReturnStmt node){
        im.genQuad(im.getOpCode().getRet(),null,null,null);
    }

    @Override
    public void inAReturn2Stmt(AReturn2Stmt node){
        im.genQuad(im.getOpCode().getRet(),null,null,null);
    }


    @Override
    public void inAReturnWithStmt(AReturnWithStmt node) {

       // System.err.println("RETURN EXPR2: " + node.getAllExpr().toString() + node.getAllExpr().getClass().getSimpleName());

        String type = node.getAllExpr().getClass().getSimpleName();
        String ourType = null;

        if (type.equals("AConstantAllExpr")){
            ourType = "Integer";
        }
        else if (type.equals("ALetterAllExpr"))
            ourType = "Char";
        else if (type.equals("AFuncAllExpr")){
            List<String> nameList = new ArrayList<String>(Arrays.asList( node.getAllExpr().toString().split(" ")));
            ourType = table.getFuncType(nameList.get(0));
            try{
                if(ourType == null){
                    throw new MyException("FUNC NOT FOUND");
                }
            }catch (MyException e){
                throw new IllegalStateException("FUNC NOT FOUND");
            }

            /*if (ourType.contains("int"))
                ourType = "Integer";
            else if(ourType.contains("char"))
                ourType = "Char";
                */
           // im.genQuad(im.getOpCode().getRet(),null,null,null);
            return;


        }
        else if (type.equals("ALValueAllExpr")){
            //array
            List<String> nameList = new ArrayList<String>(Arrays.asList( node.getAllExpr().toString().split(" ")));
            ourType = table.FindVariableType(nameList.get(0));
            System.err.println("ARRAYY " + nameList);

            try{
                if(ourType == null){
                    throw new MyException("FUNC NOT FOUND");
                }
            }catch (MyException e){
                throw new IllegalStateException("FUNC NOT FOUND");
            }

            if (ourType.contains("int"))
                ourType = "Integer";
            else if(ourType.contains("char"))
                ourType = "Char";


            List<String> tempList = new ArrayList<String>(Arrays.asList( node.getAllExpr().toString().split(" ")));
            tempList.remove(0);
            tempList.remove("[");
            tempList.remove("]");
            Object w = im.newTemp(ourType);
            String name = "$" + im.getCount();
            String prevName = name;
            InterReg reg = new InterReg(w,im.getCount(),im.getOpCode().getArray(),w.getClass().getSimpleName(),name);
            im.insertReg(reg);
            im.genQuad(im.getOpCode().getArray(),nameList.get(0),tempList.get(0), name);

            w = im.newTemp(ourType);
            String name2 = "$" + im.getCount();

            reg = new InterReg(w,im.getCount(),im.getOpCode().getAssignment(),w.getClass().getSimpleName(),name2);
            im.insertReg(reg);

            im.genQuad(im.getOpCode().getAssignment(),prevName,null, name2);

            im.genQuad(im.getOpCode().getRet(),null,null,null);
            return;
        }

        //System.err.println("Type: " + ourType);

        Object w = im.newTemp(ourType);
        String name = "$" + im.getCount();

        InterReg reg = new InterReg(w,im.getCount(),im.getOpCode().getAssignment(),w.getClass().getSimpleName(),name);
        im.insertReg(reg);

        im.genQuad(im.getOpCode().getAssignment(),node.getAllExpr(),null, name);

        im.genQuad(im.getOpCode().getRet(),null,null,null);

    }

    @Override
    public void inAReturnWith2Stmt(AReturnWith2Stmt node) {

        //System.err.println("RETURN WIITH PAR2: " + node.getAllExpr().toString());
      //  System.err.println("RETURN EXPR2: " + node.getAllExpr().toString() + node.getAllExpr().getClass().getSimpleName());

        String type = node.getAllExpr().getClass().getSimpleName();
        String ourType = null;

        if (type.equals("AConstantAllExpr")){
            ourType = "Integer";
        }
        else if (type.equals("ALetterAllExpr"))
            ourType = "Char";
        else if (type.equals("AFuncAllExpr")){
            List<String> nameList = new ArrayList<String>(Arrays.asList( node.getAllExpr().toString().split(" ")));
            ourType = table.getFuncType(nameList.get(0));
            try{
                if(ourType == null){
                    throw new MyException("FUNC NOT FOUND");
                }
            }catch (MyException e){
                throw new IllegalStateException("FUNC NOT FOUND");
            }

            /*if (ourType.contains("int"))
                ourType = "Integer";
            else if(ourType.contains("char"))
                ourType = "Char";*/
           // im.genQuad(im.getOpCode().getRet(),null,null,null);
            return;
        }
        else if (type.equals("ALValueAllExpr")){
            //array
            List<String> nameList = new ArrayList<String>(Arrays.asList( node.getAllExpr().toString().split(" ")));
            ourType = table.FindVariableType(nameList.get(0));
            System.err.println("ARRAYY " + nameList);

            try{
                if(ourType == null){
                    throw new MyException("FUNC NOT FOUND");
                }
            }catch (MyException e){
                throw new IllegalStateException("FUNC NOT FOUND");
            }

            if (ourType.contains("int"))
                ourType = "Integer";
            else if(ourType.contains("char"))
                ourType = "Char";


            List<String> tempList = new ArrayList<String>(Arrays.asList( node.getAllExpr().toString().split(" ")));
            tempList.remove(0);
            tempList.remove("[");
            tempList.remove("]");
            Object w = im.newTemp(ourType);
            String name = "$" + im.getCount();
            String prevName = name;
            InterReg reg = new InterReg(w,im.getCount(),im.getOpCode().getArray(),w.getClass().getSimpleName(),name);
            im.insertReg(reg);
            im.genQuad(im.getOpCode().getArray(),nameList.get(0),tempList.get(0), name);

            w = im.newTemp(ourType);
            String name2 = "$" + im.getCount();

            reg = new InterReg(w,im.getCount(),im.getOpCode().getAssignment(),w.getClass().getSimpleName(),name2);
            im.insertReg(reg);

            im.genQuad(im.getOpCode().getAssignment(),prevName,null, name2);

            im.genQuad(im.getOpCode().getRet(),null,null,null);
            return;
        }

        //System.err.println("Type: " + ourType);

        Object w = im.newTemp(ourType);
        String name = "$" + im.getCount();

        InterReg reg = new InterReg(w,im.getCount(),im.getOpCode().getAssignment(),w.getClass().getSimpleName(),name);
        im.insertReg(reg);

        im.genQuad(im.getOpCode().getAssignment(),node.getAllExpr(),null, name);

        im.genQuad(im.getOpCode().getRet(),null,null,null);

    }

    @Override
    public void inAReturnExprStmt(AReturnExprStmt node) {

        //System.err.println("RETURN EXPR1: " + node.getAllExpr().toString());
        //System.err.println("RETURN EXPR2: " + node.getAllExpr().toString() + node.getAllExpr().getClass().getSimpleName());

        String type = node.getAllExpr().getClass().getSimpleName();
        String ourType = null;

        if (type.equals("AConstantAllExpr")){
            ourType = "Integer";
        }
        else if (type.equals("ALetterAllExpr"))
            ourType = "Char";
        else if (type.equals("AFuncAllExpr")){
            List<String> nameList = new ArrayList<String>(Arrays.asList( node.getAllExpr().toString().split(" ")));
            ourType = table.getFuncType(nameList.get(0));
            try{
                if(ourType == null){
                    throw new MyException("FUNC NOT FOUND");
                }
            }catch (MyException e){
                throw new IllegalStateException("FUNC NOT FOUND");
            }

            /*if (ourType.contains("int"))
                ourType = "Integer";
            else if(ourType.contains("char"))
                ourType = "Char";*/
            //im.genQuad(im.getOpCode().getRet(),null,null,null);
            return;
        }
        else if (type.equals("ALValueAllExpr")){
            //array
            List<String> nameList = new ArrayList<String>(Arrays.asList( node.getAllExpr().toString().split(" ")));
            ourType = table.FindVariableType(nameList.get(0));
            System.err.println("ARRAYY " + nameList);

            try{
                if(ourType == null){
                    throw new MyException("FUNC NOT FOUND");
                }
            }catch (MyException e){
                throw new IllegalStateException("FUNC NOT FOUND");
            }

            if (ourType.contains("int"))
                ourType = "Integer";
            else if(ourType.contains("char"))
                ourType = "Char";


            List<String> tempList = new ArrayList<String>(Arrays.asList( node.getAllExpr().toString().split(" ")));
            tempList.remove(0);
            tempList.remove("[");
            tempList.remove("]");
            Object w = im.newTemp(ourType);
            String name = "$" + im.getCount();
            String prevName = name;
            InterReg reg = new InterReg(w,im.getCount(),im.getOpCode().getArray(),w.getClass().getSimpleName(),name);
            im.insertReg(reg);
            im.genQuad(im.getOpCode().getArray(),nameList.get(0),tempList.get(0), name);

            w = im.newTemp(ourType);
            String name2 = "$" + im.getCount();

            reg = new InterReg(w,im.getCount(),im.getOpCode().getAssignment(),w.getClass().getSimpleName(),name2);
            im.insertReg(reg);

            im.genQuad(im.getOpCode().getAssignment(),prevName,null, name2);

            im.genQuad(im.getOpCode().getRet(),null,null,null);
            return;
        }

        //System.err.println("Type: " + ourType);

        Object w = im.newTemp(ourType);
        String name = "$" + im.getCount();

        InterReg reg = new InterReg(w,im.getCount(),im.getOpCode().getAssignment(),w.getClass().getSimpleName(),name);
        im.insertReg(reg);

        im.genQuad(im.getOpCode().getAssignment(),node.getAllExpr(),null, name);

        im.genQuad(im.getOpCode().getRet(),null,null,null);


    }

    @Override
    public void inAReturnExpr2Stmt(AReturnExpr2Stmt node) {

        //System.err.println("RETURN EXPR2: " + node.getAllExpr().toString() + node.getAllExpr().getClass().getSimpleName());

        String type = node.getAllExpr().getClass().getSimpleName();
        String ourType = null;

        if (type.equals("AConstantAllExpr")){
            ourType = "Integer";
        }
        else if (type.equals("ALetterAllExpr"))
            ourType = "Char";
        else if (type.equals("AFuncAllExpr")){
            List<String> nameList = new ArrayList<String>(Arrays.asList( node.getAllExpr().toString().split(" ")));
            //System.err.println(nameList);
            ourType = table.getFuncType(nameList.get(0));
            try{
                if(ourType == null){
                    throw new MyException("FUNC NOT FOUND");
                }
            }catch (MyException e){
                throw new IllegalStateException("FUNC NOT FOUND");
            }

            /*if (ourType.contains("int"))
                ourType = "Integer";
            else if(ourType.contains("char"))
                ourType = "Char";*/
           // im.genQuad(im.getOpCode().getRet(),null,null,null);
            return;
        }
        else if (type.equals("ALValueAllExpr")){
            //array
            List<String> nameList = new ArrayList<String>(Arrays.asList( node.getAllExpr().toString().split(" ")));
            ourType = table.FindVariableType(nameList.get(0));
            System.err.println("ARRAYY " + nameList);

            try{
                if(ourType == null){
                    throw new MyException("FUNC NOT FOUND");
                }
            }catch (MyException e){
                throw new IllegalStateException("FUNC NOT FOUND");
            }

            if (ourType.contains("int"))
                ourType = "Integer";
            else if(ourType.contains("char"))
                ourType = "Char";


            List<String> tempList = new ArrayList<String>(Arrays.asList( node.getAllExpr().toString().split(" ")));
            tempList.remove(0);
            tempList.remove("[");
            tempList.remove("]");
            Object w = im.newTemp(ourType);
            String name = "$" + im.getCount();
            String prevName = name;
            InterReg reg = new InterReg(w,im.getCount(),im.getOpCode().getArray(),w.getClass().getSimpleName(),name);
            im.insertReg(reg);
            im.genQuad(im.getOpCode().getArray(),nameList.get(0),tempList.get(0), name);

            w = im.newTemp(ourType);
            String name2 = "$" + im.getCount();

            reg = new InterReg(w,im.getCount(),im.getOpCode().getAssignment(),w.getClass().getSimpleName(),name2);
            im.insertReg(reg);

            im.genQuad(im.getOpCode().getAssignment(),prevName,null, name2);

            im.genQuad(im.getOpCode().getRet(),null,null,null);
            return;
        }

        //System.err.println("Type: " + ourType);

        Object w = im.newTemp(ourType);
        String name = "$" + im.getCount();

        InterReg reg = new InterReg(w,im.getCount(),im.getOpCode().getAssignment(),w.getClass().getSimpleName(),name);
        im.insertReg(reg);

        im.genQuad(im.getOpCode().getAssignment(),node.getAllExpr(),null, name);

        im.genQuad(im.getOpCode().getRet(),null,null,null);


    }

    @Override
    public void outAReturnExpr2Stmt(AReturnExpr2Stmt node) {
        String type = node.getAllExpr().getClass().getSimpleName();
        if (type.equals("AFuncAllExpr")) {
            List<String> nameList = new ArrayList<String>(Arrays.asList(node.getAllExpr().toString().split(" ")));
            im.insertRet();
            im.genQuad(im.getOpCode().getRet(), null, null, null);


        }

    }

    @Override
    public void outAReturnExprStmt(AReturnExprStmt node) {
        String type = node.getAllExpr().getClass().getSimpleName();
        if (type.equals("AFuncAllExpr")) {
            List<String> nameList = new ArrayList<String>(Arrays.asList(node.getAllExpr().toString().split(" ")));
            im.insertRet();
            im.genQuad(im.getOpCode().getRet(), null, null, null);


        }
    }

    @Override
    public void outAReturnWithStmt(AReturnWithStmt node) {
        String type = node.getAllExpr().getClass().getSimpleName();
        if (type.equals("AFuncAllExpr")) {
            List<String> nameList = new ArrayList<String>(Arrays.asList(node.getAllExpr().toString().split(" ")));
            im.insertRet();
            im.genQuad(im.getOpCode().getRet(), null, null, null);


        }
    }

    @Override
    public void outAReturnWith2Stmt(AReturnWith2Stmt node) {
        String type = node.getAllExpr().getClass().getSimpleName();
        if (type.equals("AFuncAllExpr")) {
            List<String> nameList = new ArrayList<String>(Arrays.asList(node.getAllExpr().toString().split(" ")));
            im.insertRet();
            im.genQuad(im.getOpCode().getRet(), null, null, null);


        }
    }




}