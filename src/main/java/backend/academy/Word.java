package backend.academy;

import lombok.Getter;

@Getter
public class Word {
     private final String word;
     private final String hint;
     private final int difficulty;

    public Word(String word, String hint, int difficulty) {
        this.word = word;
        this.difficulty = difficulty;
        this.hint = hint;
    }
}
