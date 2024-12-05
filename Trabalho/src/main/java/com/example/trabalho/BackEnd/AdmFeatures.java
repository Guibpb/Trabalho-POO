package com.example.trabalho.BackEnd;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface AdmFeatures {
    String ROLE = "Gerente";

    default boolean deleteAny(String idUserToBeDeleted) throws FileNotFoundException{ //função de ADM
        ArrayList<String[]> matrixInfo = FileInfo.getMatrixInfo("Database/Banco.csv");

        for(String userInfo[] : matrixInfo){
            if(userInfo[0].equals(idUserToBeDeleted)){
                switch(userInfo[4]){
                    case "Artista" -> {
                        ArtistUser artistUser = new ArtistUser(userInfo[0], userInfo[1],userInfo[2],userInfo[3]);
                        artistUser.deleteSelf(artistUser);
                    }
                    case "Gerente" -> {
                        AdmUser admUser = new AdmUser(userInfo[0], userInfo[1],userInfo[2],userInfo[3]);
                        admUser.deleteSelf(admUser);
                    }
                    default -> {
                        PublicUser publicUser = new PublicUser(userInfo[0], userInfo[1],userInfo[2],userInfo[3]);
                        publicUser.deleteSelf(publicUser);
                    }
                }
                return true;
            }
        }

        return false; //mensagem de erro
    }

    default int createAny(String name, String email, String password, String password2, String role) throws FileNotFoundException{
        return SignUp.signUp(name, email, password, password2, role, false);
    }

    default int editAny(String id, String newName, String newEmail, String newPassword, String newPassword2, boolean compareName, boolean compareEmail, boolean comparePassword) throws FileNotFoundException{//função comum
        String tempName = newName;
        String tempEmail = newEmail;
        String tempPassword = newPassword;
        String tempPassword2 = newPassword2;

        if(!compareName)
            tempName = "";
        if(!compareEmail)
            tempEmail = "@.";
        if(!comparePassword)
            tempPassword = tempPassword2 = "";

        int numErro = SignUp.userCompare(tempName, tempEmail, tempPassword, tempPassword2);

        if(numErro != 0){
            //mensagem de erro de acordo com o retorno
            return numErro;
        }

        ArrayList<String[]> dataBank = FileInfo.getMatrixInfo("Database/Banco.csv");
        String oldUserInfo[];
        String oldData, newData;

        for(String[] userData : dataBank){
            if(userData[0].equals(id)){
                oldUserInfo = userData;
                oldData = String.format("\n%s,%s,%s,%s,%s", oldUserInfo[0], oldUserInfo[1], oldUserInfo[2], oldUserInfo[3], oldUserInfo[4]);
                newData = String.format("\n%s,%s,%s,%s,%s", oldUserInfo[0], newName, newEmail, newPassword, oldUserInfo[4]);
                ModifyUser.modUser(FileInfo.getRawData("Database/Banco.csv"), oldData, newData, "Database/Banco.csv");
                break;
            }
        }

        return 0;
    }

    default void seeAllUsers(String FILE_PATH)throws FileNotFoundException{
        ArrayList <String[]> users;
        users = FileInfo.getMatrixInfo("Database/Banco.csv");
        String formatData = "Relatório Estatístico: Usuários\n\n";

        for(String[] userInfo : users){
            String info = String.format("Id de Usuário: %s\nNome: %s\nE-mail: %s\nCargo: %s\n\n", userInfo[0], userInfo[1], userInfo[2], userInfo[4]);
            formatData += info;
        }

        FILE_PATH += "RelatórioUsuários.txt";
        RecordUser.replaceInFile(formatData, FILE_PATH);
    }

    default void seeAllArtists(String FILE_PATH)throws FileNotFoundException{
        ArrayList <String[]> artists;
        artists = FileInfo.getMatrixInfo("Database/Banco.csv");
        String formatData = "Relatório Estatístico: Artistas\n\n";
        artists.removeIf(artist -> !Arrays.asList(artist).contains("Artista"));

        List<String[]> musics;
        musics = MusicDatabase.getMusicCSVFile();

        for(String[] userInfo : artists){
            String info = String.format("Id de Usuário: %s\nNome: %s\nE-mail: %s\n", userInfo[0], userInfo[1], userInfo[2]);
            formatData += info;

            int count = 1;
            for(String[] musicInfo : musics){
                if(musicInfo[1].equals(userInfo[1])){
                    formatData += String.format("Musica %d: %s\n", count, musicInfo[2]);
                    count++;
                }
            }
            formatData += "\n";
        }

        FILE_PATH += "RelatórioArtistas.txt";
        RecordUser.replaceInFile(formatData, FILE_PATH);
    }

    default void seeAllMusics(String FILE_PATH)throws FileNotFoundException{
        List<String[]> musics;
        musics = MusicDatabase.getMusicCSVFile();
        musics.remove(0);
        String formatData = "Relatório Estatístico: Músicas\n\n";

        for(String[] musicInfo : musics){
            String info = String.format("Id de Música: %s\nArtista: %s\nNome da Música: %s\nVizualizações: %s\nGênero: %s\nNome do Arquivo: %s\n\n", musicInfo[0], musicInfo[1], musicInfo[2], musicInfo[3], musicInfo[4], musicInfo[5]);
            formatData += info;
        }

        FILE_PATH += "RelatórioMúsicasGeral.txt";
        RecordUser.replaceInFile(formatData, FILE_PATH);
    }

    default void seeAllPlaylists(String FILE_PATH)throws FileNotFoundException{
        List<List<String>> playlists;
        playlists = PlaylistDatabase.getPlaylistCSVFile();
        playlists.remove(0);
        String formatData = "Relatório Estatístico: Playlists\n\n";

        for(List<String> playlistInfo : playlists){
            formatData += String.format("Nome da Playlist: %s\nUsuário: %s\nVisibilidade: %s\n", playlistInfo.get(0), playlistInfo.get(1), playlistInfo.get(2));
            for(int i = 3; i < playlistInfo.size(); i++){
                formatData += String.format("Música %d: %s\n", i-2, playlistInfo.get(i));
            }
            formatData += "\n";
        }

        FILE_PATH += "RelatórioPlaylistsGeral.txt";
        RecordUser.replaceInFile(formatData, FILE_PATH);
    }
}