import java.util.NoSuchElementException;
import java.util.ArrayList;

/**
 * This class checks the correctness of the implementation of the methods defined in the class
 * ArtworkGallery.
 *
 * @author Steven Ren
 */
public class ArtGalleryTester {

    /**
     * Checks the correctness of the implementation of both compareTo() and equals() methods defined
     * in the Artwork class.
     *
     * @return true when this test verifies a correct functionality, and false otherwise
     */
    public static boolean testArtworkCompareToEquals() {
        boolean testPass = false;
        try {
            // test compareTo()
            Artwork art1 = new Artwork(" Starry Night, Van Gogh", 1889, 2000.0);
            Artwork art2 = new Artwork(" Starry Night, Van Gogh", 1889, 3000.0);
            if (art1.compareTo(art2) == -1) {
                testPass = true;
            } else {
                return false;
            }

            // test equals()
            Artwork art3 = new Artwork(" Starry Night, Van Gogh", 1889, 2000.0);
            Artwork art4 = new Artwork(" Starry Night, Van Gogh", 1889, 3000.0);
            if (art3.equals(art4)) {
                testPass = true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }

        return testPass; // Default return statement added to resolve compiler errors
    }

    /**
     * Checks the correctness of the implementation of both addArtwork() and toString() methods
     * implemented in the ArtworkGallery class. This unit test considers at least the following
     * scenarios. (1) Create a new empty ArtworkGallery, and check that its size is 0, it is empty,
     * and that its string representation is an empty string "". (2) try adding one artwork and then
     * check that the addArtwork() method call returns true, the tree is not empty, its size is 1,
     * and the .toString() called on the tree returns the expected output. (3) Try adding another
     * artwork which is smaller that the artwork at the root, (4) Try adding a third artwork which
     * is greater than the one at the root, (5) Try adding at least two further artwork such that
     * one must be added at the left subtree, and the other at the right subtree. For all the above
     * scenarios, and more, double check each time that size() method returns the expected value,
     * the add method call returns true, and that the .toString() method returns the expected string
     * representation of the contents of the binary search tree in an increasing order from the
     * smallest to the greatest artwork with respect to year, cost, and then name. (6) Try adding a
     * artwork already stored in the tree. Make sure that the addArtwork() method call returned
     * false, and that the size of the tree did not change.
     *
     * @return true when this test verifies a correct functionality, and false otherwise
     */
    public static boolean testAddArtworkToStringSize() {
        boolean testPass = false;
        ArtGallery art = new ArtGallery();
        // tests scenario (1)
        if (art.size() == 0 && art.isEmpty() && art.toString().equals("")) {
            testPass = true;
        } else {
            return false;
        }

        // tests scenario (2)
        String expected = "[(Name: Mona Lisa, DaVinci) (Year: 1503) (Cost: $1000.0)]";
        if (art.addArtwork(new Artwork("Mona Lisa, DaVinci", 1503, 1000.0)) && !art.isEmpty() && art.size() == 1 && art.toString().trim().equals(expected)) {
            testPass = true;
        } else {
            return false;
        }

        // tests scenario (3)
        String expected2 =
                "[(Name: testArt, testArtist) (Year: 1400) (Cost: $800.0)]\n[(Name: " + "Mona " +
                        "Lisa, DaVinci) (Year: 1503) (Cost: $1000.0)]";
        if (art.addArtwork(new Artwork("testArt, testArtist", 1400, 800.0)) && !art.isEmpty() && art.size() == 2 && art.toString().trim().equals(expected2)) {
            testPass = true;
        } else {
            return false;
        }

        // tests scenario (4)
        String expected3 =
                "[(Name: testArt, testArtist) (Year: 1400) (Cost: $800.0)]\n[(Name: " + "Mona " +
                        "Lisa, DaVinci) (Year: 1503) (Cost: $1000.0)]\n[(Name: testArt2, " +
                        "testArtist2) (Year: 1600) (Cost: $1200.0)]";
        if (art.addArtwork(new Artwork("testArt2, testArtist2", 1600, 1200.0)) && !art.isEmpty() && art.size() == 3 && art.toString().trim().equals(expected3)) {
            testPass = true;
        } else {
            return false;
        }

        // tests scenario (5)
        String expected4 = "[(Name: testArt3, testArtist3) (Year: 1200) (Cost: $700.0)]\n[(Name: "
                + "testArt, testArtist) (Year: 1400) (Cost: $800.0)]\n[(Name: " + "Mona Lisa, " +
                "DaVinci) (Year: 1503) (Cost: $1000.0)]\n[(Name: testArt2, " + "testArtist2) " +
                "(Year: 1600) (Cost: $1200.0)]";
        if (art.addArtwork(new Artwork("testArt3, testArtist3", 1200, 700.0)) && !art.isEmpty() && art.size() == 4 && art.toString().trim().equals(expected4)) {
            testPass = true;
        } else {
            return false;
        }
        String expected5 = "[(Name: testArt3, testArtist3) (Year: 1200) (Cost: $700.0)]\n[(Name: "
                + "testArt, testArtist) (Year: 1400) (Cost: $800.0)]\n[(Name: " + "Mona Lisa, " +
                "DaVinci) (Year: 1503) (Cost: $1000.0)]\n[(Name: testArt2, " + "testArtist2) " +
                "(Year: 1600) (Cost: $1200.0)]\n[(Name: testArt4, testArtist4) " + "(Year: 2000) " +
                "(Cost: $2000.0)]";
        if (art.addArtwork(new Artwork("testArt4, testArtist4", 2000, 2000.0)) && !art.isEmpty() && art.size() == 5 && art.toString().trim().equals(expected5)) {
            testPass = true;
        } else {
            return false;
        }

        // tests scenario (6)
        if (!art.addArtwork(new Artwork("testArt4, testArtist4", 2000, 2000.0)) && art.size() == 5) {
            testPass = true;
        } else {
            return false;
        }

        return testPass;
    }

    /**
     * This method checks mainly for the correctness of the ArtworkGallery.lookup() method. It must
     * consider at least the following test scenarios. (1) Create a new ArtworkGallery. Then, check
     * that calling the lookup() method on an empty ArtworkGallery returns false. (2) Consider a
     * ArtworkGallery of height 3 which lookup at least 5 artwork. Then, try to call lookup() method
     * to search for the artwork having a match at the root of the tree. (3) Then, search for a
     * artwork at the right and left subtrees at different levels considering successful and
     * unsuccessful search operations. Make sure that the lookup() method returns the expected
     * output for every method call.
     *
     * @return true when this test verifies a correct functionality, and false otherwise
     */
    public static boolean testLookup() {
        boolean testPass = false;
        ArtGallery art = new ArtGallery();

        // tests scenario (1)
        if (art.lookup("Mona Lisa, DaVinci", 1503, 1000.0) == false) {
            testPass = true;
        } else {
            return false;
        }

        art.addArtwork(new Artwork("Mona Lisa, DaVinci", 1503, 1000.0));
        art.addArtwork(new Artwork("TestArt, TestArtist", 1444, 900.0));
        art.addArtwork(new Artwork("Starry Night, Van Gogh", 1889, 2000.0));
        art.addArtwork(new Artwork("Guernica, Picasso", 1937, 3000.0));
        art.addArtwork(new Artwork("NightHawks, Hopper", 1942, 4000.0));

        // tests scenario (2)
        if (art.lookup("Mona Lisa, DaVinci", 1503, 1000.0)) {
            testPass = true;
        } else {
            return false;
        }

        art.addArtwork(new Artwork("TestArt2, TestArtist2", 1400, 800.0));
        art.addArtwork(new Artwork("TestArt3, TestArtist3", 1900, 2500.0));

        // tests scenario (3)
        if (art.lookup("TestArt2, TestArtist2", 1400, 800.0)) {
            testPass = true;
        } else {
            return false;
        }
        if (art.lookup("TestArt3, TestArtist3", 1900, 2500.0)) {
            testPass = true;
        } else {
            return false;
        }

        return testPass; // Default return statement added to resolve compiler errors
    }

    /**
     * Checks for the correctness of ArtworkGallery.height() method. This test must consider several
     * scenarios such as, (1) ensures that the height of an empty artwork tree is zero. (2) ensures
     * that the height of a tree which consists of only one node is 1. (3) ensures that the height
     * of a ArtworkGallery with the following structure for instance, is 4. (*) /  \ (*)  (*) \   /
     * \ (*) (*) (*) / (*)
     *
     * @return true when this test verifies a correct functionality, and false otherwise
     */
    public static boolean testHeight() {
        boolean testPass = false;
        ArtGallery art = new ArtGallery();

        // tests scenario (1)
        if (art.height() == 0) {
            testPass = true;
        } else {
            return false;
        }

        art.addArtwork(new Artwork("Mona Lisa, DaVinci", 1503, 1000.0));

        // tests scenario (2)
        if (art.height() == 1) {
            testPass = true;
        } else {
            return false;
        }

        art.addArtwork(new Artwork("TestArt2, TestArtist2", 1400, 800.0));
        art.addArtwork(new Artwork("TestArt, TestArtist", 1444, 900.0));
        art.addArtwork(new Artwork("TestArt3, TestArtist3", 1900, 2500.0));
        art.addArtwork(new Artwork("Starry Night, Van Gogh", 1889, 2000.0));
        art.addArtwork(new Artwork("Guernica, Picasso", 1937, 3000.0));
        art.addArtwork(new Artwork("NightHawks, Hopper", 1942, 4000.0));

        // tests scenario (3)
        if (art.height() == 4) {
            testPass = true;
        } else {
            return false;
        }

        return testPass; // Default return statement added to resolve compiler errors
    }

    /**
     * Checks for the correctness of ArtworkGallery.getBestArtwork() method.
     *
     * @return true when this test verifies a correct functionality, and false otherwise
     */
    public static boolean testGetBestArtwork() {
        boolean testPass = false;
        ArtGallery art = new ArtGallery();
        art.addArtwork(new Artwork("Mona Lisa, DaVinci", 1503, 1000.0));
        art.addArtwork(new Artwork("TestArt, TestArtist", 1444, 900.0));
        art.addArtwork(new Artwork("Starry Night, Van Gogh", 1889, 2000.0));
        art.addArtwork(new Artwork("Guernica, Picasso", 1937, 3000.0));
        art.addArtwork(new Artwork("NightHawks, Hopper", 1942, 4000.0));

        String expected = "[(Name: NightHawks, Hopper) (Year: 1942) (Cost: $4000.0)]";
        if (art.getBestArtwork().toString().trim().equals(expected)) {
            testPass = true;
        } else {
            return false;
        }
        return testPass; // Default return statement added to resolve compiler errors
    }


    /**
     * Checks for the correctness of ArtworkGallery.lookupAll() method. This test must consider at
     * least 3 test scenarios. (1) Ensures that the ArtworkGallery.lookupAll() method returns an
     * empty arraylist when called on an empty tree. (2) Ensures that the ArtworkGallery.lookupAll()
     * method returns an array list which contains all the artwork satisfying the search criteria of
     * year and cost, when called on a non empty artwork tree with one match, and two matches and
     * more. Vary your search criteria such that the lookupAll() method must check in left and right
     * subtrees. (3) Ensures that the ArtworkGallery.lookupAll() method returns an empty arraylist
     * when called on a non-empty artwork tree with no search results found.
     *
     * @return true when this test verifies a correct functionality, and false otherwise
     */
    public static boolean testLookupAll() {
        boolean testPass = false;
        ArtGallery art = new ArtGallery();
        ArrayList<Artwork> artCollection = new ArrayList<>();

        // tests scenario (1)
        if (art.lookupAll(1600, 1500.0).equals(artCollection)) {
            testPass = true;
        } else {
            return false;
        }

        Artwork art1 = new Artwork("Mona Lisa, DaVinci", 1503, 1000.0);
        Artwork art2 = new Artwork("TestArt, TestArtist", 1444, 900.0);
        Artwork art3 = new Artwork("Starry Night, Van Gogh", 1889, 2000.0);
        Artwork art4 = new Artwork("Guernica, Picasso", 1937, 3000.0);
        Artwork art5 = new Artwork("NightHawks, Hopper", 1942, 4000.0);

        art.addArtwork(art1);
        art.addArtwork(art2);
        art.addArtwork(art3);
        art.addArtwork(art4);
        art.addArtwork(art5);

        // tests scenario (2)
        artCollection.add(art1);
        artCollection.add(art2);
        if (art.lookupAll(1600, 1500.0).equals(artCollection)) {
            testPass = true;
        } else {
            return false;
        }

        ArrayList<Artwork> artCollection2 = new ArrayList<>();
        artCollection2.add(art2);
        if (art.lookupAll(1500, 950.0).equals(artCollection2)) {
            testPass = true;
        } else {
            return false;
        }

        // test scenario (3)
        ArrayList<Artwork> artCollection3 = new ArrayList<>();
        if (art.lookupAll(1000, 500.0).equals(artCollection3)) {
            testPass = true;
        } else {
            return false;
        }

        return testPass; // Default return statement added to resolve compiler errors
    }

    /**
     * Checks for the correctness of ArtworkGallery.buyArtwork() method. This test must consider at
     * least 3 test scenarios. (1) Buying artwork that is at leaf node (2) Buying artwork at
     * non-leaf node (3) ensures that the ArtworkGallery.buyArtwork() method throws a
     * NoSuchElementException when called on an artwork that is not present in the BST
     *
     * @return true when this test verifies a correct functionality, and false otherwise
     */
    public static boolean testBuyArtwork() {
        boolean testPass = false;

        try {
            ArtGallery art = new ArtGallery();
            Artwork art1 = new Artwork("Mona Lisa, DaVinci", 1503, 1000.0);
            Artwork art2 = new Artwork("TestArt, TestArtist", 1444, 900.0);
            art.addArtwork(art1);
            art.addArtwork(art2);

            // tests scenario (1)
            String expected = "[(Name: Mona Lisa, DaVinci) (Year: 1503) (Cost: $1000.0)]";
            art.buyArtwork("TestArt, TestArtist", 1444, 900.0);
            if (art.toString().trim().equals(expected) && art.size() == 1) {
                testPass = true;
            } else {
                return false;
            }

            // tests scenario (2)
            art.addArtwork(art2);
            Artwork art3 = new Artwork("TestArt2, TestArtist2", 1600, 1100.0);
            art.addArtwork(art3);
            Artwork art4 = new Artwork("TestArt3, TestArtist3", 1700, 1200.0);
            art.addArtwork(art4);

            String expected2 = "[(Name: TestArt, TestArtist) (Year: 1444) (Cost: $900.0)]\n[" +
                    "(Name: Mona Lisa, DaVinci) (Year: 1503) (Cost: $1000.0)]\n[(Name: " +
                    "TestArt2, TestArtist2) (Year: 1600) (Cost: $1100.0)]";
            art.buyArtwork("TestArt3, TestArtist3", 1700, 1200.0);
            if (art.toString().trim().equals(expected2) && art.size() == 3) {
                testPass = true;
            } else {
                return false;
            }

            art.addArtwork(art2);
            Artwork art5 = new Artwork("TestArt4, TestArtist4", 1400, 800.0);
            Artwork art6 = new Artwork("TestArt5, TestArtist5", 1450, 950.0);
            art.addArtwork(art5);
            art.addArtwork(art6);

            art.buyArtwork("TestArt5, TestArtist5", 1450, 950.0);
            String expected3 = "[(Name: TestArt4, TestArtist4) (Year: 1400) (Cost: $800.0)]\n[" +
                    "(Name: TestArt, TestArtist) (Year: 1444) (Cost: $900.0)]\n[(Name: Mona Lisa, " +
                    "DaVinci) (Year: 1503) (Cost: $1000.0)]\n[(Name: TestArt2, TestArtist2) (Year: " +
                    "1600) (Cost: $1100.0)]";;
            if (art.toString().trim().equals(expected3) && art.size() == 4) {
                testPass = true;
            } else {
                return false;
            }

            // tests scenario (3)
            try {
                art.buyArtwork("Potato, PotatoKing", 1111, 10.0);
                return false;
            } catch (NoSuchElementException e) {
                testPass = true;
            }
        } catch (Exception e) {
            return false;
        }

        return testPass; // Default return statement added to resolve compiler errors
    }

    /**
     * Returns false if any of the tester methods defined in this tester class fails.
     *
     * @return false if any of the tester methods defined in this tester class fails, and true if
     * all tests pass
     */
    public static boolean runAllTests() {
        return testArtworkCompareToEquals() && testAddArtworkToStringSize() && testLookup() &&
                testHeight() && testGetBestArtwork() && testLookupAll() && testBuyArtwork();
    }

    /**
     * Calls the test methods
     *
     * @param args input arguments if any
     */
    public static void main(String[] args) {
        System.out.println("testArtworkCompareToEquals(): " + testArtworkCompareToEquals());
        System.out.println("testAddArtworkToStringSize(): " + testAddArtworkToStringSize());
        System.out.println("testLookup(): " + testLookup());
        System.out.println("testHeight(): " + testHeight());
        System.out.println("testGetBestArtwork(): " + testGetBestArtwork());
        System.out.println("testLookupAll(): " + testLookupAll());
        System.out.println("testBuyArtwork(): " + testBuyArtwork());
        System.out.println("runAllTests(): " + runAllTests());
    }

}
