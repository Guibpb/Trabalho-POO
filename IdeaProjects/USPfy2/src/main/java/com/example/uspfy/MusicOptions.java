package com.example.uspfy;

import java.io.File;
import java.util.List;

public class MusicOptions {

    public static void uploadMusic(List<String[]> musicCSVFileList, String artistName, String musicName, File fileName){
        int currentID = Integer.parseInt(musicCSVFileList.getLast()[0]);
        String newID = String.valueOf(currentID + 1);


        String[] newMusic = {newID,artistName,musicName,"0", fileName.getName()};
        musicCSVFileList.add(newMusic);

    }

    public void deleteMusic(){

    }

    public void editMusic(){

    }

}
