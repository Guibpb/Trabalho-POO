package com.example.trabalho;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class userDataScreenController {
    private Parent root;
    private Stage stage;
    private Scene scene;

    @FXML
    public void goBack(ActionEvent e) throws IOException {
        App.admEditing = false;
        this.root = (Parent) FXMLLoader.load(this.getClass().getResource("admScreen.fxml"));
        this.stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        this.scene = new Scene(this.root);
        this.stage.setScene(this.scene);
        this.stage.centerOnScreen();
        this.stage.show();
    }
}
