import java.io.FileNotFoundException;
import java.util.Scanner;

public class SignIn {
    private static String id;
    private static String usuario;
    private static String email;
    private static String senha;
    private static String cargo;

    public static String getDados(){
        String dados = String.format("\n%s,%s,%s,%s,%s", id, usuario, email, senha, cargo);
        return dados;
    }

    public static String getUsuario(){
        return usuario;
    }

    public static String getCargo(){
        return cargo;
    }
    
    public static String getEmail(){
        return email;
    }
    
    public static String getSenha(){
        return senha;
    }

    private static void setUsuario(String usuarioInput, String senhaInput) throws FileNotFoundException{ 
        try(Scanner scanArquivo = new Scanner(Main.arquivo)){//boas praticas pra fechar o scanner
            boolean usuarioValido = false;
            boolean run = true;
            
            while(run && scanArquivo.hasNextLine()){ //loop para separar o usuario e a senha do csv
                String inputInfo = scanArquivo.nextLine();
                String usuarioInfo[] = inputInfo.split(",");
                String usuarioAtual = usuarioInfo[1];
                String emailAtual = usuarioInfo[2];
                String senhaAtual = usuarioInfo[3];

                if((usuarioInput.equals(usuarioAtual) || usuarioInput.equals(emailAtual)) && senhaInput.equals(senhaAtual)){
                    id = usuarioInfo[0];
                    usuario = usuarioAtual;
                    email = emailAtual;
                    senha = senhaAtual;
                    cargo = usuarioInfo[4];
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
        
        System.out.print("Insira seu usuário ou seu e-mail: ");
        usuarioInput = Main.scan.nextLine();

        System.out.print("Insira sua senha: ");
        senhaInput = Main.scan.nextLine();            

        setUsuario(usuarioInput, senhaInput);
    }
}
