/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vocabulary;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Sasith
 */
public class SQLiteDictionary {

    Connection c = null;
    Statement stmt = null;
    ResultSet rs = null;

    //eng to sin answer return
    public String plainSearchEng(String inputText) {
        String result = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite::resource:vocabulary.sqlite");
            c.setAutoCommit(false);

            stmt = c.createStatement();
            rs = stmt.executeQuery("select si_meaning from en_si where en_word = '" + inputText.toLowerCase() + "';");

            while (rs.next()) {
                result = rs.getString("si_meaning");
            }
            rs.close();
            stmt.close();
            c.close();

        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return result;
    }

    // sin to eng answer return
    public String plainSearchSin(String inputText) {
        String result = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite::resource:vocabulary.sqlite");
            c.setAutoCommit(false);

            stmt = c.createStatement();
            rs = stmt.executeQuery("select en_meaning from si_en where si_word = '" + inputText.toLowerCase() + "';");

            while (rs.next()) {
                result = rs.getString("en_meaning");
            }

            rs.close();
            stmt.close();
            c.close();

        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return result;
    }
    
    // eng to eng answer return
    public ResultSet plainSearchEngToEng (String inputText) {
        char input [] = inputText.toCharArray();
        input [0] = Character.toUpperCase(input[0]);
        String inputUpperCased = new String (input);
        ResultSet rs = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite::resource:eng_eng_db.sqlite");
            c.setAutoCommit(false);

            stmt = c.createStatement();
            rs = stmt.executeQuery("select * from entries where word = '"+inputUpperCased+"';");
            
//            rs.close();
//            stmt.close();
//            c.close();

        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return rs;
    }

    // eng words predictor
    public ArrayList wordsPredictEng(String inputText, boolean starts_with_is_selected) {
        ArrayList list = new ArrayList();
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite::resource:vocabulary.sqlite");
            c.setAutoCommit(false);

            stmt = c.createStatement();

            if (starts_with_is_selected) {
                rs = stmt.executeQuery("select en_word from en_si where en_word LIKE '" + inputText.toLowerCase() + "%';");
            } else {
                rs = stmt.executeQuery("select en_word from en_si where en_word LIKE '%" + inputText.toLowerCase() + "%';");
            }
            
            while (rs.next()) {
                list.add(rs.getString("en_word"));
            }
            rs.close();
            stmt.close();
            c.close();

        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return list;
    }

    // sin words predictor
    public ArrayList wordsPredictSin(String inputText, boolean starts_with_is_selected) {
        ArrayList list = new ArrayList();
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite::resource:vocabulary.sqlite");
            c.setAutoCommit(false);

            stmt = c.createStatement();
                        
            if (starts_with_is_selected) {
                rs = stmt.executeQuery("select si_word from si_en where si_word LIKE '" + inputText + "%';");
            } else {
                rs = stmt.executeQuery("select si_word from si_en where si_word LIKE '%" + inputText + "%';");
            }

            while (rs.next()) {
                list.add(rs.getString("si_word"));
            }
            rs.close();
            stmt.close();
            c.close();

        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return list;
    }
    
    

}
