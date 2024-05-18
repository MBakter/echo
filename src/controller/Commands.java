package controller;

import view.IVItems;
import view.IVStudent;
import view.VTransistor;

public class Commands implements ICommands{

    public Controller controller;

    public Commands(Controller c){
        controller = c;
    }

    @Override
    public void interact(EInteract option, IVStudent curPlayer, IVItems item) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'interact'");
    }

    @Override
    public void interact(EInteractTransistor option, IVStudent curPlayer, VTransistor item) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'interact'");
    }


    
}
