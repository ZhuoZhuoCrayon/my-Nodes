package datastructure.redblackbst;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Classname RedBlackBST
 * @Description 红黑色实现
 * @Date 2020/2/25 21:19
 * @Created by Crayon
 */
public class RedBlackBST {
    public static final boolean RED = true;
    public static final boolean BLACK = false;

    public int size(Node node){
        return node == null ? 0 : node.N;
    }

    public boolean isRed(Node node){
        if(node == null) return false;
        return node.color == RED;
    }

    public boolean isBlack(Node node){
        return node.color == BLACK;
    }

    public void flipColors(Node node){
        node.color = RED;
        node.left.color = BLACK;
        node.right.color = BLACK;
    }

    public void flipColorsD(Node node){
        node.color = BLACK;
        node.left.color = RED;
        node.right.color = RED;
    }

    // 左旋右旋操作中，x之上的链接颜色继承于h
    // 表示在左旋右旋中，很有可能出现两条相连的红色链接，但回溯过程会修正
    /**
     * 左旋：将右红链接转化为红左链接
     * h - x 是一条右红链接
     */
    public Node rotateLeft(Node h){
        Node x = h.right;

        /**
         * x的左子树值介于h - x之间
         * 当x被提起作为根时，将x的左子树挂到h的右子树上
         * h 成为 x 的左子树
         */
        h.right = x.left;
        x.left = h;

        /**
         * h - x 右红链接变为 x - h 左红链接
         * 指向h的是红链接
         * x 的指向链接颜色继承于h
         */
        x.color = h.color;
        h.color = RED;

        /**
         * 重新计算两个子树的节点树
         * x.N = h.N : x的两个子树还在，h的左子树也在
         */
        x.N = 1+ size(x.left) + size(x.right);
        h.N = 1 + size(h.left) + size(h.right);

        return x;       //返回新的子树根
    }

    /**
     * 右旋：将左红链接转化为右红链接
     * h - x 左红链接
     */
    public Node rotateRight(Node h){
        Node x = h.left;

        h.left = x.right;
        x.right = h;

        x.color = h.color;
        h.color = RED;

        x.N = 1 + size(x.right) + size(x.left);
        h.N = 1 + size(h.right) + size(h.left);
        return x;
    }

    /**
     * 平衡红黑树
     */
    public Node balance(Node h){
        if(isRed(h.right)){
            h = rotateLeft(h);
        }
        if(isRed(h.left) && isRed(h.left.left)){
            h = rotateRight(h.left);
        }
        if(isRed(h.right) && isRed(h.left)){
            flipColors(h);
        }
        h.N = size(h.right) + size(h.left) + 1;
        return h;
    }

    /**
     * 插入情况：
     * 1只有一个根：插入左边-增加一条红链接/插入右边-左旋
     * 2向树底部的非红链节点插入：如上述
     * 3向红链节点插入 ： 得到平衡态后，将平衡子树两个孩子变黑，自己变红
     *      新键最大：形成平衡状态，消除左右红链接
     *      新键最小：两段连续红链接，右旋上面的一段红链接，使之化为平衡态
     *      新键介于中间：左旋下面的一段，右旋上面的一段  - 右旋变成两条左红链接，其他交给回溯
     *
     * 说到底只要维护三个状态
     * 右红链接：左旋
     * 两个连续的红链接：对上层的红链接进行右旋
     * 出现左右孩子都是红链接，变黑，根链接变红
     */
    private Node put(Node node, int key){
        if(node == null){
            return new Node(key, 1, RED);
        }
        if(key < node.key){
            node.left = put(node.left, key);
        }else if(key > node.key){
            node.right = put(node.right, key);
        }

        if(isRed(node.right) && !isRed(node.left)){
            node = rotateLeft(node);
        }
        if(isRed(node.left) && isRed(node.left.left)){
            node = rotateRight(node);
        }
        if(isRed(node.left) && isRed(node.right)){
            flipColors(node);
        }
        node.N = size(node.left) + size(node.right) + 1;
        return node;
    }

    /**
     * 当待删除节点是2-节点，设h是删除节点的父亲
     * h的左右链接染红
     *      兄弟也是2-节点，此时 h和它的两个儿子成为4-节点
     *      兄弟不是2-节点，h右旋，此时兄弟的一个节点作为根，原h与待删除节点成为3-节点
     *          记得将新h的左右染黑
     *  ps:判定一个节点node不是2-节点 ----  isRed(node) or isRed(node.left)
     */
    public Node removeRight(Node h){
        flipColorsD(h);
        if(isRed(h.left.left)) {
            h = rotateRight(h);
            flipColors(h);
        }
        return h;
    }

    /**
     * 删除最大值
     */
    public Node deleteMax(Node root){
        root = deleteMaxHelper(root);
        if(root != null) {
            root.color = BLACK;
        }
        System.out.println(size(root));
        return root;
    }

    /**
     * 删除最大值主体实现
     */
    public Node deleteMaxHelper(Node root){
        if(isRed(root.left)){
            root = rotateRight(root);
        }
        //删除
        if(root.right == null){
            return null;
        }
        //待删除节点是2-节点
        if(!isRed(root.right) && !isRed(root.right.left)){
            root = removeRight(root);
        }
        root.right = deleteMaxHelper(root.right);
        return balance(root);
    }

    public int depth(Node root){
        if(root == null) return 0;
        return Math.max(depth(root.left), depth(root.right)) + 1;
    }
    public void inOrder(List<Character> out, Node root){
        LinkedList<Node> stack = new LinkedList<>();
        Node cur = root;
        while(cur != null || !stack.isEmpty()){
            while(cur != null){
                stack.addLast(cur);
                cur = cur.left;
            }
            cur = stack.removeLast();
            out.add((char)cur.key);
            cur = cur.right;
        }
        System.out.println(out);
    }
    public void postOrder(List<Character> out, Node root){
        LinkedList<Node> stack = new LinkedList<>();
        Node cur = root;
        Node pre = null;
        while(cur != null || !stack.isEmpty()){
            while(cur != null){
                stack.addLast(cur);
                cur = cur.left;
            }
            cur = stack.peekLast();
            // over visit right
            if(cur.right == pre || cur.right == null){
                out.add((char)cur.key);
                stack.removeLast();
                pre = cur;
                cur = null;
            }else{
                cur = cur.right;
            }
        }
        System.out.println(out);
    }
    public static void main(String[] args) {
        char[] input = {'S', 'E', 'A', 'R', 'C', 'H', 'X', 'M', 'P', 'L'};
        char[] input1 = {'A', 'C', 'E', 'H', 'L', 'M', 'P', 'R', 'S', 'X'};
        Node tree = null;
        RedBlackBST redBlackBST = new RedBlackBST();
        for(char c : input1){
            tree = redBlackBST.put(tree, (int)c);
        }
        redBlackBST.inOrder(new ArrayList<>(), tree);
        redBlackBST.postOrder(new ArrayList<>(), tree);
        System.out.println(redBlackBST.depth(tree));


        while(tree != null){
            tree = redBlackBST.deleteMax(tree);
        }
    }
}
