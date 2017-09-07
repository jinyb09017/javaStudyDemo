package com.abbott.sort;

import com.abbott.annotation.Timer;
import com.abbott.utils.TimerUtil;

/**
 * Created by jinyb on 2017/9/4.
 * <p>
 * 基本思想是:两两比较相邻记录的关键字,如果反序则交换
 * 冒泡排序时间复杂度最好的情况为O(n),最坏的情况是O(n^2)
 * <p>
 * 改进思路1：设置标志位，明显如果有一趟没有发生交换（flag = false)，说明排序已经完成
 * 改进思路2：记录一轮下来标记的最后位置，下次从头部遍历到这个位置就Ok
 */
public class Buddle extends Sort {

    @Timer
    private void sort() {
        for (int i = 0; i < arr.length; i++) {

            for (int j = 1; j < arr.length - i; j++) {
                if (arr[j - 1] > arr[j]) {
                    swap(arr, j - 1, j);
                }
            }

        }

        print();
    }


    @Timer
    private void sortOptimize1() {
        boolean Change = true;
        while (Change) {

            for (int j = 1; j < arr.length - j; j++) {
                Change = false;
                if (arr[j - 1] > arr[j]) {
                    Change = true;
                    swap(arr, j - 1, j);
                }
            }


        }


        print();
    }

    @Timer
    private void sortOptimize2() {
        int flag = arr.length;
        while (flag > 0) {
            int last = flag;
            flag = 0;//这个是退出条件呀
            for (int i = 1; i < last; i++) {


                if (arr[i - 1] > arr[i]) {
                    //缩小循环的范围
                    flag = i;
                    swap(arr, i - 1, i);
                }


            }
        }


        print();
    }


    public static void main(String[] args) {

        TimerUtil timerUtil = new TimerUtil();
        timerUtil.getTime();
    }
}
