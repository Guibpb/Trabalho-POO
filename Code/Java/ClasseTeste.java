import java.util.Scanner;

public class ClasseTeste {
    public static void printa(String Parametro){
        String mensagem = String.format("Code in %s!", Parametro);
        System.out.println(mensagem);
    }

    public static String escaneia(){
        try(Scanner scanf = new Scanner(System.in)){
            return scanf.nextLine();
        }
    }

    public static void main(String[] args){
        System.out.println("Pick a Coding Language: ");
        String lingua = escaneia();
        printa(lingua);
    }
}
