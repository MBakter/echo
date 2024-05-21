package model.items;

public enum EBeerState {
    INACTIVE,
    RUNNING,
    DISABLED;

    public static String convertToString(EBeerState s) {
        switch (s) {
            case INACTIVE:
                return "Inactive";
            case RUNNING:
                return "Running";
            case DISABLED:
                return "Disabled";
            default:
                return "Not defined state";
        }
    }
}
