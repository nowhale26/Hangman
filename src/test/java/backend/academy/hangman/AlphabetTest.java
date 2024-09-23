package backend.academy.hangman;

import backend.academy.HangmanGame;
import backend.academy.Letter;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class AlphabetTest {
    @Test
    public void checkAlphabet(){
        HangmanGame game=new HangmanGame();
        //Создаем новый алфавит
        Letter[] alphabet = new Letter[]{
            new Letter('а',false),
            new Letter('г',false),
            new Letter('в',true)
        };
        String printedAlphabet= game.printAlphabet(alphabet);
        //Проверяем правильность напечатанного алфавита
        Assertions.assertThat(printedAlphabet).isEqualTo("а г ");
    }
}
