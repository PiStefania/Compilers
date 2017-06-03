package compiler;

/**
 * Created by stefa on 3/6/2017.
 */
public class InterReg {

    private Object w;
    private int tag;
    private String call;

    public InterReg(Object w,int tag,String call){
        this.w = w;
        this.tag = tag;
        this.call = call;
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
