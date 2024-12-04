package com.example.trabalho;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

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
        this.root = (Parent) FXMLLoader.load(this.getClass().getResource("admUsersScreen.fxml"));
        this.stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        this.scene = new Scene(this.root);
        this.stage.setScene(this.scene);
        this.stage.centerOnScreen();
        this.stage.show();
    }

    @FXML
    public void switchToScenePlaylists(ActionEvent e) throws IOException {
        App.lastScreenVisited = "admScreen.fxml";
        this.root = (Parent) FXMLLoader.load(this.getClass().getResource("allPlaylistsScreen.fxml"));
        this.stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        this.scene = new Scene(this.root);
        this.stage.setScene(this.scene);
        this.stage.centerOnScreen();
        this.stage.show();
    }

    @FXML
    public void switchToSceneMusicsData(ActionEvent e) throws IOException {
        App.lastScreenVisited = "admScreen.fxml";
        this.root = (Parent) FXMLLoader.load(this.getClass().getResource("musicsDataScreen.fxml"));
        this.stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        this.scene = new Scene(this.root);
        this.stage.setScene(this.scene);
        this.stage.centerOnScreen();
        this.stage.show();
    }

    @FXML
    public void switchToSceneUserData(ActionEvent e) throws IOException {
        App.lastScreenVisited = "admScreen.fxml";
        this.root = (Parent) FXMLLoader.load(this.getClass().getResource("userDataScreen.fxml"));
        this.stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        this.scene = new Scene(this.root);
        this.stage.setScene(this.scene);
        this.stage.centerOnScreen();
        this.stage.show();
    }

    @FXML
    public void switchToScenePlaylistData(ActionEvent e) throws IOException {
        App.lastScreenVisited = "admScreen.fxml";
        this.root = (Parent) FXMLLoader.load(this.getClass().getResource("playlistDataScreen.fxml"));
        this.stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        this.scene = new Scene(this.root);
        this.stage.setScene(this.scene);
        this.stage.centerOnScreen();
        this.stage.show();
    }
}
