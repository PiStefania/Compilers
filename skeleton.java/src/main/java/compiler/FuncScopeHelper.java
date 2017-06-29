package compiler;


public class FuncScopeHelper {
    private String funcName;                //name of function
    private int scope;                      //scope of function

    public FuncScopeHelper(String name, int scope){
        this.funcName = name;
        this.scope = scope;
    }

    public String getFuncName() {
        return funcName;
    }

    public int getScope() {
        return scope;
    }


}
