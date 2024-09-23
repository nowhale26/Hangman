package backend.academy.hangman;

import backend.academy.Dict;
import org.junit.jupiter.api.Test;
import  org.assertj.core.api.Assertions;

public class WordTest {

    @Test
    public void checkCorrectWord() {

        Dict dict = new Dict();
        var hiddenWord = dict.getWordOfSelectedDifficulty("Фрукты", 1);
        //Проверяем правильность выбора сложности слова
        Assertions.assertThat(hiddenWord.getDifficulty()).isEqualTo(1);

    }
}
