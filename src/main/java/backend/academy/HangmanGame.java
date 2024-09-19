package backend.academy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Random;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HangmanGame {
    private static final int ALLOWED_MISTAKES = 6;
    private int difficulty;
    private String category;
    private Word hiddenWord;
    private String currentHiddenWord = "";
    private int lettersToGuess;

    public void startGame() throws IOException {
        getStartParams(System.in, System.out);
        setHiddenWord();
        startGuessing(System.in, System.out);
    }

    public void getStartParams(InputStream input, OutputStream output) throws IOException {
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(output), true);
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));

        // Выбор категории
        writer.println("Выберите одну из категорий слов: Фрукты, Животные, Страны, Еда");
        String categoryInput = reader.readLine();
        // Генерация случайной категории
        if (categoryInput == null || categoryInput.isBlank()) {
            Random random = new Random();
            int randomCategory = random.nextInt(1, 5);
            switch (randomCategory) {
                case 1:
                    category = "Фрукты";
                    break;
                case 2:
                    category = "Животные";
                    break;
                case 3:
                    category = "Страны";
                    break;
                case 4:
                    category = "Еда";
                    break;
            }
        } else {
            category = categoryInput;
        }

        // Выбор сложности
        writer.println("Выберите одну из сложностей: Легкая, Средняя, Сложная");
        String difficultyInput = reader.readLine();
        // Генерация случайной сложности от 1 до 3
        if (difficultyInput == null || difficultyInput.isBlank()) {
            Random random = new Random();
            difficulty = random.nextInt(1, 4);
        } else {
            switch (difficultyInput) {
                case "Легкая":
                    difficulty = 1;
                    break;
                case "Средняя":
                    difficulty = 2;
                    break;
                case "Сложная":
                    difficulty = 3;
                    break;
            }
        }
    }

    public void setHiddenWord() {
        Dict dict = new Dict();

        hiddenWord = dict.getWordOfSelectedDifficulty(category, difficulty);
    }

    public void underscoreHiddenWord() {
        for (int i = 0; i < hiddenWord.getWord().length(); i++) {
            if (hiddenWord.getWord().charAt(i) != ' ') {
                currentHiddenWord += '_';
                lettersToGuess++;
            } else {
                currentHiddenWord += ' ';
            }
        }
    }

    public void startGuessing(InputStream input, OutputStream output) throws IOException {
        int currentMistakes = 0;
        Dict dict = new Dict();
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(output), true);
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        underscoreHiddenWord();
        Letter[] currentAlphabet = dict.initializeAlphabet();
        writer.println("У вас " + ALLOWED_MISTAKES + " допустимых ошибок");
        while (currentMistakes != ALLOWED_MISTAKES && lettersToGuess > 0) {
            writer.println("Количество оставшихся попыток: " + (ALLOWED_MISTAKES - currentMistakes));
            writer.println(HangmanStageVisualization.STAGES[currentMistakes]);
            writer.println(currentHiddenWord);
            if (currentMistakes == 5) {
                writer.println("Подсказка: " + hiddenWord.getHint());
            }
            writer.println("Введите 1 букву из алфавита:");
            writer.println(printAlphabet(currentAlphabet));
            char guessedLetter = (char) reader.read();
            reader.readLine(); // Обнуление символа новой строки
            guessedLetter = Character.toLowerCase(guessedLetter);
            if (guessedLetter <= 'е') {
                currentAlphabet[guessedLetter - 'а'].setUsed(true);
            } else if (guessedLetter == 'ё') {
                currentAlphabet[6].setUsed(true);
            } else {
                currentAlphabet[guessedLetter - 'а' + 1].setUsed(true);
            }

            if (hiddenWord.getWord().contains(Character.toString(guessedLetter))) {
                updateCurrentHiddenWord(guessedLetter);
            } else {
                currentMistakes++;
            }
        }
        if (lettersToGuess == 0) {
            writer.println(currentHiddenWord);
            writer.println("Поздравляем, вы полностью отгадали слово!");
        } else {
            writer.println(HangmanStageVisualization.STAGES[ALLOWED_MISTAKES]);
            writer.println("К сожалению, вы не отгадали слово");
        }
    }

    public String printAlphabet(Letter[] currentAlphabet) {
        String printedAlphabet = "";
        for (int i = 0; i < currentAlphabet.length; i++) {
            if (!currentAlphabet[i].isUsed()) {
                printedAlphabet += currentAlphabet[i].getLetter() + " ";
            }
        }
        return printedAlphabet;
    }

    public void updateCurrentHiddenWord(char guessedLetter) {
        for (int i = 0; i < hiddenWord.getWord().length(); i++) {
            if (hiddenWord.getWord().charAt(i) == guessedLetter) {
                StringBuilder sb = new StringBuilder(currentHiddenWord);
                sb.setCharAt(i, guessedLetter);
                currentHiddenWord = sb.toString();
                lettersToGuess--;
            }
        }
    }

}
