package com.hspedu.sort_;

import java.util.Arrays;

public class MergeSort {//归并排序
    public static void main(String[] args) {
        //int arr[] = {12,10,11};
        int arr[] = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        int[] assist = new int[arr.length];
        sort(arr,assist,0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    public static void sort(int[] arr,int[] assist,int left,int right){
        //进行安全性校验
        if(left >= right){
            return;
        }
        int mid = (left + right)/2;
        //把数组索引left~right的的部分分为两组
        sort(arr,assist,left,mid);
        sort(arr,assist,mid + 1,right);
        //将两组数据归并
        merge(arr,assist,left,mid,right);
    }

    public static void merge(int[] arr,int[] assist,int left,int mid,int right){
        //设置三个数组索引，分别用于遍历left~mid，mid+1 ~ right以及辅助数组
        int p1 = left;
        int p2 = mid + 1;
        int i = p1;
        //使用p1和p2遍历并进行比较，选择出较小的数据加入辅助数组，实现排序
        while(p1 <= mid && p2 <= right){
            if(arr[p1] < arr[p2]){
                assist[i++] = arr[p1++];
            }else{
                assist[i++] = arr[p2++];
            }
        }

        //若p1指针未遍历完，直接将剩余部分加入到辅助数组后面
        while(p1 <= mid){
            assist[i++] = arr[p1++];
        }
        //若p2指针未遍历完，直接将剩余部分加入到辅助数组后面
        while(p2 <= right){
            assist[i++] = arr[p2++];
        }

        //将辅助数组复制到原数组中(将assist数组 索引left开始 复制到 arr数组的 left索引，复制长度为right-left+1)
        System.arraycopy(assist,left,arr,left,right - left + 1);
    }
}
