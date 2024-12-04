import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main{
    public static Scanner scan = new Scanner(System.in);
    
    public static void main(String[] args) throws FileNotFoundException{
        //ModifyUser.deleteAny("johnlennon"); // ta funcionando
        LogIn.logIn("Paul", "456");
        //LogIn.user.followUser("johnlennon", "7");
        LogIn.artistUser.seeAllOwnMusics("Relatórios/");

        scan.close();
    }
}
    
