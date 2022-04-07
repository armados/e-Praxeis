package com.armados.app.epraxeis.diaugeia.extrafields.typeB;

import android.content.Context;

import com.armados.app.epraxeis.Simple;
import com.armados.app.epraxeis.SimpleLabel;
import com.armados.app.epraxeis.diaugeia.extrafields.DecisionExtraFields;
import com.armados.app.epraxeis.diaugeia.extrafields.common.ContributingPart;
import com.armados.app.epraxeis.diaugeia.extrafields.common.Fek;

import java.util.ArrayList;
import java.util.List;

public class TypeB6 extends DecisionExtraFields {
    private int financialYear;
    private double investAmount;
    private String signDate;
    private String signExpireDate;
    private List<ContributingPart> contributingParts = new ArrayList<>();
    private Fek fek;

    public int getFinancialYear() {
        return financialYear;
    }

    public void setFinancialYear(int financialYear) {
        this.financialYear = financialYear;
    }

    public double getInvestAmount() {
        return investAmount;
    }

    public void setInvestAmount(double investAmount) {
        this.investAmount = investAmount;
    }

    public String getSignDate() {
        return signDate;
    }

    public void setSignDate(String signDate) {
        this.signDate = signDate;
    }

    public String getSignExpireDate() {
        return signExpireDate;
    }

    public void setSignExpireDate(String signExpireDate) {
        this.signExpireDate = signExpireDate;
    }

    public List<ContributingPart> getContributingParts() {
        return contributingParts;
    }

    public void setContributingParts(List<ContributingPart> contributingParts) {
        this.contributingParts = contributingParts;
    }

    public Fek getFek() {
        return fek;
    }

    public void setFek(Fek fek) {
        this.fek = fek;
    }

    @Override
    public List<Simple> getDetailInfoItems(Context context) {
        List<Simple> list = new ArrayList<>();

        if (getInvestAmount() != 0) {
            SimpleLabel r = new SimpleLabel();
            r.setLabel(showAmountWithoutCurrency(getInvestAmount()));
            r.setLabelHeader("Ποσό Σε Ευρώ Προς Χρηματοδότηση");
            r.setSubtitle("Οικονομικό έτος " + getFinancialYear());
            list.add(r);
        }

        return list;
    }

}
