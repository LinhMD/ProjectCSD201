package BackTracking.core;

import java.util.ArrayList;

public class Proposal extends ArrayList<Integer> implements Comparable<Proposal> {
    double totalCost;
    public Proposal(){
        super();
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public ArrayList<Object> getRealObjects(VarList variables){
        ArrayList<Object> realProposal = new ArrayList<>();
        for (int i = 0; i < variables.size(); i++)
            realProposal.add(variables.get(i).getIndexDomain().getRealValue(this.get(i)));
        return realProposal;
    }

    @Override
    public int compareTo(Proposal o) {
        return Double.compare(this.getTotalCost(), o.getTotalCost());
    }
}
