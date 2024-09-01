package com.example.testebotao;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label labelText;

    @FXML
    private void onClick(ActionEvent e){
        labelText.setText("Voce clicou no botao");
    }
}