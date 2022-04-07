package com.armados.app.epraxeis.diaugeia.extrafields.typeD;

import android.content.Context;

import com.armados.app.epraxeis.Config;
import com.armados.app.epraxeis.Database;
import com.armados.app.epraxeis.DictionaryEntity;
import com.armados.app.epraxeis.R;
import com.armados.app.epraxeis.Simple;
import com.armados.app.epraxeis.SimpleAFM;
import com.armados.app.epraxeis.SimpleLabel;
import com.armados.app.epraxeis.diaugeia.extrafields.DecisionExtraFields;
import com.armados.app.epraxeis.diaugeia.extrafields.common.Person;
import com.armados.app.epraxeis.diaugeia.extrafields.common.amountWithVAT;

import java.util.ArrayList;
import java.util.List;

public class TypeD1 extends DecisionExtraFields {
    private List<Person> person  = new ArrayList<>();
    private amountWithVAT awardAmount;
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
        return awardAmount;
    }

    public void setAwardAmount(amountWithVAT awardAmount) {
        this.awardAmount = awardAmount;
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

        for (String cpv : getCpv()) {
            SimpleLabel per = new SimpleLabel();
            per.setLabelHeader("Κωδικός CPV");
            per.setLabel("CPV " + cpv);

            DictionaryEntity infoCpv = Database.getInstance(context)
                    .getDictionaryDao()
                    .getEntry(Config.DICTIONARY_CPV, cpv);

            if (infoCpv != null)
                per.setDescription(infoCpv.getLabel());
            else
                per.setDescription("CPV Περιγραφή" + R.string.missing_dict_entry);

            list.add(per);
        }

        for (Person person : getPerson()) {
            SimpleAFM per = new SimpleAFM();
            per.setUid(person.getAfm());
            per.setLabelHeader("Στοιχεία δικαιούχου");
            per.setLabel(showName(person));
            per.setSubtitle(showAFM(person));
            list.add(per);
        }


        return list;
    }

}