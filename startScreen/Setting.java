package startScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Setting extends JFrame {
    public Setting() {
        setTitle("게임 설정");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 800);
        setLocationRelativeTo(null); // 화면 중앙에 표시
        setLayout(null); // Layout Manager를 사용하지 않음

        // 버튼 너비 및 높이 설정
        int buttonWidth = 200;
        int buttonHeight = 50;

        // 화면 크기
        int frameWidth = getWidth();
        int frameHeight = getHeight();

        // 뒤로가기 버튼 생성 및 설정
        JButton backButton = new JButton("뒤로가기");
        backButton.setBounds(10, 10, buttonWidth, buttonHeight); // 화면 상단 왼쪽에 배치
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false); // 현재 창 숨기기
                dispose(); // 현재 창 자원 해제
                new StartMenu().setVisible(true); // StartMenu 인스턴스 생성 및 보이기
            }
        });
        add(backButton); // 뒤로가기 버튼을 프레임에 추가

        // 버튼 생성 및 설정
        JButton button1 = new JButton("조작키 설정");
        button1.setBounds((frameWidth - buttonWidth) / 2, 50, buttonWidth, buttonHeight); // 화면 가운데를 기준으로 행 정렬
        button1.setBackground(Color.RED); // 첫 번째 버튼의 색상을 빨간색으로 설정
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "조작키 설정 기능 수행");
            }
        });

        JButton button2 = new JButton("색맹 모드");
        button2.setBounds((frameWidth - buttonWidth) / 2, 120, buttonWidth, buttonHeight);
        button2.setBackground(Color.GREEN); // 두 번째 버튼의 색상을 초록색으로 설정
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "색맹 모드 기능 수행");
            }
        });

        JButton button3 = new JButton("설정 초기화");
        button3.setBounds((frameWidth - buttonWidth) / 2, 190, buttonWidth, buttonHeight);
        button3.setBackground(Color.BLUE); // 세 번째 버튼의 색상을 파란색으로 설정
        button3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "설정 초기화 기능 수행");
            }
        });

        JButton button4 = new JButton("화면 크기 조절");
        button4.setBounds((frameWidth - buttonWidth) / 2, 260, buttonWidth, buttonHeight);
        button4.setBackground(Color.YELLOW); // 네 번째 버튼의 색상을 노란색으로 설정
        button4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "화면 크기 조절 기능 수행");
            }
        });

        JButton button5 = new JButton("스코어보드 초기화");
        button5.setBounds((frameWidth - buttonWidth) / 2, 330, buttonWidth, buttonHeight);
        button5.setBackground(Color.ORANGE); // 다섯 번째 버튼의 색상을 주황색으로 설정
        button5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "스코어보드 초기화 기능 수행");
            }
        });

        // 버튼을 프레임에 추가
        add(button1);
        add(button2);
        add(button3);
        add(button4);
        add(button5);
    }
}

