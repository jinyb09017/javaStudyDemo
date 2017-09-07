package com.abbott.sort;

import com.abbott.annotation.Timer;
import com.abbott.utils.TimerUtil;

/**
 * Created by jinyb on 2017/9/5.
 *
 * 先将整个待排元素序列分割成若干子序列（由相隔某个“增量”的元素组成的）分别进行直接插入排序，然后依次缩减增量再进行排
 *
 * 序，待整个序列中的元素基本有序（增量足够小）时，再对全体元素进行一次直接插入排序（增量为1）。其时间复杂度为O(n^3/2),要好于直接
 *
 * 插入排序的O(n^2)
 */
public class ShellSort extends Sort {

    int gap = (int) Math.floor(arr.length / 2);

    @Timer
    public void ShellSort() {

        while (gap > 0) {
            for (int i = gap; i < arr.length; i++) {
                for (int j = i; j - gap >= 0; j = j - gap) {

                    if (arr[j - gap] > arr[j]) {
                        swap(arr, j - gap, j);
                    }else{
                        //这是因为前面已经是有序的了。
                        break;
                    }
                }
            }

            gap = (int) Math.floor(gap / 2);
            System.out.println("gap = " + gap);
        }


        print();
    }

    public static void main(String[] args) {

        TimerUtil timerUtil = new TimerUtil();
        timerUtil.getTime();
    }
}
