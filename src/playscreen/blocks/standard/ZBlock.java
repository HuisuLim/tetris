package playscreen.blocks.standard;

import playscreen.blocks.Block;

public class ZBlock extends Block {

//    public ZBlock() {
//        this(1);
//    }
    public ZBlock(int colorNum) {
        super(colorNum);
    }
    protected void setShape() {
        int c = blockNum;
        shape = new int[][] {
                {c,c,0},
                {0,c,c},
                {0,0,0}
        };
    }

}
