package compiler;

import sun.reflect.generics.scope.Scope;

import java.util.*;
import java.util.List;
import java.util.LinkedList;
import java.util.Scanner;


class SymbolTable {

    private Map<Integer, Integer> map;           //hashmap
    private Stack<ScopeObject> mystack;         //stack of obj:ScopeObject
    private Stack<FuncScope> funcStack;         //stack of obj:FuncScope
    private Map<String, List> refPar;
    private int position;                       //position of map
    private int ifWhileScope;                   //scope of ifs and whiles

    public SymbolTable() {   //constructor
        map = new HashMap<Integer, Integer>();
        mystack = new Stack<ScopeObject>();
        funcStack = new Stack<FuncScope>();
        refPar = new HashMap<String, List>();
        position = -1;
        ifWhileScope = -1;
    }

    public int getIfWhileScope() {
        return ifWhileScope;
    }

    public void setIfWhileScope(int ifWhileScope) {
        this.ifWhileScope = ifWhileScope;
    }

    public void print() {
        System.out.println("Printing Stack");
        for (int i = 0; i < this.mystack.size(); i++) {
            System.out.println(this.mystack.get(i).getName());
        }
    }

    public void printFunc() {
        System.out.println("Printing FuncStack");
        for (int i = 0; i < this.funcStack.size(); i++) {
            System.out.println(this.funcStack.get(i).getFuncName());
        }
    }


    public void enter(ScopeObject obj) {             //enter obj to new scope

        try {
            if (lookupFunc(obj)) {
                throw new MyException("FUNCTION WAS NOT FOUND");
            } else if (lookupDecl(obj)) {
                throw new MyException("FOUND DIFFERENT TYPE OF FUNCTIONS");
            } else {
                position++;
                map.put(position, mystack.size());
                mystack.push(obj);
            }
        } catch (MyException e) {
            throw new IllegalStateException("FUNCTION ERROR");
        }

    }

    public void insert(ScopeObject obj) {               //insert object to stack in same scope

        if (obj.getGenre().equals("par")) {
            obj = this.findDecl(obj);
        }

        try {
            if (lookupVar(obj)) {
                throw new MyException("FOUND SAME VARIABLE");
            } else if (lookupVarAndFunc(obj)) {
                throw new MyException("FOUND SAME VARIABLE AND FUNC");
            } else if (lookupPar(obj)) {
                throw new MyException("FOUND SAME PARAMETER AND FUNC");
            } else {
                map.put(position, mystack.size());
                mystack.push(obj);
            }
        } catch (MyException e) {
            throw new IllegalStateException("VARIABLE, PARAMETER OR FUNCTION WAS DECLARED MORE THAN ONCE");
        }


    }

    public boolean lookupVar(ScopeObject obj) {              //search for same vars in same scope

        int value = map.get(position);
        int value2 = 0;
        if (position != 0) {
            value2 = map.get(position - 1);
            for (int i = value; i > value2; i--) {
                ScopeObject obj2 = (ScopeObject) mystack.get(i);
                if (obj.sameObject(obj2.getName(), obj2.getGenre())) {
                    return true;
                }
            }
            return false;
        } else {
            for (int i = value; i >= 0; i--) {
                ScopeObject obj2 = (ScopeObject) mystack.get(i);
                if (obj.sameObject(obj2.getName(), obj2.getGenre())) {
                    return true;
                }
            }
            return false;
        }
    }

    public boolean findReturnType(String returnType) {
        ScopeObject obj = null;
        for (int i = mystack.size() - 1; i >= 0; i--) {
            obj = (ScopeObject) mystack.get(i);
            if (obj.getGenre().equals("func")) {
                break;
            }
        }
        if (obj != null){
            String type = obj.getType();
            if (returnType.equals(type))
                return true;

        }
        return false;
    }




