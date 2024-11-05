/**
 * Classe que representa o usuário dentro de um aplicativo.
 * @author Guilherme
 * @param id
 * @param name
 * @param email
 * @param password
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public abstract class User implements Followable{
    protected String id;
    protected String name;
    protected String email;
    protected String password;

    public User (String id, String name, String email, String password){
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    /**
     * Escreve a formatação do arquivo com as informações do Usuário.
     * @return String com dados formatados.
     */
    public String getFormatData(){
        String dados = String.format("\n%s,%s,%s,%s,", id, name, email, password);
        return dados;
    }

    /**
     * Insere os dados em um vetor de String.
     * @return Vetor de dados.
     */
    public String[] getData(){
        String data[] = {id,name,email,password};
        return data;
    }

    public String getId(){return this.id;}

    public String getName(){return this.name;}

    public String getEmail(){return this.email;}

    public String getPassword(){return this.password;}

    @Override
    public boolean followUser(String userToBeFollowed, String idUserToBeFollowed) throws FileNotFoundException{
        final String FOLLOWINGS_FILE_NAME = String.format("Followings/FollowingsOfUser\"%s\".csv", id);
        File followingsFile = new File(FOLLOWINGS_FILE_NAME);
        String formatData;

        if(followingsFile.exists())
            formatData = String.format("\n%s,%s",userToBeFollowed, idUserToBeFollowed);

        else
            formatData = String.format("Seguindo,Id\n%s,%s",userToBeFollowed, idUserToBeFollowed);    
        
        RecordUser.writeInFile(formatData, FOLLOWINGS_FILE_NAME);

        final String FOLLOWERS_FILE_NAME = String.format("Followers/FollowersOfUser\"%s\".csv", idUserToBeFollowed);
        File followersFile = new File(FOLLOWERS_FILE_NAME);

        if(followersFile.exists())
            formatData = String.format("\n%s,%s",name, id);

        else
            formatData = String.format("Seguidores,Id\n%s,%s",name, id); 
        
        RecordUser.writeInFile(formatData, FOLLOWERS_FILE_NAME);

        return false;
    }

    @Override
    public boolean unfollowUser(String userToBeUnfollowed, String idUserToBeUnfollowed) throws FileNotFoundException{
        final String FOLLOWINGS_FILE_NAME = String.format("Followings/FollowingsOfUser\"%s\".csv", id);

        String formatData;
        formatData = String.format("\n%s,%s",userToBeUnfollowed, idUserToBeUnfollowed); 
        ModifyUser.modUser(FileInfo.getRawData(FOLLOWINGS_FILE_NAME), formatData, "", FOLLOWINGS_FILE_NAME);

        final String FOLLOWERS_FILE_NAME = String.format("Followers/FollowersOfUser\"%s\".csv", idUserToBeUnfollowed);
        formatData = String.format("\n%s,%s",name, id);
        
        ModifyUser.modUser(FileInfo.getRawData(FOLLOWERS_FILE_NAME), formatData, "", FOLLOWERS_FILE_NAME);
        
        return false;
    }

    @Override
    public ArrayList<String> getAllFollowers(int identifier) throws FileNotFoundException{
        ArrayList <String> allFollowers = new ArrayList<>();

        final String FOLLOWERS_FILE_NAME = String.format("Followers/FollowersOfUser\"%s\".csv", id);
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

    @Override
    public ArrayList<String> getAllFollowings(int identifier) throws FileNotFoundException{
        ArrayList <String> allFollowings = new ArrayList<>();

        final String FOLLOWINGS_FILE_NAME = String.format("Followings/FollowingsOfUser\"%s\".csv", id);
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

    @Override
    public void updateFollowers(String newData) throws FileNotFoundException {
        String genericFileName;
        ArrayList<String> followingsName = getAllFollowings(1);
        String formatData = String.format("\n%s,%s",name, id);

        for(String idFollowings : followingsName){//atualiza o usuário de todas as pastas de seguidores
            genericFileName = String.format("Followers/FollowersOfUser\"%s\".csv", idFollowings);
            ModifyUser.modUser(FileInfo.getRawData(genericFileName), formatData, newData, genericFileName);
        }

        followingsName = getAllFollowers(1);

        for(String idFollowers : followingsName){//atualiza o usuário de todas as pastas de seguidos
            genericFileName = String.format("Followings/FollowingsOfUser\"%s\".csv", idFollowers);
            ModifyUser.modUser(FileInfo.getRawData(genericFileName), formatData, newData, genericFileName);
        }
    }

    @Override
    public void deleteSelf() throws FileNotFoundException{
        ModifyUser.modUser(FileInfo.getRawData("Banco.csv"), getFormatData(), "", "Banco.csv");
        updateFollowers(""); //apaga o usuario de todas as pastas

        String fileToBeDeleted = String.format("Followers/FollowersOfUser\"%s\".csv", id);
        File followersFile = new File(fileToBeDeleted);
        followersFile.delete();//apaga a pasta de seguidores

        fileToBeDeleted = String.format("Followings/FollowingsOfUser\"%s\".csv", id);
        File followingsFile = new File(fileToBeDeleted);
        followingsFile.delete();//apaga a pasta de seguidos

        System.exit(0);
    }
    
    @Override
    public void editSelf(String newName, String newEmail, String newPassword, String newPassword2, String newRole) throws FileNotFoundException{//função comum
        int numErro = SignUp.userCompare(newName, newEmail, newPassword, newPassword2);
        if(numErro != 0){
            //mensagem de erro de acordo com o retorno
            return;
        }
            
        String oldData = LogIn.user.getFormatData();
        String oldUserInfo[] = LogIn.user.getData();
        String newId = oldUserInfo[0];

        String newUserData = String.format("\n%s,%s,%s,%s,%s", newId, newName, newEmail, newPassword, newRole);
        ModifyUser.modUser(FileInfo.getRawData("Banco.csv"), oldData, newUserData, "Banco.csv");

        newUserData = String.format("\n%s,%s", newName, newId);
        updateFollowers(newUserData);
    }
}
