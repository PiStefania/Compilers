package compiler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by stefa on 28/5/2017.
 */
public class FuncScope {

    private String funcName;                //name of function
    private int scope;                      //scope of function
    private Map<String,List> parameters;    //parameters of function(in map formation)

    public FuncScope(String funcName,int scope,Map parameters){     //constructor
        this.funcName = funcName;
        this.scope = scope;
        this.parameters = parameters;
    }

    public int getScope() {         //get scope
        return scope;
    }

    public Map<String, List> getParameters() {      //get map of parameters
        return parameters;
    }

    public String getFuncName() {                   //get function name
        return funcName;
    }
}
