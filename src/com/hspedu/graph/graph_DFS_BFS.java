package com.hspedu.graph;


import java.util.*;

public class graph_DFS_BFS {//图的广度优先搜索 ：优先搜索头节点的所有邻结点，搜索完后，再依次令其邻结点为头节点继续搜索直至搜索完全
    public static void main(String[] args) {
        HashMap<String, String[]> graph = new HashMap<>();//新建一个图
        graph.put("A", new String[]{"B", "C"});
        graph.put("B", new String[]{"A", "C", "D"});
        graph.put("C", new String[]{"A", "B", "D", "E"});
        graph.put("D", new String[]{"B", "C", "E", "F"});
        graph.put("E", new String[]{"C", "D"});
        graph.put("F", new String[]{"D"});
        // 图构建完成
        System.out.println("图的深度搜索结果：");
        DFS_G(graph, "A");
        System.out.println("=======");
        System.out.println("图的广度搜索结果");
        BFS_G(graph, "A");
        System.out.println("==========");


        TreeNode root = new TreeNode(1);
        TreeNode Node2 = new TreeNode(3);
        TreeNode Node3 = new TreeNode(6);
        TreeNode Node4 = new TreeNode(8);
        TreeNode Node5 = new TreeNode(10);
        TreeNode Node6 = new TreeNode(14);

        root.setLeft(Node2);
        root.setRight(Node3);
        Node2.setLeft(Node4);
        Node3.setLeft(Node5);
        Node3.setRight(Node6);
        System.out.println("二叉树的广度搜索结果");
        List<List<Integer>> tree = BFS_T(root);
        for(List<Integer> list: tree){
            System.out.println(list);
        }
    }

    private static void DFS_G(HashMap<String, String[]> graph, String s) {
        //创建一个栈，接受传入的字符串s
        Stack<String> stack = new Stack<>();
        HashSet<String> seen = new HashSet<String>();//用于记录 我们已经访问过哪些节点
        seen.add(s);//说明我们已经访问过s 这个结点
        stack.add(s);//入栈
        while(!stack.isEmpty()){
            String vertrix = stack.pop();
            String[] nodes = graph.get(vertrix);//得到与vertrix 相连的所有结点
            for(String w:nodes){//遍历与vertrix连接的所有字符串
                if(!seen.contains(w)){//用于判断字符串w是否已经查找过，若查找过则直接跳过，判断nodes中下一个字符串
                    stack.add(w);//若没有查找过，则直接入栈
                    seen.add(w);//将w如seen中，这样以后再出现改字符串，就会直接跳过
                }
            }
            System.out.println(vertrix);
        }
    }

    private static void BFS_G(HashMap<String, String[]> graph, String s) {
        //创建一个栈，接受传入的字符串s
        Queue<String> queue = new LinkedList<>();
        HashSet<String> seen = new HashSet<String>();//用于记录 我们已经访问过哪些节点
        seen.add(s);//说明我们已经访问过s 这个结点
        queue.add(s);//入栈
        while(!queue.isEmpty()){
            String vertrix = queue.poll();
            String[] nodes = graph.get(vertrix);//得到与vertrix 相连的所有结点
            for(String w:nodes){//遍历与vertrix连接的所有字符串
                if(!seen.contains(w)){//用于判断字符串w是否已经查找过，若查找过则直接跳过，判断nodes中下一个字符串
                    queue.add(w);//若没有查找过，则直接入栈
                    seen.add(w);//将w如seen中，这样以后再出现改字符串，就会直接跳过
                }
            }
            System.out.println(vertrix);
        }
    }

    private static List<List<Integer>> BFS_T(TreeNode root){//二叉树的广度调用
        List<List<Integer>> res = new ArrayList<>();
        if(root == null){
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            ArrayList<Integer> temp = new ArrayList<>();
            int size = queue.size();
            for(int i = 0;i < size;i++){
                TreeNode cur = queue.poll();
                temp.add(cur.val);
                if(cur.left != null){
                    queue.add(cur.left);
                }
                if(cur.right != null){
                    queue.add(cur.right);
                }
            }
            res.add(new ArrayList<>(temp));
        }
        return res;
    }
}

class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
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
}

