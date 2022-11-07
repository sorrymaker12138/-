package com.hspedu.huffmancode;

import java.util.*;

public class HuffmanCode {

    public static void main(String[] args) {
        String str = "i like like like java do you like a java";
        byte[] strBytes = str.getBytes();
        System.out.println(strBytes.length);//40
        /*
        List<Node> nodes = getNodes(strBytes);//node:date,weight
        Node root = CreateHuffman(nodes);//构造霍夫曼树，并返回其头结点
        //PreOrder(root);
        Map<Byte,String> codes = getCodes(root);//生成霍夫曼编码 Byte=node.date String为霍夫曼树到达该Byte的路径
        System.out.println("霍夫曼编码表为=" + codes);
        byte[] b = zip(strBytes,huffmanCode);//对霍夫曼编码表进行压缩，每8位压缩成一个byte放入byte[] b中
        */
        byte[] huffmanCodeBytes = huffmanZip(strBytes);
        System.out.println("对应霍夫曼编码压缩后的结果=" + Arrays.toString(huffmanCodeBytes));
        byte[] SourceBytes = decode(huffmanCode,huffmanCodeBytes);
        System.out.println(new String(SourceBytes));
    }

                                //数据解压部分
    private static byte[] decode(Map<Byte,String> huffmanCodes,byte[] huffmanBytes){
        StringBuilder stringBuilder = new StringBuilder();
        boolean loop;
        for(int i = 0;i < huffmanBytes.length;i++){
            loop = (i == huffmanBytes.length - 1);
            stringBuilder.append(byteToString(loop,huffmanBytes[i]));
        }
        System.out.println("霍夫曼字节数组对应的二进制字符串 =" + stringBuilder);

        //把字符串按照指定的霍夫曼编码进行解码
        //把霍夫曼编码进行调换，进行反向查询。如 (a->100) => (100 -> a)
        Map<String,Byte> map = new HashMap<>();
        for(Map.Entry<Byte,String> entry:huffmanCodes.entrySet()){
            map.put(entry.getValue(),entry.getKey());
        }
        //创建List集合，存放byte
        ArrayList<Byte> list = new ArrayList<>();
        for(int i = 0;i < stringBuilder.length();){
            int count = 1;
            boolean flag = true;
            Byte b = null;
            while(flag){
                String str = stringBuilder.substring(i,i + count);
                b = map.get(str);
                if(b == null){
                    count++;
                }else{
                    flag = false;
                }
            }
            i += count;
            list.add(b);
        }
        //System.out.println(list);
        byte[] bytes = new byte[list.size()];
        //最后，把list中的数据放入bytes[]中去
        for(int i = 0; i < list.size();i++){
            bytes[i] = list.get(i);
        }
        return bytes;
    }

    //完成对单个数据的解压
    //思路：
    //1.将huffmanCodeBytes[-88, -65, -56, -65, -56, -65, -55, 77, -57, 6, -24, -14, -117, -4, -60, -90, 28]
    //          首先转化成 霍夫曼编码对应的 二进制字符串"10101000..."
    //2.霍夫曼编码 对应的二进制的字符串"10101000..." => 对照霍夫曼编码表 => "i like like like java do you like a java"
    /**
     * 将一个byte转化位二进制的字符串
     * */
    private static String byteToString(boolean loop,byte b){
        //将byte转化为int形式
        int temp = b;
        //如果temp位负数，则str有32位，所以需要截取后8位;如果temp为正数str长度可能小于8，需要补位（即与256求按位或）
        if(!loop) {//当b为byte[]数组最后一个元素时，loop为真
            temp |= 256;//如：1 其补码为1 |= 256后等于 1 0000 0001 满足其长度大于8
        }
        //**注意：如果该byte为该byte[]数组中最后一个元素，当其对应二进制str长度小于8时，千万不能够进行补位！！
        String str = Integer.toBinaryString(temp);
        if(!loop && str.length() >= 8) {
            return str.substring(str.length() - 8);
        }else{
            return str;
        }
    }

                                //数据压缩部分

