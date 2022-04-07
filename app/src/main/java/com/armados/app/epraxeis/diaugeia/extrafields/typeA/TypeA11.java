package com.armados.app.epraxeis.diaugeia.extrafields.typeA;

import android.content.Context;

import com.armados.app.epraxeis.Simple;
import com.armados.app.epraxeis.diaugeia.extrafields.DecisionExtraFields;
import com.armados.app.epraxeis.diaugeia.extrafields.common.Fek;
import com.armados.app.epraxeis.diaugeia.extrafields.common.Law;

import java.util.ArrayList;
import java.util.List;

public class TypeA11 extends DecisionExtraFields {
    public Fek fek;
    public Law law;
    public Boolean ecpInlining;
    public Boolean interCountryLaw;
    public Boolean passAuthority;

    public Fek getFek() {
        return fek;
    }

    public void setFek(Fek fek) {
        this.fek = fek;
    }

    public Law getLaw() {
        return law;
    }

    public void setLaw(Law law) {
        this.law = law;
    }

    public Boolean getEcpInlining() {
        return ecpInlining;
    }

    public void setEcpInlining(Boolean ecpInlining) {
        this.ecpInlining = ecpInlining;
    }

    public Boolean getInterCountryLaw() {
        return interCountryLaw;
    }

    public void setInterCountryLaw(Boolean interCountryLaw) {
        this.interCountryLaw = interCountryLaw;
    }

    public Boolean getPassAuthority() {
        return passAuthority;
    }

    public void setPassAuthority(Boolean passAuthority) {
        this.passAuthority = passAuthority;
    }

    @Override
    public List<Simple> getDetailInfoItems(Context context) {
        List<Simple> list = new ArrayList<>();
        return list;
    }

}
