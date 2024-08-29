import java.util.Scanner;

public class SignUp {
    public static void signUp() {
        String usuario;
        String email;
        String senha;

        try(Scanner scanf = new Scanner(System.in)){
            System.out.print("Insira seu nome de usuário: ");
            usuario = scanf.nextLine();

            System.out.print("Insira seu e-mail: ");
            email = scanf.nextLine();

            System.out.print("Insira sua senha: ");
            senha = scanf.nextLine();
        }

        String dados = String.format("\n%s,%s,%s", usuario, email, senha);
        RecordUsuario.escreverArq(dados);
    }
}
