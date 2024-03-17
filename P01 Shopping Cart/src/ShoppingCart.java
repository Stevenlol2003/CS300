/**
 * This class contains method needed for simulating a shopping cart
 */
public class ShoppingCart {
    private static final double TAX_RATE = 0.05; // sales tax

    // MarketItems: a perfect-size two-dimensional array that stores the list of
    // available items in a given market
    // MarketItems[i][0] refers to a String representation of the item identifiers
    // MarketItems[i][1] refers the item name. Item names are also unique
    // MarketItems[i][2] a String representation of the unit price
    // of the item in dollars
    private static String[][] marketItems = new String[][]{{"4390", "Apple", "$1.59"}, {"4046",
            "Avocado", "$0.59"}, {"4011", "Banana", "$0.49"}, {"4500", "Beef", "$3.79"}, {"4033",
            "Blueberry", "$6.89"}, {"4129", "Broccoli", "$1.79"}, {"4131", "Butter", "$4.59"}, {
        "4017", "Carrot", "$1.19"}, {"3240", "Cereal", "$3.69"}, {"3560", "Cheese", "$3.49"}, {
        "3294", "Chicken", "$5.09"}, {"4071", "Chocolate", "$3.19"}, {"4363", "Cookie", "$9.5"},
            {"4232", "Cucumber", "$0.79"}, {"3033", "Eggs", "$3.09"}, {"4770", "Grape", "$2.29"},
            {"3553", "Ice Cream", "$5.39"}, {"3117", "Milk", "$2.09"}, {"3437", "Mushroom",
            "$1" + ".79"}, {"4663", "Onion", "$0.79"}, {"4030", "Pepper", "$1.99"}, {"3890",
            "Pizza", "$11.5"}, {"4139", "Potato", "$0.69"}, {"3044", "Spinach", "$3.09"}, {"4688"
            , "Tomato", "$1.79"}, null, null, null, null};

    /**
     * Returns details of a specific product in the market given its name
     *
     * @param name name of the product to search
     * @return A string representation of the product to search including the identifier of the
     * product, its name, and its price in dollars if match found.
     */
    public static String lookupProductByName(String name) {
        for (int i = 0; i < marketItems.length; i++) {
            if (marketItems[i] != null) {
                for (int j = 0; j < marketItems[i].length; j++) {
                    if (marketItems[i][j].equals(name)) {
                        return marketItems[i][0] + " " + marketItems[i][1] + " " +
                                marketItems[i][2];
                    }
                }
            }
        }
        return "No match found";
    }

    /**
     * Returns details of a specific product in the market given its name
     *
     * @param id id of the product to search
     * @return A string representation of the product to search including the identifier of the
     * product, its name, and its price in dollars if match found.
     */
    public static String lookupProductById(int id) {
        for (int i = 0; i < marketItems.length; i++) {
            if (marketItems[i] != null) {
                for (int j = 0; j < marketItems[i].length; j++) {
                    if (Integer.valueOf(marketItems[i][0]) == (id)) {
                        return marketItems[i][0] + " " + marketItems[i][1] + " " +
                                marketItems[i][2];
                    }
                }
            }
        }
        return "No match found"; // default statement added to allow this code to compile
    }

    /**
     * Returns the price in dollars (a double value) of a market item
     *
     * @param name the name of the product to check its price
     * @return A double value of the market item's price, returns -1.0 if not found in the market
     * category
     */
    public static double getProductPrice(String name) {
        for (int i = 0; i < marketItems.length; i++) {
            if (marketItems[i] != null) {
                for (int j = 0; j < marketItems[i].length; j++) {
                    if (marketItems[i][1].equals(name)) {
                        return Double.valueOf(marketItems[i][2].substring(1));
                    }
                }
            }
        }
        return -1.0;
    }

    /**
     * Creates a new array and returns a deep copy of the marketItems array
     *
     * @return A deep copy of the marketItems array
     */
    public static String[][] getCopyOfMarketItems() {
        String[][] copyOfMarketItems = new String[marketItems.length][];
        for (int i = 0; i < marketItems.length; i++) {
            if (marketItems[i] != null) {
                String[] oneDCopy = new String[marketItems[i].length];
                for (int j = 0; j < marketItems[i].length; j++) {
                    oneDCopy[j] = marketItems[i][j];
                }
                copyOfMarketItems[i] = oneDCopy;
            }
        }
        return copyOfMarketItems;
    }

