package com.example.telalogin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class telaLoginController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    public void switchToSceneLogin(ActionEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("telaLogin.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void switchToSceneRegister(ActionEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("telaRegistro.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}