package Problem;

import Core.*;

import java.util.ArrayList;

public class ItemConsultant {
    double budget;
    Variables variables;
    ItemConsultantEval itemConsultantEval;

    public ItemConsultant(double budget, Variables variables) {
        this.budget = budget;
        this.variables = variables;
        this.itemConsultantEval = new ItemConsultantEval();
    }

    private class ItemConsultantEval implements Evaluator{

        private double computeCost(Proposal proposal){
            for (Object o : proposal) if (!(o instanceof Item)) return Double.MAX_VALUE;
            double totalCost = 0d;
            for (Object o : proposal) totalCost += ((Item) o).getPrice();
            proposal.setCost(totalCost);
            return totalCost;
        }

        @Override
        public boolean isSatisfied(Proposal proposal) {
            return computeCost(proposal) <= budget;
        }
    }
    public void print(Proposal proposal){
        System.out.println("A solution:");
        for (Object o : proposal) System.out.println(o);
        System.out.println("cost: " + (int)proposal.getCost());
    }

    public static void main(String[] args) {

        RealDomain<Item> tvDom = new RealDomain<>();
        tvDom.add(new Item("TV01", "Sony 42", 4800000));
        tvDom.add(new Item("TV02", "LG 42", 3800000));
        tvDom.add(new Item("TV03", "Samsung 48", 750000));
        tvDom.add(new Item("TV04", "Sony 55", 14800000));
        tvDom.add(new Item("TV05", "LG 65", 16800000));
        tvDom.add(new Item("TV06", "Samsung 55", 11200000));

        RealDomain<Item> rfDom = new RealDomain<>();
        rfDom.add(new Item("RF01", "National 420", 11800000));
        rfDom.add(new Item("RF02", "General 380", 10800000));
        rfDom.add(new Item("RF03", "Panasonic 180", 3500000));
        rfDom.add(new Item("RF04", "Hitachi 380", 9800000));
        rfDom.add(new Item("RF05", "Beko 250", 6700000));

        Variables variables = new Variables();
        for (int i = 0; i < 3; i++) variables.add(new Variable<>(tvDom));
        for (int i = 0; i < 2; i++) variables.add(new Variable<>(rfDom));

        ItemConsultant itemConsultant = new ItemConsultant(20000000, variables);

        ArrayList<Proposal> solutions = new BackTrackSolver(itemConsultant.variables, itemConsultant.itemConsultantEval).betterGetALlSolution();
        for (Proposal solution : solutions) itemConsultant.print(solution);
    }
}
