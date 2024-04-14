package controller;

import java.util.ArrayList;
import java.util.List;

import model.ITimer;

public class Timer {
    private List<TimedObject> list;

    public String toString(){
        return "Timer@"+Integer.toString(this.hashCode()).substring(0, 4);
    }

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
            if(to.getObject().equals(o) && to.isPaused()) 
                to.resume();
            if(to.getObject().equals(o) && !to.isActive()) 
                to.activate(time);
            
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
