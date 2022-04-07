package com.armados.app.epraxeis.diaugeia.extrafields.typeB;

import android.content.Context;

import com.armados.app.epraxeis.Simple;
import com.armados.app.epraxeis.diaugeia.extrafields.DecisionExtraFields;

import java.util.ArrayList;
import java.util.List;

public class TypeB11  extends DecisionExtraFields {
    private int financialYear;
    private String budgettype;
    private String budgetkind;
    private Boolean isBudgetApprovalForOrg;
    private String refersToOtherOrgBudget;

    public int getFinancialYear() {
        return financialYear;
    }

    public void setFinancialYear(int financialYear) {
        this.financialYear = financialYear;
    }

    public String getBudgettype() {
        return budgettype;
    }

    public void setBudgettype(String budgettype) {
        this.budgettype = budgettype;
    }

    public String getBudgetkind() {
        return budgetkind;
    }

    public void setBudgetkind(String budgetkind) {
        this.budgetkind = budgetkind;
    }

    public Boolean getBudgetApprovalForOrg() {
        return isBudgetApprovalForOrg;
    }

    public void setBudgetApprovalForOrg(Boolean budgetApprovalForOrg) {
        isBudgetApprovalForOrg = budgetApprovalForOrg;
    }

    public String getRefersToOtherOrgBudget() {
        return refersToOtherOrgBudget;
    }

    public void setRefersToOtherOrgBudget(String refersToOtherOrgBudget) {
        this.refersToOtherOrgBudget = refersToOtherOrgBudget;
    }

    @Override
    public List<Simple> getDetailInfoItems(Context context) {
        List<Simple> list = new ArrayList<>();
        return list;
    }

}
