package com.converter.encodingconverterfxver2.controllers;

import com.converter.encodingconverterfxver2.utils.AlertUtils;
import com.converter.encodingconverterfxver2.services.IFileService;
import com.converter.encodingconverterfxver2.utils.FileInitializer;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class FileConverterController {

    private final IFileService fileService;

    public FileConverterController(IFileService fileService) {
        this.fileService = fileService;
    }

    // Добавляем параметр для кодировки
    public void handleSelectFile(Stage stage, String targetEncoding) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
        File selectedFile = fileChooser.showOpenDialog(stage);

        if (selectedFile != null) {
            processFileConversion(selectedFile, targetEncoding);
        }
    }

    private void processFileConversion(File selectedFile, String targetEncoding) {
        try {
            String detectedEncoding = fileService.detectFileEncoding(selectedFile);
            System.out.println(detectedEncoding);
            File outputFile = new File(FileInitializer.getSourceFilesDir() + File.separator + "convertedTo_" + targetEncoding + "_" + selectedFile.getName());
            // Передаем целевую кодировку
            fileService.convertFile(selectedFile, outputFile, detectedEncoding, targetEncoding);
            AlertUtils.showInfoAlert("Конвертация успешна", "Файл " + selectedFile.getName() + " успешно преобразован в " + targetEncoding + ".");
            fileService.openFileLocation(outputFile);
        } catch (Exception e) {
            AlertUtils.showErrorAlert("Ошибка конвертации", "Не удалось конвертировать файл.");
            e.printStackTrace();
        }
    }

    public void handleCreateFiles() {
        FileInitializer.initSourceFilesDir();
        fileService.createSampleFiles(FileInitializer.getSourceFilesDir());
    }
}
