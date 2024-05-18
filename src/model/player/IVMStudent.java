package model.player;

import java.util.ArrayList;

import view.IVItems;
import view.IVRoom;

public interface IVMStudent {
    public ArrayList<IVItems> getItemList();
    public EPlayerState getPlayerState();
    public IVRoom getVRoom();
    public int getTime();
}
