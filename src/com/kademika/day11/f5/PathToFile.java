package com.kademika.day11.f5;


import java.io.File;

public class PathToFile {
    public static void main(String[] args) {
        File file = new File("Test.txt");
        System.out.println(System.getProperty("user.dir"));
        System.out.println(file.separator);
        System.out.println(file.pathSeparator);
        for (File f : File.listRoots()) {
            System.out.println(f.getAbsolutePath());
        }
        System.out.println(getRelativeFileDir());
        System.out.println(getFilePath());
    }

    public static String getFilePath() {
        String path = "src/com/kademika/day11/f5";
        path = path.replace("/", File.separator);

        File thisFile = new File(path, PathToFile.class.getSimpleName() + ".java");

        return thisFile.getAbsolutePath();
    }

    public static String getRelativeFileDir() {
        String path = "src/com/kademika/day11/f5";
        return path.replace("/", File.separator);
    }

}
