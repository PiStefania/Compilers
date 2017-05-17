package compiler;

import compiler.analysis.DepthFirstAdapter;
import compiler.node.*;
import java.util.ArrayList;
import java.util.List;


public class PrinterAST extends DepthFirstAdapter{

    @Override
    public void inAProgram(AProgram node)
    {
        System.out.println("Starting our Program:");
    }

    @Override
    public void outAProgram(AProgram node)
    {
        System.out.println("Ending our Program");
    }

    @Override
    public void inAAllFuncDef(AAllFuncDef node)
    {
        System.out.println("AAllFuncDef: Left: " + node.getL().toString() + " Right: "  + node.getR().toString() );
    }

    @Override
    public void outAAllFuncDef(AAllFuncDef node)
    {
        System.out.println("Ending AAllFuncDef" );
    }


}
