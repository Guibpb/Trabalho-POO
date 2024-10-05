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

    public String getDadosFormat(){
        String dados = String.format("\n%s,%s,%s,%s", id, nome, email, senha);
        return dados;
    }

    public String[] getDados(){
        String dados[] = {id,nome,email,senha};
        return dados;
    }

    public String getId(){return this.id;}

    public String getNome(){return this.nome;}

    public String getEmail(){return this.email;}

    public String getSenha(){return this.senha;}

    public String getCargo(){return "";}
}
