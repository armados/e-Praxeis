package com.armados.app.epraxeis;

import com.armados.app.epraxeis.diaugeia.Organization;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class OrganizationListSorter {

    public static List<Organization> sort(List<Organization> list) {

        Comparator<Organization> comparator = Comparator
                .comparing(Organization::getStatus)
                .thenComparing(Organization::getLabel);

        Collections.sort(list, comparator);

        return list;
    }
}