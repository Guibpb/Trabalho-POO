import java.io.FileNotFoundException;
import java.util.Scanner;

public class ModificarUsuario {

    private static void modUsuario(String contAnt, String contNovo) throws FileNotFoundException{
        /*contAnt representa os dados antigos e 
         * contNovo são os que substituirão eles*/

        String contArquivo = InfoArquivo.getDadosBrutos();
        contArquivo = contArquivo.replace(contAnt,contNovo);

        RecordUsuario.substituirArq(contArquivo);
    }

    public static void deleteSelf() throws FileNotFoundException{
        modUsuario(LogIn.getDadosFormat(), ""); 
        /*apaga os dados no arquivo substituindo por uma String vazia */
    }
    
    public static void editSelf() throws FileNotFoundException{//função comum
        String dados = LogIn.getDadosFormat();
        String usuarioInfo[] = LogIn.getDados();
        System.out.println("Qual dado você desejaria mudar? (somente usuario/senha)");
        String input = Main.scan.nextLine();

        switch (input){
            case "usuario":

            case "senha":

        }
    }

    private static void verUsuarios() throws FileNotFoundException{//feature de adm
        try(Scanner scanArquivo = new Scanner(Main.arquivo)){
            @SuppressWarnings("unused")
            String firstlineuseless = scanArquivo.nextLine();

            while(scanArquivo.hasNextLine()){ //pega somente o nome e o email dos user
                String inputInfo = scanArquivo.nextLine();
                String usuarioInfo[] = inputInfo.split(",");

                
                System.out.println("Nome de usuário: " + usuarioInfo[1] + "\nE-mail: " + usuarioInfo[2] + "\nCargo: " + usuarioInfo[4] + "\n");   
            }
        }
    }

    public static void deleteAny() throws FileNotFoundException{ //função de ADM
        if(LogIn.getCargo().equals("gerente")){
            System.out.print("Deseja ver a lista de usuários? (y/n): ");
            if(Main.scan.nextLine().equals("y"))
                verUsuarios();

            System.out.print("Insira o usuário a ser deletado: ");
            String input = Main.scan.nextLine();      
            
            try(Scanner scanArquivo = new Scanner(Main.arquivo)){
                String usuarioInfo[];
                boolean run = true;

                while(scanArquivo.hasNextLine() && run){
                    String inputInfo = scanArquivo.nextLine();
                    usuarioInfo = inputInfo.split(",");

                    if(usuarioInfo[1].equals(input)){
                        run = false;
                        String dados = String.format("\n%s,%s,%s,%s,%s", usuarioInfo[0], usuarioInfo[1], usuarioInfo[2], usuarioInfo[3], usuarioInfo[4]);
                        modUsuario(dados, "");
                        System.out.println("Usuário " + input + " deletado com sucesso.");
                    }
                }
            }
        }else{
            System.out.println("Acesso negado. Necessário cargo de gerente.");
        }
    }

    /*public static void createAny(){//advinha? função de adm.
        if(LogIn.getCargo().equals("gerente")){
            System.out.println();
        }else{
            System.out.println("Acesso negado. Necessário cargo de gerente.");
        }
    }*/
}
