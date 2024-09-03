package backend.academy;

import lombok.Getter;
@Getter
public class Word {
     private String word;
     private String hint;
     private int difficulty;

    public Word(String word, String hint, int difficulty){
        this.word=word;
        this.difficulty = difficulty;
        this.hint = hint;
    }

}
