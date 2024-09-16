import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class myFrameImageView extends JFrame{
    
    JButton btn = new JButton("TROCAR");
    JPanel panel = new JPanel();

    ImageIcon img1 = new ImageIcon("C:/Users/danie/programs/java/trabalhoPOO/swing/ImageView/imagem1.jpg");
    Image resizedImage1 = img1.getImage().getScaledInstance(600, 400, Image.SCALE_SMOOTH); 
    ImageIcon iconResized1 = new ImageIcon(resizedImage1);

    ImageIcon img2 = new ImageIcon("C:/Users/danie/programs/java/trabalhoPOO/swing/ImageView/imagem2.jpg");
    Image resizedImage2 = img2.getImage().getScaledInstance(600, 400, Image.SCALE_SMOOTH); 
    ImageIcon iconResized2 = new ImageIcon(resizedImage2);
    
    JLabel label = new JLabel(iconResized1);

    ActionListener clicou = (ActionEvent e) -> {

        if(label.getIcon() == iconResized1){
            label.setIcon(iconResized2);
        }else{
            label.setIcon(iconResized1);
        }
    };    

    public myFrameImageView(){

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        this.setTitle("Image View");

        btn.setFocusable(false);
        btn.setPreferredSize(new Dimension(100, 40));
        btn.addActionListener(clicou);

        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new java.awt.Insets(30, 0, 0, 0);

        panel.add(label, gbc);
        gbc.gridy++;
        gbc.insets = new java.awt.Insets(60, 0, 0, 0);
        panel.add(btn, gbc);

        this.add(panel, BorderLayout.NORTH);
        this.setVisible(true);
    }
}