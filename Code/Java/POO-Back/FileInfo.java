import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileInfo {
    /*Esse código cria uma lista bidimensional de Strings para armazenar os valores
    do arquivo no começo do programa, e não precisar ficar chamando a função toda vez */

    private static ArrayList<String[]> matrixInfo = new ArrayList<>(); //array list dividida
    private static String rawData = ""; //String com tudo

    public static String getRawData(){ return rawData; }

    public static ArrayList <String[]> getMatrixInfo() { return matrixInfo; }

    public static int getMatrixSize(){ return matrixInfo.size(); }

    public static ArrayList <String[]> fileInfo()throws FileNotFoundException{

        try(Scanner fileScan = new Scanner(Main.file)){
            rawData = fileScan.nextLine() + '\n';

            while(fileScan.hasNextLine()){ //loop para percorrer cada linha do arquivo
                String inputInfo = fileScan.nextLine();
                rawData += inputInfo + '\n';

                String userInfo[] = inputInfo.split(",");//divide a linha do arquivo pelas vírgulas

                matrixInfo.add(userInfo); //append no arraylist
            } 
        }

        return matrixInfo;
    } 
}
