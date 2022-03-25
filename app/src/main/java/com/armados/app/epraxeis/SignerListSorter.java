package com.armados.app.epraxeis;

import com.armados.app.epraxeis.diavgeia.Signer;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SignerListSorter {

    public static List<Signer> sort(List<Signer> list) {

        Comparator<Signer> comparator = Comparator
                .comparing(Signer::isActive).reversed()
                .thenComparing(Signer::getLastName)
                .thenComparing(Signer::getFirstName);

        Collections.sort(list, comparator);

        return list;
    }
}