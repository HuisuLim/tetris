package play_screen.blocks;

public class TBlock extends Block{

    public TBlock() {
        this(1);
    }
    public TBlock(int colorNum) {
        super(colorNum);
    }
    protected void setShape() {
        int c = colorNum;
        shape = new int[][][] {
                {
                        {0,c,0},
                        {c,c,c},
                        {0,0,0}
                },
                {
                        {0,c,0},
                        {0,c,c},
                        {0,c,0}
                },
                {
                        {0,0,0},
                        {c,c,c},
                        {0,c,0}
                },
                {
                        {0,c,0},
                        {c,c,0},
                        {0,c,0}
                },
        };
    }

}
