package com.example.uspfy;


import java.io.File;
import java.util.List;

public class MusicPlaylistTestMain {
    public static void main(String[] args) {

        List<String[]> temp;
        temp = MusicDatabase.getMusicCSVFile();



        //musica a ser movida
        File testmusic = new File("Rap do Escanor (Nanatsu No Taizai) Ft. VMZ _ Tauz RapTributo 16 [ ezmp3.cc ].mp3");
        //teste de upload
        MusicOptions.uploadMusic(temp, "Tauz","Rap do Dark Souls","rap",testmusic);

        //teste de editar
        //MusicOptions.editMusic(temp, "3", "funkdogoku","funk");

        //teste de deletar
        //MusicOptions.deleteMusic(temp, testmusic);



       /* //TESTE DE PLAYLIST DATABASE E PLAYLIST OPTIONS
        List<List<String>> plTest = PlaylistDatabase.getPlaylistCSVFile();
        PlaylistOptions.generateTop10Playlist(temp,plTest,null);
*/

    }
}