package redblacktree;

/**
 * 红黑树的简单实现
 * 注意： 红黑树与 2-3 树有着本质的统一性
 * <p>
 * 《算法导论》中的红黑树
 * 1、每个节点或是红色，或者是黑色的
 * 2、根节点是黑色的
 * 3、每一个叶子节点（最后的空节点）是黑色的
 * 4、如果一个节点是红色，那么他的孩子节点都是黑色的
 * （附加：类似可知道如果一个节点是黑色的，那他的右孩子一定是黑色，左孩子则不确定。所有的红色节点都是向左倾斜的）
 * 5、从任意一个节点到叶子节点，经过的黑色节点是一样的
 * （类比于 2-3 树即可得，因 2-3 树有绝对平衡性）
 *
 * @author Angus
 * @date 2018/10/21
 */
public class RedBlackTree<K extends Comparable<K>, V> {

    /**
     * 节点为红色，表明该节点与父节点构成了 2-3 树中的 3 节点
     */
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private class Node {
        public K key;
        public V value;
        public Node left, right;
        /**
         * 红黑树节点颜色
         */
        public boolean color;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            left = null;
            right = null;
            // 节点初始时为红色（新添加的节点都是与其父节点融合）
            color = RED;
        }

        @Override
        public String toString() {
            return key.toString() + " : " + value.toString();
        }
    }

    private Node root;
    private int size;

    public RedBlackTree() {
        root = null;
        size = 0;
    }

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
     * 左旋转（类似 2-3 树中将 2 节点转变为 3 节点，亦包含一个临时的 4 节点状况）
     * 即给黑节点添加了一个红色的右节点后，要调换两个节点的位置，保证红节点的左倾斜性
     * <p>
     * ----node              x
     * ---/   \            /  \
     * -T1    x   --->   node  T3
     * ------/ \        /  \
     * ----T2  T3      T1  T2
     *
     * @param node
     * @return
     */
    private Node leftRotate(Node node) {
        Node x = node.right;
        // 左旋转
        node.right = x.left;
        x.left = node;
        // 注意这一步很重要，不能破坏原节点的特性。必须使 x 节点处于原 node 节点所在的节点（可能为 2 节点，也可能为 3节点，右旋转同理）
        x.color = node.color;
        node.color = RED;

        return x;
    }

    /**
     * 颜色反转
     * 将一个临时 4-节点，即“红-黑-红”转变为 3 个 2-节点，即“黑-红-黑”，让原根节点与其父节点融合
     *
     * @param node
     */
    private void fileColors(Node node) {
        node.color = RED;
        node.left.color = BLACK;
        node.right.color = BLACK;
    }

    /**
     * 右旋转
     * 即添加的节点在 3-节点的左侧时（形成了红红黑），此时需要将原根节点的左节点（中间的红色节点）选为根节点
     * <p>
     * -------node              x
     * ------/   \            /  \
     * -----x    T3  --->   T1  node
     * ---/ \                   /  \
     * -T1  T2                 T2  T3
     *
     * @param node
     * @return
     */
    private Node rightRotate(Node node) {
        Node x = node.left;
        // 右旋转
        node.left = x.right;
        x.right = node;
        // 注意这一步很重要，不能破坏原节点的特性
        x.color = node.color;
        node.color = RED;
        return x;
    }

    /**
     * 递归实现添加元素
     *
     * @param key
     * @param value
     */
    public void add(K key, V value) {
        root = add(root, key, value);
        // 保证红黑树中根节点的颜色为黑色
        root.color = BLACK;
    }

    /*
     * 红黑树添加新元素所有情况，及其变换
     *
     * ● - BLACK  ○ - RED
     *
     * -------● 添加一个节点后的所有情况如下、
     *
     * -------●         左旋转          ●
     * --------\    -------------->    /
     * ---------○                     ○
     *
     * ---------●
     * --------/   添加一个节点后的所有情况如下
     * -------○
     *
     * ---------●                        ●
     * --------/                        /
     * -------○       中间节点左旋转    ○     顶层节点右旋转       ●         颜色反转          ○
     * --------\     -------------->  /     -------------->    /  \    -------------->    /   \
     * ---------○                    ○                        ○    ○                     ●    ●
     */
    /**
     * 向以 node 为根的红黑树中插入元素 e（递归算法），返回新的子树根节点
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
            // 默认插入红色节点
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

        // 红黑树性质维护
        // 判断该节点是否需要左旋转
        if (isRed(node.right) && !isRed(node.left)) {
            node = leftRotate(node);
        }
        // 判断是否需要右旋转
        if (isRed(node.left) && isRed(node.left.left)) {
            node = rightRotate(node);
        }
        // 判断是否需要颜色反转
        if (isRed(node.left) && isRed(node.right)) {
            fileColors(node);
        }
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
        if (key.compareTo(node.key) < 0) {
            // 待删除节点在左子树中
            node.left = remove(node.left, key);
            return node;
        } else if (key.compareTo(node.key) > 0) {
            // 待删除节点在右子树中
            node.right = remove(node.right, key);
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

    private boolean isRed(Node node) {
        // 空节点为黑色
        if(node == null) {
            return BLACK;
        }
        return node.color;
    }
}
