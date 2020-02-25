package datastructure.redblackbst;

/**
 * @Classname Node
 * @Description 红黑树节点
 * @Date 2020/2/25 18:05
 * @Created by Crayon
 */
public class Node {
    public int key;         //键
    public Node left;       //左孩子
    public Node right;      //右孩子
    public int N;           //子树节点总数
    public boolean color;   //由父节点指向它的连接颜色
    Node(int key, int n, boolean color){
        this.key = key;
        this.N = n;
        this.color = color;
    }
}
