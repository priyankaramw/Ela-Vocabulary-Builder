/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vocabulary;

/**
 *
 * @author Sasith
 */
import java.awt.List;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;

public class SQLiteVocabulary {

    Connection c = null;
    Statement stmt = null;
    ResultSet rs = null;

    // setting today
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
    Calendar cal = Calendar.getInstance();
    String today = dateFormat.format(cal.getTime()); //2014/08/06

    // Keys for searched_words table
    private static final String TABLE_SEARCHED_WORDS = "searched_words";
    private static final String ID_KEY = "id";
    private static final String REFERENCE_KEY = "reference_id";
    private static final String WORD_KEY = "word";
    private static final String LANG_KEY = "language";
    private static final String SELECTED_MEANING_KEY = "selected_meaning";
    private static final String OTHER_M_KEY = "other_meanings";
    private static final String DATE_KEY = "date";
    private static final String REMEMBER_KEY = "remember";
    private static final String REVISION_DONE_KEY = "revision_done";
    private static final String LAST_RESULT_KEY = "last_result";
    private static final String NEXT_SCHE_KEY = "next_schedule";
    private static final String LAST_REMINDED_DAY_KEY = "last_reminded_day";
    private static final String PROGRESS_KEY = "progress";

    //keys for en_si table
    private static final String TABLE_EN_SI = "en_si";
    private static final String EN_WORD_KEY = "en_word";
    private static final String SI_MEANING_KEY = "si_meaning";

    //keys for si_en table
    private static final String TABLE_SI_EN = "si_en";
    private static final String SI_WORD_KEY = "si_word";
    private static final String EN_MEANING_KEY = "en_meaning";

    private static final String CREATE_TABLE_SEARCHED_WORDS = "CREATE TABLE IF NOT EXISTS " + TABLE_SEARCHED_WORDS + " ("
            + ID_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + REFERENCE_KEY + " INTEGER,"
            + WORD_KEY + " TEXT,"
            + LANG_KEY + " TEXT,"
            + SELECTED_MEANING_KEY + " TEXT,"
            + OTHER_M_KEY + " TEXT,"
            + DATE_KEY + " TEXT,"
            + REMEMBER_KEY + " TEXT,"
            + REVISION_DONE_KEY + " INTEGER,"
            + LAST_RESULT_KEY + " TEXT,"
            + NEXT_SCHE_KEY + " TEXT,"
            + LAST_REMINDED_DAY_KEY + " TEXT,"
            + PROGRESS_KEY + " INTEGER);";

    private static final String CREATE_TABLE_EN_SI = "CREATE TABLE IF NOT EXISTS " + TABLE_EN_SI + " ("
            + ID_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + EN_WORD_KEY + " TEXT,"
            + SI_MEANING_KEY + " TEXT);";

    private static final String CREATE_TABLE_SI_EN = "CREATE TABLE IF NOT EXISTS " + TABLE_SI_EN + " ("
            + ID_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + SI_WORD_KEY + " TEXT,"
            + EN_MEANING_KEY + " TEXT);";

