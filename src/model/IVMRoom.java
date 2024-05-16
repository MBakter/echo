package model;

import model.player.veiw_model_players.IVMCleaner;
import model.player.veiw_model_players.IVMStudent;
import model.player.veiw_model_players.IVMTeacher;

public interface IVMRoom {
    IVMStudent[] getStudnetList ();
    IVMTeacher[] getTeacherList ();
    IVMCleaner[] getCleanerList ();
    IVMRoom[] getNeighbourList ();
    ERoomEffects[] getRoomState();
}
