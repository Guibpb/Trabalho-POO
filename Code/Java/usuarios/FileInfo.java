import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Classe FileInfo extrai dados de um arquivo e converte para uma matriz
 * ou uma formatação em String para CSV.
 */

public class FileInfo {
    /**
     * Lê o conteúdo de um arquivo e converte para uma matriz (Arraylist de Array de String)
     * onde cada linha do arquivo corresponde uma array de String.
     * 
     * @param FILE_NAME Nome do arquivo para ler.
     * @return Um Array List de array de String contendo os dados do arquivo.
     * @throws FileNotFoundException Tratamento de excessão de arquivo inexistente.
     */
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

    /**
     * Lê o conteúdo de um arquivo e retorna uma String.
     * 
     * @param FILE_NAME Nome do arquivo para ler.
     * @return String contendo os dados do arquivo.
     * @throws FileNotFoundException Tratamento de excessão de arquivo inexistente.
     */
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
