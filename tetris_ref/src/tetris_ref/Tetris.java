package tetris_ref;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * 테트리스 게임의 메인 클래스입니다. 이 클래스에는 독립적으로 실행되는 애플리케이션 또는 웹 페이지 내의 애플릿으로 게임을 실행하기 위한 필요한 메서드가 포함되어 있습니다.
 */
public class Tetris {

    /**
     * 애플릿에서 실행되는 테트리스 게임입니다.
     */
    private Game game = null;

    /**
     * 독립 실행형 메인 루틴입니다.
     *
     * @param args 명령 줄 인수
     */
    public static void main(String[] args) {
        System.out.println("starting");
        Frame frame = new Frame("Tetris");
        final Game game = new Game();

        game.addPropertyChangeListener(new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent evt) {
                System.out.println("PCE " + evt.getPropertyName() + " " + evt.getNewValue());
            }
        });

        final TextArea taHiScores = new TextArea("", 10, 10, TextArea.SCROLLBARS_NONE);

        taHiScores.setBackground(Color.black);
        taHiScores.setForeground(Color.white);
        taHiScores.setFont(new Font("monospaced", 0, 11));
        taHiScores.setText(" 득점자 점수판                  \n" +
                " -----------------------------\n\n" +
                " PLAYER     LEVEL    SCORE    \n\n" +
                " 김씨         12 1  50280     \n" +
                " 양씨         12 1  50280     \n"
        );
        taHiScores.setEditable(false);

        final TextField txt = new TextField();
        txt.setEnabled(false);

        game.addPropertyChangeListener(new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent evt) {
                if (evt.getPropertyName().equals("state")) {
                    int state = ((Integer) evt.getNewValue()).intValue();
                    if (state == Game.STATE_GAMEOVER) {
                        txt.setEnabled(true);
                        txt.requestFocus();
                        txt.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                txt.setEnabled(false);
                                game.init();
                            }
                        });
                        // 점수 표시...
                    }
                }
            }
        });

        Button btnStart = new Button("Start");
        btnStart.setFocusable(false);
        btnStart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                game.start();
            }
        });

        final Container c = new Container();
        c.setLayout(new BorderLayout());
        c.add(txt, BorderLayout.NORTH);
        c.add(game.getSquareBoardComponent(), BorderLayout.CENTER);
        c.add(btnStart, BorderLayout.SOUTH);

        final Container c2 = new Container();
        c2.setLayout(new GridLayout(1, 2));
        c2.add(c);
        c2.add(taHiScores);

        frame.add(c2);

        System.out.println("packing");

        frame.pack();

        // 프레임 창 리스너 추가
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        // 프레임 보이기 (게임 시작)
        frame.show();
    }
}
