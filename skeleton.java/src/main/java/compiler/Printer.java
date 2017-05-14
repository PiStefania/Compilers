package compiler;

import compiler.analysis.DepthFirstAdapter;
import compiler.node.*;
import java.util.ArrayList;
import java.util.List;

public class Printer extends DepthFirstAdapter {

    private int indent = 0;         //gia white_space

    @Override
    public void inAProgram(AProgram node) {
        for (int i = 0; i < indent; i++)
            System.out.print(" ");
        indent++;
        System.out.println("([Program]");   //arxh tou programmatos
    }

    @Override
    public void outAProgram(AProgram node) {
        indent--;
        for (int i = 0; i < indent; i++)
            System.out.print(" ");
        System.out.println(")");        //telos tou programmatos
    }


    @Override
    public void inAFuncDef(AFuncDef node) {
        for (int i = 0; i < indent; i++)
            System.out.print(" ");
        indent++;
        System.out.println("([Function Definition]");
    }

    @Override
    public void outAFuncDef(AFuncDef node) {
        indent--;
        for (int i = 0; i < indent; i++)
            System.out.print(" ");
        System.out.println(")");
    }


    @Override
    public void inAWithHeaderHelperBiggerHeader(AWithHeaderHelperBiggerHeader node) {
        for (int i = 0; i < indent; i++)
            System.out.print(" ");
        indent++;
        System.out.print("([Header] " + node.getFun().toString() + "[id] " + node.getVarName().toString() + node.getLPar().toString() );
    }


    @Override
    public void outAWithHeaderHelperBiggerHeader(AWithHeaderHelperBiggerHeader node) {
        indent--;
        System.out.println(")");
    }

    @Override
    public void inAHeaderHelper2(AHeaderHelper2 node) {
        System.out.print(node.getRPar().toString() + node.getColon().toString());
    }

    @Override
    public void inAWithoutHeaderHelperBiggerHeader(AWithoutHeaderHelperBiggerHeader node) {
        for (int i = 0; i < indent; i++)
            System.out.print(" ");
        indent++;
        System.out.print("([Header] " + node.getFun().toString() + "[id] " + node.getVarName().toString() + node.getLPar().toString());
    }

    @Override
    public void outAWithoutHeaderHelperBiggerHeader(AWithoutHeaderHelperBiggerHeader node) {
        indent--;
        System.out.println(")");
    }

    @Override
    public void inAHeaderHelper(AHeaderHelper node) {
        System.out.print(node.getSemicolon().toString());
    }

    @Override
    public void inAHeaderHelperBigger(AHeaderHelperBigger node) {
    }


    @Override
    public void inABlockBlock(ABlockBlock node) {
        System.out.println();
        for (int i = 0; i < indent; i++)
            System.out.print(" ");
        indent++;
        System.out.println("([Block] " );

        for (int i = 0; i < indent; i++)
            System.out.print(" ");
        System.out.println(node.getCurlyLBr().toString() );
        indent++;
    }

    @Override
    public void outABlockBlock(ABlockBlock node) {
        System.out.println();
        indent--;
        for (int i = 0; i < indent; i++)
            System.out.print(" ");
        System.out.println(node.getCurlyRBr().toString());
        indent--;
        for (int i = 0; i < indent; i++)
            System.out.print(" ");
        System.out.println(")");
    }

    @Override
    public void inACharDataType(ACharDataType node) {
        System.out.print(/*"([Data Type] " + */  node.getChar().toString());
    }

    @Override
    public void outACharDataType(ACharDataType node) {
        System.out.print(")");
    }

    @Override
    public void inAIntDataType(AIntDataType node) {
        System.out.print(/*"([Data Type] " + */ node.getInt().toString());
    }

    @Override
    public void outAIntDataType(AIntDataType node) {
        System.out.print(")");
    }


    @Override
    public void inADtRetType(ADtRetType node) {
        System.out.print("([Return Data Type] ");
    }

    @Override
    public void outADtRetType(ADtRetType node) {
        System.out.print(")");
    }

    @Override
    public void inANothingRetType(ANothingRetType node) {
        System.out.print("([Return Data Type] " + node.getNothing());
    }

