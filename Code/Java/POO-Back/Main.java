import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main{
    public static File arquivo = new File("Banco.csv");
    public static Scanner scan = new Scanner(System.in);
    public static void main(String[] args) throws FileNotFoundException{
        //RecordUsuario.escreverArq(string exemplo); //realiza o arquivamento de dados no arquivo do Banco em csv (lembrar de dividr com virgula)
        SignIn.receberInput(); //realiza o login do usuario, verifica se condiz com o arquivo
        //SignUp.signUp();
        //verUsuarios();
        //ModificarUsuario.deleteSelf();//deleta sua propria conta
        ModificarUsuario.deleteAny();//deleta qualquer usuario (necessario adm)
        //ModificarUsuario.createAny();//cria contas de adm e artista (necessario adm)
        scan.close();
    }
}
    
