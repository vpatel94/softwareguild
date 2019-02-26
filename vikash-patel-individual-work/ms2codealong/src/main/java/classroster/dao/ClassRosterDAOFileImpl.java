/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classroster.dao;
import classroster.dto.*;
import java.util.*;
import java.io.*;


/**
 *
 * @author vpatel
 */
public class ClassRosterDAOFileImpl implements ClassRosterDAO
{
    private Map<String, Student> students = new HashMap<>();
    public static final String ROSTER_FILE = "roster.txt";
    public static final String DELIMITER = "::";
    
    @Override
    public void addStudent(String studentId, Student student) throws ClassRosterDAOException
    {
        Student newStudent = students.put(studentId, student);
        writeRoster();
        //return newStudent;
    }

    @Override
    public List<Student> getAllStudents() throws ClassRosterDAOException
    {
        loadRoster();
        return new ArrayList<Student>(students.values());
    }

    @Override
    public Student getStudent(String studentId) throws ClassRosterDAOException
    {
        loadRoster();
        return students.get(studentId);
    }

    @Override
    public Student removeStudent(String studentId) throws ClassRosterDAOException
    {
        Student removedStudent = students.remove(studentId);
        writeRoster();
        return removedStudent;
    }
    
    private void loadRoster() throws ClassRosterDAOException 
    {
        Scanner scanner;

        try {
            // Create Scanner for reading the file
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(ROSTER_FILE)));
        } catch (FileNotFoundException e) {
            throw new ClassRosterDAOException(
                    "-_- Could not load roster data into memory.", e);
        }
        // currentLine holds the most recent line read from the file
        String currentLine;
        // currentTokens holds each of the parts of the currentLine after it has
        // been split on our DELIMITER
        // NOTE FOR APPRENTICES: In our case we use :: as our delimiter.  If
        // currentLine looks like this:
        // 1234::Joe::Smith::Java-September2013
        // then currentTokens will be a string array that looks like this:
        //
        // ___________________________________
        // |    |   |     |                  |
        // |1234|Joe|Smith|Java-September2013|
        // |    |   |     |                  |
        // -----------------------------------
        //  [0]  [1]  [2]         [3]
        String[] currentTokens;
        // Go through ROSTER_FILE line by line, decoding each line into a 
        // Student object.
        // Process while we have more lines in the file
        while (scanner.hasNextLine()) {
            // get the next line in the file
            currentLine = scanner.nextLine();
            // break up the line into tokens
            currentTokens = currentLine.split(DELIMITER);
            // Create a new Student object and put it into the map of students
            // NOTE FOR APPRENTICES: We are going to use the student id
            // which is currentTokens[0] as the map key for our student object.
            // We also have to pass the student id into the Student constructor
            Student currentStudent = new Student(currentTokens[0]);
            // Set the remaining vlaues on currentStudent manually
            currentStudent.setFirstName(currentTokens[1]);
            currentStudent.setLastName(currentTokens[2]);
            currentStudent.setCohort(currentTokens[3]);

            // Put currentStudent into the map using studentID as the key
            students.put(currentStudent.getStudentId(), currentStudent);
        }
        // close scanner
        scanner.close();
    }
    
    /**
    * Writes all students in the roster out to a ROSTER_FILE.  See loadRoster
    * for file format.
    * 
    * @throws ClassRosterDaoException if an error occurs writing to the file
    */
   private void writeRoster() throws ClassRosterDAOException {
       // NOTE FOR APPRENTICES: We are not handling the IOException - but
       // we are translating it to an application specific exception and 
       // then simple throwing it (i.e. 'reporting' it) to the code that
       // called us.  It is the responsibility of the calling code to 
       // handle any errors that occur.
       PrintWriter write;
       
       try {
           write = new PrintWriter(new FileWriter(ROSTER_FILE));
       } catch (IOException e) {
           throw new ClassRosterDAOException(
                   "Could not save student data.", e);
       }

       // Write out the Student objects to the roster file.
       // NOTE TO THE APPRENTICES: We could just grab the student map,
       // get the Collection of Students and iterate over them but we've
       // already created a method that gets a List of Students so
       // we'll reuse it.
       List<Student> studentList = this.getAllStudents();
       for (Student currentStudent : studentList) {
           // write the Student object to the file
           write.println(currentStudent.getStudentId() + DELIMITER
                   + currentStudent.getFirstName() + DELIMITER 
                   + currentStudent.getLastName() + DELIMITER
                   + currentStudent.getCohort());
           // force PrintWriter to write line to the file
           write.flush();
       }
       // Clean up
       write.close();
   }
}
