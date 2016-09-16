package ssa;

import java.io.*;
import java.sql.*;
import java.util.*;

public class Mainline {

    private static Connection myConnection = null;
    private static PreparedStatement myStatement = null;
    private static ResultSet myResultSet = null;
    private static Statement stmt = null;

    private static void connect() throws SQLException {

        try {
            Properties prop = new Properties();
            prop.load(new FileInputStream("lib/connection.properties"));
            String userName = prop.getProperty("userName");
            String pass = prop.getProperty("pass");
            String url = prop.getProperty("url");

            myConnection = DriverManager.getConnection(url, userName, pass);

        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    private static void insertStudent(String firstName, String lastName, int sat, double gpa) throws SQLException {
        connect();
        try {
            myStatement = myConnection
                    .prepareStatement("insert into student (first_name,last_name,sat,gpa) values (?,?,?,?)");
            myStatement.setString(1, firstName);
            myStatement.setString(2, lastName);
            myStatement.setInt(3, sat);
            myStatement.setDouble(4, gpa);

            myStatement.executeUpdate();

        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            if (myConnection != null)
                myConnection.close();
            if (myStatement != null)
                myStatement.close();
            if (myResultSet != null)
                myResultSet.close();
        }

    }

    private static void updateMajor(String firstName, String lastName, String major) throws SQLException {
        connect();
        try {

            Statement temporaryStatement1 = myConnection.createStatement();
            Statement temporaryStatement2 = myConnection.createStatement();

            ResultSet myResultSet1 = temporaryStatement1
                    .executeQuery("select req_sat from major where description = '" + major + "'");

            ResultSet myResultSet2 = temporaryStatement2.executeQuery("select sat from student where first_name = '"
                    + firstName + "'" + "and last_name = '" + lastName + "'");

            int reqSatScore = 0;
            int satScore = 0;

            while (myResultSet1.next()) {
                reqSatScore = myResultSet1.getInt("req_sat");
            }

            while (myResultSet2.next()) {
                satScore = myResultSet2.getInt("sat");
            }

            if (satScore >= reqSatScore) {
                myStatement = myConnection.prepareStatement(
                        "update student set major_id = " + "(select id from major where description = '" + major + "') "
                                + "where first_name = '" + firstName + "' AND last_name = '" + lastName + "';");

                myStatement.executeUpdate();
            } else if (satScore < reqSatScore) {
                System.out.println(firstName + " " + lastName + " has a SAT Score of : " + satScore
                        + " | Required SAT Score: " + reqSatScore);
            }

            temporaryStatement1.close();
            temporaryStatement2.close();
            myResultSet1.close();
            myResultSet2.close();

        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            if (myConnection != null)
                myConnection.close();
            if (myStatement != null)
                myStatement.close();
            if (myResultSet != null)
                myResultSet.close();
        }

    }

    private static void addClasses(String firstName, String lastName, int classId) throws SQLException {
        connect();
        try {
            Statement temporaryStatement = myConnection.createStatement();

            int foundStudentId = 0;

            myResultSet = temporaryStatement.executeQuery("select id from student where first_name = '" + firstName
                    + "' AND last_name = '" + lastName + "';");

            while (myResultSet.next()) {
                foundStudentId = myResultSet.getInt("id");
            }

            myStatement = myConnection
                    .prepareStatement("insert into student_class_relationship(student_id, class_id) values (?,?)");

            myStatement.setInt(1, foundStudentId);
            myStatement.setInt(2, classId);

            myStatement.executeUpdate();

            temporaryStatement.close();

        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            if (myConnection != null)
                myConnection.close();
            if (myStatement != null)
                myStatement.close();
            if (myResultSet != null)
                myResultSet.close();
        }

    }

    private static void displayStudent(String firstName, String lastName) throws SQLException {

        int satScore = 0;
        int reqSatScore = 0;
        String majorDescription = "";

        int major_id = 0;
        int class_id = 0;

        connect();
        try {

            stmt = myConnection.createStatement();
            Statement stmt2 = myConnection.createStatement();
            Statement stmt3 = myConnection.createStatement();

            myResultSet = stmt.executeQuery(
                    "select student.sat,major.req_sat,major.description from student join major on student.major_id = major.id where first_name = '"
                            + firstName + "' and last_name = '" + lastName + "';");

            ResultSet myResultSet2 = stmt2.executeQuery(
                    "select * from student join student_class_relationship on student.id = student_class_relationship.student_id where first_name = '"
                            + firstName + "' and last_name = '" + lastName + "';");

            while (myResultSet.next()) {
                satScore = myResultSet.getInt("sat");
                reqSatScore = myResultSet.getInt("req_sat");
                majorDescription = myResultSet.getString("description");
            }

            System.out.println("Education System - Enrollment Process");
            System.out.println("=====================================");
            System.out.println();
            System.out.println("Enrolled " + firstName + " " + lastName + " as a new student.");
            System.out.println("Assigned " + firstName + " " + lastName + " to " + majorDescription
                    + " which requires a required SAT score of " + reqSatScore);
            System.out.println("Enrolled " + firstName + " " + lastName + " in the following four classes:");

            while (myResultSet2.next()) {
                major_id = myResultSet2.getInt("major_id");
                class_id = myResultSet2.getInt("class_id");

                ResultSet myResultSet3 = stmt3.executeQuery("select * from major_class_relationship where major_id = '"
                        + major_id + "' and class_id = '" + class_id + "';");

                if (myResultSet3.next()) {
                    System.out.println(class_id + " required for major");

                } else {
                    System.out.println(class_id + " elective (not required for major)");
                }

            }

            System.out.println();
            System.out.println("_____________________________________");
            System.out.println();
            
        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            if (myConnection != null)
                myConnection.close();
            if (myStatement != null)
                myStatement.close();
            if (myResultSet != null)
                myResultSet.close();
        }

    }

    public static void main(String[] args) throws SQLException {
        // TODO Auto-generated method stub
        // Mainline.insertStudent("Adam", "Zapel", 1200, 3.0);
        // Mainline.updateMajor("Adam", "Zapel", "Finance");
        // Mainline.addClasses("Adam", "Zapel", 10101);
        // Mainline.addClasses("Adam", "Zapel", 10102);
        // Mainline.addClasses("Adam", "Zapel", 20403);
        // Mainline.addClasses("Adam", "Zapel", 20404);
        Mainline.displayStudent("Adam", "Zapel");

//        Mainline.insertStudent("Graham", "Krakir", 500, 2.5);
//        Mainline.updateMajor("Graham", "Krakir", "General Studies");
//        Mainline.addClasses("Graham", "Krakir", 10101);
//        Mainline.addClasses("Graham", "Krakir", 10102);
//        Mainline.addClasses("Graham", "Krakir", 20403);
//        Mainline.addClasses("Graham", "Krakir", 20404);
        Mainline.displayStudent("Graham", "Krakir");

//        Mainline.insertStudent("Ella", "Vader", 800, 3.0);
//        Mainline.updateMajor("Ella", "Vader", "Accounting");
//        Mainline.addClasses("Ella", "Vader", 10101);
//        Mainline.addClasses("Ella", "Vader", 10102);
//        Mainline.addClasses("Ella", "Vader", 20403);
//        Mainline.addClasses("Ella", "Vader", 20404);
        Mainline.displayStudent("Ella", "Vader");

//        Mainline.insertStudent("Stanley", "Kupp", 1350, 3.3);
//        Mainline.updateMajor("Stanley", "Kupp", "Engineering");
//        Mainline.addClasses("Stanley", "Kupp", 10101);
//        Mainline.addClasses("Stanley", "Kupp", 10102);
//        Mainline.addClasses("Stanley", "Kupp", 20403);
//        Mainline.addClasses("Stanley", "Kupp", 20404);
        Mainline.displayStudent("Stanley", "Kupp");

//        Mainline.insertStudent("Lou", "Zar", 950, 3.0);
//        Mainline.updateMajor("Lou", "Zar", "Education");
//        Mainline.addClasses("Lou", "Zar", 10101);
//        Mainline.addClasses("Lou", "Zar", 10102);
//        Mainline.addClasses("Lou", "Zar", 20403);
//        Mainline.addClasses("Lou", "Zar", 20404);
        Mainline.displayStudent("Lou", "Zar");
    }

}
