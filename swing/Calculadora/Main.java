
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

public class Main{
    public static String equation = "";
    public static void main(String[] args) {
        JFrame tela = new JFrame();
        tela.setSize(400, 500);
        tela.setResizable(false);
        tela.setTitle("Calculadora");
        tela.setLayout(new BorderLayout(0, 15));
        tela.setBackground(Color.white);
        tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel visor = new JPanel(new SpringLayout());
        visor.setPreferredSize(new Dimension(100, 180));

        JPanel botoes = new JPanel();
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

        JLabel valor = new JLabel();
        valor.setText("");
        valor.setFont(new Font("Helvetica", Font.BOLD, 65));
        visor.add(valor);

        SpringLayout layout = (SpringLayout) visor.getLayout();
        layout.putConstraint(SpringLayout.SOUTH, valor, 0, SpringLayout.SOUTH, visor);
        layout.putConstraint(SpringLayout.EAST, valor, 0, SpringLayout.EAST, visor);

        tela.add(visor, BorderLayout.NORTH);
        tela.add(botoes, BorderLayout.SOUTH);

        ActionListener clicou = (ActionEvent e) -> {
            JButton sourceBtn = (JButton) e.getSource();
            String valorAtual = valor.getText();
            if(valorAtual.length() < 8){
               if("".equals(valorAtual)){
                valor.setText(sourceBtn.getText());
                if("".equals(equation)){
                    equation = sourceBtn.getText();
                }else{
                    equation += sourceBtn.getText();
                }
                }else{
                valor.setText(valor.getText() + sourceBtn.getText());
                if("".equals(equation)){
                    equation = sourceBtn.getText();
                }else{
                    equation += sourceBtn.getText();
                }
                } 
            }    
        };

        btnV.addActionListener((ActionEvent e) -> {
            if(!("".equals(equation))){
                equation += "*";
                valor.setText("");
            }
        });

         btnD.addActionListener((ActionEvent e) -> {
             if(!("".equals(equation))){
                 equation += "/";
                 valor.setText("");
             }
        });

        btnM.addActionListener((ActionEvent e) -> {
            if(!("".equals(equation))){
                equation += "+";
                valor.setText("");
            }
        });

        btnS.addActionListener((ActionEvent e) -> {
            if(!("".equals(equation))){
                equation += "-";
                valor.setText("");
            }
        });

        btnI.addActionListener((ActionEvent e) -> {
        });


        btn1.addActionListener(clicou);
        btn2.addActionListener(clicou);
        btn3.addActionListener(clicou);
        btn4.addActionListener(clicou);
        btn5.addActionListener(clicou);
        btn6.addActionListener(clicou);
        btn7.addActionListener(clicou);
        btn8.addActionListener(clicou);
        btn9.addActionListener(clicou);
        btn0.addActionListener(clicou);


        tela.setVisible(true);

    }
}