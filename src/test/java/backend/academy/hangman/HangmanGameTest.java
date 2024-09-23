package backend.academy.hangman;

import backend.academy.HangmanGame;
import backend.academy.HangmanStageVisualization;
import backend.academy.Word;
import org.junit.jupiter.api.Test;
import org.assertj.core.api.Assertions;

import java.io.*;

public class HangmanGameTest {

    @Test
    public void checkUpdateCurrentHiddenWordCorrectGuess() {
        HangmanGame game = new HangmanGame();
        game.setHiddenWord(new Word("арена", "Место проведения спортивных мероприятий",1));
        game.setCurrentHiddenWord("_____");
        game.setLettersToGuess(5); // В "арена" 5 букв

        Assertions.assertThat(game.getCurrentHiddenWord()).isEqualTo("_____");
        Assertions.assertThat(game.getLettersToGuess()).isEqualTo(5);
        game.updateCurrentHiddenWord('а');

        // Проверяем, что скрытое слово обновилось корректно
        Assertions.assertThat(game.getCurrentHiddenWord()).isEqualTo("а___а");

        // Проверяем, что количество оставшихся букв для угадывания уменьшилось на 2
        Assertions.assertThat(game.getLettersToGuess()).isEqualTo(3);
    }

    @Test
    public void checkUpdateCurrentHiddenWordIncorrectGuess() {
        HangmanGame game = new HangmanGame();
        game.setHiddenWord(new Word("арена", "Место проведения спортивных мероприятий",1));
        game.setCurrentHiddenWord("_____");
        game.setLettersToGuess(5);
        game.updateCurrentHiddenWord('б');

        // Проверяем, что скрытое слово не изменилось
        Assertions.assertThat(game.getCurrentHiddenWord()).isEqualTo("_____");

        // Проверяем, что количество оставшихся букв для угадывания не изменилось
        Assertions.assertThat(game.getLettersToGuess()).isEqualTo(5);
    }

    @Test
    public void checkHangmanStageVisualizationOnMistake() throws IOException {
        // Имитируем ввод неправильных букв
        String simulatedInput = "б\nв\nг\nд\nж\nз\n";
        InputStream input = new ByteArrayInputStream(simulatedInput.getBytes());
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        HangmanGame game = new HangmanGame();
        game.setHiddenWord(new Word("арена", "Место проведения спортивных мероприятий",1));
        game.startGuessing(input, output);
        String result = output.toString();

        // Проверяем, что стадии виселицы корректно отображаются на каждом этапе
        Assertions.assertThat(result).contains(HangmanStageVisualization.STAGES[0])
            .contains(HangmanStageVisualization.STAGES[1])
            .contains(HangmanStageVisualization.STAGES[2])
            .contains(HangmanStageVisualization.STAGES[3])
            .contains(HangmanStageVisualization.STAGES[4])
            .contains(HangmanStageVisualization.STAGES[5]);

        // Проверяем, что финальная стадия виселицы отображается после 6 неверных попыток
        Assertions.assertThat(result).contains(HangmanStageVisualization.STAGES[6]);
    }

    @Test
    public void checkGameOverOnWin() throws IOException {
        // Имитируем правильный ввод, который приведет к угадыванию
        String simulatedInput = "а\nр\nе\nн\n";
        InputStream input = new ByteArrayInputStream(simulatedInput.getBytes());
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        HangmanGame game = new HangmanGame();
        game.setHiddenWord(new Word("арена", "Место проведения спортивных мероприятий",1));
        game.startGuessing(input, output);
        String result = output.toString();

        // Проверяем, что игра завершается победой и выводится правильное сообщение
        Assertions.assertThat(result).contains("Поздравляем, вы полностью отгадали слово!");
    }

    @Test
    public void checkGameOverOnLoss() throws IOException {
        // Имитируем неправильный ввод, который приведет к поражению
        String simulatedInput = "б\nв\nг\nд\nж\nз\n";
        InputStream input = new ByteArrayInputStream(simulatedInput.getBytes());
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        HangmanGame game = new HangmanGame();
        game.setHiddenWord(new Word("арена", "Место проведения спортивных мероприятий",1));
        game.startGuessing(input, output);
        String result = output.toString();

        // Проверяем, что игра завершается поражением и выводится правильное сообщение
        Assertions.assertThat(result).contains("К сожалению, вы не отгадали слово");
    }
}

