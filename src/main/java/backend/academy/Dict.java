package backend.academy;

import java.util.Random;

public class Dict {
    private static final String ALPHABET = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";

    private Dict() {

    }

    private static final Word[][] FRUITS = new Word[][] {
        {
            new Word("банан", "Любят обезьяны", 1),
            new Word("киви", "Птица с таким же именем", 1),
            new Word("лимон", "Кислый и желтый", 1),
            new Word("груша", "В форме капли", 1),
            new Word("яблоко", "Часто зеленое или красное", 1)
        },
        {
            new Word("ананас", "Иногда так называют прическу", 2),
            new Word("вишня", "Ягода с косточкой", 2),
            new Word("персик", "Бархатистая кожура", 2),
            new Word("малина", "Любят медведи", 2),
            new Word("манго", "Много в Таиланде", 2)
        },
        {
            new Word("апельсин", "Оранжевого цвета", 3),
            new Word("клубника", "Сладкая и красная", 3),
            new Word("грейпфрут", "Горький цитрусовый", 3),
            new Word("черешня", "Похожа на вишню", 3),
            new Word("абрикос", "Оранжевый с мягкой кожурой", 3)
        }
    };
    private static final Word[][] ANIMALS = new Word[][] {
        {
            new Word("кот", "Домашний питомец", 1),
            new Word("лиса", "Ценится ее мех", 1),
            new Word("ёж", "Иголки на спине", 1),
            new Word("волк", "Серый хищник", 1),
            new Word("заяц", "Большие уши", 1)
        },
        {
            new Word("бегемот", "Большой с огромной пастью", 2),
            new Word("жираф", "Длинная шея", 2),
            new Word("лошадь", "Быстрая и с гривой", 2),
            new Word("белка", "Бегает по деревьям", 2),
            new Word("пингвин", "Живет в антарктиде", 2)
        },
        {
            new Word("крокодил", "Зеленый и длинный", 3),
            new Word("коала", "Медлительное животное", 3),
            new Word("саламандра", "Напоминает ящерицу", 3),
            new Word("орлан", "Символ свободы", 3),
            new Word("стрекоза", "Часто летает над водой", 3)
        }
    };
    private static final Word[][] FOOD = new Word[][] {
        {
            new Word("хлеб", "Бывает белым или ржаным", 1),
            new Word("суп", "Горячее жидкое блюдо", 1),
            new Word("торт", "Сладкий и круглый", 1),
            new Word("сыр", "Бывает с плесенью", 1),
            new Word("пирог", "Выпечка с начинкой", 1)
        },
        {
            new Word("блины", "Тонкая лепешка из теста", 2),
            new Word("омлет", "Блюдо из взбитых яиц", 2),
            new Word("каша", "Сваренная крупа", 2),
            new Word("салат", "Блюдо из овощей", 2),
            new Word("йогурт", "Кисломолочный продукт", 2)
        },
        {
            new Word("лазанья", "Любит кот Гарфилд", 3),
            new Word("спагетти", "Итальянская еда", 3),
            new Word("буррито", "Мексиканская еда", 3),
            new Word("рататуй", "Есть такой мультфильм", 3),
            new Word("шакшука", "Блюдо из томатов и яиц", 3)
        }
    };
    private static final Word[][] COUNTRIES = new Word[][] {
        {
            new Word("россия", "Самая большая страна в мире", 1),
            new Word("канада", "Кленовый лист на флаге", 1),
            new Word("франция", "Вкусная еда", 1),
            new Word("китай", "Родина панд", 1),
            new Word("египет", "Река Нил", 1)
        },
        {
            new Word("швеция", "Родина икеи", 2),
            new Word("австралия", "Отдельный континент", 2),
            new Word("бразилия", "Пентакампеоны", 2),
            new Word("индия", "Известна специями", 2),
            new Word("мексика", "Родина тако", 2)
        },
        {
            new Word("аргентина", "Название от элемента в таблице Менделеева", 3),
            new Word("саудовская аравия", "Нефтяная держава", 3),
            new Word("филиппины", "Островное государство", 3),
            new Word("туркменистан", "Известна коврами", 3),
            new Word("коста рика", "Страна в средней Америке", 3)
        }
    };

    public static Word getWordOfSelectedDifficulty(String category, int difficulty) {
        String switchCategory = category.toLowerCase();
        Random random = new Random();
        Word randomWord;
        int randomWordNumber = 0;
        //Выбираем рандомное слово нужной сложности и категории
        switch (switchCategory) {
            case "фрукты":
                randomWordNumber = random.nextInt(0, FRUITS[difficulty - 1].length);
                randomWord = FRUITS[difficulty - 1][randomWordNumber];
                break;
            case "животные":
                randomWordNumber = random.nextInt(0, ANIMALS[difficulty - 1].length);
                randomWord = ANIMALS[difficulty - 1][randomWordNumber];
                break;
            case "страны":
                randomWordNumber = random.nextInt(0, COUNTRIES[difficulty - 1].length);
                randomWord = COUNTRIES[difficulty - 1][randomWordNumber];
                break;
            case "еда":
                randomWordNumber = random.nextInt(0, FOOD[difficulty - 1].length);
                randomWord = FOOD[difficulty - 1][randomWordNumber];
                break;
            default:
                randomWord = COUNTRIES[difficulty - 1][randomWordNumber];
        }

        return randomWord;
    }

    public static Letter[] initializeAlphabet() {
        Letter[] currentAlphabet = new Letter[ALPHABET.length()];
        for (int i = 0; i < ALPHABET.length(); i++) {
            Letter letter = new Letter(ALPHABET.charAt(i), false);
            currentAlphabet[i] = letter;
        }
        return currentAlphabet;
    }

}
