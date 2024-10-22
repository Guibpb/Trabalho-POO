package com.example.trabalho;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class playlistScreenController {
    private Image btnPauseImage = new Image(getClass().getResourceAsStream("/com/example/trabalho/imagens/btnPause.png"));
    private Image btnPlayImage = new Image(getClass().getResourceAsStream("/com/example/trabalho/imagens/btnPlay.png"));

    @FXML
    private ImageView imgButton;

    @FXML
    public void btnPlayerOnClick(ActionEvent event) {
        if(imgButton.getImage().equals(btnPlayImage)) {
            imgButton.setImage(btnPauseImage);
        }else{
            imgButton.setImage(btnPlayImage);
        }
    }

}
