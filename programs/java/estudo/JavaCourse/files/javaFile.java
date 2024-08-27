import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class javaFile{
    public static void main(String[] args){

        try {
            FileWriter writer = new FileWriter("arquivo.txt");
            FileReader reader = new FileReader("arquivo.txt");

            writer.write("Daniel;18;Audi;");
            writer.close();

            int data;
            String intermediate = "";
            int index = 1;
            String name = "";
            int idade = 0;
            String car = "";
          
            do { 
                data = reader.read();
                if(data != -1 && (char)data != ';'){
                    intermediate += (char)data;     
                }else{
                    if(data != -1){
                        switch (index) {
                            case 1 -> {
                                name = intermediate;
                                intermediate = "";
                                index++;
                            }
                            case 2 -> {
                                idade = Integer.parseInt(intermediate);
                                intermediate = "";
                                index++;
                            }
                            case 3 -> {
                                car = intermediate;
                                intermediate = "";
                            }
                        }
                    }
                }

            } while (data != -1);

            System.out.println("My name is " + name + ", I have " +idade+ " years old, and my dream is to have a " + car);

        } catch (FileNotFoundException e) {

        }catch(IOException e){

        }

       

            
    }  
}