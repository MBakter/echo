package view;

public interface IMainWindow {
    void RefreshView();
    void InitWindow();
    void showError(String title);
    void endGame(boolean victory);
}
