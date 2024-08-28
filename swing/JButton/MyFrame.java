import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;

public class MyFrame extends JFrame{

    JButton button;

    public MyFrame(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(800, 800);
        this.setVisible(true);

        button = new JButton();
        button.setBounds((int)(this.getBounds().getWidth() / 2) - 50, (int)(this.getBounds().getHeight() / 2) - 50, 100, 50);
        button.setText("Clique");
        button.setFont(new Font("Helvetica", Font.BOLD, 20));
        button.setFocusable(false);
        button.setBackground(Color.WHITE);
        button.setBorder(BorderFactory.createLoweredSoftBevelBorder());
        button.addActionListener(e -> clicou());

        this.add(button);
    }

    public void clicou(){
        System.out.println("Clicou");
    }
}