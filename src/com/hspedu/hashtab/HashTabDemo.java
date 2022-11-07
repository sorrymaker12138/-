package com.hspedu.hashtab;

import java.util.Scanner;

public class HashTabDemo {
    public static void main(String[] args) {
    //创建一个HashTab
        HashTab hashTab = new HashTab(10);

        //写一个简单的菜单
        String key = "";
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println("add:添加雇员");
            System.out.println("remove:删除雇员");
            System.out.println("find 查找雇员");
            System.out.println("list:遍历雇员");
            System.out.println("exit:退出系统");

            key = scanner.next();
            switch (key){
                case "add":
                    System.out.println("输入id：");
                    int id = scanner.nextInt();
                    System.out.println("输入名字：");
                    String name = scanner.next();
                    //创建一个雇员
                    Emp emp1 = new Emp(id,name);
                    hashTab.add(emp1);
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "find":
                    System.out.println("请输入要查找的id");
                    int id1 = scanner.nextInt();
                    hashTab.findEmpById(id1);
                    break;

                case "exit":
                    scanner.close();
                    System.exit(0);
                default:
                    break;
            }
        }
    }
}

//创建Hash表，用于同时管理多条链表
class HashTab{
    private EmpLinkedList[] empLinkedlistArray;
    int size;
    //构造器

    public HashTab(int size) {
        this.size = size;
        empLinkedlistArray = new EmpLinkedList[size];
        //这里 有一个坑 ！！我们只创建了 HashTab 没有创建 链表，链表为空，所以需要分别初始化每一条链表
        for(int i = 0;i < size;i++){
            empLinkedlistArray[i] = new EmpLinkedList();
        }
    }
        //添加雇员
     public void add(Emp emp){//因为只有HasshTab才有权限 遍历 所有链表
        //通过取余得到雇员要加入的 数组 数组下标
        int empLinkedlistNo = HashFun(emp.id);
        //把emp加入到链表中
        empLinkedlistArray[empLinkedlistNo].add(emp);

     }
     public int HashFun(int id){
        return id % size;
     }

     //查找
    public void findEmpById(int id){
        //使用散列函数确定到那条链表进行遍历
        int empLinkedlistNo = HashFun(id);
        //查找
        Emp emp = empLinkedlistArray[empLinkedlistNo].findEmpById(id);
        if(emp == null){
            System.out.println("未查找到该id");
        }else{
            System.out.println("已在第" +(empLinkedlistNo + 1) + "条链表找到该雇员");
        }
    }

     //遍历所有的链表
    public void list(){
        for(int i = 0;i < size;i++){
            System.out.printf("第 %d 条链表的信息为;",i + 1);
            empLinkedlistArray[i].list();
        }
    }
}


//建立一个链表
class EmpLinkedList{
    //头指针指向第一个节点，所以我们的节点指向第一个节点
    private Emp head;//默认头节点为null

    //添加雇员到链表
    //1.假定，当添加员工时，id是自增长的，所以id 分配由小到大
    //  因此，我们直接把本节点加入到链表结尾处即可
    public void add(Emp emp){
        if(head == null){
            head = emp;
            return;
        }
        Emp curEmp = head;
        while(true){
            if(curEmp.next == null){
                break;
            }
            curEmp = curEmp.next;
        }
        curEmp.next = emp;
    }

    //遍历链表
    public void list(){
        if(head == null){
            System.out.println("链表为空");
            return;
        }
        //System.out.println("当前链表信息为：");
        Emp curEmp = head;//辅助指针
        while(true){
            System.out.printf("=> id=%d,name= %s/t",curEmp.id,curEmp.name);
            if(curEmp.next == null){
                break;
            }
            curEmp = curEmp.next;
        }
        System.out.println();
    }

    //根据id查找节点
    public Emp findEmpById(int id){
        //根据输入的id查找链表中是否存在该节点
        if(head == null){
            System.out.println("");
            return null;
        }
        Emp curEmp = head;
        while(true){
            if(curEmp.id == id){//及找到该节点
                break;
            }
            if(curEmp == null){
                return null;
            }
            curEmp = curEmp.next;
        }
        return curEmp;
    }

}

//建立链表节点,表示一个雇员
class Emp{
    public int id;
    public String name;
    public Emp next;//默认为空

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
