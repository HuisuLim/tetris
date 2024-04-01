package play_screen.blocks.standard;

import play_screen.blocks.Block;

public class SBlock extends Block {

    public SBlock() {
        this(1);
    }
    public SBlock(int colorNum) {
        super(colorNum);
    }
    protected void setShape() {
        int c = colorNum;
        shape = new int[][][] {
                {
                        {0,c,c},
                        {c,c,0},
                        {0,0,0}
                },
                {
                        {0,c,0},
                        {0,c,c},
                        {0,0,c}
                },
                {
                        {0,0,0},
                        {0,c,c},
                        {c,c,0}
                },
                {
                        {c,0,0},
                        {c,c,0},
                        {0,c,0}
                },
        };
    }

}
