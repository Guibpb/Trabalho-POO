import java.io.FileNotFoundException;
import java.util.ArrayList;

public class AdmUser extends User implements Commandable, Customizable{
    public AdmUser(String id, String name, String email, String password, String pfPicture){
        super(id, name, email, password, pfPicture);
    }

    public String getRole(){
        return ROLE;
    }

    @Override
    public String getFormatData(){
        String data = super.getFormatData() + ROLE + "," + pfPicture;
        return data;
    }

    @Override
    public String[] getData(){
        String[] origin = super.getData();
        String[] data = {origin[0],origin[1],origin[2],origin[3],ROLE, pfPicture};
        return data;
    }

    @Override
    public boolean deleteAny(String userToBeDeleted) throws FileNotFoundException{ //função de ADM
        ArrayList<String[]> matrixInfo = FileInfo.getMatrixInfo("Database/Banco.csv"); 

        for(String userInfo[] : matrixInfo){
            if(userInfo[1].equals(userToBeDeleted)){
                String data = String.format("\n%s,%s,%s,%s,%s,%s", userInfo[0], userInfo[1], userInfo[2], userInfo[3], userInfo[4], userInfo[5]);
                ModifyUser.modUser(FileInfo.getRawData("Database/Banco.csv"),data, "", "Database/Banco.csv");
                return true; //alterar isso aqui pra remover os arquivos de seguidores tbm kkk

            }
        }

        return false; //mensagem de erro
    }

    @Override
    public int createAny(String name, String email, String password, String password2, String role) throws FileNotFoundException{
        return SignUp.signUp(name, email, password, password2, role, false);
    }

    public boolean seeAllUsers(){return false;}
    public boolean seeAllArtists(){return false;}
    public boolean seeAllMusics(){return false;}
    public boolean seeAllPlaylists(){return false;}


    
    public boolean generateTop10Playlist(String a){return false;}
    public boolean deleteMusic(){return false;}
    public boolean registerNewMusic(){return false;}
}
