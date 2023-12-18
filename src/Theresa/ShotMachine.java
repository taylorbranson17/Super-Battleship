package Theresa;

import Common.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

public class ShotMachine {

    private Map<AIMode, Shooter> shooterMap = new EnumMap<AIMode, Shooter>(AIMode.class);
    private ArrayList<Integer> shipLengths = new ArrayList<Integer>(Arrays.asList(2, 3, 3, 4, 5));
    private Coord firstHit;
    private Coord lastHit;
    private Direction pursuitDirection;
    private AIMode currentState = AIMode.HUNT;
    private int minShip;

    public ShotMachine() {
        minShip = sortShipLengths();
        shooterMap.put(AIMode.HUNT, new HuntShooter(minShip));
    }

    public Coord getShot() {
        Coord shot = null;
        while (true) {
            try {
                shot = shooterMap.get(currentState).getShot();
                break;
            } catch (IndexOutOfBoundsException e) {
                outHandler();
                currentState = EventModeMap.getMode(currentState, Event.OUT);
                continue;
            }
        }
        return shot;
    }

    public void receiveResult(ShotResult result) {
        Coord shotCoord = result.getCoord();
        Event event = result.getEvent();
        // HUNT Mode-> change to bracket on HIT. Disregard MISS event. If SUNK, continue
        // in HUNT mode with new shooter.
        if (currentState == AIMode.HUNT && result == ShotResult.HIT) {
            firstHit = shotCoord;
            lastHit = firstHit;
            shooterMap.put(AIMode.BRACKET, new BracketShooter(shotCoord));
        }
        // If unexpected SUNK, recalculate min ship Length, generate new
        if (currentState == AIMode.HUNT && result == ShotResult.SUNK) {
            recalcShipLengths(result.getShip());
            shooterMap.put(AIMode.HUNT, new HuntShooter(minShip));
        }

        // Bracket Mode -> Disregard misses, only change on HIT or SUNK results.
        if (currentState == AIMode.BRACKET && result == ShotResult.HIT) {
            lastHit = shotCoord;
            if (shotCoord.getX() == firstHit.getX() - 1) {
                pursuitDirection = Direction.W;
            }
            if (shotCoord.getX() == firstHit.getX() + 1) {
                pursuitDirection = Direction.E;
            }
            if (shotCoord.getY() == firstHit.getY() - 1) {
                pursuitDirection = Direction.W;
            }
            if (shotCoord.getY() == firstHit.getY() + 1) {
                pursuitDirection = Direction.S;
            }
            shooterMap.put(AIMode.PURSUIT, new PursuitShooter(pursuitDirection, lastHit));
        }
        if (currentState == AIMode.BRACKET && result == ShotResult.SUNK) {
            recalcShipLengths(result.getShip());
            shooterMap.put(AIMode.HUNT, new HuntShooter(minShip));
        }

        // Pursuit Mode-> Disregard hits, only change on MISS or SUNK results.
        // Change direction if a miss...
        if (currentState == AIMode.PURSUIT && result == ShotResult.MISS) {
            pursuitDirection = pursuitDirection.getOpp();
            shooterMap.put(AIMode.REVPURSUIT, new PursuitShooter(pursuitDirection, firstHit));
        }
        // If ship is SUNK, return to HUNT...
        if (currentState == AIMode.PURSUIT && result == ShotResult.SUNK) {
            recalcShipLengths(result.getShip());
            shooterMap.put(AIMode.HUNT, new HuntShooter(minShip));
        }

        // REVERSE PURSUIT MODE-> Disregard hits, change on MISS and SUNK...
        // If a miss is found, bracket around last hit...
        if (currentState == AIMode.REVPURSUIT && result == ShotResult.MISS) {
            shooterMap.put(AIMode.BRACKET, new BracketShooter(lastHit));
        }
        // If a ship is SUNK, refactor shipLengths, removing sunk ship, then return to
        // hunting...
        if (currentState == AIMode.REVPURSUIT && result == ShotResult.SUNK) {
            recalcShipLengths(result.getShip());
            shooterMap.put(AIMode.HUNT, new HuntShooter(minShip));
        }

        // Mark first & lastHits.
        if ((currentState == AIMode.PURSUIT || currentState == AIMode.REVPURSUIT) && result == ShotResult.HIT) {
            lastHit = shotCoord;
        }

        this.currentState = EventModeMap.getMode(currentState, event);
    }

    private int sortShipLengths() {
        Collections.sort(shipLengths);
        return shipLengths.get(0);
    }

    private void recalcShipLengths(String shipName) {
        int shipLength = 0;
        switch (shipName.toLowerCase()) {
            case "battleship" -> shipLength = 4;
            case "carrier" -> shipLength = 5;
            case "patrol boat" -> shipLength = 2;
            case "destroyer" -> shipLength = 3;
            case "submarine" -> shipLength = 3;
        }
        shipLengths.remove((Integer) shipLength);
        minShip = sortShipLengths();
    }

    private void outHandler(){
        if(currentState == AIMode.PURSUIT){
            pursuitDirection = pursuitDirection.getOpp();
            shooterMap.put(AIMode.REVPURSUIT, new PursuitShooter(pursuitDirection, firstHit));
        }
        if(currentState == AIMode.BRACKET){
            minShip = sortShipLengths();
            shooterMap.put(AIMode.HUNT, new HuntShooter(minShip));
        }
        if(currentState == AIMode.REVPURSUIT){
            shooterMap.put(AIMode.HUNT, new BracketShooter(lastHit));
        }
    }

    /*******************TEST ONLY METHODS ********************/
    public AIMode getState(){
        return this.currentState;
    }

    public Coord getLastHit(){
        return this.lastHit;
    }

    public Coord getFirstHit(){
        return this.firstHit;
    }
}
