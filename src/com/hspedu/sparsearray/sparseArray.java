package com.hspedu.sparsearray;

public class sparseArray {//稀疏数组
    public static void main(String[] args) {
        //创建一个原始数组
        //0：无子，1：黑子，2：蓝子
        int chessArr1[][] = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        //输出原始的二维数组
        for(int[] row:chessArr1){
            for(int item:row){
                System.out.print(item + " ");
            }
            System.out.println();
        }
        int sum = 0;//确定数组中非0数字的个数
        for(int i = 0;i < chessArr1.length;i++){
            for(int j = 0;j < chessArr1[i].length;j++){
                if(chessArr1[i][j] != 0){
                    sum++;
                }
            }
        }
        //创建对应的稀疏数组
        int[][] sparseArr = new int[sum + 1][3];
        sparseArr[0][0] = chessArr1.length;//原数组的排数
        sparseArr[0][1] = chessArr1.length;//原数组的列数
        sparseArr[0][2] = sum;//原数组存在的非0元素个数
        int num = 0;
        //遍历二维数组，把非0数子，放入稀疏数组
        for(int i = 0;i < chessArr1.length;i++){
            for(int j = 0;j < chessArr1[i].length;j++){
                if(chessArr1[i][j] != 0){
                    num++;
                    sparseArr[num][0] = i;
                    sparseArr[num][1] = j;
                    sparseArr[num][2] = chessArr1[i][j];
                }
            }
        }
        System.out.println("输出稀疏数组");
        for(int i = 0;i < sparseArr.length;i++){
            for(int j = 0;j < sparseArr[i].length;j++){
                System.out.print(sparseArr[i][j] + " ");
            }
            System.out.println();
        }
    }
}
