package backend.academy;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Letter {
    private char letter;
    private boolean isUsed;

    Letter(){

    }

    Letter(char letter, boolean isUsed){
        this.letter=letter;
        this.isUsed=isUsed;
    }
}
