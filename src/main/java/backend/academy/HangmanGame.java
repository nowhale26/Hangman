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
    private static final String FRUITS = "Фрукты";
    private static final String ANIMALS = "Животные";
    private static final String COUNTRIES = "Страны";
    private static final String FOOD = "Еда";
    private static final int HINT_STAGE = 5;
    private static final int YO_INDEX = 6;
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
        Random random = new Random();

        // Проверка ввода категории
        while (true) {
            writer.println(
                "Выберите одну из категорий слов: Фрукты, Животные, Страны, Еда (пустой для случайного выбора)");
            String categoryInput = reader.readLine();

            category = chooseCategory(categoryInput);
            if (category.equals(" ")) {
                writer.println("Неверная категория, попробуйте снова");
            } else {
                break;
            }
        }

        // Проверка ввода сложности
        while (true) {
            writer.println(
                "Выберите одну из сложностей: Легкая, Средняя, Сложная (или оставьте пустым для случайного выбора)");
            String difficultyInput = reader.readLine();

            difficulty = chooseDifficulty(difficultyInput);
            if (difficulty == 0) {
                writer.println("Неверная сложность. Попробуйте снова.");
            } else {
                break;
            }
        }
    }

    public void setHiddenWord() {
        Dict dict = new Dict();

        hiddenWord = dict.getWordOfSelectedDifficulty(category, difficulty);
    }

    public String underscoreHiddenWord() {
        String underscoredHiddenWord = "";
        for (int i = 0; i < hiddenWord.getWord().length(); i++) {
            if (hiddenWord.getWord().charAt(i) != ' ') {
                underscoredHiddenWord += '_';
                lettersToGuess++;
            } else {
                underscoredHiddenWord += ' ';
            }
        }
        return underscoredHiddenWord;
    }

    public void startGuessing(InputStream input, OutputStream output) throws IOException {
        int currentMistakes = 0;
        Dict dict = new Dict();
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(output), true);
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        currentHiddenWord = underscoreHiddenWord();
        Letter[] currentAlphabet = dict.initializeAlphabet();
        writer.println("У вас " + ALLOWED_MISTAKES + " допустимых ошибок");

        while (currentMistakes != ALLOWED_MISTAKES && lettersToGuess > 0) {
            writer.println("Количество оставшихся попыток: " + (ALLOWED_MISTAKES - currentMistakes));
            writer.println(HangmanStageVisualization.STAGES[currentMistakes]);
            writer.println(currentHiddenWord);

            if (currentMistakes == HINT_STAGE) {
                writer.println("Подсказка: " + hiddenWord.getHint());
            }

            // Проверка ввода буквы
            char guessedLetter;
            while (true) {
                writer.println("Введите 1 букву из алфавита:");
                writer.println(printAlphabet(currentAlphabet));
                String inputLine = reader.readLine().trim();

                if (inputLine.length() == 1 && Character.isLetter(inputLine.charAt(0))) {
                    guessedLetter = Character.toLowerCase(inputLine.charAt(0));
                    if ((guessedLetter >= 'а' && guessedLetter <= 'я') || guessedLetter == 'ё') {
                        break;
                    } else {
                        writer.println("Неверная буква. Попробуйте снова.");
                    }
                } else {
                    writer.println("Пожалуйста, введите только одну букву.");
                }
            }

            // Отмечаем букву как использованную
            if (guessedLetter <= 'е') {
                currentAlphabet[guessedLetter - 'а'].setUsed(true);
            } else if (guessedLetter == 'ё') {
                currentAlphabet[YO_INDEX].setUsed(true);
            } else {
                currentAlphabet[guessedLetter - 'а' + 1].setUsed(true);
            }
            //Проверка правильности угаданной буквы
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

    private static final int RANDOM_CATEGORY_BOUND = 5;
    private static final int FRUITS_CASE = 1;
    private static final int ANIMALS_CASE = 2;
    private static final int COUNTRIES_CASE = 3;
    private static final int FOOD_CASE = 4;

    public String chooseCategory(String categoryInput) {
        String chosenCategory = "";
        if (categoryInput == null || categoryInput.isBlank()) {
            Random random = new Random();
            int randomCategory = random.nextInt(1, RANDOM_CATEGORY_BOUND);
            chosenCategory = switch (randomCategory) {
                case FRUITS_CASE -> FRUITS;
                case ANIMALS_CASE -> ANIMALS;
                case COUNTRIES_CASE -> COUNTRIES;
                case FOOD_CASE -> FOOD;
                default -> " ";
            };
            return chosenCategory;
        } else if (categoryInput.equalsIgnoreCase(FRUITS)
            || categoryInput.equalsIgnoreCase(ANIMALS)
            || categoryInput.equalsIgnoreCase(COUNTRIES)
            || categoryInput.equalsIgnoreCase(FOOD)) {
            return categoryInput;
        } else {
            return " ";
        }
    }

    private static final int DIFFICULTY_BOUND = 4;
    private static final int EASY_DIFFICULTY = 1;
    private static final int MEDIUM_DIFFICULTY = 2;
    private static final int HARD_DIFFICULTY = 3;

    public int chooseDifficulty(String difficultyInput) {
        int chosenDufficulty = 0;
        if (difficultyInput == null || difficultyInput.isBlank()) {
            Random random = new Random();
            return random.nextInt(1, DIFFICULTY_BOUND);
        } else if (difficultyInput.equalsIgnoreCase("Легкая")) {
            chosenDufficulty = EASY_DIFFICULTY;
        } else if (difficultyInput.equalsIgnoreCase("Средняя")) {
            chosenDufficulty = MEDIUM_DIFFICULTY;
        } else if (difficultyInput.equalsIgnoreCase("Сложная")) {
            chosenDufficulty = HARD_DIFFICULTY;
        }
        return chosenDufficulty;
    }
}
