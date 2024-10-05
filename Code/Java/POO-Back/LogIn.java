import java.io.FileNotFoundException;
import java.util.ArrayList;

public class LogIn {
    public static Usuario usuario;

    private static void logIn(String usuarioInput, String senhaInput) throws FileNotFoundException{ 
        int i = 0;
        int tamanho = InfoArquivo.getMatrixSize();
        String usuarioInfo[];

        ArrayList<String[]> matrixInfo = InfoArquivo.getMatrixInfo();
        
        while(i < tamanho){ //loop para iterar por todos os usuarios
            usuarioInfo = matrixInfo.get(i);
            String usuarioAtual = usuarioInfo[1];
            String emailAtual = usuarioInfo[2];
            String senhaAtual = usuarioInfo[3];

            if((usuarioInput.equals(usuarioAtual) || usuarioInput.equals(emailAtual)) && senhaInput.equals(senhaAtual)){
                defUsuario(usuarioInfo); //cria o objeto com os dados do user na Main
                //retorna true ou false se conseguir fazer o login
                  
                break; //termina o loop quando o usuario certo é encontrado
            }
                
            i++;
        }
    }

    public static boolean defUsuario(String [] usuarioInfo){
        boolean success = false;

        switch (usuarioInfo[4]) {
            case "comum" -> {
                usuario = new UsuarioComum(usuarioInfo[0], usuarioInfo[1], usuarioInfo[2], usuarioInfo[3]);
                success = true;
            }

            case "gerente" -> {
                usuario = new UsuarioAdm(usuarioInfo[0], usuarioInfo[1], usuarioInfo[2], usuarioInfo[3]);
                success = true;
            }

            case "artista" -> {
                usuario = new UsuarioArtista(usuarioInfo[0], usuarioInfo[1], usuarioInfo[2], usuarioInfo[3]);
                success = true;
            }

            default -> {//mensagem de erro nao conseguiu achar o cargo
            }
        }

        return success; //retorna se conseguiu fazer o login
    }
}
