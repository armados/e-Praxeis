package com.armados.app.epraxeis.diavgeia.extrafields.typeB;

import android.content.Context;

import com.armados.app.epraxeis.Simple;
import com.armados.app.epraxeis.SimpleLabel;
import com.armados.app.epraxeis.SimplePersonAFM;
import com.armados.app.epraxeis.diavgeia.extrafields.DecisionExtraFields;
import com.armados.app.epraxeis.diavgeia.extrafields.common.Person;

import java.util.ArrayList;
import java.util.List;

public class TypeB5 extends DecisionExtraFields {
    private Person grantor;
    private List<Person> grantee = new ArrayList<>();
    private String antikeimeno;

    public Person getGrantor() {
        return grantor;
    }

    public void setGrantor(Person grantor) {
        this.grantor = grantor;
    }

    public List<Person> getGrantee() {
        return grantee;
    }

    public void setGrantee(List<Person> grantee) {
        this.grantee = grantee;
    }

    public String getAntikeimeno() {
        return antikeimeno;
    }

    public void setAntikeimeno(String antikeimeno) {
        this.antikeimeno = antikeimeno;
    }

    @Override
    public List<Simple> getDetailInfoItems(Context context) {
        List<Simple> list = new ArrayList<>();

        if (getGrantor() != null) {
            SimpleLabel r = new SimpleLabel();
            r.setLabelHeader("Στοιχεία παραχωρητή");
            r.setLabel(showName(getGrantor()));
            r.setSubtitle(getAntikeimeno());
            list.add(r);
        }

        for (Person person : getGrantee()) {
            SimplePersonAFM per = new SimplePersonAFM();
            per.setUid(person.getAfm());
            per.setLabelHeader("Στοιχεία αποδέκτη");
            per.setLabel(showName(person));
            per.setSubtitle(showAFM(person));
            list.add(per);
        }

        return list;
    }

}
