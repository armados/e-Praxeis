package com.armados.app.epraxeis.diavgeia;

import com.google.gson.annotations.SerializedName;

/**
 *
 * @author Diavgeia Developers
 */
public class DecisionType {
    private String uid;
    @SerializedName("label")
    private String label;
    private String parent;
    private Boolean allowedInDecisions;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public Boolean isAllowedInDecisions() {
        return allowedInDecisions;
    }

    public void setAllowedInDecisions(Boolean allowedInDecisions) {
        this.allowedInDecisions = allowedInDecisions;
    }
}
