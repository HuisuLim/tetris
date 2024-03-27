package Settings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ScoreboardReset extends JFrame {

    private ArrayList<String> names;
    private ArrayList<Integer> scores;

    public ScoreboardReset(){
        names = new ArrayList<>();
        scores = new ArrayList<>();
    }

private static void Setting() {
    JFrame frame = new JFrame();
    JButton resetButton_sc = new JButton();

    resetButton_sc.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            names.clear();
            scores.clear();
            스코어보드.removeAll();
            스코어보드.revalidate();
            스코어보드.reapaint();

        }

    });

    ______add.(resetButton_sc);
}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Setting();
            }
        });
    }

}
