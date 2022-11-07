package com.hspedu.sort_;

import java.util.Arrays;

public class MergeSort02 {
    public static void main(String[] args) {
        int arr[] = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        merge(arr,0, arr.length - 1);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    public static void merge(int[] arr,int L ,int R){
        if(L >= R)
            return;
        int M = L + (R -L)/2;
        merge(arr,L,M);
        merge(arr,M + 1,R);
        mergeSort(arr,L,M,R);
    }
    public static void mergeSort(int[] arr,int L,int M,int R){
        int left_size = M - L + 1;
        int right_size = R - M;
        int left[] = new int[left_size];
        int right[] = new int[right_size];
        for (int i = L; i <= M; i++) {
            left[i - L] = arr[i];
        }
        for(int i = M + 1;i <= R;i++){
            right[i - M - 1] = arr[i];
        }
        int i = 0,j = 0,k = L;
        while(i < left_size && j < right_size){
            if(left[i] > right[j]){
                arr[k] = right[j];
                k++;j++;
            }else{
                arr[k] = left[i];
                i++;k++;
            }
        }
        while(i < left_size){
            arr[k++] = left[i++];
        }
        while(j < right_size){
            arr[k++] = right[j++];
        }
   }
}
