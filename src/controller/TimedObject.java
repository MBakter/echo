package controller;

import model.ITimer;

public class TimedObject {
    private int time;
    private ITimer object;
    private boolean active;

    public TimedObject(ITimer i) {
        time = -1;
        object = i;
        active = false;
    }
    
    public ITimer getObject() { return object; }
    public int getTime() { return time; }
    public boolean isActive() {return active; }
    public void activate(int t) { 
        time = t;
        active = true;
    }
    public void pause() { active = false; }

    /*
     * Returns *false* if timer is finished 
     *         *true* is timer is still running or inactive
     */
    public boolean iterateTime() {
        if(!active)
            return true;
        if(time <= 0 )
            return false;
        time--;
        if(time == 0)
            active = false;
        return time == 0 ? false : true;
    }
}
