package com.hspedu.graph;

import java.util.ArrayList;
import java.util.Arrays;

public class Graph {
    private ArrayList<String> vertexList;//储存顶点集合
    private int[][] edges;//储存图对应的邻阶矩阵
    private int numOfEdges;//表示边的数目
    private boolean[] isVisited;
    public static void main(String[] args) {
        int n = 5;
        String[] Vertexs = {"A","B","C","D","E"};
        Graph graph = new Graph(5);
        for(String Vertex:Vertexs){
            graph.vertexList.add(Vertex);
        }
        graph.insertEdge(0,1,1);
        graph.insertEdge(0,2,1);
        graph.insertEdge(1,2,1);
        graph.insertEdge(2,4,1);
        graph.insertEdge(2,3,1);
        graph.dfs(4);
        //graph.showEdges();
    }

    public Graph(int n) {
        //初始化邻结矩阵
        edges = new int[n][n];
        vertexList = new ArrayList<>(n);
        numOfEdges = n;
        isVisited = new boolean[n];
    }
    //展示矩阵
    public void showEdges(){
        for(int[] link:edges){
            System.out.println(Arrays.toString(link));
        }
    }

    public void dfs(int i){
        System.out.print(getValueByIndex(i)+"->");
        isVisited[i] = true;
        int w = getFirstNeighbor(i);
        while(w != -1){//即结点i 存在邻接结点
            if(!isVisited[w]){//该邻接结点为被访问过
                dfs(w);
            }
            w = getNextNeighbor(i,w);
        }
    }

    //得到对应节点第一个邻接下标 w
    public int getFirstNeighbor(int index){
        for(int j = 0;j < edges[index].length;j++){
            if(edges[index][j] > 0){
                return j;//返回邻接结点的下标
            }
        }
        return -1;
    }

    //根据前一个邻接结点的下标来获取 下一个邻接结点
    public int getNextNeighbor(int v1,int v2){//edges[v1][v2] = 前一个邻接结点
        for(int i = v2 + 1;i < edges[v1].length;i++){
            if(edges[v1][i] > 0){
                return i;
            }
        }
        return -1;
    }

    //返回结点i(下标)对应的数组 0->"A"
    public String getValueByIndex(int i){
        return vertexList.get(i);
    }

    //返回节点个数
    public int getNumOfEdges(){
        return vertexList.size();
    }

    //插入节点
    public void insertVertex(String s){
        vertexList.add(s);
    }

    //添加边
    /**
     * @param v1 表示点的下标
     * @param v2 表示第二个点的下标
     * */
    public void insertEdge(int v1,int v2,int weight){
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
    }


}
