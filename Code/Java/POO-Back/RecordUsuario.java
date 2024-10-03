import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class RecordUsuario {
    public void escreverArq(String dadosFormat) throws FileNotFoundException{

        try (FileWriter escreverArq = new FileWriter("Banco.csv", true)){
            escreverArq.append(dadosFormat);//escreve no arquivo os dados formatados
            escreverArq.close();
            InfoArquivo.infoArquivo(); //atualiza os dados no ArrayList
        } 
        
        catch (IOException e) {
            //erro de arquivo nao encontrado
        }
    }

    public static void substituirArq(String dadosBrutos) throws FileNotFoundException{

        try (FileWriter escreverArq = new FileWriter("Banco.csv")){
            escreverArq.append(dadosBrutos); //substitui no arquivo
            escreverArq.close();    
            InfoArquivo.infoArquivo();//Atualiza os dados no Arraylist 
        } 
        
        catch (IOException e) {
            //erro de arquivo nao encontrado
        }
    }
}
