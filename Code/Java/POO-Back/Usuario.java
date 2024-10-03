public class Usuario {
    protected String id;
    protected String nome;
    protected String email;
    protected String senha;

    public Usuario (String id, String nome, String email, String senha){
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public String getId(){return this.id;}

    public String getNome(){return this.nome;}

    public String getEmail(){return this.email;}

    public String getSenha(){return this.senha;}
}
