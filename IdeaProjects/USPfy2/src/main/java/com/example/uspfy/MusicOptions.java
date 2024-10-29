package com.example.uspfy;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class MusicOptions {

    public static void uploadMusic(List<String[]> musicCSVFileList, String artistName, String musicName, String musicGenre, File musicFile){
        try{ //fudeu explicar isso aq +/-, pra funcionar tu bota uma pasta no TempMusic e copia o nome dela na main na variavel "testmusic", ela n pode tar na Music tbm
            Path musicDirectory = Path.of("Musics");
            Path origin = Path.of("TempMusics"); //n to conseguindo pegar de um diretorio generalizado
            Path musicFilePath = origin.resolve(musicFile.getName());
            //esse foi o unico jeito q eu consegui fzr rodar
            Files.move(musicFilePath, musicDirectory.resolve(musicFilePath.getFileName()));
            //parte do csv abaixo, so vai rodar se a de cima n der erro
            int currentID = Integer.parseInt(musicCSVFileList.getLast()[0]);
            String newID = String.valueOf(currentID + 1);
            String[] newMusic = {newID,artistName,musicName,"0",musicGenre, musicFile.getName()};
            musicCSVFileList.add(newMusic);
            MusicDatabase.updateMusicCSVFile(musicCSVFileList);

        } catch (FileAlreadyExistsException e) {
            System.out.println("File already exists");
            //fazer alguma outra coisa sla, ou nois bota q pode upar musicas iguais (complica mais ainda)?
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public static void deleteMusic(List<String[]> musicCSVFileList, File musicFile){
        for(String[] music : musicCSVFileList){
            if(music[5].equals(musicFile.getName())){
                musicCSVFileList.remove(music);
                if(musicFile.delete())//nao funciona tnc
                    System.out.println("Successfully deleted file");
                else
                    System.out.println("Failed to delete file");
                break;
            }
        }
        //essa função edita a List, chama a função update para atualizar o arquivo tbm
        MusicDatabase.updateMusicCSVFile(musicCSVFileList);
    }

    public static void editMusic(List<String[]> musicCSVFileList,String musicID, String newName, String newGenre){
        for (String[] music : musicCSVFileList) {
            if (music[0].equals(musicID)) {
                music[2] = newName;
                music[4] = newGenre;
            }
        }
        MusicDatabase.updateMusicCSVFile(musicCSVFileList);
    }
}


