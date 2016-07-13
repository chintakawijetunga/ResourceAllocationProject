package resourceallocationproject.Classes;

import java.io.*;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Random;
import javax.swing.JOptionPane;
import java.util.StringTokenizer;
import java.util.Vector;

public class PreferenceTable  implements Cloneable{

   private Hashtable<String, StudentEntry> studentHashtable = new Hashtable<String, StudentEntry>();
   static Random RND = new Random();

   PreferenceTable() {
      // empty constructor
   }

   PreferenceTable(String sFileName) { // constructor which accept one parameter
      this.loadContentFromFile(sFileName); // calling the loadContentFromFile() bypaasing the file name.
   }

   private Vector<Vector> loadContentFromFile(String sFileName) {
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

      try {
         FileInputStream fileInputStream = new FileInputStream(sFileName);
         BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));

         while ((sLine = bufferedReader.readLine()) != null) { // reading the file
            // line by line
            // until the end.
            count = 0;
            if (!isFirstLine) {
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
               if (projectTopics.size() == 1) {
                  studentEntry.preAssignProject(projectTopics.firstElement());
               }
               studentHashtable.put(sStudentName, studentEntry); // putting the student entry object to the hashtable.
            }
            isFirstLine = false;
            vector2.add(vector1); // putting the student details vector to another vector.
         }

      } catch (Exception e) {
         JOptionPane.showMessageDialog(null, e.toString(), "InfoBox: Error when reading file",
                 JOptionPane.ERROR_MESSAGE);
      }
      return vector2;
   }

   public Vector<StudentEntry> getAllStudentEntries() {
      Enumeration<StudentEntry> eStudentEntry = studentHashtable.elements(); // getting elements in the hashtable to a enumerator.
      Vector<StudentEntry> vStudentDetails = new Vector<StudentEntry>();
      while (eStudentEntry.hasMoreElements()) {
         vStudentDetails.add((StudentEntry) eStudentEntry.nextElement()); // adding the details to the vector.
      }
      return vStudentDetails;
   }

   public StudentEntry getEntryFor(String sname) {
      if (studentHashtable.containsKey(sname)) // check whether the key exists in the hastable.
      {
         return (StudentEntry) studentHashtable.get(sname); // getting the specific student entry object.
      } else {
         return null;
      }
   }

   public StudentEntry getRandomStudent() {
		// generate a random no which is less than the no of entries in the studentHashTable, 
      // and using the getAllStudentEntries() get the vector containing all the StudentEntry
      // objects. Pick a index in that vector using the random no, and returning that 
      // StudentEntry object.
      return this.getAllStudentEntries().get(PreferenceTable.RND.nextInt(studentHashtable.size()));
   }

   public String getRandomPreference() {
//		// using getRandomStudent(), get a random student and using getRandomPreference() get a random
//		// project that student has. Returning that project as a string.
//		return this.getRandomStudent().getRandomPreference();
      StudentEntry student;
      String randomPreference = null;
      while (randomPreference == null) {
         student = getRandomStudent();
         if (!student.hasPreassignedProject()) {
            randomPreference = student.getRandomPreference();
         }
      }
      return randomPreference;
   }

   public void fillPreferencesOfAll(int maxPrefs) {
      for (StudentEntry student : getAllStudentEntries()) {
         if (!student.hasPreassignedProject()) {
            while (student.getOrderedPreferences().size() != maxPrefs) {
               student.addProject(getRandomPreference());
            }
         }
      }
   }

}
