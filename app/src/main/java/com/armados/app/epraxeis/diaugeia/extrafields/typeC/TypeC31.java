package com.armados.app.epraxeis.diaugeia.extrafields.typeC;

import android.content.Context;

import com.armados.app.epraxeis.Simple;
import com.armados.app.epraxeis.diaugeia.extrafields.DecisionExtraFields;

import java.util.ArrayList;
import java.util.List;

public class TypeC31 extends DecisionExtraFields {
    public String vacancyOpeningType;
    public String textRelatedADA;

    public String getVacancyOpeningType() {
        return vacancyOpeningType;
    }

    public void setVacancyOpeningType(String vacancyOpeningType) {
        this.vacancyOpeningType = vacancyOpeningType;
    }

    public String getTextRelatedADA() {
        return textRelatedADA;
    }

    public void setTextRelatedADA(String textRelatedADA) {
        this.textRelatedADA = textRelatedADA;
    }

    @Override
    public List<Simple> getDetailInfoItems(Context context) {
        List<Simple> list = new ArrayList<>();
        return list;
    }

}
