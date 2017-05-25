package compiler;

import compiler.analysis.DepthFirstAdapter;
import compiler.node.*;
import java.util.ArrayList;
import java.util.List;
import java.util.*;
import compiler.SymbolTable;
import compiler.ScopeObject;


public class PrinterAST extends DepthFirstAdapter{

    SymbolTable table = new SymbolTable();

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

    //PROGRAM

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

    //FUNC_DEF

    @Override
    public void inAAllFuncDef(AAllFuncDef node)
    {
        addIndentationLevel();
        printIndentation();
        System.out.println("(Function Definition:");

    }

    @Override
    public void inAHeaderWithFuncDef(AHeaderWithFuncDef node)
    {
        addIndentationLevel();
        printIndentation();
        System.out.println("(Header:");
        System.out.println("aleaafaaaataaaaaaaaaaaaaaaaaaaaaa " +node.getL().toString() + " right "+ node.getR().toString());



        List<String> myList = new ArrayList<String>(Arrays.asList(node.getL().toString().split(" ")));
        System.out.println("LISTA"+myList);
        ScopeObject obj =   new ScopeObject(myList.get(0).trim(),node.getR().toString().trim(),"func") ;
        table.enter(obj);


        Set list = table.getMap().entrySet();
        System.out.println("MAPPINGS" + list);
    }

    @Override
    public void outAHeaderWithFuncDef(AHeaderWithFuncDef node)
    {
        printIndentation();
        System.out.println(")" );
        removeIndentationLevel();
/*
        table.exit();

        Set list = table.getMap().entrySet();
        System.out.println("MAPPINGSOUTTTTTT" + list);*/
    }


    @Override
    public void inAHeaderFuncDef(AHeaderFuncDef node)
    {
        addIndentationLevel();
        printIndentation();
        System.out.println("(Header: " + node.getL().toString());
        //System.out.println("aleaafaaaataaaaaaaaaaaaaaaaaaaaaa " +node.getL().toString() + " right "+ node.getR().toString());



        List<String> myList = new ArrayList<String>(Arrays.asList(node.getL().toString().split(" ")));
        System.out.println("LISTA"+myList);
        ScopeObject obj =   new ScopeObject(myList.get(0).trim(),node.getR().toString().trim(),"func") ;
        table.enter(obj);


        Set list = table.getMap().entrySet();
        System.out.println("MAPPINGS" + list);
    }

    @Override
    public void outAHeaderFuncDef(AHeaderFuncDef node)
    {
        printIndentation();
        System.out.println(")" );
        removeIndentationLevel();

        /*table.exit();

        Set list = table.getMap().entrySet();
        System.out.println("MAPPINGSOUTTTTTT" + list);*/
    }

    @Override
    public void outAAllFuncDef(AAllFuncDef node)
    {
        printIndentation();
        System.out.println(")" );
        removeIndentationLevel();
        table.exit();

        Set list = table.getMap().entrySet();
        System.out.println("MAPPINGSOUTTTTTT" + list);

    }

    @Override
    public void inAFparDefFuncDef(AFparDefFuncDef node)
    {
        addIndentationLevel();
        printIndentation();
        System.out.println("(FPar Definition ");
        System.out.println(" fsf?");

        String type = node.getR().toString();
        List<String> myList = new ArrayList<String>(Arrays.asList(node.getL().toString().split(" ")));
        System.out.print("MGKAS "+ myList);
        for(int i=0;i<myList.size();i++){
            ScopeObject obj =   new ScopeObject(myList.get(i).trim(),type.trim(),"par") ;
            table.insert(obj);
            Set list = table.getMap().entrySet();
            System.out.println("MAPPINGSsdsd" + list);
        }


    }

    @Override
    public void outAFparDefFuncDef(AFparDefFuncDef node)
    {
        printIndentation();
        System.out.println(")");
        removeIndentationLevel();
    }

    @Override
    public void inAFparDefNoRefFuncDef(AFparDefNoRefFuncDef node)
    {
        addIndentationLevel();
        printIndentation();
        System.out.println("(FPar Definition ");
        System.out.println(" fsf?");
        ScopeObject obj =   new ScopeObject(node.getL().toString().trim(),node.getR().toString().trim(),"par") ;
        System.out.println(" asas?");
        table.insert(obj);
        System.out.println(" whaaaaa?");

        Set list = table.getMap().entrySet();
        System.out.println("MAPPINGSsdsd" + list);
    }

