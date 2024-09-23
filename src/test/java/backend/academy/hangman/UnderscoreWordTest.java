package backend.academy.hangman;

import backend.academy.HangmanGame;
import backend.academy.Word;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class UnderscoreWordTest {
    @Test
    public void checkUnderscoredWord(){
        HangmanGame game = new HangmanGame();
        Word word = new Word("Яблоко","1",1);
        game.setHiddenWord(word);
        String underscoredWord=game.underscoreHiddenWord();
        //Проверяем, что слово будет состоять из 6 прочерков
        Assertions.assertThat(underscoredWord).isEqualTo("______");
        Word word2 = new Word("Коста рика","1",1);
        game.setHiddenWord(word2);
        String underscored2=game.underscoreHiddenWord();
        //Проверяем, что пробел не поменяется на прочерк
        Assertions.assertThat(underscored2).isEqualTo("_____ ____");
    }
}
