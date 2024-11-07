package com.example.trabalho;

import com.example.trabalho.BackEnd.LogIn;
import com.example.trabalho.BackEnd.MusicDatabase;
import com.example.trabalho.BackEnd.MusicOptions;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.w3c.dom.Text;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class uploadMusicScreenController {
    private Stage stage;
    private Parent root;
    private Scene scene;

    File musicFile;

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
    public void create(ActionEvent event) {
        int response;
        if(!genreMenu.getText().equals("Gênero")){
            response = MusicOptions.uploadMusic(MusicDatabase.getMusicCSVFile(), LogIn.user.getName(), musicName.getText(), genreMenu.getText(), musicFile);
            if(response == 1){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro");
                alert.setHeaderText(null);
                alert.setContentText("Nome de música inválido");
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText(null);
            alert.setContentText("Escolha um gênero");
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
        this.stage.show();
    }
}