    @Override
    public void outANothingRetType(ANothingRetType node) {
        System.out.print(")");
    }

    @Override
    public void inAWithRefFparDef(AWithRefFparDef node) {
        System.out.print("([Function Parameters Definition] " + node.getRef().toString() + "[id] " + node.getVarName().toString());
    }

    @Override
    public void outAWithRefFparDef(AWithRefFparDef node) {
        System.out.print(")");
    }

    @Override
    public void inANoRefFparDef(ANoRefFparDef node) {
        System.out.print("([Function Parameters Definition] " + "[id] " + node.getVarName().toString());
    }

    @Override
    public void outANoRefFparDef(ANoRefFparDef node) {
        System.out.print(")");
    }

    @Override
    public void inADtType(ADtType node) {
        System.out.print("([Type] ");
    }

    @Override
    public void outADtType(ADtType node) {
        System.out.print(")");
    }

    @Override
    public void inAArType(AArType node) {
        System.out.print("([Type] " + node.getArray().toString());
    }

    @Override
    public void outAArType(AArType node) {
        System.out.print(")");
    }

    @Override
    public void inATypeHelper(ATypeHelper node) {
        System.out.println(node.getLBr().toString() + node.getConstant().toString() + node.getRBr().toString());
    }


    @Override
    public void inAVarDef(AVarDef node) {
        for (int i = 0; i < indent; i++)
            System.out.print(" ");
        indent++;
        System.out.print("([Variable Definition] " + node.getVar().toString() + "[id] " + node.getVarName());
    }

    @Override
    public void inAColonId(AColonId node) {
        System.out.print(node.getColon().toString());
    }


    @Override
    public void outAVarDef(AVarDef node) {
        indent--;
        System.out.println(node.getSemicolon().toString() + ")");
    }

    @Override
    public void inAVarDefHelper(AVarDefHelper node) {
        System.out.print(node.getComma().toString() + "[id] " + node.getVarName());
    }

    @Override
    public void inAFuncDecl(AFuncDecl node) {
        for (int i = 0; i < indent; i++)
            System.out.print(" ");
        indent++;
        System.out.println("([Function Declaration]");
    }

    @Override
    public void outAFuncDecl(AFuncDecl node) {
        indent--;
        for (int i = 0; i < indent; i++)
            System.out.print(" ");
        System.out.println(")");
    }

    @Override
    public void inAFuncDefLocalDef(AFuncDefLocalDef node) {
        for (int i = 0; i < indent; i++)
            System.out.print(" ");
        indent++;
        System.out.println("([Local Definition]");
    }

    @Override
    public void outAFuncDefLocalDef(AFuncDefLocalDef node) {
        indent--;
        for (int i = 0; i < indent; i++)
            System.out.print(" ");
        System.out.println(")");
    }

    @Override
    public void inAFuncDeclLocalDef(AFuncDeclLocalDef node) {
        for (int i = 0; i < indent; i++)
            System.out.print(" ");
        indent++;
        System.out.println("([Local Definition]");
    }

    @Override
    public void outAFuncDeclLocalDef(AFuncDeclLocalDef node) {
        indent--;
        for (int i = 0; i < indent; i++)
            System.out.print(" ");
        System.out.println(")");
    }

    @Override
    public void inAVarDefLocalDef(AVarDefLocalDef node) {
        for (int i = 0; i < indent; i++)
            System.out.print(" ");
        indent++;
        System.out.println("([Local Definition]");
    }

    @Override
    public void outAVarDefLocalDef(AVarDefLocalDef node) {
        indent--;
        for (int i = 0; i < indent; i++)
            System.out.print(" ");
        System.out.println(")");
    }

    @Override
    public void inADtWithHelperFparType(ADtWithHelperFparType node) {
        System.out.print("([Function Parameter Type] " );
    }

    @Override
    public void outADtWithHelperFparType(ADtWithHelperFparType node) {
        System.out.print(")");
    }

    @Override
    public void inADtWithoutHelperFparType(ADtWithoutHelperFparType node) {
        System.out.print("([Function Parameter Type] ");
    }

    @Override
    public void outADtWithoutHelperFparType(ADtWithoutHelperFparType node) {
        System.out.print(")");
    }

