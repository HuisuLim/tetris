package play_screen.blocks.standard;

import play_screen.blocks.Block;

public class LBlock extends Block {

    public LBlock() {
        this(1);
    }
    public LBlock(int colorNum) {
        super(colorNum);
    }
    protected void setShape() {
        int c = blockNum;
        shape = new int[][] {
                {0,0,c},
                {c,c,c},
                {0,0,0}
        };
    }

}
