package com.example.trabalho;

import com.example.trabalho.BackEnd.FileInfo;
import com.example.trabalho.BackEnd.LogIn;
import com.example.trabalho.BackEnd.PlaylistDatabase;
import javafx.application.Platform;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class initialScreenController {
    private Stage stage;
    private Parent root;
    private Scene scene;
    private Image playlistImage = new Image(getClass().getResourceAsStream("imagens/empty_image.jpg"));
    private List<List<String>> playlists;
    private ArrayList<String[]> users;

    @FXML
    VBox vbox;
    @FXML
    Label username;
    @FXML
    Button createMusicButton;

    @FXML
    public void initialize() throws FileNotFoundException {
        username.setText(LogIn.user.getName());
        playlists = PlaylistDatabase.getPlaylistCSVFile();
        addPlaylist(null);
        users = FileInfo.getMatrixInfo("Banco.csv");
        String role = "";
        for(String[]user : users) {
            if(LogIn.user.getName().equals(user[1])) {
                role = user[4];
                break;
            }
        }
        if(role.equals("Artista")){
            createMusicButton.setVisible(true);
        }else{
            createMusicButton.setVisible(false);
        }
    }

    @FXML
    public void switchToScenePlaylist(javafx.scene.input.MouseEvent e) throws IOException {
        App.lastScreenVisited = "initialScreen.fxml";
        this.root = (Parent) FXMLLoader.load(this.getClass().getResource("playlistScreen.fxml"));
        this.stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        this.scene = new Scene(this.root);
        this.stage.setScene(this.scene);
        this.stage.show();
    }

    @FXML
    public void switchToSceneConfiguration(ActionEvent e) throws IOException {
        this.root = (Parent) FXMLLoader.load(this.getClass().getResource("configurationScreen.fxml"));
        this.stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        this.scene = new Scene(this.root);
        this.stage.setScene(this.scene);
        this.stage.show();
    }

    @FXML
    public void switchToSceneFriends(ActionEvent e) throws IOException{
        this.root = (Parent) FXMLLoader.load(this.getClass().getResource("friendsScreen.fxml"));
        this.stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        this.scene = new Scene(this.root);
        this.stage.setScene(this.scene);
        this.stage.show();
    }

    @FXML
    public void switchToSceneAllPlaylists(ActionEvent e) throws IOException {
        this.root = (Parent) FXMLLoader.load(this.getClass().getResource("allPlaylistsScreen.fxml"));
        this.stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        this.scene = new Scene(this.root);
        this.stage.setScene(this.scene);
        this.stage.show();
    }

    @FXML
    public void switchToSceneAllSongs(ActionEvent e) throws IOException {
        this.root = (Parent) FXMLLoader.load(this.getClass().getResource("allSongsScreen.fxml"));
        this.stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        this.scene = new Scene(this.root);
        this.stage.setScene(this.scene);
        this.stage.show();
    }

    @FXML
    public void switchToSceneCreatePlaylist(ActionEvent e) throws IOException {
        App.lastScreenVisited = "initialScreen.fxml";
        this.root = (Parent) FXMLLoader.load(this.getClass().getResource("createPlaylistScreen.fxml"));
        this.stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        this.scene = new Scene(this.root);
        this.stage.setScene(this.scene);
        this.stage.centerOnScreen();
        this.stage.show();
    }

    @FXML
    public void switchToSceneMusicsConfig(ActionEvent e) throws IOException {
        App.lastScreenVisited = "initialScreen.fxml";
        this.root = (Parent) FXMLLoader.load(this.getClass().getResource("artistMusicsScreen.fxml"));
        this.stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        this.scene = new Scene(this.root);
        this.stage.setScene(this.scene);
        this.stage.centerOnScreen();
        this.stage.show();
    }

    @FXML
    public void switchToSceneAdmScreen(ActionEvent e) throws IOException {
        App.lastScreenVisited = "admScreen.fxml";
        this.root = (Parent) FXMLLoader.load(this.getClass().getResource("admScreen.fxml"));
        this.stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        this.scene = new Scene(this.root);
        this.stage.setScene(this.scene);
        this.stage.centerOnScreen();
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
                pane.setOnMouseClicked(mouseEvent -> {
                    Pane paneDefault = (Pane) mouseEvent.getSource();
                    List<Node> nodes = paneDefault.getChildren();
                    Label labelDefault = (Label) nodes.get(1);
                    playlistScreenController.playlistName = labelDefault.getText();
                    playlistScreenController.playlistOwner = LogIn.user.getName();
                    try {
                        switchToScenePlaylist(mouseEvent);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                });

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