    public boolean lookupPar(ScopeObject obj){      //search for same parameters inside function decl
        if(!obj.getGenre().equals("par_decl"))
        {
            return false;
        }
        int i = mystack.size()-1;
        while (i>=0) {
            ScopeObject obj2 = (ScopeObject) mystack.get(i);
            if (!obj2.getGenre().equals("decl") && !obj2.getGenre().equals("par_decl")){
                break;
            }
            if(obj.sameObjectPar(obj2.getName(),obj2.getGenre()))
                return true;
            i--;
        }
        return false;

    }

    public boolean lookupDecl(ScopeObject obj){             //search for same func and decl names

        int i = mystack.size()-1;
        while (i>=0) {
            ScopeObject obj2 = (ScopeObject) mystack.get(i);
            if(obj.getName().equals(obj2.getName()) && obj2.getGenre().equals("decl")) {
                if(!obj.getType().equals(obj2.getType()))
                    return true;
            }
            i--;
        }
        return false;
    }

    public boolean lookupDeclName(String name){             //search for same func and decl names

        int i = mystack.size()-1;
        while (i>=0) {
            ScopeObject obj = (ScopeObject) mystack.get(i);
            if(obj.getName().equals(name) && obj.getGenre().equals("decl")) {
                return true;
            }
            i--;
        }
        return false;
    }




    public boolean lookupFunc(ScopeObject obj){             //search for same func in all stack

        for(int i = mystack.size()-1;i>=0;i--){

            ScopeObject obj2 = (ScopeObject) mystack.get(i);
            if (obj.sameObject(obj2.getName(),obj2.getGenre()) ){
                return true;
            }
        }
        return false;

    }

    public boolean lookupVarAndFunc(ScopeObject obj){               //search for same function and variable names in all stack

        int value = map.get(position);
        int value2 = 0;
        if (position != 0) {
            value2 = map.get(position - 1);
            for (int i = value; i > value2; i--) {
                ScopeObject obj2 = (ScopeObject) mystack.get(i);
                if (obj.getName().equals(obj2.getName())) {
                    return true;
                }
            }
            return false;
        } else {
            for (int i = value; i >= 0; i--) {
                ScopeObject obj2 = (ScopeObject) mystack.get(i);
                if (obj.getName().equals(obj2.getName())) {
                    return true;
                }
            }
            return false;
        }

    }

    public boolean lookupVarAndType(ScopeObject obj){                   //search variable in order to find the same variable in stack with diff type(true)

        int value = map.get(position);



        for(int i=value;i>=0;i--){
            ScopeObject obj2 = (ScopeObject) mystack.get(i);
            if (obj.getName().trim().equals(obj2.getName().trim())){
                if (!obj.getType().equals(obj2.getType())){
                    return true;
                }
                else return false;
            }
        }
        return true;  //not found
    }



    public boolean lookupVarAndTypeOnlyForVariables(ScopeObject obj){                   //search variable in order to find the same variable in stack with diff type(true)

        int value = map.get(position);


        for(int i=value;i>=0;i--){
            ScopeObject obj2 = (ScopeObject) mystack.get(i);
            if (obj.getName().trim().equals(obj2.getName().trim())){
                if(obj2.getType().contains("char[") && obj2.getType().contains("]")){
                    if(obj.getType().contains("char[") && obj.getType().contains("]")){
                        return false;
                    }
                    if(obj.getType().equals("char")){
                        return false;
                    }
                }
                if(obj.getType().contains("char[") && obj.getType().contains("]")){
                    if(obj2.getType().contains("char[") && obj2.getType().contains("]")){
                        return false;
                    }
                    if(obj2.getType().equals("char")){
                        return false;
                    }
                }
                if(obj2.getType().contains("int[") && obj2.getType().contains("]")){
                    if(obj.getType().contains("int[") && obj.getType().contains("]")){
                        return false;
                    }
                    if(obj.getType().equals("int")){
                        return false;
                    }
                }
                if(obj.getType().contains("int[") && obj.getType().contains("]")){
                    if(obj2.getType().contains("int[") && obj2.getType().contains("]")){
                        return false;
                    }
                    if(obj2.getType().equals("int")){
                        return false;
                    }
                }
                if (!obj.getType().equals(obj2.getType())){
                    return true;
                }
                else return false;
            }
        }
        return true;  //not found
    }


