package controller;

import model.ITimer;

public class TimedObject {
    private int time;
    private ITimer object;
    private boolean active;
    private boolean paused;

    public TimedObject(ITimer o) {
        time = -1;
        object = o;
        active = false;
        paused = false;
    }
    
    public ITimer getObject() { return object; }
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
