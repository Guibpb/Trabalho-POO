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

public class telaRegistroController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private TextField nomeRegistro;
    @FXML
    private TextField emailRegistro;
    @FXML
    private PasswordField senhaRegistro;
    @FXML
    private PasswordField senhaConfirmacao;
    @FXML
    private CheckBox checkBoxUsuario;
    @FXML
    private CheckBox checkBoxArtista;


    //funcao para inicializar uma checkbox como true, para impedir erros
    @FXML
    public void initialize() {
        checkBoxUsuario.setSelected(true);
    }

    //funcao para mudar para a tela de login
    @FXML
    public void switchToSceneLogin(ActionEvent e) throws IOException {
        this.root = (Parent)FXMLLoader.load(this.getClass().getResource("telaLogin.fxml"));
        this.stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        this.scene = new Scene(this.root);
        this.stage.setScene(this.scene);
        this.stage.show();
    }

    //funcao para impedir que o usuario selecione duas checkbox simultaneamente ou nenhuma
    @FXML
    public void checkBoxUsuarioClicked(ActionEvent e) {
        if (this.checkBoxUsuario.isSelected()) {
            this.checkBoxArtista.setSelected(false);
        }else{
            this.checkBoxArtista.setSelected(true);
        }

    }

    //funcao para impedir que o usuario selecione duas checkbox simultaneamente ou nenhuma
    @FXML
    public void checkBoxArtistaClicked(ActionEvent e) {
        if (this.checkBoxArtista.isSelected()) {
            this.checkBoxUsuario.setSelected(false);
        }else{
            this.checkBoxUsuario.setSelected(true);
        }

    }

    @FXML
    public void registrar(ActionEvent e) throws IOException {

        //checkbox para definir o tipo de usuário (obrigatoriamente um estará escolhido para evitar erros)
        String tipoUsuario = "";
        if (this.checkBoxArtista.isSelected()) {
            tipoUsuario = "artista";
        } else {
            tipoUsuario = "comum";
        }

        //funcao chamada para efetuar o registro ou retornar possíveis erros
        int confirmacao = SignUp.signUp(nomeRegistro.getText(), emailRegistro.getText(), senhaRegistro.getText(), tipoUsuario);
        if (confirmacao == 0) {
            this.nomeRegistro.clear();
            this.senhaRegistro.clear();
            this.senhaConfirmacao.clear();
            this.emailRegistro.clear();
            this.checkBoxArtista.setSelected(false);
            this.checkBoxUsuario.setSelected(false);
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