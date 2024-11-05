import java.io.FileNotFoundException;
import java.util.ArrayList;

interface Followable {
    boolean followUser(String userToBeFollowed, String idUserToBeFollowed) throws FileNotFoundException;
    boolean unfollowUser(String userToBeUnfollowed, String idUserToBeUnfollowed) throws FileNotFoundException;
    void deleteSelf() throws FileNotFoundException;
    int editSelf(String newName, String newEmail, String newPassword, String newPassword2, boolean compareName, boolean compareEmail, boolean comparePassword) throws FileNotFoundException;
    ArrayList<String> getAllFollowers(int identifier) throws FileNotFoundException;
    ArrayList<String> getAllFollowings(int identifier) throws FileNotFoundException;
    void updateFollowers(String newData) throws FileNotFoundException;
}
