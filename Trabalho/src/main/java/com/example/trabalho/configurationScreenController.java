package com.example.trabalho;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class configurationScreenController {
    private Stage stage;
    private Parent root;
    private Scene scene;
    private Image imageIcon;

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
    public void switchToInitialScreen(ActionEvent e) throws IOException {
        this.root = (Parent) FXMLLoader.load(this.getClass().getResource("initialScreen.fxml"));
        this.stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        this.scene = new Scene(this.root);
        this.stage.setScene(this.scene);
        this.stage.show();
    }

    @FXML
    public void editUsername(ActionEvent e) throws IOException {
        username.setEditable(true);
        username.setDisable(false);
    }

    @FXML
    public void editEmail(ActionEvent e) throws IOException {
        email.setEditable(true);
        email.setDisable(false);
    }

    @FXML
    public void editPassword(ActionEvent e) throws IOException {
        confirmPasswordPane.setVisible(true);
        password.setText("");
        password.setEditable(true);
        passwordConfirm.setEditable(true);
        password.setDisable(false);
        passwordConfirm.setOpacity(1);
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
}
