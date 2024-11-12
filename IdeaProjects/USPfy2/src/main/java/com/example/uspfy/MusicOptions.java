package com.example.uspfy;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class MusicOptions {

    public static int uploadMusic(List<String[]> musicCSVFileList, String artistName, String musicName, String musicGenre, File musicFile){
        if(musicName.contains(",")){
            return 1;
        }


        try{ //fudeu explicar isso aq +/-, pra funcionar tu bota uma pasta no TempMusic e copia o nome dela na main na variavel "testmusic", ela n pode tar na Music tbm
            Path musicDirectory = Path.of("Musics");//endereco da pasta de musicas
            if (!Files.exists(musicDirectory))
                Files.createDirectories(musicDirectory);
            //endereco da pasta de musicas
            Path origin = Path.of("TempMusics"); //n to conseguindo pegar de um diretorio generalizado
            Path musicFilePath = origin.resolve(musicFile.getName()); //endereco da musica? n entendi mt bem essa aq, vou pesquisar
            //esse foi o unico jeito q eu consegui fzr rodar
            Files.move(musicFilePath, musicDirectory.resolve(musicFilePath.getFileName())); //funcao de mover, vou pesquisar

            //parte do csv abaixo, so vai rodar se a de cima n der erro
            int currentID = 1; //se for a primeira musica a ser adicionada
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

    public static void deleteMusic(List<String[]> musicCSVFileList, File musicFile){
        for(String[] music : musicCSVFileList){
            if(music[5].equals(musicFile.getName())){
                musicCSVFileList.remove(music); //funcao propria da classe ArrayList de remover, "puxando pra baixo" as String[]s pra completar o "buraco"
                break; // com isso, cada id é unico, e se uma musica for deletada o id se torna inultilizavel, ja que nao da pra voltar, alem de n precisar atualizar todos os ids
                /* nao funciona ainda
                if(musicFile.delete())
                    System.out.println("Successfully deleted file");
                else
                    System.out.println("Failed to delete file");
                 */
            }
        }
        //essa função edita a List, logo precisa chamar a função update para atualizar o arquivo csv tbm, analogo pra de baixo
        MusicDatabase.updateMusicCSVFile(musicCSVFileList);
    }

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
}


