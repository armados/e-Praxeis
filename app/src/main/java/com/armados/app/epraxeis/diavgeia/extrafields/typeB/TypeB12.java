package com.armados.app.epraxeis.diavgeia.extrafields.typeB;

import android.content.Context;

import com.armados.app.epraxeis.Simple;
import com.armados.app.epraxeis.SimplePersonAFM;
import com.armados.app.epraxeis.diavgeia.extrafields.DecisionExtraFields;
import com.armados.app.epraxeis.diavgeia.extrafields.common.AmountWithVATAndKae;

import java.util.ArrayList;
import java.util.List;

public class TypeB12 extends DecisionExtraFields {
    private int financialYear;
    private String budgettype;
    private List<AmountWithVATAndKae> amountWithVATAndKae = new ArrayList<>();
    private String primaryOfficer;
    private String secondaryOfficer;

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

    public List<AmountWithVATAndKae> getAmountWithVATAndKae() {
        return amountWithVATAndKae;
    }

    public void setAmountWithVATAndKae(List<AmountWithVATAndKae> amountWithVATAndKae) {
        this.amountWithVATAndKae = amountWithVATAndKae;
    }

    public String getPrimaryOfficer() {
        return primaryOfficer;
    }

    public void setPrimaryOfficer(String primaryOfficer) {
        this.primaryOfficer = primaryOfficer;
    }

    public String getSecondaryOfficer() {
        return secondaryOfficer;
    }

    public void setSecondaryOfficer(String secondaryOfficer) {
        this.secondaryOfficer = secondaryOfficer;
    }

    @Override
    public List<Simple> getDetailInfoItems(Context context) {
        List<Simple> list = new ArrayList<>();

     /*   SimpleLabel r = new SimpleLabel();
        r.setLabel(getBudgettype());
        r.setLabelHeader(getBudgettype());

        r.setSubtitle("Οικονομικό έτος " + getFinancialYear());
        list.add(r);
*/
        for (AmountWithVATAndKae p : getAmountWithVATAndKae()) {
            SimplePersonAFM per = new SimplePersonAFM();
            per.setUid(p.getKae());
            per.setLabel("ΚΑΕ " + p.getKae());
            if (p.getAmountWithVAT() != null)
                per.setSubtitle("ΠΟΣΟ " + p.getAmountWithVAT().getAmount());
            list.add(per);
        }

        return list;
    }

}
