package play_screen.blocks.item;

import play_screen.blocks.Block;

public class WeightBlock extends Block {
    public WeightBlock() {
        this(1);
    }
    public WeightBlock(int blockNum) {
        super(blockNum);
    }
    @Override
    protected void setShape() {
        int c = blockNum;
        shape = new int[][] {
                {0,c,c,0},
                {c,c,c,c}
        };
    }

    @Override
    public int[][] getRotatedShape() {
        return shape;
    }

    @Override
    public void rotate90() {

    }
}

