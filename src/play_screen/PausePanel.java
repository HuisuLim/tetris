package play_screen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PausePanel extends JPanel {
    public PausePanel() {
        setLayout(new GridLayout(2, 1)); // 버튼 2개를 위한 그리드 레이아웃
        setPreferredSize(new Dimension(200, 100));
        JButton btnResume = new JButton("Resume");
        JButton btnClose = new JButton("Close");

        // 버튼 이벤트 리스너 추가
        btnResume.addActionListener(e -> setVisible(false)); // Resume 버튼 클릭 시 패널 숨김
        btnClose.addActionListener(e -> System.exit(0)); // Close 버튼 클릭 시 프로그램 종료

        add(btnResume);
        add(btnClose);

        // 키보드 포커스 설정
        setFocusable(true);
        requestFocusInWindow();
        btnResume.setFocusable(true);
        btnClose.setFocusable(true);

        // 키보드 리스너로 버튼 간 포커스 이동 및 선택 처리
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    if (btnResume.hasFocus()) {
                        btnClose.requestFocus();
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                    if (btnClose.hasFocus()) {
                        btnResume.requestFocus();
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (btnResume.hasFocus()) {
                        setVisible(false);
                    } else if (btnClose.hasFocus()) {
                        System.exit(0);
                    }
                }
            }
        });
    }
}
