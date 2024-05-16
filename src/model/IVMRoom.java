package model;

import java.util.List;

import model.items.view_model_items.IVMItem;
import model.player.veiw_model_players.IVMCleaner;
import model.player.veiw_model_players.IVMStudent;
import model.player.veiw_model_players.IVMTeacher;

public interface IVMRoom {
    List<IVMStudent> getStudnetList ();
    List<IVMTeacher> getTeacherList ();
    List<IVMCleaner> getCleanerList ();
    List<IVMRoom> getNeighbourList ();
    List<ERoomEffects> getRoomState();
    List<IVMItem> getRoomItems();

}
