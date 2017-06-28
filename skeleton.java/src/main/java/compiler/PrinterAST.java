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

    JavaBytecode JB;

    boolean globalFlagNot = false;


    @Override
    public void inAProgram(AProgram node){
        //insert library functions

        //fun puti (n: int):nothing;
        Map<String,List> parType = new HashMap<String,List>();
        List<String> par = new ArrayList<String>();
        par.add("n");
        parType.put("int",par);
        List<String> listTypes = new ArrayList<String>();
        listTypes.add("int");
        List<Integer> refVars = new ArrayList<Integer>();
        FuncScope funcObj = new FuncScope("puti",parType,"nothing",par.size(),table.getPosition(),listTypes,refVars);
        table.insertFuncStack(funcObj);


        //fun putc (c: char):nothing;
        parType = new HashMap<String,List>();
        par = new ArrayList<String>();
        par.add("c");
        parType.put("char",par);
        listTypes = new ArrayList<String>();
        listTypes.add("char");
        refVars = new ArrayList<Integer>();
        funcObj = new FuncScope("putc",parType,"nothing",par.size(),table.getPosition(),listTypes,refVars);
        table.insertFuncStack(funcObj);


        //fun puts (ref s: char[]):nothing;
        parType = new HashMap<String,List>();
        par = new ArrayList<String>();
        par.add("s");
        parType.put("char[]",par);
        listTypes = new ArrayList<String>();
        listTypes.add("char[]");
        refVars = new ArrayList<Integer>();
        refVars.add(0);
        funcObj = new FuncScope("puts",parType,"nothing",par.size(),table.getPosition(),listTypes,refVars);
        table.insertFuncStack(funcObj);


        //fun geti ():int;
        parType = new HashMap<String,List>();
        par = new ArrayList<String>();
        listTypes = new ArrayList<String>();
        refVars = new ArrayList<Integer>();
        funcObj = new FuncScope("geti",parType,"int",par.size(),table.getPosition(),listTypes,refVars);
        table.insertFuncStack(funcObj);


        //fun getc ():char;
        parType = new HashMap<String,List>();
        par = new ArrayList<String>();
        listTypes = new ArrayList<String>();
        refVars = new ArrayList<Integer>();
        funcObj = new FuncScope("getc",parType,"char",par.size(),table.getPosition(),listTypes,refVars);
        table.insertFuncStack(funcObj);


        //fun gets (n:int ; ref s:char[]):nothing;
        parType = new HashMap<String,List>();
        par = new ArrayList<String>();
        par.add("n");
        parType.put("int",par);
        par.remove("n");
        par.add("s");
        parType.put("char[]",par);
        listTypes = new ArrayList<String>();
        listTypes.add("int");
        listTypes.add("char[]");
        refVars = new ArrayList<Integer>();
        refVars.add(1);
        funcObj = new FuncScope("gets",parType,"nothing",par.size(),table.getPosition(),listTypes,refVars);
        table.insertFuncStack(funcObj);


        //fun abs (n : int):int;
        parType = new HashMap<String,List>();
        par = new ArrayList<String>();
        par.add("n");
        parType.put("int",par);
        listTypes = new ArrayList<String>();
        listTypes.add("int");
        refVars = new ArrayList<Integer>();
        funcObj = new FuncScope("abs",parType,"int",par.size(),table.getPosition(),listTypes,refVars);
        table.insertFuncStack(funcObj);


        //fun ord (c: char):int;
        parType = new HashMap<String,List>();
        par = new ArrayList<String>();
        par.add("c");
        parType.put("char",par);
        listTypes = new ArrayList<String>();
        listTypes.add("char");
        refVars = new ArrayList<Integer>();
        funcObj = new FuncScope("ord",parType,"int",par.size(),table.getPosition(),listTypes,refVars);
        table.insertFuncStack(funcObj);


        //fun chr (n : int):char;
        parType = new HashMap<String,List>();
        par = new ArrayList<String>();
        par.add("n");
        parType.put("int",par);
        listTypes = new ArrayList<String>();
        listTypes.add("int");
        refVars = new ArrayList<Integer>();
        funcObj = new FuncScope("chr",parType,"char",par.size(),table.getPosition(),listTypes,refVars);
        table.insertFuncStack(funcObj);


        //fun strlen (ref s:char[]):int;
        parType = new HashMap<String,List>();
        par = new ArrayList<String>();
        par.add("s");
        parType.put("char[]",par);
        listTypes = new ArrayList<String>();
        listTypes.add("char[]");
        refVars = new ArrayList<Integer>();
        refVars.add(0);
        funcObj = new FuncScope("strlen",parType,"int",par.size(),table.getPosition(),listTypes,refVars);
        table.insertFuncStack(funcObj);


        //fun strcmp (ref s1, s2 :char[]):int;
        parType = new HashMap<String,List>();
        par = new ArrayList<String>();
        par.add("s1");
        par.add("s2");
        parType.put("char[]",par);
        listTypes = new ArrayList<String>();
        listTypes.add("char[]");
        listTypes.add("char[]");
        refVars = new ArrayList<Integer>();
        refVars.add(0);
        refVars.add(1);
        funcObj = new FuncScope("strcmp",parType,"int",par.size(),table.getPosition(),listTypes,refVars);
        table.insertFuncStack(funcObj);


        //fun strcpy (ref trg ,src :char[]):nothing;
        parType = new HashMap<String,List>();
        par = new ArrayList<String>();
        par.add("trg");
        par.add("src");
        parType.put("char[]",par);
        listTypes = new ArrayList<String>();
        listTypes.add("char[]");
        listTypes.add("char[]");
        refVars = new ArrayList<Integer>();
        refVars.add(0);
        refVars.add(1);
        funcObj = new FuncScope("strcpy",parType,"nothing",par.size(),table.getPosition(),listTypes,refVars);
        table.insertFuncStack(funcObj);


        //fun strcat (ref trg ,src :char[]):nothing;
        parType = new HashMap<String,List>();
        par = new ArrayList<String>();
        par.add("trg");
        par.add("src");
        parType.put("char[]",par);
        listTypes = new ArrayList<String>();
        listTypes.add("char[]");
        listTypes.add("char[]");
        refVars = new ArrayList<Integer>();
        refVars.add(0);
        refVars.add(1);
        funcObj = new FuncScope("strcat",parType,"nothing",par.size(),table.getPosition(),listTypes,refVars);
        table.insertFuncStack(funcObj);

    }

    @Override
    public void outAProgram(AProgram node){




        System.out.println("\nSuccessful compilation!");

        im.print();

        im.printPlace();

        JB= new JavaBytecode(im.getQuadList(), table.getFuncStack(),im.getHelpList(),table.getAllVars());

        JB.produceJavaBytecode();

        //exit function stack
        table.deleteFuncStack();

    }



    @Override
    public void inAAllFuncDef(AAllFuncDef node)
    {


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
        List<Integer> posRefs = new ArrayList<Integer>();

        for(int refCounter = 0;refCounter < refVars.size();refCounter++){
            for(int parCounter = 0; parCounter < firstList.size();parCounter++){
                if(refVars.get(refCounter).equals(firstList.get(parCounter))){
                    posRefs.add(parCounter);
                }
            }
        }


        List<String> parList = new ArrayList<String>();
        Map<String,List> parType = new HashMap<String,List>();
        int i=0;
        int k=0;
        while(i<listParamHelp.size() && k<firstList.size()){
            if(k==firstList.size()-1){
                if(k==0){
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


        ScopeObject obj =   new ScopeObject(myList.get(0).trim(),myList.get(myList.size()-1).trim(),"func", false,table.getPosition()) ;
        table.enter(obj);

        //insert to funcStack

        if (myList.get(0).trim().equals("main")){
            try{
                if (!myList.get(myList.size()-1).trim().equals("nothing")){
                    throw new MyException("ERROR! MAIN NOT VOID");
                }
            }
            catch (MyException e){
                throw new IllegalStateException("ERROR! MAIN NOT VOID");
            }

            try{
                if (!parType.isEmpty()){
                    throw new MyException("ERROR! MAIN HAS PARAMETERS");
                }
            }
            catch (MyException e){
                throw new IllegalStateException("ERROR! MAIN HAS PARAMETERS");
            }



        }


        String parameter;
        List<String> keySet = new ArrayList<String>(parType.keySet());
        List<String> listTypes = new ArrayList<String>();
        for(int counterParam=0;counterParam < firstList.size();counterParam++){
            parameter = firstList.get(counterParam);
            for(int counterKeys = 0; counterKeys<parType.keySet().size();counterKeys++){
                if(parType.get(keySet.get(counterKeys)).contains(parameter)){
                    listTypes.add(keySet.get(counterKeys));
                    break;
                }
            }
        }




        FuncScope funcObj = new FuncScope(myList.get(0).trim(),parType,myList.get(myList.size()-1).trim(),firstList.size(),table.getPosition(),listTypes,posRefs);
        table.insertFuncStack(funcObj);


        List<String> keys = new ArrayList<String>(parType.keySet());
        for(int n= 0; n<keys.size();n++){
            String key = keys.get(n);
            for(int v=0;v<parType.get(key).size();v++){
                String var = parType.get(key).get(v).toString();
                ScopeObject vars =   new ScopeObject(var,key,"par",false,table.getPosition()) ;
                table.insert(vars);
            }
        }


        for (int a=0; a<firstList.size(); a++){     //gia kathe parametro
            if (refVars.contains(firstList.get(a))){    //an anhkei sta refParameters
                int position = table.FindVarPosition(firstList.get(a));
                table.getMystack().get(position).setRef(true);              //set Ref
            }

        }

        im.genQuad(im.getOpCode().getUnit(),myList.get(0).trim(),null,null);



    }


    @Override
    public void outAAllFuncDef(AAllFuncDef node)            //exit functions
    {
        List<String> myList = new ArrayList<String>(Arrays.asList(node.getL().toString().split(" ")));

        List<String> rightList = new ArrayList<String>(Arrays.asList(node.getR().toString().split(" ")));

        try{
            if(!table.checkFuncDeclExistance()){
                throw new MyException("ERROR! DECLARATION WITHOUT DEFINITION");
            }
        }catch (MyException e){
            throw new IllegalStateException("ERROR! DECLARATION WITHOUT DEFINITION");
        }

        table.exit();
        Set list = table.getMap().entrySet();
        im.genQuad(im.getOpCode().getEndu(),myList.get(0).trim(),null,null);



    }


    @Override
    public void inAVarDefFuncDef(AVarDefFuncDef node)
    {


        String type = node.getR().toString();
        List<String> myList = new ArrayList<String>(Arrays.asList(node.getL().toString().split(" ")));
        for(int i=0;i<myList.size();i++){
            ScopeObject obj =   new ScopeObject(myList.get(i).trim(),type.trim(),"var",false,table.getPosition()) ;
            table.insert(obj);
            table.getAllVars().add(obj);
        }
    }

    @Override
    public void inAFuncDeclFuncDef(AFuncDeclFuncDef node)
    {
        List<String> myList = new ArrayList<String>(Arrays.asList(node.getFuncDef().toString().split(" ")));

        boolean decl = table.checkDeclaration(myList.get(0).toString().trim());
        try{
            if(decl==true){
                throw new MyException("ERROR! MULTIPLE DECLARATIONS FOR FUNCTION  \"" +myList.get(0).toString().trim() +"\" IN SAME SCOPE");
            }
        }
        catch (MyException e){
            throw new IllegalStateException("ERROR! MULTIPLE DECLARATIONS FOR FUNCTION  \"" +myList.get(0).toString().trim() +"\" IN SAME SCOPE");
        }

        ScopeObject obj = new ScopeObject(myList.get(0).toString().trim(),myList.get(myList.size()-1).toString().trim(),"decl", false,table.getPosition());
        table.insert(obj);
        //table.getAllVars().add(obj);


    }


    @Override
    public void inAExpressionStmt(AExpressionStmt node)
    {

        System.out.println("node is "+ node.getL().toString() + " "+ node.getR().toString());
        List<?> newl = (List) node.getL();
        String name = newl.get(0).toString();

        String initialName=name;

        if (name.contains("[")){

            System.out.println("name contains " +name);

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


            try{
                if(table.isNumeric(numb)==false){
                    throw new MyException("ERROR! NON NUMERIC VALUE GIVEN AS ARRAY INDEX");
                }
            }catch (MyException e){
                throw new IllegalStateException("ERROR! NON NUMERIC VALUE GIVEN AS ARRAY INDEX");
            }



            String type = table.FindVariableType(finalS);
            System.out.println("type "+ type);
            try{
                if(type==null){
                    throw new MyException("ERROR! VARIABLE DOESN'T EXIST");
                }
            }catch (MyException e){
                throw new IllegalStateException("ERROR! VARIABLE DOESN'T EXIST");
            }

            Object w = im.newTemp(type);
            String name2 = "$" + im.getCount();
            name=finalS;

            im.genQuad(im.getOpCode().getArray(),finalS,numb, "$" + im.getCount());
            im.insertPlaceHelper((finalS+ "["+ numb+ "]"),"$" + (im.getCount()-1));


        }

        String type;
        String str=node.getR().toString().trim();

        String reg=name;
        if (initialName.contains("[")){
            reg="$" + (im.getCount()-1);
        }

        if (node.getR().getClass().getSimpleName().equals("AFuncAllExpr")) {

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
        }


        else if (str.contains("\"")){

            int length=str.length()-1;
            type = "char[" +length+"]";

            //im.insertPlaceHelper(str,reg);

            im.genQuad(im.getOpCode().getAssignment(),str,null,reg);

        }
        else if (node.getR().getClass().getSimpleName().equals("ALetterAllExpr")) {

            type= "char";
            //im.insertPlaceHelper(str,reg);

            im.genQuad(im.getOpCode().getAssignment(),str,null,reg);
        }
        else if (node.getR().getClass().getSimpleName().equals("AConstantAllExpr")){

            type= "int";
            //im.insertPlaceHelper(str,reg);

            im.genQuad(im.getOpCode().getAssignment(),str,null,reg);
        }
        else if (node.getR().getClass().getSimpleName().equals("AAddSubAllExpr")){
            type= "int";
        }
        else if (node.getR().getClass().getSimpleName().equals("ARestSignsAllExpr")){
            type= "int";
        }
        else if (node.getR().getClass().getSimpleName().equals("AWithPlminAllExpr")){
            type= "int";
            //im.insertPlaceHelper(str,reg);

            im.genQuad(im.getOpCode().getAssignment(),str,null,reg);
        }
        else if (node.getR().toString().contains("[")){

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

            try{
                if(table.isNumeric(numb)==false){
                    throw new MyException("ERROR! NON NUMERIC VALUE GIVEN AS ARRAY INDEX");
                }
            }catch (MyException e){
                throw new IllegalStateException("ERROR! NON NUMERIC VALUE GIVEN AS ARRAY INDEX");
            }

            type = table.FindVariableType(finalS);
            try{
                if(type==null){
                    throw new MyException("ERROR! VARIABLE DOESN'T EXIST");
                }
            }catch (MyException e){
                throw new IllegalStateException("ERROR! VARIABLE DOESN'T EXIST");
            }

            ScopeObject obj = new ScopeObject(finalS,type,"var", false,table.getPosition());
           // table.getAllVars().add(obj);
            try{
                if (table.lookupVarAndTypeOnlyForVariables(obj)){
                    throw new MyException("ERROR INCORECT TYPE OF VARIABLE");
                }
            }
            catch (MyException e){
                throw new IllegalStateException("ERROR INCORECT TYPE OF VARIABLE");
            }

            return;




        }
        else {

            //find variables type
            type = table.FindVariableType(str);
            try{
                if(type==null){
                    throw new MyException("ERROR! A VARIABLE WITH NO TYPE");
                }
            }catch (MyException e){
                throw new IllegalStateException("ERROR! A VARIABLE WITH NO TYPE");
            }
            //im.insertPlaceHelper(str,reg);

            im.genQuad(im.getOpCode().getAssignment(),str,null,reg);
        }

        if (initialName.contains("[")){
            type = type + "[]";
        }


        ScopeObject obj = new ScopeObject(name,type,"var", false,table.getPosition());
       // table.getAllVars().add(obj);
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

            try{
                if(table.isNumeric(numb)==false){
                    throw new MyException("ERROR! NON NUMERIC VALUE GIVEN AS ARRAY INDEX");
                }
            }catch (MyException e){
                throw new IllegalStateException("ERROR! NON NUMERIC VALUE GIVEN AS ARRAY INDEX");
            }

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


            //im.genQuad(im.getOpCode().getArray(),finalS,numb, "$" + im.getCount());
            //im.insertPlaceHelper((finalS+numb),"$" + im.getCount());
            name=finalS;
        }

        String type;
        String str=node.getR().toString();
        List myString = new ArrayList(Arrays.asList(str.split(" ")));

        String reg=name;
        if (initialName.contains("[")){
            reg="$" + (im.getCount()-1);
        }


        if (node.getR().getClass().getSimpleName().equals("AFuncAllExpr")) {
            //im.insertPlaceHelper(myString.get(0).toString(),reg);


            im.genQuad(im.getOpCode().getAssignment(),myString.get(0),null,reg);
        }
        else if (node.getR().getClass().getSimpleName().equals("AAddSubAllExpr")){

            //im.insertPlaceHelper("$" + (im.getCount()-1),reg);

            im.genQuad(im.getOpCode().getAssignment(),"$" + (im.getCount()-1),null,reg);

        }

        else if (node.getR().getClass().getSimpleName().equals("ARestSignsAllExpr")){

            //im.insertPlaceHelper("$" + (im.getCount()-1),reg);


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

            if (numb.contains("+")|| numb.contains("-") || numb.contains("*") || numb.contains("/") || numb.contains("mod")){
                Object w = im.newTemp("Integer");
                String name2 = "$" + im.getCount();

                String myReg = im.Place(numb);
                try{
                    if(myReg==null){
                        throw new MyException("FOUND VARIABLE NOT DECLARED");
                    }
                }
                catch (MyException e){
                    throw new IllegalStateException("FOUND VARIABLE NOT DECLARED");
                }

                //System.out.println("TEMPSTRING: " + tempString + " pos: " + im.getCount());
                im.insertPlaceHelper(tempString,"$" + im.getCount());

                //im.insertPlaceHelper(tempList.get(0).toString(),name2);



                im.genQuad(im.getOpCode().getArray(),tempList.get(0), myReg,name2);

                im.genQuad(im.getOpCode().getAssignment(),"$" + (im.getCount()-1),null,name);
            }
            else{
                System.err.println("TEMPSTRING NUMERIC "+tempString);
                /* try{
                if(table.isNumeric(numb)==false){
                    throw new MyException("ERROR! NON NUMERIC VALUE GIVEN AS ARRAY INDEX");
                }
            }catch (MyException e){
                throw new IllegalStateException("ERROR! NON NUMERIC VALUE GIVEN AS ARRAY INDEX");
            }*/
                String tempString2 = tempString;
                while (tempString2.contains("[") || tempString2.contains("]")){
                    tempString2 = tempString2.replace("[","");
                    tempString2 = tempString2.replace("]","");
                }
                List tempList2 = new ArrayList(Arrays.asList(tempString2.split("\\s+")));


                Object w = im.newTemp("Integer");
                String name2 = "$" + im.getCount();


                //im.insertPlaceHelper("$" + (im.getCount()-1),name);


                im.genQuad(im.getOpCode().getArray(),tempList.get(0),tempList2.get(tempList2.size()-1),name2);
                im.insertPlaceHelper(tempString.replace(" ", ""),"$" + (im.getCount()-1));
                im.genQuad(im.getOpCode().getAssignment(),"$" + (im.getCount()-1),null,name);
            }
        }
    }



    @Override
    public void inAVarStmt(AVarStmt node)
    {

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

        System.out.println("nodee " + node.getL().toString() + " "+ node.getR().toString());

        String ournode = node.getL().toString() + node.getR().toString();

        List<String> myList = new ArrayList<String>(Arrays.asList(ournode.split(" ")));

        for(int kk=0; kk< myList.size(); kk++){
            System.out.println("item  is "+ myList.get(kk).trim());
        }



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

        for(int kk=0; kk< myList.size(); kk++){
            System.out.println("item2  is "+ myList.get(kk).trim());
        }




        for(int i=0;i<myList.size();i++) {

            String type = table.FindVariableType(myList.get(i).trim());

            if (type != null) {

                if(type.contains("int[")  && myList.contains("[")){
                    type = "int";
                }

                try{
                    if (!type.equals("int")){
                        throw new MyException("ERROR WRONG EXPR TYPE");
                    }
                }
                catch (MyException e){
                    throw new IllegalStateException("ERROR WRONG EXPR TYPE");
                }
            }
        }

    }

    @Override
    public void outAAddSubAllExpr(AAddSubAllExpr node)
    {
        List<String> myList = new ArrayList(Arrays.asList(node.getL().toString().trim().split("\\s+")));
        String op = myList.get(myList.size()-1);
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
        List<String> myList = new ArrayList(Arrays.asList(node.getL().toString().trim().split("\\s+")));
        String op = myList.get(myList.size() - 1);
        String rightNumb = node.getR().toString().trim();
        List<String> rightNumlist = new ArrayList(Arrays.asList(node.getR().toString().trim().split("\\s+")));

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


        String ournode = node.getL().toString() + node.getR().toString();

        List<String> myList = new ArrayList<String>(Arrays.asList(ournode.split(" ")));
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
            if (type != null) {
                if(type.contains("int[")  && myList.contains("[")){
                    type = "int";
                }

                try{
                    if (!type.equals("int")){
                        throw new MyException("ERROR WRONG EXPR TYPE");
                    }
                }
                catch (MyException e){
                    throw new IllegalStateException("ERROR WRONG EXPR TYPE");
                }
            }
        }

    }


    @Override
    public void inAWithPlminAllExpr(AWithPlminAllExpr node)
    {

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

    }


    @Override
    public void inAExprsignsCond(AExprsignsCond node)
    {
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
            else {
                String retType = table.getFuncType(name);
                if (retType != null) {
                    try{
                        if (!retType.equals("int")){
                            throw new MyException("ERROR WRONG EXPR TYPE");
                        }
                    }
                catch (MyException e){
                        throw new IllegalStateException("ERROR WRONG EXPR TYPE");
                    }
                }
            }

        }
        if (myListRight.size()==1){
            String name = myListRight.get(0).trim();
            String type = table.FindVariableType(name);
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
            else {
                String retType = table.getFuncType(name);
                if (retType != null) {
                    try{
                        if (!retType.equals("int")){
                            throw new MyException("ERROR WRONG EXPR TYPE");
                        }
                    }
                    catch (MyException e){
                        throw new IllegalStateException("ERROR WRONG EXPR TYPE");
                    }
                }
            }
        }


        List<String> listFinal = new ArrayList<String>();
        listFinal.addAll(myListLeft);
        listFinal.addAll(myListRight);

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

        List<String> myList = new ArrayList(Arrays.asList(node.getL().toString().trim().split("\\s+")));
        String op = myList.get(myList.size()-1);
        String rightNumb = node.getR().toString().trim();
        List<String> rightNumlist = new ArrayList(Arrays.asList(node.getR().toString().trim().split("\\s+")));

        String left = "";

        for(int i=0;i<myList.size()-1;i++){
            left += myList.get(i);
        }
        left = left.trim();

        operator myOp = new operator("relop",op);
        Object w = im.newTemp("Integer");
        String name2 = "$" + im.getCount();

        String wholeExpr = node.getL().toString() + node.getR().toString().trim();

        int myCounter=1;

        im.insertPlaceHelper(wholeExpr, "$"+ "0" + im.getCount());

        InterReg reg = new InterReg(w,im.getCount(),im.getOpCode().getAssignment(),w.getClass().getSimpleName(),name2);
        im.insertReg(reg);

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
           else{
               try{
                   if(table.isNumeric(numb)==false){
                       throw new MyException("ERROR! NON NUMERIC VALUE GIVEN AS ARRAY INDEX");
                   }
               }catch (MyException e){
                   throw new IllegalStateException("ERROR! NON NUMERIC VALUE GIVEN AS ARRAY INDEX");
               }
           }

           im.insertPlaceHelper(left,"$" + im.getCount());

           im.genQuad(im.getOpCode().getArray(),finalS,numb,"$" + im.getCount());

        }

        String right = "";

        for(int i=0;i<rightNumlist.size();i++){
            right += rightNumlist.get(i);
        }
        right = right.trim();

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
            else{
                try{
                    if(table.isNumeric(numb)==false){
                        throw new MyException("ERROR! NON NUMERIC VALUE GIVEN AS ARRAY INDEX");
                    }
                }catch (MyException e){
                    throw new IllegalStateException("ERROR! NON NUMERIC VALUE GIVEN AS ARRAY INDEX");
                }
            }
            im.insertPlaceHelper(right, "$" + im.getCount());
            im.genQuad(im.getOpCode().getArray(), finalS, numb, "$" + im.getCount());

        }

        String regRight = im.Place(node.getR().toString().trim());



        String regLeft = im.Place(left);

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

            try{
                if(regLeft == null){
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
        table.setIfWhileScope(table.getIfWhileScope()+1);
    }

    @Override
    public void outANoElseStmt(ANoElseStmt node){
        im.backpatch("endOfIf" + table.getIfWhileScope(),im.getCount());
        table.setIfWhileScope(table.getIfWhileScope() -1);
    }

    @Override
    public void inAIfstmt(AIfstmt node){
        im.backpatch("then",im.getCount());
    }

    @Override
    public void inAMyStmtelse(AMyStmtelse node){
        im.genQuad(im.getOpCode().getJump(),null,null,"END");
        im.backpatch("endOfIf" + table.getIfWhileScope(),im.getCount());


    }

    @Override
    public void inAWithElseStmt(AWithElseStmt node){
        table.setIfWhileScope(table.getIfWhileScope()+1);
    }

    @Override
    public void outAWithElseStmt(AWithElseStmt node){
        im.backpatch("END",im.getCount());
        table.setIfWhileScope(table.getIfWhileScope()-1);
    }

    @Override
    public void inAFuncCallWithoutStmt(AFuncCallWithoutStmt node){
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

        String funcName = node.getL().toString().trim();

        String nodeRight = node.getR().toString().trim();


        List <String> parameters = new ArrayList<String>(Arrays.asList(nodeRight.replaceAll("\\s+","").split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1)));

        List paramType = new ArrayList();





        for (int i=0; i<parameters.size(); i++){

            List<Integer> posRefs = table.getFuncRefs(funcName);
            try{
                if(posRefs.contains(i)){

                    if(!parameters.get(i).contains("\"") && table.FindVariableType(parameters.get(i).trim())==null)
                        throw new MyException("REF PARAMETER MUST BE VARIABLE OR STRING");
                }
            }catch (MyException e){
                throw new IllegalStateException("REF PARAMETER MUST BE VARIABLE OR STRING");
            }


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
                    else{
                        try{
                            if(table.isNumeric(numb)==false){
                                throw new MyException("ERROR! NON NUMERIC VALUE GIVEN AS ARRAY INDEX");
                            }
                        }catch (MyException e){
                            throw new IllegalStateException("ERROR! NON NUMERIC VALUE GIVEN AS ARRAY INDEX");
                        }
                    }

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
                boolean prim = false;

                String type = table.FindVariableType(parameters.get(i));

                if(type==null) {
                    type = table.getFuncType(parameters.get(i));
                }

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
        //im.genQuad(im.getOpCode().getCall(),null,null,funcName);
    }

    @Override
    public void outAFuncCallWithStmt(AFuncCallWithStmt node){
        String wholeStr = node.getL().toString() + node.getR().toString().trim();

        String left = node.getL().toString().trim();
        im.insertRef(left,table);

        im.insertPlaceHelper(wholeStr,"$" + im.getCount());
        String funcName = node.getL().toString().trim();
        im.genQuad(im.getOpCode().getCall(),null,null,funcName);
    }


    @Override
    public void outAParametersAllExpr(AParametersAllExpr node){
        List <String> parameters = new ArrayList<String>(Arrays.asList(node.toString().trim().replaceAll("\\s+","").split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1)));

        int noOfParam = parameters.size();


        for (int i=0; i<parameters.size();i++) {
            String par = String.valueOf( parameters.get(i));

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
                else{
                      try{
                          if(table.isNumeric(numb)==false){
                              throw new MyException("ERROR! NON NUMERIC VALUE GIVEN AS ARRAY INDEX");
                          }
                      }catch (MyException e){
                          throw new IllegalStateException("ERROR! NON NUMERIC VALUE GIVEN AS ARRAY INDEX");
                      }
                  }

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
        boolean returnFlag = table.findReturnType("nothing");

        try{
            if(returnFlag == false){
                throw new MyException("WRONG RETURN TYPE");
            }
        }catch (MyException e){
            throw new IllegalStateException("WRONG RETURN TYPE");
        }
    }

    @Override
    public void inAReturn2Stmt(AReturn2Stmt node){

        im.genQuad(im.getOpCode().getRet(),null,null,null);
        boolean returnFlag = table.findReturnType("nothing");

        try{
            if(returnFlag == false){
                throw new MyException("WRONG RETURN TYPE");
            }
        }catch (MyException e){
            throw new IllegalStateException("WRONG RETURN TYPE");
        }
    }


    @Override
    public void inAReturnWithStmt(AReturnWithStmt node) {


        String type = node.getAllExpr().getClass().getSimpleName();
        String ourType = null;
        String retType = null;

        if (type.equals("AConstantAllExpr")){
            ourType = "Integer";
            retType = "int";
        }
        else if (type.equals("ALetterAllExpr")) {
            ourType = "Char";
            retType = "char";
        }
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
            retType=ourType;

            return;


        }
        else if (node.getAllExpr().toString().contains("[")){
            //array
            List<String> nameList = new ArrayList<String>(Arrays.asList( node.getAllExpr().toString().split(" ")));
            ourType = table.FindVariableType(nameList.get(0));

            retType = ourType;

            String retExpr =node.getAllExpr().toString();
            int i=0;
            char c;
            int counterRet = 0;
            while(i<retType.length()){
                c = retType.charAt(i);
                if(c=='['){
                    while(c!=']'){
                        i++;
                        c = retType.charAt(i);

                    }
                    counterRet++;
                }
                i++;
            }
            i=0;
            int retCounter=0;

            while(i<retExpr.length()){
                c = retExpr.charAt(i);
                if(c=='['){
                    while(c!=']'){
                        i++;
                        c = retExpr.charAt(i);

                    }
                    retCounter++;
                }
                i++;
            }

            try{
                if(retCounter != counterRet){
                    throw new MyException("WRONG RETURN TYPE (DIMENSION ERROR)");
                }
            }catch (MyException e){
                throw new IllegalStateException("WRONG RETURN TYPE (DIMENSION ERROR)");
            }

            try{
                if(ourType == null){
                    throw new MyException("FUNC NOT FOUND");
                }
            }catch (MyException e){
                throw new IllegalStateException("FUNC NOT FOUND");
            }

            if (ourType.contains("int")) {
                ourType = "Integer";
                retType = "int";
            }
            else if(ourType.contains("char")) {
                ourType = "Char";
                retType = "char";
            }

            boolean returnFlag = table.findReturnType(retType);

            try{
                if(returnFlag == false){
                    throw new MyException("WRONG RETURN TYPE");
                }
            }catch (MyException e){
                throw new IllegalStateException("WRONG RETURN TYPE");
            }



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

            im.insertPlaceHelper(prevName,name2);

            im.genQuad(im.getOpCode().getAssignment(),prevName,null, name2);

            im.genQuad(im.getOpCode().getRet(),null,null,null);
            return;
        }
        else if(type.equals("ALValueAllExpr")){
            List<String> nameList = new ArrayList<String>(Arrays.asList( node.getAllExpr().toString().split(" ")));
            ourType = table.FindVariableType(nameList.get(0));

            retType = ourType;

            try{
                if(ourType == null){
                    throw new MyException("FUNC NOT FOUND");
                }
            }catch (MyException e){
                throw new IllegalStateException("FUNC NOT FOUND");
            }

            if (ourType.contains("int")) {
                ourType = "Integer";
            }
            else if(ourType.contains("char")) {
                ourType = "Char";
            }


            List<String> tempList = new ArrayList<String>(Arrays.asList( node.getAllExpr().toString().split(" ")));
            tempList.remove(0);
            tempList.remove("[");
            tempList.remove("]");
            Object w = im.newTemp(ourType);
            String name = "$" + im.getCount();
            String prevName = name;

        }

        boolean returnFlag = table.findReturnType(retType);

        try{
            if(returnFlag == false){
                throw new MyException("WRONG RETURN TYPE");
            }
        }catch (MyException e){
            throw new IllegalStateException("WRONG RETURN TYPE");
        }

        Object w = im.newTemp(ourType);
        String name = "$" + im.getCount();

        im.insertPlaceHelper(node.getAllExpr().toString(),name);


        im.genQuad(im.getOpCode().getAssignment(),node.getAllExpr(),null, name);

        im.genQuad(im.getOpCode().getRet(),null,null,null);

    }

    @Override
    public void inAReturnWith2Stmt(AReturnWith2Stmt node) {

        String type = node.getAllExpr().getClass().getSimpleName();
        String ourType = null;
        String retType = null;

        if (type.equals("AConstantAllExpr")){
            ourType = "Integer";
            retType = "int";
        }
        else if (type.equals("ALetterAllExpr")) {
            ourType = "Char";
            retType = "char";
        }
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
            retType=ourType;

            return;


        }
        else if (node.getAllExpr().toString().contains("[")){
            //array
            List<String> nameList = new ArrayList<String>(Arrays.asList( node.getAllExpr().toString().split(" ")));
            ourType = table.FindVariableType(nameList.get(0));

            retType = ourType;

            String retExpr =node.getAllExpr().toString();
            int i=0;
            char c;
            int counterRet = 0;
            while(i<retType.length()){
                c = retType.charAt(i);
                if(c=='['){
                    while(c!=']'){
                        i++;
                        c = retType.charAt(i);

                    }
                    counterRet++;
                }
                i++;
            }
            i=0;
            int retCounter=0;

            while(i<retExpr.length()){
                c = retExpr.charAt(i);
                if(c=='['){
                    while(c!=']'){
                        i++;
                        c = retExpr.charAt(i);

                    }
                    retCounter++;
                }
                i++;
            }

            try{
                if(retCounter != counterRet){
                    throw new MyException("WRONG RETURN TYPE (DIMENSION ERROR)");
                }
            }catch (MyException e){
                throw new IllegalStateException("WRONG RETURN TYPE (DIMENSION ERROR)");
            }

            try{
                if(ourType == null){
                    throw new MyException("FUNC NOT FOUND");
                }
            }catch (MyException e){
                throw new IllegalStateException("FUNC NOT FOUND");
            }

            if (ourType.contains("int")) {
                ourType = "Integer";
                retType = "int";
            }
            else if(ourType.contains("char")) {
                ourType = "Char";
                retType = "char";
            }

            boolean returnFlag = table.findReturnType(retType);

            try{
                if(returnFlag == false){
                    throw new MyException("WRONG RETURN TYPE");
                }
            }catch (MyException e){
                throw new IllegalStateException("WRONG RETURN TYPE");
            }



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
            im.insertPlaceHelper(prevName,name2);

            im.genQuad(im.getOpCode().getAssignment(),prevName,null, name2);

            im.genQuad(im.getOpCode().getRet(),null,null,null);
            return;
        }
        else if(type.equals("ALValueAllExpr")){
            List<String> nameList = new ArrayList<String>(Arrays.asList( node.getAllExpr().toString().split(" ")));
            ourType = table.FindVariableType(nameList.get(0));

            retType = ourType;

            try{
                if(ourType == null){
                    throw new MyException("FUNC NOT FOUND");
                }
            }catch (MyException e){
                throw new IllegalStateException("FUNC NOT FOUND");
            }

            if (ourType.contains("int")) {
                ourType = "Integer";
            }
            else if(ourType.contains("char")) {
                ourType = "Char";
            }


            List<String> tempList = new ArrayList<String>(Arrays.asList( node.getAllExpr().toString().split(" ")));
            tempList.remove(0);
            tempList.remove("[");
            tempList.remove("]");
            Object w = im.newTemp(ourType);
            String name = "$" + im.getCount();
            String prevName = name;

        }

        boolean returnFlag = table.findReturnType(retType);

        try{
            if(returnFlag == false){
                throw new MyException("WRONG RETURN TYPE");
            }
        }catch (MyException e){
            throw new IllegalStateException("WRONG RETURN TYPE");
        }

        Object w = im.newTemp(ourType);
        String name = "$" + im.getCount();

        InterReg reg = new InterReg(w,im.getCount(),im.getOpCode().getAssignment(),w.getClass().getSimpleName(),name);
        im.insertReg(reg);

        im.insertPlaceHelper(node.getAllExpr().toString(),name);

        im.genQuad(im.getOpCode().getAssignment(),node.getAllExpr(),null, name);

        im.genQuad(im.getOpCode().getRet(),null,null,null);
    }

    @Override
    public void inAReturnExprStmt(AReturnExprStmt node) {

        String type = node.getAllExpr().getClass().getSimpleName();
        String ourType = null;
        String retType = null;

        if (type.equals("AConstantAllExpr")){
            ourType = "Integer";
            retType = "int";
        }
        else if (type.equals("ALetterAllExpr")) {
            ourType = "Char";
            retType = "char";
        }
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
            retType=ourType;

            return;


        }
        else if (type.equals("AAddSubAllExpr") || type.equals("ARestSignsAllExpr")|| type.equals("APlusPlusMinus")){
            ourType = "Integer";
            retType = "int";
        }
        else if (node.getAllExpr().toString().contains("[")){
            //array
            List<String> nameList = new ArrayList<String>(Arrays.asList( node.getAllExpr().toString().split(" ")));
            ourType = table.FindVariableType(nameList.get(0));

            retType = ourType;


            String retExpr =node.getAllExpr().toString();
            int i=0;
            char c;
            int counterRet = 0;
            while(i<retType.length()){
                c = retType.charAt(i);
                if(c=='['){
                    while(c!=']'){
                        i++;
                        c = retType.charAt(i);

                    }
                    counterRet++;
                }
                i++;
            }
            i=0;
            int retCounter=0;

            while(i<retExpr.length()){
                c = retExpr.charAt(i);
                if(c=='['){
                    while(c!=']'){
                        i++;
                        c = retExpr.charAt(i);

                    }
                    retCounter++;
                }
                i++;
            }

            try{
                if(retCounter != counterRet){
                    throw new MyException("WRONG RETURN TYPE (DIMENSION ERROR)");
                }
            }catch (MyException e){
                throw new IllegalStateException("WRONG RETURN TYPE (DIMENSION ERROR)");
            }

            try{
                if(ourType == null){
                    throw new MyException("FUNC NOT FOUND");
                }
            }catch (MyException e){
                throw new IllegalStateException("FUNC NOT FOUND");
            }

            if (ourType.contains("int")) {
                ourType = "Integer";
                retType = "int";
            }
            else if(ourType.contains("char")) {
                ourType = "Char";
                retType = "char";
            }

            boolean returnFlag = table.findReturnType(retType);

            try{
                if(returnFlag == false){
                    throw new MyException("WRONG RETURN TYPE");
                }
            }catch (MyException e){
                throw new IllegalStateException("WRONG RETURN TYPE");
            }



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

            im.insertPlaceHelper(prevName,name2);

            im.genQuad(im.getOpCode().getAssignment(),prevName,null, name2);

            im.genQuad(im.getOpCode().getRet(),null,null,null);
            return;
        }
        else if(type.equals("ALValueAllExpr")){
            List<String> nameList = new ArrayList<String>(Arrays.asList( node.getAllExpr().toString().split(" ")));
            ourType = table.FindVariableType(nameList.get(0));

            retType = ourType;

            try{
                if(ourType == null){
                    throw new MyException("FUNC NOT FOUND");
                }
            }catch (MyException e){
                throw new IllegalStateException("FUNC NOT FOUND");
            }

            if (ourType.contains("int")) {
                ourType = "Integer";
            }
            else if(ourType.contains("char")) {
                ourType = "Char";
            }


            List<String> tempList = new ArrayList<String>(Arrays.asList( node.getAllExpr().toString().split(" ")));
            tempList.remove(0);
            tempList.remove("[");
            tempList.remove("]");
            Object w = im.newTemp(ourType);
            String name = "$" + im.getCount();
            String prevName = name;

        }

        boolean returnFlag = table.findReturnType(retType);

        try{
            if(returnFlag == false){
                throw new MyException("WRONG RETURN TYPE");
            }
        }catch (MyException e){
            throw new IllegalStateException("WRONG RETURN TYPE");
        }

        Object w = im.newTemp(ourType);
        String name = "$" + im.getCount();


        im.insertPlaceHelper(node.getAllExpr().toString(),name);

        im.genQuad(im.getOpCode().getAssignment(),node.getAllExpr(),null, name);

        im.genQuad(im.getOpCode().getRet(),null,null,null);
    }

    @Override
    public void inAReturnExpr2Stmt(AReturnExpr2Stmt node) {

        String type = node.getAllExpr().getClass().getSimpleName();
        String ourType = null;
        String retType = null;

        if (type.equals("AConstantAllExpr")){
            ourType = "Integer";
            retType = "int";
        }
        else if (type.equals("ALetterAllExpr")) {
            ourType = "Char";
            retType = "char";
        }
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
            retType=ourType;

            return;


        }
        else if (node.getAllExpr().toString().contains("[")){
            //array
            List<String> nameList = new ArrayList<String>(Arrays.asList( node.getAllExpr().toString().split(" ")));
            ourType = table.FindVariableType(nameList.get(0));

            retType = ourType;


            String retExpr =node.getAllExpr().toString();
            int i=0;
            char c;
            int counterRet = 0;
            while(i<retType.length()){
                c = retType.charAt(i);
                if(c=='['){
                    while(c!=']'){
                        i++;
                        c = retType.charAt(i);

                    }
                    counterRet++;
                }
                i++;
            }
            i=0;
            int retCounter=0;

            while(i<retExpr.length()){
                c = retExpr.charAt(i);
                if(c=='['){
                    while(c!=']'){
                        i++;
                        c = retExpr.charAt(i);

                    }
                    retCounter++;
                }
                i++;
            }

            try{
                if(retCounter != counterRet){
                    throw new MyException("WRONG RETURN TYPE (DIMENSION ERROR)");
                }
            }catch (MyException e){
                throw new IllegalStateException("WRONG RETURN TYPE (DIMENSION ERROR)");
            }

            try{
                if(ourType == null){
                    throw new MyException("FUNC NOT FOUND");
                }
            }catch (MyException e){
                throw new IllegalStateException("FUNC NOT FOUND");
            }

            if (ourType.contains("int")) {
                ourType = "Integer";
                retType = "int";
            }
            else if(ourType.contains("char")) {
                ourType = "Char";
                retType = "char";
            }

            boolean returnFlag = table.findReturnType(retType);

            try{
                if(returnFlag == false){
                    throw new MyException("WRONG RETURN TYPE");
                }
            }catch (MyException e){
                throw new IllegalStateException("WRONG RETURN TYPE");
            }



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



            im.insertPlaceHelper(prevName,name2);
            im.genQuad(im.getOpCode().getAssignment(),prevName,null, name2);

            im.genQuad(im.getOpCode().getRet(),null,null,null);
            return;
        }
        else if(type.equals("ALValueAllExpr")){
            List<String> nameList = new ArrayList<String>(Arrays.asList( node.getAllExpr().toString().split(" ")));
            ourType = table.FindVariableType(nameList.get(0));

            retType = ourType;

            try{
                if(ourType == null){
                    throw new MyException("FUNC NOT FOUND");
                }
            }catch (MyException e){
                throw new IllegalStateException("FUNC NOT FOUND");
            }

            if (ourType.contains("int")) {
                ourType = "Integer";
            }
            else if(ourType.contains("char")) {
                ourType = "Char";
            }


            List<String> tempList = new ArrayList<String>(Arrays.asList( node.getAllExpr().toString().split(" ")));
            tempList.remove(0);
            tempList.remove("[");
            tempList.remove("]");
            Object w = im.newTemp(ourType);
            String name = "$" + im.getCount();
            String prevName = name;

        }

        boolean returnFlag = table.findReturnType(retType);

        try{
            if(returnFlag == false){
                throw new MyException("WRONG RETURN TYPE");
            }
        }catch (MyException e){
            throw new IllegalStateException("WRONG RETURN TYPE");
        }

        Object w = im.newTemp(ourType);
        String name = "$" + im.getCount();

        InterReg reg = new InterReg(w,im.getCount(),im.getOpCode().getAssignment(),w.getClass().getSimpleName(),name);
        im.insertReg(reg);

        im.insertPlaceHelper(node.getAllExpr().toString(),name);

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

        String left = node.getL().toString().trim();
        String right = node.getR().toString().trim();
        String wholeString = node.getL().toString() +node.getR().toString().trim();

        im.insertPlaceHelper(wholeString,"$" + im.getCount());

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

        String left = node.getL().toString().trim();
        String right = node.getR().toString().trim();
        String wholeString = node.getL().toString() +node.getR().toString().trim();

        im.insertPlaceHelper(wholeString,"$" + im.getCount());

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

        String left = node.getL().toString().trim();
        String right = node.getR().toString().trim();
        String wholeString = node.getL().toString() +node.getR().toString().trim();

        im.insertPlaceHelper(wholeString,"$" + im.getCount());

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

        im.genQuad(im.getOpCode().getIfb(), regWhole, null,im.getCount()+2 );
        im.genQuad(im.getOpCode().getJump(), null, null, "endOfIf"+table.getIfWhileScope());

        globalFlagNot= false;

    }

    @Override
    public void inAWhileStmt(AWhileStmt node) {
        table.setIfWhileScope(table.getIfWhileScope()+1);
    }

    @Override
    public void inAWhileElseStmt(AWhileElseStmt node) {
        table.setIfWhileScope(table.getIfWhileScope()+1);
    }


    @Override
    public void outAWhileStmt(AWhileStmt node) {

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
        im.genQuad(im.getOpCode().getJump(),null,null,regNum);
        table.setIfWhileScope(table.getIfWhileScope()-1);


    }

    @Override
    public void inAExpressionElseStmt(AExpressionElseStmt node)
    {

        List<?> newl = (List) node.getL();
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

            try{
                if(table.isNumeric(numb)==false){
                    throw new MyException("ERROR! NON NUMERIC VALUE GIVEN AS ARRAY INDEX");
                }
            }catch (MyException e){
                throw new IllegalStateException("ERROR! NON NUMERIC VALUE GIVEN AS ARRAY INDEX");
            }

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
            name=finalS;
        }

        String type;
        String str=node.getR().toString().trim();

        String reg=name;
        if (initialName.contains("[")){
            reg="$" + (im.getCount()-1);
        }

        if (node.getR().getClass().getSimpleName().equals("AFuncAllExpr")) {

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
        }


        else if (str.contains("\"")){

            int length=str.length()-1;
            type = "char[" +length+"]";

            //im.insertPlaceHelper(str,reg);

            im.genQuad(im.getOpCode().getAssignment(),str,null,reg);

        }
        else if (node.getR().getClass().getSimpleName().equals("ALetterAllExpr")) {

            type= "char";
            //im.insertPlaceHelper(str,reg);

            im.genQuad(im.getOpCode().getAssignment(),str,null,reg);
        }
        else if (node.getR().getClass().getSimpleName().equals("AConstantAllExpr")){

            type= "int";
            //im.insertPlaceHelper(str,reg);

            im.genQuad(im.getOpCode().getAssignment(),str,null,reg);
        }
        else if (node.getR().getClass().getSimpleName().equals("AAddSubAllExpr")){
            type= "int";
        }
        else if (node.getR().getClass().getSimpleName().equals("ARestSignsAllExpr")){
            type= "int";
        }
        else if (node.getR().getClass().getSimpleName().equals("AWithPlminAllExpr")){
            type= "int";
            //im.insertPlaceHelper(str,reg);

            im.genQuad(im.getOpCode().getAssignment(),str,null,reg);
        }
        else if (node.getR().toString().contains("[")){

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

            try{
                if(table.isNumeric(numb)==false){
                    throw new MyException("ERROR! NON NUMERIC VALUE GIVEN AS ARRAY INDEX");
                }
            }catch (MyException e){
                throw new IllegalStateException("ERROR! NON NUMERIC VALUE GIVEN AS ARRAY INDEX");
            }

            type = table.FindVariableType(finalS);
            try{
                if(type==null){
                    throw new MyException("ERROR! VARIABLE DOESN'T EXIST");
                }
            }catch (MyException e){
                throw new IllegalStateException("ERROR! VARIABLE DOESN'T EXIST");
            }
        }
        else {

            //find variables type
            type = table.FindVariableType(str);
            try{
                if(type==null){
                    throw new MyException("ERROR! A VARIABLE WITH NO TYPE");
                }
            }catch (MyException e){
                throw new IllegalStateException("ERROR! A VARIABLE WITH NO TYPE");
            }
            //im.insertPlaceHelper(str,reg);

            im.genQuad(im.getOpCode().getAssignment(),str,null,reg);
        }



        ScopeObject obj = new ScopeObject(name,type,"var", false,table.getPosition());
       // table.getAllVars().add(obj);
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
    public void outAExpressionElseStmt(AExpressionElseStmt node) {

        List<?> newl = (List) node.getL();

        String name = newl.get(0).toString();

        String initialName = name;

        if (name.contains("[")) {

            String numb = "";
            int i = 0;
            String finalS = "";

            while (i < name.length()) {
                char c = name.charAt(i);
                if (c == '[') {
                    while (i < name.length()) {
                        i++;
                        c = name.charAt(i);
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


            try{
                if(table.isNumeric(numb)==false){
                    throw new MyException("ERROR! NON NUMERIC VALUE GIVEN AS ARRAY INDEX");
                }
            }catch (MyException e){
                throw new IllegalStateException("ERROR! NON NUMERIC VALUE GIVEN AS ARRAY INDEX");
            }
            String type = table.FindVariableType(finalS);
            try {
                if (type == null) {
                    throw new MyException("ERROR! VARIABLE DOESN'T EXIST");
                }
            } catch (MyException e) {
                throw new IllegalStateException("ERROR! VARIABLE DOESN'T EXIST");
            }

            Object w = im.newTemp(type);
            String name2 = "$" + im.getCount();

            InterReg reg = new InterReg(w, im.getCount(), im.getOpCode().getArray(), w.getClass().getSimpleName(), name2);
            im.insertReg(reg);


            im.genQuad(im.getOpCode().getArray(), finalS, numb, "$" + im.getCount());
            im.insertPlaceHelper((finalS + numb), "$" + im.getCount());
            name = finalS;
        }

        String type;
        String str = node.getR().toString();
        List myString = new ArrayList(Arrays.asList(str.split(" ")));

        String reg = name;
        if (initialName.contains("[")) {
            reg = "$" + (im.getCount() - 1);
        }


        if (node.getR().getClass().getSimpleName().equals("AFuncAllExpr")) {

            //im.insertPlaceHelper(myString.get(0).toString(),reg);

            im.genQuad(im.getOpCode().getAssignment(), myString.get(0), null, reg);
        } else if (node.getR().getClass().getSimpleName().equals("AAddSubAllExpr")) {

            //im.insertPlaceHelper("$" + (im.getCount() - 1),reg);

            im.genQuad(im.getOpCode().getAssignment(), "$" + (im.getCount() - 1), null, reg);

        } else if (node.getR().getClass().getSimpleName().equals("ARestSignsAllExpr")) {

            //im.insertPlaceHelper("$" + (im.getCount() - 1),reg);

            im.genQuad(im.getOpCode().getAssignment(), "$" + (im.getCount() - 1), null, reg);

        } else if (node.getR().toString().contains("[")) {
            String tempString = node.getR().toString().trim();

            List tempList = new ArrayList(Arrays.asList(tempString.split(" ")));

            String numb = "";
            int i = 0;
            String finalS = "";

            while (i < tempString.length()) {
                char c = tempString.charAt(i);
                if (c == '[') {
                    while (i < tempString.length()) {
                        i++;
                        c = tempString.charAt(i);
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

            if (numb.contains("+") || numb.contains("-") || numb.contains("*") || numb.contains("/") || numb.contains("mod")) {
                Object w = im.newTemp("Integer");
                String name2 = "$" + im.getCount();


                String myReg = im.Place(numb);
                try {
                    if (myReg == null) {
                        throw new MyException("FOUND VARIABLE NOT DECLARED");
                    }
                } catch (MyException e) {
                    throw new IllegalStateException("FOUND VARIABLE NOT DECLARED");
                }

                im.insertPlaceHelper(tempString, "$" + im.getCount());

                //im.insertPlaceHelper("$" + (im.getCount() - 1),name);



                im.genQuad(im.getOpCode().getArray(), tempList.get(0), myReg, name2);
                im.genQuad(im.getOpCode().getAssignment(), "$" + (im.getCount() - 1), null, name);
            } else {
                String tempString2 = tempString;
                while (tempString2.contains("[") || tempString2.contains("]")) {
                    tempString2 = tempString2.replace("[", "");
                    tempString2 = tempString2.replace("]", "");
                }
                List tempList2 = new ArrayList(Arrays.asList(tempString2.split("\\s+")));


                Object w = im.newTemp("Integer");
                String name2 = "$" + im.getCount();

                //im.insertPlaceHelper("$" + (im.getCount() - 1),name);




                im.genQuad(im.getOpCode().getArray(), tempList.get(0), tempList2.get(tempList2.size() - 1), name2);

                im.insertPlaceHelper(tempList.get(0).toString() + tempList2.get(tempList2.size() - 1),name2 );

                im.genQuad(im.getOpCode().getAssignment(), "$" + (im.getCount() - 1), null, name);
            }
        }
    }

}