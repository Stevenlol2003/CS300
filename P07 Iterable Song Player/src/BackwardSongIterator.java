import java.util.Iterator;
import java.util.NoSuchElementException;

public class BackwardSongIterator implements Iterator<Song> {
    private LinkedNode<Song> next; // reference to the next linked node in a list of nodes

    /**
     * Creates a new iterator which iterates through songs in back/tail to front/head order
     *
     * @param last - reference to the tail of a doubly linked list of songs
     */
    public BackwardSongIterator(LinkedNode<Song> last) {
        next = last;
    }

    /**
     * Checks whether there are more songs to return in the reverse order
     *
     * @return true if there are more songs to return in the reverse order
     */
    @Override
    public boolean hasNext() {
        if (next != null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns the next song in the iteration
     *
     * @throws NoSuchElementException - with a descriptive error message if there are no more songs
     *                                to return in the reverse order (meaning if this.hasNext()
     *                                returns false)
     */
    @Override
    public Song next() throws NoSuchElementException {
        if (!hasNext()) {
            throw new NoSuchElementException("no more songs to return in the reverse order");
        }

        Song toReturn = next.getData();
        next = next.getPrev();
        return toReturn;
    }

}
