/**
 *
 * Author: Ephraim Becker
 *
 * ShoppingListItemRenderer.java
 *
 * Renders the shopping list items with a green background and circled if purchased
 *
 */
package view.ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;
import model.ShoppingListModel;

/**
 *
 * ShoppingListItemRenderer
 */
public class ShoppingListItemRenderer extends JLabel implements ListCellRenderer<String> {

    // Model property
    private final ShoppingListModel model;

    // Purchased property
    private boolean purchased;

    /**
     * Constructor
     *
     * @param model The shopping list model
     */
    public ShoppingListItemRenderer(ShoppingListModel model) {
        this.model = model;

        // Turns transparency off
        setOpaque(true);

        // Creates a black border
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK, 2),
                BorderFactory.createEmptyBorder(8, 8, 8, 8)
        ));
    }

    /**
     * (non-Javadoc)
     *
     * @see
     * javax.swing.ListCellRenderer#getListCellRendererComponent(javax.swing.JList,
     * java.lang.Object, int, boolean, boolean)
     */
    @Override
    public Component getListCellRendererComponent(
            JList<? extends String> list,
            String value,
            int index,
            boolean isSelected,
            boolean cellHasFocus) {

        // Sets the text to an empty string when the value is null
        setText(value == null ? "" : value);

        // Sets purchased property to the purchased value in the model
        purchased = value != null && model.isPurchased(value);

        // Center text
        setHorizontalAlignment(SwingConstants.CENTER);
        setVerticalAlignment(SwingConstants.CENTER);

        // Purchased = green, selected = black
        if (purchased) {
            setBackground(new Color(46, 160, 90));
            setForeground(Color.WHITE);
        } else if (isSelected) {
            setBackground(Color.BLACK);
            setForeground(Color.WHITE);
        } else {
            setBackground(Color.WHITE);
            setForeground(Color.BLACK);
        }

        return this;
    }

    /**
     * (non-Javadoc)
     *
     * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Runs if its purchased and there's text
        if (purchased && getText() != null) {
            // Creates a new graphics object
            Graphics2D g2 = (Graphics2D) g.create();

            // Gets the font
            FontMetrics fm = g2.getFontMetrics(getFont());

            // Calculate text dimensions
            int textWidth = fm.stringWidth(getText());
            int textHeight = fm.getHeight();

            // Gets the border insets
            Insets insets = getInsets();

            // Available horizontal space
            int availableWidth = getWidth() - insets.left - insets.right;

            // Calculate text X position
            int textX;
            textX = insets.left + (availableWidth - textWidth) / 2;

            // Calculate text Y position
            int textY = (getHeight() - textHeight) / 2;

            // Draw oval around text
            g2.setColor(Color.GREEN);
            g2.setStroke(new BasicStroke(2));
            g2.drawOval(
                    textX - 5,
                    textY - 2,
                    textWidth + 10,
                    textHeight + 4
            );
            g2.dispose();
        }
    }
}
