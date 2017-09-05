package com.abbott.sort;

import com.abbott.annotation.Timer;
import com.abbott.utils.TimerUtil;

/**
 * Created by jinyb on 2017/9/5.
 * 简单选择排序(simple selection sort) 就是通过n-i次关键字之间的比较,从n-i+1
 * 个记录中选择关键字最小的记录,并和第i(1<=i<=n)个记录交换之
 * 尽管与冒泡排序同为O(n^2),但简单选择排序的性能要略优于冒泡排序
 *
 * 每次遍历找到最小的
 */
public class SelectSort extends Sort {
    @Timer
    public void SelectSort() {
        for (int i = 0; i < arr.length; i++) {
            int m = i;
            for (int j = i+1; j < arr.length; j++) {
                if (arr[j] < arr[i]) {
                    m = j;
                }
            }
            swapLeft2Right(arr,i,m);


        }


        print();
    }


    public static void main(String[] args) {

        TimerUtil timerUtil = new TimerUtil();
        timerUtil.getTime();
    }
}
