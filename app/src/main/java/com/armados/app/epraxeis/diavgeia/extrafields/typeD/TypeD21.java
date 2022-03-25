package com.armados.app.epraxeis.diavgeia.extrafields.typeD;

import android.content.Context;

import com.armados.app.epraxeis.Configuration;
import com.armados.app.epraxeis.Database;
import com.armados.app.epraxeis.DictionaryEntity;
import com.armados.app.epraxeis.R;
import com.armados.app.epraxeis.Simple;
import com.armados.app.epraxeis.SimpleLabel;
import com.armados.app.epraxeis.diavgeia.extrafields.DecisionExtraFields;
import com.armados.app.epraxeis.diavgeia.extrafields.common.amountWithVAT;

import java.util.ArrayList;
import java.util.List;

public class TypeD21 extends DecisionExtraFields {
    public List<String> cpv = new ArrayList<>();
    public String contestProgressType;
    public String manifestSelectionCriterion;
    public String manifestContractType;
    public String orgBudgetCode;
    public amountWithVAT estimatedAmountWithVAT;
    public String textRelatedADA;

    public List<String> getCpv() {
        return cpv;
    }

    public void setCpv(List<String> cpv) {
        this.cpv = cpv;
    }

    public String getContestProgressType() {
        return contestProgressType;
    }

    public void setContestProgressType(String contestProgressType) {
        this.contestProgressType = contestProgressType;
    }

    public String getManifestSelectionCriterion() {
        return manifestSelectionCriterion;
    }

    public void setManifestSelectionCriterion(String manifestSelectionCriterion) {
        this.manifestSelectionCriterion = manifestSelectionCriterion;
    }

    public String getManifestContractType() {
        return manifestContractType;
    }

    public void setManifestContractType(String manifestContractType) {
        this.manifestContractType = manifestContractType;
    }

    public String getOrgBudgetCode() {
        return orgBudgetCode;
    }

    public void setOrgBudgetCode(String orgBudgetCode) {
        this.orgBudgetCode = orgBudgetCode;
    }

    public amountWithVAT getEstimatedAmount() {
        return estimatedAmountWithVAT;
    }

    public void setEstimatedAmount(amountWithVAT amountWithVAT) {
        this.estimatedAmountWithVAT = amountWithVAT;
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

        if (getEstimatedAmount() != null) {
            SimpleLabel r = new SimpleLabel();
            r.setLabelHeader(getDocumentType());
            r.setDescription(showAmount(context, getEstimatedAmount()));
            list.add(r);
        }

        for (String cpv : getCpv()) {
            SimpleLabel per = new SimpleLabel();

            DictionaryEntity infoCpv = Database.getInstance(context)
                    .getDictionaryDao()
                    .getEntry(Configuration.DICTIONARY_CPV, cpv);

            if (infoCpv != null)
                per.setLabel("CPV Κωδικός " + infoCpv.getLabel());
            else
                per.setLabel("CPV Κωδικός " + R.string.missing_dict_entry);

            list.add(per);
        }

        return list;
    }

}
