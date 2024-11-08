package com.example.trabalho;

import com.example.trabalho.BackEnd.PlaylistDatabase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class allPlaylistsController {

    private Stage stage;
    private Parent root;
    private Scene scene;
    private Image playlistImage = new Image(getClass().getResourceAsStream("imagens/empty_image.jpg"));
    private List<List<String>> playlists;

    @FXML
    private VBox vbox;
    @FXML
    private TextField searchTextField;

    @FXML
    public void initialize(){
        playlists = PlaylistDatabase.getPlaylistCSVFile();
        addPlaylsit("");
        searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            addPlaylsit(newValue);
        });
    }

    @FXML
    public void goBack(ActionEvent e) throws IOException {
        Parent root = (Parent) FXMLLoader.load(this.getClass().getResource("initialScreen.fxml"));
        this.stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        this.stage.setScene(scene);
        this.stage.show();
    }

    @FXML
    public void switchToScenePlaylist(javafx.scene.input.MouseEvent e) throws IOException {
        this.root = (Parent) FXMLLoader.load(this.getClass().getResource("playlistScreen.fxml"));
        this.stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        this.scene = new Scene(this.root);
        this.stage.setScene(this.scene);
        this.stage.show();
    }


    public void addPlaylsit(String playlistName){
        vbox.getChildren().clear();

        for(List<String> playlist : playlists){
            boolean canAdd = false;

            if(playlist.get(0).toLowerCase().contains(playlistName.toLowerCase()) || playlist.get(1).toLowerCase().contains(playlistName.toLowerCase()) ||playlistName.equals("")){
                canAdd = true;
            }

            if(playlist.get(2).equals("public") && canAdd){
                boolean createNewHBox = true;

                Pane pane = new Pane();
                pane.setCursor(Cursor.HAND);

                Label label1 = new Label(playlist.get(0));
                Label label2 = new Label(playlist.get(1));

                ImageView imageView = new ImageView(playlistImage);
                imageView.preserveRatioProperty().set(true);

                label1.setPrefWidth(284);
                label1.setPrefHeight(35);
                label1.setLayoutX(17);
                label1.setLayoutY(146);
                label1.setAlignment(Pos.CENTER);
                label1.setTextFill(Color.WHITE);
                label1.setFont(Font.font("System", FontWeight.BOLD, 20));

                label2.setPrefWidth(140);
                label2.setPrefHeight(25);
                label2.setLayoutX(89);
                label2.setLayoutY(188);
                label2.setAlignment(Pos.CENTER);
                label2.setTextFill(Color.WHITE);
                label2.setFont(Font.font("System", 14));

                imageView.setFitWidth(200);
                imageView.setFitHeight(150);
                imageView.setLayoutX(59);
                imageView.setLayoutY(0);

                pane.getChildren().addAll(imageView, label1, label2);
                pane.setOnMouseClicked(mouseEvent -> {
                    Pane paneDefault = (Pane) mouseEvent.getSource();
                    List<Node> nodes = paneDefault.getChildren();
                    Label labelPlaylist = (Label) nodes.get(1);
                    Label labelName = (Label) nodes.get(2);
                    playlistScreenController.playlistName = labelPlaylist.getText();
                    playlistScreenController.playlistOwner = labelName.getText();
                    try {
                        App.lastScreenVisited = "allPlaylistsScreen.fxml";
                        switchToScenePlaylist(mouseEvent);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
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
