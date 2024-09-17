package com.converter.encodingconverterfxver2.services;

import java.io.File;
import java.util.List;

public interface IFileService {
    String detectFileEncoding(File file) throws Exception;
    void convertFile(File sourceFile, File targetFile, String sourceEncoding, String targetEncoding) throws Exception;
    List<String> createSampleFiles(String directory);
    void openFileLocation(File file);
}