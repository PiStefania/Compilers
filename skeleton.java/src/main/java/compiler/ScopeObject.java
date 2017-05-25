package compiler;

/**
 * Created by stefa on 24/5/2017.
 */
class ScopeObject {
    private String name;
    private String type;
    private String genre;

    public ScopeObject(String name, String type,String genre){
        this.name = name;
        this.type = type;
        this.genre = genre;


        //System.out.println("CONSTRUCTPOR: " + this.name + this.type);
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getGenre() {
        return genre;
    }

    public boolean sameObject(String name){

        System.out.println("insidde samme");

        //System.out.println("nameobj:" + this.getName()+ "!");
        //System.out.println("nammeeeee: " + name.trim()+ "!");
        if (this.getName().equals(name) ) {
            System.out.println("insidde if");
            return true;
        }
        return false;
    }

    public boolean sameObjectFunc(String name, String genre){
        if (this.getName().equals(name) && genre.equals("func"))
            return true;
        return false;
    }
}
