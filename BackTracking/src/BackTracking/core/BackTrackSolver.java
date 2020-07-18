package BackTracking.core;

import java.util.ArrayList;

public class BackTrackSolver {
    VarList variables;
    Evaluator evaluator;

    public BackTrackSolver(VarList variables, Evaluator evaluator) {
        this.variables = variables;
        this.evaluator = evaluator;
    }

    private void getNSolution(int varIndex, int n, ArrayList<Proposal> solutions){
        if(solutions.size() == n) return;
        Variable variable = variables.get(varIndex);
        IndexDomain Domain = variable.getIndexDomain();
        Domain.reset();
        while (Domain.hasNext()){// for int i = 0 i-->n
            variable.setD_Index(Domain.nextIndex());
            if(varIndex == this.variables.size() - 1){
                Proposal proposal = new Proposal();
                for (Variable variable1 : variables) proposal.add(variable1.getD_Index());
                if(evaluator.isSatisfied(proposal))
                    solutions.add(proposal);
            }else getNSolution(varIndex + 1, n, solutions);
        }
    
    }
    public ArrayList<Proposal> getAllSolution(){
        int varIndex = 0;
        ArrayList<Proposal> solutions = new ArrayList<>();
        while (varIndex != -1){
            Variable variable = variables.get(varIndex);
            IndexDomain domain = variable.getIndexDomain();
            if (domain.hasNext()){
                variable.setD_Index(domain.nextIndex());
                if(varIndex == (this.variables.size() - 1)){
                    Proposal proposal = new Proposal();
                    for (Variable value : variables) proposal.add(value.getD_Index());
                    if(evaluator.isSatisfied(proposal))
                        solutions.add(proposal);
                }else varIndex++;
            }else {
                varIndex--;
                domain.reset();
            }
        }
        return solutions;
    }

}
