import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public interface Commandable {
    String ROLE = "Gerente";

    default boolean deleteAny(String userToBeDeleted) throws FileNotFoundException{ //função de ADM
        ArrayList<String[]> matrixInfo = FileInfo.getMatrixInfo("Database/Banco.csv"); 

        for(String userInfo[] : matrixInfo){
            if(userInfo[1].equals(userToBeDeleted)){
                String data = String.format("\n%s,%s,%s,%s,%s,%s", userInfo[0], userInfo[1], userInfo[2], userInfo[3], userInfo[4], userInfo[5]);
                ModifyUser.modUser(FileInfo.getRawData("Database/Banco.csv"),data, "", "Database/Banco.csv");
                return true; //alterar isso aqui pra remover os arquivos de seguidores tbm kkk

            }
        }

        return false; //mensagem de erro
    }

    default int createAny(String name, String email, String password, String password2, String role) throws FileNotFoundException{
        return SignUp.signUp(name, email, password, password2, role, false);
    }

    /*boolean seeAllUsers();
    boolean seeAllArtists();
    boolean seeAllMusics();
    boolean seeAllPlaylists();*/

    default void generateTop10Playlist(List<String[]> musicCSVFileList, List<List<String>> playlistCSVFileList, String playlistGenre) {
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
