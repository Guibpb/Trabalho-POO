package com.example.trabalho;

import com.example.trabalho.BackEnd.FileInfo;
import com.example.trabalho.BackEnd.LogIn;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class admUserEditScreenController {
    private Stage stage;
    private Parent root;
    private Scene scene;
    private Image imageIcon;
    private ArrayList<String[]> users;
    private int userIndex;

    @FXML
    private TextField username;
    @FXML
    private TextField email;
    @FXML
    private PasswordField password;
    @FXML
    private ImageView userIcon;
    @FXML
    private PasswordField passwordConfirm;
    @FXML
    private Pane confirmPasswordPane;
    @FXML
    private Label usernameTitle;

    @FXML
    public void initialize() throws FileNotFoundException {
        users = FileInfo.getMatrixInfo("Database/Banco.csv");
        for(int i = 0; i < users.size(); i++){
            if(users.get(i)[1].equals(App.userToEdit)){
                userIndex = i;
            }
        }
        username.setText(users.get(userIndex)[1]);
        usernameTitle.setText(users.get(userIndex)[1]);
        email.setText(users.get(userIndex)[2]);
        password.setText(users.get(userIndex)[3]);
    }

    @FXML
    public void goBack(ActionEvent e) throws IOException {
        this.root = (Parent) FXMLLoader.load(this.getClass().getResource("admUsersScreen.fxml"));
        this.stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        this.scene = new Scene(this.root);
        this.stage.setScene(this.scene);
        this.stage.show();
    }

    @FXML
    public void editUsername(ActionEvent e) throws IOException {
        if(username.editableProperty().getValue()){
            username.setEditable(false);
            username.setDisable(true);
        }else{
            username.setEditable(true);
            username.setDisable(false);
        }
    }

    @FXML
    public void editEmail(ActionEvent e) throws IOException {
        if(email.editableProperty().getValue()) {
            email.setEditable(false);
            email.setDisable(true);
        }else{
            email.setEditable(true);
            email.setDisable(false);
        }
    }

    @FXML
    public void editPassword(ActionEvent e) throws IOException {
        if(password.editableProperty().getValue()) {
            password.setEditable(false);
            password.setDisable(true);
            passwordConfirm.setEditable(false);
            confirmPasswordPane.setVisible(false);
            password.setText(LogIn.user.getPassword());
        }else{
            password.setEditable(true);
            password.setDisable(false);
            password.setText("");
            passwordConfirm.setEditable(true);
            confirmPasswordPane.setVisible(true);
            confirmPasswordPane.setDisable(false);
        }
    }

    @FXML
    public void editIcon(javafx.scene.input.MouseEvent e) throws IOException {
        JFileChooser fc = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Imagens (JPG, PNG, GIF)", "jpg", "jpeg", "png", "gif"
        );
        fc.setFileFilter(filter);
        fc.showOpenDialog(null);
        File file = fc.getSelectedFile();
        if(file.exists()){
            imageIcon = new Image(file.toURI().toString());
            userIcon.setImage(imageIcon);
        }
    }

   /*@FXML
    public void confirmEdit(ActionEvent e){
        try {
            int response = LogIn.admUser.editA(username.getText(), email.getText(), password.getText(), passwordConfirm.getText(), username.isEditable(), email.isEditable(), password.isEditable());
            if(response == 0){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Editar");
                alert.setContentText("Usuário editado com sucesso!");
                alert.setHeaderText(null);
                alert.showAndWait();
                goBack(e);
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Editar");
                alert.setHeaderText(null);
                alert.setContentText("Erro");
                alert.showAndWait();
            }
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }*/
}
