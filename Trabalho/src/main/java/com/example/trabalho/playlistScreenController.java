package com.example.trabalho;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.print.attribute.standard.MediaPrintableArea;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class playlistScreenController {
    private Stage stage;
    private Parent root;
    private Scene scene;

    private Image btnPauseImage = new Image(getClass().getResourceAsStream("/com/example/trabalho/imagens/btnPause.png"));
    private Image btnPlayImage = new Image(getClass().getResourceAsStream("/com/example/trabalho/imagens/btnPlay.png"));

    boolean runSong = false;

    double totalSeconds;
    double progressRate;

    Timer timer;

    File musicFile;

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private ImageView imgButton;
    @FXML
    private Slider durationSlider;
    @FXML
    private ProgressBar durationProgressBar;
    @FXML
    private Slider volumeSlider;
    @FXML
    private ProgressBar volumeProgressBar;
    @FXML
    private MediaPlayer mediaPlayer;
    @FXML
    private Media media;
    @FXML
    VBox vboxSongs;

    @FXML
    public void initialize() throws IOException {
        imgButton.setImage(btnPlayImage);
        Scene scene = anchorPane.getScene();
        if(scene != null) {
            scene.getStylesheets().add(getClass().getResource("stylePlaylistScreen").toExternalForm());
        }

    }

    @FXML
    public void switchToInitialScreen(ActionEvent e) throws IOException {
        this.root = (Parent) FXMLLoader.load(this.getClass().getResource("initialScreen.fxml"));
        this.stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        this.scene = new Scene(this.root);
        this.stage.setScene(this.scene);
        this.stage.show();
    }

    @FXML
    public void durationSliderClcik(MouseEvent e) {
        double progress = durationSlider.getValue();
        durationProgressBar.setProgress(progress/100);
        changeSongProgress(progress);
    }

    @FXML
    public void volumeSliderClcik(MouseEvent e) {
        double volume = volumeSlider.getValue();
        volumeProgressBar.setProgress(volume/100);
        changeSongVolume(volume);
    }

    @FXML
    public void btnPlayerOnClick(ActionEvent e) throws IOException{
        musicClick(null);
    }

    @FXML
    public void musicClick(MouseEvent e) throws IOException{
        if(musicFile != null){
            if(imgButton.getImage().equals(btnPlayImage)) {
                imgButton.setImage(btnPauseImage);
                mediaPlayer.play();
            }else{
                imgButton.setImage(btnPlayImage);
                mediaPlayer.pause();
            }

            if(!runSong){
                runSong = true;
                timer = new Timer();
                timer.scheduleAtFixedRate(new TimerTask() {
                    public void run() {
                        if(durationProgressBar.getProgress()< 1 && durationSlider.getValue() < 100){
                            durationProgressBar.setProgress((durationSlider.getValue())/100 + progressRate);
                            durationSlider.setValue(durationSlider.getValue() + progressRate*100);
                        }
                    }
                }, 0, 100);
            }else{
                timer.cancel();
                runSong = false;
            }
        }
    }

    @FXML
    public void chooseSong(ActionEvent e) throws IOException {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.showOpenDialog(null);
        musicFile = fileChooser.getSelectedFile();
        if(musicFile != null){
            media = new Media(musicFile.toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setOnReady(new Runnable() {
                public void run() {
                    mediaPlayer.setVolume(volumeSlider.getValue()/100);
                    durationSlider.setValue(0);
                    durationProgressBar.setProgress(0);
                    totalSeconds = mediaPlayer.getTotalDuration().toSeconds();
                    progressRate = (1/totalSeconds)/10;
                }
            });
        }
    }

    @FXML
    public void addSong(ActionEvent e) {
        Pane pane = new Pane();
        Label songName = new Label("Nome da Música");
        Label genre = new Label("Gênero");
        Label artistName = new Label("Artista");
        Label songDuration = new Label("00:00");

        pane.setPrefHeight(84);
        pane.setPrefWidth(706);

        pane.setOnMouseEntered(mouseEvent -> {
            pane.setStyle("-fx-background-color: #616161");
        });
        pane.setOnMouseExited(mouseEvent -> {
            pane.setStyle("-fx-background-color: none");
        });

        pane.setOnMouseClicked(mouseEvent -> {
            try {
                musicClick(mouseEvent);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
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

        pane.getChildren().addAll(songName, genre, artistName, songDuration);
        vboxSongs.getChildren().add(pane);
    }

    public void changeSongVolume(double volume) {
        if(musicFile != null){
            double newVolume = volume/100;
            double logVolume = Math.pow(newVolume, 2);
            mediaPlayer.setVolume(logVolume);
        }

    }

    public void changeSongProgress(double progress) {
        if(musicFile != null){
            Duration duration = mediaPlayer.getTotalDuration();
            duration = duration.multiply(progress/100);
            mediaPlayer.seek(duration);
        }
    }
}
