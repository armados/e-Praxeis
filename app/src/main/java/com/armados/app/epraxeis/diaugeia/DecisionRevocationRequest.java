package com.armados.app.epraxeis.diaugeia;

/**
 *
 * @author Diavgeia Developers
 */
public class DecisionRevocationRequest {
    private String ada;
    private String comment;

    public DecisionRevocationRequest() {
    }

    public DecisionRevocationRequest(String ada, String comment) {
        this.ada = ada;
        this.comment = comment;
    }

    public String getAda() {
        return ada;
    }

    public void setAda(String ada) {
        this.ada = ada;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
