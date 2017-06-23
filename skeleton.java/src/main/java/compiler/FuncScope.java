package compiler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FuncScope {

    private String funcName;                //name of function
    private Map<String,List> parameters;    //parameters of function(in map formation)
    private String type;                    //type of function
    private int numOfParams;                //number of parameters
    private int scope;                      //scope of function
    private List<String> typesOfPatameters;
    private List<Integer> refVars;

    public FuncScope(String funcName,Map parameters,String type,int numOfParams,int scope, List<String> types,List<Integer> refs){     //constructor
        this.funcName = funcName;
        this.parameters = new HashMap<String, List>(parameters);
        this.type = type;
        this.numOfParams = numOfParams;
        this.scope = scope;
        this.typesOfPatameters = new ArrayList<String>(types);
        this.refVars = new ArrayList<Integer>(refs);
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

    public int getScope() {
        return scope;
    }

    public void settypesOfPatameters(List<String> typesOfPatameters) {
        this.typesOfPatameters = typesOfPatameters;
    }

    public List<String> getTypesOfPatameters() {
        return typesOfPatameters;
    }

    public List<Integer> getrefVars() {
        return refVars;
    }

    public void setScope(int scope) {
        this.scope = scope;
    }

    public void setNumOfParams(int numOfParams) {
        this.numOfParams = numOfParams;
    }

    public void setrefVars(List<Integer> positionRefVars) {
        this.refVars = positionRefVars;
    }

    public void setTypesOfPatameters(List<String> typesOfPatameters) {
        this.typesOfPatameters = typesOfPatameters;
    }
}
