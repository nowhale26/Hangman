package backend.academy;

public class HangmanStageVisualization {
    public static final int VISUALIZATION_STAGES = 7;

    public static final String[] STAGES = {
        """
               +---+
                   |
                   |
                   |
                  ===
            """,  // Этап 0: Пусто

        """
               +---+
               O   |
                   |
                   |
                  ===
            """,  // Этап 1: Голова

        """
               +---+
               O   |
               |   |
                   |
                  ===
            """,  // Этап 2: Тело

        """
               +---+
               O   |
              /|   |
                   |
                  ===
            """,  // Этап 3: Одна рука

        """
               +---+
               O   |
              /|\\  |
                   |
                  ===
            """,  // Этап 4: Две руки

        """
               +---+
               O   |
              /|\\  |
              /    |
                  ===
            """,  // Этап 5: Одна нога

        """
               +---+
               O   |
              /|\\  |
              / \\  |
                  ===
            """   // Этап 6: Полностью
    };
}