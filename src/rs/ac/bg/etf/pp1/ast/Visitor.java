// generated with ast extension for cup
// version 0.8
// 27/0/2019 5:2:17


package rs.ac.bg.etf.pp1.ast;

public interface Visitor { 

    public void visit(Unmatched Unmatched);
    public void visit(EnumDecl EnumDecl);
    public void visit(MethodDecl MethodDecl);
    public void visit(Mulop Mulop);
    public void visit(Matched Matched);
    public void visit(Relop Relop);
    public void visit(Assignop Assignop);
    public void visit(Unmatched_For Unmatched_For);
    public void visit(GlobVarDecl GlobVarDecl);
    public void visit(LoclVarList LoclVarList);
    public void visit(StatementList StatementList);
    public void visit(Addop Addop);
    public void visit(ConstPart ConstPart);
    public void visit(ClassPart ClassPart);
    public void visit(Factor Factor);
    public void visit(ExprOpt ExprOpt);
    public void visit(MethodNameType MethodNameType);
    public void visit(CondTerm CondTerm);
    public void visit(ConstList ConstList);
    public void visit(DeclList DeclList);
    public void visit(Designator Designator);
    public void visit(Matched_For Matched_For);
    public void visit(Term Term);
    public void visit(Condition Condition);
    public void visit(For For);
    public void visit(DesignatorStatementOpt DesignatorStatementOpt);
    public void visit(ActParsList ActParsList);
    public void visit(EnumList EnumList);
    public void visit(Form Form);
    public void visit(ExprList ExprList);
    public void visit(Expr Expr);
    public void visit(ActPars ActPars);
    public void visit(LoclVarDecl LoclVarDecl);
    public void visit(DesignatorStatement DesignatorStatement);
    public void visit(GlobVarList GlobVarList);
    public void visit(FormParamList FormParamList);
    public void visit(Decl Decl);
    public void visit(Statement Statement);
    public void visit(Type Type);
    public void visit(ClassDecl ClassDecl);
    public void visit(ConstDecl ConstDecl);
    public void visit(CondFact CondFact);
    public void visit(LoclVar LoclVar);
    public void visit(MethodDeclList MethodDeclList);
    public void visit(GlobVar GlobVar);
    public void visit(Enums Enums);
    public void visit(Consts Consts);
    public void visit(FormPars FormPars);
    public void visit(MethVarDecl MethVarDecl);
    public void visit(MulopMod MulopMod);
    public void visit(MulopDiv MulopDiv);
    public void visit(MulopMul MulopMul);
    public void visit(AddopMinus AddopMinus);
    public void visit(AddopPlus AddopPlus);
    public void visit(RelopLessEq RelopLessEq);
    public void visit(RelopLess RelopLess);
    public void visit(RelopMoreEq RelopMoreEq);
    public void visit(RelopMore RelopMore);
    public void visit(RelopDif RelopDif);
    public void visit(RelopEq RelopEq);
    public void visit(AssignopEq AssignopEq);
    public void visit(DesignatorID DesignatorID);
    public void visit(DesignatorExpr DesignatorExpr);
    public void visit(DesignatorDot DesignatorDot);
    public void visit(FactorExpr FactorExpr);
    public void visit(FactorNewExpr FactorNewExpr);
    public void visit(FactorNewNoExpr FactorNewNoExpr);
    public void visit(FactorBool FactorBool);
    public void visit(FactorChar FactorChar);
    public void visit(FactorNum FactorNum);
    public void visit(FactorDesignatorActPars FactorDesignatorActPars);
    public void visit(FactorDesignator FactorDesignator);
    public void visit(TermNoMulop TermNoMulop);
    public void visit(Terms Terms);
    public void visit(ExprTermNoAddop ExprTermNoAddop);
    public void visit(ExprListAddop ExprListAddop);
    public void visit(ExprMinus ExprMinus);
    public void visit(ExprNoMinus ExprNoMinus);
    public void visit(NoExpr NoExpr);
    public void visit(Exprs Exprs);
    public void visit(CondFactRelop CondFactRelop);
    public void visit(CondFactNoRelop CondFactNoRelop);
    public void visit(CondTermListNoAnd CondTermListNoAnd);
    public void visit(CondTermListAnd CondTermListAnd);
    public void visit(NoConditions NoConditions);
    public void visit(ConditionError ConditionError);
    public void visit(ConditionListNoOr ConditionListNoOr);
    public void visit(ConditionListOr ConditionListOr);
    public void visit(ActParsListNoComma ActParsListNoComma);
    public void visit(ActParsListComma ActParsListComma);
    public void visit(NoActPars NoActPars);
    public void visit(ActualParameters ActualParameters);
    public void visit(DesignatorError DesignatorError);
    public void visit(DesignatorDec DesignatorDec);
    public void visit(DesignatorInc DesignatorInc);
    public void visit(DesignatorParams DesignatorParams);
    public void visit(DesignatorAssign DesignatorAssign);
    public void visit(NoDesigStatement NoDesigStatement);
    public void visit(DesigStatementY DesigStatementY);
    public void visit(For123 For123);
    public void visit(MatchedForSeparation MatchedForSeparation);
    public void visit(UnmatchedForSeparation UnmatchedForSeparation);
    public void visit(MatchedStatement MatchedStatement);
    public void visit(MatchedPrintNumber MatchedPrintNumber);
    public void visit(MatchedPrint MatchedPrint);
    public void visit(MatchedRead MatchedRead);
    public void visit(MatchedReturnExpr MatchedReturnExpr);
    public void visit(MatchedContinue MatchedContinue);
    public void visit(MatchedBreak MatchedBreak);
    public void visit(MatchedFor MatchedFor);
    public void visit(MatchedIfElse MatchedIfElse);
    public void visit(MatchedDesignatorError MatchedDesignatorError);
    public void visit(MatchedDesignator MatchedDesignator);
    public void visit(UnmatchedFor UnmatchedFor);
    public void visit(UnmatchedIfElse UnmatchedIfElse);
    public void visit(UnmatchedIfNoElse UnmatchedIfNoElse);
    public void visit(StatementUnatched StatementUnatched);
    public void visit(StatementMatched StatementMatched);
    public void visit(NoStatements NoStatements);
    public void visit(StatementLists StatementLists);
    public void visit(Types Types);
    public void visit(FormParsError FormParsError);
    public void visit(FormBrackets FormBrackets);
    public void visit(FormNoBrackets FormNoBrackets);
    public void visit(FormParametersListSingle FormParametersListSingle);
    public void visit(FormParametersList FormParametersList);
    public void visit(NoFormParsList NoFormParsList);
    public void visit(FormParameters FormParameters);
    public void visit(NoMethodVarDecl NoMethodVarDecl);
    public void visit(MethodVarDecl MethodVarDecl);
    public void visit(MethNameVoid MethNameVoid);
    public void visit(MethNameType MethNameType);
    public void visit(MethodDeclarationType MethodDeclarationType);
    public void visit(NoMethod NoMethod);
    public void visit(MethodDeclarationList MethodDeclarationList);
    public void visit(NoClassPartMethod NoClassPartMethod);
    public void visit(ClassPartMethod ClassPartMethod);
    public void visit(ClassDeclaration ClassDeclaration);
    public void visit(LoclVarDeclarationError LoclVarDeclarationError);
    public void visit(LoclVarBrackets LoclVarBrackets);
    public void visit(LoclVarNoBrackets LoclVarNoBrackets);
    public void visit(LoclVarListNoComma LoclVarListNoComma);
    public void visit(LoclVarListComma LoclVarListComma);
    public void visit(LoclVarDeclaration LoclVarDeclaration);
    public void visit(GlobVarDeclarationError GlobVarDeclarationError);
    public void visit(GlobVarBrackets GlobVarBrackets);
    public void visit(GlobVarNoBrackets GlobVarNoBrackets);
    public void visit(GlobVarListNoComma GlobVarListNoComma);
    public void visit(GlobVarListComma GlobVarListComma);
    public void visit(GlobVarDeclaration GlobVarDeclaration);
    public void visit(EnumNum EnumNum);
    public void visit(EnumNoNum EnumNoNum);
    public void visit(EnumNoComma EnumNoComma);
    public void visit(EnumListComma EnumListComma);
    public void visit(EnumDeclaration EnumDeclaration);
    public void visit(ConstError ConstError);
    public void visit(ConstBool ConstBool);
    public void visit(ConstChar ConstChar);
    public void visit(ConstNumber ConstNumber);
    public void visit(ConstantsPart ConstantsPart);
    public void visit(ConstantsListNoComma ConstantsListNoComma);
    public void visit(ConstantsListComma ConstantsListComma);
    public void visit(ConstantsDecl ConstantsDecl);
    public void visit(Declarations4 Declarations4);
    public void visit(Declarations3 Declarations3);
    public void visit(Declarations2 Declarations2);
    public void visit(Declarations1 Declarations1);
    public void visit(NoDeclList NoDeclList);
    public void visit(Declarations Declarations);
    public void visit(ProgName ProgName);
    public void visit(Program Program);

}
