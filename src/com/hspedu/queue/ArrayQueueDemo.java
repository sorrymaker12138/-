package com.hspedu.queue;

import java.util.Scanner;

public class ArrayQueueDemo {
    public static void main(String[] args) {
        ArrayQueue arrayQueue = new ArrayQueue(3);
        char key = ' ';
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        //输出一个菜单
        while(loop){
            System.out.println("s(show):展示队列里");
            System.out.println("a(add):为队列添加数据");
            System.out.println("g(get):从队列取出数据");
            System.out.println("e(exit):退出队列");
            key = scanner.next().charAt(0);
            switch (key){
                case 's':
                    arrayQueue.showQueue();
                    break;
                case 'a':
                    System.out.println("输入要添加的数据");
                    arrayQueue.addQueue(scanner.nextInt());
                    break;
                case 'g':
                    try {
                        int res = arrayQueue.getQueue();
                        System.out.println(res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    loop = false;
                    break;
                default:
                    break;
            }
        }
    }
}


class ArrayQueue{
    private int maxSize;//表示数组最大容量
    private int front;//队列头
    private int rear;//队列尾
    private int[] arr;//模拟队列

    //创建队列的构造器
    public ArrayQueue(int maxSize){
        this.maxSize = maxSize;
        arr = new int[maxSize];
        front = -1;//指向队列头部，分析出front是指向队列头的前一个位置
        rear = -1;//指向队列尾，指向队列尾（即队列最后一个数据）
    }

    //判断队列是否为满
    public boolean isFull(){
        return rear == maxSize - 1;
    }
    //判断队列是否为空
    public boolean isEmpty(){
        return front == rear;
    }
    //添加数据到队列
    public void addQueue(int n){
         if(isFull()){
             System.out.println("队列满，不能加入数据");
             return;
         }
         arr[++rear] = n;
    }

    //获取队列数据，出队列
    public int getQueue(){
        if(isEmpty()){
            //抛出异常
            throw new RuntimeException("队列空，无法出队列");//throw本身会直接导致代码return
            //return -1;
        }
        front++;
        return arr[front];
    }

    //显示队列所有数据
    public void showQueue(){
        if(isEmpty()){
            System.out.println("队列空，没有数据");
            return;
        }
        for(int i = front + 1;i <= rear;i++){
            System.out.print(arr[i] + " ");
        }
    }
    //只显示队列的头数据，但是不取出数据
    public int headQueue(){
        if(isEmpty()){
            throw new RuntimeException("空队列，无法返回数据");
        }
        return arr[front + 1];
    }
}