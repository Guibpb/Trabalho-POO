import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;

/**
 * Interface que representa métodos exclusivos para artistas, como upar/editar/excluir músicas no aplicativo e gerar relatórios.
 * @author Mikhael
 * @author Guilherme
 */

interface ArtistFeatures {
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

    default int uploadMusic(List<String[]> musicCSVFileList, String artistName, String musicName, String musicGenre, File musicFile){
        if(musicName.contains(",")){
            return 1;
        }

        try{
            Path musicDirectory = Path.of("Musics");
            if (!Files.exists(musicDirectory))
                Files.createDirectories(musicDirectory);
            Path destinationDir = Path.of("Musics");
            Path destinationPath = destinationDir.resolve(musicFile.getName());
            Files.move(musicFile.toPath(), destinationPath, StandardCopyOption.REPLACE_EXISTING);

            int currentID = 1;
            if(!musicCSVFileList.getLast()[0].equals("MusicID"))
                currentID = Integer.parseInt(musicCSVFileList.getLast()[0]);
            //id armazenado como string, passando pra int aqui
            String newID = String.valueOf(currentID + 1);//soma +1 ao ultimo id registrado para termos um novo id
            String[] newMusic = {newID,artistName,musicName,"0",musicGenre, musicFile.getName()};
            musicCSVFileList.add(newMusic); //adiciona a nova musica a lista de strings
            MusicDatabase.updateMusicCSVFile(musicCSVFileList); //atualiza o arquivo csv com a nova musica

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

    default void deleteMusic(List<String[]> musicCSVFileList, String musicFileName){
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

    default int editMusic(List<String[]> musicCSVFileList,String musicID, String newName, String newGenre){
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

    /**
     * Gera um relatório das músicas do artista logado em forma de arquivo txt.
     * @param FILE_PATH Caminho onde o arquivo será criado.
     * @throws FileNotFoundException Tratamento de erro de arquivo inexistente.
     */

    default void seeAllOwnMusics(String FILE_PATH)throws FileNotFoundException{
        String name = LogIn.artistUser.getName();
        List<String[]> musics;
        musics = MusicDatabase.getMusicCSVFile();
        musics.remove(0);
        String formatData = String.format("Relatório Estatístico de %s: Músicas\n\n", name);

        for(String[] musicInfo : musics){
            if(musicInfo[1].equals(name)){
                String info = String.format("Id de Música: %s\nNome da Música: %s\nVizualizações: %s\nGênero: %s\nNome do Arquivo: %s\n\n", musicInfo[0], musicInfo[2], musicInfo[3], musicInfo[4], musicInfo[5]);
                formatData += info;
            }
        }

        FILE_PATH += String.format("RelatórioMúsicas%s.txt", name);
        RecordUser.replaceInFile(formatData, FILE_PATH);
    }
}
