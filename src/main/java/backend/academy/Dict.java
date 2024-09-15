package backend.academy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Dict {
    public static final Word[] FRUITS = new Word[] {
        new Word("Банан", "Любят обезьяны", 1),
        new Word("Киви", "Птица с таким же именем", 1),
        new Word("Лимон", "Кислый и желтый", 1),
        new Word("Груша", "В форме капли", 1),
        new Word("Яблоко", "Часто зеленое или красное", 1),
        new Word("Ананас", "Иногда так называют прическу", 2),
        new Word("Вишня", "Ягода с косточкой", 2),
        new Word("Персик", "Бархатистая кожура", 2),
        new Word("Малина", "Любят медведи", 2),
        new Word("Манго", "Много в Таиланде", 2),
        new Word("Апельсин", "Оранжевого цвета", 3),
        new Word("Клубника", "Сладкая и красная", 3),
        new Word("Грейпфрут", "Горький цитрусовый", 3),
        new Word("Черешня", "Похожа на вишню", 3),
        new Word("Абрикос", "Оранжевый с мягкой кожурой", 3)
    };
    public static final Word[] ANIMALS = new Word[] {
        new Word("Кот", "Домашний питомец", 1),
        new Word("Лиса", "Ценится ее мех", 1),
        new Word("Ёж", "Иголки на спине", 1),
        new Word("Волк", "Серый хищник", 1),
        new Word("Заяц", "Большие уши", 1),
        new Word("Бегемот", "Большой с огромной пастью", 2),
        new Word("Жираф", "Длинная шея", 2),
        new Word("Лошадь", "Быстрая и с гривой", 2),
        new Word("Белка", "Бегает по деревьям", 2),
        new Word("Пингвин", "Живет в антарктиде", 2),
        new Word("Крокодил", "Зеленый и длинный", 3),
        new Word("Коала", "Медлительное животное", 3),
        new Word("Саламандра", "Напоминает ящерицу", 3),
        new Word("Орлан", "Символ свободы", 3),
        new Word("Стрекоза", "Часто летает над водой", 3)
    };
    public static final Word[] FOOD = new Word[] {
        new Word("Хлеб", "Бывает белым или ржаным", 1),
        new Word("Суп", "Горячее жидкое блюдо", 1),
        new Word("Торт", "Сладкий и круглый", 1),
        new Word("Сыр", "Бывает с плесенью", 1),
        new Word("Пирог", "Выпечка с начинкой", 1),
        new Word("Блины", "Тонкая лепешка из теста", 2),
        new Word("Омлет", "Блюдо из взбитых яиц", 2),
        new Word("Каша", "Сваренная крупа", 2),
        new Word("Салат", "Блюдо из овощей", 2),
        new Word("Йогурт", "Кисломолочный продукт", 2),
        new Word("Лазанья", "Любит кот Гарфилд", 3),
        new Word("Спагетти", "Итальянская еда", 3),
        new Word("Буррито", "Мексиканская еда", 3),
        new Word("Рататуй", "Есть такой мультфильм", 3),
        new Word("Шакшука", "Блюдо из томатов и яиц", 3)
    };
    public static final Word[] COUNTRIES = new Word[] {
        new Word("Россия", "Самая большая страна в мире", 1),
        new Word("Канада", "Кленовый лист на флаге", 1),
        new Word("Франция", "Вкусная еда", 1),
        new Word("Китай", "Родина панд", 1),
        new Word("Египет", "Река Нил", 1),
        new Word("Швеция", "Родина икеи", 2),
        new Word("Австралия", "Отдельный континент", 2),
        new Word("Бразилия", "Пентакампеоны", 2),
        new Word("Индия", "Известна специями", 2),
        new Word("Мексика", "Родина тако", 2),
        new Word("Аргентина", "Название от элемента в таблице Менделеева", 3),
        new Word("Саудовская Аравия", "Нефтяная держава", 3),
        new Word("Филиппины", "Островное государство", 3),
        new Word("Туркменистан", "Известна коврами", 3),
        new Word("Коста Рика", "Страна в средней Америке", 3)
    };

    public String getWordOfSelectedDifficulty(String category, int difficulty){
        List<Word> wordsOfSelectedDifficulty = new ArrayList<>();
        Word[] selectedCategoryWords;
        //Выбираем нужную категорию слов
        switch (category){
            case "Фрукты":
                selectedCategoryWords = FRUITS.clone();
                break;
            case "Животные":
                selectedCategoryWords = ANIMALS.clone();
                break;
            case "Страны":
                selectedCategoryWords = COUNTRIES.clone();
                break;
            case "Еда":
                selectedCategoryWords = FOOD.clone();
                break;
            default:
                selectedCategoryWords = COUNTRIES.clone();
        }
        //Выбираем слова нужной сложности
        for(Word word : selectedCategoryWords){
            if(word.getDifficulty() == difficulty){
                wordsOfSelectedDifficulty.add(word);
            }
        }
        //Выбираем случайное слово этой сложности и категории
        Random random = new Random();
        Word randomWord = wordsOfSelectedDifficulty.get(random.nextInt(0,wordsOfSelectedDifficulty.size()));
        return randomWord.getWord();
    }

}
