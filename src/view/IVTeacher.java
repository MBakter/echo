package view;

import javax.swing.JButton;

import controller.IController;
import model.player.IVMTeacher;

public interface IVTeacher {
    IVMTeacher getModelTeacher();
    void draw(IVStudent curPlayer, JButton btn, IController c);
}
