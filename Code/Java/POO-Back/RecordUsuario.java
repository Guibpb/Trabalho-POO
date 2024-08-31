import java.io.FileWriter;
import java.io.IOException;

public class RecordUsuario {
    public static void escreverArq(String dados) {
        try (FileWriter escreverArq = new FileWriter("POO-Back/Banco.csv", true)){
            escreverArq.append(dados);
            escreverArq.close();
        } catch (IOException e) {
            System.out.println("Erro! Arquivo não encontrado.");
            e.printStackTrace();
        }
    }
}
