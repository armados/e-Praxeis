package com.armados.app.epraxeis.diavgeia.extrafields.typeC;

import android.content.Context;

import com.armados.app.epraxeis.Simple;
import com.armados.app.epraxeis.SimpleLabel;
import com.armados.app.epraxeis.SimplePersonAFM;
import com.armados.app.epraxeis.diavgeia.extrafields.DecisionExtraFields;
import com.armados.app.epraxeis.diavgeia.extrafields.common.Person;
import com.armados.app.epraxeis.diavgeia.extrafields.common.amountWithVAT;

import java.util.ArrayList;
import java.util.List;

public class TypeC34 extends DecisionExtraFields {
    public String contractType;
    public int numberOfPeople;
    public Boolean financedProject;
    public List<Person> person = new ArrayList<>();
    public amountWithVAT contractAmount;
    public String duration;

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public Boolean getFinancedProject() {
        return financedProject;
    }

    public void setFinancedProject(Boolean financedProject) {
        this.financedProject = financedProject;
    }

    public List<Person> getPerson() {
        return person;
    }

    public void setPerson(List<Person> person) {
        this.person = person;
    }

    public amountWithVAT getContractAmount() {
        return contractAmount;
    }

    public void setContractAmount(amountWithVAT contractAmount) {
        this.contractAmount = contractAmount;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    @Override
    public List<Simple> getDetailInfoItems(Context context) {
        List<Simple> list = new ArrayList<>();

        if (getContractAmount() != null) {
            SimpleLabel r = new SimpleLabel();
            r.setLabelHeader(getContractType());
            r.setDescription(showAmount(context, getContractAmount()));
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
