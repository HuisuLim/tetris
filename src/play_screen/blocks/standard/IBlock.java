package play_screen.blocks.standard;

import play_screen.blocks.Block;

public class IBlock extends Block {

    public IBlock() {
        this(1);
    }
    public IBlock(int colorNum) {
        super(colorNum);
        super.startPos[0] = -2;
        super.startPos[1] = 2;
    }
    protected void setShape() {
        int c = colorNum;
        shape = new int[][] {
                {0,0,0,0,0},
                {0,0,0,0,0},
                {0,c,c,c,c},
                {0,0,0,0,0},
                {0,0,0,0,0}
        };
    }

}
