package com.example.trabalho;

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
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class playlistScreenController {
    public static String playlistName = "";
    public static String playlistOwner = "";

    private List<String[]> musics;
    private List<String> playlist;
    private List<File> listMusicsFiles = new ArrayList<>();
    private List<String> listArtists = new ArrayList<>();
    private List<String> listMusicsNames = new ArrayList<>();
    private List<Pane> listMusicsPane = new ArrayList<>();

    private Stage stage;
    private Parent root;
    private Scene scene;

    private Image btnPauseImage = new Image(getClass().getResourceAsStream("/com/example/trabalho/imagens/btnPause.png"));
    private Image btnPlayImage = new Image(getClass().getResourceAsStream("/com/example/trabalho/imagens/btnPlay.png"));

    private boolean runSong = false;
    private boolean mousePressedOnDurationSlider = false;

    private double totalSeconds;
    private double progressRate;
    private int currentIndexPlaying = -1;

    Timer timer;

    File musicFile;

    @FXML
    private Label labelPlaylistName;
    @FXML
    private Label labelPlaylistOwner;
    @FXML
    private Label footerLabelMusicName;
    @FXML
    private Label footerLabelArtistName;
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
        labelPlaylistName.setText(playlistName);
        labelPlaylistOwner.setText(playlistOwner);
        durationSlider.setOnMousePressed(mouseEvent -> {
            mousePressedOnDurationSlider = true;
        });
        durationSlider.setOnMouseReleased(mouseEvent -> {
            mousePressedOnDurationSlider = false;
            durationSliderClick(mouseEvent);
        });
        musics = MusicDatabase.getMusicCSVFile();
        List<List<String>> playlists = PlaylistDatabase.getPlaylistCSVFile();
        for(List<String> findPlaylist : playlists) {
            if(findPlaylist.get(0).equals(playlistName)) {
                playlist = findPlaylist;
                break;
            }
        }
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
                musicsToAdd.add(music);
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
            listMusicsFiles.add(defaultFile);
            listArtists.add(music[1]);
            listMusicsNames.add(music[2]);
            Media defaultMedia = new Media(defaultFile.toURI().toString());
            MediaPlayer defaultMediaPlayer = new MediaPlayer(defaultMedia);
            defaultMediaPlayer.setOnReady(() -> {
                Duration duration = defaultMediaPlayer.getMedia().getDuration();
                songDuration.setText(formatDuration(duration));
            });

            pane.setPrefHeight(84);
            pane.setPrefWidth(706);

            pane.setOnMouseEntered(mouseEvent -> {
                if(currentIndexPlaying == -1 || !pane.equals(listMusicsPane.get(currentIndexPlaying))){
                    pane.setStyle("-fx-background-color: #616161");
                }

            });
            pane.setOnMouseExited(mouseEvent -> {
                if(currentIndexPlaying == -1 || !pane.equals(listMusicsPane.get(currentIndexPlaying))){
                    pane.setStyle("-fx-background-color: none");
                }
            });
            pane.setOnMouseClicked(mouseEvent -> {

                try {
                    chooseSong(index);
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
            listMusicsPane.add(pane);
            vboxSongs.getChildren().add(pane);
        }
    }

    @FXML
    public void durationSliderClick(MouseEvent e) {
        double progress = durationSlider.getValue();
        durationProgressBar.setProgress(progress/100);
        changeSongProgress(progress);
    }

    @FXML
    public void volumeSliderClick(MouseEvent e) {
        double volume = volumeSlider.getValue();
        volumeProgressBar.setProgress(volume/100);
        changeSongVolume(volume);
    }

    @FXML
    public void btnPlayerOnClick(ActionEvent e) throws IOException{
        runMusic();
    }

    @FXML
    public void nextMusic(ActionEvent e) throws IOException {
        if(runSong){
            timer.cancel();
        }
        if(currentIndexPlaying != -1 && currentIndexPlaying + 1 < listMusicsFiles.size()){
            chooseSong(currentIndexPlaying + 1);
        }else{
            chooseSong(0);
        }
    }

    @FXML
    public void lastMusic(ActionEvent e) throws IOException {
        if(runSong){
            timer.cancel();
        }
        if(currentIndexPlaying != -1 && currentIndexPlaying - 1 >= 0){
            chooseSong(currentIndexPlaying - 1);
        }else{
            chooseSong(0);
        }
    }

    public void chooseSong(int index) throws IOException {
        runSong = false;
        imgButton.setImage(btnPlayImage);
        currentIndexPlaying = index;
        media = null;
        musicFile = null;
        if(mediaPlayer != null){
            mediaPlayer.dispose(); //"Exclui" o media player para escolher outra música
        }
        musicFile = listMusicsFiles.get(index);
        footerLabelMusicName.setText(listMusicsNames.get(index));
        footerLabelArtistName.setText(listArtists.get(index));
        listMusicsPane.get(index).setStyle("-fx-background-color: #8a8888");
        for(Pane pane : listMusicsPane){
            if(!pane.equals(listMusicsPane.get(index))){
                pane.setStyle("-fx-background-color: #1b1c1f");
            }
        }

        if(musicFile.exists()){
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

            runMusic();

        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setContentText("Não foi possível encontrar o arquivo");
            alert.setHeaderText(null);
            alert.showAndWait();
        }
    }

    public void runMusic() {
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
                        }else{
                            if(!mousePressedOnDurationSlider){
                                durationProgressBar.setProgress(0);
                                durationSlider.setValue(0);
                                if(listMusicsFiles.size() > currentIndexPlaying + 1){
                                    try {
                                        Platform.runLater(() -> {
                                            try {
                                                timer.cancel();
                                                runSong = false;
                                                chooseSong(currentIndexPlaying + 1);
                                            } catch (IOException e) {
                                                throw new RuntimeException(e);
                                            }
                                        });
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }else{
                                    try {
                                        Platform.runLater(() -> {
                                            try {
                                                timer.cancel();
                                                runSong = false;
                                                chooseSong(0);
                                            } catch (IOException e) {
                                                throw new RuntimeException(e);
                                            }
                                        });
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }

                        }
                    }
                }, 0, 100);
            }else{
                timer.cancel();
                runSong = false;
            }
        }
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

    private String formatDuration(Duration duration) {
        long minutes = (long) duration.toMinutes();
        long seconds = (long) (duration.toSeconds() % 60);
        return String.format("%02d:%02d", minutes, seconds);
    }
}
