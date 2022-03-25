package com.armados.app.epraxeis.search;

import java.util.Iterator;
import java.util.List;

public class SearchHelper {

    public static String joinStringList(String delimiter, List<String> list) {
        StringBuilder sb = new StringBuilder();

        Iterator<String> it = list.iterator();
        if (it.hasNext()) {
            sb.append(it.next());
        }
        while (it.hasNext()) {
            sb.append(delimiter).append(it.next());
        }
        return sb.toString();
    }
}
