/**
 * Classe que representa o Gerente do aplicativo.
 * Herda da classe abstrata User.
 * Implementa CommomFeatures por herança, AdmFeatures e ArtistFeatures diretamente.
 * É utilizada como objeto no usuário logado, se tal usuário for Gerente do aplicativo.
 */

public class AdmUser extends User implements AdmFeatures, ArtistFeatures{
    /**
     * Construtor.
     * @param id ID.
     * @param name Nome.
     * @param email E-mail.
     * @param password Senha.
     */ 
    public AdmUser(String id, String name, String email, String password){
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
