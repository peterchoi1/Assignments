package ssa;

import java.sql.SQLException;
import java.util.ArrayList;

public class Mainline {

    public static void main(String[] args) throws SQLException {
        // TODO Auto-generated method stub

        Students students = new Students();
        // retrieve a single student with id = 1
        Student aStudent = students.getById(100);
        // display the student
        System.out.println(aStudent); // displays the student data in a
                                      // formatted way
        // retrieve all the students into a collection
        ArrayList<Student> allStudents = students.getAll();
        // iterate through the collection and display each student data in a
        // formatted way
        for (Student student : allStudents) {
            System.out.println(student);
        }

        // aStudent.setFirstName("Eric");
        // aStudent.updateStudent(aStudent);

        // aStudent.deleteStudent(aStudent, "Student");

    }
}
