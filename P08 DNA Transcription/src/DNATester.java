import java.util.NoSuchElementException;

/**
 * Test methods to verify your implementation of the methods for P08.
 */
public class DNATester {

    /**
     * Tests enqueue() and dequeue() methods
     *
     * @return true if and only if the method works correctly
     */
    public static boolean testEnqueueDequeue() {
        boolean testPass = false;
        try {
            LinkedQueue newQueue = new LinkedQueue();

            // tests enqueue methods()
            newQueue.enqueue("G");
            newQueue.enqueue("G");
            newQueue.enqueue("A");

            String queue1 =
                    String.valueOf(newQueue.dequeue()) + String.valueOf(newQueue.dequeue()) +
                            String.valueOf(newQueue.dequeue());
            System.out.println(queue1);
            String expected1 = "GGA";
            if (queue1.equals(expected1)) {
                testPass = true;
            }

            // tests dequeue(0 method
            LinkedQueue newQueue2 = new LinkedQueue();
            try {
                newQueue2.dequeue();
                return false;
            } catch (NoSuchElementException e) {
                testPass = true;
            }
        } catch (Exception e) {
            return false;
        }

        return testPass;
    }

    /**
     * Tests the transcribeDNA() method
     *
     * @return true if and only if the method works correctly
     */
    public static boolean testTranscribeDNA() {
        DNA testDNA = new DNA("GGAGTCAGTTAAGCGACCGGGACATACTGTCTTGGTAATCTCCGAGCTAGAAAGTCTCTG");
        String mRNA = "CCUCAGUCAAUUCGCUGGCCCUGUAUGACAGAACCAUUAGAGGCUCGAUCUUUCAGAGAC";
        System.out.println(testDNA.transcribeDNA().toString());
        if (testDNA.transcribeDNA().toString().replaceAll(" ", "").equals(mRNA)) {
            return true;
        }
        return false;
    }

    /**
     * Tests the translateDNA() method
     *
     * @return true if and only if the method works correctly
     */
    public static boolean testTranslateDNA() {
        DNA testDNA =
                new DNA("GGAGTCAGTTAAGCGACCGGGACATACTGTCTTGGTAATCTCCGAGCTAGAAAGTCTCTG");
        try {
            System.out.println(testDNA.translateDNA().toString());
            if (testDNA.translateDNA().toString().replaceAll(" ", "").equals(
                    "PQSIRWPCMTEPLEARSFRD")) {
                return true;
            }
        } catch (NoSuchElementException e) {
            return false;
        }
        return false;
    }

    /**
     * Tests the mRNATranslate() method
     *
     * @return true if and only if the method works correctly
     */
    public static boolean testMRNATranslate() {
        DNA testDNA =
                new DNA("GGAGTCAGTTAAGCGACCGGGACATACTGTCTTGGTAATCTCCGAGCTAGAAAGTCTCTG");
        System.out.println(testDNA.transcribeDNA().toString().replaceAll(" ", ""));

        if (testDNA.transcribeDNA().toString().replaceAll(" ", "").equals(
                "CCUCAGUCAAUUCGCUGGCCCUGUAUGACAGAACCAUUAGAGGCUCGAUCUUUCAGAGAC")) {
            return true;
        }
        return false;
    }

    public static boolean testQueueSize() {
        boolean testPass = false;
        try {
            LinkedQueue newQueue = new LinkedQueue();

            newQueue.enqueue("G");
            newQueue.enqueue("G");
            newQueue.enqueue("A");

            // tests size() method
            if (newQueue.size() == 3) {
                testPass = true;
            } else {
                return false;
            }

            //tests isEmpty() method
            if (!newQueue.isEmpty()) {
                testPass = true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
        return testPass;
    }

    /**
     * Main method - use this to run your test methods and output the results (ungraded)
     *
     * @param args unused
     */
    public static void main(String[] args) {
        System.out.println("testEnqueueDequeue: " + testEnqueueDequeue());
        System.out.println("transcribeDNA: " + testTranscribeDNA());
        System.out.println("translateDNA: " + testTranslateDNA());
        System.out.println("MRNATranslate: " + testMRNATranslate());
        System.out.println("testQueueSize: " + testQueueSize());
    }
}
