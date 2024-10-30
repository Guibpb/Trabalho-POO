public interface Commandable {
    String ROLE = "gerente";
    boolean deleteAny();
    boolean createAny();
    boolean seeAllUsers();
    boolean seeAllArtists();
    boolean seeAllMusics();
    boolean seeAllPlaylists();

    //GERAR RELATORIOS ESTATISTICOS SOBRE DADOS DE USUARIOS
    
}
