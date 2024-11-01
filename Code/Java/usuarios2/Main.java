import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main{
    public static File file = new File("Banco.csv");
    public static Scanner scan = new Scanner(System.in);
    

    public static void main(String[] args) throws FileNotFoundException{
        //ModifyUser.deleteAny("johnlennon"); // ta funcionando
        LogIn.logIn("gui", "123");
        ModifyUser.createAny("George Harrison", "George@beatles.com", "123", "123", "artista");
        scan.close();
    }
}
    
