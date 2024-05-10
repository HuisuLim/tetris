package startscreen;

import javax.swing.*;

public class StartMenuMain {
    public static void main(String[] args) {
        try {
            // 시스템 기본 룩앤필 설정
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 이벤트 디스패치 스레드에서 UI를 시작합니다.
        SwingUtilities.invokeLater(() -> {
            new StartMenuView(); // 컨트롤러가 생성될 때 뷰도 함께 생성되고 설정됩니다.
        });
    }
}
