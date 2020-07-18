package Problem;

import Core.*;

public class BitStringGenerate {
    public static void main(String[] args) {
        RealDomain<Integer> booleanSet = new RealDomain<>();
        booleanSet.add(0);
        booleanSet.add(1);

        Variables variables = new Variables();
        int numOfVal = 4;
        for (int i = 0; i < numOfVal; i++) variables.add(new Variable<>(booleanSet));

        BackTrackSolver backTrackSolver = new BackTrackSolver(variables, new DefaultEvaluator());
        for (Proposal solution : backTrackSolver.getAllSolution()) {
            System.out.println(solution);
        }
    }
}
