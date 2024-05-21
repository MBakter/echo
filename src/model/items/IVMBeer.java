package model.items;

public interface IVMBeer extends IVMItems{
    /*
     * Sör maradék ideje
     */
    int getTime();
    /*
     * Sör jelenlegi állapota
     */
    EBeerState getState();
}
