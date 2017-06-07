package compiler;


public class placeHelper {
    private String expr;
    private String position;            //register/variable

    public placeHelper(String expr,String position){
        this.expr = expr;
        this.position = position;
    }

    public String getExpr() {
        return expr;
    }

    public String getPosition() {
        return position;
    }
}
