/**
 *
 * Author: Ephraim Becker
 *
 * ShoppingListController.java
 *
 * Takes care of the button and mouse clicks
 *
 */
package controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import model.ShoppingListModel;
import view.MenuBar;
import view.ShoppingList;

public class ShoppingListController {

    // Class properties
    private final JFrame frame;
    private final MenuBar menuBar;
    private final ShoppingListModel model;
    private final ShoppingList shoppingList;

    /**
     *
     * @param frame The frame
     * @param menuBar The menu bar
     * @param shoppingList The shopping list
     * @param model The model
     */
    public ShoppingListController(JFrame frame, MenuBar menuBar, ShoppingList shoppingList, ShoppingListModel model) {
        // Sets the properties to the parameter values
        this.frame = frame;
        this.menuBar = menuBar;
        this.shoppingList = shoppingList;
        this.model = model;

        addActionListeners();
        addMouseListeners();
    }

    /**
     * Sets all the action listeners
     */
    private void addActionListeners() {
        // Add an action listener that prompts for a new item and adds it
        menuBar.getNewItem().addActionListener(event -> {
            // Shows an input dialog for adding a new item
            String item = JOptionPane.showInputDialog("Enter the name of the new item.");

            // Checks if the input text has text in it
            if (item != null && !item.isBlank()) {
                // Adds the element
                model.addElement(item);
            }
        });

        // Adds an action listener on the close item that closes the program
        menuBar.getCloseItem().addActionListener(
                event -> frame.dispatchEvent(
                        new WindowEvent(frame, WindowEvent.WINDOW_CLOSING)
                )
        );

        // Add an action listener that prompts for a new item and adds it
        menuBar.getAddNewItem().addActionListener(event -> {
            String item = JOptionPane.showInputDialog("Enter the name of the new item.");

            // Adds the item to the model
            model.addElement(item);
        });

        // Add an action listener that removes the selected item
        menuBar.getRemoveSelectedItem().addActionListener(event -> {
            int selectedIndex = shoppingList.getSelectedItemIndex();

            // Removes the item from the model
            model.removeItem(selectedIndex);
        });

        // Add an action listener that removes purchased items
        menuBar.getRemovePurchasedItems().addActionListener(event -> model.removePurchasedItems());

        // Adds an action listener on the clear item that clears the selection
        menuBar.getClearItem().addActionListener(event -> shoppingList.clearSelection());

    }

    private void addMouseListeners() {
        // Add a mouse listener
        shoppingList.addMouseListener(new MouseAdapter() {
            /**
             * (non-Javadoc)
             *
             * @see
             * java.awt.event.MouseAdapter#mouseClicked(java.awt.event.MouseEvent)
             */
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                // Check if the mouse button is equal to the left mouse button
                if (mouseEvent.getButton() == MouseEvent.BUTTON1) {
                    // Toggles the item at the mouse point
                    shoppingList.toggleItemAt(mouseEvent.getPoint());
                }
            }
        });
    }
}
