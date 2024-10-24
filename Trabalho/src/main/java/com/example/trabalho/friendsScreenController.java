package com.example.trabalho;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class friendsScreenController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    private Image following = new Image(getClass().getResourceAsStream("/com/example/trabalho/imagens/following.png"));
    private Image add = new Image(getClass().getResourceAsStream("/com/example/trabalho/imagens/addFriend.png"));

    @FXML
    public void goBack(ActionEvent e) throws IOException {
        this.root = (Parent) FXMLLoader.load(this.getClass().getResource("initialScreen.fxml"));
        this.stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        this.scene = new Scene(this.root);
        this.stage.setScene(this.scene);
        this.stage.show();
    }

    @FXML
    public void following(ActionEvent e) throws IOException {
        Button btn  = (Button) e.getSource();
        ImageView img, newImg;
        img = (ImageView) btn.getGraphic();

        if(img.getImage().equals(following)) {
            newImg = new ImageView(add);
            newImg.setFitHeight(53);
            newImg.setFitWidth(35);
            newImg.setPreserveRatio(true);
            btn.setGraphic(newImg);
        }else{
            newImg = new ImageView(following);
            newImg.setFitHeight(53);
            newImg.setFitWidth(35);
            newImg.setPreserveRatio(true);
            btn.setGraphic(newImg);
        }
    }
}
