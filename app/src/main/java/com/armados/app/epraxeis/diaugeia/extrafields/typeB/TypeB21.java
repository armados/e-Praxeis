package com.armados.app.epraxeis.diaugeia.extrafields.typeB;

import android.content.Context;

import com.armados.app.epraxeis.Simple;
import com.armados.app.epraxeis.SimpleAFM;
import com.armados.app.epraxeis.diaugeia.extrafields.DecisionExtraFields;
import com.armados.app.epraxeis.diaugeia.extrafields.common.Person;
import com.armados.app.epraxeis.diaugeia.extrafields.common.RelatedAda;
import com.armados.app.epraxeis.diaugeia.extrafields.common.Sponsor;

import java.util.ArrayList;
import java.util.List;

public class TypeB21 extends DecisionExtraFields {
    private Person org;
    private List<Sponsor> sponsor = new ArrayList<>();
    private List<RelatedAda> relatedAnalipsiYpoxreosis = new ArrayList<>();

    public Person getOrg() {
        return org;
    }

    public void setOrg(Person org) {
        this.org = org;
    }

    public List<Sponsor> getSponsor() {
        return sponsor;
    }

    public void setSponsor(List<Sponsor> sponsor) {
        this.sponsor = sponsor;
    }

    public List<RelatedAda> getRelatedAnalipsiYpoxreosis() {
        return relatedAnalipsiYpoxreosis;
    }

    public void setRelatedAnalipsiYpoxreosis(List<RelatedAda> relatedAnalipsiYpoxreosis) {
        this.relatedAnalipsiYpoxreosis = relatedAnalipsiYpoxreosis;
    }

    @Override
    public List<Simple> getDetailInfoItems(Context context) {
        List<Simple> list = new ArrayList<>();

        if (getOrg() != null) {
            SimpleAFM o = new SimpleAFM();
            o.setUid(getOrg().getAfm());
            o.setLabelHeader("Στοιχεία φορέα");
            o.setLabel(showName(getOrg()));
            o.setSubtitle(showAFM(getOrg()));
            list.add(o);
        }

        for (Sponsor sponsor : getSponsor()) {
            final Person person = sponsor.getSponsorAFMName();

            SimpleAFM o = new SimpleAFM();
            o.setLabelHeader("Στοιχεία δικαιούχου");

            if (person != null) {
                o.setUid(person.getAfm());
            }

            o.setLabel(showName(person));
            o.setSubtitle(showAFM(person));

            o.setDescription(showAmount(context, sponsor.getExpenseAmount()));

            list.add(o);
        }

        return list;
    }

}
