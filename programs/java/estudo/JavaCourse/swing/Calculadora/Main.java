
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

public class Main{
    public static void main(String[] args) {
        JFrame tela = new JFrame();
        tela.setSize(400, 500);
        tela.setResizable(false);
        tela.setTitle("Calculadora");
        tela.setLayout(new BorderLayout(0, 15));
        tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tela.setBackground(Color.red);

        JPanel visor = new JPanel(new SpringLayout());
        visor.setPreferredSize(new Dimension(100, 150));
        visor.setBackground(new Color(180, 180, 180));

        JPanel botoes = new JPanel();
        botoes.setBackground(Color.white);
        botoes.setLayout(new GridLayout(4, 4, 3, 3));
        botoes.setPreferredSize(new Dimension(100, 305));

        ButtonCalc btn1 = new ButtonCalc("1");
        ButtonCalc btn2 = new ButtonCalc("2");
        ButtonCalc btn3 = new ButtonCalc("3");
        ButtonCalc btn4 = new ButtonCalc("4");
        ButtonCalc btn5 = new ButtonCalc("5");
        ButtonCalc btn6 = new ButtonCalc("6");
        ButtonCalc btn7 = new ButtonCalc("7");
        ButtonCalc btn8 = new ButtonCalc("8");
        ButtonCalc btn9 = new ButtonCalc("9");
        ButtonCalc btn0 = new ButtonCalc("0");
        ButtonCalc btnV = new ButtonCalc("X");
        ButtonCalc btnD = new ButtonCalc("/");
        ButtonCalc btnM = new ButtonCalc("+");
        ButtonCalc btnS = new ButtonCalc("-");
        ButtonCalc btnI = new ButtonCalc("=");
        ButtonCalc btnP = new ButtonCalc(".");

        botoes.add(btn7);
        botoes.add(btn8);
        botoes.add(btn9);
        botoes.add(btnV);
        botoes.add(btn4);
        botoes.add(btn5);
        botoes.add(btn6);
        botoes.add(btnD);
        botoes.add(btn1);
        botoes.add(btn2);
        botoes.add(btn3);
        botoes.add(btnM);
        botoes.add(btn0);
        botoes.add(btnP);
        botoes.add(btnI);
        botoes.add(btnS);
        btnP.setFont(new Font("Arial", Font.PLAIN, 60));

        tela.add(botoes, BorderLayout.SOUTH);

        JLabel valor = new JLabel();
        valor.setText("23131");
        valor.setFont(new Font("Helvetica", Font.BOLD, 65));
        visor.add(valor);

        SpringLayout layout = (SpringLayout) visor.getLayout();
        layout.putConstraint(SpringLayout.SOUTH, valor, 0, SpringLayout.SOUTH, visor);
        layout.putConstraint(SpringLayout.EAST, valor, 5, SpringLayout.EAST, visor);

        tela.add(visor, BorderLayout.NORTH);

        tela.setVisible(true);

    }
}