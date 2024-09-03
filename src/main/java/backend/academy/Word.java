package backend.academy;

import lombok.Getter;

public class Word {
    @Getter private String word;
    @Getter private String hint;
    @Getter private int difficulty;

    public Word(String word, String hint, int difficulty){
        this.word=word;
        this.difficulty = difficulty;
        this.hint = hint;
    }

}
