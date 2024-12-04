import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * Interface que representa ações que qualquer usuário pode realizar.
 * @author Guilherme
 * @author Mikhael
 */
interface CommonFeatures {
    /**
     * Usuário logado segue outro usuário da lista disponível. Dois arquivos são criados, um para seguidores 
     * do usuário a ser seguido, e um para seguidos do usuários logado. Uma string é formatada com os dados
     * de cada usuário e salva em um arquivo csv.
     * Classe RecordUser escreve as informações no arquivo. 
     * @param userToBeFollowed Nome do usuário a ser seguido.
     * @param idUserToBeFollowed Id do usuário a ser seguido.
     * @return Sucesso da ação.
     * @throws FileNotFoundException Tratamento de erro para arquivo inexistente.
     */
    default boolean followUser(String userToBeFollowed, String idUserToBeFollowed) throws FileNotFoundException{
        String id = LogIn.user.getId();
        String name = LogIn.user.getName();

        final String FOLLOWINGS_FILE_NAME = String.format("Followings/FollowingsOfUser%s.csv", id);
        File followingsFile = new File(FOLLOWINGS_FILE_NAME);
        String formatData;

        if(followingsFile.exists())
            formatData = String.format("\n%s,%s",userToBeFollowed, idUserToBeFollowed);

        else
            formatData = String.format("Seguindo,Id\n%s,%s",userToBeFollowed, idUserToBeFollowed);    
        
        RecordUser.writeInFile(formatData, FOLLOWINGS_FILE_NAME);

        final String FOLLOWERS_FILE_NAME = String.format("Followers/FollowersOfUser%s.csv", idUserToBeFollowed);
        File followersFile = new File(FOLLOWERS_FILE_NAME);

        if(followersFile.exists())
            formatData = String.format("\n%s,%s",name, id);

        else
            formatData = String.format("Seguidores,Id\n%s,%s",name, id); 
        
        RecordUser.writeInFile(formatData, FOLLOWERS_FILE_NAME);

        return false;
    }

    /**
     * O usuário logado realiza ação de parar de seguir um dos seguidores válidos. No arquivo o dado é
     * substituído por uma String vazia.
     * @param userToBeUnfollowed Nome do seguidor.
     * @param idUserToBeUnfollowed Id do seguidor.
     * @return Sucesso da ação.
     * @throws FileNotFoundException Tratamento de erro para arquivo inexistente.
     */
    default boolean unfollowUser(String userToBeUnfollowed, String idUserToBeUnfollowed) throws FileNotFoundException{
        String id = LogIn.user.getId();
        String name = LogIn.user.getName();

        final String FOLLOWINGS_FILE_NAME = String.format("Followings/FollowingsOfUser%s.csv", id);

        String formatData;
        formatData = String.format("\n%s,%s",userToBeUnfollowed, idUserToBeUnfollowed); 
        ModifyUser.modUser(FileInfo.getRawData(FOLLOWINGS_FILE_NAME), formatData, "", FOLLOWINGS_FILE_NAME);

        final String FOLLOWERS_FILE_NAME = String.format("Followers/FollowersOfUser%s.csv", idUserToBeUnfollowed);
        formatData = String.format("\n%s,%s",name, id);

        System.out.println(FileInfo.getRawData(FOLLOWERS_FILE_NAME));
        
        ModifyUser.modUser(FileInfo.getRawData(FOLLOWERS_FILE_NAME), formatData, "", FOLLOWERS_FILE_NAME);

        return false;
    }

    /**
     * Retorna um Array List com os dados dos seguidores do usuário logado, dependendo do identifier retorna
     * o nome ou o ID. 
     * @param identifier Inteiro que identifica se os dados serão sobre o nome ou o ID do usuário.
     * @return ArrayList de String com os dados de todos os seguidores do usuário logado.
     * @throws FileNotFoundException Tratamento de erro para arquivo inexistente.
     */
    
