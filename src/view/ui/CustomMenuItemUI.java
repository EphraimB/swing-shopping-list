package view.ui;

/**
 * Author: Ephraim Becker
 *
 * CustomMenuItemUI.java
 *
 * Tweaks the menu to make it a translucent menu
 */
import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JComponent;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicMenuItemUI;

public class CustomMenuItemUI extends BasicMenuItemUI {

    /**
     *
     * Creates the UI for the translucent menu
     *
     * @param c The base component
     * @return The new translucent component
     *
     */
    public static ComponentUI createUI(JComponent c) {
        return new CustomMenuItemUI();
    }

    /**
     * (non-Javadoc)
     *
     * @see javax.swing.plaf.basic.BasicMenuItemUI#paint(java.awt.Graphics,
     * javax.swing.JComponent)
     */
    @Override
    public void paint(Graphics g, JComponent comp) {
        // Buffered image
        BufferedImage bugimg = new BufferedImage(
                comp.getWidth(),
                comp.getHeight(),
                BufferedImage.TYPE_INT_ARGB);

        // Creates a Graphics 2D image
        Graphics2D g2 = bugimg.createGraphics();

        // Sets the color of the 2D image to the color of the graphics
        g2.setColor(g.getColor());

        // Paints the 2D image
        super.paint(g2, comp);

        // New Graphics 2D instance
        Graphics2D gx = (Graphics2D) g;

        // Alpha composite with translucency
        gx.setComposite(AlphaComposite.getInstance(
                AlphaComposite.SRC_OVER, 0.8f));

        // Draws the image
        gx.drawImage(bugimg, 0, 0, null);
    }
}
