package readability;

import readability.text_analyzer.TextAnalyzer;

public class Main {

    public static void main(String[] args) {

        TextAnalyzer analyzer = new TextAnalyzer(args[0]);
        analyzer.calculateReadability();

    }
}