    public boolean checkDeclaration(String name){           //return true if there are more than one declarations for function in same scope
        int value = map.get(position);
        int value2 = 0;
        if (position != 0) {
            value2 = map.get(position - 1);
            for (int i = value; i > value2; i--) {
                ScopeObject obj = (ScopeObject) mystack.get(i);
                if (name.equals(obj.getName())) {
                    if (obj.getGenre().equals("decl"))
                        return true;
                }
            }
        }
        else {
            for (int i = value; i >= 0; i--) {
                ScopeObject obj = (ScopeObject) mystack.get(i);
                if (name.equals(obj.getName())) {
                    if (obj.getGenre().equals("decl"))
                        return true;
                }
            }

        }
        return false;
    }

    public boolean checkFuncDeclExistance(){
        List<ScopeObject> declarations = new ArrayList<ScopeObject>();
        for(int i=0;i<mystack.size();i++){                              //get funcs and decls
            if(mystack.get(i).getGenre().equals("decl")){
                declarations.add(mystack.get(i));
            }
        }

        ScopeObject objDecl;
        FuncScope objFunc;
        int counterDecl = 0;


        for(int i=0;i<declarations.size();i++){                         //check for every decl if func exists
            objDecl = declarations.get(i);
            for(int j=0;j<funcStack.size();j++){
                objFunc = funcStack.get(j);
                if(objDecl.getName().equals(objFunc.getFuncName())){            //same name
                    if(objDecl.getScope() <= objFunc.getScope()){
                        counterDecl++;
                    }
                }
            }
        }


        if(counterDecl ==  declarations.size())
        {
            return true;
        }
        return false;
    }




    public String FindVariableType(String name){    //returns type of variable

        int value = map.get(position);
        for(int i=value;i>=0;i--){
            ScopeObject obj = (ScopeObject) mystack.get(i);
            if (obj.getName().equals(name)){
                return obj.getType();
            }
        }
        return null;  //not found
    }



    public int FindVariablePosition(String name){       //find variable position inside hashmap

        int value = map.get(position);
        int i;
        for(i=value;i>=0;i--){
            ScopeObject obj = (ScopeObject) mystack.get(i);
            if (obj.getName().equals(name)){
                break;
            }
        }

        Map<Integer, Integer> myNewHashMap = new HashMap();
        for(Map.Entry<Integer, Integer> entry : map.entrySet()){
            myNewHashMap.put(entry.getValue(), entry.getKey());
        }

        while (i<mystack.size()){
            if (map.values().contains(i)){
                return myNewHashMap.get(i);
            }
            i++;
        }

        return -1;  //not found
    }

    public int FindVarPosition(String name){        //find variable position inside stack


        int value = map.get(position);
        int i;
        for(i=value;i>=0;i--){
            ScopeObject obj = (ScopeObject) mystack.get(i);
            if (obj.getName().equals(name)){
                return i;
            }
        }

        return -1;  //not found
    }



    public ScopeObject findDecl(ScopeObject obj){               //search and set parameter of decl as "par_decl" and return it

        ScopeObject obj2 = (ScopeObject) mystack.get(mystack.size()-1);
        if(obj2.getGenre().equals("decl")){
            obj.setGenre("par_decl");

        }
        else if(obj2.getGenre().equals("par_decl")){
            obj.setGenre("par_decl");
        }
        return obj;
    }



    public void exit(){             //remove scopes

        int value = map.get(position);
        int value2 = 0;
        if (position!=0){
            value2  = map.get(position-1);
        }
        for(int i=value;i>value2;i--){
            mystack.pop();
        }
        map.remove(position);
        position--;

    }

    public void insertFuncStack(FuncScope funcObj){      //insert funcScope obj to stack

        funcStack.push(funcObj);
    }


