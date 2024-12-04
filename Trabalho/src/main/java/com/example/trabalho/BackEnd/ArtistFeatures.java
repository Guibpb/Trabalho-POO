package com.example.trabalho.BackEnd;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.nio.file.StandardCopyOption;

interface ArtistFeatures {
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
}
