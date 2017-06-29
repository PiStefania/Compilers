package compiler;


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
