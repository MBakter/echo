package controller;

import view.*;

public interface ICommands {
    public void interact(EInteract option, IVStudent curPlayer, VItem item);
    
    public void interact(EInteractTransistor option, IVStudent curPlayer, VTransistor item);

    //TODO egyéb commandok
}
