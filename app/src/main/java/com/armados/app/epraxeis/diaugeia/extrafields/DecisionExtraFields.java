package com.armados.app.epraxeis.diaugeia.extrafields;

import android.content.Context;

import com.armados.app.epraxeis.Config;
import com.armados.app.epraxeis.Database;
import com.armados.app.epraxeis.DictionaryEntity;
import com.armados.app.epraxeis.Simple;
import com.armados.app.epraxeis.diaugeia.extrafields.common.Person;
import com.armados.app.epraxeis.diaugeia.extrafields.common.RelatedDecision;
import com.armados.app.epraxeis.diaugeia.extrafields.common.amountWithVAT;

import java.util.ArrayList;
import java.util.List;

public abstract class DecisionExtraFields {
    /* Ετικέτα με το όνομα της κλάσης */
    protected final String TAG = getClass().getSimpleName();

    /* Τύπος απόφασης */
    protected String documentType;

    /* Λίστα σχετικών αποφάσεων */
    protected List<RelatedDecision> relatedDecisions = new ArrayList<>();

    public List<RelatedDecision> getRelatedDecisions() {
        return relatedDecisions;
    }

    public void setRelatedDecisions(List<RelatedDecision> relatedDecisions) {
        this.relatedDecisions = relatedDecisions;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public abstract List<Simple> getDetailInfoItems(Context context);

    protected String showName(final Person person) {
        if (person == null || person.getName() == null)
            return "(Δεν δηλώθηκε επωνυμία)";
        else
            return person.getName();
    }

    protected String showAFM(final Person person) {
        if (person == null || person.getAfm() == null || person.getAfm().equals(""))
            return String.format("ΑΦΜ %s", "(Δεν δηλώθηκε)");
        else
            return String.format("ΑΦΜ %s", person.getAfm());
    }

    protected String showAmount(final Context context, final amountWithVAT amountWithVAT) {
        if (amountWithVAT == null || amountWithVAT.getAmount() == 0) {
            return String.format("ΠΟΣΟ %s", "(Δεν δηλώθηκε)");
        } else if (amountWithVAT.getCurrency() != null) {
            DictionaryEntity currency = Database.getInstance(context)
                    .getDictionaryDao()
                    .getEntry(Config.DICTIONARY_CURRENCIES, amountWithVAT.getCurrency());
            if (currency != null)
                return String.format("ΠΟΣΟ %s %s", amountWithVAT.getAmount(), currency.getLabel());
            else
                return String.format("ΠΟΣΟ %s", amountWithVAT.getAmount());

        } else {
            return String.format("ΠΟΣΟ %s", amountWithVAT.getAmount());
        }

    }

    protected String showAmountWithoutCurrency(double amount) {
        if (amount == 0) {
            return String.format("ΠΟΣΟ %s", "(Δεν δηλώθηκε)");
        } else {
            final String val = String.valueOf(amount);
            return String.format("ΠΟΣΟ %s", val);
        }
    }

}
