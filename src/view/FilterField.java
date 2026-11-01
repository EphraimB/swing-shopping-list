/**
 *
 * Author: Ephraim Becker
 *
 * FilterField.java
 *
 * Creates a filter text field for filtering items
 *
 */
package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import model.FilterModel;

/**
 *
 * FilterField
 */
public class FilterField extends JTextField implements DocumentListener {

    // FIlter model property
    private final FilterModel filterModel;

    /**
     * Constructor
     *
     * @param model The model to be used
     * @param width Width of the filter field
     */
    public FilterField(FilterModel model, int width) {
        // Calls the text field with the width
        super(width);

        // Sets the filter model
        this.filterModel = model;

        // Takes care of refiltering the items when the filter field changes
        getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                filterModel.refilter(getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filterModel.refilter(getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                filterModel.refilter(getText());
            }
        });
    }

    /**
     * (non-Javadoc)
     *
     * @see
     * javax.swing.event.DocumentListener#changedUpdate(javax.swing.event.DocumentEvent)
     */
    @Override
    public void changedUpdate(DocumentEvent e) {
        // Calls the refilter method on the model
        filterModel.refilter(getText());
    }

    /**
     * (non-Javadoc)
     *
     * @see
     * javax.swing.event.DocumentListener#insertUpdate(javax.swing.event.DocumentEvent)
     */
    @Override
    public void insertUpdate(DocumentEvent e) {
        // Calls the refilter method on the model
        filterModel.refilter(getText());
    }

    /**
     * (non-Javadoc)
     *
     * @see
     * javax.swing.event.DocumentListener#removeUpdate(javax.swing.event.DocumentEvent)
     */
    @Override
    public void removeUpdate(DocumentEvent e) {
        // Calls the refilter method on the model
        filterModel.refilter(getText());
    }

    /**
     * (non-Javadoc)
     *
     * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Runs if the filter field is empty
        if (getText().isEmpty()) {

            // Creates a new graphics object
            Graphics2D g2 = (Graphics2D) g.create();

            // Sets the color to gray
            g2.setColor(Color.GRAY);

            // Gets the border insets
            Insets insets = getInsets();

            // Draws the placeholder for the filter field
            g2.drawString(
                    "Filter...",
                    insets.left + 5,
                    getHeight() / 2 + g2.getFontMetrics().getAscent() / 2 - 2
            );

            // Releases the resources for the graphic
            g2.dispose();
        }
    }
}
