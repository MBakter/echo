package model;

import java.util.ArrayList;

import view.*;

public interface IVMRoom {

    /*
     * Hallgatók listája, nézetbeli interface-ként visszaadva
     */
    public ArrayList<IVStudent> getStudentList();

    /*
     * Oktatók listája, nézetbeli interface-ként visszaadva
     */
    public ArrayList<IVTeacher> getTeacherList();

    /*
     * Cleanerek listája, nézetbeli interface-ként visszaadva
     */
    public ArrayList<IVCleaner> getCleanerList();

    /*
     * Szomszédok listája, nézetbeli interface-ként visszaadva
     */
    public ArrayList<IVRoom> getNeighBourList();

    /*
     * Effektusok listája
     */
    public ArrayList<ERoomEffects> getRoomState();

    /*
     * Földön lévő tárgyak listája, nézetbeli interface-ként visszaadva
     */
    public ArrayList<IVItems> getRoomItems();

    /*
     * Név lekérése
     */
    public String getName();

    /*
     * Mérgezett-e a szoba
     */
    public boolean isPoisonous();

    /*
     * Átkozott-e a szoba
     */
    public boolean isCursed();

    /*
     * Tiszta-e a szoba
     */
    public boolean isClean();
}
