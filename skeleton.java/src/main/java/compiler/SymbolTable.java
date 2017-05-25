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




    public void enter(ScopeObject obj){

        try{
            if(lookupFunc(obj)){
                //System.err.println("FOUND SAME VARIABLE");
                throw new MyException("FOUND SAME FUNCTION");
            }else{
                position++;
                System.out.println("ENTER");
                map.put(position,mystack.size());
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

        try{
            if(lookupVar(obj)){
                //System.err.println("FOUND SAME VARIABLE");
                throw new MyException("FOUND SAME VARIABLE");
            }else{
                map.put(position,mystack.size());
                mystack.push(obj);
            }

            if(lookupVarAndFunc(obj)){
                //System.err.println("FOUND SAME VARIABLE AND FUNC");
                throw new MyException("FOUND SAME VARIABLE AND FUNC");
            }else{
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
                System.out.println("NAME2 " + obj2.getName());
                System.out.println("NAME " + obj.getName());
                System.out.println("fsfs");
                System.out.println(i);
                if (obj.sameObject(obj2.getName())){
                    System.out.println("ffffffffffffffffff");
                    return true;
                }

            }
            return false;
        }
        else{
            for(int i=value;i>=0;i--){
                ScopeObject obj2 = (ScopeObject) mystack.get(i);
                System.out.println("NAME2 " + obj2.getName());
                System.out.println("NAME " + obj.getName());
                System.out.println("fsfs");
                System.out.println(i);
                if (obj.sameObject(obj2.getName())){
                    System.out.println("ffffffffffffffffff");
                    return true;
                }
            }
            return false;
        }
    }

    public boolean lookupFunc(ScopeObject obj){

        System.out.println("ONJ: "+ obj.getName()+"!");

        for(int i = mystack.size()-1;i>=0;i--){
            System.out.println("ITEM: "+ i);
            ScopeObject obj2 = (ScopeObject) mystack.get(i);

            if (obj.sameObject(obj2.getName()) ){
                return true;
            }
        }
        return false;

    }

    public boolean lookupVarAndFunc(ScopeObject obj){

        System.out.println("ONJ: "+ obj.getName()+"!");

        for(int i = mystack.size()-1;i>=0;i--){
            System.out.println("ITEM: "+ i);
            ScopeObject obj2 = (ScopeObject) mystack.get(i);

            if (obj.sameObjectFunc(obj2.getName(),obj2.getGenre()) ){
                return true;
            }
        }
        return false;

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
