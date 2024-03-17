//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    SongPlayerTester
// Course:   CS 300 Spring 2022
//
// Author:   Steven Ren
// Email:    skren@wisc.edu
// Lecturer: Mouna Kacem
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    (name of your pair programming partner)
// Partner Email:   (email address of your programming partner)
// Partner Lecturer's Name: (name of your partner's lecturer)
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   ___ Write-up states that pair programming is allowed for this assignment.
//   ___ We have both read and understand the course Pair Programming Policy.
//   ___ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         NONE
// Online Sources:  NONE
//
///////////////////////////////////////////////////////////////////////////////

/**
 * This class implements unit test methods to check the correctness of Song, LinkedNode, SongPlayer
 * ForwardSongIterator and BackwardSongIterator classes in P07 Iterable Song Player assignment.
 */
public class SongPlayerTester {
    /**
     * This method test and make use of the Song constructor, an accessor (getter) method,
     * overridden method toString() and equal() method defined in the Song class.
     *
     * @return true when this test verifies a correct functionality, and false otherwise
     */
    public static boolean testSong() {
        boolean testPass = false;

        Song song1 = new Song("Run", "OneRepublic", "2:49");

        // checks getSomeName() method
        if (song1.getSongName().equals("Run")) {
            testPass = true;
        } else {
            return false;
        }

        // checks toString() method
        String expected = "Run---OneRepublic---2:49";
        if (song1.toString().equals(expected)) {
            testPass = true;
        } else {
            return false;
        }

        Object song2 = new Song("Run", "OneRepublic", "1:55");

        // checks equals() method
        if (song1.equals(song2)) {
            testPass = true;
        } else {
            return false;
        }

        return testPass;
    }

    /**
     * This method test and make use of the LinkedNode constructor, an accessor (getter) method, and
     * a mutator (setter) method defined in the LinkedCart class.
     *
     * @return true when this test verifies a correct functionality, and false otherwise
     */
    public static boolean testLinkedNode() {
        boolean testPass = false;

        Song song1 = new Song("Run", "OneRepublic", "2:49");
        Song song2 = new Song("Run?", "OneRepublic", "2:49");
        Song song3 = new Song("Don't Run", "OneRepublic", "2:49");

        LinkedNode node1 = new LinkedNode(null, song1, null);
        LinkedNode node2 = new LinkedNode(null, song2, null);
        LinkedNode node3 = new LinkedNode(null, song3, null);

        node1.setNext(node2);
        node2.setPrev(node1);
        node2.setNext(node3);
        node3.setPrev(node2);

        // checks getPrev() method
        if (node1.getPrev() == null) {
            testPass = true;
        } else {
            return false;
        }

        if (node2.getPrev() == node1) {
            testPass = true;
        } else {
            return false;
        }

        if (node3.getPrev() == node2) {
            testPass = true;
        } else {
            return false;
        }

        // checks getNext() method
        if (node2.getNext() == node3) {
            testPass = true;
        } else {
            return false;
        }

        // checks getData() method
        if (node3.getData().equals(song3)) {
            testPass = true;
        } else {
            return false;
        }

        // checks setNext() and setPrev() methods
        node1.setNext(node3);
        node3.setPrev(node1);
        if (node3.getPrev() == node1) {
            testPass = true;
        } else {
            return false;
        }

        return testPass;
    }

    /**
     * This method checks for the correctness of addFirst(), addLast() and add(int index) method in
     * SongPlayer class
     *
     * @return true when this test verifies a correct functionality, and false otherwise
     */
    public static boolean testSongPlayerAdd() {
        boolean testPass = false;

        // tests addFirst() when the song player is empty
        SongPlayer playList1 = new SongPlayer();
        Song song1 = new Song("Run", "OneRepublic", "2:49");
        playList1.addFirst(song1);

        int sizeExpected1 = 1;

        if (playList1.size() == sizeExpected1) {
            testPass = true;
        } else {
            return false;
        }

        // tests addFirst() when the song player has songs
        SongPlayer playList2 = new SongPlayer();
        Song song2 = new Song("Run?", "OneRepublic", "2:49");
        playList2.addFirst(song2);
        Song song3 = new Song("Run!", "OneRepublic", "2:49");
        playList2.addFirst(song3);

        int sizeExpected2 = 2;

        if (playList2.size() == sizeExpected2) {
            testPass = true;
        } else {
            return false;
        }

        // tests addLast() when the song player is empty
        SongPlayer playList3 = new SongPlayer();
        Song song4 = new Song("RUN", "OneRepublic", "2:49");
        playList3.addLast(song4);

        int sizeExpected3 = 1;

        if (playList3.size() == sizeExpected3) {
            testPass = true;
        } else {
            return false;
        }

        // tests addLast() when the song player has songs
        SongPlayer playList4 = new SongPlayer();
        Song song5 = new Song("RunRun", "OneRepublic", "2:49");
        playList4.addLast(song5);
        Song song6 = new Song("RunRun!", "OneRepublic", "2:49");
        playList4.addLast(song6);

        int sizeExpected4 = 2;

        if (playList4.size() == sizeExpected4) {
            testPass = true;
        } else {
            return false;
        }

        // tests add(int index)
        SongPlayer playList5 = new SongPlayer();
        Song song7 = new Song("RunRun1", "OneRepublic", "2:49");
        playList5.addFirst(song7);
        Song song8 = new Song("RunRun2", "OneRepublic", "2:49");
        playList5.addLast(song8);
        Song song9 = new Song("RunRun3", "OneRepublic", "2:49");
        playList5.addLast(song9);

        Song song10 = new Song("RunRun4", "OneRepublic", "2:49");
        playList5.add(1, song10);

        String expected = "RunRun4---OneRepublic---2:49";

        if (playList5.get(1).toString().equals(expected)) {
            testPass = true;
        } else {
            return false;
        }

        return testPass;
    }

