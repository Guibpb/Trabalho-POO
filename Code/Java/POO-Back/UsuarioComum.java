public class UsuarioComum extends Usuario {
    private final String cargo = "comum";

    public UsuarioComum(String id, String nome, String email, String senha){
        super(id, nome, email, senha);
    }

    @Override
    public String getCargo(){
        return cargo;
    }

    @Override
    public String getDadosFormat(){
        String dados = super.getDadosFormat() + cargo;
        return dados;
    }

    @Override
    public String[] getDados(){
        String[] origem = super.getDados();
        String[] dados = {origem[0],origem[1],origem[2],origem[3],cargo};
        return dados;
    }
}
