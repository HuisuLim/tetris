package startscreen;

import playscreen.PlayFrame;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class StartMenuController {
    private StartMenuView view;
    private JFrame nextFrame; // 다음 화면에 해당하는 JFrame
    public StartMenuView getView() {
        return view;
    }
    public StartMenuController() {
        view = StartMenuView.getInstance(); // 싱글턴 인스턴스 사용
        setupButton(view.getStartButton(), "normalMode");
        setupButton(view.getStartItemButton(), "itemMode");
        setupButton(view.getSettingsButton(), "settings");
        setupButton(view.getExitButton(), "exit");
        setupButton(view.getScoreButton(), "scoreboard");
        view.setVisible(true); // 뷰를 보이게 설정
    }

    private void setupButton(JButton button, String mode) {
        button.addActionListener(e -> handleButtonAction(mode));
        button.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    button.doClick();  // 엔터 키를 눌렀을 때 버튼 클릭 시뮬레이션
                }
            }
        });
    }

    private void handleButtonAction(String mode) {
        if (nextFrame != null && nextFrame.isVisible()) {
            nextFrame.dispose();  // 기존 창이 열려 있다면 닫기
        }
        switch (mode) {
            case "normalMode":
            case "itemMode":
                nextFrame = new PlayFrame(mode);
                break;
            case "settings":
                nextFrame = new Setting();
                break;
            case "exit":
                System.exit(0);
                return;
            case "scoreboard":
                nextFrame = new ShowScoreboard();
                break;
        }
        nextFrame.setVisible(true);
        view.dispose();
    }


    public static void main(String[] args) {
        new StartMenuController();
    }
}