    @Override
    public void inAArrFparType(AArrFparType node) {
        System.out.print("[Function Parameter Type] " + node.getArray().toString());
    }

    @Override
    public void outAArrFparType(AArrFparType node) {
        System.out.print(")");
    }

    @Override
    public void inAWithBrsFparTypeHelper2(AWithBrsFparTypeHelper2 node) {
        System.out.println(node.getBrs().toString());
    }

    @Override
    public void inAWithoutBrsFparTypeHelper2(AWithoutBrsFparTypeHelper2 node) {
    }


    @Override
    public void inAFparTypeHelper(AFparTypeHelper node) {
        System.out.println(node.getLBr().toString() + node.getConstant().toString() + node.getRBr().toString());
    }

    @Override
    public void inAVarLValue(AVarLValue node) {
        System.out.print("([L-Value] " + "[id] " + node.getVarName().toString());
    }

    @Override
    public void outAVarLValue(AVarLValue node) {
        System.out.print(") ");
    }

    @Override
    public void inAConstantStringLValue(AConstantStringLValue node) {
        System.out.print("([L-Value] " + "[Constant String]: " + node.getConstantString().toString());
    }
    @Override
    public void outAConstantStringLValue(AConstantStringLValue node) {
        System.out.print(") ");
    }

    @Override
    public void inALValueLValue(ALValueLValue node) {
        System.out.print("([L-Value] ");
    }

    @Override
    public void outALValueLValue(ALValueLValue node) {
        System.out.print(") ");
    }

    @Override
    public void inAWithParFuncCall(AWithParFuncCall node)
    {
        System.out.print( "([Function Call]" + "[id] " + node.getVarName().toString() + node.getLPar().toString() );
    }

    @Override
    public void outAWithParFuncCall(AWithParFuncCall node)
    {
        System.out.print( node.getRPar().toString() + ")" );
    }

    @Override
    public void inAWithoutParFuncCall(AWithoutParFuncCall node)
    {
        System.out.print( "([Function Call]" + "[id] " + node.getVarName().toString() + node.getLPar().toString() + node.getRPar().toString() );
    }

    @Override
    public void outAWithoutParFuncCall(AWithoutParFuncCall node)
    {
        System.out.print( node.getRPar().toString() + ")" );
    }

    @Override
    public void inAParametersParameters(AParametersParameters node) {
    }


    @Override
    public void inAParameterParameter(AParameterParameter node) {
        System.out.print(node.getComma().toString());
    }

    @Override
    public void inAExprcalcAllExpr(AExprcalcAllExpr node) {
        System.out.print("([Expression] ");
    }

    @Override
    public void outAExprcalcAllExpr(AExprcalcAllExpr node) {
        System.out.print(") ");
    }

    @Override
    public void inAGenAllExpr(AGenAllExpr node) {
        System.out.print("([Expression]");
    }

    @Override
    public void outAGenAllExpr(AGenAllExpr node) {
        System.out.print(") ");
    }


    @Override
    public void inAParexprGeneralExpr(AParexprGeneralExpr node) {
    }

    @Override
    public void inAPlusPlusMinus(APlusPlusMinus node) {
        System.out.print("[Plus] " + node.getPlus().toString());
    }

    @Override
    public void outAPlusPlusMinus(APlusPlusMinus node) {
    }

    @Override
    public void inAMinusPlusMinus(AMinusPlusMinus node) {
        System.out.print("[Minus] " + node.getMinus().toString() );
    }

    @Override
    public void outAMinusPlusMinus(AMinusPlusMinus node) {
    }

    @Override
    public void inAMultRestSigns(AMultRestSigns node) {
        System.out.print("[Mult] " + node.getMult().toString());
    }

    @Override
    public void outAMultRestSigns(AMultRestSigns node) {
    }


    @Override
    public void inADivRestSigns(ADivRestSigns node) {
        System.out.print("" + node.getDiv().toString() );
    }

    @Override
    public void outADivRestSigns(ADivRestSigns node) {
    }

    @Override
    public void inADiv1RestSigns(ADiv1RestSigns node) {
        System.out.print("[Div] " + node.getDivSpace().toString() );
    }

