package view;

/**
 *
 * Author: Ephraim Becker
 *
 * FilteredJList.java
 *
 * Adds the ability to filter the shopping list to your typed words
 *
 */
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import model.FilterModel;
import model.ShoppingListModel;

/**
 *
 * FilteredJList
 */
public class FilteredJList extends JList<String> {

    // Initialize header label
    private final JLabel headerLabel;

    // The filter field
    private final FilterField filterField;

    // The filter model
    private final FilterModel filterModel;

    // The width of the filter field
    private final int DEFAULT_FIELD_WIDTH = 20;

    // Title
    private final String title;

    // Data model
    private final ShoppingListModel dataModel;

    /**
     * Constructor for the filtered JList
     *
     * @param dataModel The data model
     */
    public FilteredJList(ShoppingListModel dataModel, String title) {
        // Calls the parent's constructor with a new filter model
        super(new FilterModel(dataModel));

        // Assigns a new filter model with the list model
        filterModel = (FilterModel) getModel();

        // Assigns to a new filter field with the default width
        filterField = new FilterField(filterModel, DEFAULT_FIELD_WIDTH);

        // Sets the title
        this.title = title;

        // Sets the data model
        this.dataModel = dataModel;

        // Sets the header label property to the title
        this.headerLabel = new JLabel(title + " (" + dataModel.getSize() + ")");

        // Listens for item changes to update the total items in the title
        dataModel.addListDataListener(new ListDataListener() {

            @Override
            public void intervalAdded(ListDataEvent e) {
                updateHeaderLabel();
            }

            @Override
            public void intervalRemoved(ListDataEvent e) {
                updateHeaderLabel();
            }

            @Override
            public void contentsChanged(ListDataEvent e) {
                updateHeaderLabel();
            }
        });
    }

    /**
     * Updates the header label
     */
    private void updateHeaderLabel() {
        // Sets the text of the header label to the updated text
        headerLabel.setText(title + " (" + dataModel.getSize() + ")");
    }

    /**
     * (non-Javadoc)
     *
     * @see javax.swing.JList#setModel(javax.swing.ListModel)
     */
    @Override
    public void setModel(ListModel<String> m) {
        // Checks if the list model isn't an instance of the filter model
        if (!(m instanceof FilterModel)) {
            // Throws an illegal argument exception
            throw new IllegalArgumentException();
        }

        // Sets the model on the parent class
        super.setModel(m);
    }

    /**
     * Adds the specified item to the list
     *
     * @param item Specified item
     */
    public void addItem(String item) {
        // Checks if the items on the filter model is an instance of the default list
        // model
        if (filterModel.getItems() instanceof ShoppingListModel model) {
            // Adds the item to the model
            model.addElement(item);
        }
    }

    /**
     * Gets the filter field
     *
     * @return A text field
     */
    public JTextField getFilterField() {
        return filterField;
    }

    /**
     *
     * Returns the filter panel
     *
     * @return The filter panel
     */
    public JPanel getFilteredPanel() {
        // New panel with a border layout
        JPanel panel = new JPanel(new BorderLayout());

        // New scroll pane
        JScrollPane scrollPane = new JScrollPane(this);

        // Centers the title
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Make title bold and larger
        headerLabel.setFont(
                headerLabel.getFont().deriveFont(Font.BOLD, 18f)
        );

        // Add some breathing room
        headerLabel.setBorder(
                BorderFactory.createEmptyBorder(10, 5, 10, 5)
        );

        // Adds the title to the scroll pane
        scrollPane.setColumnHeaderView(headerLabel);

        // New footer panel
        JPanel footerPanel = new JPanel(new BorderLayout());

        // Creates an empty border for the footer panel
        footerPanel.setBorder(
                BorderFactory.createEmptyBorder(8, 10, 8, 10)
        );

        // Adds the filter field to the footer panel
        footerPanel.add(filterField, BorderLayout.CENTER);

        // Adds the scroll pane to the center of the frame
        panel.add(scrollPane, BorderLayout.CENTER);

        // Adds the footer panel to the bottom of the frame
        panel.add(footerPanel, BorderLayout.SOUTH);

        // Returns the panel
        return panel;
    }

}
