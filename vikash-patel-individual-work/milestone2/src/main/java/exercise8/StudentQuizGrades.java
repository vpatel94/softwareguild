/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercise8;
import java.util.*;
/**
 *
 * @author vpatel
 */
public class StudentQuizGrades 
{
    private InOut io1;
    private HashMap<String, ArrayList<Integer>> studentGrades;
    
    public StudentQuizGrades()
    {
        io1 = new InOut();
        studentGrades = new HashMap();
    }
    
    public void run()
    {
        boolean run = true;
        do{
            io1.print("********************");
            io1.print("Welcome to your classroom!");
            io1.print("1.) Add a student");
            io1.print("2.) Remove a student");
            io1.print("3.) View all students");
            io1.print("4.) View list of quiz grades for student");
            io1.print("5.) View average quiz grade for student");
            io1.print("6.) Quit");
            int choice = io1.readInt("Please select one of the number options above: ");
            switch (choice){
                case 1: addStudentGrades(); break;
                case 2: removeStudent(); break;
                case 3: viewStudents(); break;
                case 4: viewGrades(); break;
                case 5: findAverage(); break;
                case 6: run = false; break;
                default: io1.print("Incorrect value.");
            }        
        } while(run);
        io1.print("Have a good day!");
    }
    
    public void findAverage()
    {
        String name = io1.readString("Enter Student Name: ");
        double sum = 0, avg;
        for(int i : studentGrades.get(name))
        {
            sum += (double) i;
        }
        avg = sum/(double) studentGrades.get(name).size();
        io1.print("Quiz Average for " + name + " is: " + String.valueOf(avg));
        io1.print("********************");
    }
    
    public void viewGrades()
    {
        String name = io1.readString("Enter Student Name: ");
        io1.print("Showing quiz grades for " + name + ": ");
        for(int i : studentGrades.get(name))
        {
            io1.print(String.valueOf(i));
        }
        io1.print("********************");
    }
    
    public void viewStudents()
    {
        io1.print("Current Roster of Students:");
        for(String student : studentGrades.keySet())
        {
            io1.print(student);
        }
        io1.print("********************");
    }
    
    public ArrayList<Integer> addGrades()
    {
        ArrayList<Integer> grades = new ArrayList<>();
        while(true)
        {
            int grade = io1.readInt("Enter Quiz Grade (Enter 999 To End): ");
            if(grade == 999)
            {
                io1.print("All quizzes were successfully entered.");
                break;
            }
            grades.add(grade);
        }
        return grades;
    }
    
    public void addStudentGrades()
    {
        studentGrades.put(io1.readString("Enter student name to add: "), addGrades());
        io1.print("Student and grades saved.\n********************");
    }
    
    public void removeStudent()
    {
        studentGrades.remove(io1.readString("Enter student name to remove: "));
        io1.print("Student removed.\n********************");
    }
}
