import org.w3c.dom.Node;
import java.util.Iterator;


public class BST <K extends Comparable<K>, V>{
    private Node root;

    private class Node {
        private K key;
        private V val;
        private Node left, right;

        public Node(K key, V val) {
            this.key = key;
            this.val = val;
            left = right = null;
        }
    }

    public BST(){
        root = null;
    }

    public BST(K key, V val){
        root = new Node(key, val);
    }

    public void put(K key, V val) {
        root = insert(root, key, val);
    }

    private Node insert(Node root, K key, V val){
        if (root == null){
            root = new Node(key, val);
            return root;
        }
        if (key.compareTo(root.key) < 0){
            root.left = insert(root.left, key, val);
        }
        else{
            root.right = insert(root.right, key, val);
        }
        return root;
    }

    public void inOrder(){
        inOrder(root);
    }

    
    private void inOrder(Node root){
        if (root != null){
            inOrder(root.left);
            System.out.println(root.key + " -> " + root.val);
            inOrder(root.right);
        }
    }

    public V get(K key){
        return get(root, key);
    }

    private V get(Node root, K key){
        if (root == null){
            return null;
        }
        if (root.key.compareTo(key) == 0){
            return root.val;
        }
        if (root.key.compareTo(key) > 0) return get(root.left, key);
        return get(root.right, key);
    }

    public void delete(K key){
        delete(root, key);
    }

    private Node delete(Node root, K key){
        if (root == null) return root;

        if (key.compareTo(root.key) < 0){
            root.left = delete(root.left, key);
        }
        else if (key.compareTo(root.key) > 0){
            root.right = delete(root.right, key);
        }
        else{
            if (root.left == null){
                return root.right;
            }
            else if (root.right == null){
                return root.left;
            }
            root.key = minNode(root.right);
            root.right = delete(root.right, root.key);
        }
        return root;
    }

    private K minNode(Node root){
        K min = root.key;
        while(root.left != null){
            mn = root.left.key;
            root = root.left;
        }
        return min;
    }

   

    public Iterator<K> iterator()
    {
        return new Iterator<K>()
        {
            private int count = 0;

            @Override
            public boolean hasNext()
            {
                return count++ < size;
            }

            @Override
            public Node next()
            {
                if (!hasNext())
                    throw new NoSuchElementException();



                if (current.right != null)
                    moveLeft(current.right);

                return current;
            }

            public K search(root, K key)
            {
                if (root == null || key.compareTo(root.key) == 0)
                {
                    return root.key;
                }

                if (key.compareTo(root.key) < 0)
                {
                    return search(root.left, key);
                }

                return search(root.right, key);
            }
        };
}
