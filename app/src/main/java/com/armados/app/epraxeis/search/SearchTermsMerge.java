package com.armados.app.epraxeis.search;

import java.io.Serializable;
import java.util.ArrayList;

public final class SearchTermsMerge implements  SearchQur, Serializable {
    private static final String TAG = SearchTermsMerge.class.getSimpleName();

    public static final String DELIMITER_AND = "AND";
    public static final String DELIMITER_OR = "OR";

    private final String delimiter;
    private final ArrayList<String> queries = new ArrayList<>();

    public SearchTermsMerge(String delimiter) {
        this.delimiter = delimiter;
    }

    public String buildQuery() {
        final String q = SearchHelper.joinStringList(" " + delimiter + " ", queries);

        return String.format("%s", q);
    }

    public void add(SearchQur query) {
        final String queryText = query.buildQuery();

        if (queryText != null)
            queries.add(queryText);
    }
}
