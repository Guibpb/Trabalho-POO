import java.io.FileNotFoundException;
import java.util.ArrayList;

public class SignUp {
    private static int idAtual;

    public static void setId(int idNovo){
        idAtual = idNovo;
    }

    public int signUp(String usuario, String email, String senha, String tipoUsuario) throws FileNotFoundException {
        int numErro = compararUsuarios(usuario, email, senha, senha);

        if(numErro == 0){
            RecordUsuario novoUsuario = new RecordUsuario();
            String dados = String.format("\n%s,%s,%s,%s", usuario, email, senha, tipoUsuario);
            novoUsuario.escreverArq(dados);
        }else{
            //mensagem de erro
        }

        return numErro;
    }

    private static int compararUsuarios(String usuario, String email, String senha, String senha2) throws FileNotFoundException{
        int i = 0;
        int tamanho = InfoArquivo.getMatrixSize();
        ArrayList<String[]> matrixInfo = InfoArquivo.getMatrixInfo();

        while(i < tamanho) {
            String usuarioInfo[] = matrixInfo.get(i);

            if(usuarioInfo[1].equals(usuario)){
                return 1; //Esse usuário já existe.
            }
                     
            if(usuarioInfo[2].equals(email)){
                return 2; //Esse e-mail já está cadastrado.
            }
                     
            if(usuario.contains(",")){
                return 3; //Formato de usuário inválido(caracter inapropriado).
            }
                     
            if(email.contains(",") || !email.contains("@") || !email.contains(".")){
                return 4; //Formato de e-mail inválido
            }
     
            if(senha.contains(",")){
                return 5; //Formato de senha inválida(caracter inapropriado)
            }
                     
            if(!senha.equals(senha2)){
                return 6; //Senhas incompatíveis
            }

            try {
                int novoId = Integer.parseInt(usuarioInfo[0]) + 1;
                SignUp.setId(novoId);
            } catch (NumberFormatException e) {
                //mensagem de erro de formatação de inteiro
            }

            i++;
        }

        return 0; //nenhuma restrição foi ativada
    }
}

