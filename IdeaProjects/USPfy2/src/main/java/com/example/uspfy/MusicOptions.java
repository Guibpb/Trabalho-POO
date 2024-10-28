package com.example.uspfy;

import java.io.File;
import java.util.List;

public class MusicOptions {

    public static void uploadMusic(List<String[]> musicCSVFileList, String artistName, String musicName, String musicGenre, File musicFile){
        int currentID = Integer.parseInt(musicCSVFileList.getLast()[0]);
        String newID = String.valueOf(currentID + 1);

        String[] newMusic = {newID,artistName,musicName,"0",musicGenre, musicFile.getName()};
        musicCSVFileList.add(newMusic);
        MusicDatabase.updateMusicCSVFile(musicCSVFileList);
    }

    public static void deleteMusic(List<String[]> musicCSVFileList, String musicID){
        musicCSVFileList.removeIf(music -> music[0].equals(musicID));
        /* equivalente:
        for(String[] music : musicCSVFileList){
            if(music[0].equals(musicID)){
                musicCSVFileList.remove(music);
            }
        }
         */
        MusicDatabase.updateMusicCSVFile(musicCSVFileList);
    }



    public static void editMusic(List<String[]> musicCSVFileList,String musicID, String newName, String newGenre){
        for (String[] music : musicCSVFileList) {
            if (music[0].equals(musicID)) {
                music[2] = newName;
                music[4] = newGenre;
            }
        }
        MusicDatabase.updateMusicCSVFile(musicCSVFileList);
    }



}