    public void ConnectSQLite() {

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:vocabulary.sqlite"); //this is to read from outside project location

            //creating tables
            stmt = c.createStatement();
            stmt.executeUpdate(CREATE_TABLE_SEARCHED_WORDS);
            stmt.close();
            c.close();

        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    public void insertWord(String word, String selectedMeaning, String otherMeanings, String language) {
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:vocabulary.sqlite");
            c.setAutoCommit(true);
            stmt = c.createStatement();

            // setting two days more
            String twoDays = "";  // Start date
            cal.setTime(dateFormat.parse(today));
            cal.add(Calendar.DATE, 2);  // number of days to add
            twoDays = dateFormat.format(cal.getTime());  // dt is now the new date

            String insertRowSQL = "INSERT INTO " + TABLE_SEARCHED_WORDS
                    + "(reference_id, word, language, selected_meaning, other_meanings, date, remember, revision_done, last_result, "
                    + "next_schedule, last_reminded_day, progress) VALUES"
                    + "(?,?,?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement preparedStatement = c.prepareStatement(insertRowSQL);
            preparedStatement.setInt(1, 0);
            preparedStatement.setString(2, word);
            preparedStatement.setString(3, language);
            preparedStatement.setString(4, selectedMeaning);
            preparedStatement.setString(5, otherMeanings);
            preparedStatement.setString(6, this.today);
            preparedStatement.setString(7, "Yes");
            preparedStatement.setInt(8, 0);
            preparedStatement.setString(9, "n/a");
            preparedStatement.setString(10, twoDays);
            preparedStatement.setString(11, this.today);
            preparedStatement.setInt(12, 0);

// execute insert SQL stetement
            preparedStatement.executeUpdate();
            System.out.println(preparedStatement);

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error occured");
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        } catch (ParseException ex) {
            Logger.getLogger(SQLiteVocabulary.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void readData() {
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:vocabulary.sqlite");
            c.setAutoCommit(false);

            stmt = c.createStatement();
            rs = stmt.executeQuery("SELECT * FROM " + TABLE_SEARCHED_WORDS + ";");
            while (rs.next()) {
                int id = rs.getInt("id");
                int reference_id = rs.getInt(REFERENCE_KEY);
                String word = rs.getString(WORD_KEY);
                String selectedLang = rs.getString(LANG_KEY);
                String selected_m = rs.getString(SELECTED_MEANING_KEY);
                String other_m = rs.getString(OTHER_M_KEY);
                String date = rs.getString(DATE_KEY);
                String remember = rs.getString(REMEMBER_KEY);
                int revision_done = rs.getInt(REVISION_DONE_KEY);
                String last_result = rs.getString(LAST_RESULT_KEY);
                String next_sche = rs.getString(NEXT_SCHE_KEY);
                String last_reminder = rs.getString(LAST_REMINDED_DAY_KEY);
                int progress = rs.getInt(PROGRESS_KEY);

                System.out.println("id = " + id);
                System.out.println("reference = " + reference_id);
                System.out.println("word = " + word);
                System.out.println("lang = " + selectedLang);
                System.out.println("selected_m = " + selected_m);
                System.out.println("other_m = " + other_m);
                System.out.println("date = " + date);
                System.out.println("remember = " + remember);
                System.out.println("revision_done = " + revision_done);
                System.out.println("last result = " + last_result);
                System.out.println("next_sche = " + next_sche);
                System.out.println("last reminder = " + last_reminder);
                System.out.println("progress = " + progress);
                System.out.println();
            }
            rs.close();
            stmt.close();
            c.close();

        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    public DefaultListModel getTodayVocabulary(String dateString, boolean isAllWords) {
        DefaultListModel lm = new DefaultListModel();
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:vocabulary.sqlite");
            c.setAutoCommit(false);

            stmt = c.createStatement();
            // this.today should be replaced to below
//            rs = stmt.executeQuery("select * from searched_words where next_schedule = '2016/06/24';");
            if (isAllWords) {
                rs = stmt.executeQuery("select * from searched_words;");
            } else {
                rs = stmt.executeQuery("select * from searched_words where next_schedule <= '" + dateString + "'"
                        + "AND " + REVISION_DONE_KEY + " <= 3;");
            }

            while (rs.next()) {
                lm.addElement(rs.getString("word"));
            }

            rs.close();
            stmt.close();
            c.close();

        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return lm;
    }

    public ResultSet getFullRecord(String word) {
        ResultSet rs = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:vocabulary.sqlite");
            c.setAutoCommit(false);

            stmt = c.createStatement();
            rs = stmt.executeQuery("select * from searched_words where word = '" + word + "';");

//        while (rs.next()) {
//            System.out.println(rs.getString("selected_meaning"));
//            System.out.println(rs.getString("other_meanings"));
//        }
//            rs.close();
//            stmt.close();
//            c.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return rs;
    }

    public ArrayList returnTodayWordsObjectList() {
        ArrayList<SavedWord> list = new ArrayList<>();

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:vocabulary.sqlite");
            c.setAutoCommit(false);

            stmt = c.createStatement();
            //rs = stmt.executeQuery("SELECT * FROM " + TABLE_SEARCHED_WORDS + ";");
            rs = stmt.executeQuery("select * from " + TABLE_SEARCHED_WORDS + " where " + NEXT_SCHE_KEY + " = '" + this.today + "'"
                    + "AND " + REVISION_DONE_KEY + " != 3;");

            while (rs.next()) {
                SavedWord sw = new SavedWord();

                sw.id = rs.getInt("id");
                sw.referenceId = rs.getInt(REFERENCE_KEY);
                sw.word = rs.getString(WORD_KEY);
                sw.language = rs.getString(LANG_KEY);
                sw.selectedMeaning = rs.getString(SELECTED_MEANING_KEY);
                sw.otherMeanings = rs.getString(OTHER_M_KEY);
                sw.date = rs.getString(DATE_KEY);
                sw.remember = rs.getString(REMEMBER_KEY);
                sw.revisionDone = rs.getInt(REVISION_DONE_KEY);
                sw.lastResult = rs.getString(LAST_RESULT_KEY);
                sw.nextSchedule = rs.getString(NEXT_SCHE_KEY);
                sw.lastRemindedDate = rs.getString(LAST_REMINDED_DAY_KEY);
                sw.progress = rs.getInt(PROGRESS_KEY);

                list.add(sw);
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

    public ArrayList returnTodayWordsObjectList(String dateString) {
        ArrayList<SavedWord> list = new ArrayList<>();

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:vocabulary.sqlite");
            c.setAutoCommit(false);

            stmt = c.createStatement();
            //rs = stmt.executeQuery("SELECT * FROM " + TABLE_SEARCHED_WORDS + ";");
            //rs = stmt.executeQuery("select * from "+TABLE_SEARCHED_WORDS+" where "+NEXT_SCHE_KEY+" <= '"+dateString+"';");
            rs = stmt.executeQuery("select * from " + TABLE_SEARCHED_WORDS + " where " + NEXT_SCHE_KEY + " <= '" + dateString + "'"
                    + "AND " + REVISION_DONE_KEY + " != 3;");

            while (rs.next()) {
                SavedWord sw = new SavedWord();

                sw.id = rs.getInt("id");
                sw.referenceId = rs.getInt(REFERENCE_KEY);
                sw.word = rs.getString(WORD_KEY);
                sw.language = rs.getString(LANG_KEY);
                sw.selectedMeaning = rs.getString(SELECTED_MEANING_KEY);
                sw.otherMeanings = rs.getString(OTHER_M_KEY);
                sw.date = rs.getString(DATE_KEY);
                sw.remember = rs.getString(REMEMBER_KEY);
                sw.revisionDone = rs.getInt(REVISION_DONE_KEY);
                sw.lastResult = rs.getString(LAST_RESULT_KEY);
                sw.nextSchedule = rs.getString(NEXT_SCHE_KEY);
                sw.lastRemindedDate = rs.getString(LAST_REMINDED_DAY_KEY);
                sw.progress = rs.getInt(PROGRESS_KEY);

                list.add(sw);
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

    public void updateFromMCQ(ArrayList<SavedWord> list) {
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:vocabulary.sqlite");
            c.setAutoCommit(true);
            //stmt = c.createStatement();

            for (int i = 0; i < list.size(); i++) {

                SavedWord sw = list.get(i);

                String updateTableSQL = "UPDATE " + TABLE_SEARCHED_WORDS
                        + " SET reference_id = ?,"
                        + "word = ?,"
                        + "language = ?,"
                        + "selected_meaning = ?,"
                        + "other_meanings = ?,"
                        + "date = ?,"
                        + "remember = ?,"
                        + "revision_done = ?,"
                        + "last_result = ?,"
                        + "next_schedule = ?,"
                        + "last_reminded_day = ?,"
                        + "progress = ?"
                        + " WHERE id = ?";

                PreparedStatement preparedStatement = c.prepareStatement(updateTableSQL);

                preparedStatement.setInt(1, sw.referenceId);
                preparedStatement.setString(2, sw.word);
                preparedStatement.setString(3, sw.language);
                preparedStatement.setString(4, sw.selectedMeaning);
                preparedStatement.setString(5, sw.otherMeanings);
                preparedStatement.setString(6, sw.date);
                preparedStatement.setString(7, sw.remember);
                preparedStatement.setInt(8, sw.revisionDone);
                preparedStatement.setString(9, sw.lastResult);
                preparedStatement.setString(10, sw.nextSchedule);
                preparedStatement.setString(11, sw.lastRemindedDate);
                preparedStatement.setInt(12, sw.progress);
                preparedStatement.setInt(13, sw.id);

                preparedStatement.executeUpdate();

//                System.out.println(sw.id);
//                System.out.println(sw.referenceId);
//                System.out.println(sw.word);
//                System.out.println(sw.language);
//                System.out.println(sw.selectedMeaning);
//                System.out.println(sw.otherMeanings);
//                System.out.println(sw.date);
//                System.out.println(sw.remember);
//                System.out.println(sw.revisionDone);
//                System.out.println(sw.lastResult);
//                System.out.println(sw.nextSchedule);
//                System.out.println(sw.lastRemindedDate);
//                System.out.println(sw.progress);
            }

            c.close();
            //stmt.close(); 
            System.out.println("Update done");

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SQLiteVocabulary.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SQLiteVocabulary.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //Return four random words for mcq
    public String[] returnRandomAnswers(String lang) {
        String RandomAnswers[] = {"", "", "", ""};
        Random r = new Random();
        int rand;
        int randForSplited;
        int okCount = 0;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite::resource:vocabulary.sqlite");
            c.setAutoCommit(false);
            stmt = c.createStatement();

            while (okCount < 4) {
                if (lang.equals("en")) {
                    rand = r.nextInt(36458) + 1;
                    rs = stmt.executeQuery("select si_meaning from en_si where rowid = '" + rand + "';");
                    try {
                        String[] splitted = rs.getString("si_meaning").split("/");
                        randForSplited = r.nextInt(splitted.length);
                        RandomAnswers[okCount] = splitted[randForSplited].trim();
                        okCount = okCount + 1;
                    } catch (Exception e) {
                        System.out.println("Error while splitting");
                    }
                } else {
                    rand = r.nextInt(65942) + 1;
                    rs = stmt.executeQuery("select en_meaning from si_en where rowid = '" + rand + "';");
                    try {
                        String[] splitted = rs.getString("en_meaning").split("/");
                        randForSplited = r.nextInt(splitted.length);
                        RandomAnswers[okCount] = splitted[randForSplited].trim();
                        okCount = okCount + 1;
                    } catch (Exception e) {
                        System.out.println("Error while splitting");
                    }
                }
            }
            rs.close();
            stmt.close();
            c.close();

        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return RandomAnswers;
    }

    public int[] returnStats() {
        int rowCountAll = 0;
        int rowCountToday = 0;
        int rememberCount = 0;
        int rememberOutOf = 0;

        int correctCount = 0;
        int wrongCount = 0;
        int revisionsDone = 0;

        int allOutputs[] = new int[]{0, 0, 0, 0, 0, 0, 0};
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:vocabulary.sqlite");
            c.setAutoCommit(false);

            stmt = c.createStatement();
            // 0 - all words
            rs = stmt.executeQuery("select count(*) from " + TABLE_SEARCHED_WORDS + ";");
            // get the number of rows from the result set
            rs.next();
            rowCountAll = rs.getInt(1);

            // 1 - today words count
            rs = stmt.executeQuery("select count(*) from " + TABLE_SEARCHED_WORDS + " where " + NEXT_SCHE_KEY + " <= '" + today + "'"
                    + "AND " + REVISION_DONE_KEY + " != 3;");
            // get the number of rows from the result set
            rs.next();
            rowCountToday = rs.getInt(1);

            // 2 - you remember
            int n_aCount = 0; // to minus the n/a (not reminded sitll set)

            rs = stmt.executeQuery("select " + LAST_RESULT_KEY + " from " + TABLE_SEARCHED_WORDS + ";");
            while (rs.next()) {
                String temp = rs.getString(LAST_RESULT_KEY);
                String splitted[] = temp.split(",");

                // 3 - remember out of - To calculate the correct ratio
                for (int i = 0; i < splitted.length; i++) {
                    if (splitted[i].trim().equals("0")) {
                        wrongCount = wrongCount + 1;
                    } else if (splitted[i].trim().equals("1")) {
                        correctCount = correctCount + 1;
                    }
                }

                // To get the last result to get remember list
                if (splitted[splitted.length - 1].trim().equals("n/a")) {
                    n_aCount = n_aCount + 1;
                } else if (splitted[splitted.length - 1].trim().equals("1")) {
                    rememberCount = rememberCount + 1;
                }
            }
            
            // 
            rs = stmt.executeQuery("select " + REVISION_DONE_KEY + " from " + TABLE_SEARCHED_WORDS + ";");
            while (rs.next()) {
                String temp = rs.getString(REVISION_DONE_KEY);
                int tempInt = Integer.parseInt(temp);
                revisionsDone = revisionsDone + tempInt;
            }

            rememberOutOf = rowCountAll - n_aCount;

            allOutputs[0] = rowCountAll;
            allOutputs[1] = rowCountToday;
            allOutputs[2] = rememberCount;
            allOutputs[3] = rememberOutOf;
            allOutputs[4] = correctCount;
            allOutputs[5] = wrongCount;
            allOutputs[6] = revisionsDone;

            rs.close();
            stmt.close();
            c.close();

        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return allOutputs;
    }

}
