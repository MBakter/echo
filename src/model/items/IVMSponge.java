package model.items;

public interface IVMSponge extends IVMItems {
    /*
     * Sponge maradék ideje
     */
    int getTime();
    /*
     * Működőképes-e a sponge
     */
    boolean isFunctional();
}
