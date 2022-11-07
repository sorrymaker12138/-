package com.hspedu.recursion;

public class RecursionTest {//递归
    public static void main(String[] args) {
        new RecursionTest().Test(20);
    }
    public void Test(int n){
        if(n > 2){
            Test(n - 1);
        }
        System.out.println("n =" + n);
    }
}
