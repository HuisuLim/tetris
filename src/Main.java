import startscreen.StartMenu;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                StartMenu menu = new StartMenu();
                menu.setVisible(true);
                //fffddd
            }
        });
    }
}