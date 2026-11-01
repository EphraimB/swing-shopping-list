/**
 *
 * Author: Ephraim Becker
 *
 * FilterModel.java
 *
 * Takes care of the filtering logic
 *
 */
package model;

import java.util.ArrayList;
import javax.swing.AbstractListModel;
import javax.swing.ListModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

public class FilterModel extends AbstractListModel<String> {

    // Define the list model items
    private final ListModel<String> items;

    // Define the filter items array list
    private final ArrayList<String> filterItems = new ArrayList<>();

    private String filterTerm = "";

    /**
     * Constructor
     *
     * @param items The list model items
     */
    public FilterModel(ShoppingListModel items) {
        // Call the parent class
        super();

        this.items = items;

        // Adds the listener on the items
        items.addListDataListener(new ListDataListener() {
            @Override
            public void intervalAdded(ListDataEvent e) {
                refilter(filterTerm);
            }

            @Override
            public void intervalRemoved(ListDataEvent e) {
                refilter(filterTerm);
            }

            @Override
            public void contentsChanged(ListDataEvent e) {
                refilter(filterTerm);
            }
        });

        // Calls the refilter method on the term
        refilter(filterTerm);
    }

    /**
     * (non-Javadoc)
     *
     * @see javax.swing.ListModel#getElementAt(int)
     */
    @Override
    public String getElementAt(int index) {
        // Checks if the index is less than the size of the filter items
        if (index < filterItems.size()) {
            // Returns the filter item element
            return filterItems.get(index);
        } else {
            // Returns null
            return null;
        }
    }

    /**
     * (non-Javadoc)
     *
     * @see javax.swing.ListModel#getSize()
     */
    @Override
    public int getSize() {
        // Returns the size of the filter items
        return filterItems.size();
    }

    public ListModel<String> getItems() {
        return items;
    }

    /**
     * Gets called after an element in the list changes
     */
    public final void refilter(String term) {

        // Gets the text on the text field in lower case
        filterTerm = term.toLowerCase();

        // Clears the filter items
        filterItems.clear();

        // Loop through the size of the items
        for (int i = 0; i < items.getSize(); i++) {
            // Gets the item element at the index
            String item = items.getElementAt(i);

            // Checks if the filter term is empty or the item contains the term
            if (filterTerm.isEmpty() || item.toLowerCase().contains(filterTerm)) {
                // Adds an item to the filter items
                filterItems.add(item);
            }
        }

        // Fires the contents changed
        fireContentsChanged(this, 0, Math.max(0, getSize() - 1));
    }
}
