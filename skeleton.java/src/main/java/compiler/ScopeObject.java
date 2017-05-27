package compiler;

/**
 * Created by stefa on 24/5/2017.
 */
class ScopeObject {
    private String name;
    private String type;
    private String genre;
    private int noOfParams;  //-1 if not func

    public ScopeObject(String name, String type,String genre ,int noOfParams){
        this.name = name;
        this.type = type;
        this.genre = genre;
        this.noOfParams = noOfParams;


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











    public boolean sameObject(String name, String genre){

        System.out.println("insidde samme");

        //System.out.println("nameobj:" + this.getName()+ "!");
        //System.out.println("nammeeeee: " + name.trim()+ "!");
        System.out.println("GENRE: " + genre+ "!");
        if (this.getName().equals(name) && !genre.equals("decl") && !genre.equals("par_decl") ) {
            System.out.println("insidde if");
            return true;
        }
        return false;
    }


    public boolean sameObjectPar(String name, String genre){

        System.out.println("insidde samme");

        //System.out.println("nameobj:" + this.getName()+ "!");
        //System.out.println("nammeeeee: " + name.trim()+ "!");
        System.out.println("GENRE PARAMETER: " + genre+ "!");

        if (this.getName().equals(name) && this.getGenre().equals(genre)  ) {
            System.out.println("insidde if");
            System.out.println("GENRE PARAMETER true 1111: " + genre+ "!");
            return true;
        }
        else if(this.getName().equals(name) && genre.equals("decl"))
        {
            System.out.println("GENRE PARAMETER true 2222: " + genre+ "!");
            return true;
        }

        return false;
    }



    public boolean sameObjectFunc(String name, String genre){
        if (this.getName().equals(name) && genre.equals("func") && !this.getGenre().equals("decl") )
            return true;
        return false;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
