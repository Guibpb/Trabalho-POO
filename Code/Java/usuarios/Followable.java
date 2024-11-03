import java.io.FileNotFoundException;

interface Followable {
    boolean followUser(String userToBeFollowed, String idUserToBeFollowed) throws FileNotFoundException;
    boolean unfollowUser(String userToBeUnfollowed, String idUserToBeUnfollowed) throws FileNotFoundException;
    //SEGUIR PESSOAS METODOS
}
