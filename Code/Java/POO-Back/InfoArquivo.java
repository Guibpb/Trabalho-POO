import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class InfoArquivo {
    /*Esse código cria uma lista bidimensional de Strings para armazenar os valores
    do arquivo no começo do programa, e não precisar ficar chamando a função toda vez */

    private static ArrayList<String[]> matrixInfo = new ArrayList<>(); //array list dividida
    private static String dadosBrutos = ""; //String com tudo

    public static String getDadosBrutos(){
        return dadosBrutos;
    }

    public static ArrayList<String[]> getMatrixInfo(){
        return matrixInfo;
    }

    public static int getMatrixSize(){
        return matrixInfo.size();
    }

    public static void infoArquivo()throws FileNotFoundException{

        try(Scanner scanArquivo = new Scanner(Main.arquivo)){
            dadosBrutos = scanArquivo.nextLine() + '\n';

            while(scanArquivo.hasNextLine()){ //loop para percorrer cada linha do arquivo
                String inputInfo = scanArquivo.nextLine();
                dadosBrutos += inputInfo + '\n';

                String usuarioInfo[] = inputInfo.split(",");//divide a linha do arquivo pelas vírgulas

                matrixInfo.add(usuarioInfo); //append no arraylist
            } 
        }
    } 
}
