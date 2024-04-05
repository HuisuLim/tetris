package playscreen.blocks.item;

import playscreen.blocks.Block;
import playscreen.blocks.BlockGenerator;

import java.util.Random;

public class ItemBlock extends Block {

    public ItemBlock() {
        this(1);
    }
    public ItemBlock(int blockNum) {
        super(blockNum);

    }

    @Override
    protected void setShape() {
        shape = new BlockGenerator().getRandomStandardBlock().getShape();
        randomInsert(shape, blockNum);
    }

    public static void randomInsert(int[][] arr, int item) {
        Random rand = new Random();
        int randomNumber = rand.nextInt(4); // 0부터 3 사이의 랜덤한 숫자 선택

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] != 0) {
                    if (randomNumber == 0) { // 랜덤 숫자가 0이면 현재 위치에 item 할당
                        arr[i][j] = item;
                        return; // 메서드 종료
                    }
                    randomNumber--; // 랜덤 숫자 감소
                }
            }
        }
    }
}