    /**
     * This method checks for the correctness of getFirst(), getLast() and get(int index) method in
     * SongPlayer class
     *
     * @return true when this test verifies a correct functionality, and false otherwise
     */
    public static boolean testSongPlayerGet() {
        boolean testPass = false;

        SongPlayer playList1 = new SongPlayer();
        Song song1 = new Song("Run1", "OneRepublic", "2:49");
        Song song2 = new Song("Run2", "OneRepublic", "2:49");
        Song song3 = new Song("Run3", "OneRepublic", "2:49");
        playList1.addFirst(song1);
        playList1.addLast(song2);
        playList1.addLast(song3);

        // checks getFirst() method
        if (playList1.getFirst().equals(song1)) {
            testPass = true;
        } else {
            return false;
        }

        // checks getLast() method
        if (playList1.getLast().equals(song3)) {
            testPass = true;
        } else {
            return false;
        }

        // checks get(int index) method
        if (playList1.get(1).equals(song2)) {
            testPass = true;
        } else {
            return false;
        }

        return testPass;
    }

    /**
     * This method checks for the correctness of removeFirst(), removeLast() and remove(int index)
     * method in SongPlayer class
     *
     * @return true when this test verifies a correct functionality, and false otherwise
     */
    public static boolean testSongPlayerRemove() {
        boolean testPass = false;

        SongPlayer playList1 = new SongPlayer();
        Song song1 = new Song("Run", "OneRepublic", "2:49");
        playList1.addFirst(song1);
        Song song2 = new Song("Heat Waves", "Glass Animals", "3:59");
        playList1.addLast(song2);
        Song song3 = new Song("Bad Habits", "Ed Sheeran", "3:51");
        playList1.addLast(song3);
        Song song4 = new Song("Maniac", "Conan Gray", "3:06");
        playList1.addLast(song4);
        Song song5 = new Song("WITHOUT YOU", "The Kid LAROI", "3:51");
        playList1.addLast(song5);

        // tests removeFirst() method
        if (playList1.removeFirst().equals(song1)) {
            testPass = true;
        } else {
            return false;
        }

        // tests removeLast() method
        if (playList1.removeLast().equals(song5)) {
            testPass = true;
        } else {
            return false;
        }

        // tests remove(int index) method
        if (playList1.remove(1).equals(song3)) {
            testPass = true;
        } else {
            return false;
        }

        return testPass;
    }

    /**
     * This method checks for the correctness of iterator(), switchPlayingDirection() and String
     * play() method in SongPlayer class
     *
     * @return true when this test verifies a correct functionality, and false otherwise
     */
    public static boolean testSongPlayerIterator() {
        boolean testPass = false;

        // tests iterator() and play() methods
        SongPlayer playList1 = new SongPlayer();
        Song song1 = new Song("Run", "OneRepublic", "2:49");
        playList1.addFirst(song1);
        Song song2 = new Song("Heat Waves", "Glass Animals", "3:59");
        playList1.addFirst(song2);
        Song song3 = new Song("Bad Habits", "Ed Sheeran", "3:51");
        playList1.addFirst(song3);

        String expected = "Bad Habits---Ed Sheeran---3:51\n" + "Heat Waves---Glass " +
                "Animals---3:59\n" + "Run---OneRepublic---2:49\n";

        if (expected.equals(playList1.play())) {
            testPass = true;
        } else {
            return false;
        }

        // tests switchPlayingDirection() method
        playList1.switchPlayingDirection();

        String expected2 = "Run---OneRepublic---2:49\nHeat Waves---Glass Animals---3:59\n" +
                "Bad Habits---Ed Sheeran---3:51\n";
        if (expected2.equals(playList1.play())) {
            testPass = true;
        } else {
            return false;
        }

        return testPass;
    }

