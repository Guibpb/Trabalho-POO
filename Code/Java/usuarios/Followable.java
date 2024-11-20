import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

interface Followable {
    default boolean followUser(String userToBeFollowed, String idUserToBeFollowed) throws FileNotFoundException{
        String id = LogIn.user.getId();
        String name = LogIn.user.getName();

        final String FOLLOWINGS_FILE_NAME = String.format("Followings/FollowingsOfUser%s.csv", id);
        File followingsFile = new File(FOLLOWINGS_FILE_NAME);
        String formatData;

        if(followingsFile.exists())
            formatData = String.format("\n%s,%s",userToBeFollowed, idUserToBeFollowed);

        else
            formatData = String.format("Seguindo,Id\n%s,%s",userToBeFollowed, idUserToBeFollowed);    
        
        RecordUser.writeInFile(formatData, FOLLOWINGS_FILE_NAME);

        final String FOLLOWERS_FILE_NAME = String.format("Followers/FollowersOfUser%s.csv", idUserToBeFollowed);
        File followersFile = new File(FOLLOWERS_FILE_NAME);

        if(followersFile.exists())
            formatData = String.format("\n%s,%s",name, id);

        else
            formatData = String.format("Seguidores,Id\n%s,%s",name, id); 
        
        RecordUser.writeInFile(formatData, FOLLOWERS_FILE_NAME);

        return false;
    }

    default boolean unfollowUser(String userToBeUnfollowed, String idUserToBeUnfollowed) throws FileNotFoundException{
        String id = LogIn.user.getId();
        String name = LogIn.user.getName();

        final String FOLLOWINGS_FILE_NAME = String.format("Followings/FollowingsOfUser%s.csv", id);

        String formatData;
        formatData = String.format("\n%s,%s",userToBeUnfollowed, idUserToBeUnfollowed); 
        ModifyUser.modUser(FileInfo.getRawData(FOLLOWINGS_FILE_NAME), formatData, "", FOLLOWINGS_FILE_NAME);

        final String FOLLOWERS_FILE_NAME = String.format("Followers/FollowersOfUser%s.csv", idUserToBeUnfollowed);
        formatData = String.format("\n%s,%s",name, id);

        System.out.println(FileInfo.getRawData(FOLLOWERS_FILE_NAME));
        
        ModifyUser.modUser(FileInfo.getRawData(FOLLOWERS_FILE_NAME), formatData, "", FOLLOWERS_FILE_NAME);

        return false;
    }

    
    default ArrayList<String> getAllFollowers(int identifier) throws FileNotFoundException{
        String id = LogIn.user.getId();

        ArrayList <String> allFollowers = new ArrayList<>();

        final String FOLLOWERS_FILE_NAME = String.format("Followers/FollowersOfUser%s.csv", id);
        ArrayList <String[]> userInfo;

        try {
            userInfo = FileInfo.getMatrixInfo(FOLLOWERS_FILE_NAME);
        } catch (FileNotFoundException e) {
            return allFollowers;  //retorna vazio
        }
        
        for(String [] names : userInfo){
            allFollowers.add(names[identifier]);
        }
        
        return allFollowers;
    }

    default ArrayList<String> getAllFollowings(int identifier) throws FileNotFoundException{
        String id = LogIn.user.getId();
        
        ArrayList <String> allFollowings = new ArrayList<>();

        final String FOLLOWINGS_FILE_NAME = String.format("Followings/FollowingsOfUser%s.csv", id);
        ArrayList <String[]> userInfo;
        try {
            userInfo = FileInfo.getMatrixInfo(FOLLOWINGS_FILE_NAME);
        } catch (FileNotFoundException e) {
            return allFollowings; //retorna vazio
        }

        for(String [] names : userInfo){
            allFollowings.add(names[identifier]);
        }
        
        return allFollowings;
    }

    default void updateFollowers(String newData) throws FileNotFoundException {
        String id = LogIn.user.getId();
        String name = LogIn.user.getName();

        String genericFileName;
        ArrayList<String> followingsName = getAllFollowings(1);
        String formatData = String.format("\n%s,%s",name, id);

        for(String idFollowings : followingsName){//atualiza o usuário de todas as pastas de seguidores
            genericFileName = String.format("Followers/FollowersOfUser%s.csv", idFollowings);
            ModifyUser.modUser(FileInfo.getRawData(genericFileName), formatData, newData, genericFileName);
        }

        followingsName = getAllFollowers(1);

        for(String idFollowers : followingsName){//atualiza o usuário de todas as pastas de seguidos
            genericFileName = String.format("Followings/FollowingsOfUser%s.csv", idFollowers);
            ModifyUser.modUser(FileInfo.getRawData(genericFileName), formatData, newData, genericFileName);
        }
    }

    default void deleteSelf() throws FileNotFoundException{
        String id = LogIn.user.getId();

        ModifyUser.modUser(FileInfo.getRawData("Database/Banco.csv"), LogIn.user.getFormatData(), "", "Database/Banco.csv");
        updateFollowers(""); //apaga o usuario de todas as pastas

        String fileToBeDeleted = String.format("Followers/FollowersOfUser%s.csv", id);
        File followersFile = new File(fileToBeDeleted);
        followersFile.delete();//apaga a pasta de seguidores

        fileToBeDeleted = String.format("Followings/FollowingsOfUser%s.csv", id);
        File followingsFile = new File(fileToBeDeleted);
        followingsFile.delete();//apaga a pasta de seguidos
    }

    default int editSelf(String newName, String newEmail, String newPassword, String newPassword2, String newPicture, boolean compareName, boolean compareEmail, boolean comparePassword) throws FileNotFoundException{//função comum
        String tempName = newName;
        String tempEmail = newEmail;
        String tempPassword = newPassword;
        String tempPassword2 = newPassword2;
        
        if(!compareName)
            tempName = "";
        if(!compareEmail)
            tempEmail = "@.";
        if(!comparePassword)
            tempPassword = tempPassword2 = "";
        
        int numErro = SignUp.userCompare(tempName, tempEmail, tempPassword, tempPassword2);

        if(numErro != 0){
            //mensagem de erro de acordo com o retorno
            return numErro;
        }
            
        String oldData = LogIn.user.getFormatData();
        String oldUserInfo[] = LogIn.user.getData();

        String newUserData = String.format("\n%s,%s,%s,%s,%s,%s", oldUserInfo[0], newName, newEmail, newPassword, oldUserInfo[4], newPicture);
        ModifyUser.modUser(FileInfo.getRawData("Database/Banco.csv"), oldData, newUserData, "Database/Banco.csv");

        newUserData = String.format("\n%s,%s", newName, oldUserInfo[0]);
        updateFollowers(newUserData);

        String [] newUserInfo = {oldUserInfo[0], newName, newEmail, newPassword, oldUserInfo[4], newPicture};
        LogIn.defUser(newUserInfo);

        return 0;
    }
}    
