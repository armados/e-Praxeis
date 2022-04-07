package com.armados.app.epraxeis.diaugeia.extrafields.typeA;

import android.content.Context;

import com.armados.app.epraxeis.Simple;
import com.armados.app.epraxeis.diaugeia.extrafields.DecisionExtraFields;

import java.util.ArrayList;
import java.util.List;

public class TypeA3 extends DecisionExtraFields {
    public String onomaEgkykliou;

    public String getOnomaEgkykliou() {
        return onomaEgkykliou;
    }

    public void setOnomaEgkykliou(String onomaEgkykliou) {
        this.onomaEgkykliou = onomaEgkykliou;
    }

    @Override
    public List<Simple> getDetailInfoItems(Context context) {
        List<Simple> list = new ArrayList<>();
        return list;
    }

}
