package resourceallocationproject.Classes;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.Random;
import java.util.Vector;

public class ResourceAllocationProject {

   private static PreferenceTable preferenceTable;
   private static SimulatedAnnealing simulatedAnnealing;
   private static CandidateSolution candidateSolution;
   protected static String sPath = "ProjectAllocationData.tsv";

   /**
    * @param args
    * the command line arguments
    */
   public static void main(String[] args) {
      TestCode();
   }

   public static void TestCode() {
      preferenceTable = new PreferenceTable(sPath);
      preferenceTable.fillPreferencesOfAll(10);
      simulatedAnnealing = new SimulatedAnnealing(preferenceTable);
      candidateSolution = simulatedAnnealing.Search();
      PrintContents();
   }

   public static void PrintContents() {
      String output = "";
      int count = 0;
      int[] prefRanks = new int[10];
      for (int k = 0; k < 10; k++) {
         prefRanks[k] = 0;
      }
      Enumeration<CandidateAssignment> eStudentEntry = candidateSolution.GetCandidateAssignements().elements();
      Vector<CandidateAssignment> vStudentDetails = new Vector<CandidateAssignment>();

      while (eStudentEntry.hasMoreElements()) {
         vStudentDetails.add((CandidateAssignment) eStudentEntry.nextElement());
         count++;
      }

      for (int i = 0; i < count; i++) {
         String StudentName = (String) candidateSolution.GetCandidateAssignements().keySet().toArray()[i];
         String AssignedProject = vStudentDetails.get(i).getAssignedProject();
         StudentEntry std = ResourceAllocationProject.preferenceTable.getEntryFor(StudentName);
         String preassigned = null;
         if (std.bPreAssigned) {
            preassigned = "Preassigned";
         } else {
            preassigned = "Not Preassigned";
         }
         int rank = std.getRanking(AssignedProject);
         StringBuilder space = new StringBuilder();
         for (int toPrepend = 20 - StudentName.length(); toPrepend > 0; toPrepend--) {
            space.append('-');
         }
         StringBuilder space1 = new StringBuilder();
         for (int toPrepend = 130 - (StudentName + "   " + space + AssignedProject).length(); toPrepend > 0; toPrepend--) {
            space1.append('*');
         }
         ////////////////////////
         switch (rank) {
            case 0: {
               prefRanks[0] += 1;
               break;
            }
            case 1: {
               prefRanks[1] += 1;
               break;
            }
            case 2: {
               prefRanks[2] += 1;
               break;
            }
            case 3: {
               prefRanks[3] += 1;
               break;
            }
            case 4: {
               prefRanks[4] += 1;
               break;
            }
            case 5: {
               prefRanks[5] += 1;
               break;
            }
            case 6: {
               prefRanks[6] += 1;
               break;
            }
            case 7: {
               prefRanks[7] += 1;
               break;
            }
            case 8: {
               prefRanks[8] += 1;
               break;
            }
            case 9: {
               prefRanks[9] += 1;
               break;
            }
         }

         ///////////////////////
         output += StudentName + "   " + space + AssignedProject + space1 + (rank + 1) + "  " + preassigned + "\n";
         System.out.println(StudentName + "   " + space + AssignedProject + space1 + (rank + 1) + "  " + preassigned);
      }
      for (int k = 0; k < 10; k++) {
         output += "Pref " + (k + 1) + ": " + prefRanks[k] + "\n";
         System.out.println("Pref " + (k + 1) + ": " + prefRanks[k]);
      }
      PrintFile(output);
   }

   public static void PrintFile(String content) {
      try {
         PrintWriter writer = new PrintWriter("OutputFile.txt", "UTF-8");
         writer.write(content);
         writer.close();
      } catch (Exception e) {
      }
   }
}
