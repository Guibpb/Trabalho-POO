import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class MusicOptions {

    /**
     * Adiciona uma nova música ao banco de dados de músicas e à pasta com os arquivos mp3 das músicas. O endereço da pasta com as
     * músicas do programa e o endereço no qual o arquivo da música a ser adicionada são obtidos pelo método Path, e a transferência
     * do arquivo entre as pastas é feita com Files.move(), na qual o arquivo da música é copiado para a pasta destino e removido
     * da pasta origem. O ID da ultima música adicionada é recebido para ser incrementado e indentificar a nova música, o que também
     * garante a unicidade do ID da música, por fim, a música é adicionada ao banco de dados com suas respectivas informações.
     * @param musicCSVFileList Lista de músicas a qual sera adicionada a nova música.
     * @param artistName Nome do artista que adicionou a nova música.
     * @param musicName Nome da nova música.
     * @param musicGenre Gênero musical da nova música.
     * @param musicFile Arquivo mp3 da nova música
     * @return Retorna 1 em casos inválidos, retorna 0 por padrão.
     */
    public static int uploadMusic(List<String[]> musicCSVFileList, String artistName, String musicName, String musicGenre, File musicFile){
        if(musicName.contains(",")){
            return 1;
        }
        for(String[] music : musicCSVFileList){
            if(music[2].equals(musicName))
                return 1;
        }

        try{
            Path musicDirectory = Path.of("Musics");
            if (!Files.exists(musicDirectory))
                Files.createDirectories(musicDirectory);

            Path origin = Path.of("TempMusics");
            Path musicFilePath = origin.resolve(musicFile.getName());

            Files.move(musicFilePath, musicDirectory.resolve(musicFilePath.getFileName()));


            int currentID = 1;
            if(!musicCSVFileList.getLast()[0].equals("MusicID"))
                currentID = Integer.parseInt(musicCSVFileList.getLast()[0]);

            String newID = String.valueOf(currentID + 1);
            String[] newMusic = {newID,artistName,musicName,"0",musicGenre, musicFile.getName()};
            musicCSVFileList.add(newMusic);
            MusicDatabase.updateMusicCSVFile(musicCSVFileList);

        } catch (FileAlreadyExistsException e) {
            return 1;
            //erro especifico pra caso a musica ja tenha sido registrada, fazer alguma outra coisa sla, ou nois bota q pode upar musicas iguais (complica mais ainda)?
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    /**
     * Remove uma música da lista de músicas, atualizando o banco de dados de músicas com a nova lista e remove a música da pasta
     * com os arquivos de música.
     * @param musicCSVFileList Lista de músicas a ser atualizada com a remoção de uma música.
     * @param musicFileName Name Arquivo de música a ser removido da pasta de músicas.
     */
    public static void deleteMusic(List<String[]> musicCSVFileList, String musicFileName){
        try {
            for (String[] music : musicCSVFileList) {
                if (music[5].equals(musicFileName)) {
                    Path origin = Path.of("Musics");
                    Path musicFilePath = origin.resolve(musicFileName);
                    Files.delete(musicFilePath);
                    musicCSVFileList.remove(music);
                    break;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        MusicDatabase.updateMusicCSVFile(musicCSVFileList);
    }

    /**
     * Edita uma música ja existente, atualiza o bando de dados de músicas com a música editada.
     * @param musicCSVFileList Lista de músicas que sera atualizada com uma música editada.
     * @param musicID ID da música que vai ser editada.
     * @param newName Novo nome da música que vai ser editada.
     * @param newGenre Novo gênero musical da música que vai ser editada.
     * @return Retorna 1 em casos inválidos, retorna 0 por padrão.
     */
    public static int editMusic(List<String[]> musicCSVFileList,String musicID, String newName, String newGenre){
        if(newName.contains(",")){
            return 1;
        }
        for (String[] music : musicCSVFileList) {
            if (music[0].equals(musicID)) {
                music[2] = newName; //altera o nome da musica
                music[4] = newGenre; //altera o genero da musica
                break; //termina o loop cedo, tenho q ver se isso n vai dar problema dps, na teoria n pq os IDs devem ser unicos
            } //se for alterar somente o genero ou somente o nome, passar como parametro o genero/nome ja existente
        }
        MusicDatabase.updateMusicCSVFile(musicCSVFileList);
        return 0;
    }

    public static void viewCounter(List<String[]> musicCSVFileList, String musicID){
        for(String[] music : musicCSVFileList){
            if(music[0].equals(musicID)){
                int currentViews = Integer.parseInt(music[3]);
                currentViews++;
                music[3] = String.valueOf(currentViews);
                break;
            }
        }
    }
}

