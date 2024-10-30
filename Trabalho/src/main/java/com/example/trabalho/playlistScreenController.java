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

public class playlistScreenController {
    private Stage stage;
    private Parent root;
    private Scene scene;

    private Image btnPauseImage = new Image(getClass().getResourceAsStream("/com/example/trabalho/imagens/btnPause.png"));
    private Image btnPlayImage = new Image(getClass().getResourceAsStream("/com/example/trabalho/imagens/btnPlay.png"));

    @FXML
    private ImageView imgButton;
    @FXML
    private Slider durationSlider;
    @FXML
    private ProgressBar durationProgressBar;

    @FXML
    public void btnPlayerOnClick(ActionEvent event) {
        if(imgButton.getImage().equals(btnPlayImage)) {
            imgButton.setImage(btnPauseImage);
        }else{
            imgButton.setImage(btnPlayImage);
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
    public void sliderClcik(MouseEvent e) {
        double progress = durationSlider.getValue();
        System.out.println(progress);
        durationProgressBar.setProgress(progress/100);
    }

}
