package book_02linkedlist;

/**
 * @Description
 * @Author Scurry
 * @Date 2023-04-14 10:40
 */
public class RandomNode {

    public int value;
    public RandomNode next;
    /**
     * random指针是Node新增的指针，可以指向链表的任何一个位置，也可以指向null
     */
    public RandomNode random;

    public RandomNode(int value) {
        this.value = value;
    }

}
