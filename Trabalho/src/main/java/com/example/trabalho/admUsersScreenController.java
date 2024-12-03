package com.example.trabalho;

import com.example.trabalho.BackEnd.FileInfo;
import com.example.trabalho.BackEnd.LogIn;
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
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class admUsersScreenController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    private ArrayList<String[]> users;
    private ArrayList<String> followings;

    @FXML
    VBox vbox;

    @FXML
    public void initialize() throws FileNotFoundException {
        try {
            users = FileInfo.getMatrixInfo("Banco.csv");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        followings = LogIn.user.getAllFollowings(0);

        try {
            addUsers();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    public void goBack(ActionEvent e) throws IOException {
        this.root = (Parent) FXMLLoader.load(this.getClass().getResource("admScreen.fxml"));
        this.stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        this.scene = new Scene(this.root);
        this.stage.setScene(this.scene);
        this.stage.show();
    }

    @FXML
    public void addUsers() throws IOException {
        for(String[] user : users){
            boolean canAdd = false;

            if(!user[0].equals(LogIn.user.getId()) && !user[4].equals("Gerente")) {
                canAdd = canAdd = true;
            }

            if(canAdd){
                Pane pane = new Pane();
                Label name = new Label();
                Label role = new Label();
                Label id = new Label();
                Button btn = new Button("Editar");

                pane.setPrefWidth(878);
                pane.setPrefHeight(76);

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

                btn.setPrefWidth(Region.USE_COMPUTED_SIZE);
                btn.setPrefHeight(38);
                btn.setLayoutX(721);
                btn.setLayoutY(17);
                btn.setStyle("-fx-background-color: #ac12c7;");
                btn.setCursor(Cursor.HAND);

                id.setText(user[0]);
                name.setText(user[1]);
                role.setText(user[4]);

                btn.setOnAction(event ->{
                    try {
                        final String username = name.getText();
                        App.userToEdit = username;
                        switchToSceneEditUser(event);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });

                pane.getChildren().addAll(name, role, btn, id);
                vbox.getChildren().add(pane);
            }
        }
    }

    @FXML
    public void switchToSceneEditUser(ActionEvent event) throws IOException {
        this.root = (Parent) FXMLLoader.load(this.getClass().getResource("admUserEditScreen.fxml"));
        this.stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        this.scene = new Scene(this.root);
        this.stage.setScene(this.scene);
        this.stage.show();
    }
}