    //使用一个方法，将后面的方法封装起来，便于我们的调用
    /**
     * @param bytes 原始的字符串对应的byte[]
     * @return 是经过 赫夫曼编码 处理后的字节数组(压缩后的数组)
     * */
    private static byte[] huffmanZip(byte[] bytes){
        //得到集合 nodes 记录原始的字符串对应的byte[]中各byte的date(字符)与weight(权值：即字符串中该字符出现的个数)
        List<Node> nodes = getNodes(bytes);
        //根据传入的nodes 得到霍夫曼树
        Node root = CreateHuffman(nodes);
        Map<Byte,String> codes = getCodes(root);//生成霍夫曼编码表 Byte=node.date String为霍夫曼树到达该Byte(字符)的路径
        //对霍夫曼编码表进行压缩，每8位压缩成一个byte放入byte[] huffmanCodeBytes中
        byte[] huffmanCodeBytes = zip(bytes,huffmanCode);
        return huffmanCodeBytes;//返回压缩后的byte[] 数组
    }
    /**
     *@param strBytes 这时原始的字符串对应的byte[]
     *@param huffmanCode 生成的霍夫曼编码map
     *@return 返回赫夫曼编码处理后的byte[]
     * 举例：String str = "i like like like java do you like a java";->byte[] strBytes = str.getBytes()->
     * 返回的字符串"1010100010111111110010001011111111001000101111111100100101001101110001110000011011101000111100101000101111111100110001001010011011100"
     * ->对应的byte[] by,即8位对应一个byte，放入到 by中
     * by[0] = "10101000";(为补码) 10101000 - 1 -> 10100111(反码) -> 11011000(原码) = -88
     * 所以by[0] = -88
     */
     private static byte[] zip(byte[] strBytes,Map<Byte,String> huffmanCode){//压缩
        StringBuilder stringBuilder = new StringBuilder();
        for(byte b:strBytes){
            stringBuilder.append(huffmanCode.get(b));
        }
        //System.out.println("霍夫曼编码长度为："+ stringBuilder);
        int len;
        len = (stringBuilder.length()+7)/8;
        byte[] by = new byte[len];
        int index = 0;
        String str;
        for(int i = 0;i < stringBuilder.length(); i += 8){
            if(i + 8 > stringBuilder.length()) {
                str = stringBuilder.substring(i);
            }else{
                str = stringBuilder.substring(i,i+8);
            }
            //把str转化为一个byte并放入到byte[]
            by[index] = (byte)Integer.parseInt(str,2);//后面的radix代表str要转成的整数的 进制数，即转为二进制
            index++;
        }
        //得到了霍夫曼编码 编程的字符串所对应的字节数组
        return by;
    }

    //生成霍夫曼树对应的霍夫曼编码
    //思路：
    //1.将霍夫曼编码表存放至Map<Byte,String>形式
    static Map<Byte,String> huffmanCode = new HashMap<>();
    //2..在生成霍夫曼编码表时，需要去拼接路径，定义一个StringBuilder，储存某个叶子结点的路径
    static StringBuilder stringBuilder = new StringBuilder();

    //可以直接重载getCodes()方法
    private static Map<Byte,String> getCodes(Node node){
        getCodes(node,"",stringBuilder);
        return huffmanCode;
    }
    /**
     * 功能：将传入的node结点以及所有叶子节点的霍夫曼树得到，并放入到huffmanCodes集合中去
     * @param node 传入结点
     * @param code 路径:左子结点为0，右子结点为1
     * @param stringBuilder 用于拼接路径
     * */
    private static void getCodes(Node node ,String code,StringBuilder stringBuilder){
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        stringBuilder2.append(code);
        if(node != null){//如果该结点不为空
            if(node.date == null){//如果该结点不为叶子结点，就分别向左向右递归遍历
                getCodes(node.left,"0",stringBuilder2);
                getCodes(node.right,"1",stringBuilder2);
            }else{//即该结点为叶子结点
                huffmanCode.put(node.date,stringBuilder2.toString());
            }
        }
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
    public static Node CreateHuffman(List<Node> nodes){
        while(nodes.size() > 1) {
            Collections.sort(nodes);
            //取出根节点权值最小的两个结点
            //(1).取出权值最小的结点
            Node leftNode = nodes.get(0);
            //(2).取出权值次小的结点
            Node rightNode = nodes.get(1);
            //(3).构建一个新的二叉树
            Node parent = new Node(null,leftNode.weight + rightNode.weight);
            parent.left = leftNode;
            parent.right = rightNode;
            //(4).从ArrayList删除处理过的两个结点
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            //(5).把得到的parent结点加入到ArrayList中去
            nodes.add(parent);
        }
        //返回霍夫曼树的头节点即可
        return nodes.get(0);
    }


    public static List<Node> getNodes(byte[] strBytes){
        //创建一个List存储Node结点
        ArrayList<Node> nodes = new ArrayList<>();
        HashMap<Byte, Integer> counts = new HashMap<Byte, Integer>();
        //遍历bytes,统计每一个byte出现的次数 -> map[key,value]
        for(Byte b:strBytes){
            Integer count = counts.get(b);
            if(count == null){//即Map中还没有这个byte，第一次
                counts.put(b,1);
            }else{
                counts.put(b,count + 1);
            }
        }
        //把每一个键 转化为 Node并存入nodes集合中
        //遍历map（即counts）
        for(Map.Entry<Byte,Integer> entry:counts.entrySet()){
            nodes.add(new Node(entry.getKey(), entry.getValue()));
        }
        return nodes;
    }
}

//创建一个结点类
class Node implements Comparable<Node>{
    Byte date;//存放数据,比如'a'
    int weight;//权值，数据出现的次数
    Node left;
    Node right;

    public Node(Byte date, int weight) {
        this.date = date;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node o) {//从小到大排序
        return this.weight - o.weight;
    }

    @Override
    public String toString() {
        return "Node{" +
                "date=" + date +
                ", weight=" + weight +
                '}';
    }

    //程序前序遍历
    public void PreOrder(Node root){
        if(root == null){
            return;
        }
        System.out.println(root);
        PreOrder(root.left);
        PreOrder(root.right);
    }
}
