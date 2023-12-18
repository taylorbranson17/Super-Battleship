package Accountant;
import Common.Ship;
import java.util.ArrayList;

public class StatCell {

    /* private boolean priority = false; */
    private int flag = 1;
    private int weight = 0;
    private ArrayList<Ship> shipRefs = new ArrayList<Ship>();

    public void addWeight(int weight) {
        if (flag == 1){
            this.weight += weight;
        }
    }

    public void reset() {
        this.shipRefs.clear();
        this.weight = this.shipRefs.size();
    }

    public void addShip(Ship ship) {
        if (flag == 1) {
            this.shipRefs.add(ship);
            this.weight = this.shipRefs.size();
        }
    }

    public void hitWeight(){
        this.flag--;
        this.weight = 0;
    }

    public void dropWeight() {
        this.shipRefs.clear();
        this.weight = this.shipRefs.size();
        this.flag = 0;
    }

    public ArrayList<Ship> getShips() {
        return this.shipRefs;
    }

    public int getWeight() {
        return this.weight;
    }

    public int print() {
        return this.weight;
    }

    public void setHit() {
        for (Ship ship : this.shipRefs) {
            ship.setHit();
        }
    }

}
