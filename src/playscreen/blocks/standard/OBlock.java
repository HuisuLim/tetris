package playscreen.blocks.standard;

import playscreen.blocks.Block;

public class OBlock extends Block {

//    public OBlock() {
//        this(1);
//    }
    public OBlock(int colorNum) {
        super(colorNum);
    }
    protected void setShape() {
        int c = blockNum;
        shape = new int[][] {
                {c,c},
                {c,c}
        };
    }

}
