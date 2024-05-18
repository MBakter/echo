package controller;

import view.*;

public interface ICommands {
    public void interact(EInteract option, IVStudent curPlayer, IVItems item);
    
    public void interact(EInteractTransistor option, IVStudent curPlayer, VTransistor item);

    //TODO egyéb commandok
}
