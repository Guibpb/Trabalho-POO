package com.example.trabalho;

import com.example.trabalho.BackEnd.LogIn;
import com.example.trabalho.BackEnd.PlaylistDatabase;
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
import java.util.List;

public class addMusicPlaylistScreenController {
    Parent root;
    Stage stage;
    Scene scene;

    private Image playlistImage = new Image(getClass().getResourceAsStream("imagens/playlistIcon.png"));
    private List<List<String>> playlists;

    @FXML
    public void initialize() throws IOException {
        playlists = PlaylistDatabase.getPlaylistCSVFile();
        playlists.remove(0);
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
        this.stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        this.scene = new Scene(this.root);
        this.stage.setScene(this.scene);
        this.stage.show();
    }

    @FXML
    public void addPlaylist(ActionEvent e) {
        for (List<String> playlist : playlists) {
            if (playlist.get(1).equals(LogIn.user.getName())) {
                boolean createNewHBox = true;
                Pane pane = new Pane();
                Label label = new Label(playlist.get(0));
                ImageView imageView = new ImageView(playlistImage);
                imageView.preserveRatioProperty().set(true);

                pane.setCursor(Cursor.HAND);

                label.setPrefWidth(284);
                label.setPrefHeight(35);
                label.setLayoutX(17);
                label.setLayoutY(155);
                label.setAlignment(Pos.CENTER);
                label.setTextFill(Color.WHITE);
                label.setFont(Font.font("System", FontWeight.BOLD, 20));


                imageView.setFitWidth(200);
                imageView.setFitHeight(150);
                imageView.setLayoutX(85);
                imageView.setLayoutY(0);

                pane.getChildren().addAll(imageView, label);
                pane.setOnMouseClicked(event -> {
                    int response = LogIn.user.addMusicToPlaylist(PlaylistDatabase.getPlaylistCSVFile(), label.getText(), App.idMusicToAdd);
                    if(response == 1){
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Erro");
                        alert.setHeaderText(null);
                        alert.setContentText("Essa música já pertence a essa playlist");
                        alert.showAndWait();
                    }else{
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle(null);
                        alert.setHeaderText(null);
                        alert.setContentText("Música adicionada com sucesso");
                        alert.showAndWait();
                        goBack(event);
                    }

                });

                List<Node> nodes = vbox.getChildren();
                for (int i = 0; i < nodes.size(); i++) {
                    HBox hbox = (HBox) nodes.get(i);
                    if (hbox.getChildren().size() < 3) {
                        hbox.getChildren().add(pane);
                        createNewHBox = false;
                        break;
                    }
                }

                if (createNewHBox) {
                    HBox hbox = new HBox();
                    hbox.getChildren().add(pane);
                    vbox.getChildren().add(hbox);
                }
            }

        }
    }
}