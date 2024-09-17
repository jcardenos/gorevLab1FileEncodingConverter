// UIManager.java
package com.converter.encodingconverterfxver2.utils;

import com.converter.encodingconverterfxver2.controllers.FileConverterController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;

public class UIManager {

    private final FileConverterController controller;

    // Создаём выпадающий список кодировок
    private final ObservableList<String> encodings = FXCollections.observableArrayList("cp866", "windows-1251", "MACCYRILLIC", "iso-8859-5");
    private final ComboBox<String> encodingComboBox = new ComboBox<>(encodings);

    public UIManager(FileConverterController controller) {
        this.controller = controller;
    }

    public Scene createMainScene(Stage primaryStage) {
        VBox vbox = new VBox();
        Label label = new Label("Выберите действие:");

        // Метка для выбора кодировки
        Label chooseEncodingLabel = new Label("Выберите кодировку для конвертации:");
        encodingComboBox.setValue("cp866"); // Значение по умолчанию

        Button selectFileButton = new Button("Выбрать файл и конвертировать в выбранную кодировку");
        selectFileButton.setPrefWidth(500);
        selectFileButton.setOnAction(e -> controller.handleSelectFile(primaryStage, encodingComboBox.getValue()));

        Button createFilesButton = new Button("Создать файлы в кодировках CP-866, CP-1251, x-MacCyrillic, ISO-8859-5");
        createFilesButton.setPrefWidth(500);
        createFilesButton.setOnAction(e -> controller.handleCreateFiles());

        Button exitButton = new Button("Выход");
        exitButton.setPrefWidth(500);
        exitButton.setOnAction(e -> primaryStage.close());
        exitButton.getStyleClass().add("exit");

        vbox.getChildren().addAll(label, chooseEncodingLabel, encodingComboBox, selectFileButton, createFilesButton, exitButton);

        Scene scene = new Scene(vbox, 550, 250);
        applyCss(scene);

        return scene;
    }

    private void applyCss(Scene scene) {
        String resourcePath = System.getProperty("user.dir") + File.separator + "style.css";
        File cssFile = new File(resourcePath);

        if (cssFile.exists()) {
            try {
                URL cssUrl = cssFile.toURI().toURL();
                scene.getStylesheets().add(cssUrl.toExternalForm());
            } catch (Exception e) {
                System.err.println("Ошибка при добавлении CSS файла: " + e.getMessage());
            }
        } else {
            System.err.println("CSS файл не найден.");
        }
    }
}
