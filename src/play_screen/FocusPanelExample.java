package play_screen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

public class FocusPanelExample extends JFrame {
    private JPanel[] panels = new JPanel[4];
    private JLabel[] labels = new JLabel[4];
    private int[] values = {0, 0, 0, 0};
    private int focusedIndex = 0;

    public FocusPanelExample() {
        setLayout(new GridLayout(1, 4, 5, 5));
        KeyAdapter keyAdapter = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        moveFocus(-1);
                        break;
                    case KeyEvent.VK_RIGHT:
                        moveFocus(1);
                        break;
                    case KeyEvent.VK_UP:
                        updateValue(1);
                        break;
                    case KeyEvent.VK_DOWN:
                        updateValue(-1);
                        break;
                }
            }
        };

        for (int i = 0; i < panels.length; i++) {
            panels[i] = new JPanel(new BorderLayout());
            panels[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
            panels[i].setFocusable(true);
            panels[i].addKeyListener(keyAdapter);

            labels[i] = new JLabel("0", SwingConstants.CENTER);
            panels[i].add(labels[i], BorderLayout.CENTER);

            add(panels[i]);
        }

        updateFocus();
        setSize(400, 100);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void updateFocus() {
        for (int i = 0; i < panels.length; i++) {
            if (i == focusedIndex) {
                panels[i].setBackground(Color.YELLOW);
                panels[i].requestFocusInWindow();
            } else {
                panels[i].setBackground(Color.WHITE);
            }
        }
    }

    private void moveFocus(int direction) {
        focusedIndex += direction;
        if (focusedIndex < 0) {
            focusedIndex = panels.length - 1;
        } else if (focusedIndex >= panels.length) {
            focusedIndex = 0;
        }
        updateFocus();
    }

    private void updateValue(int increment) {
        values[focusedIndex] += increment;
        labels[focusedIndex].setText(String.valueOf(values[focusedIndex]));
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            FocusPanelExample example = new FocusPanelExample();
            example.setVisible(true);
        });
    }
}
