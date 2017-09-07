package com.abbott.sort;

import com.abbott.annotation.Timer;
import com.abbott.utils.TimerUtil;

/**
 * Created by jinyb on 2017/9/5.
 * <p>
 * 1、在数据集之中，选择一个元素作为”基准”（pivot）
 * 2、所有小于”基准”的元素，都移到”基准”的左边；所有大于”基准”的元素，都移到”基准”的右边。
 * 这个操作称为分区 (partition) 操作，分区操作结束后，基准元素所处的位置就是最终排序后它的位置。
 * <p>
 * 3、对”基准”左边和右边的两个子集，不断重复第一步和第二步，直到所有子集只剩下一个元素为止。
 */
public class QuickSort extends Sort {


    @Timer
    public void quickSort() {
        sort(arr, 0, arr.length - 1);
        print();
    }

    /**
     * 实现对分区的排序操作
     *
     * @param arr
     * @param left
     * @param right
     * @return
     */
    int partition(int[] arr, int left, int right) {
        int storeIndex = left;
        int pivot = arr[right];//直接选择右边的数据作为pivot.这个可以随机选择。

        for (int i = left; i < right; i++) {
            if (pivot >= arr[i]) {
                swapLeft2Right(arr, storeIndex, i);
                storeIndex++;
            }


        }
        //遍历完后，替换right的数据
        swapLeft2Right(arr, right, storeIndex);

        return storeIndex;
    }

    void sort(int[] arr, int left, int right) {
        if (left > right) {
            return;
        }
        int storeIndex = partition(arr, left, right);

        sort(arr, left, storeIndex - 1);
        sort(arr, storeIndex + 1, right);

    }


    public static void main(String[] args) {

        TimerUtil timerUtil = new TimerUtil();
        timerUtil.getTime();
    }
}
