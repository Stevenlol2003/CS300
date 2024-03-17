import java.util.Iterator;
import java.util.NoSuchElementException;

public class SongPlayer implements Iterable<Song> {
    private int size; // size of the list
    private LinkedNode<Song> head; // head of this doubly linked list
    private LinkedNode<Song> tail; // tail of this doubly linked list
    private boolean playingBackward; // true if this song player is reading the list backward

    /**
     * Creates a new instance of song player which contains zero songs and set by default to play
     * songs in the forward direction. [Implementing this constructor is optional since it will be
     * added by default by the compiler]
     */
    public SongPlayer() {

    }

    /**
     * Adds a Song as Last Song
     *
     * @param oneSong - the song that is going to be added to the tail of this doubly linked list of
     *                songs
     */
    public void addLast(Song oneSong) {
        LinkedNode lastNode = new LinkedNode(null, oneSong, null);

        if (isEmpty()) {
            head = lastNode; // sets both head and tail if song player was empty
            tail = lastNode;
        } else {
            tail.setNext(lastNode);
            lastNode.setPrev(tail);
            tail = lastNode; // adds the song as tail and updates the original tail to 2nd to
            // last song
        }

        this.size++;
    }

    /**
     * add Song as First Song
     *
     * @param oneSong - the song that is going to be added to the head of this doubly linked list of
     *                songs
     * @throws NullPointerException - with a descriptive error message if the passed oneSong is
     *                              null
     */
    public void addFirst(Song oneSong) throws NullPointerException {
        if (oneSong == null) {
            throw new NullPointerException("the passed oneSong is null");
        }

        LinkedNode firstNode = new LinkedNode(null, oneSong, null);

        if (isEmpty()) {
            head = firstNode; // sets both head and tail if song player was empty
            tail = firstNode;
        } else {
            firstNode.setNext(head);
            head.setPrev(firstNode);
            head = firstNode; // adds the song as head and updates the original head to 2nd song
        }

        this.size++;
    }

    /**
     * adds Song at a given position/order within this collection of songs
     *
     * @param index   - the given index where the new song will be added
     * @param oneSong - the song that is going to be added
     * @throws NullPointerException      - with a descriptive error message if the passed oneSong is
     *                                   null
     * @throws IndexOutOfBoundsException - with a descriptive error message if index is out of the 0
     *                                   .. size() range
     */
    public void add(int index, Song oneSong) throws NullPointerException,
            IndexOutOfBoundsException {
        if (oneSong == null) {
            throw new NullPointerException("the passed oneSong is null");
        }
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException("index of out of the 0 to size() range");
        }

        LinkedNode addedSong = new LinkedNode(null, oneSong, null);
        if (index == 0) {
            addFirst(oneSong); // if index = 0, which is add as first
        } else if (index == size) {
            addLast(oneSong); // if index = size, which is add as last
        } else {
            LinkedNode currentSong = head;
            for (int i = 0; i < index - 1; ++i) {
                currentSong = currentSong.getNext(); // keeps looping until index is reached
            }
            addedSong.setNext(currentSong.getNext());
            currentSong.setNext(addedSong);
            addedSong.setPrev(currentSong);
            addedSong.getNext().setPrev(addedSong); // links previous, added song, and next song
        }

