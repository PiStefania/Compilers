package compiler;


public class InterReg {

    private Object w;
    private int tag;
    private String call;
    private String type;
    private String name;

    public InterReg(Object w,int tag,String call,String type, String name){
        this.w = w;
        this.tag = tag;
        this.call = call;
        this.type = type;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getW() {
        return w;
    }

    public int getTag() {
        return tag;
    }

    public String getCall() {
        return call;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setW(Object w) {
        this.w = w;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    public void setCall(String call) {
        this.call = call;
    }


}
