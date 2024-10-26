package com.example.uspfy;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public abstract class MusicStorage{

    public int musicID = 1;

    File musicFileDirectory;
    File[] musicFiles;
    ArrayList<File> arrayOfMusicFiles;

    PrintWriter writer;
    BufferedReader reader;

    public void getArrayOfMusicFiles() {

        musicFileDirectory = new File("Musics");
        musicFiles = musicFileDirectory.listFiles();
        arrayOfMusicFiles = new ArrayList<File>();


        if (musicFiles != null) {
            arrayOfMusicFiles.addAll(Arrays.asList(musicFiles));
        } else
            System.out.println("Pasta de musicas vazia");

        for(File file : arrayOfMusicFiles)
            System.out.println(file.getName());
    }

    public void writeCsvMusicFile() {

            try {
                writer = new PrintWriter(new FileWriter("musics.csv", true));

                for (File file : arrayOfMusicFiles) {
                            writer.printf("%d,%s, %s, %d, %s\n", musicID, "USPfy", file.getName(), 0, file);
                            musicID++;
                }

        }catch(IOException e){
                e.printStackTrace();
        }
    }

    public void viewFile() {

        try {
            reader = new BufferedReader(new FileReader("musics.csv"));
            int data = reader.read();
            while (data != -1) {
                System.out.print((char) data);
                data = reader.read();
            }

            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
