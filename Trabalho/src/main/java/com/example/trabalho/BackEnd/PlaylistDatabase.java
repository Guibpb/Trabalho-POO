package com.example.trabalho.BackEnd;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PlaylistDatabase {
    public static List<List<String>> getPlaylistCSVFile(){
        File playlistCSVFile = new File("Database/mplaylists.csv");
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
        File playlistCSVFile = new File("Database/mplaylists.csv");
        try {
            if(playlistCSVFile.createNewFile()){
                PrintWriter printTemplate = new PrintWriter(playlistCSVFile);
                printTemplate.printf("PlaylistName,User,PlaylistVisibility,Musics...");
                printTemplate.close();

            }
            PrintWriter writer = new PrintWriter(new FileWriter("Database/mplaylists.csv"));
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

    public static List<List<String>> generateTop10Playlists(List<String[]> musicCSVFileList) {
        List<List<String>> top10playlists = new ArrayList<>();
        String[] genres = {"null", "Rap", "Rock", "Funk", "Pop", "Classica", "Eletronica"};
        for (String genre : genres) {
            List<String[]> sortableMusicList = new ArrayList<>(musicCSVFileList);
            List<String> top10playedmusics = new ArrayList<>();
            String playlistName = "TOP 10: ";
            if (!genre.equals("null")) {
                playlistName += ": " + genre.toUpperCase(); //coloca o genero se tiver um no nome
                for (int i = sortableMusicList.size() - 1; i > 0; i--) {
                    if (!sortableMusicList.get(i)[4].equals(genre))//deixa apenas o genero pedido para passar na playlist
                        sortableMusicList.remove(i); //iterando de tras pra frente garante que nao ocorra nenhum erro devido ao shift de posiçoes ao remover algum elemento
                }
            }

            top10playedmusics.add(0, playlistName);
            top10playedmusics.add(1, "USPfy");
            top10playedmusics.add(2, "public");

            //BUBBLESORT, feito com o conteudo da materia de ic2, mesmo algoritimo, achei o mais simples de implementar
            int limit = sortableMusicList.size() - 1;
            for(int i=2; i<=limit; i++){ //como a primeira linha e uma template, ele começa no indice 1
                for(int j=limit; j>=i; j--){
                    if(Integer.parseInt(sortableMusicList.get(j-1)[3]) > Integer.parseInt(sortableMusicList.get(j)[3])){
                        String[] intermediary = sortableMusicList.get(j-1);
                        sortableMusicList.set(j-1, sortableMusicList.get(j));
                        sortableMusicList.set(j, intermediary);
                    }
                }
            }
            if(sortableMusicList.size() > 10) {
                for (int i = limit; i > limit - 10; i--) //armazena as top 10 em ordem decrescente, o bubblesort é crescente
                    top10playedmusics.add(sortableMusicList.get(i)[0]);
            }else {
                for (int i = limit; i > 0; i--)
                    top10playedmusics.add(sortableMusicList.get(i)[0]);
            }

            if(top10playedmusics.size() < 13) {
                playlistName = "TOP " + (top10playedmusics.size() - 3) + " MAIS TOCADAS";
                if(!genre.equals("null"))
                    playlistName += ": " + genre.toUpperCase();
                top10playedmusics.set(0, playlistName);
            }

            if(top10playedmusics.size() > 3)
                top10playlists.add(top10playedmusics);

        }

        return top10playlists;
    }
}