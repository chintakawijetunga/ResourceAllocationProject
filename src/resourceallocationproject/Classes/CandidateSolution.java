package resourceallocationproject.Classes;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

public class CandidateSolution {
	private PreferenceTable preferenceTable = null;
	private CandidateAssignment candidateAssignment = null;
	private Hashtable<String, CandidateAssignment> candidateAssignements;
	private Hashtable<String, String> projectsAssigned;
	static final int defaultPenalty = 1000;

	public CandidateSolution(PreferenceTable preferenceTable) {
		this.preferenceTable = preferenceTable; // encapsulating the PreferenceTable object.
		FillCandAssignHashTable();
	}

	public void FillCandAssignHashTable() {
		// populating the Hashtable with the CandidateAssignment instances respective
		// to each student setting the student name as the key.
		this.candidateAssignements = new Hashtable<String, CandidateAssignment>();
		for (StudentEntry studentEntries : preferenceTable.getAllStudentEntries()) {
			candidateAssignements.put(studentEntries.getStudentName(), new CandidateAssignment(studentEntries));
		}
	}

	public CandidateAssignment getAssignmentFor(String sStudentName) {
		// initializing candidateAssignment by using the sStudentName to get a
		// instance of StudentEntry using getEntryFor().
		candidateAssignment = candidateAssignements.get(sStudentName);
		return candidateAssignment; // returning that instance.
	}

	public CandidateAssignment getRandomAssignment() {
		// returning an instance of CandidateAssignment which is chosen at random
		// from the keys it has.
		Vector<String> keySet = new Vector<String>(candidateAssignements.keySet());
		return candidateAssignements.get(keySet.elementAt(ResourceAllocationProject.RND.nextInt(keySet.size())));
	}

	public int getFitness() {
		// rather than returning the inverse of the Energy, return the negative of Energy
		// to handle higher Energy values.
		return -1 * getEnergy();
	}

	public int getEnergy() {
		int totalEnergyValue = 0;
		// Getting all the element of CandidateAssignments
		Enumeration<CandidateAssignment> canAssignEnum = candidateAssignements.elements();
		// Looping through all the CandidateAssignments and calculating the energy for each and
		// getting the total energy
		while (canAssignEnum.hasMoreElements()) {
			totalEnergyValue += canAssignEnum.nextElement().getEnergy();
		}
		// Adding the Penalty value.
		totalEnergyValue += getPenalties();
		// Returning the 
		return totalEnergyValue;
	}

	public int getPenalties() {
		int totalPenaltyValue = 0;
		CandidateAssignment candidateAssignment = null;
      String assignedProject = null;
      projectsAssigned = new Hashtable<>();
		// Getting all the element of CandidateAssignments
		Enumeration<CandidateAssignment> canAssignEnum = candidateAssignements.elements();
		// Looping through each CandidateAssignments, add the assigned project in CandidateAssignments
		// to a string variable, check whether the added CandidateAssignments project exists in the Hashtable.
		// If exists, add a penalty of 1000, else, add the CandidateAssignments project to the 
		// projectAssigned hashtable for future use.
		while (canAssignEnum.hasMoreElements()) {
			candidateAssignment = canAssignEnum.nextElement();
         assignedProject = candidateAssignment.getAssignedProject().intern();
			if (projectsAssigned.containsKey(assignedProject)) {
				totalPenaltyValue += defaultPenalty;
			} else {
				projectsAssigned.put(assignedProject, "Assigned");
			}         
		}
		// returning the penalty value.
		return totalPenaltyValue;
	}
   
   //  
   public int getEnergy2(){
   Vector<CandidateAssignment> assignments = new Vector<>(candidateAssignements.values());
   int totalEnergy = 0;
   int totalPenalty = 0;
   String assignedProject;
   
   for (CandidateAssignment assignment : assignments){
      assignedProject = assignment.getAssignedProject().intern();
      if (projectsAssigned.containsKey(assignedProject)){
         totalPenalty += defaultPenalty;
      }
      else{
         projectsAssigned.put(assignedProject, "Assigned");
      }
      totalEnergy += assignment.getEnergy();
   }     
      totalEnergy += totalPenalty;
      return totalEnergy;
   }
}