        this.size++;
    }

    /**
     * Returns the first Song in this list.
     *
     * @return the Song at the head of this list
     * @throws NoSuchElementException - with a descriptive error message if this list is empty
     */
    public Song getFirst() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException("this list is empty");
        }

        return head.getData();
    }

    /**
     * Returns the last Song in this list.
     *
     * @return the Song at the tail of this list
     * @throws NoSuchElementException - with a descriptive error message if this list is empty
     */
    public Song getLast() throws NoSuchElementException {
        if (size() == 0) {
            throw new NoSuchElementException("this list is empty");
        }

        return tail.getData();
    }

    /**
     * Returns the song at the specified position in this list.
     *
     * @param index - index of the song to return
     * @return the song at the specified position in this list
     * @throws IndexOutOfBoundsException - with a descriptive error message if index is out of the 0
     *                                   .. size()-1 range
     */
    public Song get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index > (size() - 1)) {
            throw new IndexOutOfBoundsException("index of out of the 0 to size() range");
        }

        LinkedNode currentSong = head;
        for (int i = 0; i < index; ++i) {
            currentSong = currentSong.getNext(); // keeps looping until index's song is reached
        }

        return (Song) currentSong.getData();
    }

    /**
     * Removes and returns the first song from this list.
     *
     * @return the first song from this list
     * @throws NoSuchElementException - with a descriptive error message if this list is empty
     */
    public Song removeFirst() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException("this list is empty");
        }

        Song originalFirstSong = getFirst();
        if (head.getNext() != null) {
            head = head.getNext();
            head.setPrev(null);  // sets head as the original 2nd song and sets the previous of
            // this new head as null
        }
        else if (head.getNext() == null) {
            head = null; // if there is only 1 song goes to this else if
            tail = null;
        }

        size--;
        return originalFirstSong;
    }

    /**
     * Removes and returns the last song from this list.
     *
     * @return the last song from this list
     * @throws NoSuchElementException - with a descriptive error message if this list is empty
     */
    public Song removeLast() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException("this list is empty");
        }

        Song originalLastSong = getLast();
        if (tail.getPrev() != null) {
            tail = tail.getPrev();
            tail.setNext(null); // sets tail as the original 2nd to last song and sets the next of
            // this new tail as null
        }
        else if (tail.getPrev() == null) {
            head = null; // if there is only 1 song goes to this else if
            tail = null;
        }

        size--;
        return originalLastSong;
    }

    /**
     * Removes the song at the specified position in this list and returns the song that was removed
     * from the list. The order of precedence of the other songs in the list should not be
     * modified.
     *
     * @param index - the index of the song to be removed
     * @return the song previously at the specified position
     * @throws IndexOutOfBoundsException - with a descriptive error message if index is out of the 0
     *                                   .. size()-1 range
     */
    public Song remove(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index > size() - 1) {
            throw new IndexOutOfBoundsException("index of out of the 0 to size() range");
        }

        if (index == 0) {
            return removeFirst(); // if index = 0, which is the first song
        }
        else if (index == size - 1) {
            return removeLast(); // if index = size - 1, which is the last song
        }
        else {
            LinkedNode currentSong = head;
            for (int i = 0; i < index; ++i) {
                currentSong = currentSong.getNext(); // keeps looping until the index's song
            }
            // links previousSong and nextSong
            LinkedNode previousSong = currentSong.getPrev();
            LinkedNode nextSong = currentSong.getNext();
            previousSong.setNext(nextSong);
            nextSong.setPrev(previousSong);

            size--;
            return (Song) currentSong.getData();
        }
    }

    /**
     * Returns true if this list contains a match with the specified song. More formally, returns
     * true if and only if this list contains at least one song e such that Objects.equals(o, e).
     *
     * @param o - song whose presence in this list is to be tested
     * @return true if this list contains the specified song
     */
    public boolean contains(Song o) {
        boolean isFound = false;

        LinkedNode currentSong = head;
        for (int i = 0; i < size; ++i) {
            if (currentSong.getData().equals(o)) {
                isFound = true;
            }
            currentSong = currentSong.getNext();
        }

        return isFound;
    }

    /**
     * Removes all of the songs from this list. The list will be empty after this call returns.
     */
    public void clear() {
        this.size = 0;
        this.head = null;
        this.tail = null;
    }

    /**
     * Returns true if this list is empty.
     *
     * @return true if this list is empty
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Returns the number of songs in this list.
     *
     * @return the number of songs in this list
     */
    public int size() {
        return this.size;
    }

    /**
     * Returns an iterator to iterate through the songs in this list with respect to current playing
     * direction of this song player (either in the forward or in the backward direction)
     *
     * @return an Iterator to traverse the list of songs in this SongPlayer with respect to the
     * current playing direction specified by the playingBackward data field.
     */
    @Override
    public Iterator<Song> iterator() {
        if (!playingBackward) {
            return new ForwardSongIterator(head);
        }
        else {
            return new BackwardSongIterator(tail);
        }
    }

    /**
     * Mutator of the playingDirection of this song player. It switches the playing direction by
     * setting playingBackward to its opposite value.
     */
    public void switchPlayingDirection() {
        this.playingBackward = !playingBackward;
    }

    /**
     * Plays the songs in this song player in the current playing direction. This method MUST be
     * implemented using an enhanced for-each loop.
     *
     * @return a String representation of the songs in this song player. String representations of
     * each song are separated by a newline. If this song player is empty, this method returns an
     * empty string.
     */
    public String play() {
        Iterator<Song> iterator = iterator();
        String toReturn = "";
        if (isEmpty()) {
            return toReturn; // return empty string if song player is empty
        }
        while (iterator.hasNext()) {
            toReturn += iterator.next().toString() + "\n"; // loops and adds song to the string
        }

        return toReturn;
    }

}
