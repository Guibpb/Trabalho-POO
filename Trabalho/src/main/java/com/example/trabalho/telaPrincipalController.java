package com.example.trabalho;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.event.MouseEvent;
import java.io.IOException;

public class telaPrincipalController {
    private Stage stage;
    private Parent root;
    private Scene scene;

    @FXML
    public void trocarParaPlayer(javafx.scene.input.MouseEvent e) throws IOException {
        this.root = (Parent) FXMLLoader.load(this.getClass().getResource("telaPlayer.fxml"));
        this.stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        this.scene = new Scene(this.root);
        this.stage.setScene(this.scene);
        this.stage.show();
    }
}
