package resourceallocationproject.Classes;

public class SimulatedAnnealing {

   private static double val = 100000;
   private static double coolingRate = 0.0003;


   private PreferenceTable preferenceTable;
   private CandidateSolution bestSolution;
   private CandidateSolution optimumSolution;
   private final static double e = 2.71828;

   public SimulatedAnnealing(PreferenceTable preferenceTable) {
      this.preferenceTable = preferenceTable;
      Search(preferenceTable);
      //SearchBestSolution(new CandidateSolution(preferenceTable));
   }

   public static double acceptanceProbability(int energy, int newEnergy, double temperature) {
      // If the new solution is better, accept it
      if (newEnergy < energy) {
         return 1.0;
      }
      // If the new solution is worse, calculate an acceptance probability
      return Math.exp(e * ((energy - newEnergy) / temperature));
   }

   public static CandidateSolution change(CandidateSolution sol, double temp, PreferenceTable table) {
      int prevEnergy = sol.getEnergy();
      CandidateSolution oldSol = sol;
      //sol.makeChange();
      int difference = 0;

      if (sol.getEnergy() < prevEnergy) {
         return sol;
      } else {
         if (acceptanceProbability(oldSol.getEnergy(), sol.getEnergy(), temp) > Math.random()) {
            return sol;
         } else {
            return oldSol;
         }
      }

   }

  

//   public int Algorithm(){
//      double temp = val;
//		CandidateSolution currentSolution = new CandidateSolution(preferenceTable);
//		bestSolution = currentSolution.clone();
//      System.out.println("Initial solution: " + currentSolution.getEnergy());
//      int  bestEnergy = currentSolution.getEnergy();
//      PreferenceTable table;
//      change(currentSolution, temp, preferenceTable);
//        
//        while (temp > 1) {
//        	table = new PreferenceTable(ResourceAllocationProject.sPath);
//        	CandidateSolution newSolution = new CandidateSolution(table);
//        	currentSolution = change(newSolution, temp, table);
//
//            if (currentSolution.getEnergy() < bestEnergy) {
//            	bestSolution = currentSolution;
//                bestEnergy = currentSolution.getEnergy();
//               
//            } else {
//    			if(acceptanceProbability(bestEnergy, currentSolution.getEnergy(), temp)  > Math.random()) {
//    				bestSolution = currentSolution;
//                    bestEnergy = currentSolution.getEnergy();
//    			} 
//    		}
//            temp *= 1-coolingRate;
//        }
//
//        return bestEnergy;
//	}
//   
   
   
   
   
   public void Search(PreferenceTable preferenceTable) {
      int bestVal = 0;
      int curVal = 0;
      int count = 0;
      
      bestVal = SearchBestSolution(new CandidateSolution(preferenceTable)).getEnergy();

      while (count < 10) {
         bestSolution = SearchBestSolution(new CandidateSolution(preferenceTable));
         curVal = bestSolution.getEnergy();
         if (bestVal > curVal) {
            bestVal = curVal;
            optimumSolution = bestSolution;
         }
         count++;
      }
      System.out.println("Final solution Energy: " + bestVal);
      optimumSolution.PrintContents();

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
      
      if (newEnergy > currentEnergy)
         if (acceptanceProbablity(currentEnergy, newEnergy, temperature))
            randomAssignment.undoChange();
   }   
   
   private static boolean acceptanceProbablity(int currentEnergy, int newEnergy, double temperature) {
      double probability = Math.exp((newEnergy - currentEnergy) / temperature);
      return probability > ResourceAllocationProject.RND.nextDouble();
   }
}
