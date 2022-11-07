package com.hspedu.sort_;

import java.text.SimpleDateFormat;
import java.util.Date;

//初级排序方法
public class SortInserting {
    public static void main(String[] args) {
        int[] arr = {6, 3, 4, 2, 5, 1};
        /*int[] arr1 = new int[80000];
        for(int i = 0;i < 80000;i++){
            arr1[i] = (int)(Math.random()*8000000);
        }
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        String time1 = simpleDateFormat.format(date1);
        System.out.println("排序前的时间为：" + time1);
        sortInserting(arr1);

        Date date2 = new Date();
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        String time2 = simpleDateFormat.format(date2);
        System.out.println("排序前的时间为：" + time2);
        */

        /*sortInserting(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }*/
        SleepSort(arr);
    }

    //插入排序思想：把n个待排序的元素看作一个有序列表和一个无序列表。开始时有序列表只有一个元素，无序列表有n-1个元素
    //排序过程中，每次从无序列表取出一个元素，把他的排序码依次与有序列表中的排序码进行比较，再将他插入有序列表的恰当位置，使之成为新的有序列表
    public static void sortInserting(int[] arr) {//插入排序
        int insertVal;
        int insertIndex;
        for (int i = 0; i < arr.length - 1; i++) {
            insertIndex = i;
            insertVal = arr[i + 1];
            while (insertIndex >= 0 && arr[insertIndex] > insertVal) {
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }
            arr[insertIndex + 1] = insertVal;
        }
    }



    //选择排序思想：第一次从arr[0]~arr[n-1]个元素中选取出最小值，将其与arr[0]交换；第二次从arr[1]~arr[n-1]选取最小值与arr[1]交换..
    //运行该操作n-1次，即可得到从小到大的有序列表
    public static void sortChoice(int[] arr) {//选择排序
        int min;
        int minIndex;
        for (int i = 0; i < arr.length - 1; i++) {
            min = arr[i];
            minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[minIndex] > arr[j]) {
                    min = arr[j];
                    minIndex = j;
                }
            }
            arr[minIndex] = arr[i];
            arr[i] = min;
        }
    }


    /**
     * 沙雕排序--睡眠排序：
     * @param */
    public static void SleepSort(int[] arr){
        for(int i = 0;i < arr.length;i++){
            final int temp = arr[i];
            new Thread(()->{
                try {
                    Thread.sleep(temp);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(temp);
            }).start();
        }
    }

}