package controller;

import java.util.ArrayList;
import java.util.List;

import model.ITimedEntity;
import model.ITimer;

public class Timer implements ITimer {
    private List<TimedObject> list;

    public String toString(){
        return "Timer@"+Integer.toString(this.hashCode()).substring(0, 4);
    }

    public Timer() {
        list = new ArrayList<TimedObject>();
    }

    public List<TimedObject> getList() { return list; }

    @Override
    public void addEntity(ITimedEntity e) {
        list.add(new TimedObject(e));
    }

    public void iterateTime() {
        for (TimedObject to : list) {
            if(to.isActive() && !to.iterateTime()) {
                to.getObject().timerEnd();
            }
        }
    }

    @Override
    public void startTimer(ITimedEntity e, int time) {
        for (TimedObject to : list) {
            if(to.getObject().equals(e) && to.isPaused()) 
                to.resume();
            if(to.getObject().equals(e) && !to.isActive()) 
                to.activate(time);
        }
    }

    @Override
    public void pauseTimer(ITimedEntity e) {
        for (TimedObject to : list) {
            if(to.getObject().equals(e)) {
                to.pause();
            }
        }
    }

    @Override
    public int getETA(ITimedEntity e) {
        for (TimedObject to : list) {
            if(to.getObject().equals(e))
                return to.getTime();
        }
        return 0;
    }
}
