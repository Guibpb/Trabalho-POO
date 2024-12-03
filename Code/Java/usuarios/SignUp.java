import java.io.FileNotFoundException;
import java.util.ArrayList;

public class SignUp {
    private static int currentId = 0;

    public static void setId(int newId){ currentId = newId; }

    public static int signUp(String user, String email, String password, String password2, String role, boolean selfMade) throws FileNotFoundException {
        int errorNum = userCompare(user, email, password, password2);

        if(errorNum == 0){
            String id = Integer.toString(currentId);
            String userInfo[] = {id, user, email, password, role};

            if(selfMade)
                LogIn.defUser(userInfo);

            String data = String.format("\n%s,%s,%s,%s,%s,", id, user, email, password, role);
            RecordUser.writeInFile(data, "Database/Banco.csv");
        }else{
            //mensagem de erro
        }

        return errorNum;
    }

    public static int userCompare(String user, String email, String password, String password2) throws FileNotFoundException{
        int newId;
        ArrayList<String[]> matrixInfo;

        try {
            matrixInfo = FileInfo.getMatrixInfo("Database/Banco.csv");
        } catch (FileNotFoundException e) {
            matrixInfo = new ArrayList<>();
        }
        
        for(String userInfo[] : matrixInfo){
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
                newId = Integer.parseInt(userInfo[0]) + 1;
                SignUp.setId(newId);
            } catch (NumberFormatException e) {
                //mensagem de erro de formatação de inteiro
            }
        }

        return 0; //nenhuma restrição foi ativada
    }
}

