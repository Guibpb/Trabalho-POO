import java.io.FileNotFoundException;
import java.util.ArrayList;

public class ModifyUser {

    private static void modUser(String oldContent, String newContet) throws FileNotFoundException{
        /*oldContent representa os data antigos e 
         * newContet são os que substituirão eles*/

        String fileContent = FileInfo.getRawData();
        fileContent = fileContent.replace(oldContent,newContet);

        RecordUser.replaceInFile(fileContent);
    }

    public static void deleteSelf() throws FileNotFoundException{
        modUser(LogIn.user.getFormatData(), ""); 
        /*apaga os data no arquivo substituindo por uma String vazia */
    }
    
    /*public static void editSelf() throws FileNotFoundException{//função comum
        String data = LogIn.usuario.getdataFormat();
        String userInfo[] = LogIn.usuario.getdata();

        switch (input){
            case "usuario":

            case "senha":

        }
    }*/

    /*private static void verUsuarios() throws FileNotFoundException{//feature de adm
        try(Scanner scanArquivo = new Scanner(Main.arquivo)){
            @SuppressWarnings("unused")
            String firstlineuseless = scanArquivo.nextLine();

            while(scanArquivo.hasNextLine()){ //pega somente o nome e o email dos user
                String inputInfo = scanArquivo.nextLine();
                String userInfo[] = inputInfo.split(",");

                
                System.out.println("Nome de usuário: " + userInfo[1] + "\nE-mail: " + userInfo[2] + "\nCargo: " + userInfo[4] + "\n");   
            }
        }
    }*/

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
        
        else{
            return false;
            //mensagem de erro
        }

        return false; //deu errado por algum motivo
    }

    /*public static void createAny(){//advinha? função de adm.
        if(LogIn.getCargo().equals("gerente")){
            System.out.println();
        }else{
            System.out.println("Acesso negado. Necessário cargo de gerente.");
        }
    }*/
}
