package Theresa;

import java.util.EnumMap;
import java.util.HashMap;

public abstract class EventModeMap {
    private static HashMap<Event, EnumMap<AIMode, AIMode>> eventModeMap = new HashMap<Event, EnumMap<AIMode, AIMode>>();

    static {
        // create values for Event.HIT
        EnumMap<AIMode, AIMode> hitMap = new EnumMap<AIMode, AIMode>(AIMode.class);
        hitMap.put(AIMode.HUNT, AIMode.BRACKET);
        hitMap.put(AIMode.BRACKET, AIMode.PURSUIT);
        hitMap.put(AIMode.PURSUIT, AIMode.PURSUIT);
        hitMap.put(AIMode.REVPURSUIT, AIMode.REVPURSUIT);
        
        // create values for Event.MISS
        EnumMap<AIMode, AIMode> missMap = new EnumMap<AIMode, AIMode>(AIMode.class);
        missMap.put(AIMode.PURSUIT, AIMode.REVPURSUIT);
        missMap.put(AIMode.REVPURSUIT, AIMode.BRACKET);
        missMap.put(AIMode.HUNT, AIMode.HUNT);
        missMap.put(AIMode.BRACKET, AIMode.BRACKET);

        // create values for Event.OUT
        EnumMap<AIMode, AIMode> outMap = new EnumMap<AIMode, AIMode>(AIMode.class);
        outMap.put(AIMode.REVPURSUIT, AIMode.BRACKET);
        outMap.put(AIMode.BRACKET, AIMode.HUNT);
        outMap.put(AIMode.PURSUIT, AIMode.REVPURSUIT);
        outMap.put(AIMode.HUNT, AIMode.HUNT);

        // create values for Event.SUNK
        EnumMap<AIMode, AIMode> sunkMap = new EnumMap<AIMode, AIMode>(AIMode.class);
        sunkMap.put(AIMode.HUNT, AIMode.HUNT);
        sunkMap.put(AIMode.PURSUIT, AIMode.HUNT);
        sunkMap.put(AIMode.BRACKET, AIMode.HUNT);
        sunkMap.put(AIMode.REVPURSUIT, AIMode.HUNT);

        // add values to event ModeMap
        eventModeMap.put(Event.MISS, missMap);
        eventModeMap.put(Event.HIT, hitMap);
        eventModeMap.put(Event.OUT, outMap);
        eventModeMap.put(Event.SUNK, sunkMap);
    }

    static AIMode getMode(AIMode currentMode, Event currentEvent){
        return eventModeMap.get(currentEvent).get(currentMode);
    }
}
