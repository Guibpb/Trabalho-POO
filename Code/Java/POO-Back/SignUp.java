import java.io.FileNotFoundException;
import java.util.Scanner;

public class SignUp {
    private static int idAtual;

    public static void signUp() throws FileNotFoundException{
        String usuario;
        String email;
        String senha;
        String senha2;

        do{
            System.out.print("Insira seu nome de usuário: ");
            usuario = Main.scan.nextLine();
                
            System.out.print("Insira seu e-mail: ");
            email = Main.scan.nextLine();

            System.out.print("Insira sua senha: ");
            senha = Main.scan.nextLine();
            System.out.print("Confirme a senha: ");
            senha2 = Main.scan.nextLine();

        }while(!compararUsuarios(usuario, email, senha, senha2));

        System.out.println("Deseja se cadastrar como usuário comum ou artista?");
        
        if(Main.scan.nextLine().equals("artista")){
            String dados = String.format("\n%d,%s,%s,%s,artista", idAtual,usuario, email, senha);
            RecordUsuario.escreverArq(dados);
        }else{//arrumar validações
            String dados = String.format("\n%d,%s,%s,%s,comum", idAtual,usuario, email, senha);
            RecordUsuario.escreverArq(dados);
        } 
    }

    private static boolean compararUsuarios(String usuario, String email, String senha, String senha2) throws FileNotFoundException{
        boolean compararUsuarios = true;

        try(Scanner scanArquivo = new Scanner(Main.arquivo);){
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
                    System.out.println("Formato de usuário inválido(caracter inapropriado).");
                    compararUsuarios = false;
                }
                
                if(email.contains(",") || !email.contains("@") || !email.contains(".")){
                    System.out.println("Formato de e-mail inválido.");
                    compararUsuarios = false;
                }

                if(senha.contains(",")){
                    System.out.println("Formato de senha inválida(caracter inapropriado).");
                    compararUsuarios = false;
                }
                
                if(!senha.equals(senha2)){
                    System.out.println("Senhas incompatíveis.");
                    compararUsuarios = false;
                }
            }
            try {
                idAtual = Integer.parseInt(usuarioInfo[0]) + 1;
            } catch (NumberFormatException e) {
                //System.out.println("deu pau garaikkkk // numero invalido parsao");
            }
        }
        return compararUsuarios;
    }
}

