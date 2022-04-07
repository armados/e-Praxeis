package com.armados.app.epraxeis.diaugeia.extrafields.typeB;

import android.content.Context;

import com.armados.app.epraxeis.Simple;
import com.armados.app.epraxeis.SimpleAFM;
import com.armados.app.epraxeis.diaugeia.extrafields.DecisionExtraFields;
import com.armados.app.epraxeis.diaugeia.extrafields.common.Person;
import com.armados.app.epraxeis.diaugeia.extrafields.common.amountWithVAT;

import java.util.ArrayList;
import java.util.List;

public class TypeB4 extends DecisionExtraFields {
    private String donationType;
    private com.armados.app.epraxeis.diaugeia.extrafields.common.amountWithVAT amountWithVAT;
    private String kae;
    private Person donationGiver;
    private List<Person> donationReceiver = new ArrayList<>();

    public String getDonationType() {
        return donationType;
    }

    public void setDonationType(String donationType) {
        this.donationType = donationType;
    }

    public amountWithVAT getAmountWithVAT() {
        return amountWithVAT;
    }

    public void setAmountWithVAT(amountWithVAT amountWithVAT) {
        this.amountWithVAT = amountWithVAT;
    }

    public String getKae() {
        return kae;
    }

    public void setKae(String kae) {
        this.kae = kae;
    }

    public Person getDonationGiver() {
        return donationGiver;
    }

    public void setDonationGiver(Person donationGiver) {
        this.donationGiver = donationGiver;
    }

    public List<Person> getDonationReceiver() {
        return donationReceiver;
    }

    public void setDonationReceiver(List<Person> donationReceiver) {
        this.donationReceiver = donationReceiver;
    }

    @Override
    public List<Simple> getDetailInfoItems(Context context) {
        List<Simple> list = new ArrayList<>();

        if (getDonationGiver() != null) {
            SimpleAFM g = new SimpleAFM();
            g.setUid(getDonationGiver().getAfm());
            g.setLabelHeader("Στοιχεία δότη");
            g.setLabel(getDonationGiver().getName());
            g.setSubtitle(showAFM(getDonationGiver()));
            g.setDescription(showAmount(context, getAmountWithVAT()));
            list.add(g);
        }

        for (Person person : getDonationReceiver()) {
            SimpleAFM per = new SimpleAFM();
            per.setUid(person.getAfm());
            per.setLabelHeader("Στοιχεία αποδέκτη");
            per.setLabel(showName(person));
            per.setSubtitle(showAFM(person));
            list.add(per);
        }

        return list;
    }

}
