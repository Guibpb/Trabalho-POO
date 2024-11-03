import java.io.FileNotFoundException;
import java.util.ArrayList;

public class LogIn {
    public static PublicUser user;
    public static AdmUser admUser;
    public static ArtistUser artistUser;

    public static boolean logIn(String userInput, String passwordInput) throws FileNotFoundException{ 
        int i = 0;
        String userInfo[];

        ArrayList<String[]> matrixInfo = FileInfo.getMatrixInfo("Banco.csv");
        int size = matrixInfo.size();
        
        while(i < size){ //loop para iterar por todos os usuarios
            userInfo = matrixInfo.get(i);
            String currentUser = userInfo[1];
            String currentEmail = userInfo[2];
            String currentPassword = userInfo[3];

            if((userInput.equals(currentUser) || userInput.equals(currentEmail)) && passwordInput.equals(currentPassword)){
                return defUser(userInfo); //cria o objeto com os dados do user na Main
                //retorna true ou false se conseguir fazer o login
            }
                
            i++;
        }
        return false;
    }

    public static boolean defUser(String [] userInfo){
        boolean success = false;

        switch (userInfo[4]) {
            case "comum" -> {
                user = new PublicUser(userInfo[0], userInfo[1], userInfo[2], userInfo[3]);
                success = true;
            }

            case "gerente" -> {
                admUser = new AdmUser(userInfo[0], userInfo[1], userInfo[2], userInfo[3]);
                success = true;
            }

            case "artista" -> {
                artistUser = new ArtistUser(userInfo[0], userInfo[1], userInfo[2], userInfo[3]);
                success = true;
            }

            default -> {//mensagem de erro nao conseguiu achar o cargo
            }
        }

        return success; //retorna se conseguiu fazer o login
    }
}
