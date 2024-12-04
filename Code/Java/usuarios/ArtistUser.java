/**
 * Classe que representa os Artistas do aplicativo.
 * Herda da classe abstrata User.
 * Implementa CommomFeatures por herança e ArtistFeatures diretamente.
 * É utilizada como objeto no usuário logado, se tal usuário for Artista do aplicativo.
 */

public class ArtistUser extends User implements ArtistFeatures{
    private final String ROLE = "Artista";
    
    /**
     * Construtor.
     * @param id ID.
     * @param name Nome.
     * @param email E-mail.
     * @param password Senha.
     */
    public ArtistUser(String id, String name, String email, String password){
        super(id, name, email, password);
    }

    /**
     * Método herdado de User.
     * Retorna o cargo.
     */
    @Override
    public String getRole(){
        return ROLE;
    }

    /**
     * Método herdado de User.
     * Retorna os dados em formatação de String com vírgula para CSV.
     */
    @Override
    public String getFormatData(){
        String data = super.getFormatData() + ROLE;
        return data;
    }

    /**
     * Método herdado de User.
     * Retorna os dados por vetores.
     */
    @Override
    public String[] getData(){
        String[] origin = super.getData();
        String[] data = {origin[0],origin[1],origin[2],origin[3],ROLE};
        return data;
    }
}
