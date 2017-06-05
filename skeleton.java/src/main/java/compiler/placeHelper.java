package compiler;

/**
 * Created by stefa on 5/6/2017.
 */
public class placeHelper {
    private String expr;
    private String position;

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
