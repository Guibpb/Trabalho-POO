package com.example.trabalho;

import com.example.trabalho.BackEnd.LogIn;
import com.example.trabalho.BackEnd.MusicDatabase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class uploadMusicScreenController {
    private Stage stage;
    private Parent root;
    private Scene scene;

    File musicFile = null;

    @FXML
    private TextField musicName;
    @FXML
    private MenuButton genreMenu;

    @FXML
    public void selectMusicFile(ActionEvent event) {
        JFileChooser f = new JFileChooser();
        f.setFileSelectionMode(JFileChooser.FILES_ONLY);
        f.showOpenDialog(null);
        musicFile = f.getSelectedFile();
    }

    @FXML
    public void create(ActionEvent event) throws IOException {
        if(musicFile != null && musicFile.exists() && musicFile.toString().contains(".mp3")) {
            int response;
            if(!genreMenu.getText().equals("Gênero")){
                if(LogIn.user.getRole() == "Gerente"){
                    response = LogIn.admUser.uploadMusic(MusicDatabase.getMusicCSVFile(), LogIn.user.getName(), musicName.getText(), genreMenu.getText(), musicFile);
                }else{
                    response = LogIn.artistUser.uploadMusic(MusicDatabase.getMusicCSVFile(), LogIn.user.getName(), musicName.getText(), genreMenu.getText(), musicFile);
                }
                if(response == 1){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erro");
                    alert.setHeaderText(null);
                    alert.setContentText("Nome de música inválido");
                    alert.showAndWait();
                }else{
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Sucesso");
                    alert.setHeaderText(null);
                    alert.setContentText("Música criada com sucesso");
                    alert.showAndWait();
                    goBack(event);
                }
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro");
                alert.setHeaderText(null);
                alert.setContentText("Escolha um gênero");
                alert.showAndWait();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText(null);
            alert.setContentText("Escolha um arquivo válido");
            alert.showAndWait();
        }
    }


    @FXML
    public void changeMenuText(ActionEvent event) {
        MenuItem item = (MenuItem) event.getSource();
        genreMenu.setText(item.getText());
    }

    @FXML
    public void goBack(ActionEvent event) throws IOException {
        this.root = (Parent) FXMLLoader.load(this.getClass().getResource(App.lastScreenVisited));
        this.stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        this.scene = new Scene(this.root);
        this.stage.setScene(this.scene);
        this.stage.centerOnScreen();
        this.stage.show();
    }
}
