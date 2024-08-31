import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SignIn {
    private static String usuario;
    private static String email;
    private static String senha;

    public static String getUsuario(){
        return usuario;
    }
    
    public static String getEmail(){
        return email;
    }
    
    public static String getSenha(){
        return senha;
    }

    private static void setUsuario(String usuarioInput, String senhaInput) throws FileNotFoundException{ 
        File arquivo = new File("POO-Back/Banco.csv");

        try(Scanner scanArquivo = new Scanner(arquivo)){//boas praticas pra fechar o scanner
            boolean usuarioValido = false;
            boolean run = true;
            
            while(run && scanArquivo.hasNextLine()){ //loop para separar o usuario e a senha do csv
                String inputInfo = scanArquivo.nextLine();
                String usuarioInfo[] = inputInfo.split(",");
                String usuarioAtual = usuarioInfo[0];
                String emailAtual = usuarioInfo[1];
                String senhaAtual = usuarioInfo[2];

                if((usuarioInput.equals(usuarioAtual) || usuarioInput.equals(emailAtual)) && senhaInput.equals(senhaAtual)){
                    usuario = usuarioAtual;
                    email = emailAtual;
                    senha = senhaAtual;
                    System.out.println("Logado como: " + usuario + "\nSeu email é: " + email);
                    usuarioValido = true;
                    run = false;
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
            System.out.print("Insira seu usuário ou seu e-mail: ");
            usuarioInput = scanf.nextLine();

            System.out.print("Insira sua senha: ");
            senhaInput = scanf.nextLine();            
        }

        setUsuario(usuarioInput, senhaInput);
    }
}
