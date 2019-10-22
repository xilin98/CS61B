/** A Deque interface.
 * @author infinete*/
public interface Deque<T> {
    /** Adds an item of type T to the front of the deque.
     * @param item */
    void addFirst(T item);

    /** Adds an item of type T to the back of the deque.
     * @param item */
    void addLast(T item);

    /** Return the number of items in the Deque. */
    int size();

    /** Prints the items in the Deque from first to last, separated by a space.
     * Once all the items have been printed, print out a new line.
     */
    void printDeque();

    /** Removes and return the item at the front of the deque.
     * If no such item exits, return null.
     */
    T removeFirst();

    /** Removes and return the item at the back of the deque.
     * If no such item exits, returns null.
     */
    T removeLast();

    /** Gets the item at the given index, where 0 is the front,
     * 1 is the next item,
     * and so forth.
     * If no such item exists, return null.
     * @param  index
     */
    T get(int index);

    /** Return true if deque is empty, false otherwise. */
    default boolean isEmpty() {
        return size() == 0;
    }
}