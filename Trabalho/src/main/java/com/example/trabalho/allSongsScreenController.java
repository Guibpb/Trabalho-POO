package com.example.trabalho;

import com.example.trabalho.BackEnd.MusicDatabase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class allSongsScreenController {
    Parent root;
    Stage stage;
    Scene scene;

    private List<String[]> musics;

    @FXML
    VBox vboxSongs;
    @FXML
    TextField searchTextField;

    @FXML
    public void initialize(){
        musics = MusicDatabase.getMusicCSVFile();
        addSong("");
        searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            addSong(newValue);
        });
    }

    @FXML
    public void goBack(ActionEvent e) throws IOException {
        this.root = (Parent) FXMLLoader.load(this.getClass().getResource("initialScreen.fxml"));
        this.stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        this.scene = new Scene(this.root);
        this.stage.setScene(this.scene);
        this.stage.show();
    }

    public void addSong(String musicName) {
        vboxSongs.getChildren().clear();
        for(String[] music : musics) {
            boolean canAdd = false;

            if(music[2].toLowerCase().contains(musicName.toLowerCase()) || musicName.equals("")) {
                canAdd = true;
            }

            if(canAdd) {
                Pane pane = new Pane();
                Label songName = new Label(music[2]);
                Label genre = new Label(music[4]);
                Label artistName = new Label(music[1]);
                Label songDuration = new Label("00:00");
                Label id = new Label(music[0]);
                Button addPlaylistBtn = new Button("Adicionar");

                pane.setPrefHeight(84);

                songName.setPrefWidth(258);
                songName.setPrefHeight(46);
                songName.setLayoutX(14);
                songName.setLayoutY(19);
                songName.setTextFill(Color.WHITE);
                songName.setFont(new Font("Arial", 14));

                genre.setPrefWidth(141);
                genre.setPrefHeight(46);
                genre.setLayoutX(283);
                genre.setLayoutY(19);
                genre.setTextFill(Color.WHITE);
                genre.setFont(new Font("Arial", 14));
                genre.setAlignment(Pos.CENTER);

                artistName.setPrefWidth(141);
                artistName.setPrefHeight(46);
                artistName.setLayoutX(469);
                artistName.setLayoutY(19);
                artistName.setTextFill(Color.WHITE);
                artistName.setFont(new Font("Arial", 14));
                artistName.setAlignment(Pos.CENTER);

                songDuration.setPrefWidth(74);
                songDuration.setPrefHeight(46);
                songDuration.setLayoutX(610);
                songDuration.setLayoutY(19);
                songDuration.setTextFill(Color.WHITE);
                songDuration.setFont(new Font("System", 14));
                songDuration.setAlignment(Pos.CENTER);

                id.setVisible(false);

                addPlaylistBtn.setPrefWidth(Region.USE_COMPUTED_SIZE);
                addPlaylistBtn.setPrefHeight(35);
                addPlaylistBtn.setLayoutX(750);
                addPlaylistBtn.setLayoutY(25);
                addPlaylistBtn.setStyle("-fx-background-color:  #8A2BE2; -fx-text-fill: #1b1c1f;");
                addPlaylistBtn.setCursor(Cursor.HAND);
                addPlaylistBtn.setFont(Font.font("System", FontWeight.BOLD, 14));
                addPlaylistBtn.setOnAction(event -> {
                    final String idToPass = id.getText();
                    App.idMusicToAdd = idToPass;
                    /*
                    Pane defaultPane = (Pane)event.getSource();
                    List<Node> defaultPaneNodes = defaultPane.getChildren();
                    Label defaultId = (Label) defaultPaneNodes.get(5);
                    App.idMusicToAdd = defaultId.getText();
                     */
                    try {
                        this.root = (Parent) FXMLLoader.load(this.getClass().getResource("addMusicPlaylistScreen.fxml"));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    this.stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                    this.scene = new Scene(this.root);
                    this.stage.setScene(this.scene);
                    this.stage.show();
                    App.lastScreenVisited = "allSongsScreen.fxml";
                });

                pane.getChildren().addAll(songName, genre, artistName, songDuration, addPlaylistBtn,id);
                vboxSongs.getChildren().add(pane);
            }
        }
    }
}
