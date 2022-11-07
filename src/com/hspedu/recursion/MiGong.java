package com.hspedu.recursion;

public class MiGong {
    public static void main(String[] args) {
        //用二维数组来模拟迷宫
        int[][] map = new int[8][7];
        //使用1 表示墙，即不能通过
        //令上下为1
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        //令左右为1
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        //设置挡板
        for(int i = 1;i < 5;i++){
            map[2][i] = 1;
        }
        for(int i = 2;i < 5;i++){
            map[4][i] = 1;
        }
        map[5][5] = 1;

        //输出地图
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                if(map[i][j] == 1){
                    System.out.print("*" + " ");
                }else {
                    System.out.print(map[i][j] + " ");
                }
            }
            System.out.println();
        }
        System.out.println();
        setWay(map,1,1);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                if(map[i][j] == 1){
                    System.out.print("*" + " ");//为了方便观看，在map[i][j] == 1处 我们输出"*"
                }else {
                    System.out.print(map[i][j] + " ");
                }
            }
            System.out.println();
        }
    }


        //使用递归来为小球找路
        //说明：
        //1.map表示地图
        //2.i,j表示迷宫游戏的起始位置
        //3.若小球能到达map[6][5]即说明小球 找到通路
        //4.当map[i][j] 为0时代表该处小球未走过；若为1，表示墙；若为2，表示通路可以走；若为3，表示小球走过，但是走不通
        //5. 走迷宫时，确定一个策略。下-> 右-> 上-> 左 路径依次原则，如果走不同就进行回溯
        public static boolean setWay ( int[][] map, int i, int j){
            if (map[6][5] == 2) {
                return true;
            } else {
                if (map[i][j] == 0) {//即假设当前这个点还没有走过
                    //按照下-> 右-> 上-> 左进行操作
                    map[i][j] = 2;//假定该点是可以走 通 的
                    if (setWay(map, i + 1, j)) {//下
                        return true;
                    } else if (setWay(map, i, j + 1)) {//右
                        return true;
                    } else if (setWay(map, i - 1, j)) {//上
                        return true;
                    } else if (setWay(map, i, j - 1)) {//左
                        return true;
                    } else {//即所有方向都走不通，是死路
                        map[i][j] = 3;
                        return false;
                    }
                } else {//即map[i][j] == 1、2、3,1代表无法通过，2代表走过了，就不要再走了，3代表走过了但是走不通。所以直接返回false
                    return false;
                }
            }
        }
}
