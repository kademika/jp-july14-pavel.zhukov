package com.kademika.day10.f21;


import java.io.File;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Created by Admin on 07.07.14.
 */
public class DescendantNumber {
    public static void main(String[] args) throws Exception {
        Class myClass = Class.forName("java.lang.Number");
        Package pack = myClass.getPackage();
        String clPackName = (pack.getName() + "." + myClass.getSimpleName()).replace(".", "/") + ".class";

        URL url = ClassLoader.getSystemResource(clPackName);
        String fileName = URLDecoder.decode(url.getFile(), "UTF-8");
        fileName = fileName.substring(6, fileName.indexOf("!"));

        File file = new File(fileName);
        JarFile jf = new JarFile(file);
        Enumeration entries = jf.entries();

        ArrayList<String> listNumber = new ArrayList<>();

        while (entries.hasMoreElements()) {
            JarEntry entry = (JarEntry) entries.nextElement();
            if (!entry.getName().endsWith(".class") || !entry.getName().substring(0, 10).equals("java/lang/")) {
                continue;
            }
            String str = entry.getName().replace("/", ".");
            str = str.substring(0, str.indexOf(".class"));
            Class c = Class.forName(str);
            if (c.getSuperclass() == Number.class) {
                listNumber.add(c.getSimpleName());
            }
        }
        for (String s : listNumber) {
            System.out.println(s);
        }
    }
}
