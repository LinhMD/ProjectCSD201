package Core;

import java.util.ArrayList;

public class BackTrackSolver {
    Variables variables;
    Evaluator evaluator;

    public BackTrackSolver(Variables variables, Evaluator evaluator) {
        this.variables = variables;
        this.evaluator = evaluator;
    }

    private void getAllSolution(Integer varIndex, ArrayList<Proposal> solutions){
        for (Object e : this.variables.get(varIndex).getDomain()) {
            this.variables.get(varIndex).setInstantValue(e);
            if (varIndex == (this.variables.size() - 1)) { // when varIndex reach the last var in varList then adding all instant value to new proposal
                Proposal proposal = new Proposal();
                for (Variable variable : variables) proposal.add(variable.getInstantValue());
                if (this.evaluator.isSatisfied(proposal)) solutions.add(proposal);
            } else getAllSolution(varIndex + 1, solutions);
        }
    }

    public ArrayList<Proposal> getAllSolution(){
        ArrayList<Proposal> solutions = new ArrayList<>();
        Integer varIndex = 0;
        getAllSolution(varIndex, solutions);
        return solutions;
    }
    public ArrayList<Proposal> betterGetALlSolution(){
        ArrayList<Proposal> solutions = new ArrayList<>();
        int varIndex = 0;
        while(varIndex != -1){
            Variable variable = this.variables.get(varIndex);
            if(variable.domainIterator.hasNext()){
                variable.setInstantValue(variable.domainIterator.next());
                if(varIndex == (this.variables.size() - 1)){
                    Proposal proposal = new Proposal();
                    for (Variable value : variables)
                        proposal.add(value.getInstantValue());
                    if(evaluator.isSatisfied(proposal))
                        solutions.add(proposal);
                }else
                    varIndex++;
            }else {
                varIndex--;
                variable.resetDomain();
            }
        }
        return solutions;
    }
}
