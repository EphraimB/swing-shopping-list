package view.ui;

/**
 * Author: Ephraim Becker
 *
 * CustomPopupMenuUI.java
 *
 * Tweaks the menu popup to make it a translucent menu popup UI
 */
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.Popup;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicPopupMenuUI;

public class CustomPopupMenuUI extends BasicPopupMenuUI {

    /**
     *
     * Creates the UI for the translucent popup menu
     *
     * @param c The base component
     * @return The new translucent popup
     *
     */
    public static ComponentUI createUI(JComponent c) {
        return new CustomPopupMenuUI();
    }

    /**
     * (non-Javadoc)
     *
     * @see
     * javax.swing.plaf.basic.BasicPopupMenuUI#installUI(javax.swing.JComponent)
     */
    @Override
    public void installUI(JComponent c) {
        // Installs the UI
        super.installUI(c);

        // Turns off the opaque on the popup menu
        popupMenu.setOpaque(false);
    }

    /**
     * (non-Javadoc)
     *
     * @see javax.swing.plaf.PopupMenuUI#getPopup(javax.swing.JPopupMenu, int,
     * int)
     */
    @Override
    public Popup getPopup(JPopupMenu popup, int x, int y) {
        // Get the popup
        Popup pp = super.getPopup(popup, x, y);

        // Get the parent panel
        JPanel panel = (JPanel) popup.getParent();

        // Turns off the opaque on the panel
        panel.setOpaque(false);

        // Returns the popup
        return pp;
    }
}
