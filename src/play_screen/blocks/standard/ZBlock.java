package play_screen.blocks.standard;

import play_screen.blocks.Block;

public class ZBlock extends Block {

    public ZBlock() {
        this(1);
    }
    public ZBlock(int colorNum) {
        super(colorNum);
    }
    protected void setShape() {
        int c = colorNum;
        shape = new int[][][] {
                {
                        {c,c,0},
                        {0,c,c},
                        {0,0,0}
                },
                {
                        {0,0,c},
                        {0,c,c},
                        {0,c,0}
                },
                {
                        {0,0,0},
                        {c,c,0},
                        {0,c,c}
                },
                {
                        {0,c,0},
                        {c,c,0},
                        {c,0,0}
                },
        };
    }

}
