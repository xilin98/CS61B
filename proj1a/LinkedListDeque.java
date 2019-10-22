public class LinkedListDeque<T> {
    private Node sentinel;
    private int size;

    private  class Node {
         T item;
         Node next;
         Node prev;

        public Node(Node prev, T item, Node next) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
    }

    /** Creates an empty linked list deque */
    public LinkedListDeque(){
        sentinel = new Node(null, null, null);
        size = 0;
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
    }

    /** Adds an item of type T to the front of the deque. */
    public void addFirst(T item) {
        Node node = new Node(sentinel, item, sentinel.next);
        sentinel.next.prev = node;
        sentinel.next = node;
        size += 1;
    }

    /** Adds an item of type T to the back of the deque. */
    public void addLast(T item) {
        Node node = new Node(sentinel.prev, item, sentinel);
        sentinel.prev.next = node;
        sentinel.prev = node;
        size += 1;
    }

    /** Return true if deque is empty, false otherwise. */
    public boolean isEmpty() {
        return size == 0;
    }

    /** Return the number of item in the deque. */
    public int size() {
        return size;
    }

    /** Print the items in the deque from first to last, Separated by a space.
     * Once all items have been printed, print out a new line.
     */
    public void printDeque(){
        Node curr = sentinel.next;
        while(curr != sentinel) {
            System.out.print(curr.item + " ");
            curr = curr.next;
        }
        System.out.println();
    }

    /** Removes and returns the item at the front of the deque.
     * if no such items exists, return null.
     */
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T result = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size -= 1;
        return result;
    }

    /** Remove and return the item in the back of the deque.
     * If no such item exists, return null.
     */
    public T removeLast() {
        if (this.isEmpty()){
            return null;
        }
        T result = sentinel.prev.item;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        size -= 1;
        return result;
    }
    /** Gets the item at the given index,where o is the front,1 is the next item,and so forth.
     * If no such item exists,return null.
     * Didn't alter the deque.
     */
    public T get(int index) {
        Node curr = sentinel.next;
        while(index != 0 && curr != sentinel){
            index -= 1;
            curr = curr.next;
        }
        if (curr == sentinel) {
            return null;
        }
        return curr.item;
    }

    /** Add an item of type T to the front of deque. */
    public LinkedListDeque(LinkedListDeque other) {
        sentinel = new Node(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;

        Node pt_result = sentinel.next;
        Node pt_other =  other.sentinel.next;
        while(pt_other != sentinel){
            pt_result = new Node(pt_result.prev, (T) pt_other.item, sentinel);
            sentinel.prev = pt_result;
            pt_result = pt_result.next;
            pt_other = pt_other.next;
            size +=1;
        }
    }

    /** Same as get, but uses recursion. */
    public T getRecursive(int index) {
        return getRecursiveHelper(sentinel.next, index);
    }

    public T getRecursiveHelper(Node curr, int index) {
        if (curr == sentinel) {
            return null;
        }else if(index == 0) {
            return curr.item;
        }else {
            return getRecursiveHelper(curr.next, index-1);
        }
    }

}
