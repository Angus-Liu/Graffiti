package avl;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Angus
 * @date 2018/10/20
 */
public class AVLTree<K extends Comparable<K>, V> {

    private class Node {
        public K key;
        public V value;
        public Node left, right;
        /**
         * Node 所在的高度
         */
        public int height;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            left = null;
            right = null;
            height = 1;
        }

        @Override
        public String toString() {
            return key.toString() + " : " + value.toString();
        }
    }

    private Node root;
    private int size;

    /**
     * 返回以 node 为根节点的二分搜索树中 key 所在的节点
     *
     * @param node
     * @param key
     * @return
     */
    private Node getNode(Node node, K key) {
        if (node == null) {
            return null;
        }
        if (key.equals(node.key)) {
            return node;
        } else if (key.compareTo(node.key) < 0) {
            return getNode(node.left, key);
        } else {
            return getNode(node.right, key);
        }
    }

    /**
     * 递归实现添加元素
     *
     * @param key
     * @param value
     */
    public void add(K key, V value) {
        root = add(root, key, value);
    }

    /**
     * 向以 node 为根的二分搜索树中插入元素 e（递归算法），返回新的子树根节点
     *
     * @param node
     * @param key
     * @param value
     * @return
     */
    private Node add(Node node, K key, V value) {
        // 在空节点处插入
        if (node == null) {
            size++;
            return new Node(key, value);
        }
        if (key.compareTo(node.key) < 0) {
            node.left = add(node.left, key, value);
        } else if (key.compareTo(node.key) > 0) {
            node.right = add(node.right, key, value);
        } else {
            // 更新已有节点的 value
            node.value = value;
        }
        // 更新 height
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        // 计算平衡因子
        int balanceFactor = getBalanceFactor(node);
        // 平衡维护
        if (balanceFactor > 1) {
            if (getBalanceFactor(node.left) >= 0) {
                // LL：插入元素在不平衡节点的左侧的左侧，从而导致的不平衡，使用右旋转进行维护
                return rightRotate(node);
            } else if (getBalanceFactor(node.left) < 0) {
                // LR：插入元素在不平衡节点的左侧的右侧，从而导致的不平衡，使用右旋转进行维护
                // 左孩子左旋转后，LR -> LL
                node.left = leftRotate(node.left);
                return rightRotate(node);
            }
        }
        if (balanceFactor < -1) {
            if (getBalanceFactor(node.right) <= 0) {
                // RR：插入元素在不平衡节点的右侧的右侧，从而导致的不平衡，使用左旋转进行维护
                return leftRotate(node);
            } else if (getBalanceFactor(node.right) > 0) {
                // RL：插入元素在不平衡节点的右侧的左侧，从而导致的不平衡，使用左旋转进行维护
                // 右孩子右旋转后，RL -> RR
                node.right = rightRotate(node.right);
                return leftRotate(node);
            }
        }
//        if (Math.abs(getBalanceFactor(node)) > 1) {
//            System.out.println("unbalanced: " + balanceFactor);
//        }
        return node;
    }

    /**
     * 找到以 node 为根的最小值所在节点
     *
     * @param node
     * @return
     */
    private Node minimum(Node node) {
        if (node.left == null) {
            return node;
        }
        return minimum(node.left);
    }

    /**
     * 删除以 node 为根的二分搜索树中值为 e 的节点，返回删除节点后的新的二分搜索树的根（递归方式）
     *
     * @param node
     * @param key
     * @return
     */
    private Node remove(Node node, K key) {
        if (node == null) {
            // 未找到待删除元素
            return null;
        }

        Node retNode;

        if (key.compareTo(node.key) < 0) {
            // 待删除节点在左子树中
            node.left = remove(node.left, key);
            retNode = node;
        } else if (key.compareTo(node.key) > 0) {
            // 待删除节点在右子树中
            node.right = remove(node.right, key);
            retNode = node;
        } else {
            // 当前为待删除节点
            if (node.left == null) {
                // 待删除节点左子树为空
                Node rightNode = node.right;
                node.right = null;
                size--;
                // 返回右子树作为新的根
                retNode = rightNode;
            } else if (node.right == null) {
                // 待删除节点右子树为空
                Node leftNode = node.left;
                node.left = null;
                size--;
                // 返回左子树作为新的根
                retNode = leftNode;
            } else {
                // 左右子树都存在的情况，返回后继节点（右子树最小值）作为新的根
                Node successor = minimum(node.right);
                // 该后继节点右子树等于节点右子树删除该后继节点后的子树
                // successor.right = removeMin(node.right);
                // 原 removeMin 没有维护平衡
                successor.right = remove(node.right, successor.key);
                successor.left = node.left;
                node.left = node.right = null;
                retNode = successor;
            }
        }
        if (retNode == null) {
            return null;
        }
        // 更新 height
        retNode.height = Math.max(getHeight(retNode.left), getHeight(retNode.right)) + 1;
        // 计算平衡因子
        int balanceFactor = getBalanceFactor(retNode);
        // 平衡维护（与添加节点时相同）
        if (balanceFactor > 1) {
            // 删除节点时一定要考虑等于 0 的情况，添加时则不用
            // -----O             O
            // ----/ \           /
            // ---O   O  --->   O
            // --/ \           / \
            // -O   O         O   O
            if (getBalanceFactor(retNode.left) >= 0) {
                // LL：插入元素在不平衡节点的左侧的左侧，从而导致的不平衡，使用右旋转进行维护
                return rightRotate(retNode);
            } else if (getBalanceFactor(retNode.left) < 0) {
                // LR：插入元素在不平衡节点的左侧的右侧，从而导致的不平衡，使用右旋转进行维护
                // 左孩子左旋转后，LR -> LL
                retNode.left = leftRotate(retNode.left);
                return rightRotate(retNode);
            }
        }
        if (balanceFactor < -1) {
            if (getBalanceFactor(retNode.right) <= 0) {
                // RR：插入元素在不平衡节点的右侧的右侧，从而导致的不平衡，使用左旋转进行维护
                return leftRotate(retNode);
            } else if (getBalanceFactor(retNode.right) > 0) {
                // RL：插入元素在不平衡节点的右侧的左侧，从而导致的不平衡，使用左旋转进行维护
                // 右孩子右旋转后，RL -> RR
                retNode.right = rightRotate(retNode.right);
                return leftRotate(retNode);
            }
        }
        return retNode;
    }

    public V remove(K key) {
        Node delNode = getNode(root, key);
        if (delNode != null) {
            root = remove(root, key);
            return delNode.value;
        }
        return null;
    }

    public boolean contains(K key) {
        return getNode(root, key) != null;
    }

    public V get(K key) {
        Node node = getNode(root, key);
        return node != null ? node.value : null;
    }

    public void set(K key, V value) {
        Node node = getNode(root, key);
        if (node == null) {
            throw new IllegalArgumentException(key + " doesn't exist!");
        }
        node.value = value;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 返回 node 所在的高度
     *
     * @param node
     * @return
     */
    private int getHeight(Node node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    /**
     * 计算该节点的平衡因子
     *
     * @param node
     * @return
     */
    private int getBalanceFactor(Node node) {
        if (node == null) {
            return 0;
        }
        return getHeight(node.left) - getHeight(node.right);
    }

    /**
     * 使用中序遍历，来判断当前的 AVL 是否依旧是一棵二分搜索树
     *
     * @return
     */
    public boolean isBST() {
        List<K> keys = new ArrayList<>();
        inOrder(root, keys);
        for (int i = 1; i < keys.size(); i++) {
            if (keys.get(i - 1).compareTo(keys.get(i)) > 0) {
                return false;
            }
        }
        return true;
    }

    private void inOrder(Node node, List<K> keys) {
        if (node == null) {
            return;
        }
        inOrder(node.left, keys);
        keys.add(node.key);
        inOrder(node.right, keys);
    }

    /**
     * 检查每个节点的平衡因子，判断该树是否是一课平衡树
     *
     * @return
     */
    public boolean isBalanced() {
        return isBalanced(root);
    }

    private boolean isBalanced(Node node) {
        if (node == null) {
            return true;
        }
        // 先判断该节点是否满足平衡二叉树性质
        int balanceFactor = getBalanceFactor(node);
        if (Math.abs(balanceFactor) > 1) {
            return false;
        }
        // 递归判断该节点的子树是否满足平衡二叉树性质
        return isBalanced(node.left) && isBalanced(node.right);
    }

    /**
     * 对节点 y 进行向右旋转操作，返回操作后的新的根节点 x
     * <p>
     * T1 < z < T2 < x < T3 < y < T4
     * <p>
     * --------------y
     * ------------/  \                     x
     * ----------x    T4                 /    \
     * --------/  \         ===>       z       y
     * ------z    T3                 /  \    /  \
     * ----/  \                    T1   T2 T3  T4
     * --T1   T2
     *
     * @param y
     * @return
     */
    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T3 = x.right;
        // 向右旋转过程
        x.right = y;
        y.left = T3;
        // 更新 x 和 y 节点的height值（T3 子树没有变更，不用更改）
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
        return x;
    }

    /**
     * 对节点 y 进行向左旋转操作，返回操作后的新的根节点 x
     * <p>
     * T1 < y < T2 < x < T3 < z < T4
     * <p>
     * ----------y
     * --------/  \                         x
     * ------T1    x                     /    \
     * ----------/  \         ===>     y       z
     * --------T2    z               /  \    /  \
     * ------------/  \            T1   T2 T3  T4
     * ----------T3   T4
     *
     * @param y
     * @return
     */
    private Node leftRotate(Node y) {
        Node x = y.right;
        Node T2 = x.left;
        // 向左旋转过程
        x.left = y;
        y.right = T2;
        // 更新 x 和 y 节点的height值（T3 子树没有变更，不用更改）
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
        return x;
    }


    /**
     * 对节点 y 进行向右旋转操作，返回操作后的新的根节点 x
     * <p>
     * T1 < x < T2 < z < T3 < y < T4
     * <p>
     * --------------y
     * ------------/  \                     z
     * ----------x    T4                 /    \
     * --------/  \         ===>       x       y
     * ------T1    z                 /  \    /  \
     * ----------/  \              T1   T2 T3  T4
     * --------T2   T3
     *
     * @param y
     * @return
     */
    private Node leftRightRotate(Node y) {
        Node x = y.left;
        Node z = x.right;
        Node T2 = z.left;
        Node T3 = z.right;
        // 进行旋转
        x.right = T2;
        y.left = T3;
        z.left = x;
        z.right = y;
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
        z.height = Math.max(getHeight(z.left), getHeight(z.right)) + 1;
        return z;
    }

    /**
     * 对节点 y 进行向左旋转操作，返回操作后的新的根节点 x
     * <p>
     * T1 < y < T2 < z < T3 < x < T4
     * <p>
     * ----------y
     * --------/  \                         z
     * ------T1    x                     /    \
     * ----------/  \         ===>     y       x
     * ---------z    T4              /  \    /  \
     * -------/  \                  T1   T2 T3  T4
     * -----T2   T3
     *
     * @param y
     * @return
     */
    private Node rightLeftRotate(Node y) {
        Node x = y.right;
        Node z = x.left;
        Node T2 = z.left;
        Node T3 = z.right;
        // 进行旋转
        x.left = T3;
        y.right = T2;
        z.left = y;
        z.right = x;
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
        z.height = Math.max(getHeight(z.left), getHeight(z.right)) + 1;
        return z;
    }
}