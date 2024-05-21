package view;

public interface IMainWindow {
    /*
     * Megjelenített kép és képen megjelenő adatok frissítése
     */
    void RefreshView();
    /*
     * Kezdeti menü kép
     */
    void InitWindow();
    /*
     * Felugró ablakok megjelenítésére
     */
    void showError(String title);
    /*
     * Győzelmi kép
     */
    void endGame(boolean victory);
}
