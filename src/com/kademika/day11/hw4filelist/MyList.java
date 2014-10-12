package com.kademika.day11.hw4filelist;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;


public class MyList<T> implements SimpleList {
    private File file;
    private FileWriter fileWriter;
    private int id = 0;
    private int position = 0;
    private FileInputStream fileInputStream;
    private InputStreamReader reader;
    private BufferedReader bufferedReader;
    private String editStr = "";
    private boolean addFlag = true;
    private String value = "";
    private StringBuilder stringBuilder = null;

    public MyList(String fileName) throws IOException {
        file = new File(fileName);
        if (!file.exists()) {
            file.createNewFile();
            fileWriter = new FileWriter(file);
            fileWriter.write(id + ".;\n");
            id++;
            fileWriter.close();
        }
    }

    private String enteredValue(Object obj) {
        return obj.toString();
    }


    @Override
    public void add(Object object) {
        value = enteredValue(object);
        String resultStr = "";
        stringBuilder = new StringBuilder();
        addFlag = true;

        try {

            fileInputStream = new FileInputStream(file);
            reader = new InputStreamReader(fileInputStream);
            bufferedReader = new BufferedReader(reader);

            position = getMaxIdOfList() + 1;
            String str;
            while ((str = bufferedReader.readLine()) != null) {
                if (addObjInExistingStr(str, value) == 1) {
                    str = editStr;
                }
                str = str + "\n";
//                System.out.println(Integer.parseInt(str.substring(0, str.indexOf("."))));
                stringBuilder.append(str);
            }

            resultStr = stringBuilder.toString();
            if (addFlag) {
                resultStr = resultStr + position + "." + value + ";\n";
                addFlag = false;
                id++;
            }

            fileWriter = new FileWriter(file);
            fileWriter.write(resultStr);
            fileWriter.close();
            System.out.println("*ADD* Object '" + value + "' is added in List");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int addObjInExistingStr(String str, String obj) throws IOException {
        if (addFlag) {
            if (str.length() == 0) return 0;
            if (str.substring(str.indexOf(".") + 1, str.indexOf(";")).length() == 0) {
//                position = Integer.parseInt(str.substring(0, str.indexOf(".")));
                editStr = position + "." + obj + ";";
                addFlag = false;
                return 1;
            }
        }
        return 0;
    }

    private int getMaxIdOfList() {
        int num = 0;
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            InputStreamReader reader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(reader);

            String str;
            int k;
            while ((str = bufferedReader.readLine()) != null) {
                k = Integer.parseInt(str.substring(0, str.indexOf(".")));
                if (k > num) {
                    num = k;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return num;

    }

    @Override
    public boolean contains(Object object) {
        value = enteredValue(object);

        try {
            fileInputStream = new FileInputStream(file);
            reader = new InputStreamReader(fileInputStream);
            bufferedReader = new BufferedReader(reader);
            String str;
            while ((str = bufferedReader.readLine()) != null) {
                if (str.length() != 0 && str.substring(str.indexOf(".") + 1, str.indexOf(";")).equals(value)) {
                    position = Integer.parseInt(str.substring(0, str.indexOf(".")));
                    System.out.println("*CONTAIN* Entered value '" + value + "' is present in this List");
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("*CONTAIN* There is no such value '" + value + "' in this List");
        return false;
    }

    @Override
    public void remove(Object object) {
        if (contains(object)) {
            stringBuilder = new StringBuilder();
            String str;
            try {
                fileInputStream = new FileInputStream(file);
                reader = new InputStreamReader(fileInputStream);
                bufferedReader = new BufferedReader(reader);
                while ((str = bufferedReader.readLine()) != null) {
                    if (position == Integer.parseInt(str.substring(0, str.indexOf(".")))) {
                        str = position + ".;";
                    }
                    str = str + "\n";
                    stringBuilder.append(str);
                }

                fileWriter = new FileWriter(file);
                fileWriter.write(stringBuilder.toString());
                fileWriter.close();
                System.out.println("*REMOVE* Object '" + value + "' is removed from List");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("*REMOVE* Can't to remove this object: It is not in the List");
        }
    }

    @Override
    public int size() {
        int count = 0;
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            InputStreamReader reader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(reader);

            String str;
            while ((str = bufferedReader.readLine()) != null) {
                if (str.length() != 0 && str.substring(str.indexOf(".") + 1, str.indexOf(";")).length() != 0) {
                    count++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.print("*SIZE* Number of records in this List: ");
        return count;
    }

    @Override
    public Iterator iterator() {
        return new MLIterator(this);
    }

    class MLIterator implements Iterator<Object> {
        private int idElement = -1;
        private ArrayList<String> list;
        private MyList myList;

        public MLIterator(MyList ml) {
            this.myList = ml;
            list = new ArrayList<>();
//            ArrayList<String> listInString = new ArrayList<>();
            try {

                fileInputStream = new FileInputStream(file);
                reader = new InputStreamReader(fileInputStream);
                bufferedReader = new BufferedReader(reader);

                String str;
                while ((str = bufferedReader.readLine()) != null) {
                    if (str.length() != 0 && str.substring(str.indexOf(".") + 1, str.indexOf(";")).length() != 0) {
                        list.add(str);
                    }
                }
//                listInString =new String[list.size()];
                String tmp;

                for (int i = 0; i < list.size() - 1; i++) {
//                    System.out.println(Integer.parseInt(list.get(i).substring(0, list.get(i).indexOf("."))));
                    if (Integer.parseInt(list.get(i).substring(0, list.get(i).indexOf("."))) > Integer.parseInt(list.get(i + 1).substring(0, list.get(i + 1).indexOf(".")))) {
                        tmp = list.get(i);
                        list.set(i, list.get(i + 1));
                        list.set(i + 1, tmp);
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public boolean hasNext() {
            if (idElement == list.size() - 1) return false;
            if (list.get(idElement + 1).substring(list.get(idElement + 1).indexOf(".") + 1, list.get(idElement + 1).indexOf(";")).length() == 0) {
                idElement++;
            }
            return ((list.size() != 0) && (idElement < list.size() - 1));
        }

        @Override
        public Object next() {
            if (hasNext()) {
                idElement++;
                return list.get(idElement).substring(list.get(idElement).indexOf(".") + 1, list.get(idElement).indexOf(";"));
            }
            throw new IllegalStateException("List has no more elements");
        }

        @Override
        public void remove() {
            myList.remove(list.get(idElement).substring(list.get(idElement).indexOf(".") + 1, list.get(idElement).indexOf(";")));
        }
    }
}
