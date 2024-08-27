import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Admin {
    private static String usuario;
    private static String senha;

    private static void setUsuario(String inputUsuario, String inputSenha) throws FileNotFoundException{ 
        File arquivo = new File("/home/guilherme/Code/Java/POO-Back/teste.csv");

        try(Scanner scanArquivo = new Scanner(arquivo)){//boas praticas pra fechar o scanner
            boolean usuarioValido = false;
            
            while(scanArquivo.hasNextLine()){ //loop para separar o usuario e a senha do csv
                String usuarioAtual = scanArquivo.nextLine();
                int index = usuarioAtual.indexOf(',');
                String senhaAtual = usuarioAtual.substring(index + 1);
                usuarioAtual = usuarioAtual.substring(0, index);

                if(inputUsuario.equals(usuarioAtual) && inputSenha.equals(senhaAtual)){
                    usuario = inputUsuario;
                    senha = inputSenha;
                    System.out.println(usuario + senha);
                    usuarioValido = true;
                }  
            }
            if(!usuarioValido)
                System.out.println("Erro! Usuário não reconhecido, por favor tente novamente.");
        }
    }

    private static void receberInput() throws FileNotFoundException {
        String inputUsuario;
        String inputSenha;
        
        try(Scanner scanf = new Scanner(System.in)){
            System.out.print("Insira seu usuário: ");
            inputUsuario = scanf.nextLine();

            System.out.print("Insira sua senha: ");
            inputSenha = scanf.nextLine();            
        }

        setUsuario(inputUsuario, inputSenha);
    }

    public static void main(String[] args) throws FileNotFoundException {
        receberInput();
    }
}
