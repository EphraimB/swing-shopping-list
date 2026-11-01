
/**
 *
 * Author: Ephraim Becker
 *
 * Main.java
 *
 * The driver class
 *
 */
import controller.ShoppingListController;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.UIManager;
import model.ShoppingListModel;
import view.MenuBar;
import view.ShoppingList;

public class Main {

    public static void main(String[] args) {
        // Installs the menu transparency
        UIManager.put("PopupMenuUI", "view.ui.CustomPopupMenuUI");
        UIManager.put("MenuItemUI", "view.ui.CustomMenuItemUI");

        // Create a new frame with a title
        JFrame jFrame = new JFrame("Shopping List Application");

        // New shopping list with a few initial items
        ShoppingListModel shoppingListModel = new ShoppingListModel(
                "Milk", "Eggs", "Cheese", "Bread", "Coffee");

        // Creates an instance of the shopping list
        ShoppingList shoppingList
                = new ShoppingList(shoppingListModel, "Shopping list");

        // Creates the menu bar
        MenuBar menuBar = new MenuBar(jFrame);

        // Initializes the controller
        ShoppingListController controller
                = new ShoppingListController(jFrame, menuBar, shoppingList, shoppingListModel);

        // Program exits on close
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set layout to a border layout
        jFrame.setLayout(new BorderLayout());

        // Adds the filtered shopping list to the frame
        jFrame.add(shoppingList.getFilteredPanel(), BorderLayout.CENTER);

        // Menu bar
        jFrame.setJMenuBar(menuBar);

        // Makes a card-based layout
        shoppingList.setFixedCellWidth(160);
        shoppingList.setFixedCellHeight(80);
        shoppingList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        shoppingList.setVisibleRowCount(-1);

        // Sets the dimension for the frame
        jFrame.setSize(500, 300);

        // Puts window in the center of the screen
        jFrame.setLocationRelativeTo(null);

        // Makes the frame visible
        jFrame.setVisible(true);
    }
}
