import java.io.FileNotFoundException;

public class ModifyUser {
    public static void modUser(String fileContent, String oldContent, String newContet, String fileName) throws FileNotFoundException{
        fileContent = fileContent.replace(oldContent,newContet);
        RecordUser.replaceInFile(fileContent, fileName);
    }
}
