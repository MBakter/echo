package model.player;

import java.util.ArrayList;

import view.VItem;

public interface IVMTeacher {
    public EPlayerState getPlayerState();
    public ArrayList<VItem> getItems();
    public int getTime();
}
