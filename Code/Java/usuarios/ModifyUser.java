import java.io.FileNotFoundException;

public class ModifyUser {
    public static void modUser(String fileContent, String oldContent, String newContet, String fileName) throws FileNotFoundException{
        fileContent = fileContent.replace(oldContent,newContet);
        RecordUser.replaceInFile(fileContent, fileName);
    }

    public static void deleteSelf() throws FileNotFoundException{
        modUser(FileInfo.getRawData("Banco.csv"), LogIn.user.getFormatData(), "", "Banco.csv"); 
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
        modUser(FileInfo.getRawData("Banco.csv"), oldData, newUserData, "Banco.csv");
    }
}
