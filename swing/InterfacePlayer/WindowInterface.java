import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class WindowInterface extends JFrame{

    JPanel painelDeControle = new JPanel();
    JPanel painelBotoes = new JPanel();
    JPanel painelEscolherMusica = new JPanel();
    JLabel iconeMusica = new JLabel();
    JButton play = new JButton();
    JButton back = new JButton();
    JButton next = new JButton();
    JButton escolherMusica = new JButton();

    ImageIcon imgPlay = new ImageIcon("C:/Users/danie/programs/java/trabalhoPOO/swing/InterfacePlayer/play.png");
    Image resizedImagePlay = imgPlay.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH); 
    ImageIcon imgPlayResized = new ImageIcon(resizedImagePlay);

    ImageIcon imgPause = new ImageIcon("C:/Users/danie/programs/java/trabalhoPOO/swing/InterfacePlayer/pause.png");
    Image resizedImagePause = imgPause.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH); 
    ImageIcon imgPauseResized = new ImageIcon(resizedImagePause);

    Clip clip;

    ActionListener tocarOuPausarMusica = (ActionEvent e) -> {

        if(play.getIcon() == imgPlayResized){
            play.setIcon(imgPauseResized);
            clip.start();
        }else{
            play.setIcon(imgPlayResized);
            clip.stop();
        }
    };

    public void escolherMusicaFunction() throws UnsupportedAudioFileException, IOException, LineUnavailableException{
        JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fc.showOpenDialog(fc);
        File f = fc.getSelectedFile();
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(f);
        clip = AudioSystem.getClip();
        clip.open(audioStream);
        clip.start();
        play.setIcon(imgPauseResized);
    }

    ActionListener escolherMusicaFunction = (ActionEvent e) -> {
        try {
            escolherMusicaFunction();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            // Print the stack trace or handle the exception as needed
        }
    };

    

    public WindowInterface() {
        this.setSize(1200, 800);
        this.setTitle("Player de Música");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(new BorderLayout());
        this.getContentPane().setBackground(Color.black);
        this.setLocationRelativeTo(null);

        painelDeControle.setBackground(Color.white);
        painelDeControle.setVisible(true);
        painelDeControle.setPreferredSize(new Dimension(0, 150));
        painelDeControle.setLayout(null);

        painelBotoes.setBackground(Color.white);
        painelBotoes.setSize(150, 60);
        painelBotoes.setBounds(440, 25, 250, 90);
        painelBotoes.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));

        play.setPreferredSize(new Dimension(70, 70));
        play.setFocusable(false);
        play.setIcon(imgPlayResized);
        play.setBackground(Color.white);
        play.setBorder(null);
        play.addActionListener(tocarOuPausarMusica);

        back.setPreferredSize(new Dimension(70, 70));
        back.setFocusable(false);
        ImageIcon imgBack = new ImageIcon("C:/Users/danie/programs/java/trabalhoPOO/swing/InterfacePlayer/back.png");
        Image resizedImageBack = imgBack.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH); 
        ImageIcon imgBackResized = new ImageIcon(resizedImageBack);
        back.setIcon(imgBackResized);
        back.setBackground(Color.white);
        back.setBorder(null);

        next.setPreferredSize(new Dimension(70, 70));
        next.setFocusable(false);
        ImageIcon imgNext = new ImageIcon("C:/Users/danie/programs/java/trabalhoPOO/swing/InterfacePlayer/next.png");
        Image resizedImageNext = imgNext.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH); 
        ImageIcon imgNextResized = new ImageIcon(resizedImageNext);
        next.setIcon(imgNextResized);
        next.setBackground(Color.white);
        next.setBorder(null);

        painelBotoes.add(back);
        painelBotoes.add(play);
        painelBotoes.add(next);

        ImageIcon imgMusica = new ImageIcon("C:/Users/danie/programs/java/trabalhoPOO/swing/InterfacePlayer/iconeMusica.png");
        Image resizedImageMusica = imgMusica.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH); 
        ImageIcon iconResizedMusica = new ImageIcon(resizedImageMusica);

        iconeMusica.setIcon(iconResizedMusica);
        iconeMusica.setBounds(40, 35, 80, 80);

        painelDeControle.add(painelBotoes);
        painelDeControle.add(iconeMusica);

        escolherMusica.setText("Escolha a musica");
        escolherMusica.setBounds(425, 200, 300, 200);
        escolherMusica.setFont(new Font("Helvetica", Font.BOLD, 30));
        escolherMusica.setBackground(Color.lightGray);
        escolherMusica.setFocusable(false);
        escolherMusica.addActionListener(escolherMusicaFunction);

        painelEscolherMusica.setPreferredSize(new Dimension(0, 900));
        painelEscolherMusica.setLayout(null);
        painelEscolherMusica.setBackground(Color.black);
        painelEscolherMusica.add(escolherMusica);

        this.add(painelDeControle, BorderLayout.SOUTH);
        this.add(painelEscolherMusica, BorderLayout.CENTER);
        
        this.setVisible(true);
        
    }
}