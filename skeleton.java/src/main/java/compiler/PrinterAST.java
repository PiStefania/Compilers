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

    boolean globalFlagNot = false;


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

        System.out.println("\nSuccessful compilation!");

        im.print();

        table.printFuncStack();

        im.printPlace();

    }



    @Override
    public void inAAllFuncDef(AAllFuncDef node)
    {

        // System.out.println("(Function Definition:");

        List<String> myList = new ArrayList<String>(Arrays.asList(node.getL().toString().split(" ")));


        List<String> listParam = new ArrayList<String>(myList);

        listParam.remove(0);
        listParam.remove(listParam.size()-1);

        List<String> refList = new ArrayList<String>(listParam);

        while(listParam.contains("ref"))
            listParam.remove("ref");




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


        List<String> refVars = new ArrayList<String>();
        int y=0;
        while(y<refList.size()){
            //System.out.println(y);
            if(refList.get(y).equals("ref")){
                y++;
                while(!refList.get(y).equals("char") && !refList.get(y).equals("int") && !refList.get(y).contains("int[") && !refList.get(y).contains("char["))
                {
                    refVars.add(refList.get(y));
                    y++;
                    if(y==refList.size()) break;
                }
            }
            else{
                y++;
            }
        }

        String function = myList.get(0).trim();
        table.insertRefList(function,refVars);

        System.err.println("REFFFFFFFFFFFFFF" + table.getRefPar().entrySet());


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

        System.out.println("FULL DICTIONARY: " + parType.entrySet() + myList.get(0).trim());


        ScopeObject obj =   new ScopeObject(myList.get(0).trim(),myList.get(myList.size()-1).trim(),"func", false) ;
        table.enter(obj);

        //insert to funcStack

        FuncScope funcObj = new FuncScope(myList.get(0).trim(),parType,myList.get(myList.size()-1).trim(),firstList.size());

        table.insertFuncStack(funcObj);



        //System.err.println("hash size "+ parType.size());
        List<String> keys = new ArrayList<String>(parType.keySet());

        for(int n= 0; n<keys.size();n++){
            String key = keys.get(n);
            //System.err.println("KEY: " + key);
            for(int v=0;v<parType.get(key).size();v++){
                String var = parType.get(key).get(v).toString();
                //System.err.println("VAR: " + var);
                ScopeObject vars =   new ScopeObject(var,key,"par",false) ;
                table.insert(vars);
            }

        }



        for (int a=0; a<firstList.size(); a++){     //gia kathe parametro
            if (refVars.contains(firstList.get(a))){    //an anhksei sta refParameters
                int position = table.FindVarPosition(firstList.get(a));      //set Ref
                //System.out.println("PARAMETER####3 "+ table.getMystack().get(position).getName());
                table.getMystack().get(position).setRef(true);
            }

        }
        //System.out.println("AAAAAAAAAAAAAAAADDDDDDDDDDD");

        table.print();
        System.err.println("first list "+ listParamHelp);

        List<String> sortedPars = new ArrayList<String>();








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


    /*@Override
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

       table.print();


    }*/



    /*@Override
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




    }*/



    @Override
    public void inAVarDefFuncDef(AVarDefFuncDef node)
    {
        //System.err.println("VARDEF "+ node.getL().toString() + " right "+ node.getR().toString());
        //addIndentationLevel();
        // printIndentation();

        String type = node.getR().toString();
        List<String> myList = new ArrayList<String>(Arrays.asList(node.getL().toString().split(" ")));
        //System.err.println("myList "+ myList);
        for(int i=0;i<myList.size();i++){
            ScopeObject obj =   new ScopeObject(myList.get(i).trim(),type.trim(),"var",false) ;
            System.out.println("List "+ myList.get(i));
            table.insert(obj);
            Set list = table.getMap().entrySet();
              //System.out.println("MAPPINGS" + list+ obj.getName());
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

        ScopeObject obj = new ScopeObject(myList.get(0).toString().trim(),myList.get(myList.size()-1).toString().trim(),"decl", false);
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
            //System.err.println("finalS "+finalS);
            //System.err.println("numb "+numb);

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

            System.out.println("Arrat%%% " +finalS + " "+ numb);

           // im.genQuad(im.getOpCode().getArray(),finalS,numb, "$" + im.getCount());
            name=finalS;
        }

        String type;
        String str=node.getR().toString().trim();
        //str = str.replace("[","");
        //str = str.replace("]","");


        //checking for value

        String reg=name;
        if (initialName.contains("[")){
            reg="$" + (im.getCount()-1);
        }

        if (node.getR().getClass().getSimpleName().equals("AFuncAllExpr")) {

            //.println("FUNCTION CALL");
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


        else if (str.contains("\"")){

            //not sure about this one
            int length=str.length()-1;
            type = "char[" +length+"]";

            im.genQuad(im.getOpCode().getAssignment(),str,null,reg);

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
            //System.out.println("AAddSubAllExpr");
            type= "int";
        }
        else if (node.getR().getClass().getSimpleName().equals("ARestSignsAllExpr")){
            //System.out.println("ARestSignsAllExpr");
            type= "int";
        }
        else if (node.getR().getClass().getSimpleName().equals("AWithPlminAllExpr")){


            //  System.out.println("AWithPlminAllExpr");
            type= "int";
            im.genQuad(im.getOpCode().getAssignment(),str,null,reg);
        }
        else if (node.getR().toString().contains("[")){

            //System.err.println("right "+node.getR().toString() + " "+ str);

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
            //System.err.println("finalS "+finalS);
            //System.err.println("numb "+numb);

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



        ScopeObject obj = new ScopeObject(name,type,"var", false);
        //System.err.println("EEEEEEEEERRRRRRRR " +name + " "+ type );
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

        //System.err.println("LEFTRRTTRRRRRRRR: " + node.getL().toString() + "RIGHTSSSSSSSSSS: "+ node.getL().toString());

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
            im.insertPlaceHelper((finalS+numb),"$" + im.getCount());
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

            String numb="";
            int i=0;
            String finalS="";

            while(i<tempString.length()){
                char c = tempString.charAt(i);
                if(c == '['){
                    while(i<tempString.length()){
                        i++;
                        c = tempString.charAt(i);
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



            //System.err.println("tempstring "+ tempString);
            //System.err.println("finalS "+ finalS);
            //System.err.println("numb "+ numb);


            if (numb.contains("+")|| numb.contains("-") || numb.contains("*") || numb.contains("/") || numb.contains("mod")){
                Object w = im.newTemp("Integer");
                String name2 = "$" + im.getCount();

                InterReg reg2 = new InterReg(w,im.getCount(),im.getOpCode().getAssignment(),w.getClass().getSimpleName(),name2);
                im.insertReg(reg2);
                String myReg = im.Place(numb);
                try{
                    if(myReg==null){
                        throw new MyException("FOUND VARIABLE NOT DECLARED");
                    }
                }
                catch (MyException e){
                    throw new IllegalStateException("FOUND VARIABLE NOT DECLARED");
                }

                im.insertPlaceHelper(tempString,"$" + im.getCount());


                im.genQuad(im.getOpCode().getArray(),tempList.get(0), myReg,name2);
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

        //System.err.println("(ADD- SUB EXPRESSION left child: " + node.getL().toString() + "right child"+node.getR().toString()+"!");
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
        //System.out.println("(ADD- SUB EXPRESSION left child: " + node.getL().toString() + "right child "+node.getR().toString()+"!");
        List<String> myList = new ArrayList(Arrays.asList(node.getL().toString().trim().split("\\s+")));
        //  System.err.println("addsub myList "+myList);
        String op = myList.get(myList.size()-1);
        // System.err.println("op "+op);
        String rightNumb = node.getR().toString().trim();
        List<String> rightNumlist = new ArrayList(Arrays.asList(node.getR().toString().trim().split("\\s+")));

        String left = "";

        for(int i=0;i<myList.size()-1;i++){
            left += myList.get(i);
        }
        left = left.trim();

        operator myOp = new operator("op",op);
        Object w = im.newTemp("Integer");
        String name2 = "$" + im.getCount();

        String wholeExpr = node.getL().toString() + node.getR().toString().trim();

        im.insertPlaceHelper(wholeExpr,name2);

        InterReg reg = new InterReg(w,im.getCount(),im.getOpCode().getAssignment(),w.getClass().getSimpleName(),name2);
        im.insertReg(reg);

        String regRight = im.Place(node.getR().toString().trim());

        String regLeft = im.Place(left);



        if (myList.size()==2){
            if(rightNumlist.size()==1){
                im.genQuad(myOp,myList.get(0),rightNumb,name2);
            }
            else {
                try{
                    if(regRight == null){
                        throw new MyException("EXPRESSION NOT FOUND");
                    }
                }catch (MyException e){
                    throw new IllegalStateException("EXPRESSION NOT FOUND");
                }

                im.genQuad(myOp, myList.get(0),regRight , name2);
            }
        }
        else {
            try{
                if(regLeft == null){
                    throw new MyException("EXPRESSION NOT FOUND");
                }
            }catch (MyException e){
                throw new IllegalStateException("EXPRESSION NOT FOUND");
            }
            if (rightNumlist.size() == 1) {
                im.genQuad(myOp, regLeft, rightNumb, name2);
            } else {
                try{
                    if(regRight == null){
                        throw new MyException("EXPRESSION NOT FOUND");
                    }
                }catch (MyException e){
                    throw new IllegalStateException("EXPRESSION NOT FOUND");
                }
                im.genQuad(myOp, regLeft, regRight, name2);
            }
        }
    }
    @Override
    public void outARestSignsAllExpr(ARestSignsAllExpr node) {
        //System.out.println("(Rest_signs expr: " + node.getL().toString() + " right child " + node.getR().toString());
        List<String> myList = new ArrayList(Arrays.asList(node.getL().toString().trim().split("\\s+")));
        // System.err.println("Rest myList " + myList);
        String op = myList.get(myList.size() - 1);
        //System.err.println("op "+op);
        String rightNumb = node.getR().toString().trim();
        List<String> rightNumlist = new ArrayList(Arrays.asList(node.getR().toString().trim().split("\\s+")));

        // System.err.println("rightNumb " + rightNumb);
        operator myOp = new operator("op", op);
        Object w = im.newTemp("Integer");
        String name2 = "$" + im.getCount();

        String left = "";

        for(int i=0;i<myList.size()-1;i++){
            left += myList.get(i);
        }
        left = left.trim();

        String wholeExpr = node.getL().toString() + node.getR().toString().trim();

        im.insertPlaceHelper(wholeExpr, name2);

        InterReg reg = new InterReg(w, im.getCount(), im.getOpCode().getAssignment(), w.getClass().getSimpleName(), name2);
        im.insertReg(reg);

        String regRight = im.Place(node.getR().toString().trim());


        String regLeft = im.Place(left);


        if (myList.size()==2){

            if(rightNumlist.size()==1){
                im.genQuad(myOp,myList.get(0),rightNumb,name2);
            }
            else {
                try{
                    if(regRight == null){
                        throw new MyException("EXPRESSION NOT FOUND");
                    }
                }catch (MyException e){
                    throw new IllegalStateException("EXPRESSION NOT FOUND");
                }
                im.genQuad(myOp, myList.get(0), regRight, name2);
            }
        }
        else {
            try{
                if(regLeft == null){
                    throw new MyException("EXPRESSION NOT FOUND");
                }
            }catch (MyException e){
                throw new IllegalStateException("EXPRESSION NOT FOUND");
            }

            if (rightNumlist.size() == 1) {
                im.genQuad(myOp, regLeft, rightNumb, name2);
            } else {
                try{
                    if(regRight == null){
                        throw new MyException("EXPRESSION NOT FOUND");
                    }
                }catch (MyException e){
                    throw new IllegalStateException("EXPRESSION NOT FOUND");
                }
                im.genQuad(myOp, regLeft, regRight, name2);
            }
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

         //System.err.println("(EXPRESSION CONDITION: left child " + node.getL().toString() + " right child "+node.getR().toString() + "!");



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
    public void outAExprsignsCond(AExprsignsCond node)
    {
        System.out.println("(EXPRESSION CONDITION: left child " + node.getL().toString() + " right child "+node.getR().toString() + "!");

        List<String> myList = new ArrayList(Arrays.asList(node.getL().toString().trim().split("\\s+")));
        //System.err.println("type "+node.getL().toString() + " " +node.getL().getClass().getSimpleName());
        String op = myList.get(myList.size()-1);
        //  System.err.println("op "+op);
        String rightNumb = node.getR().toString().trim();
        List<String> rightNumlist = new ArrayList(Arrays.asList(node.getR().toString().trim().split("\\s+")));

        String left = "";

        for(int i=0;i<myList.size()-1;i++){
            left += myList.get(i);
        }
        left = left.trim();
          System.out.println("Left "+left);

        operator myOp = new operator("op",op);
        Object w = im.newTemp("Integer");
        String name2 = "$" + im.getCount();

        String wholeExpr = node.getL().toString() + node.getR().toString().trim();
        // System.err.println("wholeExpr "+wholeExpr);

        int myCounter=1;

        im.insertPlaceHelper(wholeExpr, "$"+ "0" + im.getCount());

        InterReg reg = new InterReg(w,im.getCount(),im.getOpCode().getAssignment(),w.getClass().getSimpleName(),name2);
        im.insertReg(reg);


       /* try{
            if(regRight == null){
                throw new MyException("EXPRESSION NOT FOUND");
            }
        }catch (MyException e){
            throw new IllegalStateException("EXPRESSION NOT FOUND");
        }*/

       if (left.contains("[")){             //array

           int i=0;
           String finalS="";
           String numb="";
           while(i<left.length()){
               char c = left.charAt(i);
               if(c == '['){
                   while(i<left.length()){
                       i++;
                       c = left.charAt(i);
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

           if (numb.contains("+") ||numb.contains("-") || numb.contains("*") || numb.contains("/") || numb.contains("mod")){
               numb = im.Place(numb);
               try{
                   if(numb==null){
                       throw new MyException("ERROR! WRONG EXPRESSION");
                   }
               }
               catch (MyException e){
                   throw new IllegalStateException("ERROR! WRONG EXPRESSION");
               }

           }

           //System.err.println("finalS "+finalS);
           //System.err.println("numb "+numb);


           im.insertPlaceHelper(left,"$" + im.getCount());

           im.genQuad(im.getOpCode().getArray(),finalS,numb,"$" + im.getCount());

        }

        String right = "";

        for(int i=0;i<rightNumlist.size();i++){
            right += rightNumlist.get(i);
        }
        right = right.trim();
        System.out.println("Right "+right);


        if (right.contains("[")) {             //array

            int i = 0;
            String finalS = "";
            String numb = "";
            while (i < right.length()) {
                char c = right.charAt(i);
                if (c == '[') {
                    while (i < right.length()) {
                        i++;
                        c = right.charAt(i);
                        if (c == ']')
                            break;

                        numb += c;

                    }
                    break;
                }
                finalS += c;
                i++;
            }
            finalS = finalS.trim();
            numb = numb.trim();
            if (numb.contains("+") ||numb.contains("-") || numb.contains("*") || numb.contains("/") || numb.contains("mod")){
                numb = im.Place(numb);
                try{
                    if(numb==null){
                        throw new MyException("ERROR! WRONG EXPRESSION");
                    }
                }
                catch (MyException e){
                    throw new IllegalStateException("ERROR! WRONG EXPRESSION");
                }

            }

            //System.err.println("finalSR " + finalS);
            //System.err.println("numbR " + numb);





            im.insertPlaceHelper(right, "$" + im.getCount());
            im.genQuad(im.getOpCode().getArray(), finalS, numb, "$" + im.getCount());

            im.printPlace();
        }

        String regRight = im.Place(node.getR().toString().trim());



        String regLeft = im.Place(left);


       //System.err.println("MYLIST "+myList);


        if (myList.size()==2){
            if(rightNumlist.size()==1){
                im.genQuad(myOp,myList.get(0),rightNumb,im.getCount()+1);
            }
            else {
                try{
                    if(regRight == null){

                        throw new MyException("EXPRESSION NOT FOUND");
                    }
                }catch (MyException e){
                    throw new IllegalStateException("EXPRESSION NOT FOUND");
                }
                im.genQuad(myOp, myList.get(0), regRight, im.getCount()+1);
            }
        }
        else {
            //System.out.println("node.getL().toString() "+node.getL().toString());
            //String sub = node.getL().toString().trim().substring(0, node.getL().toString().trim().length()-1);
            //System.out.println("sub "+sub);




            try{
                if(regLeft == null){
                    System.out.println("ERRRORRR: "+left);
                    throw new MyException("EXPRESSION NOT FOUND");
                }
            }catch (MyException e){
                throw new IllegalStateException("EXPRESSION NOT FOUND");
            }
            if (rightNumlist.size() == 1) {
                im.genQuad(myOp, regLeft, rightNumb, im.getCount()+1);
            } else {

                try{
                    if(regRight == null){
                        throw new MyException("EXPRESSION NOT FOUND");
                    }
                }catch (MyException e){
                    throw new IllegalStateException("EXPRESSION NOT FOUND");
                }
                im.genQuad(myOp, regLeft, regRight, im.getCount()+1);
            }
        }
        String regWhole = im.Place(wholeExpr);
        try{
            if(wholeExpr == null){
                throw new MyException("EXPRESSION NOT FOUND");
            }
        }catch (MyException e){
            throw new IllegalStateException("EXPRESSION NOT FOUND");
        }
        if (!globalFlagNot) {
            im.genQuad(im.getOpCode().getIfb(), regWhole, null, im.getCount() + 2);
            im.genQuad(im.getOpCode().getJump(), null, null, "endOfIf" + table.getIfWhileScope());
        }
        else
            im.genQuad(im.getOpCode().getIfb(), regWhole, null, im.getCount() + 1);
    }


    @Override
    public void inANoElseStmt(ANoElseStmt node){
        System.out.println("INno else stmt "+ node.getL().toString() + " right "+ node.getR().toString() );
        table.setIfWhileScope(table.getIfWhileScope()+1);
    }

    @Override
    public void outANoElseStmt(ANoElseStmt node){
        System.out.println("no else stmt "+ node.getL().toString() + " right "+ node.getR().toString() );
        //System.out.println(im.getCount());
        im.backpatch("endOfIf" + table.getIfWhileScope(),im.getCount());
        table.setIfWhileScope(table.getIfWhileScope() -1);
    }

    @Override
    public void inAIfstmt(AIfstmt node){
        //System.out.println("if  stmt "+ node.toString() );
        //System.out.println(im.getCount());
        im.backpatch("then",im.getCount());
    }

    @Override
    public void inAMyStmtelse(AMyStmtelse node){
        System.out.println("inAMyStmtelse111111111 "+ node.toString() );
        //im.backpatch("endOfIf",im.getCount());
        im.genQuad(im.getOpCode().getJump(),null,null,"END");




        im.backpatch("endOfIf" + table.getIfWhileScope(),im.getCount());


    }

    @Override
    public void outAMyStmtelse(AMyStmtelse node){
        //System.out.println("ENDDD inAMyStmtelse111111111 "+ node.toString() );
        //im.backpatch("endOfIf",im.getCount());
        //im.genQuad(im.getOpCode().getJump(),null,null,"END");
        //im.backpatch("endOfIf",im.getCount());
        //table.setIfWhileScope(table.getIfWhileScope()+1);
    }

    @Override
    public void inAWithElseStmt(AWithElseStmt node){
        System.out.println("MY IN stmttt with else 2222 "+ node.getL().toString() + " right "+ node.getR().toString() );
        table.setIfWhileScope(table.getIfWhileScope()+1);
    }

    @Override
    public void outAWithElseStmt(AWithElseStmt node){
        System.out.println("ENDD stmttt with else 2222 "+ node.getL().toString() + " right "+ node.getR().toString() );
        im.backpatch("END",im.getCount());
        //im.backpatch("endOfIf"+table.getIfWhileScope(),im.getCount());
        table.setIfWhileScope(table.getIfWhileScope()-1);
    }



    @Override
    public void inAFuncCallWithoutStmt(AFuncCallWithoutStmt node){
        //System.err.println("FUNC CALL WITHOUT PAR: " + node.getVarName().toString().trim() + "!");
        String funcName = node.getVarName().toString().trim();

        try{
            if(!table.checkScopeWithout(funcName)){
                throw new MyException("INCORRECT FUNCTION CALL.");
            }
        }catch (MyException e){
            throw new IllegalStateException("INCORRECT FUNCTION CALL.");
        }

        im.genQuad(im.getOpCode().getCall(),null,null,funcName);
    }

    @Override
    public void inAFuncCallWithStmt(AFuncCallWithStmt node){
        System.out.println("FUNC CALL WITH PAR: " + node.getL().toString() + "!" + node.getR().toString() + "!");

        String funcName = node.getL().toString().trim();

        String nodeRight = node.getR().toString().trim();

        List <String> parameters = new ArrayList<String>(Arrays.asList(nodeRight.replaceAll("\\s+","").split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1)));

        //List<?> params = new ArrayList(Arrays.asList(node.getR().replaceAll("\\s+","").split(",")));
        //System.err.println("pnode "+node.getR() + node.getR().getClass().getSimpleName());

      //  List  parameters = new ArrayList (Arrays.asList(node.getR()));

        //System.err.println("sizeee "+ parameters.size());

        List paramType = new ArrayList();

        for (int i=0; i<parameters.size(); i++){
            System.err.println("AAAA " +parameters.get(i) + " "+ parameters.get(i).getClass().getSimpleName());

            if (parameters.get(i).contains("\"")){
                paramType.add("char[]");
            }
            else if (parameters.get(i).contains("'")){
                paramType.add("char");
            }
            else if (parameters.get(i).contains("[")){
                String left = parameters.get(i);

                if (left.contains("[")){             //array

                    int k=0;
                    String finalS="";
                    String numb="";
                    while(k<left.length()){
                        char c = left.charAt(k);
                        if(c == '['){
                            while(k<left.length()){
                                k++;
                                c = left.charAt(k);
                                if (c == ']')
                                    break;

                                numb+=c;

                            }
                            break;
                        }
                        finalS += c;
                        k++;
                    }
                    finalS=finalS.trim();
                    numb=numb.trim();

                    if (numb.contains("+") ||numb.contains("-") || numb.contains("*") || numb.contains("/") || numb.contains("mod")){
                        numb = im.Place(numb);
                        try{
                            if(numb==null){
                                throw new MyException("ERROR! WRONG EXPRESSION");
                            }
                        }
                        catch (MyException e){
                            throw new IllegalStateException("ERROR! WRONG EXPRESSION");
                        }

                    }

                    //System.err.println("finalS "+finalS);
                    //System.err.println("numb "+numb);


                    im.insertPlaceHelper(left,"$" + im.getCount());

                    im.genQuad(im.getOpCode().getArray(),finalS,numb,"$" + im.getCount());

                }

                paramType.add("int");


            }
            else if (table.isNumeric(parameters.get(i))){
                paramType.add("int");
            }
            else if (parameters.get(i).contains("+") || parameters.get(i).contains("-") || parameters.get(i).contains("*")|| parameters.get(i).contains("/")|| parameters.get(i).contains("mod"))
                paramType.add("int");
            else{
                //variable
                String type = table.FindVariableType(parameters.get(i));
                try{
                    if(type == null){
                        throw new MyException("VARIABLE NOT DECLARED");
                    }
                }catch (MyException e){
                    throw new IllegalStateException("VARIABLE NOT DECLARED");
                }

                paramType.add(type);
            }

        }

        System.err.println("PARAMTYPE "+ paramType);



        System.err.println("parameters func call "+ parameters + " "+ funcName);

        try{
            if(!table.checkScopeWith(funcName,paramType)){

                throw new MyException("INCORRECT FUNCTION CALL.");
            }
        }catch (MyException e){
            throw new IllegalStateException("INCORRECT FUNCTION CALL.");
        }
    }



    @Override
    public void outAFuncCallWithoutStmt(AFuncCallWithoutStmt node){

        String funcName = node.toString().trim();
        im.insertPlaceHelper(funcName,"$" + im.getCount());
        im.genQuad(im.getOpCode().getCall(),null,null,funcName);

    }

    @Override
    public void outAFuncCallWithStmt(AFuncCallWithStmt node){
        //System.err.println("left "+node.getL().toString() + " right "+ node.getR().toString());
        String wholeStr = node.getL().toString() + node.getR().toString().trim();

        String left = node.getL().toString().trim();
        im.insertRef(left,table);

        im.insertPlaceHelper(wholeStr,"$" + im.getCount());
        String funcName = node.getL().toString().trim();
        im.genQuad(im.getOpCode().getCall(),null,null,funcName);
    }





    @Override
    public void outAParametersAllExpr(AParametersAllExpr node){
        //System.err.println("ALL PARAMETERS: left " + node.getL().toString() +" right " + node.getR().toString());
        //System.err.println("ALL PARAMETERS: whole " + node.toString() );

        List <String> parameters = new ArrayList<String>(Arrays.asList(node.toString().trim().replaceAll("\\s+","").split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1)));
        System.err.println("parameters "+ parameters);

        int noOfParam = parameters.size();


        for (int i=0; i<parameters.size();i++) {
            String par = String.valueOf( parameters.get(i));
            //List parList = new ArrayList(Arrays.asList(par.split(" ")));
           // System.err.println("PARLIST " + parList);

            String left = par;

            if (left.contains("[")){             //array

                int k=0;
                String finalS="";
                String numb="";
                while(k<left.length()){
                    char c = left.charAt(k);
                    if(c == '['){
                        while(k<left.length()){
                            k++;
                            c = left.charAt(k);
                            if (c == ']')
                                break;

                            numb+=c;

                        }
                        break;
                    }
                    finalS += c;
                    k++;
                }
                finalS=finalS.trim();
                numb=numb.trim();

                  if (numb.contains("+") ||numb.contains("-") || numb.contains("*") || numb.contains("/") || numb.contains("mod")){
                    numb = im.Place(numb);
                    try{
                        if(numb==null){
                            throw new MyException("ERROR! WRONG EXPRESSION");
                        }
                    }
                    catch (MyException e){
                        throw new IllegalStateException("ERROR! WRONG EXPRESSION");
                    }

                }

                System.err.println("finalS "+finalS);
                System.err.println("numb "+numb);


                im.insertPlaceHelper(left,"$" + im.getCount());

                im.genQuad(im.getOpCode().getArray(),finalS,numb,"$" + im.getCount());

            }




            else if(par.contains("+") || par.contains("-") ||par.contains("*") ||par.contains("/") ||par.contains("mod") ) {
                if (!table.isNumeric(par)) {
                    String reg = im.Place(par);
                    try {
                        if (reg == null) {
                            throw new MyException("EXPRESSION NOT FOUND");
                        }
                    } catch (MyException e) {
                        throw new IllegalStateException("EXPRESSION NOT FOUND");
                    }
                    im.genQuad(im.getOpCode().getPar(), reg, "V", null);
                    return;
                }
            }
             im.genQuad(im.getOpCode().getPar(),par,"V",null);


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

            return;


        }
        else if (type.equals("ALValueAllExpr")){
            //array
            List<String> nameList = new ArrayList<String>(Arrays.asList( node.getAllExpr().toString().split(" ")));
            ourType = table.FindVariableType(nameList.get(0));
            //System.err.println("ARRAYY " + nameList);

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
            //System.err.println("ARRAYY " + nameList);

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
            //System.err.println("ARRAYY " + nameList);

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
            //System.err.println("ARRAYY " + nameList);

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


    @Override
    public void outAOrCond(AOrCond node) {
        //System.out.println("AOrCond Left "+node.getL().toString() + " right " +node.getR().toString());

        String left = node.getL().toString().trim();
        String right = node.getR().toString().trim();
        String wholeString = node.getL().toString() +node.getR().toString().trim();

        im.insertPlaceHelper(wholeString,"$" + im.getCount());
        // System.err.println(wholeString);

        String regLeft = im.Place(left);
        try{
            if(regLeft == null){
                throw new MyException("EXPRESSION NOT FOUND");
            }
        }catch (MyException e){
            throw new IllegalStateException("EXPRESSION NOT FOUND");
        }
        String regRight = im.Place(right);
        try{
            if(regRight == null){
                throw new MyException("EXPRESSION NOT FOUND");
            }
        }catch (MyException e){
            throw new IllegalStateException("EXPRESSION NOT FOUND");
        }

        im.genQuad(im.getOpCode().getIfb(),regLeft, null,"then");
        im.genQuad(im.getOpCode().getIfb(),regRight, null,"then");
        im.genQuad(im.getOpCode().getJump(),null, null,"endOfIf"+table.getIfWhileScope());
    }


    @Override
    public void outAAndCond(AAndCond node) {
        //System.out.println("AAndCond Left "+node.getL().toString() + " right " +node.getR().toString());

        String left = node.getL().toString().trim();
        String right = node.getR().toString().trim();
        String wholeString = node.getL().toString() +node.getR().toString().trim();

        im.insertPlaceHelper(wholeString,"$" + im.getCount());
        // System.err.println(wholeString);

        String regLeft = im.Place(left);
        try{
            if(regLeft == null){
                throw new MyException("EXPRESSION NOT FOUND");
            }
        }catch (MyException e){
            throw new IllegalStateException("EXPRESSION NOT FOUND");
        }
        String regRight = im.Place(right);
        try{
            if(regRight == null){
                throw new MyException("EXPRESSION NOT FOUND");
            }
        }catch (MyException e){
            throw new IllegalStateException("EXPRESSION NOT FOUND");
        }

        im.genQuad(im.getOpCode().getIfb(),regLeft, null,im.getCount()+2);
        im.genQuad(im.getOpCode().getJump(),null, null,"endOfIf"+table.getIfWhileScope());
        im.genQuad(im.getOpCode().getIfb(),regRight, null,im.getCount()+2);
        im.genQuad(im.getOpCode().getJump(),null, null,"endOfIf"+table.getIfWhileScope());

    }

    @Override
    public void inANotCond(ANotCond node) {
        globalFlagNot = true;
    }

    @Override
    public void outANotCond(ANotCond node) {
        System.err.println("ANotCond Left "+node.getL().toString() + " right " +node.getR().toString());

        String left = node.getL().toString().trim();
        String right = node.getR().toString().trim();
        String wholeString = node.getL().toString() +node.getR().toString().trim();

        im.insertPlaceHelper(wholeString,"$" + im.getCount());
        System.err.println(wholeString);

        String regRight = im.Place(right);
        try{
            if(regRight == null){
                throw new MyException("EXPRESSION NOT FOUND");
            }
        }catch (MyException e){
            throw new IllegalStateException("EXPRESSION NOT FOUND");
        }

        List notList = new ArrayList(Arrays.asList(left.split(" ")));
        int noOfNot = notList.size();


        String regWhole = im.Place(wholeString);
        try{
            if(regWhole == null){
                throw new MyException("EXPRESSION NOT FOUND");
            }
        }catch (MyException e){
            throw new IllegalStateException("EXPRESSION NOT FOUND");
        }


        System.err.println("regright "+ regRight);
        System.err.println("wholeString "+ wholeString);
        im.genQuad(im.getOpCode().getIfb(), regWhole, null,im.getCount()+2 );
        im.genQuad(im.getOpCode().getJump(), null, null, "endOfIf"+table.getIfWhileScope());


        globalFlagNot= false;



    }

    @Override
    public void inAWhileStmt(AWhileStmt node) {
        //System.out.println("AWHILE "+node.getL().toString() + " right " +node.getR().toString());
        table.setIfWhileScope(table.getIfWhileScope()+1);
    }

    @Override
    public void inAWhileElseStmt(AWhileElseStmt node) {
        //System.out.println("AWHILE "+node.getL().toString() + " right " +node.getR().toString());
        table.setIfWhileScope(table.getIfWhileScope()+1);
    }




    @Override
    public void outAWhileStmt(AWhileStmt node) {

        // System.out.println("ENDIIIINGAWHILE "+node.getL().toString() + " right " +node.getR().toString());
        String cond = node.getL().toString().trim();

        String reg = im.Place(cond);

        try{
            if(reg == null){
                throw new MyException("EXPRESSION NOT FOUND");
            }
        }catch (MyException e){
            throw new IllegalStateException("EXPRESSION NOT FOUND");
        }

        String regtemp = reg.replace("$","");
        int regNum = Integer.parseInt(regtemp);


        im.backpatch("endOfIf"+table.getIfWhileScope(),im.getCount()+1);
        im.genQuad(im.getOpCode().getJump(),null,null,regNum);  //do
        System.err.println(node.getL().toString() + im.getCount());

        table.setIfWhileScope(table.getIfWhileScope()-1);

        // im.whileJump();



    }

    @Override
    public void inAExpressionElseStmt(AExpressionElseStmt node)
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
            //System.err.println("finalS "+finalS);
            //System.err.println("numb "+numb);

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
        String str=node.getR().toString().trim();
        //str = str.replace("[","");
        //str = str.replace("]","");


        //checking for value

        String reg=name;
        if (initialName.contains("[")){
            reg="$" + (im.getCount()-1);
        }

        if (node.getR().getClass().getSimpleName().equals("AFuncAllExpr")) {

            System.err.println("FUNCTION CALL");
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


        else if (str.contains("\"")){

            //not sure about this one
            int length=str.length()-1;
            type = "char[" +length+"]";

            im.genQuad(im.getOpCode().getAssignment(),str,null,reg);

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
            //System.out.println("AAddSubAllExpr");
            type= "int";
        }
        else if (node.getR().getClass().getSimpleName().equals("ARestSignsAllExpr")){
            //System.out.println("ARestSignsAllExpr");
            type= "int";
        }
        else if (node.getR().getClass().getSimpleName().equals("AWithPlminAllExpr")){


            //  System.out.println("AWithPlminAllExpr");
            type= "int";
            im.genQuad(im.getOpCode().getAssignment(),str,null,reg);
        }
        else if (node.getR().toString().contains("[")){

            //System.err.println("right "+node.getR().toString() + " "+ str);

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
            //System.err.println("finalS "+finalS);
            //System.err.println("numb "+numb);

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



        ScopeObject obj = new ScopeObject(name,type,"var", false);
        System.err.println("EEEEEEEEERRRRRRRR " +name + " "+ type );
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
    public void outAExpressionElseStmt(AExpressionElseStmt node)
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







}