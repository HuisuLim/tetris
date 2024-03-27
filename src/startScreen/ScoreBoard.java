package startScreen;

import Settings.LoadData;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class ScoreBoard extends JFrame {
    private int screenRatio = StartMenu.screenRatio; //화면 비율 조절
    public ScoreBoard() {
        setTitle("스코어보드");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500*screenRatio, 800*screenRatio);
        setLocationRelativeTo(null); // 화면 중앙에 표시

        // 스코어보드 파일을 읽어서 텍스트 영역에 표시
        JTextArea textArea = new JTextArea();
        try {
            File file = new File("scoreboard.txt"); // 스코어보드 파일 경로
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                textArea.append(line + "\n");
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 뒤로가기 버튼 추가
        JButton backButton = new JButton("뒤로가기");
        backButton.addActionListener(e -> {
            this.setVisible(false);
            this.dispose();
            new StartMenu().setVisible(true); // StartMenu 인스턴스를 새로 생성하여 보여줌
        });

        // 스코어보드 텍스트 영역을 스크롤 가능하도록 JScrollPane에 추가
        JScrollPane scrollPane = new JScrollPane(textArea);

        // 프레임에 스크롤 패널 추가
        add(scrollPane);
        // 레이아웃 설정 및 컴포넌트 추가
        setLayout(new BorderLayout()); // BorderLayout을 명시적으로 설정
        add(scrollPane, BorderLayout.CENTER); // 스크롤 패널을 중앙에 배치
        add(backButton, BorderLayout.SOUTH); // 뒤로가기 버튼을 하단에 배치


    }
}