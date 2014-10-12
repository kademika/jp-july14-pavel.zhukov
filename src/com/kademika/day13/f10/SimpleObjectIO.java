package com.kademika.day13.f10;

import java.io.*;

/**
 * Created by Admin on 07.10.2014.
 */
public class SimpleObjectIO {
    public static void main(String[] args) throws Exception {
        writeToFile("output.dat");
        readFromFile("output.dat");
    }

    private static void readFromFile(String file) throws Exception {
        try (
                ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)));
        ) {
            Person person = (Person) in.readObject();
            System.out.println(person.name + " " + person.surname);
            person = (Person) in.readObject();
            System.out.println(person.name + " " + person.surname);
            person = (Person) in.readObject();
            System.out.println(person.name + " " + person.surname);
        }
    }

    private static void writeToFile(String file) throws Exception {
        try (
                ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
        ) {
            out.writeObject(new Person("Irina", "Popova"));
            out.writeObject(new Person("Semen", "Slepakov"));
            out.writeObject(new Person("Nikolay", "Koster-Waldau"));
        }
    }

    static class Person implements Serializable {

        private String name;
        private String surname;

        public Person(String name, String surname) {
            this.name = name;
            this.surname = surname;
        }
    }
}
