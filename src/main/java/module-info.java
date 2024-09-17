module com.converter.encodingconverterfxver2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.github.albfernandez.juniversalchardet;
    requires java.desktop;


    opens com.converter.encodingconverterfxver2 to javafx.fxml;
    exports com.converter.encodingconverterfxver2;
    exports com.converter.encodingconverterfxver2.services;
    opens com.converter.encodingconverterfxver2.services to javafx.fxml;
    exports com.converter.encodingconverterfxver2.controllers;
    opens com.converter.encodingconverterfxver2.controllers to javafx.fxml;
    exports com.converter.encodingconverterfxver2.utils;
    opens com.converter.encodingconverterfxver2.utils to javafx.fxml;
}