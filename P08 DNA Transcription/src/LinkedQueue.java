import java.util.NoSuchElementException;

public class LinkedQueue<T> implements QueueADT<T> {
    protected Node<T> back; // The node at the back of the queue, added MOST recently
    protected Node<T> front; // The node at the front of the queue, added LEAST recently
    private int n; // The number of elements in the queue

    /**
     * Adds the given data to this queue; every addition to a queue is made at the back
     *
     * @param data the data to add
     */
    public void enqueue(T data) {
        Node<T> lastNode = new Node(data, null);
        if (isEmpty()) {
            back = lastNode;
            front = back; // front = back if queue is empty
        } else {
            back.setNext(lastNode);
            back = lastNode;
        }
        n++;
    }

    /**
     * Removes and returns the item from this queue that was least recently added
     *
     * @return the item from this queue that was least recently added (front)
     * @throws NoSuchElementException if this queue is empty
     */
    public T dequeue() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Node<T> dequeuedNode = front;
        if (front.getNext() != null) {
            front = front.getNext();
        } else {
            front = null; // if the queue removes its only item
            back = null;
        }
        n--;
        return dequeuedNode.getData();
    }

    /**
     * Returns the item least recently added to this queue without removing it
     *
     * @return the item least recently added to this queue (front)
     * @throws NoSuchElementException if this queue is empty
     */
    public T peek() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return front.getData();
    }

    /**
     * Checks whether the queue contains any elements
     *
     * @return
     */
    public boolean isEmpty() {
        return n == 0;
    }

    /**
     * Returns the number of items in this queue
     *
     * @return the number of items in this queue
     */
    public int size() {
        return n;
    }

    /**
     * Returns a string representation of this queue, beginning at the front (least recently added)
     * of the queue and ending at the back (most recently added). An empty queue is represented as
     * an empty string; otherwise items in the queue are represented using their data separated by
     * spaces
     *
     * @return the sequence of items in FIFO order, separated by spaces
     */
    public String toString() {
        String toReturn = "";
        if (isEmpty()) {
            return toReturn;
        }

        Node<T> current = front;
        for (int i = 0; i < size(); ++i) {
            toReturn += current.getData() + " ";
            current = current.getNext();
        }

        return toReturn;
    }
}
