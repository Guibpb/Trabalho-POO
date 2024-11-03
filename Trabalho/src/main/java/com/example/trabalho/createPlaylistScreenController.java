package com.example.trabalho;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;

import java.io.IOException;

public class createPlaylistScreenController{
    private Stage stage;
    private Parent root;
    private Scene scene;

    @FXML
    private RadioButton publicRadioBtn;
    @FXML
    private RadioButton privateRadioBtn;

    @FXML
    public void initialize(){
        publicRadioBtn.setSelected(true);
    }

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

    @FXML
    public void privateRadioBtnOnClick(ActionEvent e) {
        if (this.privateRadioBtn.isSelected()) {
            this.publicRadioBtn.setSelected(false);
        }else{
            this.publicRadioBtn.setSelected(true);
        }
    }

    @FXML
    public void publicRadioBtnOnClick(ActionEvent e) {
        if(this.publicRadioBtn.isSelected()) {
            this.privateRadioBtn.setSelected(false);
        }else{
            this.privateRadioBtn.setSelected(true);
        }
    }
}
