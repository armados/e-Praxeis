package com.armados.app.epraxeis.diavgeia.extrafields.typeA;

import android.content.Context;

import com.armados.app.epraxeis.Simple;
import com.armados.app.epraxeis.diavgeia.extrafields.DecisionExtraFields;

import java.util.ArrayList;
import java.util.List;

public class TypeA5 extends DecisionExtraFields {
    public String arPraktikou;

    public String getArPraktikou() {
        return arPraktikou;
    }

    public void setArPraktikou(String arPraktikou) {
        this.arPraktikou = arPraktikou;
    }

    @Override
    public List<Simple> getDetailInfoItems(Context context) {
        List<Simple> list = new ArrayList<>();
        return list;
    }

}
