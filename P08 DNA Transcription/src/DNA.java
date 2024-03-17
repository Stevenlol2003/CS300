import java.util.NoSuchElementException;

public class DNA {
    protected static String[][] mRNAtoProteinMap = {{"UUU", "F"}, {"UUC", "F"}, {"UUA", "L"}, {
        "UUG", "L"}, {"UCU", "S"}, {"UCC", "S"}, {"UCA", "S"}, {"UCG", "S"}, {"UAU", "Y"}, {"UAC"
            , "Y"}, {"UAA", "STOP"}, {"UAG", "STOP"}, {"UGU", "C"}, {"UGC", "C"}, {"UGA", "STOP"}
            , {"UGG", "W"}, {"CUU", "L"}, {"CUC", "L"}, {"CUA", "L"}, {"CUG", "L"}, {"CCU", "P"},
            {"CCC", "P"}, {"CCA", "P"}, {"CCG", "P"}, {"CAU", "H"}, {"CAC", "H"}, {"CAA", "Q"}, {
        "CAG", "Q"}, {"CGU", "R"}, {"CGC", "R"}, {"CGA", "R"}, {"CGG", "R"}, {"AUU", "I"}, {"AUC"
            , "I"}, {"AUA", "I"}, {"AUG", "M"}, {"ACU", "T"}, {"ACC", "T"}, {"ACA", "T"}, {"ACG",
            "T"}, {"AAU", "N"}, {"AAC", "N"}, {"AAA", "K"}, {"AAG", "K"}, {"AGU", "S"}, {"AGC",
            "S"}, {"AGA", "R"}, {"AGG", "R"}, {"GUU", "V"}, {"GUC", "V"}, {"GUA", "V"}, {"GUG",
            "V"}, {"GCU", "A"}, {"GCC", "A"}, {"GCA", "A"}, {"GCG", "A"}, {"GAU", "D"}, {"GAC",
            "D"}, {"GAA", "E"}, {"GAG", "E"}, {"GGU", "G"}, {"GGC", "G"}, {"GGA", "G"}, {"GGG",
            "G"}};
    protected LinkedQueue<Character> DNA; // The queue containing the original DNA sequence

    /**
     * Creates the DNA queue from the provided String. Each Node contains a single Character from
     * the sequence.
     *
     * @param sequence a String containing the original DNA sequence
     */
    public DNA(String sequence) {
        DNA = new LinkedQueue<>();
        for (int i = 0; i < sequence.length(); ++i) {
            DNA.enqueue(sequence.charAt(i));
        }
    }

    /**
     * Creates and returns a new queue of mRNA characters from the stored DNA. The transcription is
     * done one character at a time, as (A->U, T->A, C->G, G->C).
     *
     * @return the queue containing the mRNA sequence corresponding to the stored DNA sequence
     */
    public LinkedQueue<Character> transcribeDNA() {
        LinkedQueue<Character> mRNA = new LinkedQueue<>();
        String stringDNA = DNA.toString().replaceAll(" ", "");

        for (int i = 0; i < stringDNA.length(); ++i) {
            if (stringDNA.charAt(i) == 'A') {
                mRNA.enqueue('U');
            } else if (stringDNA.charAt(i) == 'T') {
                mRNA.enqueue('A');
            } else if (stringDNA.charAt(i) == 'C') {
                mRNA.enqueue('G');
            } else if (stringDNA.charAt(i) == 'G') {
                mRNA.enqueue('C');
            }
        }

        return mRNA;
    }

    /**
     * Creates and returns a new queue of amino acids from a provided queue of mRNA characters. The
     * translation is done three characters at a time, according to the static mRNAtoProteinMap
     * provided above. Translation ends either when you run out of mRNA characters OR when a STOP
     * codon is reached (i.e. the three-character sequence corresponds to the word STOP in the map,
     * rather than a single amino acid character).
     *
     * @param mRNA a queue containing the mRNA sequence corresponding to the stored DNA sequence
     * @return the queue containing the amino acid sequence corresponding to the provided mRNA
     * sequence
     */
    public LinkedQueue<String> mRNATranslate(LinkedQueue<Character> mRNA) {
        LinkedQueue<String> mRNATranslated = new LinkedQueue<>();

        while (!mRNA.isEmpty()) {
            try {
                String temp =
                        String.valueOf(mRNA.dequeue()) + String.valueOf(mRNA.dequeue()) +
                                String.valueOf(mRNA.dequeue());
                for (int i = 0; i < mRNAtoProteinMap.length; ++i) {
                    if (mRNAtoProteinMap[i][0].equals(temp)) {
                        if (mRNAtoProteinMap[i][0].equals("UAA") ||
                                mRNAtoProteinMap[i][0].equals("UAG") ||
                                mRNAtoProteinMap[i][0].equals("UGA")) {
                            return mRNATranslated;
                        }
                        mRNATranslated.enqueue(mRNAtoProteinMap[i][1]);
                    }
                }
            } catch (NoSuchElementException e) {
                return mRNATranslated;
            }

        }

        return mRNATranslated;
    }

    /**
     * A shortcut method that translates the stored DNA sequence to a queue of amino acids using the
     * other two methods in this class
     *
     * @return the queue containing the amino acid sequence corresponding to the stored DNA
     * sequence, via its mRNA transcription
     */
    public LinkedQueue<String> translateDNA() {
        LinkedQueue<String> mRNATranslated = mRNATranslate(transcribeDNA());

        return mRNATranslated;
    }
}
