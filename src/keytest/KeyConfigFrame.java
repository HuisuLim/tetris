//키설정 테스트. gpt한테만 물어보고 뭔내용인진 아직 안봄. 어려워보임.

package keytest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class KeyConfigFrame extends JFrame {
    private int leftKey = KeyEvent.VK_LEFT;  // 기본값
    private int rightKey = KeyEvent.VK_RIGHT;
    private int rotateKey = KeyEvent.VK_UP;
    private int downKey = KeyEvent.VK_DOWN;

    // 키 이름을 표시할 JLabel들
    private JLabel leftKeyLabel, rightKeyLabel, rotateKeyLabel, downKeyLabel;

    public KeyConfigFrame() {
        this.setTitle("키 설정 변경");
        this.setSize(400, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(4, 2, 10, 10)); // GridLayout 사용

        // 키 설정을 위한 버튼과 라벨 추가
        leftKeyLabel = new JLabel(KeyEvent.getKeyText(leftKey));
        addConfigButton("왼쪽 키 설정", "LEFT", leftKeyLabel);

        rightKeyLabel = new JLabel(KeyEvent.getKeyText(rightKey));
        addConfigButton("오른쪽 키 설정", "RIGHT", rightKeyLabel);

        rotateKeyLabel = new JLabel(KeyEvent.getKeyText(rotateKey));
        addConfigButton("회전 키 설정", "ROTATE", rotateKeyLabel);

        downKeyLabel = new JLabel(KeyEvent.getKeyText(downKey));
        addConfigButton("내리기 키 설정", "DOWN", downKeyLabel);

        this.setVisible(true);

        loadKeyConfig(); // 설정 로드
    }

    private void addConfigButton(String buttonText, String keyType, JLabel keyLabel) {
        JButton button = new JButton(buttonText);
        button.addActionListener(e -> {
            JDialog dialog = new JDialog(this, "키 입력", true);
            dialog.setSize(200, 100);
            dialog.setLayout(new BorderLayout());
            dialog.add(new JLabel("원하는 키를 누르세요."), BorderLayout.CENTER);
            dialog.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    int keyCode = e.getKeyCode();
                    switch (keyType) {
                        case "LEFT":
                            leftKey = keyCode;
                            leftKeyLabel.setText(KeyEvent.getKeyText(leftKey));
                            break;
                        case "RIGHT":
                            rightKey = keyCode;
                            rightKeyLabel.setText(KeyEvent.getKeyText(rightKey));
                            break;
                        case "ROTATE":
                            rotateKey = keyCode;
                            rotateKeyLabel.setText(KeyEvent.getKeyText(rotateKey));
                            break;
                        case "DOWN":
                            downKey = keyCode;
                            downKeyLabel.setText(KeyEvent.getKeyText(downKey));
                            break;
                    }
                    dialog.dispose();
                }
            });
            dialog.setVisible(true);
        });
        this.getContentPane().add(button);
        this.getContentPane().add(keyLabel);
    }

    public void saveKeyConfig() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("keyconfig.txt"));
            writer.write("LEFT:" + leftKey + "\n");
            writer.write("RIGHT:" + rightKey + "\n");
            writer.write("ROTATE:" + rotateKey + "\n");
            writer.write("DOWN:" + downKey);
            writer.close();
            JOptionPane.showMessageDialog(this, "키 설정이 저장되었습니다.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "설정 저장에 실패하였습니다.", "오류", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void loadKeyConfig() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("keyconfig.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    int keyCode = Integer.parseInt(parts[1]);
                    switch (parts[0]) {
                        case "LEFT":
                            leftKey = keyCode;
                            leftKeyLabel.setText(KeyEvent.getKeyText(leftKey));
                            break;
                        case "RIGHT":
                            rightKey = keyCode;
                            rightKeyLabel.setText(KeyEvent.getKeyText(rightKey));
                            break;
                        case "ROTATE":
                            rotateKey = keyCode;
                            rotateKeyLabel.setText(KeyEvent.getKeyText(rotateKey));
                            break;
                        case "DOWN":
                            downKey = keyCode;
                            downKeyLabel.setText(KeyEvent.getKeyText(downKey));
                            break;
                    }
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            // 파일이 없을 경우 기본값 사용
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "설정 로드에 실패하였습니다.", "오류", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        KeyConfigFrame frame = new KeyConfigFrame();
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.saveKeyConfig();
            }
        });
    }
}
