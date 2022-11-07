package com.hspedu.search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BinarySearch {//二分查找
    public static void main(String[] args) {
        int[] arr = {1,2,5,9,15,19,89,89,89,999,3050,3060,3090};//11
        ArrayList<Integer> resIndex = binarySearch2(arr,0, arr.length - 1, 89);
        //int resIndex = binarySearch(arr,0, arr.length- 1, 89);
        System.out.println(resIndex);
        //int Index = binarySearch(arr,0, arr.length - 1,  3060);
        //System.out.println("Index =" + Index);
    }

    //二分查找算法
    public static int binarySearch(int[] arr,int left,int right,int findVal) {
        System.out.println("二分查找被调用~");
        //首先确定退出递归的条件
        if (left > right) {
            return -1;
        }
        int mid = (left + right) / 2;
        int midVal = arr[mid];
        if (findVal > midVal) {
            return binarySearch(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) {
            return binarySearch(arr, left, mid - 1, findVal);
        } else {
            return mid;
        }
    }

    //进行改进
    public static ArrayList<Integer> binarySearch2(int[] arr,int left,int right,int findVal) {
        //首先确定退出递归的条件
        if (left > right) {
            return null;
        }
        int mid = (left + right) / 2;
        int midVal = arr[mid];
        if (findVal > midVal) {
            return binarySearch2(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) {
            return binarySearch2(arr, left, mid - 1, findVal);
        } else {
            /**
             * 思路：
             * 1.找到mid值，先不忙返回
             * 2.向mid索引值的左边扫描，将所有满足条件的下标加入到集合中去
             * 3.向mid索引值的右边扫描，将所有满足条件的下标加入到集合中去
             * 4.返回集合*/
            ArrayList<Integer> IndexList = new ArrayList<>();
            int temp = mid - 1;
            while(true){
                if(temp < 0 || arr[temp] != arr[mid]){
                    break;
                }
                IndexList.add(temp);
                temp--;
            }
            IndexList.add(mid);
            temp = mid + 1;
            while(true){
                if(temp > right || arr[temp] != arr[mid]){
                    break;
                }
                IndexList.add(temp);
                temp++;
            }
            return IndexList;
        }
    }
}
