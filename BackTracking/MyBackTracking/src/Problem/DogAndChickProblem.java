package Problem;

import Core.*;

import java.util.ArrayList;

public class DogAndChickProblem {
    static int numOfLeg = 100;
    public static class DogAndChickEval implements Evaluator{
        private int countLeg(Proposal proposal){
            for (Object o : proposal) if (!(o instanceof Integer)) return -1;
            return (Integer) proposal.get(0) * 2 + (Integer)proposal.get(1) * 4;
        }
        @Override
        public boolean isSatisfied(Proposal proposal) {
            return countLeg(proposal) == DogAndChickProblem.numOfLeg;
        }
    }

    public static void main(String[] args) {
        RealDomain<Integer> dogDomain = new RealDomain<>();
        for (int i = 0; i <= 25; i++) dogDomain.add(i);

        RealDomain<Integer> chickDomain = new RealDomain<>();
        for (int i = 0; i <=  50; i++) chickDomain.add(i);

        Variables variables = new Variables();
        variables.add(new Variable<Integer>(dogDomain));
        variables.add(new Variable<Integer>(chickDomain));

        BackTrackSolver solver = new BackTrackSolver(variables, new DogAndChickEval());
        ArrayList<Proposal> solutions = solver.betterGetALlSolution();
        for (Proposal solution : solutions) {
            System.out.println(solution);
        }
    }
}
