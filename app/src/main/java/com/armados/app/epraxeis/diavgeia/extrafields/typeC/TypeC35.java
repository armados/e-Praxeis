package com.armados.app.epraxeis.diavgeia.extrafields.typeC;

import android.content.Context;

import com.armados.app.epraxeis.Simple;
import com.armados.app.epraxeis.diavgeia.extrafields.DecisionExtraFields;
import com.armados.app.epraxeis.diavgeia.extrafields.common.Fek;

import java.util.ArrayList;
import java.util.List;

public class TypeC35 extends DecisionExtraFields {
    public String eidosYpMetavolis;
    public int numberOfPeople;
    public Fek fek;

    public String getEidosYpMetavolis() {
        return eidosYpMetavolis;
    }

    public void setEidosYpMetavolis(String eidosYpMetavolis) {
        this.eidosYpMetavolis = eidosYpMetavolis;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
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
