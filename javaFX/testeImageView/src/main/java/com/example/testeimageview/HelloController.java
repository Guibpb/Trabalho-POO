package com.example.testeimageview;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;

import java.net.URL;

public class HelloController {
    @FXML
    private ImageView image;

    Image img1 = new Image(getClass().getResourceAsStream("/com/example/testeimageview/imagem1.jpg"));
    Image img2 = new Image(getClass().getResourceAsStream("/com/example/testeimageview/imagem2.jpg"));

    int index = 0;

    @FXML
    public void trocarImg(javafx.event.ActionEvent actionEvent) {

        if (index == 0) {
            image.setImage(img1);
            image.setFitWidth(500);
            image.setLayoutX(270);
            image.setLayoutX(50);
            image.setLayoutY(14);
            index++;
        } else {
            image.setImage(img2);
            image.setFitWidth(500);
            image.setFitHeight(270);
            image.setLayoutX(50);
            image.setLayoutY(14);
            index = 0;
        }
    }

}