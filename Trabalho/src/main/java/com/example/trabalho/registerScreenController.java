package com.example.trabalho;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class registerScreenController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private TextField registerName;
    @FXML
    private TextField registerEmail;
    @FXML
    private PasswordField registerPassword;
    @FXML
    private PasswordField registerPasswordConfirm;
    @FXML
    private CheckBox userCheckbox;
    @FXML
    private CheckBox artistCheckbox;


    //funcao para inicializar uma checkbox como true, para impedir erros
    @FXML
    public void initialize() {
        userCheckbox.setSelected(true);
    }

    //funcao para mudar para a tela de login
    @FXML
    public void switchToSceneLogin(ActionEvent e) throws IOException {
        this.root = (Parent)FXMLLoader.load(this.getClass().getResource("loginScreen.fxml"));
        this.stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        this.scene = new Scene(this.root);
        this.stage.setScene(this.scene);
        this.stage.show();
    }

    //funcao para impedir que o usuario selecione duas checkbox simultaneamente ou nenhuma
    @FXML
    public void userCheckboxOnClick(ActionEvent e) {
        if (this.userCheckbox.isSelected()) {
            this.artistCheckbox.setSelected(false);
        }else{
            this.artistCheckbox.setSelected(true);
        }

    }

    //funcao para impedir que o usuario selecione duas checkbox simultaneamente ou nenhuma
    @FXML
    public void artistCheckboxOnClick(ActionEvent e) {
        if (this.artistCheckbox.isSelected()) {
            this.userCheckbox.setSelected(false);
        }else{
            this.userCheckbox.setSelected(true);
        }

    }

    @FXML
    public void register(ActionEvent e) throws IOException {

        //checkbox para definir o tipo de usuário (obrigatoriamente um estará escolhido para evitar erros)
        String tipoUsuario = "";
        if (this.artistCheckbox.isSelected()) {
            tipoUsuario = "artista";
        } else {
            tipoUsuario = "comum";
        }

        //funcao chamada para efetuar o registro ou retornar possíveis erros
        int confirmacao = SignUp.signUp(registerName.getText(), registerEmail.getText(), registerPassword.getText(), tipoUsuario);
        if (confirmacao == 0) {
            this.registerName.clear();
            this.registerPassword.clear();
            this.registerPasswordConfirm.clear();
            this.registerEmail.clear();
            this.artistCheckbox.setSelected(false);
            this.userCheckbox.setSelected(false);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Registro");
            alert.setContentText("Seu registro foi efetuado com sucesso!");
            alert.setHeaderText(null);
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText(null);

            //pop-ups de erros que podem ocorrer no registro
            switch (confirmacao){
                case 1:
                    alert.setContentText("Esse nome de usuário já existe");
                    alert.showAndWait();
                    break;
                case 2:
                    alert.setContentText("Esse e-mail já está cadastrado");
                    alert.showAndWait();
                    break;
                case 3:
                    alert.setContentText("Formato de usuário inválido");
                    alert.showAndWait();
                    break;
                case 4:
                    alert.setContentText("Formato de e-mail inválido");
                    alert.showAndWait();
                    break;
                case 5:
                    alert.setContentText("Formato de senha inválido");
                    alert.showAndWait();
                case 6:
                    alert.setContentText("Senhas incompatíveis");
                    alert.showAndWait();
            }
        }
    }
}