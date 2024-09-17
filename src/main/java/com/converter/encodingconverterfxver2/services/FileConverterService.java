package com.converter.encodingconverterfxver2.services;

import com.converter.encodingconverterfxver2.utils.AlertUtils;
import org.mozilla.universalchardet.UniversalDetector;

import java.awt.*;
import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class FileConverterService implements IFileService {

    @Override
    public String detectFileEncoding(File file) throws IOException {
        byte[] buf = new byte[4096];
        try (FileInputStream fis = new FileInputStream(file)) {
            UniversalDetector detector = new UniversalDetector(null);

            int nread;
            while ((nread = fis.read(buf)) > 0 && !detector.isDone()) {
                detector.handleData(buf, 0, nread);
            }

            detector.dataEnd();
            String encoding = detector.getDetectedCharset();
            detector.reset();

            return encoding != null ? encoding : "UTF-8";
        }
    }

    // Изменяем метод для работы с целевой кодировкой
    @Override
    public void convertFile(File sourceFile, File targetFile, String sourceEncoding, String targetEncoding) throws IOException {
        try (
                BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(sourceFile), Charset.forName(sourceEncoding)));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(targetFile), Charset.forName(targetEncoding)))
        ) {
            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line);
                writer.newLine();
            }
        }
    }

    @Override
    public List<String> createSampleFiles(String directory) {
        String text = "Привет, расскажи, как у тебя дела?\nУ меня всё хорошо!";
        List<String> fileNames = new ArrayList<>();

        File[] files = {
                new File(directory + File.separator + "file_cp866.txt"),
                new File(directory + File.separator + "file_cp1251.txt"),
                new File(directory + File.separator + "file_cp10007.txt"),
                new File(directory + File.separator + "file_iso88595.txt")
        };

        Charset[] encodings = {
                Charset.forName("CP866"),
                Charset.forName("windows-1251"),
                Charset.forName("x-MacCyrillic"),
                Charset.forName("ISO-8859-5")
        };

        try {
            for (int i = 0; i < files.length; i++) {
                createFileWithEncoding(files[i], text, encodings[i]);
                fileNames.add(files[i].getName());
            }
            openFileLocation(files[0]);
            AlertUtils.showInfoAlert("Файлы созданы", "Файлы успешно созданы.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileNames;
    }

    @Override
    public void openFileLocation(File file) {
        try {
            File parentDirectory = file.getParentFile();
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(parentDirectory);
            } else {
                AlertUtils.showErrorAlert("Ошибка", "Открытие проводника не поддерживается.");
            }
        } catch (IOException e) {
            AlertUtils.showErrorAlert("Ошибка", "Не удалось открыть проводник.");
        }
    }

    private static void createFileWithEncoding(File file, String text, Charset encoding) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), encoding))) {
            writer.write(text);
        }
    }
}
