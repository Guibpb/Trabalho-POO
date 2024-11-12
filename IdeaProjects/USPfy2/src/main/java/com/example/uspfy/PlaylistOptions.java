package com.example.uspfy;

import java.util.*;

public class PlaylistOptions {

    public static int createPlaylist(List<List<String>> playlistCSVFileList, String playlistName, String userName, String visibility) {
        if(playlistName.contains(",")){
            return 1;
        }


        List<String> newPlaylist = new ArrayList<>();
        newPlaylist.add(0, playlistName);
        newPlaylist.add(1, userName);
        newPlaylist.add(2, visibility);
        playlistCSVFileList.addLast(newPlaylist);
        PlaylistDatabase.updatePlaylistCSVFile(playlistCSVFileList);
        return 0;
    }

    public static int addMusicToPlaylist(List<List<String>> playlistCSVFileList, String playlistName, String musicID) {
        for(List<String> playlist : playlistCSVFileList){
            if(playlist.getFirst().equals(playlistName)){
                for(String checkID : playlist){
                    if(checkID.equals(musicID))
                        return 1;
                }
                playlist.addLast(musicID);
                break;
            }
        }
        PlaylistDatabase.updatePlaylistCSVFile(playlistCSVFileList);
        return 0;
    }

    public static void deletePlaylist(List<List<String>> playlistCSVFileList, String playlistName) {
        for(List<String> playlist : playlistCSVFileList){
            if(playlist.getFirst().equals(playlistName)){
                playlistCSVFileList.remove(playlist);
                break;
            }
        }
        PlaylistDatabase.updatePlaylistCSVFile(playlistCSVFileList);
    }

    public static int editPlaylist(List<List<String>> playlistCSVFileList, String oldPlaylistName, String newPlaylistName, String newVisibility) {
        if(newPlaylistName.contains(",")){
            return 1;
        }

        for(int i=1; i<playlistCSVFileList.size(); i++){
            if(playlistCSVFileList.get(i).getFirst().equals(oldPlaylistName)){
                playlistCSVFileList.get(i).set(0, newPlaylistName);
                playlistCSVFileList.get(i).set(2, newVisibility);
                break;
            }
        }
        PlaylistDatabase.updatePlaylistCSVFile(playlistCSVFileList);
        return 0;
    }

    public static void removeMusicFromPlaylist(List<List<String>> playlistCSVFileList, String playlistName, String musicID){
        for(int i=1; i<playlistCSVFileList.size(); i++){
            if(playlistCSVFileList.get(i).getFirst().equals(playlistName)){
                for(int j=0; j<playlistCSVFileList.get(i).size(); j++){
                    if(playlistCSVFileList.get(i).get(j).equals(musicID)){
                        playlistCSVFileList.get(i).remove(j);
                        break;
                    }
                }
            }
        }
        PlaylistDatabase.updatePlaylistCSVFile(playlistCSVFileList);
    }

    public static void generateTop10Playlist(List<String[]> musicCSVFileList, List<List<String>> playlistCSVFileList, String playlistGenre) {
        List<String> top10playedmusics = new ArrayList<>();
        String playlistName = "TOP 10 MAIS TOCADAS";
        if(playlistGenre != null) {
            playlistName += ": " + playlistGenre.toUpperCase(); //coloca o genero se tiver um no nome

            for(int i = musicCSVFileList.size() - 1; i > 0; i--){
                if(!musicCSVFileList.get(i)[4].equals(playlistGenre))//deixa apenas o genero pedido para passar na playlist
                    musicCSVFileList.remove(i); //iterando de tras pra frente garante que nao ocorra nenhum erro devido ao shift de posiçoes ao remover algum elemento
            }
        }

        //nome, artista e visibilidade padrao
        top10playedmusics.add(0, playlistName);
        top10playedmusics.add(1, "USPfy");
        top10playedmusics.add(2, "public");




        //BUBBLESORT, feito com o conteudo da materia de ic2, mesmo algoritimo, achei o mais simples de implementar
        int limit = musicCSVFileList.size() - 1;
        for(int i=2; i<=limit; i++){ //como a primeira linha e uma template, ele começa no indice 1 
            for(int j=limit; j>=i; j--){
                if(Integer.parseInt(musicCSVFileList.get(j-1)[3]) > Integer.parseInt(musicCSVFileList.get(j)[3])){
                    String[] intermediate = musicCSVFileList.get(j-1);
                    musicCSVFileList.set(j-1, musicCSVFileList.get(j));
                    musicCSVFileList.set(j, intermediate);
                }
            }
        }

        if(musicCSVFileList.size() > 10) {
            for (int i = limit; i > limit - 10; i--) //armazena as top 10 em ordem decrescente, o bubblesort é crescente
                top10playedmusics.add(musicCSVFileList.get(i)[0]);
        }else {
            for (int i = limit; i > 0; i--)
                top10playedmusics.add(musicCSVFileList.get(i)[0]);
        }

        playlistCSVFileList.add(top10playedmusics); //adiciona essa playlist a lista de playlists
        PlaylistDatabase.updatePlaylistCSVFile(playlistCSVFileList); //atualiza o arquivo das playlists
    }
}
