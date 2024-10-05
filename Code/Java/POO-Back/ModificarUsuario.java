import java.io.FileNotFoundException;
import java.util.ArrayList;

public class ModificarUsuario {

    private static void modUsuario(String contAnt, String contNovo) throws FileNotFoundException{
        /*contAnt representa os dados antigos e 
         * contNovo são os que substituirão eles*/

        String contArquivo = InfoArquivo.getDadosBrutos();
        contArquivo = contArquivo.replace(contAnt,contNovo);

        RecordUsuario.substituirArq(contArquivo);
    }

    public static void deleteSelf() throws FileNotFoundException{
        modUsuario(LogIn.usuario.getDadosFormat(), ""); 
        /*apaga os dados no arquivo substituindo por uma String vazia */
    }
    
    /*public static void editSelf() throws FileNotFoundException{//função comum
        String dados = LogIn.usuario.getDadosFormat();
        String usuarioInfo[] = LogIn.usuario.getDados();

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
                String usuarioInfo[] = inputInfo.split(",");

                
                System.out.println("Nome de usuário: " + usuarioInfo[1] + "\nE-mail: " + usuarioInfo[2] + "\nCargo: " + usuarioInfo[4] + "\n");   
            }
        }
    }*/

    public static boolean deleteAny(String usuarioASerDeletado) throws FileNotFoundException{ //função de ADM
        if(LogIn.usuario.getCargo().equals("gerente")){
            int i = 0;
            int tamanho = InfoArquivo.getMatrixSize();
            ArrayList<String[]> matrixInfo = InfoArquivo.getMatrixInfo();  
            
            while(i < tamanho){
                String usuarioInfo[] = matrixInfo.get(i);

                if(usuarioInfo[1].equals(usuarioASerDeletado)){
                    String dados = String.format("\n%s,%s,%s,%s,%s", usuarioInfo[0], usuarioInfo[1], usuarioInfo[2], usuarioInfo[3], usuarioInfo[4]);
                    modUsuario(dados, "");
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
