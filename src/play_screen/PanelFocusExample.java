package play_screen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class PanelFocusExample extends JFrame {
    public PanelFocusExample() {
        this.setLayout(new FlowLayout());

        JPanel panel1 = new JPanel();
        panel1.setBackground(Color.GRAY);
        panel1.setPreferredSize(new Dimension(100, 100));
        panel1.setFocusable(true); // 패널이 포커스를 받을 수 있도록 설정

        JPanel panel2 = new JPanel();
        panel2.setBackground(Color.LIGHT_GRAY);
        panel2.setPreferredSize(new Dimension(100, 100));
        panel2.setFocusable(true); // 패널이 포커스를 받을 수 있도록 설정

        // 패널 1에 대한 포커스 리스너
        panel1.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                panel1.setBackground(Color.YELLOW);
            }

            @Override
            public void focusLost(FocusEvent e) {
                panel1.setBackground(Color.GRAY);
            }
        });

        // 패널 2에 대한 포커스 리스너
        panel2.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                panel2.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent e) {
                panel2.setBackground(Color.LIGHT_GRAY);
            }
        });

        // 패널과 버튼 추가
        this.add(panel1);
        this.add(panel2);
        JButton button = new JButton("Focus on Panel 1");
        button.addActionListener(e -> panel1.requestFocusInWindow());
        this.add(button);

        // 기본 윈도우 설정
        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new PanelFocusExample();
    }
}
