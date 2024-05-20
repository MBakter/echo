package view;

import java.awt.*;
import java.io.File;

import javax.swing.*;

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
    public JPanel draw(IVStudent curPlayer, GridBagConstraints c) {
                JPanel cleanerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        cleanerPanel.setOpaque(false);
        c.insets = new Insets(0, 0, 0, 0);
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 2;
        c.gridheight = 1;
        c.weightx = 1;
        c.weighty = 1;
        c.ipadx = 60;
        c.ipady = 0;
        c.fill = GridBagConstraints.HORIZONTAL;

        for (int i = 0; i < 10; i++) {
            JButton cleaner = new JButton();
            cleaner.setContentAreaFilled(false);
            cleaner.setIcon(new ImageIcon("textures" + File.separator + "Cleaner.png"));
            cleaner.setPreferredSize(new Dimension(65, 100));
            cleanerPanel.add(cleaner);
        }

        return cleanerPanel;
    }
    
}
