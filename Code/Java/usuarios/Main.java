import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main{
    public static Scanner scan = new Scanner(System.in);
    
    public static void main(String[] args) throws FileNotFoundException{
        //ModifyUser.deleteAny("johnlennon"); // ta funcionando
        LogIn.logIn("mikhael", "000");
        LogIn.user.followUser("johnlennon", "7");
        scan.close();
    }
}
    
