package com.example.uspfy;
import java.util.Scanner;

public class UploadMusic extends MusicStorage {

    public void upload(){

        try (Scanner scan = new Scanner(System.in)) {
            System.out.print("Nome do artista: ");
            String artistName = scan.nextLine();
            System.out.print("Nome da musica: ");
            String musicName = scan.nextLine();
            System.out.print("Arquivo da musica: ");
            String fileName = scan.nextLine(); //temporario, colocar arquivo aq


            writer.printf("%d,%s, %s, %d, %s\n", musicID, artistName, musicName, 0, fileName);
            musicID++;

            writer.close();

            System.out.println("Sucesso!");
        }
    }
}
