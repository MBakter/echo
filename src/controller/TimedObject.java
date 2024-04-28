package controller;

import model.ITimedEntity;

public class TimedObject {
    private int time;
    private ITimedEntity entity;
    private boolean active;
    private boolean paused;

    public TimedObject(ITimedEntity e) {
        time = -1;
        entity = e;
        active = false;
        paused = false;
    }
    
    public ITimedEntity getObject() { return entity; }
    public int getTime() { return time; }
    public boolean isActive() {return active; }
    public boolean isPaused() {return paused; }
    public void activate(int t) { 
        time = t;
        active = true;
    }
    public void pause() { paused = true; }

    public void resume() { paused = false; }
    /*
     * Returns *false* if timer is finished 
     *         *true* is timer is still running or inactive
     */
    public boolean iterateTime() {
        if(!active || paused)
            return true;
        if(time <= 0 )
            return false;
        time--;
        if(time == 0)
            active = false;
        return time == 0 ? false : true;
    }
}
