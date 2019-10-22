public class ArrayDeque<T> {
    private T[] container;
    private int size;
    private int nextFirst;
    private int nextLast;
    private static final int INIT_CAPACITY = 8;
    private static final int RFACTOR = 2;
    private static final double MIN_USAGE_RATIO = 0.25;

    /**Creates an empty array deque.
     * the starting size of your array should be 8.
     */
    public ArrayDeque() {
        container = (T[]) new Object[INIT_CAPACITY];
        size = 0;
        nextFirst = 4;
        nextLast = 5;
    }

    /**Create a deep copy of other. */
    public ArrayDeque(ArrayDeque other) {
        container = (T[]) new Object[INIT_CAPACITY];
        size = 0;
        nextFirst = 4;
        nextLast = 5;

        for (int i = 0; i < other.size(); i++) {
            addLast((T) other.get(i));
        }
    }
    private int minusOne(int index) {
        return (index - 1 + container.length) % container.length;
    }

    private int plusOne(int index) {
        return (index + 1) % container.length;
    }

    private void resize(int capacity) {
        T[] new_container = (T[]) new Object[capacity];

        int curr = plusOne(nextFirst);
        for (int i = 0; i < size; i++) {
            new_container[i] = container[curr];
            curr = plusOne(curr);
        }

        container = new_container;
        nextFirst = capacity - 1;
        nextLast = size;
    }

    /** Add an item of type T to the front of the deque. */
    public void addFirst(T item) {
        if (size == container.length) {
            resize(size * RFACTOR);
        }

        container[nextFirst] = item;
        size += 1;
        nextFirst = minusOne(nextFirst);
    }

    /** Add an item of type T to the back of the deque. */
    public void addLast(T item) {
        if (size == container.length){
            resize(size * RFACTOR);
        }

        container[nextLast] = item;
        size += 1;
        nextLast = plusOne(nextLast);
    }

    /** Return true if deque is empty, false otherwise. */
    public boolean isEmpty() {
        return size == 0;
    }

    /** Return the number of items in the deque.
     * Must take constant time.
     */
    public int size() {
        return size;
    }

    /** Print the items in the deque from first to the last. separated by a space.
     * Once all the items have been printed, print out a new line.
     */
    public void printDeque() {
        for (int i = plusOne(nextFirst); i < plusOne(nextFirst) + size; i++) {
            System.out.print(container[i % container.length] + "");
        }
        System.out.println();
    }

    /** Removes and return the item at the front of the deque.
     * If no such item exists, return null.
     */
    public T removeFirst() {
        if (size == 0) {
            return null;
        }

        int first = plusOne(nextFirst);
        T firstItem = container[first];
        container[first] = null;
        nextFirst = first;
        size -= 1;

        if (container.length >= 16 && size < container.length * MIN_USAGE_RATIO) {
            resize(container.length / 2);
        }

        return firstItem;
    }

    /** Removes and returns the item at the back of deque .
     * If no such item exists, return null.
     */
    public T removeLast() {
        if (size == 0) {
            return null;
        }

        int last = minusOne(nextLast);
        T lastItem = container[last];
        container[last] = null;
        nextLast = last;
        size -= 1;

        if (container.length >= 16 && size < container.length * MIN_USAGE_RATIO) {
            resize(container.length / 2);
        }

        return lastItem;
    }

    /** Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     * If no such item exists, return null.
     * Must not alter the deque and must take constant time.
     */
    public T get(int index){
        if (size == 0) {
            return null;
        }

        int first = plusOne(nextFirst);
        return container[(index + first) % container.length];
    }
}