    @Override
    public void outADiv1RestSigns(ADiv1RestSigns node) {
    }

    @Override
    public void inAModRestSigns(AModRestSigns node) {
        System.out.print("[Mod] " + node.getModSpace().toString());
    }

    @Override
    public void outAModRestSigns(AModRestSigns node) {
    }

    @Override
    public void inAParexprParenthesesExpr(AParexprParenthesesExpr node) {
        System.out.print(node.getLPar().toString());
    }

    @Override
    public void outAParexprParenthesesExpr(AParexprParenthesesExpr node) {
        System.out.print(node.getRPar().toString());
    }


    @Override
    public void inAPlusPlusMinus2(APlusPlusMinus2 node) {
        System.out.print("( " +node.getPlus());

    }

    @Override
    public void inAMinusPlusMinus2(AMinusPlusMinus2 node) {
        System.out.print("( " +node.getMinus());

    }


    @Override
    public void inALetterFactor(ALetterFactor node) {
        System.out.print("[Constant character]: "+ node.getConstantCharacter().toString() );
    }

    @Override
    public void inAExprCalcFactor(AExprCalcFactor node) {
        System.out.print(node.getLPar().toString());
    }
    @Override
    public void outAExprCalcFactor(AExprCalcFactor node) {
        System.out.print(node.getRPar().toString());
    }

    @Override
    public void inAConstantFactor(AConstantFactor node) {
        System.out.print(node.getConstant().toString());
    }

    @Override
    public void inARestSignsTerm(ARestSignsTerm node) {
        System.out.print("(");
    }

    @Override
    public void outARestSignsTerm(ARestSignsTerm node) {
        System.out.print(")");
    }


    @Override
    public void inAAddSubExprCalc(AAddSubExprCalc node) {
        System.out.print("(");
    }

    @Override
    public void outAAddSubExprCalc(AAddSubExprCalc node) {
        System.out.print(")");
    }

    @Override
    public void inANotTermCond1(ANotTermCond1 node) {
        System.out.print("(");
    }

    @Override
    public void outANotTermCond1(ANotTermCond1 node) {
        System.out.print(")");
    }

    @Override
    public void inAAndTermCond2(AAndTermCond2 node) {
        System.out.print("(");
    }

    @Override
    public void outAAndTermCond2(AAndTermCond2 node) {
        System.out.print(")");
    }

    @Override
    public void inAOrCondExpr(AOrCondExpr node) {
        System.out.print("(");
    }

    @Override
    public void outAOrCondExpr(AOrCondExpr node) {
        System.out.print(")");
    }

    @Override
    public void inAEqualAssSigns(AEqualAssSigns node) {
        System.out.print("[Equal] " + node.getEqual().toString());
    }

    @Override
    public void inANeAssSigns(ANeAssSigns node) {
        System.out.print("[Not Equal] " + node.getNotEqual().toString());
    }
    @Override
    public void inASmallAssSigns(ASmallAssSigns node) {
        System.out.print("[Smaller] " + node.getSmaller().toString());
    }
    @Override
    public void inAGreatAssSigns(AGreatAssSigns node) {
        System.out.print("[Greater] " + node.getGreater().toString());
    }
    @Override
    public void inASmalleAssSigns(ASmalleAssSigns node) {
        System.out.print("[Smaller or Equal] " + node.getSmallerEq().toString());
    }
    @Override
    public void inAGreateAssSigns(AGreateAssSigns node) {
        System.out.print("[Greater or Equal] " + node.getGreaterEq().toString());
    }

    @Override
    public void inALogicalAnd(ALogicalAnd node) {
        System.out.print("[Logical] " + node.getAnd().toString());
    }
    @Override
    public void inALogicalOr(ALogicalOr node) {
        System.out.print("[Logical] " + node.getOr().toString());
    }
    @Override
    public void inALogicalNot(ALogicalNot node) {
        System.out.print("[Logical] " + node.getNot().toString());
    }


    @Override
    public void inASemicolonStmt(ASemicolonStmt node)
    {
        indent++;
        System.out.print("([Statement] " + node.getSemicolon().toString());
    }

