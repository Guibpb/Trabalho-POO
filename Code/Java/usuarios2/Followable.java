import java.io.FileNotFoundException;

interface Followable {
    boolean followUser(String userFollowing, String userToBeFollowed, String idUserToBeFollowed) throws FileNotFoundException;
    boolean unfollowUser(String userUnfollowing, String userToBeUnfollowed);
    //SEGUIR PESSOAS METODOS
}
