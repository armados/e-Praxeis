package com.armados.app.epraxeis.diaugeia.extrafields.typeC;

import android.content.Context;

import com.armados.app.epraxeis.Simple;
import com.armados.app.epraxeis.SimpleAFM;
import com.armados.app.epraxeis.diaugeia.extrafields.DecisionExtraFields;
import com.armados.app.epraxeis.diaugeia.extrafields.common.Fek;
import com.armados.app.epraxeis.diaugeia.extrafields.common.amountWithVAT;

import java.util.ArrayList;
import java.util.List;

public class TypeC2 extends DecisionExtraFields {
    public String eidosPraxisSyllogikou;
    public String typeOfSullogiko;
    public amountWithVAT positionSalary;
    public Fek fek;

    public String getEidosPraxisSyllogikou() {
        return eidosPraxisSyllogikou;
    }

    public void setEidosPraxisSyllogikou(String eidosPraxisSyllogikou) {
        this.eidosPraxisSyllogikou = eidosPraxisSyllogikou;
    }

    public String getTypeOfSullogiko() {
        return typeOfSullogiko;
    }

    public void setTypeOfSullogiko(String typeOfSullogiko) {
        this.typeOfSullogiko = typeOfSullogiko;
    }

    public amountWithVAT getPositionSalary() {
        return positionSalary;
    }

    public void setPositionSalary(amountWithVAT positionSalary) {
        this.positionSalary = positionSalary;
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

        if (getPositionSalary() != null && getPositionSalary().getAmount() != 0) {
            SimpleAFM g = new SimpleAFM();
            g.setLabelHeader(getEidosPraxisSyllogikou());
            g.setDescription(showAmount(context, getPositionSalary()));
            list.add(g);
        }

        return list;
    }

}
