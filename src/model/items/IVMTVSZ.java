package model.items;

public interface IVMTVSZ extends IVMItems{
        /*
         * TVSZ maradék élete
         */
        int getHitpoints();
        /*
         * Hamis-e a tvsz
         */
        boolean isFake();
}
