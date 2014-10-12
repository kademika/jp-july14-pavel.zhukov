package com.kademika.day10.f22;


import java.lang.reflect.*;
import java.util.*;

public class ClassInfo<T extends Class> {
    public ClassInfo() {

    }

    public static void printClassInfo(Class c) {
        Class cl = c;
        while (true) {
            if (cl != null) {
                System.out.println("Simple name: " + cl.getSimpleName());
                System.out.println("Package: " + cl.getPackage());
                System.out.println("SuperClasses: " + cl.getSuperclass());
            } else {
                break;
            }
            cl = cl.getSuperclass();
            System.out.println();
        }
    }

    public static void printClassMethods(Class c) {
        Class cl = c;
        while (true) {
            if (cl != null) {
                System.out.println("Class: " + cl.getSimpleName());
                System.out.println("Constructors: ");
                for (Constructor con : cl.getConstructors()) {
                    System.out.println(con.toString());
                }
                System.out.println("Methods:");
                for (Method m : cl.getDeclaredMethods()) {
                    System.out.println(m.toString());
                }
            } else {
                break;
            }
            cl = cl.getSuperclass();
            System.out.println();
        }
    }

    public static void printClassFields(Class c) {
        Class cl = c;
        while (true) {
            if (cl != null) {
                System.out.println("Class: " + cl.getSimpleName());
                System.out.println("Fields: ");
                for (Field f : cl.getDeclaredFields()) {
                    System.out.println(f.toString());
                }
            } else {
                break;
            }
            cl = cl.getSuperclass();
            System.out.println();
        }
    }

    public static <T> T initClass(Class<T> clazz, Map<String, Object> map) throws Exception { // or Class t
        String key = "";
        Object value = null;
        Constructor[] constructors = clazz.getConstructors();
        Constructor ct = null;
        for (Constructor c : constructors) {
            if (constructorByDefault(c)) {
                ct = c;
                break;
            } else {
                ct = c;
            }
        }
        T cl = (T) ct.newInstance();
        Method[] methods = clazz.getMethods();
        if (methods[0] != null) {
            for (Method m : methods) {
                if (m != null) {
                    String methodName = m.getName();
                    if (methodName.startsWith("set")) {
                        for (Map.Entry e : map.entrySet()) {
                            key = e.getKey().toString();
                            if (key.startsWith("is")) {
                                key = key.substring(2, key.length());
                            }
                            value = e.getValue();
                            if (methodName.substring(3, methodName.length()).toLowerCase().equals(key.toLowerCase())) {
                                break;
                            }
                        }
                        m.invoke(cl, value);
                    }
                } else {
                    System.out.println("Can not find any method");
                    break;
                }
            }
        } else {
            System.out.println("Can not find any method");
        }
        return cl;
    }

    private static boolean constructorByDefault(Constructor ct) {
        Class[] paramTypes = ct.getParameterTypes();
        if (paramTypes.length == 0) {
            return true;
        }
        return false;
    }

    public static <T> T initClass(Class<T> clazz, List<Object> list) throws Exception {
        Constructor[] constructors = clazz.getConstructors();
        Constructor ct = null;
        String[] typeClasses = new String[list.size()];
        String[] tpsStr;
        Object[] obj = list.toArray();

        for (int i = 0; i < obj.length; i++) {
            typeClasses[i] = obj[i].getClass().getSimpleName().substring(0, 3).toLowerCase();
        }

        for (Constructor c : constructors) {
            Class[] tps = c.getParameterTypes();
            tpsStr = new String[tps.length];

            for (int i = 0; i < tps.length; i++) {
                tpsStr[i] = tps[i].getSimpleName().substring(0, 3).toLowerCase();
            }
            if (Arrays.equals(typeClasses, tpsStr)) {
                ct = c;
                break;
            }
        }
        T cl = (T) ct.newInstance(obj);

        return cl;
    }

    public static void setPrivates(Object obj, Map<String, Object> map) throws NoSuchFieldException, IllegalAccessException {
        String key = "";
        Object value = null;
        ArrayList<Field[]> listFields = new ArrayList<>();
        Class tmp = obj.getClass();
        Field[] fields;
        while (true) {
            fields = tmp.getDeclaredFields();
            listFields.add(fields);
            tmp = tmp.getSuperclass();
            if (tmp == Object.class || tmp == null) {
                break;
            }
        }

        for (Field[] fl : listFields) {
            for (Field f : fl) {
                String fieldName = f.getName();
                for (Map.Entry e : map.entrySet()) {
                    key = e.getKey().toString();
                    if (key.startsWith("is")) {
                        key = key.substring(2, key.length());
                    }
                    value = e.getValue();
                    if (fieldName.toLowerCase().equals(key.toLowerCase())) {
                        break;
                    }
                }
                f.setAccessible(true);
                f.set(obj, value);
            }
        }
    }
}
