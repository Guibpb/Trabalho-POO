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
        RecordUser newFriend = new RecordUser();
        String formatData;

        if(followingsFile.exists())
            formatData = String.format("\n%s,%s,",userToBeFollowed, idUserToBeFollowed);

        else
            formatData = String.format("Seguindo,Id,\n%s,%s,",userToBeFollowed, idUserToBeFollowed);    
        
        newFriend.writeInFile(formatData, FOLLOWINGS_FILE_NAME);


        final String FOLLOWERS_FILE_NAME = String.format("Followers/FollowersOfUser\"%s\".csv", idUserToBeFollowed);
        File followersFile = new File(FOLLOWERS_FILE_NAME);

        if(followersFile.exists())
            formatData = String.format("\n%s,%s",name, id);

        else
            formatData = String.format("Seguidores,Id,\n%s,%s",name, id); 
        
        newFriend.writeInFile(formatData, FOLLOWERS_FILE_NAME);

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
}
