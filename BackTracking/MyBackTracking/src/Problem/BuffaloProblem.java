package Problem;

import Core.*;

import java.util.ArrayList;

public class BuffaloProblem {
    int numOfBuffalo = 0;
    int numOfWhisks = 0;
    BuffaloEvaluate evaluate;

    public BuffaloProblem(int numOfBuffalo, int numOfWhisks) {
        this.numOfBuffalo = numOfBuffalo;
        this.numOfWhisks = numOfWhisks;
        evaluate = new BuffaloEvaluate();
    }

    public class BuffaloEvaluate implements Evaluator{
        private int countGrass(Proposal proposal){
            if(proposal.size() != 3) return 0;
            for (Object o : proposal) if (!(o instanceof Integer)) return 0;
            if (((Integer) proposal.get(2) % 2) == 1) return 0;
            return (Integer) proposal.get(0) + (Integer)proposal.get(1) *2 + (Integer)proposal.get(2) /2;
        }
        private int countBuffalo(Proposal proposal){
            for (Object o : proposal) if (!(o instanceof Integer)) return -1;
            int i = 0;
            for (Object o : proposal) i += (Integer) o;
            return i;
        }
        @Override
        public boolean isSatisfied(Proposal proposal) {
            return countGrass(proposal) == numOfWhisks && countBuffalo(proposal) == numOfBuffalo;
        }
    }

    public static void main(String[] args) {
        int numOfWhisks = 100;
        int numOfBuffaloes = 100;
        BuffaloProblem problem = new BuffaloProblem(numOfBuffaloes, numOfWhisks);

        RealDomain<Integer> domain = new RealDomain<>();
        for (int i = 0; i < 100; i++) domain.add(i);

        Variables variables = new Variables();
        for (int i = 0; i < 3; i++) variables.add(new Variable<>(domain));

        Evaluator evaluator = problem.evaluate;
        BackTrackSolver solver = new BackTrackSolver(variables, evaluator);

        ArrayList<Proposal> proposals = solver.betterGetALlSolution();
        for (Proposal proposal : proposals) System.out.println(proposal);
    }
}

