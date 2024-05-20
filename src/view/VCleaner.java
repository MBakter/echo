package view;

import java.awt.*;
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'draw'");
    }
    
}
