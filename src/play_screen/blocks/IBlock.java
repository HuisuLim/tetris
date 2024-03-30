package play_screen.blocks;

public class IBlock extends Block{

    public IBlock() {
        this(1);
    }
    public IBlock(int colorNum) {
        super(colorNum);
    }
    protected void setShape() {
        int c = colorNum;
        shape = new int[][][] {
                {
                        {0,0,0,0},
                        {c,c,c,c},
                        {0,0,0,0},
                        {0,0,0,0}
                },
                {
                        {0,c,0,0},
                        {0,c,0,0},
                        {0,c,0,0},
                        {0,c,0,0}
                }
        };
    }

}
