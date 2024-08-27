import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.Border;

public class Main{
    public static void main(String[]args){
         
        ImageIcon image = new ImageIcon("C:/Users/danie/programs/java/estudo/Java Course/swing/JLabel/java-logo-1.png"); //cria uma imagem
        Image resizedImage = image.getImage().getScaledInstance(400, 400, Image.SCALE_SMOOTH); //cria uma imagem com um novo tamanho
        ImageIcon iconResized = new ImageIcon(resizedImage); //seta essa imagem com um novo tamanho como um icon
        Border border = BorderFactory.createLineBorder(Color.red, 3); //cria uma borda para usar nos labels
                                                                      //BorderFactory.EstiloDaBorda(Cor, espessura)

        JLabel label = new JLabel(); //cria o label
        label.setText("Texto no LABEL"); //coloca um texto no label
        label.setIcon(iconResized); //coloca uma imagem no label
        label.setHorizontalTextPosition(JLabel.CENTER); //determina a posição horizontal do texto (LEFT, CENTER, RIGHT)
        label.setVerticalTextPosition(JLabel.TOP); //determina a posição vertical do texto (TOP, CENTER, BOTTOM)
        label.setForeground(Color.red); //determina a cor do texto
        label.setFont(new Font("MV Boli", Font.BOLD, 40)); //determina a fonte, seu estilo, e seu tamanho
        label.setIconTextGap(10); //adiciona uma distância entre o texto e a imagem (pode ser um valor negativo para ficar mais perto)
        label.setBackground(Color.black); //adiciona a cor de fundo do label
        label.setOpaque(true); //faz a cor ser visivel
        label.setBorder(border);
        label.setVerticalAlignment(JLabel.CENTER); //determina a posição vertical do texto e a imagem
        label.setHorizontalAlignment(JLabel.CENTER); //determina a posição horizontal do texto e a imagem
        //label.setBounds(0, 0, 300, 300); //posicao X e Y do label, tamanho vertical e horizontal, respectivamente

        MyFrame myFrame = new MyFrame();
        myFrame.setTitle("Spotify"); //coloca um titulo
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // termina o programa quando fecha a janela
        //myFrame.setResizable(false); //impede que mude o tamanho da tela criada
        myFrame.setSize(800, 600); //seta a dimensao x, y
        //myFrame.setLayout(null);
        myFrame.setVisible(true); // faz a tela visivel

        myFrame.add(label); //adiciona o label no frame
        myFrame.pack(); //seta o tamanho do frame do tamanho do label


    }
}