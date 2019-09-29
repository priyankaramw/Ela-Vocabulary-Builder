/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vocabulary;

import java.awt.Font;
import java.awt.Image;
import java.awt.List;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import org.jdesktop.xswingx.PromptSupport;

/**
 *
 * @author Sasith
 */
public class MCQPane extends javax.swing.JFrame {

    ArrayList<SavedWord> list;
    ArrayList<SavedWord> listForUpdate;
    static int currentIndex = 0;
    String dateString = null;
    int lengthOfList = 0;

    int randomAnswerPosition[];
    int userAnswer[];
    boolean answerSeen[];
    boolean shouldRemindAgain[];

    String allAnswers[][];

    // setting today
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
    Calendar cal = Calendar.getInstance();
    String today = dateFormat.format(cal.getTime()); //2014/08/06

    /**
     * Creates new form MCQPane
     */
    public MCQPane(String dateString) {
        initComponents();
        currentIndex = 0;
        this.dateString = dateString;
        loadWordObjectList(this.dateString);
        setFonts();
        initializeArrays();
        loadNextWordObject();
    }

    public MCQPane() {
        initComponents();
        currentIndex = 0;
        this.dateString = today;
        loadWordObjectList(this.dateString);
        setFonts();
        initializeArrays();
        loadNextWordObject();
        
        // To load the look and feel
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(MCQPane.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(MCQPane.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(MCQPane.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(MCQPane.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
    }

    // Making array sizes according to the size of list. used 10 as a checking value becase 0 can not be used as initial coz it is used for answers
    public void initializeArrays() {
        SQLiteVocabulary v = new SQLiteVocabulary();
        randomAnswerPosition = new int[lengthOfList];
        userAnswer = new int[lengthOfList];
        answerSeen = new boolean[lengthOfList];
        allAnswers = new String[lengthOfList][5];
        shouldRemindAgain = new boolean[lengthOfList];
        Random r = new Random();

        for (int i = 0; i < lengthOfList; i++) {
            int rand = r.nextInt(5);
            randomAnswerPosition[i] = rand;
            userAnswer[i] = 10;
            answerSeen[i] = false;
            shouldRemindAgain[i] = true;

            for (int j = 0; j < 5; j++) {
                allAnswers[i][j] = "null";
            }
        }

        // To populate all Answers
        for (int i = 0; i < lengthOfList; i++) {
            allAnswers[i][randomAnswerPosition[i]] = list.get(i).selectedMeaning;

            // To populate the other four random answers.
            String[] answers = v.returnRandomAnswers(list.get(i).language);
            for (int j = 0; j < 4; j++) {
                if (allAnswers[i][0].equals("null")) {
                    allAnswers[i][0] = answers[j];
                } else if (allAnswers[i][1].equals("null")) {
                    allAnswers[i][1] = answers[j];
                } else if (allAnswers[i][2].equals("null")) {
                    allAnswers[i][2] = answers[j];
                } else if (allAnswers[i][3].equals("null")) {
                    allAnswers[i][3] = answers[j];
                } else {
                    allAnswers[i][4] = answers[j];
                }
            }
        }

        // To capitalize if english
        if (list.get(currentIndex).language.equals("si")) {
            for (int i = 0; i < lengthOfList; i++) {
                for (int j = 0; j < 5; j++) {
                    allAnswers[i][j] = allAnswers[i][j].substring(0, 1).toUpperCase() + allAnswers[i][j].substring(1);
                }
            }
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        answers_btn_group = new javax.swing.ButtonGroup();
        dontRemindAgain = new javax.swing.JCheckBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        otherMeanings = new javax.swing.JTextArea();
        btnCheckAnswer = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        lblQuestionNo = new javax.swing.JLabel();
        btnPreviousQuestion = new javax.swing.JButton();
        btnNextQuestion = new javax.swing.JButton();
        btnSubmitAll = new javax.swing.JButton();
        lblWordOut = new javax.swing.JLabel();
        radioPanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        ans1 = new javax.swing.JRadioButton();
        correctLbl1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        ans2 = new javax.swing.JRadioButton();
        correctLbl2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        ans3 = new javax.swing.JRadioButton();
        correctLbl3 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        ans4 = new javax.swing.JRadioButton();
        correctLbl4 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        ans5 = new javax.swing.JRadioButton();
        correctLbl5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        submitResultOut = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Today MCQ Test");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        dontRemindAgain.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        dontRemindAgain.setText("Don't remind this word again.");
        dontRemindAgain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dontRemindAgainActionPerformed(evt);
            }
        });

        otherMeanings.setEditable(false);
        otherMeanings.setColumns(20);
        otherMeanings.setLineWrap(true);
        otherMeanings.setRows(2);
        otherMeanings.setWrapStyleWord(true);
        otherMeanings.setRequestFocusEnabled(false);
        jScrollPane1.setViewportView(otherMeanings);

        btnCheckAnswer.setText("Check Answer");
        btnCheckAnswer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCheckAnswerActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("What is the meaning of : ");

        lblQuestionNo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblQuestionNo.setText("Question 1 of 6");

        btnPreviousQuestion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vocabulary/L.png"))); // NOI18N
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("vocabulary/Bundle"); // NOI18N
        btnPreviousQuestion.setText(bundle.getString("Dictionary.btnPreviousDay.text")); // NOI18N
        btnPreviousQuestion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPreviousQuestion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPreviousQuestionActionPerformed(evt);
            }
        });

