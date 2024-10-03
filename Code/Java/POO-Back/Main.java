import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main{
    public static File arquivo = new File("Banco.csv");
    public static Scanner scan = new Scanner(System.in);
    public static UsuarioAdm usuarioAdm;
    public static UsuarioArtista usuarioArtista;
    public static UsuarioComum usuarioComum;
    public static String classeAtual = "";

    public static void main(String[] args) throws FileNotFoundException{
        InfoArquivo.infoArquivo(); //cria uma matriz pra manter as informações dos arquivos
        //RecordUsuario.escreverArq(string exemplo); //realiza o arquivamento de dados no arquivo do Banco em csv (lembrar de dividr com virgula)
        //LogIn.receberInput(); //realiza o login do usuario, verifica se condiz com o arquivo
        //SignUp.signUp();
        //verUsuarios();
        //ModificarUsuario.deleteSelf();//deleta sua propria conta
        ModificarUsuario.deleteAny();//deleta qualquer usuario (necessario adm)
        //ModificarUsuario.createAny();//cria contas de adm e artista (necessario adm)
        
        scan.close();
    }

    public static boolean defUsuario(String [] usuarioInfo){
        boolean success = false;

        switch (usuarioInfo[4]) {
            case "comum":
                usuarioComum = new UsuarioComum(usuarioInfo[0], usuarioInfo[1], usuarioInfo[2], usuarioInfo[3]);
                classeAtual = "usuarioComum";
                success = true;
                break;

            case "gerente":
                usuarioAdm = new UsuarioAdm(usuarioInfo[0], usuarioInfo[1], usuarioInfo[2], usuarioInfo[3]);
                classeAtual = "usuarioAdm";
                success = true;
                break;

            case "artista":
                usuarioArtista = new UsuarioArtista(usuarioInfo[0], usuarioInfo[1], usuarioInfo[2], usuarioInfo[3]);
                classeAtual = "usuarioArtista";
                success = true;
                break;

            default:
                //mensagem de erro nao conseguiu achar o cargo
                break;
        }

        return success; //retorna se conseguiu fazer o login
    }
}
    
