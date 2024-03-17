/**
 * This class contains tester methods for the ShoppingCart class
 */
public class ShoppingCartTester {
    /**
     * Main method
     * @param args input arguments if any
     */
    public static void main(String[] args) {
        System.out.println("testLookupMethods(): " + testLookupMethods());
        System.out.println("testGetProductPrice(): " + testGetProductPrice());
        System.out.println("testAddItemToCartContainsNbOccurrences(): " +
                testAddItemToCartContainsNbOccurrences());
        System.out.println("testRemoveItem(): " + testRemoveItem());
        System.out.println("testCheckoutGetCartSummary(): " + testCheckoutGetCartSummary());
        System.out.println("runAllTests(): " + runAllTests());
    }

    /**
     * Checks whether ShoppingCart.lookupProductByName() and ShoppingCart.lookupProductById()
     * methods work as expected.
     *
     * @return true when this test verifies a correct functionality, and false otherwise
     */
    public static boolean testLookupMethods() {
        // 1. The item to find is at index 0 of the marketItems array
        String expectedOutput = "4390 Apple $1.59";
        if (!ShoppingCart.lookupProductByName("Apple").equals(expectedOutput)) {
            System.out.println("Problem detected: Your lookupProductByName() method failed " +
                    "to return the expected output when passed Apple as input");
            return false;
        }
        if (!ShoppingCart.lookupProductById(4390).equals(expectedOutput)) {
            System.out.println("Problem detected: Your lookupProductById() method failed to " +
                    "return the expected output when passed the id of Apple as input");
            return false;
        }

        // 2. The item to find is at the last non-null position of the marketItems array
        String expectedOutput2 = "4688 Tomato $1.79";
        if (!ShoppingCart.lookupProductByName("Tomato").equals(expectedOutput2)) {
            System.out.println("Problem detected: Your lookupProductByName() method failed " +
                    "to return the expected output when passed Tomato as input");
            return false;
        }
        if (!ShoppingCart.lookupProductById(4688).equals(expectedOutput2)) {
            System.out.println("Problem detected: Your lookupProductById() method failed to " +
                    "return the expected output when passed the id of Tomato as input");
            return false;
        }

        // 3. The item to find is at an arbitrary position of the middle of the marketItems array
        String expectedOutput3 = "4071 Chocolate $3.19";
        if (!ShoppingCart.lookupProductByName("Chocolate").equals(expectedOutput3)) {
            System.out.println("Problem detected: Your lookupProductByName() method failed " +
                    "to return the expected output when passed Chocolate as input");
            return false;
        }
        if (!ShoppingCart.lookupProductById(4071).equals(expectedOutput3)) {
            System.out.println("Problem detected: Your lookupProductById() method failed to " +
                    "return the expected output when passed the id of Chocolate as input");
            return false;
        }

        // 4. The item to find is not found in the market
        expectedOutput = "No match found";
        if (!ShoppingCart.lookupProductByName("NOT FOUND").equals(expectedOutput)) {
            System.out.println("Problem detected: Your lookupProductByName() method failed " +
                    "to return the expected output when passed the name of a product not found " +
                    "in the market.");
            return false;
        }
        if (!ShoppingCart.lookupProductById(1000).equals(expectedOutput)) {
            System.out.println("Problem detected: Your lookupProductById() method failed to " +
                    "return the expected output when passed the identifier of a product " +
                    "not found in the market.");
            return false;
        }

        return true; // NO BUGS detected by this tester method

    }

    /**
     * Checks the correctness of ShoppingCart.getProductPrice() method
     *
     * @return true when this test verifies a correct functionality, and false otherwise
     */
    public static boolean testGetProductPrice() {
    // first test scenario: get the price of Apple
        double expectedPrice = 1.59; // price of the product Apple in the market
        if (Math.abs(ShoppingCart.getProductPrice("Apple") - expectedPrice) > 0.001) {
            // print feedback to report the detected bug
            return false;
        }

    // second test scenario: get the price of Tomato
        double expectedPrice2 = 1.79;
        if (Math.abs(ShoppingCart.getProductPrice("Tomato") - expectedPrice2) > 0.001) {
            // print feedback to report the detected bug
            return false;
        }

    // third test scenario: get the price of something not in market
        double expectedPrice3 = -1.0;
        if (Math.abs(ShoppingCart.getProductPrice("NOT FOUND") - expectedPrice3) > 0.001) {
            // print feedback to report the detected bug
            return false;
        }

        return true; // No bug detected. The ShoppingCart.getProductPrice() passed this tester.
    }

