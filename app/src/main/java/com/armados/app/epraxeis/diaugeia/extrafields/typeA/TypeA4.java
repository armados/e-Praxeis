package com.armados.app.epraxeis.diaugeia.extrafields.typeA;

import android.content.Context;

import com.armados.app.epraxeis.Simple;
import com.armados.app.epraxeis.diaugeia.extrafields.DecisionExtraFields;

import java.util.ArrayList;
import java.util.List;

public class TypeA4 extends DecisionExtraFields {
    public String gnomodotisiEidosForea;

    public String getGnomodotisiEidosForea() {
        return gnomodotisiEidosForea;
    }

    public void setGnomodotisiEidosForea(String gnomodotisiEidosForea) {
        this.gnomodotisiEidosForea = gnomodotisiEidosForea;
    }

    @Override
    public List<Simple> getDetailInfoItems(Context context) {
        List<Simple> list = new ArrayList<>();
        return list;
    }

}
