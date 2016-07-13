package resourceallocationproject.Classes;

public class SimulatedAnnealing extends Thread {

   private static double val = 100000;
   private static double coolingRate = 0.0003;

   private PreferenceTable preferenceTable;
   private CandidateSolution bestSolution;
   private CandidateSolution optimumSolution;
   private final static double e = 2.71828;

   public SimulatedAnnealing(PreferenceTable preferenceTable) {
      this.preferenceTable = preferenceTable;
   }

   public CandidateSolution Search() {
      int bestVal = 0;
      int curVal = 0;
      int count = 0;
      PreferenceTable preferenceTable = this.preferenceTable;
      bestVal = SearchBestSolution(new CandidateSolution(preferenceTable)).getEnergy();

      while (count < 1) {
         bestSolution = SearchBestSolution(new CandidateSolution(preferenceTable));
         curVal = bestSolution.getEnergy();
         if (bestVal > curVal) {
            bestVal = curVal;
            optimumSolution = bestSolution;
         }
         count++;
      }
      System.out.println("Final solution Energy: " + bestVal);
      return optimumSolution;
   }

   private static CandidateSolution SearchBestSolution(CandidateSolution candidateSolution) {
      CandidateSolution initialSolution = candidateSolution;
      CandidateSolution bestSolution = candidateSolution;
      double temperature = SimulatedAnnealing.val;
      int currentEnergy = 0;
      int bestEnergy = 0;

      while (temperature > 1) {
         CandidateSolution currentSolution = initialSolution.clone();
         modify(currentSolution, temperature);

         currentEnergy = currentSolution.getEnergy();
         bestEnergy = bestSolution.getEnergy();

         if (currentEnergy < bestEnergy) {
            bestSolution = currentSolution;
         }
         initialSolution = currentSolution;
         temperature *= 1 - SimulatedAnnealing.coolingRate;

      }
      System.out.println("Solution Energy: " + bestEnergy);
      return bestSolution;
   }

   private static void modify(CandidateSolution currentSolution, double temperature) {
      CandidateAssignment randomAssignment = currentSolution.getRandomAssignment();
      int currentEnergy = currentSolution.getEnergy();
      randomAssignment.randomizeAssignment();
      int newEnergy = currentSolution.getEnergy();

      if (newEnergy > currentEnergy) {
         if (acceptanceProbablity(currentEnergy, newEnergy, temperature)) {
            randomAssignment.undoChange();
         }
      }
   }

   private static boolean acceptanceProbablity(int currentEnergy, int newEnergy, double temperature) {
      double probability = Math.exp((newEnergy - currentEnergy) / temperature);
      return probability > PreferenceTable.RND.nextDouble();
   }
}