    /**
     * This method checks for the correctness of contains(Object song), clear(), isEmpty()and size()
     * method in SongPlayer class
     *
     * @return true when this test verifies a correct functionality, and false otherwise
     */
    public static boolean testSongPlayerCommonMethod() {
        boolean testPass = false;

        SongPlayer playList1 = new SongPlayer();
        Song song1 = new Song("Run1", "OneRepublic", "2:49");
        Song song2 = new Song("Run2", "OneRepublic", "2:49");
        Song song3 = new Song("Run3", "OneRepublic", "2:49");
        playList1.addFirst(song1);
        playList1.addLast(song2);
        playList1.addLast(song3);

        // tests contains(Object song) method
        if (playList1.contains(song2)) {
            testPass = true;
        } else {
            return false;
        }

        // tests clear(), isEmpty(), and size() methods
        playList1.clear();

        if (playList1.isEmpty()) {
            testPass = true;
        } else {
            return false;
        }

        if (playList1.size() == 0) {
            testPass = true;
        } else {
            return false;
        }

        return testPass;
    }

    /**
     * This method checks for the correctness of ForwardSongIterator class
     *
     * @return true when this test verifies a correct functionality, and false otherwise
     */
    public static boolean testForwardSongIterator() {
        boolean testPass = false;

        Song song1 = new Song("Run", "OneRepublic", "2:49");
        Song song2 = new Song("Run?", "OneRepublic", "2:49");
        Song song3 = new Song("Don't Run", "OneRepublic", "2:49");

        LinkedNode node1 = new LinkedNode(null, song1, null);
        LinkedNode node2 = new LinkedNode(null, song2, null);
        LinkedNode node3 = new LinkedNode(null, song3, null);

        node1.setNext(node2);
        node2.setPrev(node1);
        node2.setNext(node3);
        node3.setPrev(node2);

        ForwardSongIterator forward = new ForwardSongIterator(node1);

        // expected returned song is this (the first song, song1) because the first call of Iterator
        // .next() method MUSTbalways returns the first item in the collection if it is not
        // empty, the next call of next() must return the second item in the collection, etc
        String expected = "Run---OneRepublic---2:49";

        if (forward.hasNext()) {
            testPass = true;
        } else {
            return false;
        }
        if (forward.next().toString().equals(expected)) {
            testPass = true;
        } else {
            return false;
        }

        return testPass;
    }

    /**
     * This method checks for the correctness of BackwardSongIterator class
     *
     * @return true when this test verifies a correct functionality, and false otherwise
     */
    public static boolean testBackwardSongIterator() {
        boolean testPass = false;

        Song song1 = new Song("Run", "OneRepublic", "2:49");
        Song song2 = new Song("Run?", "OneRepublic", "2:49");
        Song song3 = new Song("Don't Run", "OneRepublic", "2:49");

        LinkedNode node1 = new LinkedNode(null, song1, null);
        LinkedNode node2 = new LinkedNode(null, song2, null);
        LinkedNode node3 = new LinkedNode(null, song3, null);

        node1.setNext(node2);
        node2.setPrev(node1);
        node2.setNext(node3);
        node3.setPrev(node2);

        BackwardSongIterator backward = new BackwardSongIterator(node2);

        // should return node2's song2
        String expected = "Run?---OneRepublic---2:49";

        if (backward.hasNext()) {
            testPass = true;
        } else {
            return false;
        }
        if (backward.next().toString().equals(expected)) {
            testPass = true;
        } else {
            return false;
        }

        return testPass;
    }

    /**
     * This method calls all the test methods defined and implemented in your SongPlayerTester
     * class.
     *
     * @return true if all the test methods defined in this class pass, and false otherwise.
     */
    public static boolean runAllTests() {
        return testSong() && testLinkedNode() && testSongPlayerAdd() && testSongPlayerGet() &&
                testSongPlayerRemove() && testSongPlayerIterator() && testSongPlayerCommonMethod()
                && testForwardSongIterator() && testBackwardSongIterator();
    }

    /**
     * Driver method defined in this SongPlayerTester class
     *
     * @param args input arguments if any.
     */
    public static void main(String[] args) {
        System.out.println("testSong(): " + testSong());
        System.out.println("testLinkedNode(): " + testLinkedNode());
        System.out.println("testSongPlayerAdd(): " + testSongPlayerAdd());
        System.out.println("testSongPlayerGet(): " + testSongPlayerGet());
        System.out.println("testSongPlayerRemove(): " + testSongPlayerRemove());
        System.out.println("testSongPlayerIterator(): " + testSongPlayerIterator());
        System.out.println("testSongPlayerCommonMethod(): " + testSongPlayerCommonMethod());
        System.out.println("testForwardSongIterator(): " + testForwardSongIterator());
        System.out.println("testBackwardSongIterator(): " + testBackwardSongIterator());
        System.out.println("runAllTests(): " + runAllTests());
    }
}
