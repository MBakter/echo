package view;

import javax.swing.JButton;

import controller.IController;
import model.IVMRoom;

public interface IVRoom {
    /*
     * Modelbeli room
     */
    IVMRoom getModelRoom();
    /*
     * Rajzolás (ajtók)
     */
    void draw(IVStudent curPlayer, JButton door, IController c);

}
