package com.armados.app.epraxeis;

public abstract class Simple {
    protected String uid;
    protected String label;

    protected String labelHeader;

    protected String subtitle;
    protected String description;

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

    public String getLabelHeader() {
        return labelHeader;
    }

    public void setLabelHeader(String labelHeader) {
        this.labelHeader = labelHeader;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
