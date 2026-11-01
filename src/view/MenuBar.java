/**
 *
 * Author: Ephraim Becker
 *
 * MenuBar.java
 *
 * Creates the Menu Bar
 *
 */
package view;

import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * Creates the menu bar
 *
 * @param frame The frame
 * @return A menu bar
 */
public class MenuBar extends JMenuBar {

    // Initialize all the components
    private JFrame frame = null;

    private JMenuItem newItem = null;
    private JMenuItem closeItem = null;
    private JMenuItem addNewItem = null;
    private JMenuItem removeSelectedItem = null;
    private JMenuItem removePurchasedItems = null;
    private JMenuItem clearItem = null;

    /**
     *
     * Constructor
     *
     * @param frame The frame
     */
    public MenuBar(JFrame frame) {
        this.frame = frame;

        // New menus
        JMenu fileMenu = new JMenu("File");
        JMenu editMenu = new JMenu("Edit");


        /* File */
        newItem = new JMenuItem("New");
        closeItem = new JMenuItem("Close");

        /* Edit */
        addNewItem = new JMenuItem("Add new item");
        removeSelectedItem = new JMenuItem("Remove selected item");
        removePurchasedItems = new JMenuItem("Remove purchased items");
        clearItem = new JMenuItem("Clear selection");

        // Adds the menu items to their menus
        fileMenu.add(newItem);
        fileMenu.add(closeItem);

        editMenu.add(clearItem);
        editMenu.add(addNewItem);
        editMenu.add(removeSelectedItem);
        editMenu.add(removePurchasedItems);

        // Adds the menus to the menu bar
        add(fileMenu);
        add(editMenu);

    }

    // Getters
    public JMenuItem getNewItem() {
        return newItem;
    }

    public JMenuItem getCloseItem() {
        return closeItem;
    }

    public JMenuItem getAddNewItem() {
        return addNewItem;
    }

    public JMenuItem getRemoveSelectedItem() {
        return removeSelectedItem;
    }

    public JMenuItem getRemovePurchasedItems() {
        return removePurchasedItems;
    }

    public JMenuItem getClearItem() {
        return clearItem;
    }

    /**
     * Closes the window
     */
    public void closeWindow() {
        dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
    }
}
