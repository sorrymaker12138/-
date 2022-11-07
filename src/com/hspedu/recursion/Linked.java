package com.hspedu.recursion;


public class Linked {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        SingleLinkedList.add(head,2);
        SingleLinkedList.add(head,3);
        SingleLinkedList.add(head,4);
        SingleLinkedList.add(head,5);
        //SingleLinkedList.reverseList(head);
        SingleLinkedList.list(SingleLinkedList.reverseList(head));
    }
}
class ListNode{
    int val;
    ListNode next;

    public ListNode(int val) {
        this.val = val;
    }



}
class SingleLinkedList{

    public static void add(ListNode head,int i){
        ListNode temp = head;
        while(temp.next != null){
            temp = temp.next;
        }
        temp.next = new ListNode(i);
    }

    public static void list(ListNode head){//显示链表
        ListNode temp = head;
        while(temp != null){
            System.out.print(temp.val + " ");
            temp = temp.next;
        }
    }

    public static ListNode reverseList(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }else{
            ListNode res = reverseList(head.next);
            head.next.next = head;
            head.next = null;
            return res;
        }
    }
}
