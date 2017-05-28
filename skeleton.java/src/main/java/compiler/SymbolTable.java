package compiler;

import java.util.*;



/**
 * Created by stefa on 23/5/2017.
 */
class SymbolTable {

    private Map<Integer,Integer> map;
    private Stack<ScopeObject> mystack;
    private int position;

    public SymbolTable(){   //constructor
            map = new HashMap<Integer,Integer>();
            mystack = new Stack<ScopeObject>();
        position = -1;
    }

    public void print(){
        System.out.println("printing stack");
        for (int i=0; i<this.mystack.size();i++){
            System.out.println(this.mystack.get(i).getName());
        }
        System.out.println("end of printing stack");
    }


    public void enter(ScopeObject obj){

        try{
            if(lookupFunc(obj)) {
                System.err.println("asdsadsadasda");
                throw new MyException("FOUND SAME FUNCTION");
            } else if (lookupDecl(obj)) {
                System.err.println("FOUND DIFFERENT TYPE");
                throw new MyException("FOUND DIFFERENT TYPE");
            } else {
                position++;
                System.out.println("ENTER");
                map.put(position, mystack.size());
                mystack.push(obj);
            }
        }catch (MyException e){
            //System.err.println("FOUND SAME VARIABLE");
            //throw new AssertionError();
            //throw new RuntimeException(e); //should not happen
            throw new IllegalStateException("FOUND SAME FUNCTION");
        }

    }

    public void insert(ScopeObject obj) {

        System.out.println("INSERTTTTTT: "+position);

        if (obj.getGenre().equals("par")) {
            obj = this.findDecl(obj);
        }

        try{
            if(lookupVar(obj)){
                //System.err.println("FOUND SAME VARIABLE");
                throw new MyException("FOUND SAME VARIABLE");
            }
            else if (lookupVarAndFunc(obj)){
                //System.err.println("FOUND SAME VARIABLE AND FUNC");
                throw new MyException("FOUND SAME VARIABLE AND FUNC");
            }
            else if(lookupPar(obj)){
                throw new MyException("FOUND SAME PARAMETER AND FUNC");
            }
            else{
                map.put(position,mystack.size());

                mystack.push(obj);
            }
        }catch (MyException e){
            //System.err.println("FOUND SAME VARIABLE");
            //throw new AssertionError();
            //throw new RuntimeException(e); //should not happen
            throw new IllegalStateException("FOUND SAME VARIABLE");
        }


    }

    public boolean lookupVar(ScopeObject obj){


        System.out.println("LOOKUP");
        System.out.println("kk");
        int value = map.get(position);
        int value2 = 0;
        System.out.println("a");
        if (position!=0){
            value2  = map.get(position-1);
            for(int i=value;i>value2;i--){
                ScopeObject obj2 = (ScopeObject) mystack.get(i);
                //System.out.println("NAME2 " + obj2.getName());
                //System.out.println("NAME " + obj.getName());
                //System.out.println("fsfs");
                //System.out.println(i);
                if (obj.sameObject(obj2.getName(),obj2.getGenre())){
                    //System.out.println("ffffffffffffffffff");
                    return true;
                }

            }
            return false;
        }
        else{
            for(int i=value;i>=0;i--){
                ScopeObject obj2 = (ScopeObject) mystack.get(i);
                /*System.out.println("NAME2 " + obj2.getName());
                System.out.println("NAME " + obj.getName());
                System.out.println("fsfs");
                System.out.println(i);*/
                if (obj.sameObject(obj2.getName(),obj2.getGenre())){
                    //System.out.println("ffffffffffffffffff");
                    return true;
                }
            }
            return false;
        }
    }


    public boolean lookupPar(ScopeObject obj){
        System.out.println("PARAAAAAAAAAAAAAAAA");
        if(!obj.getGenre().equals("par_decl"))
        {
            System.out.println("PTOTO IF");
            return false;
        }

        System.out.println("ONJ: "+ obj.getName()+"!");
        int i = mystack.size()-1;
        while (i>=0) {
            System.out.println(i);
            ScopeObject obj2 = (ScopeObject) mystack.get(i);
            System.out.println(obj2.getGenre());
            if (!obj2.getGenre().equals("decl") && !obj2.getGenre().equals("par_decl")){
                System.out.println("TEUTERO");
                break;
            }
            if(obj.sameObjectPar(obj2.getName(),obj2.getGenre()))
                return true;
            i--;
        }
        System.out.println("TRITO");
        return false;

    }

