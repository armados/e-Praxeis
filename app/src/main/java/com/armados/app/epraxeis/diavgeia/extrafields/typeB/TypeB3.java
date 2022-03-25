package com.armados.app.epraxeis.diavgeia.extrafields.typeB;

import android.content.Context;

import com.armados.app.epraxeis.Simple;
import com.armados.app.epraxeis.diavgeia.extrafields.DecisionExtraFields;

import java.util.ArrayList;
import java.util.List;

public class TypeB3 extends DecisionExtraFields {
    private String accountType;
    private int financialYear;
    private String timePeriod;
    private Boolean isAccountApprovalForOtherOrg;
    private String refersToOtherOrgAccount;

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public int getFinancialYear() {
        return financialYear;
    }

    public void setFinancialYear(int financialYear) {
        this.financialYear = financialYear;
    }

    public String getTimePeriod() {
        return timePeriod;
    }

    public void setTimePeriod(String timePeriod) {
        this.timePeriod = timePeriod;
    }

    public Boolean getAccountApprovalForOtherOrg() {
        return isAccountApprovalForOtherOrg;
    }

    public void setAccountApprovalForOtherOrg(Boolean accountApprovalForOtherOrg) {
        isAccountApprovalForOtherOrg = accountApprovalForOtherOrg;
    }

    public String getRefersToOtherOrgAccount() {
        return refersToOtherOrgAccount;
    }

    public void setRefersToOtherOrgAccount(String refersToOtherOrgAccount) {
        this.refersToOtherOrgAccount = refersToOtherOrgAccount;
    }

    @Override
    public List<Simple> getDetailInfoItems(Context context) {
        List<Simple> list = new ArrayList<>();
        return list;
    }

}
