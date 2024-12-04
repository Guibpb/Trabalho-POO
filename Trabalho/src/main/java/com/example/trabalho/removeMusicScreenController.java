package com.example.trabalho;

import com.example.trabalho.BackEnd.LogIn;
import com.example.trabalho.BackEnd.MusicDatabase;
import com.example.trabalho.BackEnd.PlaylistDatabase;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class removeMusicScreenController {
    private Stage stage;
    private Parent root;
    private Scene scene;

    private List<String[]> musics;
    private List<String> playlist;

    @FXML
    VBox vboxSongs;

    @FXML
    public void initialize() {
        musics = MusicDatabase.getMusicCSVFile();
        List<List<String>> playlists = PlaylistDatabase.getPlaylistCSVFile();
        for(List<String> findPlaylist : playlists) {
            if(findPlaylist.get(0).equals(App.playlistToEdit)) {
                playlist = findPlaylist;
                break;
            }
        }
        addSong();
    }

    @FXML
    public void goBack(ActionEvent e) throws IOException {
        this.root = (Parent) FXMLLoader.load(this.getClass().getResource("editPlaylistScreen.fxml"));
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

    private void goBackMouseEvent(javafx.scene.input.MouseEvent mouseEvent) throws IOException {
        this.root = (Parent) FXMLLoader.load(this.getClass().getResource("editPlaylistScreen.fxml"));
        this.stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
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

    public void addSong() {
        List<String[]> musicsToAdd = new ArrayList<>();
        for(String[] music : musics){
            boolean hasMusic = false;
            for(int j = 3; j < playlist.size(); j++){
                if(playlist.get(j).equals(music[0])){
                    hasMusic = true;
                    break;
                }
            }
            if(hasMusic){
                File defaultFile = new File("Musics/" + music[5]);
                if(defaultFile.exists()){
                    musicsToAdd.add(music);
                }
            }
        }

        for(int i = 0; i < musicsToAdd.size(); i++){
            final int index = i;
            String[] music = musicsToAdd.get(i);
            Pane pane = new Pane();
            Label songName = new Label(music[2]);
            Label genre = new Label(music[4]);
            Label artistName = new Label(music[1]);
            Label songDuration = new Label();
            File defaultFile = new File("Musics/" + music[5]);
            Media defaultMedia = new Media(defaultFile.toURI().toString());
            MediaPlayer defaultMediaPlayer = new MediaPlayer(defaultMedia);
            defaultMediaPlayer.setOnReady(() -> {
                Duration duration = defaultMediaPlayer.getMedia().getDuration();
                songDuration.setText(formatDuration(duration));
            });

            pane.setPrefHeight(84);
            pane.setPrefWidth(200);

            pane.setOnMouseEntered(mouseEvent -> {
                pane.setStyle("-fx-background-color: #616161");
            });

            pane.setOnMouseExited(mouseEvent -> {
                pane.setStyle("-fx-background-color: none");
            });

            pane.setOnMouseClicked(mouseEvent -> {
                LogIn.user.removeMusicFromPlaylist(PlaylistDatabase.getPlaylistCSVFile(), App.playlistToEdit, music[0]);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Excluir Música");
                alert.setHeaderText(null);
                alert.setContentText("Música excluida com sucesso");
                alert.showAndWait();
                try {
                    goBackMouseEvent(mouseEvent);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

            pane.setCursor(Cursor.HAND);

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
            genre.setAlignment(Pos.CENTER_LEFT);

            artistName.setPrefWidth(141);
            artistName.setPrefHeight(46);
            artistName.setLayoutX(469);
            artistName.setLayoutY(19);
            artistName.setTextFill(Color.WHITE);
            artistName.setFont(new Font("Arial", 14));
            artistName.setAlignment(Pos.CENTER_LEFT);

            songDuration.setPrefWidth(74);
            songDuration.setPrefHeight(46);
            songDuration.setLayoutX(610);
            songDuration.setLayoutY(19);
            songDuration.setTextFill(Color.WHITE);
            songDuration.setFont(new Font("System", 14));
            songDuration.setAlignment(Pos.CENTER_RIGHT);

            pane.getChildren().addAll(songName, genre, artistName, songDuration);
            vboxSongs.getChildren().add(pane);
        }
    }

    private String formatDuration(Duration duration) {
        int minutes = (int) duration.toMinutes();
        int seconds = (int) (duration.toSeconds() % 60); //Calculndo o resto para os segundos
        return String.format("%02d:%02d", minutes, seconds); //Ficar no formato 00:00
    }
}
