package com.converter.encodingconverterfxver2.utils;

import java.io.File;

public class FileInitializer {
    static String sourceFilesDir = System.getProperty("user.dir") + File.separator + "sourceFiles";

    public static void initSourceFilesDir() {
        File dir = new File(sourceFilesDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    public static String getSourceFilesDir() {
        return sourceFilesDir;
    }
}
