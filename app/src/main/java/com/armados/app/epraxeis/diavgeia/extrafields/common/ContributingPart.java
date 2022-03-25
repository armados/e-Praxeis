package com.armados.app.epraxeis.diavgeia.extrafields.common;

import java.util.ArrayList;
import java.util.List;

public class ContributingPart {
    private String ministryId;
    private List<String> signerIds = new ArrayList<>();

    public String getMinistryId() {
        return ministryId;
    }

    public void setMinistryId(String ministryId) {
        this.ministryId = ministryId;
    }

    public List<String> getSignerIds() {
        return signerIds;
    }

    public void setSignerIds(List<String> signerIds) {
        this.signerIds = signerIds;
    }
}
