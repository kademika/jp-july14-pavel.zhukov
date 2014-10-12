package com.kademika.day10.f26;


import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ApplicationManager {
    public static <T> T getServiceClass(Class<T> clazz) throws InvocationTargetException, IllegalAccessException, InstantiationException {
        for (Annotation a : clazz.getAnnotations()) {
            if (a.annotationType() == Service.class) {
                System.out.print(clazz.getSimpleName() + " - ");
                System.out.println("This Class used annotation @Service");
                Method[] methods = clazz.getMethods();
                for (Method m : methods) {

                    if (m.isAnnotationPresent(InitService.class)) {
                        T cl = clazz.newInstance();
                        m.invoke(cl);
                        return cl;
                    }
                }
            }
        }
        return null;
    }

    public static <T> T getService(Class<T> clazz) throws InvocationTargetException, IllegalAccessException, InstantiationException {
        for (Annotation a : clazz.getAnnotations()) {
            if (a.annotationType() == Service.class) {
                System.out.print(clazz.getSimpleName() + " - ");
                System.out.println("This Class used annotation @Service");
                Method[] methods = clazz.getMethods();
                for (Method m : methods) {
                    if (m.isAnnotationPresent(Service.class)) {
                        T cl = clazz.newInstance();
                        m.invoke(cl);
                        return cl;
                    }
                }
            }
        }
        return null;
    }
}
