package view;

import javax.swing.JButton;
import javax.swing.JLabel;

import controller.IController;

public interface IVItems {
    /*
     * Földön lévő item rajzolása
     */
    void draw(IVStudent curPlayer, JButton btn, IController c);

    /*
     * Inventoryban lévő item rajzolása
     */
    void draw(IVStudent curPlayer, JLabel btn, IController c);

    /*
     * Párosítható-e a tárgy
     */
    boolean isPairable();
}
