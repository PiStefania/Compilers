package compiler;

/**
 * Created by stefa on 24/5/2017.
 */
class ScopeObject {
    private String name;
    private String type;

    public ScopeObject(String name, String type){
        this.name = name;
        this.type = type;


        //System.out.println("CONSTRUCTPOR: " + this.name + this.type);
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public boolean sameObject(String name, String type){
        if (this.getName().equals(name) && this.getType().equals(type))
            return true;
        return false;
    }
}