    @Override
    public void outASemicolonStmt(ASemicolonStmt node)
    {
        indent--;
        System.out.println(")");
    }

    @Override
    public void inAExpressionStmt(AExpressionStmt node)
    {
        for(int i=0; i< indent; i++)
            System.out.print(" ");
        indent++;
        System.out.print("([Statement] ");
    }

    @Override
    public void outAExpressionStmt(AExpressionStmt node)
    {
        indent--;
        System.out.println(")");
    }

    @Override
    public void inABlockStmt(ABlockStmt node)
    {
        System.out.println();
        for(int i=0; i< indent; i++)
            System.out.print(" ");
        indent++;
        System.out.print("([Statement] ");
    }

    @Override
    public void outABlockStmt(ABlockStmt node)
    {
        indent--;
        for(int i=0; i< indent; i++)
            System.out.print(" ");
        System.out.println(")");
    }

    @Override
    public void inAFuncallStmt(AFuncallStmt node)
    {
        for(int i=0; i< indent; i++)
            System.out.print(" ");
        indent++;
        System.out.print("([Statement] ");
    }

    @Override
    public void outAFuncallStmt(AFuncallStmt node)
    {
        indent--;
        System.out.println(")");
    }

    @Override
    public void inAIfStmt(AIfStmt node)
    {
        for(int i=0; i< indent; i++)
            System.out.print(" ");
        indent++;
        System.out.print("([Statement] ");
    }

    @Override
    public void outAIfStmt(AIfStmt node)
    {
        indent--;
        for(int i=0; i< indent; i++)
            System.out.print(" ");
        System.out.println(")");
    }

    @Override
    public void inAWhileStmt(AWhileStmt node)
    {
        for(int i=0; i< indent; i++)
            System.out.print(" ");
        indent++;
        System.out.print("([Statement] " + node.getWhile().toString() );
    }

    @Override
    public void outAWhileStmt(AWhileStmt node)
    {
        indent--;
        for(int i=0; i< indent; i++)
            System.out.print(" ");
        System.out.println(")");
    }

    @Override
    public void inAReturnWithExprStmt(AReturnWithExprStmt node)
    {
        for(int i=0; i< indent; i++)
            System.out.print(" ");
        indent++;
        System.out.print("([Statement] " + node.getReturn().toString()   );
    }

    @Override
    public void outAReturnWithExprStmt(AReturnWithExprStmt node)
    {
        indent--;
        System.out.print(")");
    }

    @Override
    public void inAReturnExprStmt(AReturnExprStmt node)
    {

        indent++;
        System.out.print("([Statement] " + node.getReturn().toString() );
    }

    @Override
    public void outAReturnExprStmt(AReturnExprStmt node)
    {
        indent--;
        System.out.print(")");
    }

    @Override
    public void inAReturnStmt(AReturnStmt node)
    {
        indent++;
        System.out.print("([Statement] " + node.getReturn().toString() + node.getSemicolon().toString());
    }

    @Override
    public void outAReturnStmt(AReturnStmt node)
    {
        indent--;
        System.out.print(")");
    }

    @Override
    public void inANoElseIfStmt(ANoElseIfStmt node)
    {
        System.out.print( node.getIf());
    }

    @Override
    public void outANoElseIfStmt(ANoElseIfStmt node)
    {
    }

    @Override
    public void inAWithElseIfStmt(AWithElseIfStmt node)
    {
        System.out.print( node.getIf());
    }

    @Override
    public void outAWithElseIfStmt(AWithElseIfStmt node)
    {
    }


    @Override
    public void inASemicolonStmtWithElse(ASemicolonStmtWithElse node)
    {
        indent++;
        System.out.print("([Statement] " + node.getSemicolon().toString());
    }

    @Override
    public void outASemicolonStmtWithElse(ASemicolonStmtWithElse node)
    {
        indent--;
        System.out.print(")");
    }

    @Override
    public void inAExpressionStmtWithElse(AExpressionStmtWithElse node)
    {
        for(int i=0; i< indent; i++)
            System.out.print(" ");
        indent++;
        System.out.print("([Statement] ");
    }

