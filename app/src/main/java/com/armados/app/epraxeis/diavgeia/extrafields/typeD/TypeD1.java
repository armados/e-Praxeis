package com.armados.app.epraxeis.diavgeia.extrafields.typeD;

import android.content.Context;

import com.armados.app.epraxeis.Simple;
import com.armados.app.epraxeis.SimpleLabel;
import com.armados.app.epraxeis.SimplePersonAFM;
import com.armados.app.epraxeis.diavgeia.extrafields.DecisionExtraFields;
import com.armados.app.epraxeis.diavgeia.extrafields.common.Person;
import com.armados.app.epraxeis.diavgeia.extrafields.common.amountWithVAT;

import java.util.ArrayList;
import java.util.List;

public class TypeD1 extends DecisionExtraFields {
    private List<Person> person  = new ArrayList<>();
    private amountWithVAT awardAmountWithVAT;
    private String assignmentType;
    private List<String> cpv = new ArrayList<>();
    private String textRelatedADA;

    public List<Person> getPerson() {
        return person;
    }

    public void setPerson(List<Person> person) {
        this.person = person;
    }

    public amountWithVAT getAwardAmount() {
        return awardAmountWithVAT;
    }

    public void setAwardAmount(amountWithVAT awardAmountWithVAT) {
        this.awardAmountWithVAT = awardAmountWithVAT;
    }

    public String getAssignmentType() {
        return assignmentType;
    }

    public void setAssignmentType(String assignmentType) {
        this.assignmentType = assignmentType;
    }

    public List<String> getCpv() {
        return cpv;
    }

    public void setCpv(List<String> cpv) {
        this.cpv = cpv;
    }

    public String getTextRelatedADA() {
        return textRelatedADA;
    }

    public void setTextRelatedADA(String textRelatedADA) {
        this.textRelatedADA = textRelatedADA;
    }

    @Override
    public List<Simple> getDetailInfoItems(Context context) {
        List<Simple> list = new ArrayList<>();

        if (getAwardAmount() != null) {
            SimpleLabel r = new SimpleLabel();
            r.setLabelHeader(getAssignmentType());
            r.setDescription(showAmount(context, getAwardAmount()));
            list.add(r);
        }

        for (Person person : getPerson()) {
            SimplePersonAFM per = new SimplePersonAFM();
            per.setUid(person.getAfm());
            per.setLabel(showName(person));
            per.setSubtitle(showAFM(person));
            list.add(per);
        }

        return list;
    }

}