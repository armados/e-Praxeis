package com.armados.app.epraxeis.search;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public final class SearchTerm implements SearchQur, Serializable {
    public static final String TERM_ORGANIZATION_UID = "organizationUid";
    public static final String TERM_SIGNER_UID = "signerUid";
    public static final String TERM_UNIT_UID = "unitUid";
    public static final String TERM_SUBJECT = "subject";

    private final String term;
    private final List<String> values = new ArrayList<>();

    public SearchTerm(String term) {
        this.term = term;
    }

    public String buildQuery() {
        if (values.size()==0)
            return null;

        final String q = String.format(
                "%s:[\"%s\"]",
                term,
                SearchHelper.joinStringList("\",\"", values)
        );

        return String.format("%s", q);
    }

    public void add(String uid) {
        if (!values.contains(uid))
            values.add(uid);
    }

}
