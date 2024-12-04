import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * A classe LogIn fornece métodos para autenticação de usuários com base em um banco de dados.
 * @author Guilherme
 */

public class LogIn {
    public static User user;
    public static AdmUser admUser;
    public static ArtistUser artistUser;

    /**
     * Tenta fazer login com o usuário e senha fornecidos.
     * Utiliza um Array List de array de String como matriz para receber dados de todos os usuários,
     * itera nessa matriz para realizar o login com os dados.
     * 
     * @param userInput O nome de usuário ou email fornecido pelo usuário.
     * @param passwordInput A senha fornecida pelo usuário.
     * @return true se o login for bem-sucedido, false caso contrário.
     * @throws FileNotFoundException Se o arquivo do banco de dados não for encontrado.
     */

    public static boolean logIn(String userInput, String passwordInput) throws FileNotFoundException{ 
        ArrayList<String[]> matrixInfo = FileInfo.getMatrixInfo("Database/Banco.csv");
        
        for(String [] userInfo : matrixInfo){ //loop para iterar por todos os usuarios
            String currentUser = userInfo[1];
            String currentEmail = userInfo[2];
            String currentPassword = userInfo[3];

            if((userInput.equals(currentUser) || userInput.equals(currentEmail)) && passwordInput.equals(currentPassword)){
                return defUser(userInfo); //cria o objeto com os dados do user na Main
                //retorna true ou false se conseguir fazer o login
            }
        }
        return false;
    }

    /**
     * Define um objeto com o tipo de usuário com base nas informações fornecidas.
     * user é o objeto genérico, admUser e artistUser são objetos utilizados em 
     * métodos específicos.
     *
     * @param userInfo Um array de strings contendo as informações do usuário.
     * @return true se o usuário foi definido com sucesso, false caso contrário.
     */

    public static boolean defUser(String [] userInfo){
        boolean success = false;

        switch (userInfo[4]) {
            case "Comum" -> {
                user = new PublicUser(userInfo[0], userInfo[1], userInfo[2], userInfo[3]);
                success = true;
            }

            case "Gerente" -> {
                user = admUser = new AdmUser(userInfo[0], userInfo[1], userInfo[2], userInfo[3]);
                success = true;
            }

            case "Artista" -> {
                user = artistUser = new ArtistUser(userInfo[0], userInfo[1], userInfo[2], userInfo[3]);
                success = true;
            }

            default -> {//mensagem de erro nao conseguiu achar o cargo
            }
        }

        return success; //retorna se conseguiu fazer o login
    }
}
