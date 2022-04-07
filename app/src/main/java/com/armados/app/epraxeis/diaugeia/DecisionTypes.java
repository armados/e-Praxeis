package com.armados.app.epraxeis.diaugeia;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 *
 * @author Diavgeia Developers
 */
public class DecisionTypes {
    @SerializedName("decisionTypes")
    private List<DecisionType> decisionTypes;

    public List<DecisionType> getDecisionTypes() {
        return decisionTypes;
    }

    public void setDecisionTypes(List<DecisionType> decisionTypes) {
        this.decisionTypes = decisionTypes;
    }
}
