package com.example.dolist;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ToDoListController {

    public ArrayList<String>tasksList = new ArrayList<String>();
    public int numOfTasks = 0;
    public FileReader fileReader;
    public FileWriter fileWriter;

    @FXML
    VBox vbox = new VBox();

    @FXML
    TextField taskName = new TextField();

    @FXML
    public void initialize() throws IOException {
        readTasks();
    }

    @FXML
    private void onClickAdd() throws IOException {
        if(numOfTasks < 6){
            String task = taskName.getText();
            addTask(task);
            writeTask(task);
            numOfTasks++;
        }
    }

    @FXML
    private void deleteTask(ActionEvent event) {
        Button sourceButton = (Button) event.getSource();
        Pane sourcePane = new Pane();
        Label sourceLabel = new Label();
        sourcePane = (Pane) sourceButton.getParent();
        sourceLabel = (Label) sourcePane.getChildren().get(0);
        tasksList.remove((String)sourceLabel.getText());
        vbox.getChildren().remove(sourceButton.getParent());
        try{
            fileWriter = new FileWriter("tasks.txt");
            fileWriter.close();
            for(int i = 0; i < tasksList.size(); i++){
                writeTask(tasksList.get(i));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        numOfTasks--;
    }

    public void addTask(String task){

        Pane newPane = new Pane();
        newPane.setPrefWidth(1114);
        newPane.setPrefHeight(70);

        Label label = new Label(task);
        label.setId("defaultLabel");
        label.setText(task);

        Button removeBtn = new Button();
        removeBtn.setLayoutX(1000);
        removeBtn.setLayoutY(12);
        removeBtn.setText("Remover");
        removeBtn.setOnAction(this::deleteTask);
        removeBtn.setId("btnRemove");

        newPane.getChildren().add(label);
        newPane.getChildren().add(removeBtn);

        vbox.getChildren().add(newPane);
        taskName.setText("");
    }

    public void writeTask(String task) throws IOException {
        fileWriter = new FileWriter("tasks.txt", true);
        fileWriter.write(task + ";" + "\n");
        fileWriter.close();
    }

    public void readTasks() throws IOException {
        String taskRead = "";
        int data;
        fileWriter = new FileWriter("tasks.txt", true);
        fileReader = new FileReader("tasks.txt");

        do{
            data = fileReader.read();
            if(data != -1 && (char)data != ';'){
                taskRead += (char)data;
            }else{
                if(!(taskRead.isEmpty())) {
                    tasksList.add((String) taskRead);
                    numOfTasks++;
                    taskRead = "";
                    data = fileReader.read();
                }
            }
        }while(data != -1);

        fileReader.close();
        fileWriter.close();

        if(numOfTasks > 0){
            for(int i = 0; i < numOfTasks; i++){
                addTask(tasksList.get(i));
            }
        }
    }
}