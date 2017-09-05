package com.abbott.sort;

import com.abbott.annotation.Timer;
import com.abbott.utils.TimerUtil;

/**
 * Created by jinyb on 2017/9/5.
 *
 * 将一个记录插入到已经排好序的有序表中, 从而得到一个新的,记录数增1的有序表
 * 时间复杂度也为O(n^2), 比冒泡法和选择排序的性能要更好一些
 *
 */
public class InsertSort extends Sort {


    @Timer
    public void insertSort() {
        int i, j;
        for (i = 1; i < arr.length; i++) {
            int temp = arr[i];

            for (j = i; j > 0 && arr[j - 1] > temp; j--) {
                arr[j] = arr[j - 1];
            }

            arr[j] = temp;


        }
        print();
    }


    public static void main(String[] args) {

        TimerUtil timerUtil = new TimerUtil();
        timerUtil.getTime();
    }
}