        btnNextQuestion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vocabulary/R.png"))); // NOI18N
        btnNextQuestion.setText(bundle.getString("Dictionary.btnNextDay.text")); // NOI18N
        btnNextQuestion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNextQuestion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextQuestionActionPerformed(evt);
            }
        });

        btnSubmitAll.setText("Submit All");
        btnSubmitAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubmitAllActionPerformed(evt);
            }
        });

        lblWordOut.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblWordOut.setForeground(new java.awt.Color(255, 0, 51));

        radioPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        radioPanel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                radioPanelKeyPressed(evt);
            }
        });

        answers_btn_group.add(ans1);
        ans1.setText("Answer 1");
        ans1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ans1MouseClicked(evt);
            }
        });

        correctLbl1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vocabulary/Nothing.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(correctLbl1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ans1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(correctLbl1, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ans1))
                .addContainerGap())
        );

        answers_btn_group.add(ans2);
        ans2.setText("Answer 2");
        ans2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ans2ActionPerformed(evt);
            }
        });

        correctLbl2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vocabulary/Nothing.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(correctLbl2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ans2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(correctLbl2, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ans2))
                .addContainerGap())
        );

        answers_btn_group.add(ans3);
        ans3.setText("Answer 3");
        ans3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ans3ActionPerformed(evt);
            }
        });

        correctLbl3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vocabulary/Nothing.png"))); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(correctLbl3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ans3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(correctLbl3, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ans3))
                .addContainerGap())
        );

        answers_btn_group.add(ans4);
        ans4.setText("Answer 4");
        ans4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ans4ActionPerformed(evt);
            }
        });

        correctLbl4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vocabulary/Nothing.png"))); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addComponent(correctLbl4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ans4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ans4)
                    .addComponent(correctLbl4, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        answers_btn_group.add(ans5);
        ans5.setText("Answer 5");
        ans5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ans5ActionPerformed(evt);
            }
        });

        correctLbl5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vocabulary/Nothing.png"))); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addComponent(correctLbl5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ans5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(correctLbl5, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ans5))
                .addContainerGap())
        );

        javax.swing.GroupLayout radioPanelLayout = new javax.swing.GroupLayout(radioPanel);
        radioPanel.setLayout(radioPanelLayout);
        radioPanelLayout.setHorizontalGroup(
            radioPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(radioPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(radioPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        radioPanelLayout.setVerticalGroup(
            radioPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(radioPanelLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("Other Meanings:");

        submitResultOut.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblQuestionNo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(submitResultOut, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblWordOut, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dontRemindAgain)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSubmitAll, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnPreviousQuestion, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnNextQuestion, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 123, Short.MAX_VALUE)
                        .addComponent(btnCheckAnswer, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(radioPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(42, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblQuestionNo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(submitResultOut, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblWordOut, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(radioPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnPreviousQuestion, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNextQuestion, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCheckAnswer, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnSubmitAll, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(dontRemindAgain)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnNextQuestionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextQuestionActionPerformed
        answers_btn_group.clearSelection();
        btnPreviousQuestion.setEnabled(true);
        currentIndex = currentIndex + 1;
        setCorrectIconsFree();
        loadNextWordObject();
        loadPreviousSelections();
        disableInputIfAnswerChecked();
        if (currentIndex == lengthOfList - 1) {
            btnNextQuestion.setEnabled(false);
        } else {
            btnNextQuestion.setEnabled(true);
        }

        if (shouldRemindAgain[currentIndex] == true) {
            dontRemindAgain.setSelected(false);
        } else {
            dontRemindAgain.setSelected(true);
        }
    }//GEN-LAST:event_btnNextQuestionActionPerformed

    public void loadPreviousSelections() {
        if (userAnswer[currentIndex] == 10) {
            btnCheckAnswer.setEnabled(false);
        } else {
            btnCheckAnswer.setEnabled(true);
            if (userAnswer[currentIndex] == 0) {
                ans1.setSelected(true);
            } else if (userAnswer[currentIndex] == 1) {
                ans2.setSelected(true);
            } else if (userAnswer[currentIndex] == 2) {
                ans3.setSelected(true);
            } else if (userAnswer[currentIndex] == 3) {
                ans4.setSelected(true);
            } else if (userAnswer[currentIndex] == 4) {
                ans5.setSelected(true);
            }
        }
    }

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
//        PromptSupport.setPrompt("Click \"Check Answer\" to see the answer.\n"
//                + "And click \"Submit All\" to evaluate all answers.", otherMeanings);
        //PromptSupport.setPrompt("Other meanings will appear here.", otherMeanings);
        btnPreviousQuestion.setEnabled(false);
        btnCheckAnswer.setEnabled(false);
        btnSubmitAll.setEnabled(false);
        
        // To set the Title icon
        Image i = null;
        try {
            i = ImageIO.read(getClass().getResource("/vocabulary/V_Icon.png"));
        } catch (IOException ex) {
            Logger.getLogger(Dictionary.class.getName()).log(Level.SEVERE, null, ex);
        }
        setIconImage(i);
        
        // For the keyboard buttons functionality
        radioPanel.requestFocus();
    }//GEN-LAST:event_formWindowOpened

    private void btnPreviousQuestionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPreviousQuestionActionPerformed
        answers_btn_group.clearSelection();
        btnNextQuestion.setEnabled(true);
        currentIndex = currentIndex - 1;
        setCorrectIconsFree();
        loadNextWordObject();
        loadPreviousSelections();
        disableInputIfAnswerChecked();
        if (currentIndex == 0) {
            btnPreviousQuestion.setEnabled(false);
        } else {
            btnPreviousQuestion.setEnabled(true);
        }

        if (shouldRemindAgain[currentIndex] == true) {
            dontRemindAgain.setSelected(false);
        } else {
            dontRemindAgain.setSelected(true);
        }
    }//GEN-LAST:event_btnPreviousQuestionActionPerformed

    private void ans1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ans1MouseClicked
        btnCheckAnswer.setEnabled(true);
        userAnswer[currentIndex] = 0;
        if (checkSubmitAllPossible()) {
            btnSubmitAll.setEnabled(true);
        }
    }//GEN-LAST:event_ans1MouseClicked

    private void ans2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ans2ActionPerformed
        btnCheckAnswer.setEnabled(true);
        userAnswer[currentIndex] = 1;
        if (checkSubmitAllPossible()) {
            btnSubmitAll.setEnabled(true);
        }
    }//GEN-LAST:event_ans2ActionPerformed

    private void ans3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ans3ActionPerformed
        btnCheckAnswer.setEnabled(true);
        userAnswer[currentIndex] = 2;
        if (checkSubmitAllPossible()) {
            btnSubmitAll.setEnabled(true);
        }
    }//GEN-LAST:event_ans3ActionPerformed

    private void ans4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ans4ActionPerformed
        btnCheckAnswer.setEnabled(true);
        userAnswer[currentIndex] = 3;
        if (checkSubmitAllPossible()) {
            btnSubmitAll.setEnabled(true);
        }
    }//GEN-LAST:event_ans4ActionPerformed

    private void ans5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ans5ActionPerformed
        btnCheckAnswer.setEnabled(true);
        userAnswer[currentIndex] = 4;
        if (checkSubmitAllPossible()) {
            btnSubmitAll.setEnabled(true);
        }
    }//GEN-LAST:event_ans5ActionPerformed

    private void btnCheckAnswerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCheckAnswerActionPerformed
        answerSeen[currentIndex] = true;
        otherMeanings.setText(list.get(currentIndex).otherMeanings);
        btnCheckAnswer.setEnabled(false);
        setCorrectIcons();
        ans1.setEnabled(false);
        ans2.setEnabled(false);
        ans3.setEnabled(false);
        ans4.setEnabled(false);
        ans5.setEnabled(false);

    }//GEN-LAST:event_btnCheckAnswerActionPerformed

    private void dontRemindAgainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dontRemindAgainActionPerformed
        if (dontRemindAgain.isSelected()) {
            shouldRemindAgain[currentIndex] = false;
        }
    }//GEN-LAST:event_dontRemindAgainActionPerformed

    private void btnSubmitAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubmitAllActionPerformed
        try {
            updateAfterMCQDone();
        } catch (ParseException ex) {
            Logger.getLogger(MCQPane.class.getName()).log(Level.SEVERE, null, ex);
        }
        btnSubmitAll.setEnabled(false);
    }//GEN-LAST:event_btnSubmitAllActionPerformed

    private void radioPanelKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_radioPanelKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
            btnNextQuestion.doClick();
        } else if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
            btnPreviousQuestion.doClick();
        }
    }//GEN-LAST:event_radioPanelKeyPressed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(MCQPane.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(MCQPane.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(MCQPane.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(MCQPane.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new MCQPane().setVisible(true);