    public boolean lookupDecl(ScopeObject obj){
        System.out.println("PARAAAAAAAAAAAAAAAA");


        System.out.println("ONJ: "+ obj.getName()+"!");
        int i = mystack.size()-1;
        while (i>=0) {
            System.out.println(i);
            ScopeObject obj2 = (ScopeObject) mystack.get(i);
            System.out.println(obj2.getGenre());
            if(obj.getName().equals(obj2.getName()) && obj2.getGenre().equals("decl")) {

                if(!obj.getType().equals(obj2.getType()))
                    return true;
            }
            i--;
        }
        System.out.println("TRITO");
        return false;

    }




    public boolean lookupFunc(ScopeObject obj){

        System.out.println("ONJ: "+ obj.getName()+"!");

        for(int i = mystack.size()-1;i>=0;i--){
            //System.out.println("ITEM: "+ i);
            ScopeObject obj2 = (ScopeObject) mystack.get(i);

            if (obj.sameObject(obj2.getName(),obj2.getGenre()) ){
                return true;
            }
        }
        return false;

    }

    public boolean lookupVarAndFunc(ScopeObject obj){

        System.out.println("ONJ: "+ obj.getName()+"!");

        for(int i = mystack.size()-1;i>=0;i--){
           // System.out.println("ITEM: "+ i);
            ScopeObject obj2 = (ScopeObject) mystack.get(i);

            if (obj.sameObjectFunc(obj2.getName(),obj2.getGenre()) ){
                return true;
            }
        }
        return false;

    }



    public boolean lookupVarAndType(ScopeObject obj){


        System.out.println("lookupVarAndType");
        int value = map.get(position);
        int value2 = 0;
        System.out.println("a");
        if (position!=0){
            value2  = map.get(position-1);
            for(int i=value;i>value2;i--){
                ScopeObject obj2 = (ScopeObject) mystack.get(i);

                if (obj.getName().equals(obj2.getName())){
                    if (!obj.getType().equals(obj2.getType())){
                        System.out.println("error1");
                        return true;            //diaforetikoi tupoi
                    }
                    else return false;
                }

            }
            System.out.println("error2");
            return true;    //den to vrhke
        }
        else{
            for(int i=value;i>=0;i--){
                System.out.println(i);
                ScopeObject obj2 = (ScopeObject) mystack.get(i);
                System.out.println("obj "+obj.getName() + obj.getType());
                System.out.println("obj2 "+obj2.getName() + obj2.getType());
                if (obj.getName().trim().equals(obj2.getName().trim())){
                    System.out.println("whaa");
                    if (!obj.getType().equals(obj2.getType())){
                        System.out.println("error3");
                        return true;
                    }
                    else return false;
                }
            }
            System.out.println("error4");
            return true;  //den to vrhke
        }
    }





    public String FindVariableType(String name){


        System.out.println("FindVariableType");
        System.out.println(name);
        int value = map.get(position);
        int value2 = 0;
        System.out.println("a");
        if (position!=0){
            value2  = map.get(position-1);
            for(int i=value;i>value2;i--){
                ScopeObject obj = (ScopeObject) mystack.get(i);

                if (obj.getName().equals(name)){
                    System.out.println("found");
                    return obj.getType();
                }

            }
            return null;    //den to vrhke
        }
        else{
            for(int i=value;i>=0;i--){
                ScopeObject obj = (ScopeObject) mystack.get(i);

                if (obj.getName().equals(name)){
                    System.out.println("found");
                    return obj.getType();
                }
            }
            return null;  //den to vrhke
        }
    }




















    public ScopeObject findDecl(ScopeObject obj){

        System.out.println("ONJ: "+ obj.getName()+"!");

        // System.out.println("ITEM: "+ i);
        ScopeObject obj2 = (ScopeObject) mystack.get(mystack.size()-1);
        if(obj2.getGenre().equals("decl")){
            obj.setGenre("par_decl");

        }
        else if(obj2.getGenre().equals("par_decl")){
            obj.setGenre("par_decl");
        }

        return obj;

    }



    public void exit(){

        System.out.println("kk");
        int value = map.get(position);
        int value2 = 0;
        System.out.println("a");
        if (position!=0){
            value2  = map.get(position-1);
        }
        System.out.println("b");
        for(int i=value;i>value2;i--){
            mystack.pop();
        }
        System.out.println("POSIIIIITIONNNN Before remove: "+position);
        map.remove(position);

        position--;
        System.out.println("POSIIIIITIONNNN: "+position);

    }

    public int getPosition() {
        return position;
    }

    public Map<Integer, Integer> getMap() {
        return map;
    }

    public Stack<ScopeObject> getMystack() {
        return mystack;
    }
}
