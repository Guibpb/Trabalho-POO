package com.example.uspfy;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PlaylistDatabase {

    public static List<List<String>> getPlaylistCSVFile(){
        File playlistCSVFile = new File("mplaylists.csv");
        List<List<String>> playlistCSVFileList = new ArrayList<>(); //List é mais generico, permite trocar dps entre Linked e Array
        String row; //cada linha do arquivo
        try {
            if(playlistCSVFile.createNewFile()){
                PrintWriter printTemplate = new PrintWriter(playlistCSVFile);
                printTemplate.printf("PlaylistName,User,PlaylistVisibility,Musics...");
                printTemplate.close();

            }

            BufferedReader reader = new BufferedReader(new FileReader(playlistCSVFile));
            while((row = reader.readLine()) != null){
                List<String> rowList = new ArrayList<>(); //uma Lista com os dados de cada linha do arquivo
                String[] values = row.split(",");
                for (String value : values) {
                    rowList.add(value); //no intellij ele fala q da pra substituir por um addAll, mas resolvi deixar assim, unico "warning" do programa
                }
                playlistCSVFileList.add(rowList);
            }
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return playlistCSVFileList; //uma lista de listas de strings
    }

    public static void updatePlaylistCSVFile(List<List<String>> playlistCSVFileList){
        File playlistCSVFile = new File("mplaylists.csv");
        try {
            if(playlistCSVFile.createNewFile()){
                PrintWriter printTemplate = new PrintWriter(playlistCSVFile);
                printTemplate.printf("PlaylistName,User,PlaylistVisibility,Musics...");
                printTemplate.close();

            }
            PrintWriter writer = new PrintWriter(new FileWriter("mplaylists.csv"));
            for(List<String> data : playlistCSVFileList){ //itera sobre cada "playlist"
                for (int i = 0; i < data.size(); i++) { //itera sobre cada string contida na playlist
                    writer.print(data.get(i));
                    if(i != data.size()-1)
                        writer.print(","); //desse jeito eu n preciso arrumar um jeito de tirar a virgula dps do ultimo elemento
                }
                writer.println(); //prox playlist dps de ler tudo de uma
            }
            writer.close();
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }
}