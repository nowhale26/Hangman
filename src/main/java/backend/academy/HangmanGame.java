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
    private String hiddenWord;
    private String currentHiddenWord = "";

    public void startGame() {
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
        for (int i = 0; i < hiddenWord.length(); i++) {
            if (hiddenWord.charAt(i) != ' ') {
                currentHiddenWord += '_';
            } else {
                currentHiddenWord += ' ';
            }
        }
    }

}