//            }
//        });  
//    }
    public void setFonts() {
        Font font = new Font("Iskoola pota", Font.PLAIN, 24);
        Font font2 = new Font("Iskoola pota", Font.PLAIN, 16);
        otherMeanings.setFont(font2);
        lblWordOut.setFont(font);
        ans1.setFont(font2);
        ans2.setFont(font2);
        ans3.setFont(font2);
        ans4.setFont(font2);
        ans5.setFont(font2);
    }

    public boolean checkSubmitAllPossible() {
        boolean isPossible = true;
        for (int i = 0; i < lengthOfList; i++) {
            if (userAnswer[i] == 10) {
                isPossible = false;
                break;
            }
        }
        return isPossible;
    }

    public void loadWordObjectList(String dateString) {
        SQLiteVocabulary v = new SQLiteVocabulary();
        list = v.returnTodayWordsObjectList(dateString);
        lengthOfList = list.size();
    }

    public void loadNextWordObject() {
        try {
            lblQuestionNo.setText("Question " + (currentIndex + 1) + " of " + list.size());

            // To capitalize if english Word
            if (list.get(currentIndex).language.equals("en")) {
                lblWordOut.setText(" " + list.get(currentIndex).word.trim().substring(0, 1).toUpperCase() + list.get(currentIndex).word.trim().substring(1));
            } else {
                lblWordOut.setText(" " + list.get(currentIndex).word.trim());
            }

            ans1.setText("  " + allAnswers[currentIndex][0]);
            ans2.setText("  " + allAnswers[currentIndex][1]);
            ans3.setText("  " + allAnswers[currentIndex][2]);
            ans4.setText("  " + allAnswers[currentIndex][3]);
            ans5.setText("  " + allAnswers[currentIndex][4]);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No scheduled words for today.");
        }
    }

    public void disableInputIfAnswerChecked() {
        if (answerSeen[currentIndex] == true) {
            otherMeanings.setText(list.get(currentIndex).otherMeanings);
            ans1.setEnabled(false);
            ans2.setEnabled(false);
            ans3.setEnabled(false);
            ans4.setEnabled(false);
            ans5.setEnabled(false);

            btnCheckAnswer.setEnabled(false);

            setCorrectIcons();

        } else {
            otherMeanings.setText("");

            ans1.setEnabled(true);
            ans2.setEnabled(true);
            ans3.setEnabled(true);
            ans4.setEnabled(true);
            ans5.setEnabled(true);
            btnCheckAnswer.setEnabled(true);
        }
    }

    public void setCorrectIconsFree() {
        correctLbl1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vocabulary/Nothing.png")));
        correctLbl2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vocabulary/Nothing.png")));
        correctLbl3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vocabulary/Nothing.png")));
        correctLbl4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vocabulary/Nothing.png")));
        correctLbl5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vocabulary/Nothing.png")));
    }

    public void setCorrectIcons() {
        if (randomAnswerPosition[currentIndex] == userAnswer[currentIndex]) {
            switch (randomAnswerPosition[currentIndex]) {
                case 0:
                    correctLbl1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vocabulary/Correct.png")));
                    break;
                case 1:
                    correctLbl2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vocabulary/Correct.png")));
                    break;
                case 2:
                    correctLbl3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vocabulary/Correct.png")));
                    break;
                case 3:
                    correctLbl4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vocabulary/Correct.png")));
                    break;
                case 4:
                    correctLbl5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vocabulary/Correct.png")));
                    break;
            }
        } else {
            switch (randomAnswerPosition[currentIndex]) {
                case 0:
                    correctLbl1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vocabulary/Correct.png")));
                    break;
                case 1:
                    correctLbl2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vocabulary/Correct.png")));
                    break;
                case 2:
                    correctLbl3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vocabulary/Correct.png")));
                    break;
                case 3:
                    correctLbl4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vocabulary/Correct.png")));
                    break;
                case 4:
                    correctLbl5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vocabulary/Correct.png")));
                    break;
            }
            switch (userAnswer[currentIndex]) {
                case 0:
                    correctLbl1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vocabulary/Wrong.png")));
                    break;
                case 1:
                    correctLbl2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vocabulary/Wrong.png")));
                    break;
                case 2:
                    correctLbl3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vocabulary/Wrong.png")));
                    break;
                case 3:
                    correctLbl4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vocabulary/Wrong.png")));
                    break;
                case 4:
                    correctLbl5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vocabulary/Wrong.png")));
                    break;
            }
        }
    }

    public void checkAnsweredOrChecked() {
        if (userAnswer[currentIndex] != 10) {
            if (userAnswer[currentIndex] == 0) {
                ans1.setSelected(true);
            } else if (userAnswer[currentIndex] == 1) {
                ans2.setSelected(true);
            } else if (userAnswer[currentIndex] == 2) {
                ans3.setSelected(true);
            } else if (userAnswer[currentIndex] == 0) {
                ans4.setSelected(true);
            } else if (userAnswer[currentIndex] == 0) {
                ans5.setSelected(true);
            }
        }
    }

    public void updateAfterMCQDone() throws ParseException {
        SQLiteVocabulary v = new SQLiteVocabulary();
        listForUpdate = list;

        // setting two weeks more
        String twoWeeks = "";  // Start date
        String twoMonths = "";
        String sixMonths = "";
        
        try {
            for (int i = 0; i < lengthOfList; i++) {

                // Update revision done
                if (listForUpdate.get(i).revisionDone == 0) {
                    listForUpdate.get(i).revisionDone = 1;
                } else if (listForUpdate.get(i).revisionDone == 1) {
                    listForUpdate.get(i).revisionDone = 2;
                } else if (listForUpdate.get(i).revisionDone == 2) {
                    listForUpdate.get(i).revisionDone = 3;
                } else {
                    listForUpdate.get(i).revisionDone = 3;
                }

                // Update don't remember again
                if (shouldRemindAgain[i] == true) {
                    listForUpdate.get(i).remember = "Yes";
                } else {
                    listForUpdate.get(i).remember = "No";
                }

                // Update last Result in the following format  1,1,1 means all 3 times were correct 0,0,0 means all 3 times it was wrong
                if (listForUpdate.get(i).revisionDone == 1) {
                    if (randomAnswerPosition[i] == userAnswer[i]) {
                        listForUpdate.get(i).lastResult = "1";
                    } else {
                        listForUpdate.get(i).lastResult = "0";
                    }
                } else if (listForUpdate.get(i).revisionDone == 2) {
                    if (randomAnswerPosition[i] == userAnswer[i]) {
                        listForUpdate.get(i).lastResult = list.get(i).lastResult + ",1";
                    } else {
                        listForUpdate.get(i).lastResult = list.get(i).lastResult + ",0";
                    }
                } else if (listForUpdate.get(i).revisionDone == 3) {
                    if (randomAnswerPosition[i] == userAnswer[i]) {
                        listForUpdate.get(i).lastResult = list.get(i).lastResult + ",1";
                    } else {
                        listForUpdate.get(i).lastResult = list.get(i).lastResult + ",0";
                    }
                }

                // Update next shecule 
                if (listForUpdate.get(i).revisionDone == 1) {
                    cal.setTime(dateFormat.parse(today));   //dateString
                    cal.add(Calendar.DATE, 14);  // number of days to add
                    twoWeeks = dateFormat.format(cal.getTime());  // dt is now the new date
                    listForUpdate.get(i).nextSchedule = twoWeeks;
                } else if (listForUpdate.get(i).revisionDone == 2) {
                    cal.add(Calendar.MONTH, 2);
                    twoMonths = dateFormat.format(cal.getTime());
                    listForUpdate.get(i).nextSchedule = twoMonths;
                } else if (listForUpdate.get(i).revisionDone == 3) {
                    cal.add(Calendar.MONTH, 6);
                    sixMonths = dateFormat.format(cal.getTime());
                    listForUpdate.get(i).nextSchedule = sixMonths;
                } else {
                    listForUpdate.get(i).nextSchedule = "Done";
                }

                // Update last reminded date
                listForUpdate.get(i).lastRemindedDate = today;
            }
            
            // Calling excecute query
            v.updateFromMCQ(listForUpdate);
                
        } catch (Exception e) {
            System.out.println("Exception in updating");
        }

//            listForUpdate.get(i).id = list.get(i).id;
//            listForUpdate.get(i).referenceId = list.get(i).referenceId;
//            listForUpdate.get(i).word = list.get(i).word;
//            listForUpdate.get(i).language = list.get(i).language;
//            listForUpdate.get(i).selectedMeaning = list.get(i).selectedMeaning;
//            listForUpdate.get(i).otherMeanings = list.get(i).otherMeanings;
//            listForUpdate.get(i).date  = list.get(i).date;
//            listForUpdate.get(i).remember = list.get(i).remember;
//            listForUpdate.get(i).revisionDone = list.get(i).revisionDone;
//            listForUpdate.get(i).lastResult = list.get(i).lastResult;
//            listForUpdate.get(i).nextSchedule = list.get(i).nextSchedule;
//            listForUpdate.get(i).lastRemindedDate = list.get(i).lastRemindedDate;
//            listForUpdate.get(i).progress = list.get(i).progress;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton ans1;
    private javax.swing.JRadioButton ans2;
    private javax.swing.JRadioButton ans3;
    private javax.swing.JRadioButton ans4;
    private javax.swing.JRadioButton ans5;
    private javax.swing.ButtonGroup answers_btn_group;
    private javax.swing.JButton btnCheckAnswer;
    private javax.swing.JButton btnNextQuestion;
    private javax.swing.JButton btnPreviousQuestion;
    private javax.swing.JButton btnSubmitAll;
    private javax.swing.JLabel correctLbl1;
    private javax.swing.JLabel correctLbl2;
    private javax.swing.JLabel correctLbl3;
    private javax.swing.JLabel correctLbl4;
    private javax.swing.JLabel correctLbl5;
    private javax.swing.JCheckBox dontRemindAgain;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblQuestionNo;
    private javax.swing.JLabel lblWordOut;
    private javax.swing.JTextArea otherMeanings;
    private javax.swing.JPanel radioPanel;
    private javax.swing.JLabel submitResultOut;
    // End of variables declaration//GEN-END:variables
}
