package com.example.trabalho;

import com.example.trabalho.BackEnd.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class friendsScreenController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    private Image userIcon = new Image(getClass().getResourceAsStream("/com/example/trabalho/imagens/imgIcon.png"));
    private Image following = new Image(getClass().getResourceAsStream("/com/example/trabalho/imagens/following.png"));
    private Image add = new Image(getClass().getResourceAsStream("/com/example/trabalho/imagens/addFriend.png"));

    private ArrayList<String[]> users;
    private ArrayList<String> followings;


    @FXML
    VBox vbox;
    @FXML
    CheckBox friendsCheckbox;
    @FXML
    TextField searchTextField;

    @FXML
    public void initialize() throws FileNotFoundException {
        try {
            users = FileInfo.getMatrixInfo("Database/Banco.csv");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        followings = LogIn.user.getAllFollowings(0);

        try {
            addUsers("");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        friendsCheckbox.setOnAction(event -> {
            try {
                vbox.getChildren().clear();
                addUsers("");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                vbox.getChildren().clear();
                addUsers(newValue);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @FXML
    public void goBack(ActionEvent e) throws IOException {
        this.root = (Parent) FXMLLoader.load(this.getClass().getResource("initialScreen.fxml"));
        this.stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        this.scene = new Scene(this.root);
        this.stage.setScene(this.scene);
        this.stage.show();
    }

    @FXML
    public void friendBtn(ActionEvent e) throws IOException {
        Button btn  = (Button) e.getSource();
        ImageView img, newImg;
        img = (ImageView) btn.getGraphic();
        List<Node> nodes = new ArrayList<Node>();
        Pane pane = (Pane) btn.getParent();
        nodes = pane.getChildren();
        Label name= (Label) nodes.get(1);
        Label id = (Label) nodes.get(4);

        if(img.getImage().equals(following)) {
            newImg = new ImageView(add);
            newImg.setFitHeight(53);
            newImg.setFitWidth(35);
            newImg.setPreserveRatio(true);
            btn.setGraphic(newImg);
            LogIn.user.unfollowUser(name.getText(), id.getText());
        }else{
            newImg = new ImageView(following);
            newImg.setFitHeight(53);
            newImg.setFitWidth(35);
            newImg.setPreserveRatio(true);
            btn.setGraphic(newImg);
            LogIn.user.followUser(name.getText(), id.getText());
        }
        followings = LogIn.user.getAllFollowings(0); //Atualizar a lista de amigos
    }

    @FXML
    public void addUsers(String username) throws IOException {
        for(String[] user : users){
            if(!user[0].equals(LogIn.user.getId())){
                boolean canAdd = false;
                if(user[1].toLowerCase().contains(username.toLowerCase()) || username.equals("")) {
                    canAdd = true;
                }

                if(friendsCheckbox.isSelected() && !followings.contains(user[1])){
                    canAdd = false;
                }

                if(canAdd){
                    Pane pane = new Pane();
                    ImageView icon = new ImageView(userIcon);
                    ImageView btnIcon;
                    Label name = new Label();
                    Label role = new Label();
                    Label id = new Label();
                    Button btn = new Button();

                    pane.setPrefWidth(878);
                    pane.setPrefHeight(76);

                    icon.preserveRatioProperty().set(true);
                    icon.setFitWidth(85);
                    icon.setFitHeight(71);
                    icon.setLayoutX(14);
                    icon.setLayoutY(3);

                    name.setPrefWidth(143);
                    name.setPrefHeight(38);
                    name.setFont(Font.font("System", 17));
                    name.setTextFill(Color.WHITE);
                    name.setLayoutX(131);
                    name.setLayoutY(19);

                    role.setPrefWidth(143);
                    role.setPrefHeight(38);
                    role.setFont(Font.font("System", 17));
                    role.setTextFill(Color.WHITE);
                    role.setAlignment(Pos.CENTER);
                    role.setLayoutX(368);
                    role.setLayoutY(20);

                    id.visibleProperty().set(false);
                    id.setPrefWidth(0);
                    id.setPrefHeight(0);

                    if(followings.contains(user[1])){
                        btnIcon = new ImageView(following);
                    }else{
                        btnIcon = new ImageView(add);
                    }

                    btnIcon.setFitWidth(53);
                    btnIcon.setFitHeight(35);
                    btnIcon.setPreserveRatio(true);
                    btn.setGraphic(btnIcon);

                    btn.setPrefWidth(54);
                    btn.setPrefHeight(38);
                    btn.setLayoutX(721);
                    btn.setLayoutY(17);
                    btn.setStyle("-fx-background-color: none;");
                    btn.setCursor(Cursor.HAND);

                    btn.setOnAction(event ->{
                        try {
                            friendBtn(event);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });

                    id.setText(user[0]);
                    name.setText(user[1]);
                    role.setText(user[4]);


                    pane.getChildren().addAll(icon, name, role, btn, id);
                    vbox.getChildren().add(pane);
                }
            }
        }
    }
}
