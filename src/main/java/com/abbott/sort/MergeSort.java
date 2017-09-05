package com.abbott.sort;

import com.abbott.annotation.Timer;
import com.abbott.utils.TimerUtil;

/**
 * Created by jinyb on 2017/9/5.
 */
public class MergeSort extends Sort {


    @Timer
    public void mergeSort() {

        sort(arr, 0, arr.length-1);
        print();
    }

    public void sort(int[] arr, int start, int end) {
        int first = start;
        int last = end;
        //这是停止递归的条件，非常重要
        if (last - first < 1) {
            return;
        }

        int middle = (int) Math.floor((first + last) / 2);

        //进行递归
        sort(arr, first, middle);
        sort(arr, middle + 1, last);

        int f = first;
        int m = middle;
        int i, temp;

        while (f <= m && m + 1 <= last) {
            if (arr[f] >= arr[m + 1]) {
                temp = arr[m + 1];
                //这里用到了插入排序的手法。
                for (i = m; i >= f; i--) {
                    arr[i + 1] = arr[i];
                }
                arr[f] = temp;
                m++;
            } else {
                f++;
            }

            print();
        }


    }

    int fn(int n) {

        if (n == 1) {
            return 1;
        } else {
            return n * fn(n - 1);
        }
    }

    public static void main(String[] args) {

        TimerUtil timerUtil = new TimerUtil();
        timerUtil.getTime();


        System.out.println("fn(5)--" + new MergeSort().fn(5));
        System.out.println("arr length--" + new MergeSort().arr.length);
    }
}
