package com.kademika.day10.basics.rules;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 27.06.14.
 */
public class TrickyList {
    public static void main(String[] args) {
        List data = new ArrayList();
        List<String> strList = new ArrayList<>();

        data = strList;
        data.add(10);

        String s = strList.get(0);
    }
}
