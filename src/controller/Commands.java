package controller;

import view.IVStudent;
import view.VItem;
import view.VTransistor;

public class Commands implements ICommands{

    public Controller controller;

    public Commands(Controller c){
        controller = c;
    }

    @Override
    public void interact(EInteract option, IVStudent curPlayer, VItem item) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'interact'");
    }

    @Override
    public void interact(EInteractTransistor option, IVStudent curPlayer, VTransistor item) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'interact'");
    }


    
}
