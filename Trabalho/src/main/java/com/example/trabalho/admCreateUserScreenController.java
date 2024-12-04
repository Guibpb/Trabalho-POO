package com.example.trabalho;

import com.example.trabalho.BackEnd.LogIn;
import com.example.trabalho.BackEnd.SignUp;
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

import java.io.IOException;

public class admCreateUserScreenController {
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
    @FXML
    private CheckBox admCheckbox;

    @FXML
    public void initialize() {
        userCheckbox.setSelected(true);

        //Limita o número de caracteres do username para 15
        registerName.textProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue.length() > 15){
                registerName.setText(oldValue);
            }
        });
    }

    @FXML
    public void goBack(ActionEvent e) throws IOException {
        this.root = (Parent) FXMLLoader.load(this.getClass().getResource("admUsersScreen.fxml"));
        this.stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        this.scene = new Scene(this.root);
        this.stage.setScene(this.scene);
        this.stage.centerOnScreen();
        this.stage.show();
    }

    @FXML
    public void userCheckboxOnClick(ActionEvent e) {
        admCheckbox.setSelected(false);
        if (this.userCheckbox.isSelected()) {
            this.artistCheckbox.setSelected(false);
        }else{
            this.artistCheckbox.setSelected(true);
        }
    }

    @FXML
    public void artistCheckboxOnClick(ActionEvent e) {
        admCheckbox.setSelected(false);
        if (this.artistCheckbox.isSelected()) {
            this.userCheckbox.setSelected(false);
        }else{
            this.userCheckbox.setSelected(true);
        }
    }

    @FXML
    public void admCheckboxOnClick(ActionEvent e) {
        if(admCheckbox.isSelected()){
            this.userCheckbox.setSelected(false);
            this.artistCheckbox.setSelected(false);
        }else{
            this.userCheckbox.setSelected(true);
        }
    }

    @FXML
    public void create(ActionEvent e) throws IOException {

        //checkbox para definir o tipo de usuário (obrigatoriamente um estará escolhido para evitar erros)
        String tipoUsuario = "";
        if (this.artistCheckbox.isSelected()) {
            tipoUsuario = "Artista";
        } else if(this.userCheckbox.isSelected()) {
            tipoUsuario = "Comum";
        }else{
            tipoUsuario = "Gerente";
        }

        //funcao chamada para efetuar o registro ou retornar possíveis erros
        int confirmacao = LogIn.admUser.createAny(registerName.getText(), registerEmail.getText(), registerPassword.getText(), registerPasswordConfirm.getText(), tipoUsuario);
        if (confirmacao == 0) {
            this.registerName.clear();
            this.registerPassword.clear();
            this.registerPasswordConfirm.clear();
            this.registerEmail.clear();
            this.artistCheckbox.setSelected(false);
            this.userCheckbox.setSelected(false);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Usuário");
            alert.setContentText("Usuário criado com sucesso!");
            alert.setHeaderText(null);
            alert.showAndWait();
            goBack(e);
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
