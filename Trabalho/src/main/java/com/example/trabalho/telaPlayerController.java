package com.example.trabalho;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class telaPlayerController {
    private Image btnPauseImage = new Image("file:com/example/trabalho/imagens/btnPause.png");

    @FXML
    private ImageView imgButton;

    @FXML
    public void btnClick(ActionEvent event) {
        imgButton.setImage(btnPauseImage);
    }

}
