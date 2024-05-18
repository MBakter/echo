package view;

import java.awt.*;
import java.io.File;

import javax.swing.*;
import model.player.IVMTeacher;
import model.player.Teacher;

public class VTeacher implements IVTeacher {

    IVMTeacher modelTeacher;

    public VTeacher(Teacher t) {
        modelTeacher = t;
    }

    @Override
    public IVMTeacher getModelTeacher() {
        return modelTeacher;
    }

    @Override
    public JPanel draw(IVStudent curPlayer, GridBagConstraints c) {
        JPanel teacherPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
        c.insets = new Insets(43, 0, 5, 0);
        teacherPanel.setOpaque(false);
        c.gridx = 1;
        c.gridy = 2;
        c.gridwidth = 2;
        c.gridheight = 2;
        c.weighty = 1;
        c.ipadx = 00;
        c.ipady = 00;

        for (int i = 0; i < 10; i++) {
            JButton teacher = new JButton();
            teacher.setContentAreaFilled(false);
            teacher.setIcon(new ImageIcon("textures" + File.separator + "Teacher.png"));
            teacher.setPreferredSize(new Dimension(55, 100));
            teacherPanel.add(teacher);
        }

        return teacherPanel;
    }
}
