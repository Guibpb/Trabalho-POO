import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Classe que representa a ação de registrar uma nova conta.
 * @author Guilherme
 */

public class SignUp {
    private static int currentId = 0;

    public static void setId(int newId){ currentId = newId; }

    /**Método realiza o registro de uma conta com dados válidos, 
     * escreve no arquivo e constrói o objeto específico da classe User.
     * <p>
     * Após a construção do objeto, utiliza um método específico dele para receber 
     * as informações formatadas em uma string específica para registrar no arquivo.
     * 
     * @author Guilherme
     * @param user
     * @param email
     * @param password
     * @param password2
     * @param role
     * @return Sucesso na ação.
     * @throws FileNotFoundException
     */

    public static int signUp(String user, String email, String password, String password2, String role, boolean selfMade) throws FileNotFoundException {
        int errorNum = userCompare(user, email, password, password2);

        if(errorNum == 0){
            String id = Integer.toString(currentId);
            String profilePicture = "Database/Default.png";
            String userInfo[] = {id, user, email, password, role};

            if(selfMade)
                LogIn.defUser(userInfo);

            String data = String.format("\n%s,%s,%s,%s,%s,%s", id, user, email, password, role, profilePicture);
            RecordUser.writeInFile(data, "Database/Banco.csvs");
        }else{
            //mensagem de erro
        }

        return errorNum;
    }

    /**Compara e valida as informações da conta a ser registrada.
     * @author Guilherme
     * @param user
     * @param email
     * @param password
     * @param password2
     * @return Número de erro específico.
     * @throws FileNotFoundException
     */

    public static int userCompare(String user, String email, String password, String password2) throws FileNotFoundException{
        int newId;
        ArrayList<String[]> matrixInfo;

        try {
            matrixInfo = FileInfo.getMatrixInfo("Database/Banco.csvs");
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

