import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SignUp {
    private static String usuario;
    private static String email;
    private static String senha;
    private static int idAtual;

    public static void signUp() throws FileNotFoundException{
        try(Scanner scanf = new Scanner(System.in)){
            String senha2;
            do{
                System.out.print("Insira seu nome de usuário: ");
                usuario = scanf.nextLine();
                
                System.out.print("Insira seu e-mail: ");
                email = scanf.nextLine();

                System.out.print("Insira sua senha: ");
                senha = scanf.nextLine();
                System.out.print("Confirme a senha: ");
                senha2 = scanf.nextLine();
            }while(compararUsuarios(senha2) == false);
        }

        idAtual++;
        String dados = String.format("\n%d,%s,%s,%s", idAtual,usuario, email, senha);
        RecordUsuario.escreverArq(dados);
    }

    private static boolean compararUsuarios(String senha2) throws FileNotFoundException{
        File arquivo = new File("Banco.csv");
        boolean compararUsuarios = true;

        try(Scanner scanArquivo = new Scanner(arquivo)){
            String usuarioInfo[] = null;
            while(scanArquivo.hasNextLine() && compararUsuarios){ //pega somente o nome e o email dos user
                String inputInfo = scanArquivo.nextLine();
                usuarioInfo = inputInfo.split(",");

                if(usuarioInfo[1].equals(usuario)){
                   System.out.println("Esse usuário já existe.");
                    compararUsuarios = false;
                }
                
                if(usuarioInfo[2].equals(email)){
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
                
                if(!senha.equals(senha2)){
                    System.out.println("Senhas incompatíveis.");
                    compararUsuarios = false;
                }
            }
            try {
                idAtual = Integer.parseInt(usuarioInfo[usuarioInfo.length - 4]);
            } catch (NumberFormatException e) {
                System.out.println("deu pau garaikkkk // numero invalido parsao");
            }
        }
        return compararUsuarios;
    }
}

