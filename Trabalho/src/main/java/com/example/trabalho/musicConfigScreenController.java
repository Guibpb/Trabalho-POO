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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class musicConfigScreenController {

    private Parent root;
    private Stage stage;
    private Scene scene;
    private String stringMusicName;
    private String musicId;
    private String musicGenre;
    private List<String[]> musics;

    @FXML
    TextField musicName;
    @FXML
    MenuButton genreMenu;

    @FXML
    public void initialize(){
        stringMusicName = App.musicToEdit;
        musicName.setText(stringMusicName);
        musics = MusicDatabase.getMusicCSVFile();
        int index = 0;
        for(int i = 0; i < musics.size(); i++){
            if(musics.get(i)[1].equals(stringMusicName)){
                index = i;
            }
        }
        musicGenre = musics.get(index)[4];
        musicId = musics.get(index)[0];
        genreMenu.setText(musicGenre);
    }

    @FXML
    public void goBack(ActionEvent e) throws IOException {
        this.root = (Parent) FXMLLoader.load(this.getClass().getResource("artistMusicsScreen.fxml"));
        this.stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        this.scene = new Scene(this.root);
        this.stage.setScene(this.scene);
        this.stage.centerOnScreen();
        this.stage.show();
    }

    @FXML
    public void deleteMusic(ActionEvent e) throws IOException {
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("Deletar Música");
        confirmation.setHeaderText("Deseja continuar?");
        confirmation.setContentText("A música será permanentemente apagada");
        Optional<ButtonType> result = confirmation.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK){
            //código para excluir musica
        }
    }

    @FXML
    public void editMusicName(ActionEvent e) throws IOException {
        if(musicName.isEditable()){
            musicName.setDisable(true);
            musicName.setEditable(false);
        }else{
            musicName.setDisable(false);
            musicName.setEditable(true);
        }
    }

    @FXML
    public void editMusicGenre(ActionEvent e) throws IOException {
        if(genreMenu.isDisable()){
            genreMenu.setDisable(false);
        }else{
            genreMenu.setDisable(true);
        }
    }

    @FXML
    public void changeGenreText(ActionEvent e) throws IOException {
        
    }

}
