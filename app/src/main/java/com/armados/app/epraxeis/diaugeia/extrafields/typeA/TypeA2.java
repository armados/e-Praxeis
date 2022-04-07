package com.armados.app.epraxeis.diaugeia.extrafields.typeA;

import android.content.Context;

import com.armados.app.epraxeis.Simple;
import com.armados.app.epraxeis.diaugeia.extrafields.DecisionExtraFields;
import com.armados.app.epraxeis.diaugeia.extrafields.common.CoCompetent;
import com.armados.app.epraxeis.diaugeia.extrafields.common.Fek;

import java.util.ArrayList;
import java.util.List;

public class TypeA2 extends DecisionExtraFields {
    private Fek fek;
    private String kanonistikipraxiaa;
    private String kanonistikipraxitype;
    private CoCompetent coCompetent;

    public Fek getFek() {
        return fek;
    }

    public void setFek(Fek fek) {
        this.fek = fek;
    }

    public String getKanonistikipraxiaa() {
        return kanonistikipraxiaa;
    }

    public void setKanonistikipraxiaa(String kanonistikipraxiaa) {
        this.kanonistikipraxiaa = kanonistikipraxiaa;
    }

    public String getKanonistikipraxitype() {
        return kanonistikipraxitype;
    }

    public void setKanonistikipraxitype(String kanonistikipraxitype) {
        this.kanonistikipraxitype = kanonistikipraxitype;
    }

    public CoCompetent getCoCompetent() {
        return coCompetent;
    }

    public void setCoCompetent(CoCompetent coCompetent) {
        this.coCompetent = coCompetent;
    }

    @Override
    public List<Simple> getDetailInfoItems(Context context) {
        List<Simple> list = new ArrayList<>();
        return list;
    }
}
