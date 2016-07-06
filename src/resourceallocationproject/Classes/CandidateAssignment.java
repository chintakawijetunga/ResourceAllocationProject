package resourceallocationproject.Classes;

public class CandidateAssignment {

	private StudentEntry studentEntry = null;
	private String previousAssignment = null;
	private String currentAssignment = null;

	CandidateAssignment(StudentEntry studentEntry) {
		this.studentEntry = studentEntry; // encapsulating the studentEntry object.
		this.randomizeAssignment(); // calling randomizeAssignment()
	}

	public void randomizeAssignment() {
		this.currentAssignment = this.studentEntry.getRandomPreference().intern(); // getting a random preference.
		studentEntry.setAssingedProject(this.currentAssignment); // setting it as the assigned project. (before this, should check whether any other student has been assigned with this)
      
		// ******
		// Code to undo the changes back to previous if needed. (To be added)
		// this.undoChange();
		this.previousAssignment = this.currentAssignment;
	}

	public void undoChange() {
		if (!this.previousAssignment.equals(null)) {
			this.currentAssignment = this.previousAssignment;// undoing the currentAssignment by assigning it with the previous assignment.
			this.studentEntry.setAssingedProject(null);
		}
	}

	public StudentEntry getStudentEntry() {
		return null; // to be implemented.
	}

	public String getAssignedProject() {
		return this.currentAssignment; // returning the currentAssignment
	}

	public int getEnergy() {
		int rank = 0;
		int energy = 0; 
		// Passing the current protect assigned to the student and get the ranking of it.
		rank = studentEntry.getRanking(currentAssignment);
		// Calculating the energy using the rank value.
		energy = (int) Math.pow((rank + 1), 2);
		
		return energy;
	}
	
	public String toString() {
		// Overiding the toString() to fascilitate easy printing of StudentEntry objects.
		return studentEntry.getStudentName()+": " + currentAssignment;
	}
}
