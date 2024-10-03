public class UsuarioArtista extends Usuario{
    private final String cargo = "artista";
    
    public UsuarioArtista(String id, String nome, String email, String senha){
        super(id, nome, email, senha);
    }

    public String getDadosFormat(){
        String dados = String.format("\n%s,%s,%s,%s,%s", id, nome, email, senha, cargo);
        return dados;
    }

    public String[] getDados(){
        String dados[] = {id,nome,email,senha,cargo};
        return dados;
    }
}
