package com.hspedu.binarysorttree;

//二叉排序树原理：BST（Binary Sort（Search） Tree），对于二叉排序树地任何一个非叶子节点，要求其左子结点的值要比当前结点的值小，右子节点不小于当前节点的值
public class BinarySortTreeDemo {
    public static void main(String[] args) {
        int[] arr = {7,3,10,12,5,1,9,2};
        BinarySortTree binarySortTree = new BinarySortTree();
        for(int i:arr){
            binarySortTree.add(new Node(i));
        }
        binarySortTree.infixOrder();//5 3 6 7 9 12
        binarySortTree.delNode(2);
        binarySortTree.delNode(5);
        binarySortTree.delNode(9);
        binarySortTree.delNode(12);
        binarySortTree.delNode(7);
        binarySortTree.delNode(3);
        binarySortTree.delNode(10);
        binarySortTree.delNode(1);
        System.out.println("删除后：");
        binarySortTree.infixOrder();
    }
}
//创建二叉排序树
class BinarySortTree{
    private Node root;

    public void add(Node node){
        if(root == null){
            root = node;
        }else {
            root.add(node);
        }
    }

    //删除叶子结点
    public void delNode(int a){
        //若要删除结点为头节点
        if(root == null){
            return;
        }else{
            //1.按需求先去找到要删除的结点
            Node targetNode = root.search(a);
            if(targetNode == null){
                return;
            }
            //如果targetNode没有父节点
            if(root.left == null && root.right == null){
                root = null;
                return;
            }
            //查找要删去结点的父节点
            Node parent = root.searchParent(a);

            //如果要删去的结点为叶子节点
            if(targetNode.left == null && targetNode.right == null){
                if (parent.left == targetNode) {
                    parent.left = null;
                } else if (parent.right == targetNode) {
                    parent.right = null;
                }
            }else if(targetNode.right != null && targetNode.left != null){//要删除结点有两个子结点
                int minVal = delRightTreeMin(targetNode.right);
                targetNode.value = minVal;
            }else{//要删除的结点有一个子结点
            if(targetNode.left != null){
                if(parent != null) {//当删减到只剩一颗子树时，我们必须要考虑parent为null的情况
                    if (parent.left.value == a) {
                        parent.left = targetNode.left;
                    } else {
                        parent.right = targetNode.left;
                    }
                }else{
                    root = targetNode.left;
                }
            }else{
                if(parent != null) {
                    if (parent.left.value == a) {
                        parent.left = targetNode.right;
                    } else {
                        parent.right = targetNode.right;
                    }
                }else{
                    root = targetNode.right;
                }
            }
            }
        }
    }
    //编写方法
    //1.返回以node为根节点的二叉排序树的 右子树 最小结点的值（也可以找左子树最大的）
    //2.删除node为根节点的二叉排序树的 右子树 最小节点
    //3.把node结点的值改为删去的 右子树 最小结点的值
    public int delRightTreeMin(Node node){
        Node cur = node;
        while(cur.left != null){
            cur = cur.left;
        }
        int temp = cur.value;
        delNode(cur.value);
        return temp;
    }



    //遍历方法
    public void infixOrder(){
        if(root != null) {
            root.InfixOrder();
        }else{
            System.out.println("这克二叉排序树为空");
        }
    }
}


class Node {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    //添加结点的方法
    public void add(Node node){
        if(node == null)
            return;
        if(node.value < this.value){
            if(this.left == null){
                this.left = node;
            }else{//要传入的结点值小于改结点值，若该结点左子结点不为null
                //递归地向左子树添加
                this.left.add(node);
            }
        }else{
            if(this.right == null){
                this.right = node;
            }else{
                this.right.add(node);
            }
        }
    }

    //中序遍历
    public void InfixOrder(){
        if(this == null){
            return;
        }
        if(this.left != null)
        this.left.InfixOrder();
        System.out.print(this.value + " ");

        if(this.right != null)
        this.right.InfixOrder();
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    //删除结点
    public Node search(int a) {//返回要删除的结点
        if(this.value == a){
            return this;
        }else if(this.value > a){//如果要查找的值小于该结点的值，，则向左递归
            if(this.left == null) {
                return null;
            }
            return this.left.search(a);
        }else {
            if (this.right == null) {
                return null;
            }
            return this.right.search(a);
        }
    }

    public Node searchParent(int a) {//查找要删除结点的父节点
    if((this.left != null && this.left.value == a) ||  (this.right != null && this.right.value == a)){
        return this;
        }else{
        if(a < this.value && this.left != null){
            return this.left.searchParent(a);
        }else if(a >= this.value && this.right != null){
            return this.right.searchParent(a);
        }else{
            return null;
        }
    }
    }
}