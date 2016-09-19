package ssa;

import java.io.*;
import java.sql.*;
import java.util.*;

public class DBConnection {

     static Connection conn = null;
     static PreparedStatement pstmt = null;
     static ResultSet rs = null;
     static Statement stmt = null;

    static void connect() throws Exception {

        try {
            Properties prop = new Properties();
            prop.load(new FileInputStream("library/connection.properties"));
            String userName = prop.getProperty("userName");
            String pass = prop.getProperty("pass");
            String url = prop.getProperty("url");

            conn = DriverManager.getConnection(url, userName, pass);

        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

}
