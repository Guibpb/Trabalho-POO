import java.io.FileNotFoundException;
import java.util.ArrayList;

public class SignUp {
    private static int currentId = 0;

    public static void setId(int newId){ currentId = newId; }

    public int signUp(String user, String email, String password, String role, String password2) throws FileNotFoundException {
        int errorNum = userCompare(user, email, password, password2);

        if(errorNum == 0){
            RecordUser newUser = new RecordUser();
            String data = String.format("\n%d,%s,%s,%s,%s", currentId, user, email, password, role);
            newUser.writeInFile(data);
        }else{
            //mensagem de erro
        }

        return errorNum;
    }

    private static int userCompare(String user, String email, String password, String password2) throws FileNotFoundException{
        int i = 0;
        ArrayList<String[]> matrixInfo = FileInfo.fileInfo();
        int size = FileInfo.getMatrixSize();

        while(i < size) {
            String userInfo[] = matrixInfo.get(i);

            if(userInfo[1].equals(user)){
                return 1; //Esse usuário já existe.
            }
                     
            if(userInfo[2].equals(email)){
                return 2; //Esse e-mail já está cadastrado.
            }
                     
            if(user.contains(",")){
                return 3; //Formato de usuário inválido(caracter inapropriado).
            }
                     
            if(email.contains(",") || !email.contains("@") || !email.contains(".")){
                return 4; //Formato de e-mail inválido
            }
     
            if(password.contains(",")){
                return 5; //Formato de password inválida(caracter inapropriado)
            }
                     
            if(!password.equals(password2)){
                return 6; //passwords incompatíveis
            }

            try {
                int newId = Integer.parseInt(userInfo[0]) + 1;
                SignUp.setId(newId);
            } catch (NumberFormatException e) {
                //mensagem de erro de formatação de inteiro
            }

            i++;
        }

        return 0; //nenhuma restrição foi ativada
    }
}