    @Override
    public void outAFparDefNoRefFuncDef(AFparDefNoRefFuncDef node)
    {
        printIndentation();
        System.out.println(")");
        removeIndentationLevel();
    }


    @Override
    public void inAHeaderBigFuncDef(AHeaderBigFuncDef node)
    {
        addIndentationLevel();
        printIndentation();
        System.out.println("(FPar Definition " + node.getL().toString());
    }

    @Override
    public void outAHeaderBigFuncDef(AHeaderBigFuncDef node)
    {
        addIndentationLevel();
        printIndentation();
        System.out.println(")");
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
    public void inATypeFuncDef(ATypeFuncDef node)
    {
        addIndentationLevel();
        printIndentation();
        System.out.print("(Type: " );
    }

    @Override
    public void outATypeFuncDef(ATypeFuncDef node)
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

    @Override
    public void inAArray2FuncDef(AArray2FuncDef node)
    {
        addIndentationLevel();
        printIndentation();
        System.out.print("(Array: " + node.getArray().toString());
    }

    @Override
    public void outAArray2FuncDef(AArray2FuncDef node)
    {
        printIndentation();
        System.out.println(")");
        removeIndentationLevel();
    }

    @Override
    public void inAFparTypeFuncDef(AFparTypeFuncDef node)
    {
        addIndentationLevel();
        printIndentation();
        System.out.print("(Fpar type: ");
    }

    @Override
    public void outAFparTypeFuncDef(AFparTypeFuncDef node)
    {
        printIndentation();
        System.out.println(")");
        removeIndentationLevel();
    }

    @Override
    public void inAVarDefFuncDef(AVarDefFuncDef node)
    {
        addIndentationLevel();
        printIndentation();
        System.out.println("(Variable Definition fefe " + node.getL().toString() + " right "+node.getR().toString());



        String type = node.getR().toString();
        List<String> myList = new ArrayList<String>(Arrays.asList(node.getL().toString().split(" ")));
        System.out.print("MGKAS "+ myList);
        for(int i=0;i<myList.size();i++){
            ScopeObject obj =   new ScopeObject(myList.get(i).trim(),type.trim(),"var") ;
            table.insert(obj);
            Set list = table.getMap().entrySet();
            System.out.println("MAPPINGSsdsd" + list);
        }


    }

    @Override
    public void outAVarDefFuncDef(AVarDefFuncDef node)
    {
        printIndentation();
        System.out.println(")" );
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
        System.out.println(")" );
        removeIndentationLevel();
    }

    @Override
    public void inAFparTypeHelperFuncDef(AFparTypeHelperFuncDef node)
    {
        addIndentationLevel();
        printIndentation();
        System.out.print("(Fpar type helper:" + node.getConstant().toString());
    }

    @Override
    public void outAFparTypeHelperFuncDef(AFparTypeHelperFuncDef node)
    {
        printIndentation();
        System.out.println(")" );
        removeIndentationLevel();
    }

    @Override
    public void inAWithBrsFuncDef(AWithBrsFuncDef node)
    {
        addIndentationLevel();
        printIndentation();
        System.out.print("(With brs:" );
    }

    @Override
    public void outAWithBrsFuncDef(AWithBrsFuncDef node)
    {
        printIndentation();
        System.out.println(")" );
        removeIndentationLevel();
    }


    @Override
    public void inAWithoutBrsFuncDef(AWithoutBrsFuncDef node)
    {
        addIndentationLevel();
        printIndentation();
        System.out.print("(Without brs:" );
    }

    @Override
    public void outAWithoutBrsFuncDef(AWithoutBrsFuncDef node)
    {
        printIndentation();
        System.out.println(")" );
        removeIndentationLevel();
    }

    @Override
    public void inAFuncDeclFuncDef(AFuncDeclFuncDef node)
    {
        addIndentationLevel();
        printIndentation();

        System.out.println("FUNCTION DECLARATION" + node.getFuncDef().toString() );
    }

    @Override
    public void outAFuncDeclFuncDef(AFuncDeclFuncDef node)
    {
        printIndentation();
        System.out.println("ENDDDDDDDDDDDDDD)" );
        removeIndentationLevel();
    }



    //FUNC_DEFINITION

    @Override
    public void inAFuncDefinition(AFuncDefinition node)
    {
        addIndentationLevel();
        printIndentation();
        System.out.print("(Func definition:" );
    }

    @Override
    public void outAFuncDefinition(AFuncDefinition node)
    {
        printIndentation();
        System.out.println(")" );
        removeIndentationLevel();
    }


    //HEAD


    @Override
    public void inAHead(AHead node)
    {
        addIndentationLevel();
        printIndentation();
        System.out.print("(Function Name : " + node.getL().toString() + node.getR().toString());
    }

    @Override
    public void outAHead(AHead node)
    {
        printIndentation();
        System.out.println(")");
        removeIndentationLevel();
    }


    //FPAR

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

    //NO_REF

    @Override
    public void inANoRef(ANoRef node)
    {
        addIndentationLevel();
        printIndentation();
        System.out.println("(Parameter: " + node.getL().toString() + node.getR().toString());
    }

    @Override
    public void outANoRef(ANoRef node)
    {
        printIndentation();
        System.out.println(")");
        removeIndentationLevel();
    }

    //VAR1

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

    //STMT

    @Override
    public void inAExpressionStmt(AExpressionStmt node)
    {
        addIndentationLevel();
        printIndentation();
        System.out.print("(Stmt: " + node.getL().toString() + node.getR().toString());
    }

    @Override
    public void outAExpressionStmt(AExpressionStmt node)
    {
        printIndentation();
        System.out.println(")");
        removeIndentationLevel();
    }

    @Override
    public void inAWhileStmt(AWhileStmt node)
    {
        addIndentationLevel();
        printIndentation();
        System.out.print("(While stmt: " + "left " + node.getL().toString() + "right "+node.getR().toString() + "  end");

    }

    @Override
    public void outAWhileStmt(AWhileStmt node)
    {
        printIndentation();
        System.out.println(")");
        removeIndentationLevel();
    }

    @Override
    public void inANoElseStmt(ANoElseStmt node)
    {
        addIndentationLevel();
        printIndentation();
        System.out.print("(No_else stmt: " + node.getL().toString() + node.getR().toString());
    }

    @Override
    public void outANoElseStmt(ANoElseStmt node)
    {
        printIndentation();
        System.out.println(")");
        removeIndentationLevel();
    }

    @Override
    public void inAWithElseStmt(AWithElseStmt node)
    {
        addIndentationLevel();
        printIndentation();
        System.out.print("(With_else stmt: " + node.getL().toString() + node.getR().toString());
    }

    @Override
    public void outAWithElseStmt(AWithElseStmt node)
    {
        printIndentation();
        System.out.println(")");
        removeIndentationLevel();
    }

    @Override
    public void inAExpressionElseStmt(AExpressionElseStmt node)
    {
        addIndentationLevel();
        printIndentation();
        System.out.print("(Stmt from else: " + node.getL().toString() + node.getR().toString());
    }

    @Override
    public void outAExpressionElseStmt(AExpressionElseStmt node)
    {
        printIndentation();
        System.out.println(")");
        removeIndentationLevel();
    }

    @Override
    public void inAWhileElseStmt(AWhileElseStmt node)
    {
        addIndentationLevel();
        printIndentation();
        System.out.print("(While from else stmt: " + node.getL().toString() + node.getR().toString());
    }

    @Override
    public void outAWhileElseStmt(AWhileElseStmt node)
    {
        printIndentation();
        System.out.println(")");
        removeIndentationLevel();
    }

    @Override
    public void inAWithElse2Stmt(AWithElse2Stmt node)
    {
        addIndentationLevel();
        printIndentation();
        System.out.print("(With_else stmt2: " + node.getL().toString() + node.getR().toString());
    }

    @Override
    public void outAWithElse2Stmt(AWithElse2Stmt node)
    {
        printIndentation();
        System.out.println(")");
        removeIndentationLevel();
    }

    @Override
    public void inAFuncCallWithStmt(AFuncCallWithStmt node)
    {
        addIndentationLevel();
        printIndentation();
        System.out.print("(Func call with: " + node.getL().toString() + node.getR().toString());
    }

    @Override
    public void outAFuncCallWithStmt(AFuncCallWithStmt node)
    {
        printIndentation();
        System.out.println(")");
        removeIndentationLevel();
    }

    @Override
    public void inAFuncCallWithoutStmt(AFuncCallWithoutStmt node)
    {
        addIndentationLevel();
        printIndentation();
        System.out.print("(Func call without: " + node.getVarName().toString());
    }

    @Override
    public void outAFuncCallWithoutStmt(AFuncCallWithoutStmt node)
    {
        printIndentation();
        System.out.println(")");
        removeIndentationLevel();
    }

    @Override
    public void inAVarStmt(AVarStmt node)
    {
        addIndentationLevel();
        printIndentation();
        System.out.print("(Var_name: " + node.getVarName().toString());
    }

    @Override
    public void outAVarStmt(AVarStmt node)
    {
        printIndentation();
        System.out.println(")");
        removeIndentationLevel();
    }

    @Override
    public void inAStringStmt(AStringStmt node)
    {
        addIndentationLevel();
        printIndentation();
        System.out.print("(String: " + node.getConstantString().toString());
    }

    @Override
    public void outAStringStmt(AStringStmt node)
    {
        printIndentation();
        System.out.println(")");
        removeIndentationLevel();
    }

    @Override
    public void inALValueStmt(ALValueStmt node)
    {
        addIndentationLevel();
        printIndentation();
        System.out.print("(L_value: " + node.getL().toString() + node.getR().toString());
    }

    @Override
    public void outALValueStmt(ALValueStmt node)
    {
        printIndentation();
        System.out.println(")");
        removeIndentationLevel();
    }

    @Override
    public void inAReturnWithStmt(AReturnWithStmt node)
    {
        addIndentationLevel();
        printIndentation();
        System.out.print("(Return with: " + node.getAllExpr().toString());
    }

    @Override
    public void outAReturnWithStmt(AReturnWithStmt node)
    {
        printIndentation();
        System.out.println(")");
        removeIndentationLevel();
    }

    @Override
    public void inAReturnExprStmt(AReturnExprStmt node)
    {
        addIndentationLevel();
        printIndentation();
        System.out.print("(Return expr: " + node.getAllExpr().toString());
    }

    @Override
    public void outAReturnExprStmt(AReturnExprStmt node)
    {
        printIndentation();
        System.out.println(")");
        removeIndentationLevel();
    }

    @Override
    public void inAReturnStmt(AReturnStmt node)
    {
        addIndentationLevel();
        printIndentation();
        System.out.print("(Return: " );
    }

    @Override
    public void outAReturnStmt(AReturnStmt node)
    {
        printIndentation();
        System.out.println(")");
        removeIndentationLevel();
    }





    @Override
    public void inAReturnWith2Stmt(AReturnWith2Stmt node)
    {
        addIndentationLevel();
        printIndentation();
        System.out.print("(Return with: " + node.getAllExpr().toString());
    }

    @Override
    public void outAReturnWith2Stmt(AReturnWith2Stmt node)
    {
        printIndentation();
        System.out.println(")");
        removeIndentationLevel();
    }

    @Override
    public void inAReturnExpr2Stmt(AReturnExpr2Stmt node)
    {
        addIndentationLevel();
        printIndentation();
        System.out.print("(Return expr: " + node.getAllExpr().toString());
    }

    @Override
    public void outAReturnExpr2Stmt(AReturnExpr2Stmt node)
    {
        printIndentation();
        System.out.println(")");
        removeIndentationLevel();
    }

    @Override
    public void inAReturn2Stmt(AReturn2Stmt node)
    {
        addIndentationLevel();
        printIndentation();
        System.out.print("(Return: " );
    }

    @Override
    public void outAReturn2Stmt(AReturn2Stmt node)
    {
        printIndentation();
        System.out.println(")");
        removeIndentationLevel();
    }

    //STMT WITH ELSE

    @Override
    public void inAStmtWithElse(AStmtWithElse node)
    {
        addIndentationLevel();
        printIndentation();
        System.out.print("(stmt with else: " + node.getL().toString() + node.getR().toString());
    }

    @Override
    public void outAStmtWithElse(AStmtWithElse node)
    {
        printIndentation();
        System.out.println(")");
        removeIndentationLevel();
    }

    @Override
    public void inAStmtWithElse2(AStmtWithElse2 node)
    {
        addIndentationLevel();
        printIndentation();
        System.out.print("(stmt with else: " + node.getL().toString() + node.getR().toString());
    }

    @Override
    public void outAStmtWithElse2(AStmtWithElse2 node)
    {
        printIndentation();
        System.out.println(")");
        removeIndentationLevel();
    }

    //ALL_EXPR


    @Override
    public void inAAddSubAllExpr(AAddSubAllExpr node)
    {
        addIndentationLevel();
        printIndentation();
        System.out.print("(Add_sub expr: " + node.getL().toString() + node.getR().toString());
    }

    @Override
    public void outAAddSubAllExpr(AAddSubAllExpr node)
    {
        printIndentation();
        System.out.println(")");
        removeIndentationLevel();
    }

    @Override
    public void inARestSignsAllExpr(ARestSignsAllExpr node)
    {
        addIndentationLevel();
        printIndentation();
        System.out.print("(Rest_signs expr: " + node.getL().toString() + node.getR().toString());
    }

    @Override
    public void outARestSignsAllExpr(ARestSignsAllExpr node)
    {
        printIndentation();
        System.out.println(")");
        removeIndentationLevel();
    }

    @Override
    public void inAWithPlminAllExpr(AWithPlminAllExpr node)
    {
        addIndentationLevel();
        printIndentation();
        System.out.print("(Plus_minus expr: " + node.getL().toString() + node.getR().toString());
    }

    @Override
    public void outAWithPlminAllExpr(AWithPlminAllExpr node)
    {
        printIndentation();
        System.out.println(")");
        removeIndentationLevel();
    }

    @Override
    public void inALetterAllExpr(ALetterAllExpr node)
    {
        addIndentationLevel();
        printIndentation();
        System.out.print("(Letter expr: " + node.getConstantCharacter().toString());
    }

    @Override
    public void outALetterAllExpr(ALetterAllExpr node)
    {
        printIndentation();
        System.out.println(")");
        removeIndentationLevel();
    }

    @Override
    public void inAConstantAllExpr(AConstantAllExpr node)
    {
        addIndentationLevel();
        printIndentation();
        System.out.print("(Letter expr: " + node.getConstant().toString());
    }

    @Override
    public void outAConstantAllExpr(AConstantAllExpr node)
    {
        printIndentation();
        System.out.println(")");
        removeIndentationLevel();
    }

    @Override
    public void inAParametersAllExpr(AParametersAllExpr node)
    {
        addIndentationLevel();
        printIndentation();
        System.out.print("(Parameters expr: " + node.getL().toString() + node.getR().toString());
    }

    @Override
    public void outAParametersAllExpr(AParametersAllExpr node)
    {
        printIndentation();
        System.out.println(")");
        removeIndentationLevel();
    }

    @Override
    public void inALValueAllExpr(ALValueAllExpr node)
    {
        addIndentationLevel();
        printIndentation();
        System.out.print("(LValue expr: " + node.getStmt().toString());
    }

    @Override
    public void outALValueAllExpr(ALValueAllExpr node)
    {
        printIndentation();
        System.out.println(")");
        removeIndentationLevel();
    }

    @Override
    public void inAFuncAllExpr(AFuncAllExpr node)
    {
        addIndentationLevel();
        printIndentation();
        System.out.print("(Func expr: " + node.getStmt().toString());
    }

    @Override
    public void outAFuncAllExpr(AFuncAllExpr node)
    {
        printIndentation();
        System.out.println(")");
        removeIndentationLevel();
    }

    //REST SIGNS EXPR

    @Override
    public void inARestSignsExpr(ARestSignsExpr node)
    {
        addIndentationLevel();
        printIndentation();
        System.out.print("(Rest signs expr: " + node.getL().toString() + node.getR().toString());
    }

    @Override
    public void outARestSignsExpr(ARestSignsExpr node)
    {
        printIndentation();
        System.out.println(")");
        removeIndentationLevel();
    }

    //ADD_SUB_EXPR


    @Override
    public void inAAddSubExpr(AAddSubExpr node)
    {
        addIndentationLevel();
        printIndentation();
        System.out.print("(Add_sub signs expr: " + node.getL().toString() + node.getR().toString());
    }

    @Override
    public void outAAddSubExpr(AAddSubExpr node)
    {
        printIndentation();
        System.out.println(")");
        removeIndentationLevel();
    }


    //COND

    @Override
    public void inAOrCond(AOrCond node)
    {
        addIndentationLevel();
        printIndentation();
        System.out.print("(Or cond: " + node.getL().toString() + node.getR().toString());
    }

    @Override
    public void outAOrCond(AOrCond node)
    {
        printIndentation();
        System.out.println(")");
        removeIndentationLevel();
    }

    @Override
    public void inAAndCond(AAndCond node)
    {
        addIndentationLevel();
        printIndentation();
        System.out.print("(And cond: " + node.getL().toString() + node.getR().toString());
    }

    @Override
    public void outAAndCond(AAndCond node)
    {
        printIndentation();
        System.out.println(")");
        removeIndentationLevel();
    }

    @Override
    public void inAExprsignsCond(AExprsignsCond node)
    {
        addIndentationLevel();
        printIndentation();
        System.out.print("(Exprsigns cond: " + node.getL().toString() + node.getR().toString());
    }

    @Override
    public void outAExprsignsCond(AExprsignsCond node)
    {
        printIndentation();
        System.out.println(")");
        removeIndentationLevel();
    }

    @Override
    public void inANotCond(ANotCond node)
    {
        addIndentationLevel();
        printIndentation();
        System.out.print("(Not cond: " + node.getL().toString() + node.getR().toString());
    }

    @Override
    public void outANotCond(ANotCond node)
    {
        printIndentation();
        System.out.println(")");
        removeIndentationLevel();
    }


    //LOGICAL NOT

    @Override
    public void inALogicalNot(ALogicalNot node)
    {
        addIndentationLevel();
        printIndentation();
        System.out.print("(Logical not: " + node.getNot().toString());
    }

    @Override
    public void outALogicalNot(ALogicalNot node)
    {
        printIndentation();
        System.out.println(")");
        removeIndentationLevel();
    }


    //ASS_SIGNS_COND

    @Override
    public void inAAssSignsCond(AAssSignsCond node)
    {
        addIndentationLevel();
        printIndentation();
        System.out.print("(Ass_signs cond: " + node.getL().toString() + node.getR().toString());
    }

    @Override
    public void outAAssSignsCond(AAssSignsCond node)
    {
        printIndentation();
        System.out.println(")");
        removeIndentationLevel();
    }


    //ASS_SIGNS

    @Override
    public void inAEqualAssSigns(AEqualAssSigns node)
    {
        addIndentationLevel();
        printIndentation();
        System.out.print("(Equal: " + node.getEqual().toString());
    }

    @Override
    public void outAEqualAssSigns(AEqualAssSigns node)
    {
        printIndentation();
        System.out.println(")");
        removeIndentationLevel();
    }

    @Override
    public void inANeAssSigns(ANeAssSigns node)
    {
        addIndentationLevel();
        printIndentation();
        System.out.print("(Not Equal: " + node.getNotEqual().toString());
    }

    @Override
    public void outANeAssSigns(ANeAssSigns node)
    {
        printIndentation();
        System.out.println(")");
        removeIndentationLevel();
    }

    @Override
    public void inASmallAssSigns(ASmallAssSigns node)
    {
        addIndentationLevel();
        printIndentation();
        System.out.print("(Smaller: " + node.getSmaller().toString());
    }

    @Override
    public void outASmallAssSigns(ASmallAssSigns node)
    {
        printIndentation();
        System.out.println(")");
        removeIndentationLevel();
    }

    @Override
    public void inAGreatAssSigns(AGreatAssSigns node)
    {
        addIndentationLevel();
        printIndentation();
        System.out.print("(Greater: " + node.getGreater().toString());
    }

    @Override
    public void outAGreatAssSigns(AGreatAssSigns node)
    {
        printIndentation();
        System.out.println(")");
        removeIndentationLevel();
    }

    @Override
    public void inASmalleAssSigns(ASmalleAssSigns node)
    {
        addIndentationLevel();
        printIndentation();
        System.out.print("(Smaller Equal: " + node.getSmallerEq().toString());
    }

    @Override
    public void outASmalleAssSigns(ASmalleAssSigns node) {
        printIndentation();
        System.out.println(")");
        removeIndentationLevel();
    }

    @Override
    public void inAGreateAssSigns(AGreateAssSigns node)
    {
        addIndentationLevel();
        printIndentation();
        System.out.print("(Greater Equal: " + node.getGreaterEq().toString());
    }

    @Override
    public void outAGreateAssSigns(AGreateAssSigns node)
    {
        printIndentation();
        System.out.println(")");
        removeIndentationLevel();
    }


    //REST_SIGNS

    @Override
    public void inAMultRestSigns(AMultRestSigns node)
    {
        addIndentationLevel();
        printIndentation();
        System.out.print("(Mult: " + node.getMult().toString());
    }

    @Override
    public void outAMultRestSigns(AMultRestSigns node)
    {
        printIndentation();
        System.out.println(")");
        removeIndentationLevel();
    }

    @Override
    public void inADivRestSigns(ADivRestSigns node)
    {
        addIndentationLevel();
        printIndentation();
        System.out.print("(Div: " + node.getDiv().toString());
    }

    @Override
    public void outADivRestSigns(ADivRestSigns node)
    {
        printIndentation();
        System.out.println(")");
        removeIndentationLevel();
    }

    @Override
    public void inADiv1RestSigns(ADiv1RestSigns node)
    {
        addIndentationLevel();
        printIndentation();
        System.out.print("(Div1: " + node.getDivSpace().toString());
    }

    @Override
    public void outADiv1RestSigns(ADiv1RestSigns node)
    {
        printIndentation();
        System.out.println(")");
        removeIndentationLevel();
    }


    @Override
    public void inAModRestSigns(AModRestSigns node)
    {
        addIndentationLevel();
        printIndentation();
        System.out.print("(Div1: " + node.getModSpace().toString());
    }

    @Override
    public void outAModRestSigns(AModRestSigns node)
    {
        printIndentation();
        System.out.println(")");
        removeIndentationLevel();
    }


    //PLUS_MINUS

    @Override
    public void inAPlusPlusMinus(APlusPlusMinus node)
    {
        addIndentationLevel();
        printIndentation();
        System.out.print("(Plus: " + node.getPlus().toString());
    }

    @Override
    public void outAPlusPlusMinus(APlusPlusMinus node)
    {
        printIndentation();
        System.out.println(")");
        removeIndentationLevel();
    }

    @Override
    public void inAMinusPlusMinus(AMinusPlusMinus node)
    {
        addIndentationLevel();
        printIndentation();
        System.out.print("(Minus: " + node.getMinus().toString());
    }

    @Override
    public void outAMinusPlusMinus(AMinusPlusMinus node)
    {
        printIndentation();
        System.out.println(")");
        removeIndentationLevel();
    }

    //PLUS_MINUS2

    @Override
    public void inAPlusPlusMinus2(APlusPlusMinus2 node)
    {
        addIndentationLevel();
        printIndentation();
        System.out.print("(Plus: " + node.getPlus().toString());
    }

    @Override
    public void outAPlusPlusMinus2(APlusPlusMinus2 node)
    {
        printIndentation();
        System.out.println(")");
        removeIndentationLevel();
    }

    @Override
    public void inAMinusPlusMinus2(AMinusPlusMinus2 node)
    {
        addIndentationLevel();
        printIndentation();
        System.out.print("(Minus: " + node.getMinus().toString());
    }

    @Override
    public void outAMinusPlusMinus2(AMinusPlusMinus2 node)
    {
        printIndentation();
        System.out.println(")");
        removeIndentationLevel();
    }



}