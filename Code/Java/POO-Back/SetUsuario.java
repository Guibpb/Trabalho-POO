import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SetUsuario {
    private static String usuario;
    private static String senha;

    public static String getSenha(){
        return senha;
    }

    public static String getUsuario(){
        return usuario;
    }

    private static void setUsuario(String usuarioInput, String senhaInput) throws FileNotFoundException{ 
        File arquivo = new File("/home/guilherme/Code/Java/POO-Back/teste.csv");

        try(Scanner scanArquivo = new Scanner(arquivo)){//boas praticas pra fechar o scanner
            boolean usuarioValido = false;
            
            while(scanArquivo.hasNextLine()){ //loop para separar o usuario e a senha do csv
                String usuarioAtual = scanArquivo.nextLine();
                int index = usuarioAtual.indexOf(',');
                String senhaAtual = usuarioAtual.substring(index + 1);
                usuarioAtual = usuarioAtual.substring(0, index);

                if(usuarioInput.equals(usuarioAtual) && senhaInput.equals(senhaAtual)){
                    usuario = usuarioInput;
                    senha = senhaInput;
                    System.out.println("Logado como: " + usuario);
                    usuarioValido = true;
                }  
            }
            if(!usuarioValido)
                System.out.println("Erro! Usuário não reconhecido, por favor tente novamente.");
        }
    }

    public static void receberInput() throws FileNotFoundException {
        String usuarioInput;
        String senhaInput;
        
        try(Scanner scanf = new Scanner(System.in)){
            System.out.print("Insira seu usuário: ");
            usuarioInput = scanf.nextLine();

            System.out.print("Insira sua senha: ");
            senhaInput = scanf.nextLine();            
        }

        setUsuario(usuarioInput, senhaInput);
    }
}
