package com.example.trabalho;

import com.example.trabalho.BackEnd.LogIn;
import com.example.trabalho.BackEnd.PlaylistDatabase;
import com.example.trabalho.BackEnd.PlaylistOptions;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class addMusicPlaylistScreenController {
    Parent root;
    Stage stage;
    Scene scene;

    private Image playlistImage = new Image(getClass().getResourceAsStream("imagens/empty_image.jpg"));
    private List<List<String>> playlists;

    @FXML
    public void initialize(){
        playlists = PlaylistDatabase.getPlaylistCSVFile();
        addPlaylist(null);
    }

    @FXML
    VBox vbox;

    @FXML
    public void goBack(MouseEvent event) {
        try {
            this.root = (Parent) FXMLLoader.load(this.getClass().getResource(App.lastScreenVisited));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        this.scene = new Scene(this.root);
        this.stage.setScene(this.scene);
        this.stage.show();
    }

    @FXML
    public void addPlaylist(ActionEvent e){
        for(List<String> playlist : playlists){
            if(playlist.get(1).equals(LogIn.user.getName())){
                boolean createNewHBox = true;
                Pane pane = new Pane();
                Label label = new Label(playlist.get(0));
                ImageView imageView = new ImageView(playlistImage);
                imageView.preserveRatioProperty().set(true);

                pane.setCursor(Cursor.HAND);

                label.setPrefWidth(284);
                label.setPrefHeight(35);
                label.setLayoutX(17);
                label.setLayoutY(146);
                label.setAlignment(Pos.CENTER);
                label.setTextFill(Color.WHITE);
                label.setFont(Font.font("System", FontWeight.BOLD, 20));


                imageView.setFitWidth(200);
                imageView.setFitHeight(150);
                imageView.setLayoutX(59);
                imageView.setLayoutY(0);

                pane.getChildren().addAll(imageView, label);
                pane.setOnMouseClicked(event -> {
                    PlaylistOptions.addMusicToPlaylist(PlaylistDatabase.getPlaylistCSVFile(), label.getText(), App.idMusicToAdd);
                    goBack(event);
                });

                List<Node> nodes = vbox.getChildren();
                for(int i = 0; i < nodes.size(); i++){
                    HBox hbox = (HBox) nodes.get(i);
                    if(hbox.getChildren().size() < 3){
                        hbox.getChildren().add(pane);
                        createNewHBox = false;
                        break;
                    }
                }

                if(createNewHBox){
                    HBox hbox = new HBox();
                    hbox.getChildren().add(pane);
                    vbox.getChildren().add(hbox);
                }
            }

        }
    }
}
