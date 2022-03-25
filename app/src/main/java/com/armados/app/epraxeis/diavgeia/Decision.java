package com.armados.app.epraxeis.diavgeia;

import android.util.Log;

import com.armados.app.epraxeis.diavgeia.extrafields.DecisionExtraFields;
import com.armados.app.epraxeis.diavgeia.extrafields.type100.Type100;
import com.armados.app.epraxeis.diavgeia.extrafields.type2461.Type2461;
import com.armados.app.epraxeis.diavgeia.extrafields.type2471.Type2471;
import com.armados.app.epraxeis.diavgeia.extrafields.typeA.TypeA11;
import com.armados.app.epraxeis.diavgeia.extrafields.typeA.TypeA12;
import com.armados.app.epraxeis.diavgeia.extrafields.typeA.TypeA2;
import com.armados.app.epraxeis.diavgeia.extrafields.typeA.TypeA3;
import com.armados.app.epraxeis.diavgeia.extrafields.typeA.TypeA4;
import com.armados.app.epraxeis.diavgeia.extrafields.typeA.TypeA5;
import com.armados.app.epraxeis.diavgeia.extrafields.typeA.TypeA6;
import com.armados.app.epraxeis.diavgeia.extrafields.typeA.TypeA7;
import com.armados.app.epraxeis.diavgeia.extrafields.typeB.TypeB11;
import com.armados.app.epraxeis.diavgeia.extrafields.typeB.TypeB12;
import com.armados.app.epraxeis.diavgeia.extrafields.typeB.TypeB13;
import com.armados.app.epraxeis.diavgeia.extrafields.typeB.TypeB21;
import com.armados.app.epraxeis.diavgeia.extrafields.typeB.TypeB22;
import com.armados.app.epraxeis.diavgeia.extrafields.typeB.TypeB3;
import com.armados.app.epraxeis.diavgeia.extrafields.typeB.TypeB4;
import com.armados.app.epraxeis.diavgeia.extrafields.typeB.TypeB5;
import com.armados.app.epraxeis.diavgeia.extrafields.typeB.TypeB6;
import com.armados.app.epraxeis.diavgeia.extrafields.typeC.TypeC2;
import com.armados.app.epraxeis.diavgeia.extrafields.typeC.TypeC31;
import com.armados.app.epraxeis.diavgeia.extrafields.typeC.TypeC32;
import com.armados.app.epraxeis.diavgeia.extrafields.typeC.TypeC33;
import com.armados.app.epraxeis.diavgeia.extrafields.typeC.TypeC34;
import com.armados.app.epraxeis.diavgeia.extrafields.typeC.TypeC35;
import com.armados.app.epraxeis.diavgeia.extrafields.typeC.TypeC36;
import com.armados.app.epraxeis.diavgeia.extrafields.typeD.TypeD1;
import com.armados.app.epraxeis.diavgeia.extrafields.typeD.TypeD21;
import com.armados.app.epraxeis.diavgeia.extrafields.typeD.TypeD22;
import com.armados.app.epraxeis.diavgeia.extrafields.typeE.TypeE1;
import com.armados.app.epraxeis.diavgeia.extrafields.typeE.TypeE2;
import com.armados.app.epraxeis.diavgeia.extrafields.typeE.TypeE3;
import com.armados.app.epraxeis.diavgeia.extrafields.typeE.TypeE4;
import com.armados.app.epraxeis.diavgeia.extrafields.typeZ.TypeZ1;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Diavgeia Developers
 */
public class Decision {
    private String protocolNumber;
    private String subject;
    private Date issueDate;
    private String organizationId;
    private List<String> signerIds;
    private List<String> unitIds;
    private String decisionTypeId;
    private List<String> thematicCategoryIds;
    private Map<String, ? extends Object> extraFieldValues;
    private boolean privateData;
    private String ada;
    private Date publishTimestamp;
    private Date submissionTimestamp;
    private String versionId;
    private String status;
    private String url;
    private String documentUrl;
    private String documentChecksum;
    private String correctedVersionId;
    private List<Attachment> attachments;

    public String getProtocolNumber() {
        return protocolNumber;
    }

