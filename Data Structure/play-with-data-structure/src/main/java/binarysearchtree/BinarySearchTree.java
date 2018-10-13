package binarysearchtree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 二分搜索树的简单实现
 *
 * @author Angus
 * @date 2018/10/9
 */
public class BinarySearchTree<E extends Comparable<E>> {

    /**
     * 节点用私有内部类表示
     */
    private class Node {
        public E e;
        /**
         * 左右孩子
         */
        public Node left, right;

        public Node(E e) {
            this.e = e;
            left = null;
            right = null;
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }

    private Node root;
    private int size;

    public BinarySearchTree() {
        root = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public Node root() {
        return root;
    }


    /**
     * 递归实现添加元素
     *
     * @param e
     */
    public void add(E e) {
        root = add(root, e);
    }

    /**
     * 向以 node 为根的二分搜索树中插入元素 e（递归算法），返回新的子树根节点
     *
     * @param node
     * @param e
     */
    private Node add(Node node, E e) {
        // 在空节点处插入
        if (node == null) {
            size++;
            return new Node(e);
        }
        // 不考虑相同元素的情况
        if (e.compareTo(node.e) < 0) {
            node.left = add(node.left, e);
        } else if (e.compareTo(node.e) > 0) {
            node.right = add(node.right, e);
        }
        return node;
    }

    /**
     * 非递归实现插入元素
     *
     * @param e
     */
    public void addNr(E e) {
        if (root == null) {
            root = new Node(e);
            return;
        }
        Node node = root;
        while (true) {
            if (e.compareTo(node.e) < 0) {
                if (node.left == null) {
                    node.left = new Node(e);
                    break;
                } else {
                    node = node.left;
                }
            } else if (e.compareTo(node.e) > 0) {
                if (node.right == null) {
                    node.right = new Node(e);
                    break;
                } else {
                    node = node.right;
                }
            } else {
                break;
            }
        }
    }

    /**
     * 查看二分搜索树中是否包含元素 e
     *
     * @param e
     * @return
     */
    public boolean contains(E e) {
        return contains(root, e);
    }

    private boolean contains(Node node, E e) {
        if (node == null) {
            return false;
        }
        if (e.compareTo(node.e) < 0) {
            return contains(node.left, e);
        } else if (e.compareTo(node.e) > 0) {
            return contains(node.right, e);
        } else {
            return true;
        }
    }

    /**
     * 前序遍历（递归方式）
     */
    public void preOrder() {
        preOrder(root);
    }

    private void preOrder(Node node) {
        if (node == null) {
            return;
        }
        System.out.println(node);
        preOrder(node.left);
        preOrder(node.right);
    }

    /**
     * 前序遍历（非递归方式 - 借助栈实现）
     */
    public void preOrderNr() {
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            if (cur == null) {
                break;
            }
            System.out.println(cur);
            if (cur.right != null) {
                stack.push(cur.right);
            }
            if (cur.left != null) {
                stack.push(cur.left);
            }
        }
    }

    /**
     * 中序遍历
     */
    public void inOrder() {
        inOrder(root);
    }

    private void inOrder(Node node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);
        System.out.println(node);
        inOrder(node.right);
    }

    /**
     * 后序遍历
     */
    public void postOrder() {
        postOrder(root);
    }

    private void postOrder(Node node) {
        if (node == null) {
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node);
    }

    /**
     * 层序遍历（借助队列实现）
     */
    public void levelOrder() {
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node cur = queue.remove();
            System.out.println(cur);
            if (cur.left != null) {
                queue.add(cur.left);
            }
            if (cur.right != null) {
                queue.add(cur.right);
            }
        }
    }

    /**
     * 寻找二分搜索树的最小元素
     *
     * @return
     */
    public E minimum() {
        if (size == 0) {
            throw new IllegalStateException("BST is empty!");
        }
        return minimum(root).e;
    }

    private Node minimum(Node node) {
        if (node.left == null) {
            return node;
        }
        return minimum(node.left);
    }

    /**
     * 寻找二分搜索树的最大元素
     *
     * @return
     */
    public E maximum() {
        if (size == 0) {
            throw new IllegalStateException("BST is empty!");
        }
        return maximum(root).e;
    }

    private Node maximum(Node node) {
        if (node.right == null) {
            return node;
        }
        return maximum(node.right);
    }

    /**
     * 从二分搜索树中删除最小值所在节点，返回最小值
     *
     * @return
     */
    public E removeMin() {
        E ret = minimum();
        root = removeMin(root);
        return ret;
    }

    /**
     * 删除掉以 node 为根的二分搜索树中的最小节点，返回删除节点后的新的二分搜索树的根
     *
     * @param node
     * @return
     */
    private Node removeMin(Node node) {
        // 递归到待删除节点
        if (node.left == null) {
            Node rightNode = node.right;
            node.right = null;
            size--;
            // 返回右子树作为新的根
            return rightNode;
        }
        node.left = removeMin(node.left);
        return node;
    }

    /**
     * 从二分搜索树中删除最大值所在节点，返回最大值
     *
     * @return
     */
    public E removeMax() {
        E ret = maximum();
        root = removeMax(root);
        return ret;
    }

    private Node removeMax(Node node) {
        if (node.right == null) {
            Node leftNode = node.left;
            node.left = null;
            size--;
            return leftNode;
        }
        node.right = removeMax(node.right);
        return node;
    }

    /**
     * 从二分搜索树中删除元素为 e 的节点
     *
     * @param e
     */
    public void remove(E e) {
        // 将新的根返回回来
        root = remove(root, e);
    }

    /**
     * 删除以 node 为根的二分搜索树中值为 e 的节点，返回删除节点后的新的二分搜索树的根（递归方式）
     *
     * @param node
     * @param e
     * @return
     */
    private Node remove(Node node, E e) {
        if (node == null) {
            // 未找到待删除元素
            return null;
        }
        if (e.compareTo(node.e) < 0) {
            // 待删除节点在左子树中
            node.left = remove(node.left, e);
            return node;
        } else if (e.compareTo(node.e) > 0) {
            // 待删除节点在右子树中
            node.right = remove(node.right, e);
            return node;
        } else {
            // 当前为待删除节点
            if (node.left == null) {
                // 待删除节点左子树为空
                Node rightNode = node.right;
                node.right = null;
                size--;
                // 返回右子树作为新的根
                return rightNode;
            } else if (node.right == null) {
                // 待删除节点右子树为空
                Node leftNode = node.left;
                node.left = null;
                size--;
                // 返回左子树作为新的根
                return leftNode;
            } else {
                // 左右子树都存在的情况，返回后继节点（右子树最小值）作为新的根
                Node successor = minimum(node.right);
                // 该后继节点右子树等于节点右子树删除该后继节点后的子树
                successor.right = removeMin(node.right);
                successor.left = node.left;
                node.left = null;
                node.right = null;
                return successor;
            }
        }
    }

    private String print(Node node) {
        if (node == null) {
            return null;
        }
        StringBuilder res = new StringBuilder();
        // 打印左节点
        if (node.left != null) {
            res.append("(");
            res.append(print(node.left));
            res.append(")");
        }
        // 打印根
        res.append(" " + node + " ");
        // 打印右节点
        if (node.right != null) {
            res.append("(");
            res.append(print(node.right));
            res.append(")");
        }
        return res.toString();
    }

    @Override
    public String toString() {
        return print(root);
    }
}
