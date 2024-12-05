package com.example.trabalho.BackEnd;

import java.io.FileNotFoundException;

public class ModifyUser {
    public static void modUser(String fileContent, String oldContent, String newContent, String fileName) throws FileNotFoundException{
        fileContent = fileContent.replace(oldContent,newContent);
        RecordUser.replaceInFile(fileContent, fileName);
    }
}