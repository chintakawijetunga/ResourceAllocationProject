package resourceallocationproject.Classes;

import java.util.Vector;

public class StudentEntry implements Cloneable {

	String sStudentName;
	Boolean bPreAssigned = false;
	Vector<String> vProjects = new Vector<String>();
	int NoOfOrderedProjects = 0;
	String sPreAssignedProject = null;
	private String sAssingedProject = null;

	StudentEntry(String Name) { // constructor which sets the student name.
		this.sStudentName = Name;
	}

	public void setAssingedProject(String assingedProject) {
		this.sAssingedProject = assingedProject; // setting the project which is
													// assigned to the student.
	}

	public String getAssingedProject() {
		return this.sAssingedProject; // getting the project which is assigned
										// to the student.
	}

	public String getStudentName() {
		return sStudentName; // returns the student name.

	}

	public void SetNoOfOrderedProjects(int num) {
		this.NoOfOrderedProjects = num; // setting the no of ordered projects
	}

	public Vector<String> getOrderedPreferences() {
		return vProjects; // returning a vector containing the ordered projects.
	}

	public void preAssignProject(String pname) {
      bPreAssigned = true;
		sPreAssignedProject = pname; // Assign the preassigned project to
										// variable.
	}

	public Boolean hasPreassignedProject() {
		return bPreAssigned; // return a boolean stating whether its prearranged
								// or not
	}

	public int getNumberOfStatedPreferences() {
		return this.NoOfOrderedProjects; // returning the no or ordered projects
	}

	public void addProject(String pname) {
      if(!hasPreference(pname)) {
         vProjects.add(pname.intern()); // add a new project to the list
      }
	}

	public void setOrderedProject(Vector<String> projects) {
		vProjects = projects; // sets the ordered projects
	}

	public boolean hasPreference(String preference) {
		// checking whether a particular preference is already there in
		// students's project vector.
		return this.getOrderedPreferences().contains(preference);
	}

	public String getRandomPreference() {
		// generate a random no which is less than the projects stated by the
		// student and using that random no,
		// picking a project and returning it.
		return this.getOrderedPreferences().get(ResourceAllocationProject.RND.nextInt(this.getNumberOfStatedPreferences()));
	}

	public int currentProjSize() {
		return vProjects.size(); // done to test the code. returning the no. of
									// projects currently in the array.

	}

   /*
   Calculates the level of disappointment the student has with the assigned project.
   */
	public int getRanking(String proj) {
		int rank = 0;
		// If the project receive to the method exists in student's project list, return the index which it appears,
		// else return -1.
		if(hasPreassignedProject()) {
			return 0;
		}
		else if(hasPreference(proj.intern())) {
			rank = vProjects.indexOf(proj.intern());
		} 
		else {
			rank = -1;
		}
		return rank;
	}
   
	public String toString() {
		return "Name: "+this.sStudentName+" prefereces:"+getOrderedPreferences();
	}

}
