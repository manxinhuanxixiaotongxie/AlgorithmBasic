package systemimprove.code34;

public class SBTNode<K extends Comparable<K>, V> {
    K k;
    V v;
    SBTNode<K, V> left;
    SBTNode<K, V> right;
    int size;

    SBTNode(K k, V v) {
        this.k = k;
        this.v = v;
        this.size = 1;
    }

    SBTNode(K k) {
        this(k, null);
    }
}
