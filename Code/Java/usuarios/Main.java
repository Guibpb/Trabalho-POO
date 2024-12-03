import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main{
    public static Scanner scan = new Scanner(System.in);
    
    public static void main(String[] args) throws FileNotFoundException{
        //ModifyUser.deleteAny("johnlennon"); // ta funcionando
        LogIn.logIn("gui", "123");
        //LogIn.user.followUser("johnlennon", "7");
        ArrayList<String[]> artistas = LogIn.admUser.seeAllArtists();
        ArrayList<String[]> pessoas = LogIn.admUser.seeAllUsers();

        for(String [] artista : artistas){
            for(String a : artista){
                System.out.println(a);
            }
        }

        for(String [] pessoa : pessoas){
            for(String p : pessoa){
                System.out.println(p);
            }
        }


        scan.close();
    }
}
    
