package readability.text_analyzer.algorithms;

import readability.text_analyzer.TextAnalyzer;

public class FK extends Algorithm{
    @Override
    public int calculateIndex(TextAnalyzer analyzer) {
        double score = (0.39 * analyzer.getWords()) / analyzer.getSentences() +
                (11.8 * analyzer.getSyllables()) / analyzer.getWords() - 15.59;
        System.out.printf("Flesch–Kincaid readability tests: %.2f (about %s year olds).%n",
                score, countAge(score));
        return Integer.parseInt(countAge(score));
    }
}
