package resourceallocationproject.Classes;

import java.util.Random;
import java.util.Vector;

public class ResourceAllocationProject {

	private static PreferenceTable preferenceTable;   
   private static SimulatedAnnealing simulatedAnnealing;
	protected static Random RND = new Random();
   protected static String sPath = "ProjectAllocationData.tsv";
	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {
      TestCode4();
	}
   
   public static void TestCode4(){
	   preferenceTable = new PreferenceTable(sPath);
      preferenceTable.fillPreferencesOfAll(10);
		simulatedAnnealing = new SimulatedAnnealing(preferenceTable);
   }
   
   public static void TestCode1() {
		// **********************************************************
		// Temparary code to test the StudentEntry class object.
		// 1. All Students
      sPath = "D:\\Personel\\Assign\\ResourceAllocationProject\\src\\resourceallocationproject\\Files\\ProjectAllocationData.tsv"; // setting
		preferenceTable = new PreferenceTable(sPath);
      preferenceTable.fillPreferencesOfAll(10);
		Vector<StudentEntry> test = preferenceTable.getAllStudentEntries();
		for (int i = 0; i < test.size(); i++) {
			System.out.print(test.get(i).currentProjSize() + "\t");
			System.out.print(test.get(i).getStudentName() + "\t");
			if (test.get(i).hasPreassignedProject()) {
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
			if (obj1.hasPreassignedProject()) {
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
			if (obj1.hasPreassignedProject()) {
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
