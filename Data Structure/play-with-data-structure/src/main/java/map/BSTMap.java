package map;

/**
 * 基于二分搜索树实现的映射
 *
 * @author Angus
 * @date 2018/10/10
 */
public class BSTMap<K extends Comparable<K>, V> implements Map<K, V> {

    private class Node {
        public K key;
        public V value;
        /**
         * 左右孩子
         */
        public Node left, right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            left = null;
            right = null;
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
    @Override
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

    @Override
    public V remove(K key) {
        Node delNode = getNode(root, key);
        if (delNode != null) {
            root = remove(root, key);
            return delNode.value;
        }
        return null;
    }

    @Override
    public boolean contains(K key) {
        return getNode(root, key) != null;
    }

    @Override
    public V get(K key) {
        Node node = getNode(root, key);
        return node != null ? node.value : null;
    }

    @Override
    public void set(K key, V value) {
        Node node = getNode(root, key);
        if (node == null) {
            throw new IllegalArgumentException(key + " doesn't exist!");
        }
        node.value = value;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
