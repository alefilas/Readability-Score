package readability.text_analyzer.algorithms;

import readability.text_analyzer.TextAnalyzer;

public class CL extends Algorithm{
    @Override
    public int calculateIndex(TextAnalyzer analyzer) {
        double score = 0.0588 * ((double) analyzer.getCharacters() / analyzer.getWords() * 100) -
                0.296 * ((double) analyzer.getSentences() / analyzer.getWords() * 100) - 15.8;
        System.out.printf("Coleman–Liau index: %.2f (about %s year olds).%n",
                score, countAge(score));
        return Integer.parseInt(countAge(score));
    }
}
