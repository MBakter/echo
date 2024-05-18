package view;

import java.awt.GridBagConstraints;

import javax.swing.JPanel;

import model.IVMRoom;

public interface IVRoom {
    IVMRoom getModelRoom();
    JPanel draw(IVStudent curPlayer, GridBagConstraints c);
    //Ezeket az Alex írta valami Observer minta megvalósításához, elvileg nem fog kelleni
/*     public boolean add(Student s);

    public boolean add(Teacher t);

    public boolean add(Cleaner c);

    public void addItem(IItem i);

    public boolean removeItem(IItem i); */

}
