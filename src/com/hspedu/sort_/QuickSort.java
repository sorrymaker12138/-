package com.hspedu.sort_;

import java.util.Arrays;

public class QuickSort {//快速排序
    public static void main(String[] args) {
        int[] arr = {8,4,5,7,1,3,6,2};
        sort(arr,0,arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }
    //对数组内元素进行排序
    public static void sort(int[] arr){
        int left = 0;
        int right = arr.length - 1;
        sort(arr,left,right);
    }

    //对数组内索引left~right的元素进行排序
    public static void sort(int[] arr,int left,int right){
        //安全性校验
        if(left >= right){
            return;
        }
        //对数组中索引left到right的元素进行分组（左子组和右子组）：
        int partition = partition(arr,left,right);
        //让左子组有序
        sort(arr,left,partition - 1);
        //让右子组有序
        sort(arr,partition + 1,right);
    }

    //对数组arr中，从索引left到right之间的元素进行排序，并返回对应分组界限的索引
    public static int partition(int[] arr,int left,int right){
        int temp;
        //确定分界值
        int key = arr[left];
        //定义两个指针，分别指向待切分数组的最小索引和 最大索引的下一个位置
        int p1 = left;
        int p2 = right + 1;

        //切分
        while(true){
            //先从右往左扫描，移动p2指针，找到比分界值小的值就停止
            while(key < arr[--p2]){
                if(p2 == left){
                    break;
                }
            }
            //再从左往右扫描，移动p1指针，找到比分界值大的值就停止
            while(key > arr[++p1]){
                if(p1 == right){
                    break;
                }
            }
            if(p1 >= p2){//判断退出条件
                break;
            }else{
                temp = arr[p1];
                arr[p1] = arr[p2];
                arr[p2] = temp;
            }
        }
        temp = arr[p2];
        arr[p2] = arr[left];
        arr[left] = temp;

        return p2;
    }
}
