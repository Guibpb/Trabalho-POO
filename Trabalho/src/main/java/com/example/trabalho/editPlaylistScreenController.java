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
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class editPlaylistScreenController {
    private Stage stage;
    private Parent root;
    private Scene scene;

    int index;
    String oldPlaylistName;
    String newVisibility;

    @FXML
    private RadioButton publicRadioBtn;
    @FXML
    private RadioButton privateRadioBtn;
    @FXML
    private TextField textFieldPlaylistName;

    List<List<String>> playlists;
    @FXML
    public void initialize(){
        oldPlaylistName = App.playlistToEdit;
        playlists = PlaylistDatabase.getPlaylistCSVFile();
        for(int i = 1; i < playlists.size(); i++){
            if(App.playlistToEdit.equals(playlists.get(i).get(0))){
                index = i;
            }
        }
        textFieldPlaylistName.setText(App.playlistToEdit);
        if(playlists.get(index).get(2).equals("public")){
            publicRadioBtn.setSelected(true);
            newVisibility = "public";
        }else{
            privateRadioBtn.setSelected(true);
            newVisibility = "private";
        }
    }

    @FXML //Função para voltar para a tela inicial
    public void goBack(ActionEvent e) throws IOException {
        this.root = (Parent) FXMLLoader.load(this.getClass().getResource("playlistScreen.fxml"));
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
    public void switchToSceneRemoveMusic(ActionEvent e) throws IOException {
        this.root = (Parent) FXMLLoader.load(this.getClass().getResource("removeMusicScreen.fxml"));
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
            newVisibility = "public";
        }
    }

    @FXML
    public void publicRadioBtnOnClick(ActionEvent e) {
        if(this.publicRadioBtn.isSelected()) {
            this.privateRadioBtn.setSelected(false);
        }else{
            this.privateRadioBtn.setSelected(true);
            newVisibility = "private";
        }
    }

    @FXML
    public void edit(ActionEvent e) throws IOException {
        LogIn.user.editPlaylist(PlaylistDatabase.getPlaylistCSVFile(), oldPlaylistName, textFieldPlaylistName.getText(), newVisibility);
        playlistScreenController.playlistName = textFieldPlaylistName.getText();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Editar Playlist");
        alert.setHeaderText(null);
        alert.setContentText("Playlist editado com sucesso!");
        alert.showAndWait();
        goBack(e);
    }

    @FXML
    public void deletePlaylist(ActionEvent e) throws IOException {
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("Deletar Playlist");
        confirmation.setHeaderText(null);
        confirmation.setContentText("Deseja continuar? A playlist será deletada para sempre");
        Optional<ButtonType> result = confirmation.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK){
            if(App.admEditing){
                App.lastScreenVisited = "admScreen.fxml";
            }else{
                App.lastScreenVisited = "initialScreen.fxml";
            }
            LogIn.user.deletePlaylist(PlaylistDatabase.getPlaylistCSVFile(), oldPlaylistName);
            this.root = (Parent) FXMLLoader.load(this.getClass().getResource(App.lastScreenVisited));
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

    }
}
