package com.hspedu.tree;

public class BinaryTreeDemo {//二叉树 前序遍历
    public static void main(String[] args) {
        //创建一颗二叉树
        BinaryTree binaryTree = new BinaryTree();
        //创建需要的结点
        HeroNode root = new HeroNode(0,"米龙");
        HeroNode hero1 = new HeroNode(1,"宋江");
        HeroNode hero2 = new HeroNode(2,"吴用");
        HeroNode hero3 = new HeroNode(3,"卢俊义");
        HeroNode hero4 = new HeroNode(4,"林冲");

        //手写一段二叉树
        binaryTree.setRoot(root);
        root.setLeft(hero1);
        root.setRight(hero4);
        hero1.setLeft(hero2);
        hero1.setRight(hero3);
        /*
        binaryTree.infixOrder();
        System.out.println("==========");
        HeroNode resNode = binaryTree.preOrderSearch(3);
        if(resNode != null){
            System.out.println(resNode);
        }else{
            System.out.println("未找到");
        }*/
        System.out.println("删除前：前序遍历");
        binaryTree.PreOrder();
        binaryTree.delNode(4);
        System.out.println("删除后，前序遍历");
        binaryTree.PreOrder();
    }
}

//创建二叉树
class BinaryTree{
    private HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
    }



    //递归删除结点
    //如果删除的结点的叶子结点，则直接删除
    //如果删除的结点是非叶子节点，则删除该子树

    public void delNode(int no){
        if(root != null){//判断该二叉树根节点是否为空
            if(root.getNo() == no){
                root = null;
            }else{
                root.delNode(no);
            }
        }else{
            System.out.println("空树，无法删除");
        }
    }
    //前序遍历
    public void PreOrder(){
        if(root != null){
            root.PreOrder();
        }else{
            System.out.println("二叉树为空，无法遍历");
        }
    }
    //中序遍历
    public void infixOrder(){
        if(root != null){
            root.infixOrder();
        }else{
            System.out.println("二叉树为空，无法遍历");
        }
    }
    //后序遍历
    public void curOrder(){
        if(root != null){
            root.curOrder();
        }else{
            System.out.println("二叉树为空，无法遍历");
        }
    }

    //调用前序 查找 的方法
    public HeroNode preOrderSearch(int no){
        if(root != null){
            return root.preOrderSearch(no);
        }else{
            System.out.println("该二叉树为空");
            return null;
        }
    }
    //调用中序查找的方法
    public HeroNode infixOrderSearch(int no){
        if(root != null){
            return root.infixOrderSearch(no);
        }else{
            System.out.println("该二叉树为空");
            return null;
        }
    }
    //调用后续查找的方法
    public HeroNode postOrderSearch(int no){
        if(root != null){
            return root.postOrderSearch(no);
        }else{
            System.out.println("该二叉树为空");
            return null;
        }
    }
}

//先创建节点
class HeroNode{
    private int no;
    private String name;
    private HeroNode left;//默认为null
    private HeroNode right;

    public HeroNode(int no, String name) {
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

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
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
     * 5.如果第4补也没有找到，就对右子树递归删除*/
    public void delNode(int no){
        if(this.left != null && this.left.no == no){
            this.left = null;
            return;
        }
        if(this.right != null && this.right.no == no){
            this.right = null;
            return;
        }
        if(this.left != null){
            this.left.delNode(no);
        }
        if(this.right != null){
            this.right.delNode(no);
        }
    }


    //编写 二叉树 前序遍历的方法：先输出 父节点 ，再遍历左子树和右子树
    public void PreOrder(){
        System.out.println(this);//先输出父结点
        //递归向左子树遍历
        if(this.left != null) {
            this.left.PreOrder();
        }
        //递归向右子树遍历
        if(this.right != null){
            this.right.PreOrder();
        }

    }
    //中序遍历：先遍历左子树， 再输出父节点，再遍历右子树
    public void infixOrder(){
        //遍历左子树
        if(this.left != null){
            this.left.infixOrder();
        }
        //输出父结点
        System.out.println(this);
        //遍历右子树
        if(this.right != null){
            this.right.infixOrder();
        }
    }
    //后序遍历：先遍历左子树、右子树，最后输出父节点
    public void curOrder(){
        if(this.left != null){
            this.left.curOrder();
        }
        if(this.right != null){
            this.right.curOrder();
        }
        System.out.println(this);
    }

    //前序遍历查找
    public HeroNode preOrderSearch(int no){
        System.out.println("前序遍历次数+1");
        if(this.no == no){//找到了该排名
            return this;
        }
        //定义一个辅助结点
        HeroNode resNode = null;
        //判断当前结点的左节点是否为空，，若不为空，继续向前递归查找
        if(this.left != null){
            resNode = this.left.preOrderSearch(no);
        }
        //判断
        if(resNode != null){//说明我们在左子树中找到了目标结点
            return resNode;
        }
        if(this.right != null){
            resNode = this.right.preOrderSearch(no);
        }
        //在右边不管有没有找到，直接返回resNode
        return resNode;
    }
    //中序遍历查找
    public HeroNode infixOrderSearch(int no){
        HeroNode resNode = null;
        if(this.left != null){
            resNode = this.left.infixOrderSearch(no);
        }
        if(resNode != null){//说明在左子树中找到了
            return resNode;
        }
        System.out.println("中序遍历次数+1");
        if(this.no == no){
            return this;
        }
        if(this.right != null){
            resNode = this.right.infixOrderSearch(no);
        }
        return resNode;
    }

    //后序遍历查找
    public HeroNode postOrderSearch(int no){
        HeroNode resNode = null;
        if(this.left != null){//遍历左子树
            resNode = this.left.postOrderSearch(no);
        }
        if(resNode != null){//左子树中找到
            return resNode;
        }
        if(this.right != null){
            resNode = this.right.postOrderSearch(no);
        }
        if(resNode != null){
            return resNode;
        }
        System.out.println("后序遍历查找次数+1");
        //如果左右子树都没有找到，比较该no与跟节点的no
        if(this.no == no){
            return this;
        }
        return resNode;
    }
}