    public boolean checkScopeWithout(String name){

        String funcName;
        int scopeFunc;
        int numParams;



        boolean declFound = lookupDeclName(name);
        if(declFound){

        }


        for (int i=0; i<this.funcStack.size();i++){

            funcName = this.funcStack.get(i).getFuncName();
            numParams = this.funcStack.get(i).getNumOfParams();





            System.out.println("BRE8HKE " + funcName + name);
            if(name.equals(funcName)){
                System.out.println("prin t scopes " + this.funcStack.get(i).getScope() + " tora:" + this.position);
                if(this.funcStack.get(i).getScope() - this.position <=1)
                {
                    if(numParams == 0) {
                        return true;
                    }
                    else return false;
                }
            }
        }
        return false;
    }



    public boolean checkScopeWith(String name,List<String> par){

        String funcName;
        int numParams;
        List<String> typeOfParams = new ArrayList<String>();
        for (int i=0; i<this.funcStack.size();i++){

            funcName = this.funcStack.get(i).getFuncName();
            numParams = this.funcStack.get(i).getNumOfParams();
            typeOfParams = this.funcStack.get(i).getTypesOfPatameters();
            //System.out.println(funcName);

            if(name.equals(funcName)){
                System.out.println("BRE8HKE " + funcName);
                System.out.println("prin t scopes " + this.funcStack.get(i).getScope() + " tora:" + this.position);

                if(this.funcStack.get(i).getScope() - this.position <=1) {
                    System.out.println("KOMPLE SCOPE");

                    if (numParams == par.size()) {
                        for (int j = 0; j < par.size(); j++) {
                            if (par.get(j).contains("[")) {

                                int counter = 0;
                                char c;
                                int counterDim = 0;
                                String typeOfArray = "";
                                while (counter < par.get(j).length()) {
                                    c = par.get(j).charAt(counter);
                                    typeOfArray += c;
                                    if (c == '[') {
                                        while (c != ']') {
                                            counter++;
                                            c = par.get(j).charAt(counter);

                                        }
                                        typeOfArray += "]";
                                        counterDim++;
                                    }
                                    counter++;
                                }


                                String fixedType = typeOfArray;
                                par.set(j, fixedType);
                            }
                        }

                        System.out.println("PAR TYPES: " +  par);
                        System.out.println("typeOfParams TYPES: " +  typeOfParams);
                        int flag = 0;
                        for (int j=0; j < par.size();j++) {
                            if (par.get(j).contains("char[") && typeOfParams.get(j).contains("char["))
                                flag ++;
                            else if (par.get(j).contains("int[") && typeOfParams.get(j).contains("int["))
                                flag++;
                            else if (par.get(j).equals(typeOfParams.get(j))) {
                                flag++;
                            }
                        }
                        if(flag == par.size()){
                            return true;
                        }

                    }
                    return false;
                }
                return false;
            }
        }
        return false;
    }








    public boolean isNumeric(String s) {
        return s != null && s.matches("[-+]?\\d*\\.?\\d+");
    }







    public String getFuncType(String name){

        String funcName;
        for (int i=0; i<this.funcStack.size();i++){

            funcName = this.funcStack.get(i).getFuncName();

            //System.out.println("FUNCNAMES: " + funcName);

            if(name.equals(funcName)){
                return this.funcStack.get(i).getType();
            }
        }
        return null;
    }

    public List<Integer> getFuncRefs(String name){

        String funcName;
        for (int i=0; i<this.funcStack.size();i++){

            funcName = this.funcStack.get(i).getFuncName();

            if(name.equals(funcName)){
                return this.funcStack.get(i).getrefVars();
            }
        }
        return null;
    }




    public void deleteFuncStack(){
        this.funcStack.clear();
    }


    public int getPosition() {
        return position;
    }                   //get position

    public Map<Integer, Integer> getMap() {
        return map;
    }           //get hashmap

    public Stack<ScopeObject> getMystack() {
        return mystack;
    }      //get stack


    public void insertRefList(String funcName,List<String> refList){
        refPar.put(funcName,refList);
    }

    public Map<String, List> getRefPar() {
        return refPar;
    }

    public Stack<FuncScope> getFuncStack() {
        return funcStack;
    }
}

