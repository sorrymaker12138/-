package com.hspedu.search;

import java.util.Comparator;
import java.util.LinkedList;

public class InsertValueSearch {//插值查找
    public static void main(String[] args) {
        /*int[] arr = new int[100];
        for(int i = 0; i < arr.length; i++){
            arr[i] = i + 1;
        }*/
        int[] arr = {1,2,5,9,15,16,19,51,89,999,3050,3060,3090};
        int resIndex = insertValueSearch(arr,0, arr.length - 1, 51);
        System.out.println(resIndex);
    }
    public static int insertValueSearch(int[] arr,int left,int right,int findVal) {
        System.out.println("差值查找次数~");
        if(left > right || findVal < arr[0] || findVal > arr[arr.length - 1]){
            return -1;
        }

        int mid = left + (right - left)*(findVal - arr[left])/(arr[right] - arr[left]);
        int midVal = arr[mid];
        if(findVal < midVal){
            return insertValueSearch(arr,0,mid - 1,findVal);
        }else if(findVal > midVal){
            return insertValueSearch(arr,mid + 1,right,findVal);
        }else{
            return mid;
        }
    }
}
