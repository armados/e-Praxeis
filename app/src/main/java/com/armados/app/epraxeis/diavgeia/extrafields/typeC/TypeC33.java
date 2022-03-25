package com.armados.app.epraxeis.diavgeia.extrafields.typeC;

import android.content.Context;

import com.armados.app.epraxeis.Simple;
import com.armados.app.epraxeis.diavgeia.extrafields.DecisionExtraFields;
import com.armados.app.epraxeis.diavgeia.extrafields.common.Fek;

import java.util.ArrayList;
import java.util.List;

public class TypeC33 extends DecisionExtraFields {
    public int numberOfPeople;
    public String employerOrg;
    public Fek fek;

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public String getEmployerOrg() {
        return employerOrg;
    }

    public void setEmployerOrg(String employerOrg) {
        this.employerOrg = employerOrg;
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
        return list;
    }

}
