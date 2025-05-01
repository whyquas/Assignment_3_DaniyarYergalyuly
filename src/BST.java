import java.util.*;

public class BST<K extends Comparable<K>, V> implements Iterable<BST.KeyValue<K, V>> {
    private class Node {
        K key;
        V value;
        Node left, right;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public static class KeyValue<K, V> {
        private K key;
        private V value;

        public KeyValue(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() { return key; }
        public V getValue() { return value; }
    }

    private Node root;
    private int size = 0;

    public void put(K key, V value) {
        Node newNode = new Node(key, value);
        if (root == null) {
            root = newNode;
            size++;
            return;
        }

        Node parent = null;
        Node current = root;
        while (current != null) {
            parent = current;
            int cmp = key.compareTo(current.key);
            if (cmp < 0) current = current.left;
            else if (cmp > 0) current = current.right;
            else {
                current.value = value;
                return;
            }
        }

        if (key.compareTo(parent.key) < 0) parent.left = newNode;
        else parent.right = newNode;
        size++;
    }

    public V get(K key) {
        Node current = root;
        while (current != null) {
            int cmp = key.compareTo(current.key);
            if (cmp == 0) return current.value;
            else if (cmp < 0) current = current.left;
            else current = current.right;
        }
        return null;
    }

    public void delete(K key) {
        root = deleteNodeIterative(root, key);
    }

    private Node deleteNodeIterative(Node root, K key) {
        Node parent = null, current = root;
        while (current != null && !current.key.equals(key)) {
            parent = current;
            int cmp = key.compareTo(current.key);
            current = (cmp < 0) ? current.left : current.right;
        }

        if (current == null) return root;

        if (current.left == null || current.right == null) {
            Node newChild = (current.left != null) ? current.left : current.right;

            if (parent == null) return newChild;

            if (current == parent.left) parent.left = newChild;
            else parent.right = newChild;
        } else {
            Node successorParent = current;
            Node successor = current.right;

            while (successor.left != null) {
                successorParent = successor;
                successor = successor.left;
            }

            current.key = successor.key;
            current.value = successor.value;

            if (successorParent != current) successorParent.left = successor.right;
            else successorParent.right = successor.right;
        }

        size--;
        return root;
    }

    public int size() {
        return size;
    }

    @Override
    public Iterator<KeyValue<K, V>> iterator() {
        return new Iterator<>() {
            Stack<Node> stack = new Stack<>();
            { pushLeft(root); }

            private void pushLeft(Node node) {
                while (node != null) {
                    stack.push(node);
                    node = node.left;
                }
            }

            public boolean hasNext() {
                return !stack.isEmpty();
            }

            public KeyValue<K, V> next() {
                Node node = stack.pop();
                pushLeft(node.right);
                return new KeyValue<>(node.key, node.value);
            }
        };
    }
}
