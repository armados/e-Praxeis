package com.armados.app.epraxeis.search;

import com.armados.app.epraxeis.Configuration;

import java.io.Serializable;

public class SearchQuery implements Serializable {
     private SearchQur terms;
    private int page = 0;
    private int pageSize = Configuration.SEARCH_PAGE_SIZE;

    public SearchQur getTerms() {
        return terms;
    }

    public void setTerms(SearchQur terms) {
        this.terms = terms;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
