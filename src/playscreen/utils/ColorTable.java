                                                                                                  package playscreen.utils;

public class ColorTable {
    private static final int[][] colorHex = {
            {0xffffff,0x00ffff,0x0000ff,0xffa500,0xffff00,0x00ff00,0x800080,0xff0000,0x000000},//TTC World Standard 색상표 (참고 : https://ko.wikipedia.org/wiki/%ED%85%8C%ED%8A%B8%EB%A6%AC%EC%8A%A4)
            {0xffffff,0xe1a102,0x56b4e8,0x009f73,0xf0e442,0x0072b1,0xd45d00,0xcc79a6,0x000000} //색약용 색상표 (참고 : https://nuli.navercorp.com/community/article/1132656)
    };//색상들을 16진수로 저장. 후에 new Color(colorHex[isColorBlindness][i]) 형식으로 이용

    public static int[] getTable(boolean colorMode) {
        if(colorMode) {
            return colorHex[1];
        }
        else {
            return colorHex[0];
        }
    }
}
