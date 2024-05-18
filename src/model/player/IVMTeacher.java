package model.player;

import java.util.ArrayList;

import view.IVItems;

public interface IVMTeacher {
    public EPlayerState getPlayerState();
    public ArrayList<IVItems> getItems();
    public int getTime();
}
