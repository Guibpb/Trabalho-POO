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
        FXMLLoader fxmlLoaderLogin = new FXMLLoader(App.class.getResource("loginScreen.fxml"));
        Scene sceneLogin = new Scene((Parent)fxmlLoaderLogin.load());
        sceneLogin.getStylesheets().add(this.getClass().getResource("styleLoginScreen.css").toExternalForm());

        FXMLLoader fxmlLoaderRegister = new FXMLLoader(App.class.getResource("registerScreen.fxml"));
        Scene sceneRegister = new Scene((Parent) fxmlLoaderRegister.load());
        sceneRegister.getStylesheets().add(this.getClass().getResource("styleRegisterScreen.css").toExternalForm());

        FXMLLoader fxmlLoaderPlaylist = new FXMLLoader(App.class.getResource("playlistScreen.fxml"));
        Scene scenePlaylist = new Scene((Parent)fxmlLoaderPlaylist.load());
        scenePlaylist.getStylesheets().add(this.getClass().getResource("stylePlaylistScreen.css").toExternalForm());

        FXMLLoader fxmlLoaderConfiguration = new FXMLLoader(App.class.getResource("configurationScreen.fxml"));
        Scene sceneConfiguration= new Scene((Parent)fxmlLoaderConfiguration.load());
        sceneConfiguration.getStylesheets().add(this.getClass().getResource("styleConfigurationScreen.css").toExternalForm());

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