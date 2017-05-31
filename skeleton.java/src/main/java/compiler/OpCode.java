package compiler;

/**
 * Created by anton_000 on 31/5/2017.
 */

class operator{
    private String name;
    private String value;
    public operator(String name, String value){
        this.name=name;
        this.value=value;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
}

public class OpCode {
    private String unit;
    private String endu;
    private operator op;
    private String assignment;
    private String array;
    private operator relop;
    private String ifb;
    private String jump;
    private String label;
    private String jumpl;
    private String call;
    private String par;
    private String ret;

    public OpCode(){
        this.unit="unit";
        this.endu="endu";
        //this.op="op";
        this.assignment=":=";
        this.array="array";
        //this.relop="relop";
        this.ifb="ifb";
        this.jump="jump";
        this.label="label";
        this.jumpl="jumpl";
        this.call="call";
        this.par="par";
        this.ret="ret";
    }

    public String getUnit() {return unit;}

    public String getEndu() {return endu;}

    public operator getOp() {return op;}

    public String getAssignment() {return assignment;}

    public String getArray() {return array;}

    public operator getRelop() {return relop;}

    public String getIfb() {return ifb;}

    public String getJump() {return jump;}

    public String getLabel() {return label;}

    public String getJumpl() {return jumpl;}

    public String getCall() {return call;}

    public String getPar() {return par;}

    public String getRet() {return ret;}
}
