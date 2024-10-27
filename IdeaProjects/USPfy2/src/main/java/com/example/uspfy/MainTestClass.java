package com.example.uspfy;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainTestClass {
    public static void main(String[] args) {

        File musicDirectory = new File("Musics");
        File CSVFile = new File("musics.csv");
        File[] musicFiles = musicDirectory.listFiles();

        //teste
        List<String[]> temp;
        temp = MusicDatabase.getMusicCSVFile(CSVFile);

        temp.get(2)[1] = "lil jon";

        File testmusic = new File("Rap do Ban (Nanatsu no Taizai) _ Tauz RapTributo 50.mp3");
        MusicOptions.uploadMusic(temp, "Tauz","Rap do Ban",testmusic);

        MusicDatabase.updateMusicCSVFile(temp);



    }
}