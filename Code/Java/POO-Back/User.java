public class User {
    protected String id;
    protected String name;
    protected String email;
    protected String password;

    public User (String id, String name, String email, String password){
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getFormatData(){
        String dados = String.format("\n%s,%s,%s,%s,", id, name, email, password);
        return dados;
    }

    public String[] getData(){
        String data[] = {id,name,email,password};
        return data;
    }

    public String getId(){return this.id;}

    public String getName(){return this.name;}

    public String getEmail(){return this.email;}

    public String getPassword(){return this.password;}

    public String getRole(){return "";}
}
