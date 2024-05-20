package view;

import javax.swing.JButton;

import controller.IController;

public interface IVItems {
    void draw(IVStudent curPlayer, JButton btn, IController c);
    void drawOnGround(IVStudent curPlayer, JButton btn, IController c);
}
