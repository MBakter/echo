package model;

public interface ITimer {
    /*
     * Időzítő indítása az adott idővel
     */
    public void startTimer(ITimedEntity e, int TIME);
    /*
     * Időzítő szüneteltetése
     */
    public void pauseTimer(ITimedEntity e);
    /*
     * Új időzített objektum felvétele
     */
    public void addEntity(ITimedEntity e);
    /*
     * Hátralévő idő lekérése
     */
    public int getETA(ITimedEntity e);
}
