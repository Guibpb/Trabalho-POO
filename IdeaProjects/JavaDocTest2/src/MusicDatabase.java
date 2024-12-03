import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MusicDatabase {

    /**
     * Le o banco de dados de músicas e passa para uma Lista de Arrays de Strings, pois o tamanho de cada linha é estático,
     * ou seja, não sera alterada comforme a execução do programa, que sera ultilizada como intermediario
     * para editar as músicas no banco de dados. Caso o arquivo não exista, ele cria um novo arquivo com um
     * modelo que sera transcrito na primeira linha do arquivo.
     * @return Retorna uma Lista de Arrays de Strings com os dados do arquivo de músicas.
     */
    public static List<String[]> getMusicCSVFile(){
        List<String[]> musicCSVFileList = new ArrayList<>(); //como cada linha tem 6 elementos sempre, n precisa ser dinamico
        String row; //cada linha do arquivo
        File musicCSVFile = new File("musics.csv");
        try {
            if(musicCSVFile.createNewFile()) {
                PrintWriter printTemplate = new PrintWriter(musicCSVFile);
                printTemplate.printf("MusicID,Artist,MusicName,Views,MusicGenre,FileName");
                printTemplate.close();
            }
            BufferedReader reader = new BufferedReader(new FileReader("musics.csv"));
            while((row = reader.readLine()) != null){ //le até o fim do arquivo
                musicCSVFileList.add(row.split(",")); //le cada linha e divide os elementos quando achar uma virgula
            }//se precisar colocar musica com virgula, nois vai ter q mudar isso e usar REGEX, achei meio paia p explicar se ela perguntar
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return musicCSVFileList; // retorna uma lista de arrays de string
    }

    /**
     * Atualiza o banco de dados de músicas. Recebe uma Lista de músicas e escreve ela no arquivo das músicas,
     * separando cada música por linhas e cada dado de uma música por virgulas com o uso do PrintWriter, que no caso
     * permite escrever de forma formatada diretamente no arquivo.
     * @param musicCSVFileList Lista de músicas que vai ser transcrita para o banco de dados de músicas.
     */
    public static void updateMusicCSVFile(List<String[]> musicCSVFileList){
        File musicCSVFile = new File("musics.csv");
        try {
            if(musicCSVFile.createNewFile()) {
                PrintWriter printTemplate = new PrintWriter(musicCSVFile);
                printTemplate.printf("MusicID,Artist,MusicName,Views,MusicGenre,FileName");
                printTemplate.close();
            } //printwriter mt mais pratico pra escrever de uma matriz pra um arquivo na minha opiniao
            PrintWriter writer = new PrintWriter(new FileWriter("musics.csv"));
            for(String[] data : musicCSVFileList){ //como n se altera o tamanho do array, n precisa fzr a gambiarra que nem nas playlist
                writer.printf("%s,%s,%s,%s,%s,%s\n", data[0], data[1], data[2], data[3], data[4], data[5]);
            } //so vai dar erro se alguem mecher manualmente no arquivo csv, se der erro ele vai apagar tudo, melhor criar um backup
            writer.close();
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }
}