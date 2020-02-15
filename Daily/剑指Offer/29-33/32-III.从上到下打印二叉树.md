## 32-III.从上到下打印二叉树

### Information

* TIME: 2020/02/15
* LINK: [Click Here](https://leetcode-cn.com/problems/cong-shang-dao-xia-da-yin-er-cha-shu-iii-lcof/)
* TAG: `BFS` `Tree`

### Description

> 请实现一个函数按照之字形顺序打印二叉树，即第一行按照从左到右的顺序打印，第二层按照从右到左的顺序打印，第三行再按照从左到右的顺序打印，其他行以此类推。

### Example

```text
例如:
给定二叉树: [3,9,20,null,null,15,7],

    3
   / \
  9  20
    /  \
   15   7
返回其层次遍历结果：

[
  [3],
  [20,9],
  [15,7]
]
```

### My Answer

> 层次遍历变形
>
> 记录遍历的层级，如果是奇数层，按从左到右，否者从右到左
>
> 添加顺序：`LinkedList`
>
> * `addFirst` 头插
> * `addLast` 尾插

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        int depth = 1;
        if(root == null) return ans;
        queue.offer(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            LinkedList<Integer> level = new LinkedList<>();
            for(int i = 0; i < size; i++){
                TreeNode node = queue.poll();
                if(depth % 2 == 1){
                    level.addLast(node.val);
                }else{
                    level.addFirst(node.val);
                }
                if(node.left != null) queue.offer(node.left);
                if(node.right != null) queue.offer(node.right);
            }
            ans.add(level);
            depth++;
        }
        return ans;
    }
}
```

### Extend & Reference

> 来源：力扣（LeetCode）
> 链接：https://leetcode-cn.com
> 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

### More

> **更多题解，请访问我的GitHub仓库：[LeetCode 刷题日记](https://github.com/ZhuoZhuoCrayon/my-Nodes/blob/master/Daily/README_2020.md)**
>
> ![alt](https://raw.githubusercontent.com/ZhuoZhuoCrayon/my-Nodes/master/Daily/img/mynode.png)
>
> [**更多笔记**](https://github.com/ZhuoZhuoCrayon/my-Nodes)：**`Vue` `Java SSM 框架` `阿里云服务器` `JavaScript/HTML/CSS`   `数据库` ...**
>
> **我的GitHub主页 -> [ZhuoZhuoCrayon](https://github.com/ZhuoZhuoCrayon)**
>
> 共勉~

