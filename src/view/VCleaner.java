package view;

import java.io.File;

import javax.swing.*;

import controller.IController;
import model.player.*;;

public class VCleaner implements IVCleaner{

    IVMCleaner modelCleaner;

    public VCleaner(Cleaner c){
        modelCleaner = c;
    }

    @Override
    public IVMCleaner getModelCleaner() {
        return modelCleaner;
    }

    @Override
    public void draw(IVStudent curPlayer, JButton btn, IController c) {
        System.out.println("VCLEANER DRAW");

        btn.setIcon(new ImageIcon("textures" + File.separator + "Cleaner.png"));
    }
    
}
