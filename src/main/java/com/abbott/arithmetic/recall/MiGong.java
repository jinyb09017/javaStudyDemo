package com.abbott.arithmetic.recall;

/**
 * http://blog.csdn.net/tianyaleixiaowu/article/details/50948031
 * 通过回溯法来实现找出所有迷宫出路。
 *
 *     该图是一个迷宫的图。1代表是墙不能走，0是可以走的路线。只能往上下左右走，直到从左上角到右下角出口。
 *     做法是用一个二维数组来定义迷宫的初始状态，然后从左上角开始，不停的去试探所有可行的路线，碰到1就结束本次路径，
 *     然后探索其他的方向，当然我们要标记一下已经走的路线，不能反复的在两个可行的格子之间来回走。直到走到出口为止，算找到了一个正确路径。
 *
 *     迷宫可以通过一个二维数组来表示。
 */
public class MiGong {
    /**
     * 定义迷宫数组
     */
    private int[][] array = {
            {0, 0, 1, 0, 0, 0, 1, 0},
            {0, 0, 1, 0, 0, 0, 1, 0},
            {0, 0, 1, 0, 1, 1, 0, 1},
            {0, 1, 1, 1, 0, 0, 1, 0},
            {0, 0, 0, 1, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 1, 0, 1},
            {0, 1, 1, 1, 1, 0, 0, 1},
            {1, 1, 0, 0, 0, 1, 0, 1},
            {1, 1, 0, 0, 0, 0, 0, 0}

    };
    private int maxLine = 8;
    private int maxRow = 9;

    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());
        new MiGong().check(0, 0);
        System.out.println(System.currentTimeMillis());
    }

    private void check(int i, int j) {
        //如果到达右下角出口
        if (i == maxRow - 1 && j == maxLine - 1) {
            print();
            return;
        }

        //向右走
        if (canMove(i, j, i, j + 1)) {
            array[i][j] = 5;
            check(i, j + 1);
            array[i][j] = 0;
        }
        //向左走
        if (canMove(i, j, i, j - 1)) {
            array[i][j] = 5;
            check(i, j - 1);
            array[i][j] = 0;
        }
        //向下走
        if (canMove(i, j, i + 1, j)) {
            array[i][j] = 5;
            check(i + 1, j);
            array[i][j] = 0;
        }
        //向上走
        if (canMove(i, j, i - 1, j)) {
            array[i][j] = 5;
            check(i - 1, j);
            array[i][j] = 0;
        }
    }

    private boolean canMove(int i, int j, int targetI, int targetJ) {
        System.out.println("从第" + (i + 1) + "行第" + (j + 1) + "列，走到第" + (targetI + 1) + "行第" + (targetJ + 1) + "列");
        if (targetI < 0 || targetJ < 0 || targetI >= maxRow || targetJ >= maxLine) {
            System.out.println("到达最左边或最右边，失败了");
            return false;
        }
        if (array[targetI][targetJ] == 1) {
            System.out.println("目标是墙，失败了");
            return false;
        }
        //避免在两个空格间来回走
        if (array[targetI][targetJ] == 5) {
            System.out.println("来回走，失败了");
            return false;
        }

        return true;
    }

    private void print() {
        System.out.println("得到一个解：");
        for (int i = 0; i < maxRow; i++) {
            for (int j = 0; j < maxLine; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
    }
}
