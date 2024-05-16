package model.items;

import java.util.ArrayList;

import model.items.view_model_items.IVMItem;
import model.player.*;

public interface IItem extends IVMItem{
    public String getName();
    // Tudom hogy ez csúnya.
    public void printStat(String name);
    public void statesOptions();
    public void setState(ArrayList<String> args);
    /*
     * A tárgy használata.
     * Paraméterként kap egy Player-t
     * Majd a funkcióját megvalósítja
     */
    public void useItem(Player p);

    /*
     * A tárgy felvétele.
     * Paraméterként kap egy Student-et
     * Majd a hallgató listájába beleteszi magát illetve 
     * tulajdonságából adódóan csinálhat mást is
     */
    public void pickUp(Student s);

    /*
     * A tárgy felvétele.
     * Paraméterként kap egy Teacher-t
     * Majd az oktató listájába beleteszi magát 
     */
    public void pickUp(Teacher t);

    /*
     * A tárgy felvétele.
     * Paraméterként kap egy Cleaner-t
     * Majd a takarító listájába beleteszi magát 
     */
    public void pickUp(Cleaner c);

    /*
     * A tárgy eldobása.
     * Paraméterként kap egy Player-t
     * Majd a játékos listájából kiveszi magát illetve 
     * ha olyan a tulajdonsága akkor megállítja a stoppert
     */
    public void dropItem(Player p);
    
    /*
     * Tanár támadásakor védekezés
     * Paraméterként kap egy Student-et, majd megvédheti őt
     * A védekezésre alkalmas tárgy működésekor true-t ad vissza
     * Egyébként false a visszatérési értéke
     */
    public boolean TeacherAttacked(Student s);

    /*
     * Szoba mérgezésekor védekezés
     * Paraméterként kap egy Student-et, majd megvédheti őt
     * A védekezésre alkalmas tárgy működésekor true-t ad vissza
     * Egyébként false a visszatérési értéke
     */
    public boolean RoomPoisoned(Student s);

    /*
     * Szoba megtisztulása
     * Paraméterként kap egy Student-et, majd
     * reagál a szoba megtisztulására
     */
    public void RoomCleanFromPoison(Student s);

    /*
     * Tanár támadásakor visszatámadás
     * Paraméterként kap egy Student-et, majd megvédheti őt
     * A támadásra alkalmas tárgy működésekor true-t ad vissza
     * Egyébként false a visszatérési értéke
     */
    public boolean TeacherAttackable(Student s);
}