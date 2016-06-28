/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resourceallocationproject;

import java.io.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;
import javax.swing.JOptionPane;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 *
 * @author L D Chintaka Wijetunga 14209687
 */
public class PreferenceTable {

	String sFileName;
	private Hashtable<String, StudentEntry> studentHashtable = new Hashtable<String, StudentEntry>();
	private int noOfStudents = 0;

	PreferenceTable() {
		// empty constructor
	}

	PreferenceTable(String sName) { // constructor which accept one parameter
		this.loadContentFromFile(sName); // calling the loadContentFromFile()
											// bypaasing the file name.
	}

	private Vector<Vector> loadContentFromFile(String sName) {
		// **** local variable declaration
		String sLine;
		StringTokenizer tokens;
		String sNext = null;
		String sPreAsssinged = null;
		String sStudentName = null;
		Boolean isFirstLine = true;
		int numTokens;
		StudentEntry studentEntry = null;
		Vector<String> projectTopics = null;
		Vector<StudentEntry> vStudentEntry = new Vector<StudentEntry>();
		int count = 0;
		Vector<String> vector1 = null;
		Vector<Vector> vector2 = new Vector();
		// ********

		this.sFileName = sName;
		try {
			FileInputStream fileInputStream = new FileInputStream(this.sFileName);
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));

			while ((sLine = bufferedReader.readLine()) != null) { // reading the file
															// line by line
															// until the end.
				count = 0;
				if (!isFirstLine) {
					noOfStudents++;
					tokens = new StringTokenizer(sLine, "\t"); // tokenizing the
																// line.
					numTokens = tokens.countTokens();
					sStudentName = tokens.nextToken(); // getting the name.
					vector1 = new Vector<>();
					vector1.add(sStudentName);// adding the name to the string type
											// vector.
					studentEntry = new StudentEntry(sStudentName);
					projectTopics = new Vector<String>();
					while (tokens.hasMoreTokens()) {
						// reading the other parts of the line
						if (count == 0) {
							sPreAsssinged = tokens.nextToken();
							count = 1;
						}
						if (count == 1) {
							sNext = tokens.nextToken();
							projectTopics.add(sNext);
						}
						vector1.add(sNext); // adding to the string vector
					}
					studentEntry.SetNoOfOrderedProjects(numTokens - 2);
					studentEntry.setOrderedProject(projectTopics);
					if (projectTopics.size() == 1)
						studentEntry.preAssignProject(projectTopics.firstElement());
					studentHashtable.put(sStudentName, studentEntry); // putting the
															// student entry
															// object to the
															// hashtable.
				}
				isFirstLine = false;
				vector2.add(vector1); // putting the student details vector to another
							// vector.
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.toString(), "InfoBox: Error when reading file",
					JOptionPane.ERROR_MESSAGE);
		}
		return vector2;
	}

	public Vector<StudentEntry> getAllStudentEntries() {
		Enumeration<StudentEntry> eStudentEntry = studentHashtable.elements(); // getting
																// elements in
																// the hashtable
																// to a
																// enumerator.
		Vector<StudentEntry> vStudentDetails = new Vector<StudentEntry>();
		while (eStudentEntry.hasMoreElements()) {
			vStudentDetails.add((StudentEntry) eStudentEntry.nextElement()); // adding the
																	// details
																	// to the
																	// vector.
		}
		return vStudentDetails;
	}

	public StudentEntry getEntryFor(String sname) {
		if (studentHashtable.containsKey(sname)) // check whether the key exists in
												// the hastable.
		{
			return (StudentEntry) studentHashtable.get(sname); // getting the
															// specific student
															// entry object.
		} else {
			return null;
		}
	}

	

	public StudentEntry getRandomStudent() {
		// generate a random no which is less than the no of entries in the studentHashTable, 
		// and using the getAllStudentEntries() get the vector containing all the StudentEntry
		// objects. Pick a index in that vector using the random no, and returning that 
		// StudentEntry object.
		return this.getAllStudentEntries().get(ResourceAllocationProject.RND.nextInt(studentHashtable.size()));
	}

	public String getRandomPreference() {
		// using getRandomStudent(), get a random student and using getRandomPreference() get a random
		// project that student has. Returning that project as a string.
		return this.getRandomStudent().getRandomPreference();
	}

	public void fillPreferencesOfAll(int maxPrefs) {
		Vector<StudentEntry> vAllStudentEntries = this.getAllStudentEntries(); // get all the StudentEntry objects.
		int noOfPrefToAdd = 0;
		int count = 0;
		String prefToAdd = null;

		for (int i = 0; i < vAllStudentEntries.size(); i++) { // Looping through the StudentEntry objects
			count = 0;
			noOfPrefToAdd = maxPrefs - vAllStudentEntries.get(i).getNumberOfStatedPreferences(); // get the no of projects to
																								 // to be added to that particular 
																								 // student to archive that max 
																								 //preference value.
			if (noOfPrefToAdd > 0) {
				while (count < noOfPrefToAdd) {
					prefToAdd = this.getRandomPreference(); // get a random project.
					if (!vAllStudentEntries.get(i).hasPreference(prefToAdd)) { // checking whether the student has that project.
						vAllStudentEntries.get(i).addProject(prefToAdd); // adding the project to the student's project vector.
						count++;
					}
				}
			}
		}
	}
   
   public void findUniqueProjects(){
   
   }
}
