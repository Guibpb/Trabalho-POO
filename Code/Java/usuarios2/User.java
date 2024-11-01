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

public class User implements Followable{
    protected String id;
    protected String name;
    protected String email;
    protected String password;
    private final String FILE_NAME = String.format("User%sFriends.csv", id);
    public File userFile = new File(FILE_NAME);

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

    public String getRole(){return "";}

    public boolean followUser(String userFollowing, String userToBeFollowed, String idUserToBeFollowed) throws FileNotFoundException{
        RecordUser newFriend = new RecordUser();
        String formatData = String.format("\n%s,%s,",userToBeFollowed, idUserToBeFollowed);
        newFriend.writeInFile(formatData, FILE_NAME);
        return false;
    }

    public boolean unfollowUser(String userUnfollowing, String userToBeUnfollowed){
        //;
        return false;
    }
}
