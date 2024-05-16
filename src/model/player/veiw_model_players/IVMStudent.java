package model.player.veiw_model_players;

import java.util.ArrayList;

import model.IVMRoom;
import model.items.view_model_items.IVMItem;
import model.player.EPlayerState;

public interface IVMStudent {
    ArrayList<IVMItem> getItemList();
    EPlayerState getPlayerState();
    IVMRoom getRoom();
}
