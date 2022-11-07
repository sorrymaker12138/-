package com.hspedu.recursion;

public class Queen8{
    static final int  MIX = 8;
    int[] arr = new int[MIX];
    int num = 0;
    public static void main(String[] args) {
        new Queen8().check(0);
    }

    public void check(int n){//放置第 n+1 个皇后
        if(n >= MIX){
            print();
            return;
        }
        for(int i = 0;i < MIX;i++){
            arr[n] = i;
            if(judge(n)){//即第 n+1 个皇后的位置成立，开始放下一个皇后位置
                check(n + 1);
            }
        }
    }

    public boolean judge(int n){//判断第n - 1个皇后是否成立
        for(int i = 0;i < n;i++){//遍历0 ~ (n- 2)个皇后，观察它们和第（n-1）个皇后是否冲突
            if(arr[i] == arr[n] || Math.abs(arr[i] - arr[n]) == Math.abs(i - n)){
                return false;
            }
        }
        return true;
    }

    public void print(){
        System.out.print(++num + ":");
        for(int i = 0;i < arr.length;i++){
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}




