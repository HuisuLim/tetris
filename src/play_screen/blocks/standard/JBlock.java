package play_screen.blocks.standard;

import play_screen.blocks.Block;

public class JBlock extends Block {

    public JBlock() {
        this(1);
    }
    public JBlock(int colorNum) {
        super(colorNum);
    }
    protected void setShape() {
        int c = colorNum;
        shape = new int[][][] {
                {
                        {c,0,0},
                        {c,c,c},
                        {0,0,0}
                },
                {
                        {0,c,c},
                        {0,c,0},
                        {0,c,0}
                },
                {
                        {0,0,0},
                        {c,c,c},
                        {0,0,c}
                },
                {
                        {0,c,0},
                        {0,c,0},
                        {c,c,0}
                },
        };
    }

}
