import java.io.FileNotFoundException;
import java.util.ArrayList;

public class ModifyUser {

    private static void modUser(String oldContent, String newContet) throws FileNotFoundException{
        String fileContent = FileInfo.getRawData();
        fileContent = fileContent.replace(oldContent,newContet);

        RecordUser.replaceInFile(fileContent);
    }

    public static void deleteSelf() throws FileNotFoundException{
        modUser(LogIn.user.getFormatData(), ""); 
        /*apaga os data no arquivo substituindo por uma String vazia */
    }
    
    public static void editSelf(String newName, String newEmail, String newPassword, String newPassword2, String newRole) throws FileNotFoundException{//função comum
        int numErro = SignUp.userCompare(newName, newEmail, newPassword, newPassword2);
        if(numErro != 0){
            //mensagem de erro de acordo com o retorno
            return;
        }
            
        String oldData = LogIn.user.getFormatData();
        String oldUserInfo[] = LogIn.user.getData();
        String newId = oldUserInfo[0];

        String newUserData = String.format("\n%s,%s,%s,%s,%s", newId, newName, newEmail, newPassword, newRole);
        modUser(oldData, newUserData);
    }

    public static boolean deleteAny(String userToBeDeleted) throws FileNotFoundException{ //função de ADM
        if(LogIn.user.getRole().equals("gerente")){
            ArrayList<String[]> matrixInfo = FileInfo.getMatrixInfo(); 
            int size = FileInfo.getMatrixSize();
            
            for(int i = 0; i < size; i++){
                String userInfo[] = matrixInfo.get(i);

                if(userInfo[1].equals(userToBeDeleted)){
                    String data = String.format("\n%s,%s,%s,%s,%s", userInfo[0], userInfo[1], userInfo[2], userInfo[3], userInfo[4]);
                    modUser(data, "");
                    return true;
                }
            }
        }

        return false; //mensagem de erro
    }

    public static boolean createAny(String name, String email, String password, String password2, String role) throws FileNotFoundException{
        if(LogIn.user.getRole().equals("gerente")){
            SignUp newUser = new SignUp();
            newUser.signUp(name, email, password, password2, role);
            return true;
        }

        return false; //mensagem de erro de permissão
    }
}
