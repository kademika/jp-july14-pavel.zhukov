package com.kademika.day13.f11;

import java.io.*;

/**
 * Created by Admin on 07.10.2014.
 */
public class CustomObjectIO {
    public static void main(String[] args) throws Exception {
        writeToFile("output.dat");
        readFromFile("output.dat");
    }

    private static void readFromFile(String file) throws Exception {
        try (
                ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)));
        ) {
            Person person = (Person) in.readObject();
            System.out.println(person.getName() + " " + person.getAge() + " years old");
            person = (Person) in.readObject();
            System.out.println(person.getName() + " " + person.getAge() + " years old");
            person = (Person) in.readObject();
            System.out.println(person.getName() + " " + person.getAge() + " years old");
        }
    }

    private static void writeToFile(String file) throws Exception {
        try (
                ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
        ) {
            out.writeObject(new Person("Irina", 26));
            out.writeObject(new Person("Pavel", 35));
            out.writeObject(new Person("Ivan", 40));
        }
    }

    static class Person implements Externalizable {

        private String name;
        private int age;

        public Person() {
        }

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        @Override
        public void writeExternal(ObjectOutput out) throws IOException {
            out.writeInt(age);
            out.writeInt(name.getBytes().length);
            out.write(name.getBytes());
        }

        @Override
        public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
            this.age = in.readInt();
            int length = in.readInt();
            byte[] buff = new byte[length];
            in.read(buff);
            this.name = new String(buff);

        }
    }
}
