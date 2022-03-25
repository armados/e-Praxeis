package com.armados.app.epraxeis.diavgeia.extrafields.type2471;

import android.content.Context;

import com.armados.app.epraxeis.Simple;
import com.armados.app.epraxeis.diavgeia.extrafields.DecisionExtraFields;
import com.armados.app.epraxeis.diavgeia.extrafields.common.CoCompetent;
import com.armados.app.epraxeis.diavgeia.extrafields.common.Fek;

import java.util.ArrayList;
import java.util.List;

public class Type2471 extends DecisionExtraFields {
    private String kanonistikipraxitype;
    private String kanonistikiPraxiPublishMedium;
    private Fek fek;
    private List<CoCompetent> co_competent = new ArrayList<>();

    public String getKanonistikipraxitype() {
        return kanonistikipraxitype;
    }

    public void setKanonistikipraxitype(String kanonistikipraxitype) {
        this.kanonistikipraxitype = kanonistikipraxitype;
    }

    public String getKanonistikiPraxiPublishMedium() {
        return kanonistikiPraxiPublishMedium;
    }

    public void setKanonistikiPraxiPublishMedium(String kanonistikiPraxiPublishMedium) {
        this.kanonistikiPraxiPublishMedium = kanonistikiPraxiPublishMedium;
    }

    public Fek getFek() {
        return fek;
    }

    public void setFek(Fek fek) {
        this.fek = fek;
    }

    public List<CoCompetent> getCo_competent() {
        return co_competent;
    }

    public void setCo_competent(List<CoCompetent> co_competent) {
        this.co_competent = co_competent;
    }

    @Override
    public List<Simple> getDetailInfoItems(Context context) {
        List<Simple> list = new ArrayList<>();
        return list;
    }

}
