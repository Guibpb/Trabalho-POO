import java.io.FileNotFoundException;
import java.util.ArrayList;

public class LogIn {
    public static User user;
    public static AdmUser admUser;
    public static ArtistUser artistUser;

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
