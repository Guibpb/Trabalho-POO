import java.util.*;

public class PlaylistOptions {

    /**
     * Cria uma nova playlist, sendo adicionada ao bando de dados das playlists.
     * @param playlistCSVFileList ArrayList de playlists na qual vai ser adicionada a nova playlist.
     * @param playlistName Nome da nova playlist.
     * @param userName Nome do usuário que criou a nova playlist.
     * @param visibility Visibilidade da nova playlist.
     * @return Retorna 1 em casos inválidos, retorna 0 por padrão.
     */
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

    /**
     *Adiciona uma música a uma playlist ja existente, atualiza a playlist no banco de dados das playlists.
     * @param playlistCSVFileList ArrayList de playlists que vai ser atualizado com a playlist editada.
     * @param playlistName Nome da playlist a qual vai ser adicionada a nova música.
     * @param musicID ID da musica que vai ser adicionada à playlist.
     * @return Retorna 1 em casos inválidos, retorna 0 por padrão.
     */
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

    /**
     * Deleta uma playlist do banco de dados, atualiza a playlist no banco de dados das playlists.
     * @param playlistCSVFileList ArrayList de playlists do qual vai ser deletado a playlist.
     * @param playlistName Nome da playlist que vai ser deletada.
     */
    public static void deletePlaylist(List<List<String>> playlistCSVFileList, String playlistName) {
        for(List<String> playlist : playlistCSVFileList){
            if(playlist.getFirst().equals(playlistName)){
                playlistCSVFileList.remove(playlist);
                break;
            }
        }
        PlaylistDatabase.updatePlaylistCSVFile(playlistCSVFileList);
    }

    /**
     * Edita os dados de uma playlist, atualiza a playlist no banco de dados das playlists.
     * @param playlistCSVFileList ArrayList de playlists que vai ser atualizado com a playlist editada.
     * @param oldPlaylistName Nome atual da playlist.
     * @param newPlaylistName Novo nome da playlist.
     * @param newVisibility Nova visibilidade da playlist.
     * @return Retorna 1 em casos inválidos, retorna 0 por padrão.
     */
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

    /**
     * Remove uma música de uma playlist ja existente, atualiza a playlist no banco de dados das playlists.
     * @param playlistCSVFileList ArrayList de playlists que vai ser atualizado com a playlist editada.
     * @param playlistName Nome da playlist a qual vai ser removida uma música.
     * @param musicID ID da musica que vai ser removida da playlist.
     */
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

    /**
     * Gera uma playlist com as top 10 musicas mais tocadas da plataforma, caso o playlistGenre seja diferente de null,
     * gera uma playlist com as top 10 músicas mais tocadas para o genero passado como parâmetro. Caso o número de músicas seja
     * inferior a 10, gera uma playlist com o número de músicas mais tocadas. A playlist é gerada por meio de um BubbleSort que percorre
     * as visualizações das músicas da plataforma.
     * @param musicCSVFileList ArrayList de músicas que será lido para gerar a playlist.
     * @param playlistCSVFileList ArrayList de playlists que será atualizado com a nova playlist.
     * @param playlistGenre Gênero da playlist das top 10 músicas mais tocadas, caso seja null, a playlist não pussui um gênero específico.
     * @return Retorna 1 em casos inválidos, retorna 0 por padrão.
     */
    public static int generateTop10Playlist(List<String[]> musicCSVFileList, List<List<String>> playlistCSVFileList, String playlistGenre) {
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
                    String[] intermediary = musicCSVFileList.get(j-1);
                    musicCSVFileList.set(j-1, musicCSVFileList.get(j));
                    musicCSVFileList.set(j, intermediary);
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

        if(top10playedmusics.size() <= 3)
            return 1; //erro se nao tiver nenhuma musica

        if(top10playedmusics.size() < 13) {
            playlistName = "TOP " + (top10playedmusics.size() - 3) + " MAIS TOCADAS";
            if(playlistGenre != null)
                playlistName += ": " + playlistGenre.toUpperCase();
            top10playedmusics.set(0, playlistName);
        }

        playlistCSVFileList.add(top10playedmusics); //adiciona essa playlist a lista de playlists
        PlaylistDatabase.updatePlaylistCSVFile(playlistCSVFileList); //atualiza o arquivo das playlists
        return 0;
    }
}