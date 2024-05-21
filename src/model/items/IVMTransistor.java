package model.items;

public interface IVMTransistor extends IVMItems {
    /*
     * Aktiváltuk-e már a tranzisztort
     */
    boolean isActive();
    /*
     * Tranzisztor párjának a neve
     */
    String getPairName();
    /*
     * Tranzisztor neve
     */
    String getName();
}
