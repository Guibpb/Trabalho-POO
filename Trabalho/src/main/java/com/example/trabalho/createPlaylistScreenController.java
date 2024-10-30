package com.example.trabalho;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class createPlaylistScreenController{
    private Stage stage;
    private Parent root;
    private Scene scene;

    @FXML //Função para voltar para a tela inicial
    public void goBack(ActionEvent e) throws IOException {
        this.root = (Parent) FXMLLoader.load(this.getClass().getResource("initialScreen.fxml"));
        this.stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        this.scene = new Scene(this.root);
        this.stage.setScene(this.scene);
        Platform.runLater(() -> {
            this.stage.setWidth(1200);
            this.stage.setHeight(800);
            this.stage.sizeToScene();
        });;
        this.stage.centerOnScreen();
        this.stage.show();
    }
}
