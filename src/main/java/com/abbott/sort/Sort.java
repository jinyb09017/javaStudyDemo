package com.abbott.sort;

/**
 * Created by jinyb on 2017/9/4.
 */
public class Sort {
    int arr[] = {12, 23, 1, 88, 34, 54, 5, 63, 22, 33, 57,10,123,789,258,55,103,895,635,521};

    /**
     * 实现将左边的值换到右边
     * @param arr
     * @param left
     * @param right
     */
    protected void swapLeft2Right(int[] arr,int left, int right) {
        int temp = arr[right];
        arr[right] = arr[left];
        arr[left] = temp;
    }

    public void print(){
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+" ");
        }
        System.out.println("");
    }
}
