package playscreen;


import playscreen.panels.PlayPanel;
import playscreen.utils.SinglePlayKeyListener;
import settings.settingModel;

import javax.swing.*;

public class PlayFrame extends JFrame {

    private settingModel data = new settingModel();
    private double screenSize = data.loadScreenSize();
    public PlayPanel playPanel;
    private SinglePlayKeyListener singlePlayKeyListener;


    public PlayFrame(String gameMode) {
        setTitle("Play Frame");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false); // 창 크기 변경 불가능 설정 추가

        playPanel = new PlayPanel(gameMode);
        add(playPanel);

        singlePlayKeyListener = new SinglePlayKeyListener(this, data.loadKeys());
        addKeyListener(singlePlayKeyListener);

        pack();//화면자동설정.
        setLocationRelativeTo(null); //화면 중앙에 위치.
        setVisible(true); //창 보이게.
    }
}
