package startscreen;

import javax.swing.*;

public class StartMenuMain {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            StartMenuController menuController = new StartMenuController();
            menuController.getView().setVisible(true); // 컨트롤러를 통해 뷰를 얻어 표시
        });
    }
}
