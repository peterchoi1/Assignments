package ssa;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class Student {

    private int id;
    private String firstName;
    private String lastName;
    private int sat;
    private double gpa;
    private int majorId;

//    public void deleteStudent(Student student, String table) throws SQLException {
//        try {
//            
//            DBConnection.connect();
//            
//             ResultSetMetaData rsmd = DBConnection.rs.getMetaData();
//             int columnsNumber = rsmd.getColumnCount();
//             String columnHeaders = null;
//            
//             for (int i = 1; i <= columnsNumber; i++) {
//                 columnHeaders +=  "where " + rsmd.getColumnName(i) +  " = ? ";
//             }
//            
//            
//            
//            
//            String sql = "delete from " + table + " " + columnHeaders;
//            DBConnection.pstmt = DBConnection.conn.prepareStatement(sql);
//            DBConnection.pstmt.setInt(1, this.id);
//            DBConnection.pstmt.setString(2, this.firstName);
//            DBConnection.pstmt.setString(3, this.lastName);
//            DBConnection.pstmt.setInt(4, this.sat);
//            DBConnection.pstmt.setDouble(5, this.gpa);
//            DBConnection.pstmt.setInt(6, this.majorId);
//
//            DBConnection.pstmt.executeUpdate();
//            
//            rsmd = null;
//
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        } finally {
//            if (DBConnection.conn != null)
//                DBConnection.conn.close();
//            if (DBConnection.pstmt != null)
//                DBConnection.pstmt.close();
//            if (DBConnection.stmt != null)
//                DBConnection.stmt.close();
//            if (DBConnection.rs != null)
//                DBConnection.rs.close();
//        }
//    }

    public void updateStudent(Student student) throws SQLException {

        try {

            DBConnection.connect();
            String sql = "update student set first_name = ?, last_name = ?, sat = ?, gpa = ?, major_id = ? where id = ?";
            DBConnection.pstmt = DBConnection.conn.prepareStatement(sql);
            DBConnection.pstmt.setString(1, this.firstName);
            DBConnection.pstmt.setString(2, this.lastName);
            DBConnection.pstmt.setInt(3, this.sat);
            DBConnection.pstmt.setDouble(4, this.gpa);
            DBConnection.pstmt.setInt(5, this.majorId);
            DBConnection.pstmt.setInt(6, this.id);

            DBConnection.pstmt.executeUpdate();

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

    }

    @Override
    public String toString() {
        String prettyPrint = String.format("%-5d%-10s%-20s%-5.2f%-5d", this.id, this.firstName, this.lastName, this.gpa,
                this.sat);
        return prettyPrint;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getSat() {
        return sat;
    }

    public void setSat(int sat) {
        this.sat = sat;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public int getMajorId() {
        return majorId;
    }

    public void setMajorId(int majorId) {
        this.majorId = majorId;
    }

}
