package resourceallocationproject.Classes;

public class GAPopulation {
	private final CandidateSolution[] solutions;
    
    GAPopulation(int size) {
       solutions = new CandidateSolution[size];
    }
    
    GAPopulation(PreferenceTable preferenceTable) {
	   solutions = new CandidateSolution[50/*Util.POPULATION_SIZE*/];
       for (int i = 0; i < solutions.length; i++) {
          solutions[i] = new CandidateSolution(preferenceTable);
       }
    }
    
    public void addCandidateSolution(int index, CandidateSolution solution) {
       solutions[index] = solution;
    }
    
    public CandidateSolution getCandidateSolution(int index) {
       return solutions[index];
    }
    
    public int size() {
       return solutions.length;
    }
    
    public CandidateSolution getFittest() {
       CandidateSolution fittest = solutions[0];
       double bestFitness = fittest.getFitness();
       double fitness;
       
       for (int i = 0; i < solutions.length; i++) {
          fitness = solutions[i].getFitness();
          if (fitness > bestFitness) {
             bestFitness = fitness;
             fittest = solutions[i];
          }
       }
       
       return fittest;
    }
}
