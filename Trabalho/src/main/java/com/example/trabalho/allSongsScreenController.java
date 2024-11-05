package com.example.trabalho;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.IOException;

public class allSongsScreenController {
    Parent root;
    Stage stage;
    Scene scene;

    @FXML
    VBox vboxSongs;

    @FXML
    public void goBack(ActionEvent e) throws IOException {
        this.root = (Parent) FXMLLoader.load(this.getClass().getResource("initialScreen.fxml"));
        this.stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        this.scene = new Scene(this.root);
        this.stage.setScene(this.scene);
        this.stage.show();
    }

    @FXML
    public void addSong(ActionEvent e) {
        Pane pane = new Pane();
        Label songName = new Label("Nome da Música");
        Label genre = new Label("Gênero");
        Label artistName = new Label("Artista");
        Label songDuration = new Label("00:00");
        Button addPlaylistBtn = new Button("Adicionar");

        pane.setPrefHeight(84);

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

        addPlaylistBtn.setTextFill(Color.WHITE);
        addPlaylistBtn.setPrefWidth(83);
        addPlaylistBtn.setPrefHeight(35);
        addPlaylistBtn.setLayoutX(710);
        addPlaylistBtn.setLayoutY(25);
        addPlaylistBtn.setStyle("-fx-background-color:  #8A2BE2");
        addPlaylistBtn.setCursor(Cursor.HAND);
        addPlaylistBtn.setFont(Font.font("System", FontWeight.BOLD, 14));

        pane.getChildren().addAll(songName, genre, artistName, songDuration, addPlaylistBtn);
        vboxSongs.getChildren().add(pane);
    }
}
