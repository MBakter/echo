package model.items;

import model.player.Player;
import model.player.Student;
import model.player.Teacher;

public interface IItem {

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