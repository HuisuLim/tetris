package temp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TextFieldExample {
    public static void main(String[] args) {

        JFrame frame = new JFrame("TextField Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLayout(new GridLayout(1, 2));

        JPanel aPanel = new JPanel();
        aPanel.setBackground(Color.RED);
        frame.add(aPanel);

        JPanel rightPanel = new JPanel(new GridLayout(2,1));
        JPanel bPanel = new JPanel();
        bPanel.setBackground(Color.BLUE);
        JPanel cPanel = new JPanel();
        cPanel.setBackground(Color.YELLOW);
        rightPanel.add(bPanel);
        rightPanel.add(cPanel);
        frame.add(rightPanel);



        JPanel inputPanel = new JPanel(new GridLayout(3, 1));
        inputPanel.setBackground(Color.lightGray);
        inputPanel.setSize(200, 300);
        JLabel inputLabel = new JLabel("이름을 입력하세요");
        JTextField inputField = new JTextField(20);
        JLabel inputHelpLabel = new JLabel("이름완료 후 Enter를 누르시오.");
        inputPanel.add(inputLabel);
        inputPanel.add(inputField);
        inputPanel.add(inputHelpLabel);
        inputPanel.setLocation((frame.getWidth() - inputPanel.getWidth()) / 2, (frame.getHeight() - inputPanel.getHeight()) / 2); // 위치 중앙으로 설정
        frame.getLayeredPane().add(inputPanel, JLayeredPane.POPUP_LAYER); // JLayeredPane에 PausePanel 추가
        inputPanel.setVisible(true);

        JPanel outputPanel = new JPanel(new GridLayout(2,1));
        outputPanel.setSize(300,300);
        outputPanel.setBackground(Color.lightGray);
        JLabel outputLabel = new JLabel("output");
        JLabel output = new JLabel();
        outputPanel.add(outputLabel);
        outputPanel.add(output);
        outputPanel.setVisible(false);
        outputPanel.setLocation((frame.getWidth() - outputPanel.getWidth()) / 2, (frame.getHeight() - outputPanel.getHeight()) / 2); // 위치 중앙으로 설정
        frame.getLayeredPane().add(outputPanel, JLayeredPane.POPUP_LAYER); // JLayeredPane에 PausePanel 추가



        inputField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = inputField.getText();  // 텍스트 필드로부터 텍스트 추출
                System.out.println("입력된 이름: " + s);  // 콘솔에 입력된 이름 출력
                inputPanel.setVisible(false);
                output.setText(inputField.getText());
                outputPanel.setVisible(true);
            }
        });

        frame.setVisible(true);
    }
}
