/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resourceallocationproject;

import java.util.Random;
import java.util.Vector;

/**
 *
 * @author L D Chintaka Wijetunga 14209687
 */
public class ResourceAllocationProject {

	private static PreferenceTable preferenceTable;   
   private static SimulatedAnnealing simulatedAnnealing;
	protected static Random RND = new Random();
   protected static String sPath = null;
	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {
      TestCode4();
	}
   
   public static void TestCode4(){
      sPath = "D:\\Personel\\Assign\\ResourceAllocationProject\\src\\resourceallocationproject\\ProjectAllocationData.tsv"; // setting
		preferenceTable = new PreferenceTable(sPath);
      preferenceTable.fillPreferencesOfAll(10);
      CandidateSolution candidateSolution = new CandidateSolution(preferenceTable);
		String name = preferenceTable.getRandomStudent().getStudentName();
//		System.out.println("Assignment: "+candidateSolution.getAssignmentFor(name));
//		System.out.println("**********************************************************************************");
//		System.out.println("Randon Assignment: "+candidateSolution.getRandomAssignment());
//		System.out.println("**********************************************************************************");
//      System.out.println("Energy: " + candidateSolution.getEnergy());
//		System.out.println("**********************************************************************************");
      simulatedAnnealing = new SimulatedAnnealing(preferenceTable);
   }
   
   public static void TestCode1() {
		// **********************************************************
		// Temparary code to test the StudentEntry class object.
		// 1. All Students
		Vector<StudentEntry> test = preferenceTable.getAllStudentEntries();
		for (int i = 0; i < test.size(); i++) {
			System.out.print(test.get(i).currentProjSize() + "\t");
			System.out.print(test.get(i).getStudentName() + "\t");
			if (test.get(i).hasPreassignedPeoject()) {
				System.out.print("Yes" + "\t");
			} else {
				System.out.print("No" + "\t");	
			}
			Vector<String> projectTopics = new Vector<String>();
			projectTopics = test.get(i).getOrderedPreferences();

			for (int j = 0; j < projectTopics.size(); j++) {
				System.out.print(projectTopics.get(j) + "\t");
			}

			System.out.println();
		}
	}

	public static void TestCode2(String name) {
		// 2. One particular Student
		 StudentEntry obj1 = preferenceTable.getEntryFor(name);
		//StudentEntry obj1 = preferenceTable.getRandomStudent();
		if (obj1 != null) {
			System.out.print(obj1.getStudentName() + "\t");
			if (obj1.hasPreassignedPeoject()) {
				System.out.print("Yes" + "\t");
			} else {
				System.out.print("No" + "\t");
			}
			Vector<String> projectTopics = new Vector<String>();
			projectTopics = obj1.getOrderedPreferences();
			for (int j = 0; j < projectTopics.size(); j++) {
				System.out.println(projectTopics.get(j) + "\t");
			}
		} else {
			System.out.print("Specified Student namenot found");
		}
		System.out.println();
		// ***********************************************************
	}

	public static void TestCode3(StudentEntry obj) {
		StudentEntry obj1 = obj;
		if (obj1 != null) {
			System.out.print(obj1.getNumberOfStatedPreferences() + "\t");
			System.out.print(obj1.getStudentName() + "\t");
			if (obj1.hasPreassignedPeoject()) {
				System.out.print("Yes" + "\t");
			} else {
				System.out.print("No" + "\t");
			}
			Vector<String> projectTopics = new Vector<String>();
			projectTopics = obj1.getOrderedPreferences();
			for (int j = 0; j < projectTopics.size(); j++) {
				System.out.print(projectTopics.get(j) + "\t");
			}
		} else {
			System.out.print("Specified Student namenot found");
		}
		System.out.println();
		// ***********************************************************
	}

}
