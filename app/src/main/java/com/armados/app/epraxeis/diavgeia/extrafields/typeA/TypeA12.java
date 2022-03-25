package com.armados.app.epraxeis.diavgeia.extrafields.typeA;

import android.content.Context;

import com.armados.app.epraxeis.Simple;
import com.armados.app.epraxeis.diavgeia.extrafields.DecisionExtraFields;
import com.armados.app.epraxeis.diavgeia.extrafields.common.Fek;

import java.util.ArrayList;
import java.util.List;

public class TypeA12 extends DecisionExtraFields {
    public Fek fek;
    public String lawpraxiaa;

    public Fek getFek() {
        return fek;
    }

    public void setFek(Fek fek) {
        this.fek = fek;
    }

    public String getLawpraxiaa() {
        return lawpraxiaa;
    }

    public void setLawpraxiaa(String lawpraxiaa) {
        this.lawpraxiaa = lawpraxiaa;
    }

    @Override
    public List<Simple> getDetailInfoItems(Context context) {
        List<Simple> list = new ArrayList<>();
        return list;
    }

}
