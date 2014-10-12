package com.kademika.day11.hw4filelist;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Launcher {
    public static void main(String[] args) throws Exception {
        MyList2<String> myList = new MyList2("TestDir" + File.separator + "simpleList.txt");
        myList.add("true");
        myList.add("abcd");
        myList.add("1000");
        myList.add("123456");
        myList.add("abcd");

        System.out.println(myList.size());

        myList.contains(true);
        myList.contains("true1");

        myList.remove("abcd");
        System.out.println(myList.size());

        myList.add("fatality");
        System.out.println(myList.size());

        for (Iterator<String> it = myList.iterator(); it.hasNext(); ) {
            System.out.println(it.next() + " ");
            it.remove();
        }
        System.out.println(myList.size());
    }
}