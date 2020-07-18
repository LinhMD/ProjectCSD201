package Core;

import java.util.ArrayList;

public class Proposal extends ArrayList<Object> {
    double cost = 0;

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
