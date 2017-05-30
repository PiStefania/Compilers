package compiler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anton_000 on 30/5/2017.
 */
import compiler.Quad;
public class Intermediate {

    private int count;
    List<Quad> quadList;

    public Intermediate(){
        this.count=0;
        this.quadList = new ArrayList<Quad>();
    }

    public void genQuad(Object op, Object arg1, Object arg2, Object arg3) {
        String forPrint ="";
        if (op != null)
            forPrint += " " + op.toString();
        if (arg1 != null)
            forPrint += " " +arg1.toString();
        if (arg2 != null)
            forPrint += " " +arg2.toString();
        if (arg3 != null)
            forPrint += " " +arg3.toString();

        System.out.println(count + ":" +forPrint);
        count++;
        Quad myquad = new Quad(op,arg1,arg2,arg3);
        this.quadList.add(myquad);

    }

    public int nextQuad() {
        return count;
    }

    public <t> void newTemp(Object t){
        t myTemp;
    }



}
