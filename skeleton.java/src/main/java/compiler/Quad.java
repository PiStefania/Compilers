package compiler;

/**
 * Created by anton_000 on 30/5/2017.
 */
public class Quad {

    private Object op;
    private Object arg1;
    private Object arg2;
    private Object arg3;


    public Quad(Object op, Object arg1, Object arg2, Object arg3) {
        this.op=op;
        this.arg1=arg1;
        this.arg2=arg2;
        this.arg3=arg3;
    }

    public boolean oneElement(Object x){
        int counter=0;
        boolean flag=false;

        if (this.op==null)
            counter++;
        else if (this.op.equals(x))
            flag=true;

        if (this.arg1==null)
            counter++;
        else if (this.arg1.equals(x))
            flag=true;

        if (this.arg2==null)
            counter++;
        else if (this.arg2.equals(x))
            flag=true;

        if (this.arg3==null)
            counter++;
        else if (this.arg3.equals(x))
            flag=true;

        if(counter==3 && flag == true)
            return true;
        return false;
    }

    public Object getOp() {
        return op;
    }

    public Object getArg1() {
        return arg1;
    }

    public Object getArg2() {
        return arg2;
    }

    public Object getArg3() {
        return arg3;
    }

    public void setOp(Object op) {
        this.op = op;
    }

    public void setArg1(Object arg1) {
        this.arg1 = arg1;
    }

    public void setArg2(Object arg2) {
        this.arg2 = arg2;
    }

    public void setArg3(Object arg3) {
        this.arg3 = arg3;
    }


    public boolean relopTrueFalse(){

        operator relop = (operator) this.op;
        String operator = relop.getValue();
        Integer x = (Integer) this.arg1;
        Integer y = (Integer) this.arg2;

        if(operator.equals("<")) {
            if (x < y)
                return true;
            return false;
        }
        else if(operator.equals(">")) {
            if (x > y)
                return true;
            return false;
        }
        else if(operator.equals("=")) {
            if (x == y)
                return true;
            return false;
        }
        else if(operator.equals("#")) {
            if (x != y)
                return true;
            return false;
        }
        else if(operator.equals("<=")) {
            if (x <= y)
                return true;
            return false;
        }
        else {                //operator.equals(">=")
            if (x >= y)
                return true;
            return false;
        }


    }

}
