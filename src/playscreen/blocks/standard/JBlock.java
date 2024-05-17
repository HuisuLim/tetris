package playscreen.blocks.standard;

import playscreen.blocks.Block;

public class JBlock extends Block {

//    public JBlock() {
//        this(1);
//    }
    public JBlock(int colorNum) {
        super(colorNum);
    }
    protected void setShape() {
        int c = blockNum;
        shape = new int[][] {
                {c,0,0},
                {c,c,c},
                {0,0,0}
        };
    }

}
