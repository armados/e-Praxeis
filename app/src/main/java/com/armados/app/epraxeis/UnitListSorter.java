package com.armados.app.epraxeis;

import com.armados.app.epraxeis.diaugeia.Unit;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class UnitListSorter {

    public static List<Unit> sort(List<Unit> list) {

        Comparator<Unit> comparator = Comparator
                .comparing(Unit::isActive).reversed()
                .thenComparing(Unit::getLabel);

        Collections.sort(list, comparator);

        return list;
    }
}
