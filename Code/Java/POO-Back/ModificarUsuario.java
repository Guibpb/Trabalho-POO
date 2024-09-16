import java.io.FileNotFoundException;
import java.util.Scanner;

public class ModificarUsuario {
    private static String contArquivo = "";

    private static void modUsuario(String contAntigo, String contNovo) throws FileNotFoundException{//feature de adm
        try(Scanner scanArquivo = new Scanner(Main.arquivo)){
            while(scanArquivo.hasNextLine()){
                contArquivo += scanArquivo.nextLine() + '\n';
            }
            contArquivo = contArquivo.replace(contAntigo, contNovo);
            RecordUsuario.substituirArq(contArquivo);
        }
    }

    public static void deleteSelf() throws FileNotFoundException{
        modUsuario(SignIn.getDados(), "");
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
        if(SignIn.getCargo().equals("gerente")){
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

    public static void createAny(){//advinha? função de adm.
        if(SignIn.getCargo().equals("gerente")){
            System.out.println();
        }else{
            System.out.println("Acesso negado. Necessário cargo de gerente.");
        }
    }
}
