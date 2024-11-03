import java.io.FileNotFoundException;

public interface Commandable {
    String ROLE = "gerente";
    boolean deleteAny(String userToBeDeleted)throws FileNotFoundException;
    int createAny(String name, String email, String password, String password2, String role)throws FileNotFoundException;
    boolean seeAllUsers();
    boolean seeAllArtists();
    boolean seeAllMusics();
    boolean seeAllPlaylists();
    boolean generateTop10Playlist(String a);
    //GERAR RELATORIOS ESTATISTICOS SOBRE DADOS DE USUARIOS
}
