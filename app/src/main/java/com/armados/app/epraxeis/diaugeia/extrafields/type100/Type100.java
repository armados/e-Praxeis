package com.armados.app.epraxeis.diaugeia.extrafields.type100;

import android.content.Context;

import com.armados.app.epraxeis.Simple;
import com.armados.app.epraxeis.SimpleAFM;
import com.armados.app.epraxeis.diaugeia.extrafields.DecisionExtraFields;
import com.armados.app.epraxeis.diaugeia.extrafields.common.amountWithVAT;

import java.util.ArrayList;
import java.util.List;

public class Type100 extends DecisionExtraFields {
    public String eidosPraxis;
    public amountWithVAT positionSalary;
    public String position;
    public String employerOrg;

    public String getEidosPraxis() {
        return eidosPraxis;
    }

    public void setEidosPraxis(String eidosPraxis) {
        this.eidosPraxis = eidosPraxis;
    }

    public amountWithVAT getPositionSalary() {
        return positionSalary;
    }

    public void setPositionSalary(amountWithVAT positionSalary) {
        this.positionSalary = positionSalary;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getEmployerOrg() {
        return employerOrg;
    }

    public void setEmployerOrg(String employerOrg) {
        this.employerOrg = employerOrg;
    }

    @Override
    public List<Simple> getDetailInfoItems(Context context) {
        List<Simple> list = new ArrayList<>();

        if (getPositionSalary() != null && getPositionSalary().getAmount() != 0) {
            SimpleAFM g = new SimpleAFM();
            g.setLabelHeader(getEidosPraxis());
            g.setDescription(showAmount(context, getPositionSalary()));
            list.add(g);
        }

        return list;
    }

}
