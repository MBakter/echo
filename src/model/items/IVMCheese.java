package model.items;

public interface IVMCheese extends IVMItems {
    /*
     * Modelbeli cheese ideje
     */
    int getTime();
    /*
     * Már használt-e a sajt a modelben
     */
    boolean isUsed();
}
