package backend.academy.hangman;

import  org.assertj.core.api.Assertions;
import backend.academy.HangmanGame;
import org.junit.jupiter.api.Test;

public class StartParamsTest {
    @Test
    public void checkCategory(){
        HangmanGame hangman = new HangmanGame();
        String category=hangman.chooseCategory("Фрукты");
        Assertions.assertThat(category).isEqualTo("Фрукты");
        category=hangman.chooseCategory("akfjdssdkl");
        Assertions.assertThat(category).isEqualTo(" ");
        category=hangman.chooseCategory("Страны");
        Assertions.assertThat(category).isEqualTo("Страны");
        category=hangman.chooseCategory("Еда");
        Assertions.assertThat(category).isEqualTo("Еда");
    }
    @Test
    public void checkDifficulty(){
        HangmanGame hangman= new HangmanGame();
        int difficulty= hangman.chooseDifficulty("ЛеГкая");
        Assertions.assertThat(difficulty).isEqualTo(1);
        difficulty= hangman.chooseDifficulty("СЛОЖная");
        Assertions.assertThat(difficulty).isEqualTo(3);
        difficulty= hangman.chooseDifficulty("ароарылораыв");
        Assertions.assertThat(difficulty).isEqualTo(0);
    }

}
