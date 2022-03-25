package com.armados.app.epraxeis.diavgeia.extrafields.type2461;

import android.content.Context;

import com.armados.app.epraxeis.Simple;
import com.armados.app.epraxeis.diavgeia.extrafields.DecisionExtraFields;
import com.armados.app.epraxeis.diavgeia.extrafields.common.CoCompetent;

import java.util.ArrayList;
import java.util.List;

/* Πράξη 2.4.6.1 */
public class Type2461 extends DecisionExtraFields {
    public String xorotaxikipraxitype;
    public String municipality;
    public List<CoCompetent> co_competent = new ArrayList<>();

    public String getXorotaxikipraxitype() {
        return xorotaxikipraxitype;
    }

    public void setXorotaxikipraxitype(String xorotaxikipraxitype) {
        this.xorotaxikipraxitype = xorotaxikipraxitype;
    }

    public String getMunicipality() {
        return municipality;
    }

    public void setMunicipality(String municipality) {
        this.municipality = municipality;
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
