package com.armados.app.epraxeis.diavgeia.extrafields.typeB;


import android.content.Context;

import com.armados.app.epraxeis.Simple;
import com.armados.app.epraxeis.SimpleLabel;
import com.armados.app.epraxeis.SimplePersonAFM;
import com.armados.app.epraxeis.diavgeia.extrafields.DecisionExtraFields;
import com.armados.app.epraxeis.diavgeia.extrafields.common.AmountWithKae;
import com.armados.app.epraxeis.diavgeia.extrafields.common.Person;
import com.armados.app.epraxeis.diavgeia.extrafields.common.amountWithVAT;

import java.util.ArrayList;
import java.util.List;


public class TypeB13 extends DecisionExtraFields { // verify again
    private int financialYear;
    private String budgettype;
    private String entryNumber;
    private Boolean partialead;
    private Boolean recalledExpenseDecision;
    private amountWithVAT amountWithVAT;
    private List<AmountWithKae> amountWithKae = new ArrayList<>();
    private String relatedPartialADA;

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

    public String getEntryNumber() {
        return entryNumber;
    }

    public void setEntryNumber(String entryNumber) {
        this.entryNumber = entryNumber;
    }

    public Boolean getPartialead() {
        return partialead;
    }

    public void setPartialead(Boolean partialead) {
        this.partialead = partialead;
    }

    public Boolean getRecalledExpenseDecision() {
        return recalledExpenseDecision;
    }

    public void setRecalledExpenseDecision(Boolean recalledExpenseDecision) {
        this.recalledExpenseDecision = recalledExpenseDecision;
    }

    public amountWithVAT getAmountWithVAT() {
        return amountWithVAT;
    }

    public void setAmountWithVAT(amountWithVAT amountWithVAT) {
        this.amountWithVAT = amountWithVAT;
    }

    public List<AmountWithKae> getAmountWithKae() {
        return amountWithKae;
    }

    public void setAmountWithKae(List<AmountWithKae> amountWithKae) {
        this.amountWithKae = amountWithKae;
    }

    public String getRelatedPartialADA() {
        return relatedPartialADA;
    }

    public void setRelatedPartialADA(String relatedPartialADA) {
        this.relatedPartialADA = relatedPartialADA;
    }

    @Override
    public List<Simple> getDetailInfoItems(Context context) {
        List<Simple> list = new ArrayList<>();

        if (getAmountWithVAT() != null) {
            SimpleLabel r = new SimpleLabel();
            r.setLabelHeader(getBudgettype());
            r.setLabel(showAmount(context, getAmountWithVAT()));
            list.add(r);
        }

        for (AmountWithKae p : getAmountWithKae()) {
            final Person person = p.getSponsorAFMName();

            SimplePersonAFM o = new SimplePersonAFM();
            o.setLabelHeader("Στοιχεία δικαιούχου");

            if (person != null) {
                o.setUid(person.getAfm());
            }

            o.setLabel(showName(person));
            o.setSubtitle(showAFM(person));

            o.setDescription(showAmountWithoutCurrency(p.getAmountWithVAT()));
            list.add(o);
        }

        return list;
    }

}