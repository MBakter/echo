package model;

/**
 * IItem
 */
public interface IItem {
    public void useItem(Player p);
    public void pickUp(Player p);
    public void dropItem(Player p);
    
    public boolean TeacherAttacked(Student s);
    public boolean RoomPoisoned(Student s);
    public void RoomCleanFromPoison(Student s);
    public void TeacherAttackable(Student s);
}