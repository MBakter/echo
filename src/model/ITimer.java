package model;

public interface ITimer {
    public void startTimer(ITimedEntity e, int TIME);
    public void pauseTimer(ITimedEntity e);
    public void addItem(ITimedEntity e);
}
