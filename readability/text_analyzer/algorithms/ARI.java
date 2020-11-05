package readability.text_analyzer.algorithms;

import readability.text_analyzer.TextAnalyzer;

public class ARI extends Algorithm{

    @Override
    public int calculateIndex(TextAnalyzer analyzer) {
        double score = (4.71 * analyzer.getCharacters()) / analyzer.getWords() +
                (0.5 * analyzer.getWords()) / analyzer.getSentences() - 21.43;
        System.out.printf("Automated Readability Index: %.2f (about %s year olds).%n",
                score, countAge(score));
        return Integer.parseInt(countAge(score));
    }
}
