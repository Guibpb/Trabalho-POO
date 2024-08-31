import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GerenciamentoUsuario {
    public static void main(String[] args) throws FileNotFoundException{
        //RecordUsuario.escreverArq(string exemplo); //realiza o arquivamento de dados no arquivo do Banco em csv (lembrar de dividr com virgula)
        //SignIn.receberInput(); //realiza o login do usuario, verifica se condiz com o arquivo
        SignUp.signUp();
        //verUsuarios();
    }

    private static void verUsuarios() throws FileNotFoundException{//feature de adm
        File arquivo = new File("POO-Back/Banco.csv");

        try(Scanner scanArquivo = new Scanner(arquivo)){
            String firstlineuseless = scanArquivo.nextLine();

            while(scanArquivo.hasNextLine()){ //pega somente o nome e o email dos user
                String inputInfo = scanArquivo.nextLine();
                String usuarioInfo[] = inputInfo.split(",");

                for(int i = 0; i < usuarioInfo.length; i += 3)
                    System.out.println("Nome de usuário: " + usuarioInfo[i] + "\nE-mail: " + usuarioInfo[i+1] + "\n");   
            }
        }
    }
}
    
