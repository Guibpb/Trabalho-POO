package swing.JOptionPane;

import javax.swing.JOptionPane;

public class Main {
    public static void main(String[] args) {
        //JOptionPane.showMessageDialog(null, "This is some useless info", "tittle", JOptionPane.PLAIN_MESSAGE);
        //JOptionPane.showMessageDialog(null, "This is some useless info", "tittle", JOptionPane.INFORMATION_MESSAGE);
        //JOptionPane.showMessageDialog(null, "This is some useless info", "tittle", JOptionPane.QUESTION_MESSAGE);
        //JOptionPane.showMessageDialog(null, "Your computer has a VIRUS!!", "Warning", JOptionPane.WARNING_MESSAGE);
        //JOptionPane.showMessageDialog(null, "Something going wrong...", "ERROR", JOptionPane.ERROR_MESSAGE);

        //JOptionPane.showConfirmDialog(null, "Hello?", "Tittle", JOptionPane.YES_NO_CANCEL_OPTION);
        //Yes returns 0
        //No returns 1
        //Cancel returns 2
        //Exit returns -1

        String[] responses = {"No, yo are awesome", "thank you", "bro?"};

        //JOptionPane.showOptionDialog(null, "You are awesome", "Secret message", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, responses, 0);
    }
}
