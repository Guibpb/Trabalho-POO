import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class WindowInterface extends JFrame implements ActionListener{

    JPanel painelDeControle = new JPanel();
    JPanel painelBotoes = new JPanel();

    public WindowInterface() {
        this.setSize(1200, 800);
        this.setTitle("Player de Música");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(new BorderLayout(0, 0));
        this.getContentPane().setBackground(Color.gray);
        painelDeControle.setBackground(Color.black);
        painelDeControle.setVisible(true);
        painelDeControle.setPreferredSize(new Dimension(1200, 100));
        painelDeControle.setBackground(Color.black);
        painelDeControle.setLayout(new GridBagLayout());

        painelBotoes.setBackground(Color.gray);
        painelBotoes.setVisible(true);
        painelBotoes.setPreferredSize(new Dimension(100, 50));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.WEST;

        painelDeControle.add(painelBotoes, gbc);

        this.add(painelDeControle, BorderLayout.SOUTH);

        this.setVisible(true);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}