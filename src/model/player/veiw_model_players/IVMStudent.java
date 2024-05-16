package model.player.veiw_model_players;

import model.IVMRoom;
import model.items.view_model_items.IVMItem;
import model.player.EPlayerState;

public interface IVMStudent {
    IVMItem[] getItemList();
    EPlayerState getPlayerState();
    IVMRoom getRoom();
}
