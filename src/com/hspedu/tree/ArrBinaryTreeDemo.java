package com.hspedu.tree;

public class ArrBinaryTreeDemo {//存储二叉树
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7};
        /*
        *    1
        *  2   3
        * 4 5 6 7
        * 1.数组第n个元素的左子结点下标为 2 * n + 1（n代表数组下标）
        * 2.数组第n个元素的左子结点下标为 2 * n + 2
        * 3.数组第n个元素的左子结点下标为 (n - 1)/2
        * 4.顺序二叉树通常只考虑完全二叉树
        * 5.n代表二叉树中第n个元素（从0开始计数）*/
        ArrayBinary arrayBinary = new ArrayBinary(arr);
        arrayBinary.postOrder(0);
    }
}


class ArrayBinary{
    private int[] arr;

    public ArrayBinary(int[] arr) {
        this.arr = arr;
    }
    public void PreOrder(){
        PreOrder(0);
    }
    //编写一个方法，实现顺序存储二叉树的前序遍历
    public void PreOrder(int index){//index代表数组下标
        if(arr == null || arr.length == 0){
            System.out.println("数组为空");
            return;
        }
        //输出当前元素
        System.out.println(arr[index]);
        if(2*index + 1 < arr.length){//向左遍历
            PreOrder(2*index + 1);
        }
        if(2*index + 2 < arr.length){//向右遍历
            PreOrder(2*index + 2);
        }
    }

    //编写一个方法，实现顺序存储二叉树的中序遍历
    public void infixOrder(int index){
        if(arr == null || arr.length == 0){
            System.out.println("数组为空");
            return;
        }
        if(2*index + 1 < arr.length){
            infixOrder(2 * index + 1);
        }
        System.out.println(arr[index]);
        if(2 * index + 2 < arr.length){
            infixOrder(2 * index + 2);
        }
    }

    //编写一个方法，实现顺序存储二叉树的后序遍历
    public void postOrder(int index){
        if(arr == null || arr.length == 0){
            System.out.println("数组为空");
            return;
        }
        if(2 * index + 1 < arr.length){
            postOrder(2 * index + 1);
        }
        if(2 * index + 2 < arr.length){
            postOrder(2 * index + 2);
        }
        System.out.println(arr[index]);
    }
}