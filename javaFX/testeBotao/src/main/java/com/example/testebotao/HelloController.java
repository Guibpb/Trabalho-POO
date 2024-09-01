package com.example.testebotao;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label labelMessage;

    @FXML
    private void onClcik(ActionEvent e){
        labelMessage.setText("Voce clicou no botao");
    }
}