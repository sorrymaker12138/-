package com.hspedu.tree.heapsort;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HeapSort {//堆排序
    public static void main(String[] args) {
        //      4
        //    6   8
        //   5 9
        //int arr[] = {2,5,3,1,10,4};
        int[] arr = new int[8000000];
        for (int i = 0; i < 8000000; i++) {
            arr[i] = (int) (Math.random() * 8000000);
        }
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        String time1 = simpleDateFormat.format(date1);
        System.out.println("排序前的时间为：" + time1);
        heapSort(arr,arr.length);
        /*for(int i = 0;i < arr.length;i++){
            System.out.println(arr[i]);
        }*/
        System.out.println("排序后的时间：" + simpleDateFormat.format(date1));
    }

    /**
     * 堆排序的基本思想是：
     * 1.将代排序序列构造成一个大顶堆（整个过程本质上 就是对数组进行操作，按树的形式进行操作）
     * 2.此时，整个数列最大的值就是堆顶的根结点
     * 3.将其与末尾元素进行交换，此时末尾就为最大值 并把末尾元素排除序列
     * 4.然后将剩余n-1个元素重新构造成一个堆，这样就会得到n的元素的次小值。如此反复执行，便能的到一个升序序列了*/
    //编写一个堆排序的方法
    public static void heapSort(int arr[],int length){
        BuildSort(arr,length);
        for(int i = length - 1;i >= 0;i--){
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            heapify(arr,0,i);
        }
    }

    public static void BuildSort(int arr[],int length){
        int IndexHeap = length - 1;//代表序列的最大下标
        int maxIndex = (IndexHeap - 1)/2;//代表 最大的 非叶子结点的索引。从该元素向前依次进行 heapify
        for(int i = maxIndex;i >= 0;i--){
            heapify(arr,i,length);
        }
    }

    //将一个数组(二叉树) 调整成一个大顶堆
    /**
     *功能：将以 i 为对应的非子叶结点调整成大顶堆
     * 示例：{4,6,8,5,9} ->(i= 1)-> adjustHeap -> {4,9,8,5,6}
     *  我们再次调用 adjustHeap (i = 0) -> {4,9,8,5,6} -> {9,6,8,5,4} == 大顶堆
     * @param arr 待调整的数组
     * @param i 表示非叶子 结点的对应 索引
     * @param length 表示对多少个元素进行调整，length在不断地减少
     * */
    public static void heapify(int arr[],int i,int length){
        //设置推出递归条件
        if(i >= length){
            return;
        }
        int c1 = 2*i + 1;//左子结点
        int c2 = 2*i + 2;//右子节点
        int max = i;
        if(c1 < length && arr[c1] > arr[max]){
            max = c1;
        }
        if(c2 < length && arr[c2] > arr[max]){
            max = c2;
        }
        if(max != i){//如果左右子结点 有存在值大于父节点的将父子结点交换，并对交换后的子结点进行递归heapify
            int temp = arr[max];
            arr[max] = arr[i];
            arr[i] = temp;
            //向下递归
            heapify(arr,max,length);
        }
   }
}
