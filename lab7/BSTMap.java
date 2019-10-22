import java.util.Set;
import java.util.Iterator;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    private Node root;

    private class Node {
        private K k;
        private V v;
        private Node left, right;
        private int size;

        public Node(K k, V v, int size) {
            this.k = k;
            this.v = v;
            this.size = size;
        }
    }

    public BSTMap() {
    }
    @Override
    public void clear() {
        root = null;
    }

    @Override
    public boolean containsKey(K k) {
        if (k == null) throw new IllegalArgumentException("argument to contains() is null");
        return get(k) != null;
    }
    @Override
    public V get(K k) {
        return get(root, k);
    }

    private V get(Node n, K k) {
        if (k == null) throw new IllegalArgumentException("calls get() with a null key");
        if (n == null) return null;
        int cmp = k.compareTo(n.k);
        if (cmp < 0) return get(n.left, k);
        else if (cmp > 0) return get(n.right, k);
        else return n.v;
    }

    @Override
    public int size() {
        if (root == null) return 0;
        return root.size;
    }

    private int size(Node n) {
        if (n == null) return 0;
        return n.size;
    }

    @Override
    public void put(K k, V v) {
        if (k == null) throw new IllegalArgumentException("calls put with a null key");
        root = put(root, k, v);
    }

    private Node put(Node n, K k, V v) {
        if (n == null) return new Node(k, v, 1);
        int cmp = k.compareTo(n.k);
        if (cmp < 0) n.left = put(n.left, k, v);
        else if (cmp > 0) n.right = put (n.right, k, v);
        else n.v = v;
        n.size = 1 + size(n.left) + size(n.right);
        return n;
    }

    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K k) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K k, V v) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }
}
