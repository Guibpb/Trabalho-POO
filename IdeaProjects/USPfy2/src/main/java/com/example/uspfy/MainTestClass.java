package com.example.uspfy;


public class MainTestClass {
    public static void main(String[] args) {

        UploadMusic up = new UploadMusic();

        up.getArrayOfMusicFiles();
        up.writeCsvMusicFile();
        up.upload();
        up.viewFile();
    }
}