package com.example.trabalho;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class telaRegistroController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private TextField nomeRegistro;
    @FXML
    private TextField emailRegistro;
    @FXML
    private TextField senhaRegistro;
    @FXML
    private CheckBox checkBoxUsuario;
    @FXML
    private CheckBox checkBoxArtista;

    public telaRegistroController() {
    }

    @FXML
    public void switchToSceneLogin(ActionEvent e) throws IOException {
        this.root = (Parent)FXMLLoader.load(this.getClass().getResource("telaLogin.fxml"));
        this.stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        this.scene = new Scene(this.root);
        this.stage.setScene(this.scene);
        this.stage.show();
    }

    @FXML
    public void checkBoxUsuarioClicked(ActionEvent e) {
        if (this.checkBoxUsuario.isSelected()) {
            this.checkBoxArtista.setSelected(false);
        }

    }

    @FXML
    public void checkBoxArtistaClicked(ActionEvent e) {
        if (this.checkBoxArtista.isSelected()) {
            this.checkBoxUsuario.setSelected(false);
        }

    }

    @FXML
    public void registrar(ActionEvent e) throws IOException {
        String tipoUsuario = "";
        if (this.checkBoxArtista.isSelected()) {
            tipoUsuario = "artista";
        } else {
            tipoUsuario = "comum";
        }

        int confirmacao = SignUp.signUp(nomeRegistro.getText(), emailRegistro.getText(), senhaRegistro.getText(), tipoUsuario);
        if (confirmacao == 0) {
            this.nomeRegistro.clear();
            this.senhaRegistro.clear();
            this.emailRegistro.clear();
            this.checkBoxArtista.setSelected(false);
            this.checkBoxUsuario.setSelected(false);
        } else {
            System.out.println("Error");
        }

    }
}