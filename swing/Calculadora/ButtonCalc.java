import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;

public class ButtonCalc extends JButton{
    ButtonCalc(String text){
        this.setText(text);
        this.setFocusable(false);
        this.setBackground(new Color(25, 25, 25));
        this.setForeground(new Color(250, 250, 250));
        this.setFont(new Font("Arial", Font.PLAIN, 20));

        JButton btn = this;

        this.addMouseListener(new MouseListener(){

            @Override
            public void mouseClicked(MouseEvent e) {
                
            }

            @Override
            public void mousePressed(MouseEvent e) {
                
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(new Color(50, 50, 50));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btn.setBackground(new Color(25, 25, 25));
            }
        });
    }
}
