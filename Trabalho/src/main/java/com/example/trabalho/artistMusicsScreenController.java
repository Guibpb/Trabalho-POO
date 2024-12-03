package com.example.trabalho;

import com.example.trabalho.BackEnd.LogIn;
import com.example.trabalho.BackEnd.MusicDatabase;
import com.example.trabalho.BackEnd.PublicUser;
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
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class artistMusicsScreenController {
    Parent root;
    Stage stage;
    Scene scene;

    private List<String[]> musics;

    @FXML
    VBox vboxSongs;

    @FXML
    public void initialize(){
        musics = MusicDatabase.getMusicCSVFile();
        addSong();
    }

    @FXML
    public void goBack(ActionEvent e) throws IOException {
        this.root = (Parent) FXMLLoader.load(this.getClass().getResource(App.lastScreenVisited));
        this.stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        this.scene = new Scene(this.root);
        this.stage.setScene(this.scene);
        this.stage.show();
    }

    public void addSong() {
        for(String[] music : musics) {
            boolean canAdd = false;

            if(LogIn.user.getRole().equals("Gerente") || music[1].equals(LogIn.user.getName())) { //colocar para aparecer todos se for adm
                canAdd = true;
            }

            if(canAdd) {
                Pane pane = new Pane();
                Label songName = new Label(music[2]);
                Label genre = new Label(music[4]);
                Label artistName = new Label(music[1]);
                Label songDuration = new Label("00:00");
                Label id = new Label(music[0]);
                Button editMusicButton = new Button("Editar");

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

                editMusicButton.setPrefWidth(Region.USE_COMPUTED_SIZE);
                editMusicButton.setPrefHeight(35);
                editMusicButton.setLayoutX(750);
                editMusicButton.setLayoutY(25);
                editMusicButton.setStyle("-fx-background-color:  #8A2BE2; -fx-text-fill: #1b1c1f;");
                editMusicButton.setCursor(Cursor.HAND);
                editMusicButton.setFont(Font.font("System", FontWeight.BOLD, 14));
                editMusicButton.setOnAction(event -> {
                    final String musicNameToPass = songName.getText();
                    final String idToPass = id.getText();
                    App.idMusicToAdd = idToPass;
                    App.musicToEdit = musicNameToPass;
                    try {
                        this.root = (Parent) FXMLLoader.load(this.getClass().getResource("musicConfigScreen.fxml"));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    this.stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                    this.scene = new Scene(this.root);
                    this.stage.setScene(this.scene);
                    this.stage.centerOnScreen();
                    this.stage.show();
                    App.lastScreenVisited = "allSongsScreen.fxml";
                });

                File defaultFile = new File("Musics/" + music[5]);
                if(defaultFile.exists()) {
                    Media defaultMedia = new Media(defaultFile.toURI().toString());
                    MediaPlayer defaultMediaPlayer = new MediaPlayer(defaultMedia);
                    defaultMediaPlayer.setOnReady(() -> {
                        Duration duration = defaultMediaPlayer.getMedia().getDuration();
                        songDuration.setText(formatDuration(duration));
                    });
                }

                pane.getChildren().addAll(songName, genre, artistName, songDuration, editMusicButton,id);
                vboxSongs.getChildren().add(pane);
            }
        }
    }

    private String formatDuration(Duration duration) {
        int minutes = (int) duration.toMinutes();
        int seconds = (int) (duration.toSeconds() % 60); //Calculndo o resto para os segundos
        return String.format("%02d:%02d", minutes, seconds); //Ficar no formato 00:00
    }
}
