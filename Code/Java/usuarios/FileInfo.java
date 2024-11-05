import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileInfo {
    public static ArrayList <String[]> getMatrixInfo(String FILE_NAME)throws FileNotFoundException{
        File file = new File(FILE_NAME);
        ArrayList <String[]> matrixInfo = new ArrayList<>();

        try(Scanner fileScan = new Scanner(file)){
            fileScan.nextLine();
            while(fileScan.hasNextLine()){ //loop para percorrer cada linha do arquivo
                String inputInfo = fileScan.nextLine();
                String userInfo[] = inputInfo.split(",");//divide a linha do arquivo pelas vírgulas

                matrixInfo.add(userInfo); //append no arraylist
            } 
        }

        return matrixInfo;
    } 

    public static String getRawData(final String FILE_NAME)throws FileNotFoundException{
        File file = new File(FILE_NAME);
        String rawData;

        try(Scanner fileScan = new Scanner(file)){
            rawData = fileScan.nextLine() + '\n';

            while(fileScan.hasNextLine()){ //loop para percorrer cada linha do arquivo
                String inputInfo = fileScan.nextLine();
                rawData += inputInfo + '\n';
            } 
        }

        rawData = rawData.trim();

        return rawData;
    } 
}
