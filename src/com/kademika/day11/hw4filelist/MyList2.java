package com.kademika.day11.hw4filelist;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class MyList2<T> implements SimpleList {
    private List<String> list;
    private File file;
    private FileWriter fileWriter;
    private FileInputStream fileInputStream;
    private InputStreamReader reader;
    private BufferedReader bufferedReader;
    private boolean editFlag = false;
    private String value = "";
    private StringBuilder stringBuilder = null;

    public MyList2(String fileName) throws Exception {
        list = new ArrayList<>();
        file = new File(fileName);

        if (!file.exists()) {
            file.createNewFile();
        }
        readFromFile();
        editFlag = false;

    }

    private String enteredValue(Object obj) {
        return obj.toString();
    }

    private void readFromFile() throws Exception {
        openInputStream();

        String str;
        while ((str = bufferedReader.readLine()) != null) {
            list.add(str);
        }
    }

    private void writeInFile() {
        if (!editFlag) return;
        stringBuilder = new StringBuilder();

        try {
            fileWriter = new FileWriter(file);
            for (String str : list) {
                stringBuilder.append(str + "" + System.getProperty("line.separator"));
            }
            fileWriter.write(stringBuilder.toString());
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        editFlag = false;
    }

    private void openInputStream() throws Exception {
        fileInputStream = new FileInputStream(file);
        reader = new InputStreamReader(fileInputStream);
        bufferedReader = new BufferedReader(reader);
    }

    @Override
    public void add(Object object) {
        value = enteredValue(object);
        if (value != null) {
            list.add(value);
            editFlag = true;
        } else {
            System.out.println("Entered value must to be not null!");
        }
        writeInFile();
    }

    @Override
    public boolean contains(Object object) {
        value = enteredValue(object);
        return list.contains(value);
    }

    @Override
    public void remove(Object object) {
        value = enteredValue(object);
        if (contains(object)) {
            list.remove(value);
            editFlag = true;
        } else {
            System.out.println("*REMOVE* Can't to remove this object: It is not in the List");
        }
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public Iterator<String> iterator() {
        return new MLIterator();
    }

    class MLIterator<String> implements Iterator<String> {
        private Iterator iterator = list.iterator();

        @Override
        public boolean hasNext() {
            return iterator.hasNext();
        }

        @Override
        public String next() {
            return (String) iterator.next();
        }

        @Override
        public void remove() {
            iterator.remove();
            editFlag = true;
            writeInFile();
        }
    }
}