    default ArrayList<String> getAllFollowers(int identifier) throws FileNotFoundException{
        String id = LogIn.user.getId();

        ArrayList <String> allFollowers = new ArrayList<>();

        final String FOLLOWERS_FILE_NAME = String.format("Followers/FollowersOfUser%s.csv", id);
        ArrayList <String[]> userInfo;

        try {
            userInfo = FileInfo.getMatrixInfo(FOLLOWERS_FILE_NAME);
        } catch (FileNotFoundException e) {
            return allFollowers;  //retorna vazio
        }
        
        for(String [] names : userInfo){
            allFollowers.add(names[identifier]);
        }
        
        return allFollowers;
    }

    /**
     * Retorna um Array List com os dados de quem o usuário logado está seguindo, dependendo do identifier retorna
     * o nome ou o ID. 
     * @param identifier Inteiro que identifica se os dados serão sobre o nome ou o ID do usuário.
     * @return ArrayList de String com os dados de quem o usuário logado está seguindo.
     * @throws FileNotFoundException Tratamento de erro para arquivo inexistente.
     */
    default ArrayList<String> getAllFollowings(int identifier) throws FileNotFoundException{
        String id = LogIn.user.getId();
        
        ArrayList <String> allFollowings = new ArrayList<>();

        final String FOLLOWINGS_FILE_NAME = String.format("Followings/FollowingsOfUser%s.csv", id);
        ArrayList <String[]> userInfo;
        try {
            userInfo = FileInfo.getMatrixInfo(FOLLOWINGS_FILE_NAME);
        } catch (FileNotFoundException e) {
            return allFollowings; //retorna vazio
        }

        for(String [] names : userInfo){
            allFollowings.add(names[identifier]);
        }
        
        return allFollowings;
    }

    /**
     * Método que atualiza o arquivo para cada mudança de seguidor/seguido.
     * @param newData Dados do usuário a ser editado nos seguidores/seguidos.
     * @throws FileNotFoundException Tratamento de erro para arquivo inexistente.
     */

    default void updateFollowers(String newData) throws FileNotFoundException {
        String id = LogIn.user.getId();
        String name = LogIn.user.getName();

        String genericFileName;
        ArrayList<String> followingsName = getAllFollowings(1);
        String formatData = String.format("\n%s,%s",name, id);

        for(String idFollowings : followingsName){//atualiza o usuário de todas as pastas de seguidores
            genericFileName = String.format("Followers/FollowersOfUser%s.csv", idFollowings);
            ModifyUser.modUser(FileInfo.getRawData(genericFileName), formatData, newData, genericFileName);
        }

        followingsName = getAllFollowers(1);

        for(String idFollowers : followingsName){//atualiza o usuário de todas as pastas de seguidos
            genericFileName = String.format("Followings/FollowingsOfUser%s.csv", idFollowers);
            ModifyUser.modUser(FileInfo.getRawData(genericFileName), formatData, newData, genericFileName);
        }
    }

    /**
     * Método que apaga os dados do usuário logado em todas as pastas.
     * @throws FileNotFoundException Tratamento de erro para arquivo inexistente.
     */
    default void deleteSelf() throws FileNotFoundException{
        String id = LogIn.user.getId();

        ModifyUser.modUser(FileInfo.getRawData("Database/Banco.csv"), LogIn.user.getFormatData(), "", "Database/Banco.csv");
        updateFollowers(""); //apaga o usuario de todas as pastas

        String fileToBeDeleted = String.format("Followers/FollowersOfUser%s.csv", id);

        try {
            File followersFile = new File(fileToBeDeleted);
            followersFile.delete();//apaga a pasta de seguidores
        } catch (Exception e) {
        }
        

        fileToBeDeleted = String.format("Followings/FollowingsOfUser%s.csv", id);

        try {
            File followingsFile = new File(fileToBeDeleted);
            followingsFile.delete();//apaga a pasta de seguidos
        } catch (Exception e) {
        }
    }

