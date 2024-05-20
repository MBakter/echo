package view;

import javax.swing.JButton;
import javax.swing.JLabel;

import controller.IController;

public interface IVItems {
    void draw(IVStudent curPlayer, JButton btn, IController c);
    void draw(IVStudent curPlayer, JLabel btn, IController c);
    boolean isPairable();
}
