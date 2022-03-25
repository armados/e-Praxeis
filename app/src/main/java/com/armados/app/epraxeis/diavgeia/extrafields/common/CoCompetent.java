package com.armados.app.epraxeis.diavgeia.extrafields.common;

import java.util.ArrayList;
import java.util.List;

public class CoCompetent {
    private String ministryId;
    private List<String> unitId = new ArrayList<>();
    private List<String> signerIds = new ArrayList<>();

    public String getMinistryId() {
        return ministryId;
    }

    public void setMinistryId(String ministryId) {
        this.ministryId = ministryId;
    }

    public List<String> getUnitId() {
        return unitId;
    }

    public void setUnitId(List<String> unitId) {
        this.unitId = unitId;
    }

    public List<String> getSignerIds() {
        return signerIds;
    }

    public void setSignerIds(List<String> signerIds) {
        this.signerIds = signerIds;
    }
}
