package startscreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BattleDisplayWinner {
    // 두 플레이어의 점수를 받아서 승자를 표시하는 메소드
    public static void displayWinner(int player1Score, int player2Score) {
        String winnerMessage;
        if (player1Score > player2Score) {
            winnerMessage = "Player 1 win!";
        } else if (player2Score > player1Score) {
            winnerMessage = "Player 2 win! ";
        } else {
            winnerMessage = "It's a tie! ";
        }

        // Create and set up the frame
        JFrame frame = new JFrame("Winner");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create and set up the winner message label
        JLabel winnerLabel = new JLabel(winnerMessage, SwingConstants.CENTER);
        winnerLabel.setFont(new Font("Arial", Font.BOLD, 18));
        frame.getContentPane().add(winnerLabel, BorderLayout.CENTER);

        // Add key listener to the frame to listen for Escape key press event
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    // If Escape key is pressed, close the frame
                    frame.dispose();
                }
            }
        });

        // Size the frame and make it visible
        frame.setSize(400, 150);
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setVisible(true);
        // Request focus on the frame to ensure key events are captured
        frame.requestFocus();
    }

}
