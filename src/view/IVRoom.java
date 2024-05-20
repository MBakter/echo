package view;

import javax.swing.JButton;

import model.IVMRoom;

public interface IVRoom {
    IVMRoom getModelRoom();
    void draw(IVStudent curPlayer, JButton c);
    //Ezeket az Alex írta valami Observer minta megvalósításához, elvileg nem fog kelleni
/*     public boolean add(Student s);

    public boolean add(Teacher t);

    public boolean add(Cleaner c);

    public void addItem(IItem i);

    public boolean removeItem(IItem i); */

}
