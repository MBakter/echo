package view;

import javax.swing.JButton;
import javax.swing.JLabel;

import controller.IController;
import model.player.IVMTeacher;

public interface IVTeacher {
    IVMTeacher getModelTeacher();
    void draw(IVStudent curPlayer, JLabel label, IController c);
}
