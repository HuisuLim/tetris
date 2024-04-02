package startscreen;
import javax.swing.*;


public class StartMenu_main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                StartMenu menu = new StartMenu();
                menu.setVisible(true);
            }
        });
    }
}
