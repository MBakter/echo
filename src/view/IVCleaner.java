package view;

import javax.swing.JLabel;

import controller.IController;
import model.player.IVMCleaner;

public interface IVCleaner {
    /*
     * Modelbeli cleaner interfacen keresztüli lekérése
     */
    IVMCleaner getModelCleaner();

    /*
     * Cleaner rajzolása a képre label módosítása által
     */
    void draw(IVStudent curPlayer, JLabel label, IController c);

}
