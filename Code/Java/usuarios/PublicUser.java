public class PublicUser extends User {
    private final String ROLE = "Comum";

    public PublicUser(String id, String name, String email, String password, String pfPicture){
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
}
