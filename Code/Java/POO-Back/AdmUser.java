public class AdmUser extends User implements Commandable{
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

    public boolean deleteAny(){\\};
    public boolean createAny(){\\};
    public boolean seeAllUsers(){\\};
    public boolean seeAllArtists(){\\};
    public boolean seeAllMusics(){\\};
    public boolean seeAllPlaylists(){\\};
}
