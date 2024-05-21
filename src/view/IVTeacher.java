package view;

import javax.swing.JLabel;

import controller.IController;
import model.player.IVMTeacher;

public interface IVTeacher {
    /*
     * Modelbeli teacher
     */
    IVMTeacher getModelTeacher();
    /*
     * Rajzolás
     */
    void draw(IVStudent curPlayer, JLabel label, IController c);
}
