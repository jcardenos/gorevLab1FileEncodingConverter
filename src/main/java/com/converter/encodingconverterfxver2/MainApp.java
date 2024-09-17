package com.converter.encodingconverterfxver2;

import com.converter.encodingconverterfxver2.controllers.FileConverterController;
import com.converter.encodingconverterfxver2.services.FileConverterService;
import com.converter.encodingconverterfxver2.services.IFileService;
import com.converter.encodingconverterfxver2.utils.UIManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        IFileService fileService = new FileConverterService();
        primaryStage.setTitle("File Converter");
        UIManager uiManager = new UIManager(new FileConverterController(fileService));
        Scene scene = uiManager.createMainScene(primaryStage);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}