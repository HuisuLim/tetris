package temp;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrameWithPopup {

    private static JPanel mainPanel;
    private static JLabel textLabel;

    public static void main(String[] args) {
        // 메인 프레임 설정
        JFrame frame = new JFrame("Main Frame with Popup");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLayout(new BorderLayout());

        // 메인 패널 설정
        mainPanel = new JPanel();
        mainPanel.setBackground(Color.BLUE);
        mainPanel.setLayout(new GridBagLayout());
        frame.add(mainPanel, BorderLayout.CENTER);

        // 중앙 텍스트 라벨 설정
        textLabel = new JLabel(" ");
        mainPanel.add(textLabel);

        // 팝업 패널 설정
        JPanel popupPanel = new JPanel();
        popupPanel.setBackground(Color.WHITE);
        popupPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        JTextField textField = new JTextField(20);
        popupPanel.add(textField);
        popupPanel.setSize(250, 100);
        popupPanel.setLocation((500 - 250) / 2, (500 - 100) / 2);
        popupPanel.setVisible(false);

        // 레이어드 패인에 팝업 패널 추가
        JLayeredPane layeredPane = frame.getLayeredPane();
        layeredPane.add(popupPanel, JLayeredPane.POPUP_LAYER);

        // 텍스트 필드에 액션 리스너 추가
        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textLabel.setText(textField.getText());
                textField.setText("");
                popupPanel.setVisible(false);
            }
        });

        // 스페이스바 키 리스너 추가
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE && !popupPanel.isVisible()) {
                    popupPanel.setVisible(true);
                    textField.requestFocus();
                }
            }
        });

        frame.setVisible(true);
        frame.setFocusable(true);
        frame.setFocusTraversalKeysEnabled(false);
    }
}
