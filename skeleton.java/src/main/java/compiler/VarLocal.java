package compiler;

/**
 * Created by anton_000 on 25/6/2017.
 */
public class VarLocal {
    private String name;
    private int storeCounter;
    private String value;

    public VarLocal(String name, int storeCounter, String value){
        this.name = name;
        this.storeCounter = storeCounter;
        this.value = value;
    }


    public VarLocal(String name, int storeCounter){
        this.name = name;
        this.storeCounter = storeCounter;
        this.value = null;
    }

    public String getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public int getStoreCounter() {
        return storeCounter;
    }

}
