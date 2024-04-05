package play_screen.blocks.standard;

import play_screen.blocks.Block;

public class TBlock extends Block {

    public TBlock() {
        this(1);
    }
    public TBlock(int colorNum) {
        super(colorNum);
    }
    protected void setShape() {
        int c = blockNum;
        shape = new int[][] {
                {0,c,0},
                {c,c,c},
                {0,0,0}
        };
    }

}