    /**
     * Appends an item to a given cart (appends means adding to the end). if the cart is already
     * full (meaning its size equals its length), the item will not be added to the cart. Returns
     * the size of the oversize array cart after trying to add item to the cart. This method
     * returns the same of size without making any change to the contents of the array if it is
     * full.
     *
     * @param item the name of the product to be added to the cart
     * @param cart an array of strings which contains the names of items in the cart
     * @param size the number of items in the cart
     * @return An integer value of the number of items in the cart
     */
    public static int addItemToCart(String item, String[] cart, int size) {
        if (size == cart.length) {
            return size;
        } else {
            for (int i = 0; i < cart.length; ++i) {
                if (cart[i] == null) {
                    cart[i] = item;
                    break;
                }
            }
        }
        return size + 1;
    }

    /**
     * Returns the number of occurrences of a given item within a cart. This method makes no
     * changes to the contents of the cart.
     *
     * @param item the name of the item to search
     * @param cart an array of strings which contains the names of items in the cart
     * @param size the number of items in the cart
     * @return An integer value of occurrences of a given item within a cart
     */
    public static int nbOccurrences(String item, String[] cart, int size) {
        int occurrences = 0;
        for (int i = 0; i < size; ++i) {
            if (cart[i].equals(item)) {
                ++occurrences;
            }
        }
        return occurrences;
    }

    /**
     * Checks whether a cart contains at least one occurrence of a given item. This method makes no
     * changes to the contents of the cart.
     *
     * @param item the name of the item to search
     * @param cart an array of strings which contains the names of items in the cart
     * @param size the number of items in the cart
     * @return A boolean value, true if there is a match (exact match) of item within the provided
     * cart, and false otherwise
     */
    public static boolean contains(String item, String[] cart, int size) {
        int occurrences = 0;
        for (int i = 0; i < size; ++i) {
            if (cart[i].equals(item)) {
                ++occurrences;
            }
        }
        if (occurrences >= 1) {
            return true;
        }
        return false;
    }

    /**
     * This method returns the total value in dollars of the cart.
     *
     * @param cart an array of strings which contains the names of items in the cart
     * @param size the number of items in the cart
     * @return A double value in dollars of the cart accounting taxes.
     */
    public static double checkout(String[] cart, int size) {
        double totalValue = 0.0;
        for (int i = 0; i < size; ++i) {
            totalValue += getProductPrice(cart[i]);
        }
        return totalValue * (1 + TAX_RATE);
    }

    /**
     * Removes one occurrence of item from a given cart. If no match with item  found in the cart,
     * the method returns the same value of input size without making any change to the contents of
     * the array.
     *
     * @param item the name of the item to remove
     * @param cart an array of strings which contains the names of items in the cart
     * @param size the number of items in the cart
     * @return An integer value of the number of items in the cart
     */
    public static int removeItem(String[] cart, String item, int size) {
        for (int i = 0; i < size; ++i) {
            if (cart[i].equals(item)) {
                cart[i] = null;
                return size - 1;
            }
        }
        return size;
    }

    /**
     * Removes all items from a given cart. The array cart contains only null references after this
     * method returns.
     *
     * @param cart an array of strings which contains the names of items in the cart
     * @param size the number of items in the cart
     * @return An integer value of the number of items in the cart
     */
    public static int emptyCart(String[] cart, int size) {
        for (int i = 0; i < size; ++i) {
            cart[i] = null;
        }
        int itemNum = 0;
        for (int i = 0; i < cart.length; ++i) {
            if (cart[i] != null) itemNum++;
        }
        return itemNum;
    }

    /**
     * Returns a string representation of the summary of the contents of a given cart. The format
     * of the returned string contains a set of lines where each line contains the number of
     * occurrences of a given item, between parentheses, followed by one space followed by the
     * name of a unique item in the cart.
     *
     * @param cart an an array of strings which contains the names of items in the cart
     * @param size the number of items in the cart
     * @return A string representation of the summary of the contents of the cart
     */
    public static String getCartSummary(String[] cart, int size) {
        StringBuilder cartSummary = new StringBuilder();
        for (int i = 0; i < size; ++i) {
            if (!(cartSummary.toString().contains(cart[i]))) {
                cartSummary.append("(").append(nbOccurrences(cart[i], cart, size)).append(") ").
                        append(cart[i]).append("\n");
            }
        }
        return cartSummary.toString();
    }
}

