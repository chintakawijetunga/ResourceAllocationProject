package resourceallocationproject.Classes;

import java.util.ArrayList;

public class GeneticAlgorithm {
	   
	   public CandidateSolution search(PreferenceTable preferenceTable){
		  GAPopulation candidateSolutionPopulation = new GAPopulation(preferenceTable);
	      CandidateSolution bestSolution = candidateSolutionPopulation.getFittest();
	      ArrayList<String> studentNames = bestSolution.getStudentNames();
	      
	      for (int i = 0; i < 1000/*Util.GA_ROUND_COUNT*/; i++) {
	         candidateSolutionPopulation = evolvePopulation(candidateSolutionPopulation, studentNames);
	      }
	      
	      bestSolution = candidateSolutionPopulation.getFittest();
	      return bestSolution;
	   }
	   
	   private static GAPopulation evolvePopulation(GAPopulation population, ArrayList<String> studentNames) {
		  GAPopulation newPopulation = new GAPopulation(50/*Util.POPULATION_SIZE*/);
	      int newPopulationSize = newPopulation.size();
	      
	      CandidateSolution fittest = population.getFittest();
	      newPopulation.addCandidateSolution(0, fittest);
	      
	      for (int i = 1; i < newPopulationSize; i++) {
	         CandidateSolution parent1 = tournamentSelection(population);
	         CandidateSolution parent2 = tournamentSelection(population);
	         CandidateSolution child   = crossover(parent1, parent2, studentNames);
	         mutate(child);
	         newPopulation.addCandidateSolution(i, child);
	      }
	      return newPopulation;
	   }
	   
	   private static void mutate(CandidateSolution candidateSolution) {
	      candidateSolution.getAssignmentFor(name).randomizeAssignment();
	   }
	   
	   private static CandidateSolution crossover(CandidateSolution parent1, CandidateSolution parent2, ArrayList<String> studentNames) {
	      CandidateSolution childSolution = new CandidateSolution();
	      String name;
	      CandidateAssignment candidateAssignment;
	      
		  int a = PreferenceTable.RND.nextInt(limit);
	      int b = PreferenceTable.RND.nextInt(limit);
		  int lowerBound;
	      int upperBound;
		  if (a <= b) {
	         lowerBound = a;
			 upperBound = b;
	      } else {
	         lowerBound = b;
			 upperBound = a;
	      }
			 
	      for (int i = 0; i < studentNames.size(); i++) {
	         name = studentNames.get(i);
	         if (((lowerBound <= i) && (i <= upperBound))) {            
	            candidateAssignment = parent1.getAssignmentFor(name);
	         } else {
	            candidateAssignment = parent2.getAssignmentFor(name);
	         }
	         childSolution.addAssignment(candidateAssignment);
	      }
	      
	      return childSolution;
	   }
	   
	   private static CandidateSolution tournamentSelection(GAPopulation population) {
		  GAPopulation tournamentPopulation = new GAPopulation(50/*Util.TOURNAMENT_SIZE*/);
	      CandidateSolution candidateSolution;
	      int populationSize = population.size();
	      int randomIndex;
	      
	      for (int i = 0; i < 50/*Util.TOURNAMENT_SIZE*/; i++) {
	         randomIndex = PreferenceTable.RND.nextInt((populationSize));
	         candidateSolution = population.getCandidateSolution(randomIndex);
	         tournamentPopulation.addCandidateSolution(i, candidateSolution);
	      }
	      
	      candidateSolution = tournamentPopulation.getFittest();
	      return candidateSolution;
	   }
	   
	}
