package com.example.uspfy;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainTestClass {
    public static void main(String[] args) {

        File CSVFile = new File("musics.csv");

        //teste
        List<String[]> temp;
        temp = MusicDatabase.getMusicCSVFile(CSVFile);


        //temp.get(1)[1] = "everest";

        File testmusic = new File("Rap do Dark Souls _ Tauz RapGame 33.mp3");
        MusicOptions.uploadMusic(temp, "Tauz","Rap do Dark Souls","rap",testmusic);

        //MusicOptions.editMusic(temp, "3", "funkdogoku","funk");

        //MusicOptions.deleteMusic(temp, "7");


        //MusicDatabase.updateMusicCSVFile(temp);
    }
}