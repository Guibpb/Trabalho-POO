package com.example.trabalho;

import com.example.trabalho.BackEnd.LogIn;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.nio.file.Path;

public class admScreenController {

    private Parent root;
    private Stage stage;
    private Scene scene;

    @FXML
    public void initialize() {
        App.admEditing = true;
    }

    @FXML
    public void goBack(ActionEvent e) throws IOException {
        App.admEditing = false;
        this.root = (Parent) FXMLLoader.load(this.getClass().getResource("configurationScreen.fxml"));
        this.stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        this.scene = new Scene(this.root);
        this.stage.setScene(this.scene);
        this.stage.centerOnScreen();
        this.stage.show();
    }

    @FXML
    public void switchToSceneMusicsConfig(ActionEvent e) throws IOException {
        App.admEditing = true;
        App.lastScreenVisited = "admScreen.fxml";
        this.root = (Parent) FXMLLoader.load(this.getClass().getResource("artistMusicsScreen.fxml"));
        this.stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        this.scene = new Scene(this.root);
        this.stage.setScene(this.scene);
        this.stage.centerOnScreen();
        this.stage.show();
    }

    @FXML
    public void switchToSceneUsers(ActionEvent e) throws IOException {
        App.admEditing = true;
        this.root = (Parent) FXMLLoader.load(this.getClass().getResource("admUsersScreen.fxml"));
        this.stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        this.scene = new Scene(this.root);
        this.stage.setScene(this.scene);
        this.stage.centerOnScreen();
        this.stage.show();
    }

    @FXML
    public void switchToScenePlaylists(ActionEvent e) throws IOException {
        App.admEditing = true;
        App.lastScreenVisited = "admScreen.fxml";
        this.root = (Parent) FXMLLoader.load(this.getClass().getResource("allPlaylistsScreen.fxml"));
        this.stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        this.scene = new Scene(this.root);
        this.stage.setScene(this.scene);
        this.stage.centerOnScreen();
        this.stage.show();
    }

    @FXML
    public void musicData(ActionEvent e) throws IOException {
        JFileChooser jf = new JFileChooser();
        jf.setDialogTitle("Selecione uma pasta");
        jf.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        jf.setAcceptAllFileFilterUsed(false);
        int result = jf.showOpenDialog(null);
        if(result == JFileChooser.APPROVE_OPTION){
            String path = jf.getSelectedFile().toString() + "/";
            LogIn.admUser.seeAllMusics(path);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Relatório criado com sucesso");
            alert.setTitle("Relatório");
            alert.setHeaderText(null);
            alert.showAndWait();
        }
    }

    @FXML
    public void userData(ActionEvent e) throws IOException {
        JFileChooser jf = new JFileChooser();
        jf.setDialogTitle("Selecione uma pasta");
        jf.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        jf.setAcceptAllFileFilterUsed(false);
        int result = jf.showOpenDialog(null);
        if(result == JFileChooser.APPROVE_OPTION){
            String path = jf.getSelectedFile().toString() + "/";
            LogIn.admUser.seeAllUsers(path);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Relatório criado com sucesso");
            alert.setTitle("Relatório");
            alert.setHeaderText(null);
            alert.showAndWait();
        }
    }

    @FXML
    public void playlistData(ActionEvent e) throws IOException {
        JFileChooser jf = new JFileChooser();
        jf.setDialogTitle("Selecione uma pasta");
        jf.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        jf.setAcceptAllFileFilterUsed(false);
        int result = jf.showOpenDialog(null);
        if(result == JFileChooser.APPROVE_OPTION){
            String path = jf.getSelectedFile().toString() + "/";
            LogIn.admUser.seeAllPlaylists(path);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Relatório criado com sucesso");
            alert.setTitle("Relatório");
            alert.setHeaderText(null);
            alert.showAndWait();
        }
    }
}
