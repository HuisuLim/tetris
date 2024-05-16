package playscreen.blocks.standard;

import playscreen.blocks.Block;

public class SBlock extends Block {

//    public SBlock() {
//        this(1);
//    }
    public SBlock(int colorNum) {
        super(colorNum);
    }
    protected void setShape() {
        int c = blockNum;
        shape = new int[][] {
                {0,c,c},
                {c,c,0},
                {0,0,0}
        };
    }

}
