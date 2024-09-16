package com.example.telalogin;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class telaLoginMain extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoaderLogin = new FXMLLoader(telaLoginMain.class.getResource("telaLogin.fxml"));
        Scene sceneLogin = new Scene(fxmlLoaderLogin.load());
        sceneLogin.getStylesheets().add(getClass().getResource("styleTelaLogin.css").toExternalForm());

        FXMLLoader fxmlLoaderRegistro = new FXMLLoader(telaLoginMain.class.getResource("telaRegistro.fxml"));
        Scene sceneRegistro = new Scene(fxmlLoaderRegistro.load());
        sceneRegistro.getStylesheets().add(getClass().getResource("styleTelaRegistro.css").toExternalForm());

        stage.setResizable(false);
        stage.setTitle("USPfy");
        stage.setScene(sceneLogin);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}