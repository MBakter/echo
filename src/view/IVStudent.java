package view;

import javax.swing.JLabel;

import controller.IController;
import model.player.IVMStudent;

public interface IVStudent {
    /*
     * Modelbeli student
     */
    public IVMStudent getModelStudent();
    /*
     * Rajzolás
     */
    public void draw(IVStudent curPlayer, JLabel label, IController c);
}
