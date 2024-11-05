import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class RecordUser {
    public static void writeInFile(String formatData, String fileName) throws FileNotFoundException{
        try (FileWriter writeInFile = new FileWriter(fileName, true)){
            writeInFile.append(formatData);//escreve no arquivo os dados formatados
            writeInFile.close();
        } 
        
        catch (IOException e) {
            //erro de arquivo nao encontrado
        }
    }

    public static void replaceInFile(String rawData, String fileName) throws FileNotFoundException{
        try (FileWriter writeInFile = new FileWriter(fileName)){
            writeInFile.append(rawData); //substitui no arquivo
            writeInFile.close();    
        } 
        
        catch (IOException e) {
            //erro de arquivo nao encontrado
        }
    }
}
