package com.example.uspfy;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MusicDatabase {

    public static List<String[]> getMusicCSVFile(File musicCSVFile){

        List<String[]> musicCSVFileList = new ArrayList<>();
        String row;

        try {
            BufferedReader reader = new BufferedReader(new FileReader("musics.csv"));
            while((row = reader.readLine()) != null){
                musicCSVFileList.add(row.split(","));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return musicCSVFileList; // retorna uma lista de arrays
    }

    public static void updateMusicCSVFile(List<String[]> musicCSVFileList){

        try {
            PrintWriter writer = new PrintWriter(new FileWriter("musics.csv"));
            for(String[] data : musicCSVFileList){
                writer.printf("%s,%s,%s,%s,%s\n", data[0], data[1], data[2], data[3], data[4]);
            }
            writer.close();
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }
}
