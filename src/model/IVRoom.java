package model;

import model.items.IItem;
import model.player.*;

public interface IVRoom {
  public boolean add(Student s);
  public boolean add(Teacher t);
  public boolean add(Cleaner c);
  public void addItem(IItem i);
  public boolean removeItem(IItem i);
}
