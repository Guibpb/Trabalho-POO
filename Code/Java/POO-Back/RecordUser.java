import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class RecordUser {
    public void writeInFile(String formatData) throws FileNotFoundException{

        try (FileWriter writeInFile = new FileWriter("Banco.csv", true)){
            writeInFile.append(formatData);//escreve no arquivo os dados formatados
            writeInFile.close();
            FileInfo.fileInfo(); //atualiza os dados no ArrayList
        } 
        
        catch (IOException e) {
            //erro de arquivo nao encontrado
        }
    }

    public static void replaceInFile(String rawData) throws FileNotFoundException{

        try (FileWriter writeInFile = new FileWriter("Banco.csv")){
            writeInFile.append(rawData); //substitui no arquivo
            writeInFile.close();    
            FileInfo.fileInfo();//Atualiza os dados no Arraylist 
        } 
        
        catch (IOException e) {
            //erro de arquivo nao encontrado
        }
    }
}
