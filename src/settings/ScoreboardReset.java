/*
package settings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
//삭제해도 될듯

public class ScoreboardReset extends JFrame {

    private JButton yesButton;
    private JButton noButton;

    public ScoreboardReset() {
        setTitle("스코어보드 초기화");
        setSize(300, 200);
        setLayout(new GridLayout(3, 1));

        JLabel questionLabel = new JLabel("스코어보드를 초기화 하겠습니까?", SwingConstants.CENTER);
        add(questionLabel);

        yesButton = new JButton("Yes");
        noButton = new JButton("No");

        // 버튼 포커스 리스너 추가
        FocusListener focusListener = new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                JButton source = (JButton) e.getComponent();
                source.setBackground(Color.CYAN); // 포커스 있을 때 색상
            }

            @Override
            public void focusLost(FocusEvent e) {
                JButton source = (JButton) e.getComponent();
                source.setBackground(UIManager.getColor("Button.background")); // 포커스 없을 때 기본 색상
            }
        };

        yesButton.addFocusListener(focusListener);
        noButton.addFocusListener(focusListener);

        yesButton.addActionListener(e -> clearScoreboard());
        noButton.addActionListener(e -> dispose());

        add(yesButton);
        add(noButton);

        setupKeyBindings();

        setLocationRelativeTo(null);
    }

    private void setupKeyBindings() {
        settingModel settingModel = new settingModel();
        // 사용자 설정 키 로드
        int upKey = settingModel.getUpKey();
        int downKey = settingModel.getDownKey();

        // 위로 이동 (W)
        noButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(upKey, 0), "focusUp");
        noButton.getActionMap().put("focusUp", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                yesButton.requestFocus();
            }
        });

        // 아래로 이동 (S)
        yesButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(downKey, 0), "focusDown");
        yesButton.getActionMap().put("focusDown", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                noButton.requestFocus();
            }
        });

        yesButton.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke("ENTER"), "yesPressed");
        yesButton.getActionMap().put("yesPressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearScoreboard();
            }
        });

        noButton.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke("ENTER"), "noPressed");
        noButton.getActionMap().put("noPressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        // 프레임 전체에 대한 ESC 키 처리 - "ESC"를 누르면 창 닫기
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "closeWindow");
        getRootPane().getActionMap().put("closeWindow", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // 현재 창 닫기
            }
        });
    }

    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if (visible) {
            yesButton.requestFocusInWindow(); // 창이 보여질 때 yesButton에 포커스를 줍니다.
        }
    }

    private void clearScoreboard() {
        File file = new File("scoreboard.txt");
        try {
            new FileWriter(file, false).close();
            JOptionPane.showMessageDialog(null, "스코어보드가 초기화되었습니다.", "완료", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ioException) {
            JOptionPane.showMessageDialog(null, "스코어보드 초기화 실패.", "오류", JOptionPane.ERROR_MESSAGE);
        }
        dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ScoreboardReset().setVisible(true));
    }
}

 */

