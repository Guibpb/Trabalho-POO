import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SignUp {
    private static String usuario;
    private static String email;
    private static String senha;

    public static void signUp() throws FileNotFoundException{
        try(Scanner scanf = new Scanner(System.in)){
            do{
                System.out.print("Insira seu nome de usuário: ");
                usuario = scanf.nextLine();
                
                System.out.print("Insira seu e-mail: ");
                email = scanf.nextLine();

                System.out.print("Insira sua senha: ");
                senha = scanf.nextLine();
            }while(compararUsuarios() == false);
        }

        String dados = String.format("\n%s,%s,%s", usuario, email, senha);
        RecordUsuario.escreverArq(dados);
    }

    private static boolean compararUsuarios() throws FileNotFoundException{
        File arquivo = new File("POO-Back/Banco.csv");
        boolean compararUsuarios = true;

        try(Scanner scanArquivo = new Scanner(arquivo)){
            while(scanArquivo.hasNextLine() && compararUsuarios){ //pega somente o nome e o email dos user
                String inputInfo = scanArquivo.nextLine();
                String usuarioInfo[] = inputInfo.split(",");

                if(usuarioInfo[0].equals(usuario)){
                   System.out.println("Esse usuário já existe.");
                    compararUsuarios = false;
                }
                
                if(usuarioInfo[1].equals(email)){
                    System.out.println("Esse e-mail já está cadastrado.");
                    compararUsuarios = false;
                }
                
                if(usuario.contains(",")){
                    System.out.println("Formato de usuário inválido");
                    compararUsuarios = false;
                }
                
                if(email.contains(",") || !email.contains("@") || !email.contains(".")){
                    System.out.println("Formato de e-mail inválido.");
                    compararUsuarios = false;
                }

                if(senha.contains(",")){
                    System.out.println("Formato de senha inválida.");
                    compararUsuarios = false;
                }
            }
        }
        return compararUsuarios;
    }
}

