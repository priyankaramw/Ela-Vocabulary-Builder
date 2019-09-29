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
public class SavedWord {
    int id;
    int referenceId;
    String word;
    String language;
    String selectedMeaning;
    String otherMeanings;
    String date;
    String remember;
    int revisionDone;
    String lastResult;
    String nextSchedule;
    String lastRemindedDate;
    int progress;
    
    public SavedWord (int id, int referenceId, String word, String language, String selectedMeaning, String otherMeanings, String date,
            String remember, int revisionDone, String lastResult, String nextSchedule, String lastRemindedDate, int progress) {
        this.id = id;
        this.referenceId = referenceId;
        this.word = word;
        this.language = language;
        this.selectedMeaning = selectedMeaning;
        this.otherMeanings = otherMeanings;
        this.date = date;
        this.remember = remember;
        this.revisionDone = revisionDone;
        this.lastResult = lastResult;
        this.nextSchedule = nextSchedule;
        this.lastRemindedDate = lastRemindedDate;
        this.progress = progress;
    }
    
    public SavedWord (){
    }
}
