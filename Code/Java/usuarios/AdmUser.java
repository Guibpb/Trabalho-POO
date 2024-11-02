public class AdmUser extends User implements Commandable, Addable{
    public AdmUser(String id, String name, String email, String password){
        super(id, name, email, password);
    }

    @Override
    public String getRole(){
        return ROLE;
    }

    @Override
    public String getFormatData(){
        String data = super.getFormatData() + ROLE;
        return data;
    }

    @Override
    public String[] getData(){
        String[] origin = super.getData();
        String[] data = {origin[0],origin[1],origin[2],origin[3],ROLE};
        return data;
    }

    public boolean deleteAny(){return false;}
    public boolean createAny(){return false;}
    public boolean seeAllUsers(){return false;}
    public boolean seeAllArtists(){return false;}
    public boolean seeAllMusics(){return false;}
    public boolean seeAllPlaylists(){return false;}
    public boolean generateTop10Playlist(String a){return false;}
    public boolean deleteMusic(){return false;}
    public boolean registerNewMusic(){return false;}
}
