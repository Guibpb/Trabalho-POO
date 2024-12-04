package com.example.trabalho;

import com.example.trabalho.BackEnd.LogIn;
import com.example.trabalho.BackEnd.PlaylistDatabase;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class createPlaylistScreenController{
    private Stage stage;
    private Parent root;
    private Scene scene;

    @FXML
    private RadioButton publicRadioBtn;
    @FXML
    private RadioButton privateRadioBtn;
    @FXML
    private TextField labelPlaylistName;

    List<List<String>> playlists;

    @FXML
    public void initialize(){
        publicRadioBtn.setSelected(true);
        playlists = PlaylistDatabase.getPlaylistCSVFile();
        PlaylistDatabase.updatePlaylistCSVFile(playlists);
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


    public void createPlaylist(ActionEvent e) throws IOException {
        String playlistName = labelPlaylistName.getText();
        String artist = LogIn.user.getName();
        String visibility;
        if(privateRadioBtn.isSelected()) {
            visibility = "private";
        }else{
            visibility = "public";
        }
        LogIn.user.createPlaylist(playlists, playlistName, artist, visibility);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Playlist");
        alert.setHeaderText(null);
        alert.setContentText("Playlist criada com sucesso!");
        alert.showAndWait();
        goBack(e);
    }

}
