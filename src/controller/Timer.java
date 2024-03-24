package controller;

import java.util.ArrayList;
import java.util.List;

import model.ITimer;

class TimedObject {
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
        return time == 0 ? false : true;
    }
}

public class Timer {
    private List<TimedObject> list;

    public Timer() {
        list = new ArrayList<TimedObject>();
    }

    public List<TimedObject> getList() { return list; }

    public void addItem(ITimer o) {
        list.add(new TimedObject(o));
    }

    public void iterateTime() {
        for (TimedObject to : list) {
            if(to.isActive() && !to.iterateTime()) {
                to.getObject().timerEnd();
            }
        }
    }

    public void startTimer(ITimer o, int time) {
        for (TimedObject to : list) {
            if(to.getObject().equals(o)) {
                to.activate(time);
            }
        }
    }

    public void pauseTimer(ITimer o) {
        for (TimedObject to : list) {
            if(to.getObject().equals(o)) {
                to.pause();
            }
        }
    }
}
