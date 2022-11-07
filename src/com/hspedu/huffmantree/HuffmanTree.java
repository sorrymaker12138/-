package com.hspedu.huffmantree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HuffmanTree {
    public static void main(String[] args) {
        int[] arr = {13,7,8,3,29,6,1};
            Node node = createHuffmanTree(arr);
            //测试一把，我们使用前序遍历
            PreOrder(node);
    }
    //前序遍历
    public static void PreOrder(Node node){//index代表数组下标
        if(node == null){
            return;
        }
        System.out.print(node + " ");
        if(node.left != null){
            PreOrder(node.left);
        }
        if(node.right != null){
            PreOrder(node.right);
        }
    }
    //创建一个霍夫曼树
    public static Node createHuffmanTree(int arr[]){
        List<Node> nodes = new ArrayList<Node>();
        for(int value:arr){
            nodes.add(new Node(value));
        }

        //该处理过程是一个循环的过程
        for(int i = 0;i < arr.length - 1;i++) {
            Collections.sort(nodes);
            //System.out.println("nodes =" + nodes);
            //取出根节点权值最小的两个结点
            //(1).取出权值最小的结点
            Node leftNode = nodes.get(0);
            //(2).取出权值次小的结点
            Node rightNode = nodes.get(1);
            //(3).构建一颗新的二叉树
            Node parent = new Node(leftNode.value + rightNode.value);
            parent.left = leftNode;
            parent.right = rightNode;
            //(4).从ArrayList删除处理过的两个结点
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            //(5).把得到的parent结点加入到ArrayList中去
            nodes.add(parent);
            //System.out.println("第一次处理后nodes=" + nodes);
        }
        //返回霍夫曼树的头节点即可
        return nodes.get(0);
    }
}

//创建节点
//为了让Node类 对象持续排序Collections集合排序
//让Node 实现Comparable接口
class Node implements Comparable<Node>{
    public int value;//结点权值
    Node left;//左子结点
    Node right;//右子节点

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        return this.value - o.value;//由小到大排序
    }
}
