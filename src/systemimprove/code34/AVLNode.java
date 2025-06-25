package systemimprove.code34;

public class AVLNode<K extends Comparable<K>, V> {
    AVLNode<K,V> left;

    AVLNode<K,V> right;

    int height;

    K k;

    V v;

    AVLNode(K k) {
        this.k = k;
        this.height = 1;
    }
}
