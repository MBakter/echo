package controller;

import java.util.List;

public class Timer {
    private List<model.ITimer> items;
    public Timer(){
        //System.out.println("<<create>> Student");
    }

    public void startTimer() {}
    public void pauseTimer() {
        System.out.println("pauseTimer()");
    }
}