    /**
     * Checks whether ShoppingCart.addItemToCart() and ShoppingCart.contains(), and ShoppingCart
     * .nbOccurrences() methods work as expected.
     *
     * @return true when this test verifies a correct functionality, and false otherwise
     */
    public static boolean testAddItemToCartContainsNbOccurrences() {
        String[] cart = new String[]{"Milk", "Apple", "Banana", "Pizza", null, null};

        // tests addItemToCart()
        int expectedValue = 5;
        if (!(ShoppingCart.addItemToCart("Chocolate", cart, 4) == expectedValue)) {
            System.out.println("Problem detected: Your addItemToCart() method failed to return " +
                    "the expected value");
            return false;
        }

        // tests nbOccurrences()
        int expectedValue2 = 1;
        if (!(ShoppingCart.nbOccurrences("Apple", cart, 5) == expectedValue2)) {
            System.out.println("Problem detected: Your nbOccurrences() method failed to return " +
                    "the expected value");
            return false;
        }

        // tests contains()
        boolean expectedValue3 = true;
        if (!(ShoppingCart.contains("Apple", cart, 5) == expectedValue3)) {
            System.out.println("Problem detected: Your contains() method failed to return the " +
                    "expected value");
            return false;
        }

        return true; // No bugs detected
    }

    /**
     * Checks the correctness of ShoppingCart.removeItem() method
     *
     * @return true when this test verifies a correct functionality, and false otherwise
     */
    public static boolean testRemoveItem() {
        String[] cart = new String[]{"Milk", "Apple", "Banana", "Pizza", null, null};
        int expectedValue = 3;
        if (!(ShoppingCart.removeItem(cart, "Banana", 4) == expectedValue)) {
            System.out.println("Problem detected: Your removeItem() method failed to return " +
                    "the expected value");
            return false;
        }
        return true; // No bugs detected
    }

    /**
     * Checks the correctness of ShoppingCart.getCartSummary() method
     *
     * @return true when this test verifies a correct functionality, and false otherwise
     */
    public static boolean testCheckoutGetCartSummary() {
        String[] cart = new String[] {"Tomato", "Milk", "Milk", "Eggs", "Tomato", "Onion", "Eggs",
                "Milk", "Banana", null, null};
        String expectedSummary = "(2) Tomato\n(3) Milk\n(2) Eggs\n(1) Onion\n(1) Banana\n";
        if (!(ShoppingCart.getCartSummary(cart, 9).equals(expectedSummary))) {
            System.out.println("Problem detected: Your getCartSummary() method failed to return " +
                    "the expected string");
            System.out.println(ShoppingCart.getCartSummary(cart, 9));
            return false;
        }
        return true; // No bugs detected
    }

    /**
     * Checks if all test methods defined in this tester class work correctly
     *
     * @return true when this test verifies a correct functionality, and false otherwise
     */
    public static boolean runAllTests() {
        if (!(testLookupMethods() == true)) {
            System.out.println("Problem detected: testLookupMethods() did not pass");
            return false;
        }
        else if (!(testGetProductPrice() == true)) {
            System.out.println("Problem detected: testGetProductPrice() did not pass");
            return false;
        }
        else if (!(testAddItemToCartContainsNbOccurrences() == true)) {
            System.out.println("Problem detected: testAddItemToCartContainsNbOccurrences() did " +
                    "not pass");
            return false;
        }
        else if (!(testRemoveItem() == true)) {
            System.out.println("Problem detected: testRemoveItem() did not pass");
            return false;
        }
        else if (!(testCheckoutGetCartSummary() == true)) {
            System.out.println("Problem detected: testCheckoutGetCartSummary() did not pass");
            return false;
        }
        return true; // All tests pass
    }
}
