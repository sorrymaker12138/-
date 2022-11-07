package com.hspedu.tree.threadedBinaryTree;
import com.hspedu.tree.BinaryTreeDemo;

@SuppressWarnings({"all"})
public class ThreadedBinaryTreeDemo {//线索化二叉树
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1,"1");
        TreeNode Node2 = new TreeNode(3,"3");
        TreeNode Node3 = new TreeNode(6,"6");
        TreeNode Node4 = new TreeNode(8,"8");
        TreeNode Node5 = new TreeNode(10,"10");
        TreeNode Node6 = new TreeNode(14,"14");

        //二叉树，后面我们会使用递归添加，现在我们先进行手动添加

        root.setLeft(Node2);
        root.setRight(Node3);
        Node2.setLeft(Node4);
        Node2.setRight(Node5);
        Node3.setLeft(Node6);

        Node2.setParent(root);
        Node3.setParent(root);
        Node4.setParent(Node2);
        Node5.setParent(Node2);
        Node6.setParent(Node3);
        //        1
        //    3       6
        //  8   10  14
        //我们对其进行测试中序线索化
        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();

        //线索化二叉树后就不能 使用之前的遍历方法了，会陷入死循环

        //System.out.println("中序遍历 线索化二叉树");
        //threadedBinaryTree.threadedNodes(root);
        //threadedBinaryTree.threadedListInfix(root);

        //System.out.println("前序遍历 线索化二叉树");
        //threadedBinaryTree.threadedPre(root);
        //threadedBinaryTree.threadedListPre(root);

        System.out.println("后续遍历 线索化二叉树");
        threadedBinaryTree.threadedPost(root);
        //后序： 8 10 3 14 6 1
        threadedBinaryTree.threadedListPost(root);
    }
}

//创建   线索化二叉树，实现了线索化功能的 二叉树
class ThreadedBinaryTree extends BinaryTreeDemo {
    //为了实现线索化，需要创建一个指向当前结点的  前驱结点  的一个指针
    //在递归进行线索化时，总是保留前一个结点
    private TreeNode pre = null;


    //对线索化二叉树 进行前序遍历
    public void threadedListPre(TreeNode root){
        //定义一个变量，辅助当前遍历的结点
        TreeNode node = root;
        while(node != null){
            //如果node非null，存在
            while(node.getLeftType() == 0){
                System.out.println(node);
                node = node.getLeft();
            }
            System.out.println(node);
            node = node.getRight();
        }
    }
    //对线索化二叉树 进行中序遍历
    public void threadedListInfix(TreeNode root){
        //定义一个变量，辅助当前遍历的结点，从root开始
        TreeNode node = root;
        while(node != null){
            //循环找到 LeftType = 1的结点（即存在前驱节点的结点）
            while(node.getLeftType() == 0){
                node = node.getLeft();
                //得到的第一个结点就是8
            }
            System.out.println(node);
            if(node.getRightType() == 1){
                node = node.getRight();
                System.out.println(node);
            }
            node = node.getRight();
        }
    }


    //对 后序线索化二叉树 进行遍历
    //后序： 8 10 3 14 6 1
    public void threadedListPost(TreeNode root) {
        TreeNode node = root;
        while (node != null && node.getLeftType() == 0) {
            node = node.getLeft();
        }
        while (node != null) {
            //右节点是线索
            if (node.getRightType() == 1) {
                System.out.println(node);
                pre = node;
                node = node.getRight();
            } else {
                //如果上个处理的节点是当前节点的右节点
                if (node.getRight() == pre) {
                    System.out.println(node);
                    if (node == root) {
                        return;
                    }
                    pre = node;
                    node = node.getParent();
                } else {    //如果从左节点的进入则找到有子树的最左节点
                    node = node.getRight();
                    while (node != null && node.getLeftType() == 0) {
                        node = node.getLeft();
                    }
                }
            }
        }
    }


    //编写 对二叉树 前序线索化的方法
    public void threadedPre(TreeNode node){
        if(node == null){
            //不能线索化
            return;
        }
        //线索化当前结点
        //处理前驱节点
        if(node.getLeft() == null){
            node.setLeft(pre);
            node.setLeftType(1);
        }
        //处理后继结点
        if(pre != null && pre.getRight() == null){
            pre.setRight(node);
            pre.setRightType(1);
        }
        pre = node;
        //线索化 左子树
        if(node.getLeftType() != 1) {
            threadedPre(node.getLeft());
        }
        //线索化 右子树
        if(node.getRightType() != 1) {
            threadedPre(node.getRight());
        }
    }

    //编写对二叉树进行 中序 线索化的方法
    /**
     * node:就是当前需要线索化的结点*/
    public void threadedNodes(TreeNode node){
        if(node == null){
            return;
        }
        //(一).先线索化左子树
        threadedNodes(node.getLeft());
        //(二).[***]再线索化当前结点
        if(node.getLeft() == null){
            //让当前结点的左指针指向前驱结点
            node.setLeft(pre);
            //修改当前结点的左指针的类型，指向前驱节点
            node.setLeftType(1);
        }
        //处理后续结点
        if(pre != null && pre.getRight() == null){
            //让前驱结点的指针指向当前结点
            pre.setRight(node);
            //修改前驱节点的右指针类型
            pre.setRightType(1);
        }
        //每处理一个节点后，让当前结点是下一个结点的前驱结点
        pre = node;//非常非常重要！！
        //(三).最后线索化右子树
        threadedNodes(node.getRight());
    }

