package view;

import javax.swing.JButton;

import controller.IController;
import model.player.IVMStudent;

public interface IVStudent {
    public IVMStudent getModelStudent();
    public void draw(IVStudent curPlayer, JButton btn, IController c);
}
