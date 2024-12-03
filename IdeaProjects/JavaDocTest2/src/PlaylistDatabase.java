import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PlaylistDatabase {

    /**
     * Le o banco de dados de playlists e passa para uma Lista de Lista de strings, pois o tamanho de cada playlist é
     * dinâmico, que sera ultilizada como intermediario
     * para editar as playlists no banco de dados. Caso o arquivo não exista, ele cria um novo arquivo com um
     * modelo que sera transcrito na primeira linha do arquivo.
     * @return Retorna uma Lista de Lista de strings com os dados do arquivo de playlists.
     */
    public static List<List<String>> getPlaylistCSVFile(){
        File playlistCSVFile = new File("mplaylists.csv");
        List<List<String>> playlistCSVFileList = new ArrayList<>();
        String row;
        try {
            if(playlistCSVFile.createNewFile()){
                PrintWriter printTemplate = new PrintWriter(playlistCSVFile);
                printTemplate.printf("PlaylistName,User,PlaylistVisibility,Musics...");
                printTemplate.close();

            }

            BufferedReader reader = new BufferedReader(new FileReader(playlistCSVFile));
            while((row = reader.readLine()) != null){
                List<String> rowList = new ArrayList<>();
                String[] values = row.split(",");
                for (String value : values) {
                    rowList.add(value);
                }
                playlistCSVFileList.add(rowList);
            }
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return playlistCSVFileList;
    }

    /**
     * Atualiza o banco de dados de playlists. Recebe uma Lista de playlists e escreve ela no arquivo das playlists,
     * separando cada playlist por linhas e cada dado de uma playlist por virgulas com o uso do PrintWriter, que no caso
     * permite escrever de forma formatada diretamente no arquivo.
     * @param playlistCSVFileList Lista de playlists que vai ser transcrita para o banco de dados de playlists.
     */
    public static void updatePlaylistCSVFile(List<List<String>> playlistCSVFileList){
        File playlistCSVFile = new File("mplaylists.csv");
        try {
            if(playlistCSVFile.createNewFile()){
                PrintWriter printTemplate = new PrintWriter(playlistCSVFile);
                printTemplate.printf("PlaylistName,User,PlaylistVisibility,Musics...");
                printTemplate.close();

            }
            PrintWriter writer = new PrintWriter(new FileWriter("mplaylists.csv"));
            for(List<String> data : playlistCSVFileList){
                for (int i = 0; i < data.size(); i++) {
                    writer.print(data.get(i));
                    if(i != data.size()-1)
                        writer.print(",");
                }
                writer.println();
            }
            writer.close();
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }
}