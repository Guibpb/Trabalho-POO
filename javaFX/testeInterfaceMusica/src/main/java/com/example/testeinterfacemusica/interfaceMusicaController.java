package com.example.testeinterfacemusica;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.IOException;

public class interfaceMusicaController{

    @FXML
    private ImageView btnPlayImage;

    Clip clip;
    AudioInputStream audioStream;
    public Image imgPlay = new Image(getClass().getResourceAsStream("/com/example/testeinterfacemusica/play.png"));
    public Image imgPause = new Image(getClass().getResourceAsStream("/com/example/testeinterfacemusica/pause.png"));

    int indexPlayer = 1;


    @FXML
    public void playClicked(javafx.event.ActionEvent actionEvent) {
        if(indexPlayer == 1){
            btnPlayImage.setImage(imgPause);
            indexPlayer = 0;
            clip.start();
        }else{
            btnPlayImage.setImage(imgPlay);
            indexPlayer = 1;
            clip.stop();
        }
    }

    @FXML
    public void escolherMusica(javafx.event.ActionEvent actionEvent) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fc.showOpenDialog(fc);
        File f = fc.getSelectedFile();
        audioStream = AudioSystem.getAudioInputStream(f);
        clip = AudioSystem.getClip();
        clip.open(audioStream);
    }
}