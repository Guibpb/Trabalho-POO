public class ArtistUser extends User{
    private final String role = "artista";
    
    public ArtistUser(String id, String name, String email, String password){
        super(id, name, email, password);
    }

    @Override
    public String getRole(){
        return role;
    }

    @Override
    public String getFormatData(){
        String data = super.getFormatData() + role;
        return data;
    }

    @Override
    public String[] getData(){
        String[] origin = super.getData();
        String[] data = {origin[0],origin[1],origin[2],origin[3],role};
        return data;
    }
}
