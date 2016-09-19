package ssa;

import java.sql.SQLException;
import java.util.ArrayList;

public class Students {

    public ArrayList<Student> getAll() throws SQLException {
        ArrayList<Student> studentList = new ArrayList<Student>();

        try {

            DBConnection.connect();
            String sql = "select * from student;";
            DBConnection.pstmt = DBConnection.conn.prepareStatement(sql);
            DBConnection.rs = DBConnection.pstmt.executeQuery();

            while (DBConnection.rs.next()) {
                Student student = new Student();
                student.setId(DBConnection.rs.getInt("id"));
                student.setFirstName(DBConnection.rs.getString("first_name"));
                student.setLastName(DBConnection.rs.getString("last_name"));
                student.setSat(DBConnection.rs.getInt("sat"));
                student.setGpa(DBConnection.rs.getDouble("gpa"));
                student.setMajorId(DBConnection.rs.getInt("major_id"));
                studentList.add(student);
            }

            return studentList;

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (DBConnection.conn != null)
                DBConnection.conn.close();
            if (DBConnection.pstmt != null)
                DBConnection.pstmt.close();
            if (DBConnection.stmt != null)
                DBConnection.stmt.close();
            if (DBConnection.rs != null)
                DBConnection.rs.close();
        }
        return null;
    }

    public Student getById(int findId) throws SQLException {
        Student student = new Student();
        try {
            DBConnection.connect();
            String sql = "select * from student where id = ?;";
            DBConnection.pstmt = DBConnection.conn.prepareStatement(sql);
            DBConnection.pstmt.setInt(1, findId);
            DBConnection.rs = DBConnection.pstmt.executeQuery();

            while (DBConnection.rs.next()) {
                student.setId(DBConnection.rs.getInt("id"));
                student.setFirstName(DBConnection.rs.getString("first_name"));
                student.setLastName(DBConnection.rs.getString("last_name"));
                student.setSat(DBConnection.rs.getInt("sat"));
                student.setGpa(DBConnection.rs.getDouble("gpa"));
                student.setMajorId(DBConnection.rs.getInt("major_id"));
            }

            return student;

            // ResultSetMetaData rsmd = DBConnection.rs.getMetaData();
            // int columnsNumber = rsmd.getColumnCount();
            //
            // for (int i = 1; i <= columnsNumber; i++) {
            // System.out.printf("%-15s", rsmd.getColumnName(i));
            // }
            //
            // while (DBConnection.rs.next()) {
            // System.out.println();
            // for (int i = 1; i <= columnsNumber; i++) {
            // System.out.printf("%-15s", DBConnection.rs.getString(i));
            // }
            //
            // }
            //
            // rsmd = null;

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (DBConnection.conn != null)
                DBConnection.conn.close();
            if (DBConnection.pstmt != null)
                DBConnection.pstmt.close();
            if (DBConnection.stmt != null)
                DBConnection.stmt.close();
            if (DBConnection.rs != null)
                DBConnection.rs.close();
        }
        return null;
    }
}
