package com.hspedu.sort_;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class ShellSort {
    public static void main(String[] args) {
        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        /*int[] arr1 = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr1[i] = (int) (Math.random() * 8000000);
        }
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        String time1 = simpleDateFormat.format(date1);
        System.out.println("排序前的时间为：" + time1);
        shellsort02(arr1);

        Date date2 = new Date();
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        String time2 = simpleDateFormat.format(date2);
        System.out.println("排序前的时间为：" + time2);*/
        shellsort02(arr);
        System.out.println(Arrays.toString(arr));
    }

    //希尔排序思想：希尔排序是把记录按下标的一定增量分组，对每组使用直接插入排序算法排序；随着增量的持续减少，每组包含的关键词越来越多，当增量减3至1时
    //    整个文件被分为一组，算法于是终止
    public static void shellsort01(int[] arr) {
        int gap;
        int temp = 0;
        int max = 0;
        int maxIndex = 0;
        for (gap = arr.length; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                for (int j = i - gap; j >= 0; j -= gap) {
                    if (arr[j] > arr[j + gap]) {//交换法，易于理解但是效率较低
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
        }
        //System.out.println(Arrays.toString(arr));
    }

    public static void shellsort02(int[] arr) {
        int insertVal;
        int insertIndex;
        int j;
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                j = i;
                insertVal = arr[i];
                while (j - gap >= 0 && insertVal < arr[j - gap]) {//排序法，效率高但是不易理解
                    arr[j] = arr[j - gap];
                    j -= gap;
                }
                arr[j] = insertVal;
            }
        }
        //System.out.println(Arrays.toString(arr));
    }
}
