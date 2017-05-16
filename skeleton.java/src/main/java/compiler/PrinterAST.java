package compiler;

import compiler.analysis.DepthFirstAdapter;
import compiler.node.*;
import compiler.analysis.*;
import java.util.ArrayList;
import java.util.List;


public class PrinterAST extends AnalysisAdapter{

    private double value = 0;

    @Override
    public void caseStart(Start node) {
        node.getPProgram().apply(this);
    }

    @Override
    public void caseAAllFuncDef(AAllFuncDef node) {
        node.getL().apply(this);
        double lhs = value;

        node.getR().apply(this);
        double rhs = value;

        value = lhs * rhs;
        System.out.println(value);
    }



}
