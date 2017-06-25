package compiler;

/**
 * Created by anton_000 on 25/6/2017.
 */
public class VarLocal {
    private String name;
    private int storeCounter;

    public VarLocal(String name, int storeCounter){
        this.name = name;
        this.storeCounter = storeCounter;
    }

    public String getName() {
        return name;
    }

    public int getStoreCounter() {
        return storeCounter;
    }

    public int getSCOfVariable(String name){
        if (this.name.equals(name))
            return this.storeCounter;
        return -1; //not found
    }
}
