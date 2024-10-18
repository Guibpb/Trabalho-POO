import java.io.FileNotFoundException;
import java.util.ArrayList;

public class LogIn {
    public static User user;

    private static void logIn(String userInput, String passwordInput) throws FileNotFoundException{ 
        int i = 0;
        String userInfo[];

        ArrayList<String[]> matrixInfo = FileInfo.getMatrixInfo();
        int size = FileInfo.getMatrixSize();
        
        while(i < size){ //loop para iterar por todos os usuarios
            userInfo = matrixInfo.get(i);
            String currentUser = userInfo[1];
            String currentEmail = userInfo[2];
            String currentPassword = userInfo[3];

            if((userInput.equals(currentUser) || userInput.equals(currentEmail)) && passwordInput.equals(currentPassword)){
                defUser(userInfo); //cria o objeto com os dados do user na Main
                //retorna true ou false se conseguir fazer o login
                  
                break; //termina o loop quando o usuario certo é encontrado
            }
                
            i++;
        }
    }

    public static boolean defUser(String [] userInfo){
        boolean success = false;

        switch (userInfo[4]) {
            case "comum" -> {
                user = new PublicUser(userInfo[0], userInfo[1], userInfo[2], userInfo[3]);
                success = true;
            }

            case "gerente" -> {
                user = new AdmUser(userInfo[0], userInfo[1], userInfo[2], userInfo[3]);
                success = true;
            }

            case "artista" -> {
                user = new ArtistUser(userInfo[0], userInfo[1], userInfo[2], userInfo[3]);
                success = true;
            }

            default -> {//mensagem de erro nao conseguiu achar o cargo
            }
        }

        return success; //retorna se conseguiu fazer o login
    }
}
