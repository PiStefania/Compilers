package compiler;

import compiler.analysis.DepthFirstAdapter;
import compiler.node.*;
import java.util.ArrayList;
import java.util.List;
import java.util.*;


public class PrinterAST extends DepthFirstAdapter{

    int indentation = 0;
    private void addIndentationLevel() {
        indentation++;
    }

    private void removeIndentationLevel() {
        indentation--;
    }

    private void printIndentation() {
        // create a list with n copies
        List list = Collections.nCopies(indentation, " ");

        // create an iterator
        Iterator itr = list.iterator();

        while (itr.hasNext()){
            System.out.print(itr.next());
        }
    }

    @Override
    public void inAProgram(AProgram node)
    {
        System.out.println("(Starting our Program:");
    }

    @Override
    public void outAProgram(AProgram node)
    {
        System.out.println(")");
    }

    @Override
    public void inAAllFuncDef(AAllFuncDef node)
    {
        addIndentationLevel();
        printIndentation();
        System.out.println("(Function Definition:");
    }

    @Override
    public void outAAllFuncDef(AAllFuncDef node)
    {
        printIndentation();
        System.out.println(")" );
        removeIndentationLevel();
    }

    @Override
    public void inAFparDefFuncDef(AFparDefFuncDef node)
    {
        addIndentationLevel();
        printIndentation();
        System.out.println("(AAAAAAAAFPar Definition");
    }

    @Override
    public void outAFparDefFuncDef(AFparDefFuncDef node)
    {
        printIndentation();
        System.out.println("AAAAAAAAA)");
        removeIndentationLevel();
    }

    @Override
    public void inAHeaderBigFuncDef(AHeaderBigFuncDef node)
    {
        addIndentationLevel();
        printIndentation();
        System.out.println("(AAAAAAAAFPar Definition" + node.getL());
    }

   /* @Override
    public void inAHeaderFuncDef(AHeaderFuncDef node)
    {
        addIndentationLevel();
        printIndentation();
        System.out.println("(Header Definition");
    }

    @Override
    public void outAHeaderFuncDef(AHeaderFuncDef node)
    {
        printIndentation();
        System.out.println(")" );
        removeIndentationLevel();
    }*/

    @Override
    public void inAVarDefFuncDef(AVarDefFuncDef node)
    {
        addIndentationLevel();
        printIndentation();
        System.out.println("(Variable Definition");
    }

    @Override
    public void outAVarDefFuncDef(AVarDefFuncDef node)
    {
        printIndentation();
        System.out.println(")" );
        removeIndentationLevel();
    }

    @Override
    public void inAHead(AHead node)
    {
        addIndentationLevel();
        printIndentation();
        System.out.print("(Function Name: " + node.getL().toString() + node.getR().toString());
    }

    @Override
    public void outAHead(AHead node)
    {
        printIndentation();
        System.out.println(")");
        removeIndentationLevel();
    }


    @Override
    public void inAFpar(AFpar node)
    {
        addIndentationLevel();
        printIndentation();
        System.out.println("(Parameter: " + node.getL().toString() + node.getR().toString());
    }

    @Override
    public void outAFpar(AFpar node)
    {
        printIndentation();
        System.out.println(")");
        removeIndentationLevel();
    }

    @Override
    public void inAVar1(AVar1 node)
    {
        addIndentationLevel();
        printIndentation();
        System.out.print("(Variable: " + node.getL().toString() + node.getR().toString());
    }

    @Override
    public void outAVar1(AVar1 node)
    {
        printIndentation();
        System.out.println(")");
        removeIndentationLevel();
    }

    @Override
    public void inABlockFuncDef(ABlockFuncDef node)
    {
        addIndentationLevel();
        printIndentation();
        System.out.println("(Block: " + node.getL().toString() + node.getR().toString());
    }

    @Override
    public void outABlockFuncDef(ABlockFuncDef node)
    {
        printIndentation();
        System.out.println(")");
        removeIndentationLevel();
    }

    @Override
    public void inAVarHelpFuncDef(AVarHelpFuncDef node)
    {
        addIndentationLevel();
        printIndentation();
        System.out.print("(Variable Name: " + node.getVarName().toString());
    }

    @Override
    public void outAVarHelpFuncDef(AVarHelpFuncDef node)
    {
        printIndentation();
        System.out.println(")");
        removeIndentationLevel();
    }

    @Override
    public void inAIntFuncDef(AIntFuncDef node)
    {
        addIndentationLevel();
        printIndentation();
        System.out.print("(Type: " + node.getInt().toString());
    }

    @Override
    public void outAIntFuncDef(AIntFuncDef node)
    {
        printIndentation();
        System.out.println(")");
        removeIndentationLevel();
    }

    @Override
    public void inACharFuncDef(ACharFuncDef node)
    {
        addIndentationLevel();
        printIndentation();
        System.out.print("(Type: " + node.getChar().toString());
    }

    @Override
    public void outACharFuncDef(ACharFuncDef node)
    {
        printIndentation();
        System.out.println(")");
        removeIndentationLevel();
    }

    @Override
    public void inAConstantFuncDef(AConstantFuncDef node)
    {
        addIndentationLevel();
        printIndentation();
        System.out.print("(Constant: " + node.getConstant().toString());
    }

    @Override
    public void outAConstantFuncDef(AConstantFuncDef node)
    {
        printIndentation();
        System.out.println(")");
        removeIndentationLevel();
    }

    @Override
    public void inANothingFuncDef(ANothingFuncDef node)
    {
        addIndentationLevel();
        printIndentation();
        System.out.print("(Type: " + node.getNothing().toString());
    }

    @Override
    public void outANothingFuncDef(ANothingFuncDef node)
    {
        printIndentation();
        System.out.println(")");
        removeIndentationLevel();
    }

    @Override
    public void inAArrayFuncDef(AArrayFuncDef node)
    {
        addIndentationLevel();
        printIndentation();
        System.out.print("(Array: " + node.getArray().toString());
    }

    @Override
    public void outAArrayFuncDef(AArrayFuncDef node)
    {
        printIndentation();
        System.out.println(")");
        removeIndentationLevel();
    }


}