    //编写后续 线索化二叉树 的方法
    //        1
    //    3       6
    //  8   10  14
    //后序： 8 10 3 14 6 1
    public void threadedPost(TreeNode node){
        //空结点，既不能线索化，返回null
        if(node == null){
            return;
        }
        //先线索化左子树
        threadedPost(node.getLeft());
        //再线索化右子树
        threadedPost(node.getRight());
        //线索化当前结点
        if(node.getLeft() == null){//需要线序化
            node.setLeft(pre);
            node.setLeftType(1);//标记该节点，说明该结点已经被线索化
        }
        if(pre != null && pre.getRight() == null){
            pre.setRight(node);
            pre.setRightType(1);
        }
        pre = node;

    }


}

//先创建节点
class TreeNode {
    private int no;
    private String name;
    private TreeNode left;//默认为null
    private TreeNode right;
    //1.如果leftType == 0代表 当前结点指向左子树，等于1则代表指向前驱结点
    //2.如果rightType == 0代表 当前结点指向右子树，等于1则代表指向后继结点
    private int leftType;
    private int rightType;

    //用于后序遍历线索化二叉树
    private TreeNode Parent;

    public TreeNode getParent() {
        return Parent;
    }

    public void setParent(TreeNode parent) {
        Parent = parent;
    }

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    public TreeNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    /**
     * 思路：
     * 1.因为我们的二叉树是单向的，所以我们应该判断的是当前节点的子节点是否需要删除
     * 2.如果当前左子结点不为空，且左子结点就是要删除的结点，就this.left = null
     * 3.如果当前右子结点不为空，且右子结点就是要删除的结点，就this.right = null
     * 4.如果第2、3补没有删除结点，就对左子树递归删除
     * 5.如果第4补也没有找到，就对右子树递归删除
     */
    public void delNode(int no) {
        if (this.left != null && this.left.no == no) {
            this.left = null;
            return;
        }
        if (this.right != null && this.right.no == no) {
            this.right = null;
            return;
        }
        if (this.left != null) {
            this.left.delNode(no);
        }
        if (this.right != null) {
            this.right.delNode(no);
        }
    }


    //编写 二叉树 前序遍历的方法：先输出 父节点 ，再遍历左子树和右子树
    public void PreOrder() {
        System.out.println(this);//先输出父结点
        //递归向左子树遍历
        if (this.left != null) {
            this.left.PreOrder();
        }
        //递归向右子树遍历
        if (this.right != null) {
            this.right.PreOrder();
        }

    }

    //中序遍历：先遍历左子树， 再输出父节点，再遍历右子树
    public void infixOrder() {
        //遍历左子树
        if (this.left != null) {
            this.left.infixOrder();
        }
        //输出父结点
        System.out.println(this);
        //遍历右子树
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    //后序遍历：先遍历左子树、右子树，最后输出父节点
    public void curOrder() {
        if (this.left != null) {
            this.left.curOrder();
        }
        if (this.right != null) {
            this.right.curOrder();
        }
        System.out.println(this);
    }

    //前序遍历查找
    public TreeNode preOrderSearch(int no) {
        System.out.println("前序遍历次数+1");
        if (this.no == no) {//找到了该排名
            return this;
        }
        //定义一个辅助结点
        TreeNode resNode = null;
        //判断当前结点的左节点是否为空，，若不为空，继续向前递归查找
        if (this.left != null) {
            resNode = this.left.preOrderSearch(no);
        }
        //判断
        if (resNode != null) {//说明我们在左子树中找到了目标结点
            return resNode;
        }
        if (this.right != null) {
            resNode = this.right.preOrderSearch(no);
        }
        //在右边不管有没有找到，直接返回resNode
        return resNode;
    }

    //中序遍历查找
    public TreeNode infixOrderSearch(int no) {
        TreeNode resNode = null;
        if (this.left != null) {
            resNode = this.left.infixOrderSearch(no);
        }
        if (resNode != null) {//说明在左子树中找到了
            return resNode;
        }
        System.out.println("中序遍历次数+1");
        if (this.no == no) {
            return this;
        }
        if (this.right != null) {
            resNode = this.right.infixOrderSearch(no);
        }
        return resNode;
    }

    //后序遍历查找
    public TreeNode postOrderSearch(int no) {
        TreeNode resNode = null;
        if (this.left != null) {//遍历左子树
            resNode = this.left.postOrderSearch(no);
        }
        if (resNode != null) {//左子树中找到
            return resNode;
        }
        if (this.right != null) {
            resNode = this.right.postOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        System.out.println("后序遍历查找次数+1");
        //如果左右子树都没有找到，比较该no与跟节点的no
        if (this.no == no) {
            return this;
        }
        return resNode;
    }
}

