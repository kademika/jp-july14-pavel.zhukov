package com.kademika.day10.f26;


import java.lang.reflect.InvocationTargetException;

public class Launcher {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException, InvocationTargetException {
//        TestClass tc = ApplicationManager.getServiceClass(TestClass.class);
        TestClass tc1 = ApplicationManager.getService(TestClass.class);
    }
}
