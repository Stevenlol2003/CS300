import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.zip.DataFormatException;
import java.util.Scanner;

/**
 * This class contains tester methods for the ExceptionalShoppingCart class
 */

public class ExceptionalShoppingCartTester {
    /**
     * Main method
     *
     * @param args input arguments if any
     */
    public static void main(String[] args) {
        System.out.println("testLookupMethods(): " + testLookupMethods());
        System.out.println("testAddItemToMarketCatalog(): " + testAddItemToMarketCatalog());
        System.out.println("testContainsAndNbOccurrences(): " + testContainsAndNbOccurrences());
        System.out.println("testSaveCartSummary(): " + testSaveCartSummary());
        System.out.println("testLoadCartSummary(): " + testLoadCartSummary());
        System.out.println("runAllTests(): " + runAllTests());
    }

    // tests lookupProductByName and lookupProductById methods' exception handling
    public static boolean testLookupMethods() {
        try {
            ExceptionalShoppingCart.lookupProductByName("No match found");
        } catch (NoSuchElementException e) {
            return true; //exception caught
        }
        try {
            ExceptionalShoppingCart.lookupProductById(333);
        } catch (IllegalArgumentException e) {
            return true; // exception caught
        }
        try {
            ExceptionalShoppingCart.lookupProductById(3333);
        } catch (NoSuchElementException e) {
            return true; // exception caught
        }
        return false; //did not catch the exceptions
    }

    // tests addItemToMarketCatalog method's exception handling
    public static boolean testAddItemToMarketCatalog() {
        try {
            ExceptionalShoppingCart.addItemToMarketCatalog("44A4", "Potato", "$5.55");
        } catch (IllegalArgumentException e) {
            return true; // exception caught
        }
        try {
            ExceptionalShoppingCart.addItemToMarketCatalog("4444", "", "$5.55");
        } catch (IllegalArgumentException e) {
            return true; // exception caught
        }
        try {
            ExceptionalShoppingCart.addItemToMarketCatalog("44A4", "Potato", "$5.G5");
        } catch (IllegalArgumentException e) {
            return true; // exception caught
        }
        return false; //did not catch the exceptions
    }

    // tests contains and nbOccurrences methods' exception handling
    public static boolean testContainsAndNbOccurrences() {
        String[] cart = new String[3];
        try {
            ExceptionalShoppingCart.contains("Tomato", cart, -1);
        } catch (IllegalArgumentException e) {
            return true; // exception caught
        }
        try {
            ExceptionalShoppingCart.nbOccurrences("Tomato", cart, -1);
        } catch (IllegalArgumentException e) {
            return true; // exception caught
        }
        return false; //did not catch the exceptions
    }

    // tests saveCartSummary method's exception handling
    public static boolean testSaveCartSummary() {
        String[] cart = new String[] {"Tomato", "Apple", "Avocado", "Milk", "Apple"};
        File myFile = new File("shoppingcart.txt");

       try {
            ExceptionalShoppingCart.saveCartSummary(cart, 5, myFile);
        } catch (IllegalArgumentException e) {
            return false; // exception caught
        }
        return true;
    }

    // tests loadCartSummary method's and sees if file is correctly loaded
    public static boolean testLoadCartSummary() {
        String[] cart = new String[]{"Banana", null, null, null, null, null, null, null, null,
                null};
        try {
            ExceptionalShoppingCart.loadCartSummary(new File("shoppingcart.txt"), cart, 1);
        } catch (IllegalArgumentException e) {
            return false; // exception caught
        }
        System.out.println(Arrays.toString(cart));
        return true;
    }

    // runs all tests
    public static boolean runAllTests() {
        return (testLookupMethods() && testAddItemToMarketCatalog() &&
                testContainsAndNbOccurrences() && testSaveCartSummary() &&
                testLoadCartSummary()); // returns true if all tests pass

    }
}


