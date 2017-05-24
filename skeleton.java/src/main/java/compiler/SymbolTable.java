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

        position++;
        System.out.println("ENTER");
        map.put(position,mystack.size());
        mystack.push(obj);

    }

    public void insert(ScopeObject obj){

        System.out.println("INSERTTTTTT: "+position);

        map.put(position,mystack.size());

        mystack.push(obj);

    }

    public boolean lookup(ScopeObject obj){


        for (int i=0;i<mystack.size();i++){
            ScopeObject obj2 = (ScopeObject) mystack.get(i);

            if (obj.sameObject(obj2.getName(), obj2.getType())){
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
