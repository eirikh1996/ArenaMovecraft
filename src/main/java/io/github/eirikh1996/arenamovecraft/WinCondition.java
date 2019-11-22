package io.github.eirikh1996.arenamovecraft;



import java.util.HashMap;

public enum WinCondition {



    SINK_OPPONENTS, CRAFTS_LEFT, LAST_MAN_STANDING;

    private static HashMap<String, WinCondition> BY_NAME = new HashMap<>();

    static {
        for (WinCondition wc : WinCondition.values()){
            BY_NAME.put(wc.name(), wc);
        }
    }

    public static WinCondition getWinCondition(String name){
        if (!BY_NAME.containsKey(name.toUpperCase())){
            throw new IllegalArgumentException(name + " is not a valid win condition");
        }
        return BY_NAME.get(name.toUpperCase());
    }
}
