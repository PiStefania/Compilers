package compiler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FuncScope {

    private String funcName;                //name of function
    private Map<String,List> parameters;    //parameters of function(in map formation)
    private String type;                    //type of function
    private int numOfParams;                //number of parameters

    public FuncScope(String funcName,Map parameters,String type,int numOfParams){     //constructor
        this.funcName = funcName;
        this.parameters = new HashMap<String, List>(parameters);
        this.type = type;
        this.numOfParams = numOfParams;
    }


    public Map<String, List> getParameters() {      //get map of parameters
        return parameters;
    }

    public String getFuncName() {                   //get function name
        return funcName;
    }

    public String getType() {
        return type;
    }

    public int getNumOfParams() {
        return numOfParams;
    }

    public void setFuncName(String funcName) {
        this.funcName = funcName;
    }

    public void setParameters(Map<String, List> parameters) {
        this.parameters = parameters;
    }


    public void setType(String type) {
        this.type = type;
    }
}
