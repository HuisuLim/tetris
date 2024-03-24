package tetrisPlay;
import java.awt.*;
import java.util.Random;

public class Blocks {

    public int[][][] iBlock = {
            {
                    {0,0,0,0},
                    {1,1,1,1},
                    {0,0,0,0},
                    {0,0,0,0}
            },
            {
                    {0,1,0,0},
                    {0,1,0,0},
                    {0,1,0,0},
                    {0,1,0,0}
            }
    };
    public int[][][] jBlock = new int[4][3][3];
    public int[][][] lBlock = new int[4][3][3];
    public int[][][] oBlock = {
            {
                    {4,4},
                    {4,4}
            }
    };
    public int[][][] sBlock = new int[4][3][3];
    public int[][][] tBlock = new int[4][3][3];
    public int[][][] zBlock = new int[4][3][3];

    private void setBlocks() {
        jBlock[0] = new int[][] {
                {2,0,0},
                {2,2,2},
                {0,0,0}
        };
        lBlock[0] = new int[][] {
                {0,0,3},
                {3,3,3},
                {0,0,0}
        };
        sBlock[0] = new int[][] {
                {0,5,5},
                {5,5,0},
                {0,0,0}
        };
        tBlock[0] = new int[][] {
                {0,6,0},
                {6,6,6},
                {0,0,0}
        };
        zBlock[0] = new int[][] {
                {7,7,0},
                {0,7,7},
                {0,0,0}
        };
        for(int i = 0; i < 3; i++) {
            rotate90(jBlock[i], jBlock[i+1]);
            rotate90(lBlock[i], lBlock[i+1]);
            rotate90(sBlock[i], sBlock[i+1]);
            rotate90(tBlock[i], tBlock[i+1]);
            rotate90(zBlock[i], zBlock[i+1]);
        }

    }

    public static void rotate90(int[][] a, int[][] b) {
        int n = a.length; // 배열의 길이를 가정합니다. a와 b는 n * n 배열이라고 가정합니다.

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // 90도 회전된 위치로 원소 이동
                // a[i][j]의 새 위치는 b[j][n-1-i]
                b[j][n-1-i] = a[i][j];
            }
        }
    }

    public int[][][] getRandomBlock() {
        int[][][] temp = {{{0}}};
        Random random = new Random();
        return switch (random.nextInt(7) + 1) {
            case 1 -> iBlock;
            case 2 -> jBlock;
            case 3 -> lBlock;
            case 4 -> oBlock;
            case 5 -> sBlock;
            case 6 -> tBlock;
            case 7 -> zBlock;
            default -> temp;
        };
    }

    public Blocks() {
        this.setBlocks();
    }

}