    public void setProtocolNumber(String protocolNumber) {
        this.protocolNumber = protocolNumber;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public List<String> getSignerIds() {
        return signerIds;
    }

    public void setSignerIds(List<String> signerIds) {
        this.signerIds = signerIds;
    }

    public List<String> getUnitIds() {
        return unitIds;
    }

    public void setUnitIds(List<String> unitIds) {
        this.unitIds = unitIds;
    }

    public String getDecisionTypeId() {
        return decisionTypeId;
    }

    public void setDecisionTypeId(String decisionTypeId) {
        this.decisionTypeId = decisionTypeId;
    }

    public List<String> getThematicCategoryIds() {
        return thematicCategoryIds;
    }

    public void setThematicCategoryIds(List<String> thematicCategoryIds) {
        this.thematicCategoryIds = thematicCategoryIds;
    }

    public Map<String, ? extends Object> getExtraFieldValues() {
        return extraFieldValues;
    }

    public void setExtraFieldValues(Map<String, ? extends Object> extraFieldValues) {
        this.extraFieldValues = extraFieldValues;
    }

    public boolean isPrivateData() {
        return privateData;
    }

    public void setPrivateData(boolean privateData) {
        this.privateData = privateData;
    }

    public String getAda() {
        return ada;
    }

    public void setAda(String ada) {
        this.ada = ada;
    }

    public Date getPublishTimestamp() {
        return publishTimestamp;
    }

    public void setPublishTimestamp(Date publishTimestamp) {
        this.publishTimestamp = publishTimestamp;
    }

    public Date getSubmissionTimestamp() {
        return submissionTimestamp;
    }

    public void setSubmissionTimestamp(Date submissionTimestamp) {
        this.submissionTimestamp = submissionTimestamp;
    }

    public String getVersionId() {
        return versionId;
    }

    public void setVersionId(String versionId) {
        this.versionId = versionId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDocumentUrl() {
        return documentUrl;
    }

    public void setDocumentUrl(String documentUrl) {
        this.documentUrl = documentUrl;
    }

    public String getDocumentChecksum() {
        return documentChecksum;
    }

    public void setDocumentChecksum(String documentChecksum) {
        this.documentChecksum = documentChecksum;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }

    public String getCorrectedVersionId() {
        return correctedVersionId;
    }

    public void setCorrectedVersionId(String correctedVersionId) {
        this.correctedVersionId = correctedVersionId;
    }

    // ===================

    private final String TAG = getClass().getSimpleName();

    private DecisionExtraFields extraFields;

    public DecisionExtraFields getExtraFields() {
        return extraFields;
    }

    public void setExtraFields(DecisionExtraFields extraFields) {
        this.extraFields = extraFields;
    }

    //private static final Gson gson = new Gson();

    public void processExtraFields() {

        try {

            Gson gson = new Gson();

            String json = gson.toJson(getExtraFieldValues());

            switch (getDecisionTypeId()) {
                /* 2.4.1 ΠΡΑΞΕΙΣ ΚΑΝΟΝΙΣΤΙΚΟΥ ΠΕΡΙΕΧΟΜΕΝΟΥ, ΕΓΚΥΚΛΙΟΙ, ΓΝΩΜΟΔΟΤΗΣΕΙΣ */
                case "Α.1.1":
                    setExtraFields(gson.fromJson(json, TypeA11.class));
                    break; // ΝΟΜΟΣ
                case "Α.1.2":
                    setExtraFields(gson.fromJson(json, TypeA12.class));
                    break; // ΠΡΑΞΗ ΝΟΜΟΘΕΤΙΚΟΥ ΠΕΡΙΕΧΟΜΕΝΟΥ
                case "Α.2":
                    setExtraFields(gson.fromJson(json, TypeA2.class));
                    break;   // ΚΑΝΟΝΙΣΤΙΚΗ ΠΡΑΞΗ
                case "Α.3":
                    setExtraFields(gson.fromJson(json, TypeA3.class));
                    break;   // ΕΓΚΥΚΛΙΟΣ
                case "Α.4":
                    setExtraFields(gson.fromJson(json, TypeA4.class));
                    break;   // ΓΝΩΜΟΔΟΤΗΣΗ
                case "Α.5":
                    setExtraFields(gson.fromJson(json, TypeA5.class));
                    break;   // ΠΡΑΚΤΙΚΑ (Νομικού Συμβουλίου του Κράτους)
                case "Α.6":
                    setExtraFields(gson.fromJson(json, TypeA6.class));
                    break;   // ΕΚΘΕΣΗ ΑΠΟΤΙΜΗΣΗΣ ΓΙΑ ΤΗΝ ΚΑΤΑΣΤΑΣΗ ΤΗΣ ΥΦΙΣΤΑΜΕΝΗΣ ΝΟΜΟΘΕΣΙΑΣ
                case "Α.7":
                    setExtraFields(gson.fromJson(json, TypeA7.class));
                    break;   // ΕΚΘΕΣΗ ΤΗΣ ΚΕΝΤΡΙΚΗΣ ΕΠΙΤΡΟΠΗΣ ΚΩΔΙΚΟΠΟΙΗΣΗΣ

                /* 2.4.2 ΠΡΑΞΕΙΣ ΟΙΚΟΝΟΜΙΚΟΥ ΠΕΡΙΕΧΟΜΕΝΟΥ */
                case "Β.1.1":
                    setExtraFields(gson.fromJson(json, TypeB11.class));
                    break; // ΕΓΚΡΙΣΗ ΠΡΟΥΠΟΛΟΓΙΣΜΟΥ
                case "Β.1.2": // ΕΠΙΤΡΟΠΙΚΟ ΕΝΤΑΛΜΑ
                    setExtraFields(gson.fromJson(json, TypeB12.class));
                    break;
                case "Β.1.3": // ΑΝΑΛΗΨΗ ΥΠΟΧΡΕΩΣΗΣ - sponsor
                    setExtraFields(gson.fromJson(json, TypeB13.class));
                    break;
                case "Β.3":
                    setExtraFields(gson.fromJson(json, TypeB3.class));
                    break;   // ΙΣΟΛΟΓΙΣΜΟΣ – ΑΠΟΛΟΓΙΣΜΟΣ
                case "Β.2.1": // ΕΓΚΡΙΣΗ ΔΑΠΑΝΗΣ
                    setExtraFields(gson.fromJson(json, TypeB21.class));
                    break;
                case "Β.2.2": // ΟΡΙΣΤΙΚΟΠΟΙΗΣΗ ΠΛΗΡΩΜΗΣ
                    setExtraFields(gson.fromJson(json, TypeB22.class));
                    break;
                case "Β.4":   // ΔΩΡΕΑ - ΕΠΙΧΟΡΗΓΗΣΗ
                    setExtraFields(gson.fromJson(json, TypeB4.class));
                    break;
                case "Β.5":    // ΠΑΡΑΧΩΡΗΣΗ ΧΡΗΣΗΣ ΠΕΡΙΟΥΣΙΑΚΩΝ ΣΤΟΙΧΕΙΩΝ
                    setExtraFields(gson.fromJson(json, TypeB5.class));
                    break;
                case "Β.6":
                    setExtraFields(gson.fromJson(json, TypeB6.class));
                    break;   // ΠΡΟΓΡΑΜΜΑΤΙΚΗ ΣΥΜΦΩΝΙΑ ΟΙΚΟΝΟΜΙΚΗΣ ΥΠΟΣΤΗΡΙΞΗΣ

                /* 2.4.3 ΠΡΑΞΕΙΣ ΟΡΓΑΝΩΤΙΚΟΥ ΚΑΙ ΔΙΟΙΚΗΤΙΚΟΥ ΠΕΡΙΕΧΟΜΕΝΟΥ */
                case "100":
                    setExtraFields(gson.fromJson(json, Type100.class));
                    break;   // ΠΡΑΞΗ ΠΟΥ ΑΦΟΡΑ ΣΕ ΘΕΣΗ ΓΕΝΙΚΟΥ - ΕΙΔΙΚΟΥ ΓΡΑΜΜΑΤΕΑ - ΜΟΝΟΜΕΛΕΣ ΟΡΓΑΝΟ
                case "Γ.2":
                    setExtraFields(gson.fromJson(json, TypeC2.class));
                    break;   // ΠΡΑΞΗ ΠΟΥ ΑΦΟΡΑ ΣΕ ΣΥΛΛΟΓΙΚΟ ΟΡΓΑΝΟ - ΕΠΙΤΡΟΠΗ - ΟΜΑΔΑ ΕΡΓΑΣΙΑΣ - ΟΜΑΔΑ ΕΡΓΟΥ - ΜΕΛΗ ΣΥΛΛΟΓΙΚΟΥ ΟΡΓΑΝΟΥ
                case "Γ.3.1":
                    setExtraFields(gson.fromJson(json, TypeC31.class));
                    break; // ΠΡΟΚΗΡΥΞΗ ΠΛΗΡΩΣΗΣ ΘΕΣΕΩΝ
                case "Γ.3.2":
                    setExtraFields(gson.fromJson(json, TypeC32.class));
                    break; // ΠΙΝΑΚΕΣ ΕΠΙΤΥΧΟΝΤΩΝ, ΔΙΟΡΙΣΤΕΩΝ & ΕΠΙΛΑΧΟΝΤΩΝ
                case "Γ.3.3":
                    setExtraFields(gson.fromJson(json, TypeC33.class));
                    break; // ΔΙΟΡΙΣΜΟΣ
                case "Γ.3.4":        // ΣΥΜΒΑΣΗ
                    setExtraFields(gson.fromJson(json, TypeC34.class));
                    break;
                case "Γ.3.5":
                    setExtraFields(gson.fromJson(json, TypeC35.class));
                    break; // ΥΠΗΡΕΣΙΑΚΗ ΜΕΤΑΒΟΛΗ
                case "Γ.3.6":
                    setExtraFields(gson.fromJson(json, TypeC36.class));
                    break; // ΑΘΩΩΤΙΚΗ ΠΕΙΘΑΡΧΙΚΗ ΑΠΟΦΑΣΗ

                /* 2.4.4 ΠΡΑΞΕΙΣ ΑΝΑΘΕΣΕΩΝ ΠΡΟΜΗΘΕΙΩΝ ΚΑΙ ΔΙΑΓΩΝΙΣΜΩΝ - ΔΗΜΟΣΙΩΝ ΣΥΜΒΑΣΕΩΝ */
                case "Δ.1":   // ΑΝΑΘΕΣΗ ΕΡΓΩΝ / ΠΡΟΜΗΘΕΙΩΝ / ΥΠΗΡΕΣΙΩΝ / ΜΕΛΕΤΩΝ
                    setExtraFields(gson.fromJson(json, TypeD1.class));
                    break;
                case "Δ.2.1": // ΠΕΡΙΛΗΨΗ ΔΙΑΚΗΡΥΞΗΣ
                    setExtraFields(gson.fromJson(json, TypeD21.class));
                    break;
                case "Δ.2.2": // ΚΑΤΑΚΥΡΩΣΗ
                    setExtraFields(gson.fromJson(json, TypeD22.class));
                    break;

                /* 2.4.5 ΠΡΑΞΕΙΣ ΑΝΑΠΤΥΞΙΑΚΩΝ ΝΟΜΩΝ */
                case "Ε.1":
                    setExtraFields(gson.fromJson(json, TypeE1.class));
                    break;   // ΠΡΑΞΗ ΥΠΑΓΩΓΗΣ ΕΠΕΝΔΥΣΕΩΝ
                case "Ε.2":
                    setExtraFields(gson.fromJson(json, TypeE2.class));
                    break;   // ΣΥΜΒΑΣΗ-ΠΡΑΞΕΙΣ ΑΝΑΠΤΥΞΙΑΚΩΝ ΝΟΜΩΝ
                case "Ε.3":
                    setExtraFields(gson.fromJson(json, TypeE3.class));
                    break;   // ΑΠΟΦΑΣΗ ΕΝΑΡΞΗΣ ΠΑΡΑΓΩΓΙΚΗΣ ΛΕΙΤΟΥΡΓΙΑΣ ΕΠΕΝΔΥΣΗΣ
                case "Ε.4":
                    setExtraFields(gson.fromJson(json, TypeE4.class));
                    break;   // ΑΛΛΗ ΠΡΑΞΗ ΑΝΑΠΤΥΞΙΑΚΟΥ ΝΟΜΟΥ

                /* 2.4.6 ΠΡΑΞΕΙΣ ΧΩΡΟΤΑΞΙΚΟΥ - ΠΟΛΕΟΔΟΜΙΚΟΥ ΠΕΡΙΕΧΟΜΕΝΟΥ */
                case "2.4.6.1":        // ΠΡΑΞΕΙΣ ΧΩΡΟΤΑΞΙΚΟΥ - ΠΟΛΕΟΔΟΜΙΚΟΥ ΠΕΡΙΕΧΟΜΕΝΟΥ
                    setExtraFields(gson.fromJson(json, Type2461.class));
                    break;

                /* 2.4.7 ΛΟΙΠΕΣ ΑΤΟΜΙΚΕΣ ΔΙΟΙΚΗΤΙΚΕΣ ΠΡΑΞΕΙΣ */
                case "2.4.7.1":
                    setExtraFields(gson.fromJson(json, Type2471.class));
                    break; // ΛΟΙΠΕΣ ΑΤΟΜΙΚΕΣ ΔΙΟΙΚΗΤΙΚΕΣ ΠΡΑΞΕΙΣ

                /* 2.4.8 ΛΟΙΠΕΣ ΠΡΑΞΕΙΣ */
                case "Ζ.1":
                    setExtraFields(gson.fromJson(json, TypeZ1.class));
                    break;   // ΔΗΜΟΣΙΑ ΠΡΟΤΥΠΑ ΕΓΓΡΑΦΑ

                default:
                    Log.e(TAG, "Undefined Decision Type: " + getDecisionTypeId());
                    break;
            }

        } catch (JsonSyntaxException e) {
            Log.e(TAG, "Invalid JSON decision type for ADA " + getAda());
        }

    }
}