    /**
     * Método que apaga os dados do usuário logado em todas as pastas.
     * Método alterado para funcionar e deletar qualquer tipo de usuário.
     * @throws FileNotFoundException Tratamento de erro para arquivo inexistente.
     */
    default void deleteSelf(User user) throws FileNotFoundException{
        String id = user.getId();

        ModifyUser.modUser(FileInfo.getRawData("Database/Banco.csv"), user.getFormatData(), "", "Database/Banco.csv");
        updateFollowers(""); //apaga o usuario de todas as pastas

        String fileToBeDeleted = String.format("Followers/FollowersOfUser%s.csv", id);

        try {
            File followersFile = new File(fileToBeDeleted);
        followersFile.delete();//apaga a pasta de seguidores
        } catch (Exception e) {
        }
        
        fileToBeDeleted = String.format("Followings/FollowingsOfUser%s.csv", id);

        try {
            File followingsFile = new File(fileToBeDeleted);
            followingsFile.delete();//apaga a pasta de seguidos
        } catch (Exception e) {
        }
    }

    /**
     * Método que edita os dados do usuário logado no arquivo.
     * @param newName Novo nome.
     * @param newEmail Novo e-mail.
     * @param newPassword Nova senha.
     * @param newPassword2 Confirmação de nova senha.
     * @param compareName Booleano que determina se nome será alterado.
     * @param compareEmail Booleano que determina se E-mail será alterado.
     * @param comparePassword Booleano que determina se senha será alterada.
     * @return Retorna número de erro de comparação de contas, para determinar se algum dado é inválido.
     * @throws FileNotFoundException Tratamento de erro para arquivo inexistente.
     */
    default int editSelf(String newName, String newEmail, String newPassword, String newPassword2, boolean compareName, boolean compareEmail, boolean comparePassword) throws FileNotFoundException{//função comum
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
            
        String oldData = LogIn.user.getFormatData();
        String oldUserInfo[] = LogIn.user.getData();

        String newUserData = String.format("\n%s,%s,%s,%s,%s", oldUserInfo[0], newName, newEmail, newPassword, oldUserInfo[4]);
        ModifyUser.modUser(FileInfo.getRawData("Database/Banco.csv"), oldData, newUserData, "Database/Banco.csv");

        newUserData = String.format("\n%s,%s", newName, oldUserInfo[0]);
        updateFollowers(newUserData);

        String [] newUserInfo = {oldUserInfo[0], newName, newEmail, newPassword, oldUserInfo[4]};
        LogIn.defUser(newUserInfo);

        return 0;
    }

    /**
     * Cria uma nova playlist, sendo adicionada ao bando de dados das playlists.
     * @param playlistCSVFileList ArrayList de playlists na qual vai ser adicionada a nova playlist.
     * @param playlistName Nome da nova playlist.
     * @param userName Nome do usuário que criou a nova playlist.
     * @param visibility Visibilidade da nova playlist.
     * @return Retorna 1 em casos inválidos, retorna 0 por padrão.
     */

    default int createPlaylist(List<List<String>> playlistCSVFileList, String playlistName, String userName, String visibility) {
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
    default int addMusicToPlaylist(List<List<String>> playlistCSVFileList, String playlistName, String musicID) {
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
    default void deletePlaylist(List<List<String>> playlistCSVFileList, String playlistName) {
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
    default int editPlaylist(List<List<String>> playlistCSVFileList, String oldPlaylistName, String newPlaylistName, String newVisibility) {
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

    default void removeMusicFromPlaylist(List<List<String>> playlistCSVFileList, String playlistName, String musicID){
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
     * Recebe uma música da Lista de músicas por meio do ID, incrementa a visualização dessa música.
     * @param musicID ID específico da música que foi visualizada e incrementada por 1 as visualizações.
     */

    default void viewCounter(String musicID){
        List<String[]> musicCSVFileList = MusicDatabase.getMusicCSVFile();

        for(String[] music : musicCSVFileList){
            if(music[0].equals(musicID)){
                int currentViews = Integer.parseInt(music[3]);
                currentViews++;
                music[3] = String.valueOf(currentViews);
                break;
            }
        }

        MusicDatabase.updateMusicCSVFile(musicCSVFileList);
    }
}    
