package com.example.trabalho;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class loginScreenController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;

    @FXML
    public void switchToSceneRegister(ActionEvent e) throws IOException {
        this.root = (Parent)FXMLLoader.load(this.getClass().getResource("registerScreen.fxml"));
        this.stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        this.scene = new Scene(this.root);
        this.stage.setScene(this.scene);
        this.stage.show();
    }

    @FXML
    public void login(ActionEvent e) throws IOException {
        boolean successLogin = false;
        successLogin = LogIn.logIn(this.username.getText(), this.password.getText());
        if(successLogin){
            this.root = (Parent)FXMLLoader.load(this.getClass().getResource("initialScreen.fxml"));
            this.stage = (Stage)((Node)e.getSource()).getScene().getWindow();
            this.scene = new Scene(this.root);
            this.stage.setScene(this.scene);
            this.stage.setWidth(1200);
            this.stage.setHeight(800);
            this.stage.centerOnScreen();
            this.stage.show();
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login");
            alert.setContentText("Usuário ou senha incorretos");
            alert.setHeaderText(null);
            alert.showAndWait();
        }

    }
}