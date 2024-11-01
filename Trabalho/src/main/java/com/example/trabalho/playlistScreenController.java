package com.example.trabalho;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

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

    Timer timer = new Timer();

    @FXML
    private ImageView imgButton = new ImageView(btnPlayImage);
    @FXML
    private Slider durationSlider;
    @FXML
    private ProgressBar durationProgressBar;
    @FXML
    private Slider volumeSlider;
    @FXML
    private ProgressBar volumeProgressBar;

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
    }

    @FXML
    public void volumeSliderClcik(MouseEvent e) {
        double progress = volumeSlider.getValue();
        volumeProgressBar.setProgress(progress/100);
    }

    @FXML
    public void btnPlayerOnClick(ActionEvent e) throws IOException {
        musicClick(null);
    }

    @FXML
    public void musicClick(MouseEvent e){
        if(imgButton.getImage().equals(btnPlayImage)) {
            imgButton.setImage(btnPauseImage);
        }else{
            imgButton.setImage(btnPlayImage);
        }

        if(!runSong){
            runSong = true;
            timer.scheduleAtFixedRate(new TimerTask() {
                public void run() {
                    durationProgressBar.setProgress((durationSlider.getValue())/100 + 0.005);
                    durationSlider.setValue(durationSlider.getValue() + 0.5);
                }
            }, 0, 250);
        }else{
            timer.cancel();
            runSong = false;
        }

    }

}
