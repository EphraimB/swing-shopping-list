package model;

import java.util.HashMap;
import javax.swing.DefaultListModel;

/*
 * Author: Ephraim Becker
 * 
 * ShoppingListModel.java (WIP)
 * 
 * Creates a custom DefaultListModel for our ShoppingListApplication
 * 
 * https://docs.oracle.com/javase/8/docs/api/javax/swing/DefaultListModel.html
 */
public class ShoppingListModel extends DefaultListModel<String> {

    // Creates a HashMap for the purchased items
    private final HashMap<String, Boolean> purchasedItems = new HashMap<>();

    /**
     *
     * @param purchasedItemList A list of purchased items
     */
    public ShoppingListModel(String... purchasedItemList) {
        super();

        // Loops through the items in the list
        for (String item : purchasedItemList) {
            // Adds the item to the model
            addElement(item);

            // Adds the item to the HashMap with it not purchased
            purchasedItems.put(item, false);
        }
    }

    /**
     *
     * Adds the item
     *
     * @param item The item to add
     */
    public void add(String item) {
        // Adds the item to the model
        addElement(item);

        // Adds the item to the HashMap with it not purchased
        purchasedItems.put(item, false);
    }

    /**
     *
     * Removes the item
     *
     * @param index The item to remove
     */
    public void removeItem(int index) {
        // Checks if the index is at least 0 and is less than the the total items
        if (index >= 0 && index < getSize()) {
            // Removes the item at the index
            removeElementAt(index);
        }
    }

    /**
     *
     * Checks if the item is purchased or not
     *
     * @param item The item to check if purchased
     * @return If the item is purchased or not
     */
    public boolean isPurchased(String item) {
        return purchasedItems.getOrDefault(item, false);
    }

    /**
     * Removes all purchased items
     */
    public void removePurchasedItems() {
        // Loops through the items in reverse
        for (int index = getSize() - 1; index >= 0; index--) {

            // Gets the item at the current index
            String item = getElementAt(index);

            // Checks whether the item was purchased
            if (purchasedItems.getOrDefault(item, false)) {
                // Removes the item
                removeItem(index);
            }
        }
    }

    /**
     * Removes all items
     */
    public void removeAll() {
        // Clears the list from the model
        clear();

        // Clears the HashMap
        purchasedItems.clear();
    }

    /**
     * Toggles the purchased at the index
     *
     * @param index Index to toggle the purchased
     */
    public void togglePurchased(int index) {
        // Retrieve the shopping list item at the specified index
        String item = getElementAt(index);

        // Gets the purchased status
        boolean purchased = purchasedItems.getOrDefault(item, false);

        // Toggle its purchased status
        purchasedItems.put(item, !purchased);
    }
}
