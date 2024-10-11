package com.example.trabalho;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    public static File arquivo = new File("Banco.csv");
    public static Scanner scan;

    public App() {
    }

    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoaderLogin = new FXMLLoader(App.class.getResource("telaLogin.fxml"));
        Scene sceneLogin = new Scene((Parent)fxmlLoaderLogin.load());
        sceneLogin.getStylesheets().add(this.getClass().getResource("styleTelaLogin.css").toExternalForm());
        FXMLLoader fxmlLoaderRegistro = new FXMLLoader(App.class.getResource("telaRegistro.fxml"));
        Scene sceneRegistro = new Scene((Parent)fxmlLoaderRegistro.load());
        sceneRegistro.getStylesheets().add(this.getClass().getResource("styleTelaRegistro.css").toExternalForm());
        stage.setResizable(false);
        stage.setTitle("USPfy");
        stage.setScene(sceneLogin);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    static {
        scan = new Scanner(System.in);
    }
}