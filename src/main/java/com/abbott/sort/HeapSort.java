package com.abbott.sort;

import com.abbott.annotation.Timer;
import com.abbott.utils.TimerUtil;

/**
 * Created by jinyb on 2017/9/5.
 * <p>
 * 将一个记录插入到已经排好序的有序表中, 从而得到一个新的,记录数增1的有序表
 * 时间复杂度也为O(n^2), 比冒泡法和选择排序的性能要更好一些
 *
 * http://bubkoo.com/2014/01/14/sort-algorithm/heap-sort/
 *
 *
 * 堆（二叉堆）可以视为一棵完全的二叉树，完全二叉树的一个“优秀”的性质是，除了最底层之外，
 * 每一层都是满的，这使得堆可以利用数组来表示（普通的一般的二叉树通常用链表作为基本容器表示），
 * 每一个结点对应数组中的一个元素。
 *
 *
 *
 */
public class HeapSort extends Sort {


    @Timer
    public void heapSort() {

        buildMaxHeap();
        for (int index = arr.length - 1; index >= 0; index--) {
            swap(arr, 0, index);
            maxHeapify(arr, 0, index);
        }
        print();
    }

    @Timer
    public void heapSort2() {

        for (int index = arr.length ; index >= 1; index--) {

            buildMaxHeap2(0, index);
            swap(arr, 0, index-1);

        }
        print();
    }


    /**
     * @param arr
     * @param i
     * @param size
     */
    public void maxHeapify(int[] arr, int i, int size) {
        int right = (i + 1) * 2;
        int left = i * 2 + 1;

        int iMax = i;

        if (left < size && arr[left] > arr[iMax]) {
            iMax = left;
        }

        if (right < size && arr[right] > arr[iMax]) {
            iMax = right;
        }

        if (iMax != i) {
            swap(arr, iMax, i);
            //向下递归，实现最大堆
            maxHeapify(arr, iMax, size);
        }
    }


    /**
     * 构建一个最大堆
     * 最大堆中的最大元素值出现在根结点（堆顶）
     * 堆中每个父节点的元素值都大于等于其孩子结点（如果存在）
     */
    public void buildMaxHeap() {
        int parent = (int) (Math.floor(arr.length - 1) / 2);
        for (int i = parent; i >= 0; i--) {
            maxHeapify(arr, i, arr.length);
        }
    }

    /**
     * 递归实现最大堆
     */
    public void buildMaxHeap2(int index, int size) {
        int right = (index + 1) * 2;
        int left = index * 2 + 1;

        //结束递归的条件
        if (left >= size && right >= size) {
            return;
        } else if (left < size && right >= size) {
            buildMaxHeap2(left, size);

            if (arr[left] > arr[index]) {
                swap(arr, left, index);
            }


        } else if (left < size && right < size) {
            buildMaxHeap2(left, size);
            buildMaxHeap2(right, size);

            int iMax = index;
            if (arr[left] > arr[index]) {
                iMax = left;
            }

            if (arr[right] > arr[iMax]) {
                iMax = right;
            }

            if (iMax != index) {
                swap(arr, iMax, index);
            }
        }


    }

    @Timer
    public void test() {
        if (4 > 3 && getTest()) {
            System.out.println("haha");
        } else {
            System.out.println("test was false");
        }
    }


    public static void main(String[] args) {
        System.out.println("size = " + new HeapSort().arr.length);
        TimerUtil timerUtil = new TimerUtil();
        timerUtil.getTime();
    }

    public boolean getTest() {
        System.out.println("get Test was execute");
        return false;
    }
}
