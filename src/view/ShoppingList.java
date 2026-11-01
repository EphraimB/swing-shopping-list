/**
 *
 * Author: Ephraim Becker
 *
 * ShoppingList.java
 *
 * Takes care of managing the shopping list
 *
 */
package view;

import java.awt.Point;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.ListSelectionModel;
import model.ShoppingListModel;
import view.ui.ShoppingListItemRenderer;

public class ShoppingList extends FilteredJList {

    // Uses the custom ShoppingList model
    private final ShoppingListModel itemModel;

    /**
     * Creates a shopping list with the given title and initial items
     *
     * @param title text shown in the list's column header.
     * @param initialItems items to display initially; may be blank
     */
    public ShoppingList(ShoppingListModel model, String title) {
        // Calls the parent JList with the new default list model
        super(model, title);

        // Sets the item model to a method that gets the model
        this.itemModel = model;

        // Make it that you can only select a single item
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Sets the cell renderer to the custom renderer
        setCellRenderer(new ShoppingListItemRenderer(itemModel));

        // Add the togglePurchased listener
        getActionMap().put("togglePurchased", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent event) {
                // toggle selected item at purchased
                toggleSelectedItem();
            }
        });
    }

    /**
     * Constructor
     *
     * @param title The title of the Shopping List
     * @param initialItems Initial items
     */
    public ShoppingList(String title) {
        // Calls the shopping list with the title and the initial items
        this(new ShoppingListModel(), title);
    }

    /**
     * (non-Javadoc)
     *
     * @see view.FilteredJList#addItem(java.lang.String)
     */
    @Override
    public void addItem(String item) {
        itemModel.add(item);
    }

    /**
     * Removes the selected item from the shopping list
     */
    public void removeSelectedItem() {
        // Gets the selected index
        int index = getSelectedIndex();

        // Checks if the index is at least 0
        if (index >= 0) {
            // Removes the item from the model
            itemModel.removeItem(index);
        }
    }

    /**
     * Toggles the selected item
     */
    private void toggleSelectedItem() {
        // Gets the index
        int index = getSelectedIndex();

        // Checks if the index is at least 0
        if (index >= 0) {
            // Toggles the purchased
            togglePurchased(index);
        }
    }

    /**
     * Toggles the purchased at the specified index
     *
     * @param index Specified index
     */
    public void togglePurchased(int index) {
        // Calls the toggle purchased method
        itemModel.togglePurchased(index);

        // Repaints the cell
        repaint(getCellBounds(index, index));
    }

    /**
     * Toggles the item at the specified location
     *
     * @param point Location specified
     */
    public void toggleItemAt(Point point) {
        // Initialize index to the index closest to the location
        int index = locationToIndex(point);

        // Checks if index is at least 0 and the cell bounds within the index contain
        // the location
        if (index >= 0 && getCellBounds(index, index).contains(point)) {
            // Sets the cell at the index
            setSelectedIndex(index);

            // Toggles the purchase
            togglePurchased(index);
        }
    }

    /**
     * Returns the selected item index
     *
     * @return The selected item index
     */
    public int getSelectedItemIndex() {
        // Returns the selected item index from the parent class
        return super.getSelectedIndex();
    }
}
