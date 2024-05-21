package model.items;

public interface IVMMask extends IVMItems {
    /*
     * Mask maradék ideje
     */
    int getTime();
    /*
     * Működőképes-e a maszk
     */
    boolean isFunctional();
    /*
     * Hamis-e a mask
     */
    boolean isFake();
}