    @Override
    public void outAExpressionStmtWithElse(AExpressionStmtWithElse node)
    {
        indent--;
        System.out.println(")");
    }

    @Override
    public void inABlockStmtWithElse(ABlockStmtWithElse node)
    {
        for(int i=0; i< indent; i++)
            System.out.print(" ");
        indent++;
        System.out.print("([Statement] ");
    }

    @Override
    public void outABlockStmtWithElse(ABlockStmtWithElse node)
    {
        indent--;
        System.out.println(")");
    }

    @Override
    public void inAFuncallStmtWithElse(AFuncallStmtWithElse node)
    {
        for(int i=0; i< indent; i++)
            System.out.print(" ");
        indent++;
        System.out.print("([Statement] ");
    }

    @Override
    public void outAFuncallStmtWithElse(AFuncallStmtWithElse node)
    {
        indent--;
        System.out.println(")");
    }

    @Override
    public void inAIfElseStmtWithElse(AIfElseStmtWithElse node)
    {
        for(int i=0; i< indent; i++)
            System.out.print(" ");
        indent++;
        System.out.print("([Statement] ");
    }

    @Override
    public void outAIfElseStmtWithElse(AIfElseStmtWithElse node)
    {
        indent--;
        System.out.println(")");
    }

    @Override
    public void inAWhileStmtWithElse(AWhileStmtWithElse node)
    {
        for(int i=0; i< indent; i++)
            System.out.print(" ");
        indent++;
        System.out.print("([Statement] " + node.getWhile().toString() );
    }

    @Override
    public void outAWhileStmtWithElse(AWhileStmtWithElse node)
    {
        indent--;
        System.out.println(")");
    }

    @Override
    public void inAReturnWithExprStmtWithElse(AReturnWithExprStmtWithElse node)
    {
        indent++;
        System.out.print("([Statement] " + node.getReturn().toString()   );
    }

    @Override
    public void outAReturnWithExprStmtWithElse(AReturnWithExprStmtWithElse node)
    {
        indent--;
        System.out.print(")");
    }

    @Override
    public void inAReturnExprStmtWithElse(AReturnExprStmtWithElse node)
    {
        indent++;
        System.out.print("([Statement] " + node.getReturn().toString() );
    }

    @Override
    public void outAReturnExprStmtWithElse(AReturnExprStmtWithElse node)
    {
        indent--;
        System.out.print(")");
    }

    @Override
    public void inAReturnStmtWithElse(AReturnStmtWithElse node)
    {
        indent++;
        System.out.print("([Statement] " + node.getReturn().toString() + node.getSemicolon().toString());
    }

    @Override
    public void outAReturnStmtWithElse(AReturnStmtWithElse node)
    {
        indent--;
        System.out.print(")");
    }


    @Override
    public void inAIfElseStmt(AIfElseStmt node)
    {
        System.out.print(node.getIf().toString());
    }

    @Override
    public void outAIfElseStmt(AIfElseStmt node)
    {
        System.out.println();
    }

    @Override
    public void inASemicolonId(ASemicolonId node)
    {
        System.out.print( node.getSemicolon().toString());
    }

    @Override
    public void inADoId(ADoId node)
    {
        System.out.print(node.getDo().toString());
    }

    @Override
    public void inAThenId(AThenId node)
    {
        System.out.print(node.getThen().toString());
    }

    @Override
    public void inAElseId(AElseId node)
    {

        for (int i=0; i< indent; i++)
            System.out.print(" ");
        System.out.print(node.getElse().toString());
    }

    @Override
    public void outAElseId(AElseId node)
    {
    }

    @Override
    public void inAAssignId(AAssignId node)
    {
        System.out.print("[Assignment] " + node.getAssign().toString());
    }

    @Override
    public void inALbrId(ALbrId node)
    {
        System.out.print(node.getLBr().toString());
    }

    @Override
    public void inARbrId(ARbrId node)
    {
        System.out.print(node.getRBr().toString());
    }

    @Override
    public void inAParFactorCond(AParFactorCond node)
    {
        System.out.print( node.getLPar().toString());
    }

    @Override
    public void outAParFactorCond(AParFactorCond node)
    {
        System.out.print( node.getRPar().toString());
    }


}