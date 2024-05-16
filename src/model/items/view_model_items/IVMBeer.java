package model.items.view_model_items;

import model.items.EBeerState;

public interface IVMBeer extends IVMItem{
    Integer getTime();
    EBeerState getState();    
}