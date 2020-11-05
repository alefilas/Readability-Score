package readability.text_analyzer.algorithms;

import readability.text_analyzer.TextAnalyzer;

public class SMOG extends Algorithm{
    @Override
    public int calculateIndex(TextAnalyzer analyzer) {
        double score = 1.043 * Math.sqrt(analyzer.getPolysyllables() * ((double)30 /
                analyzer.getSentences())) + 3.1291;
        System.out.printf("Simple Measure of Gobbledygook: %.2f (about %s year olds).%n",
                score, countAge(score));
        return Integer.parseInt(countAge(score));
    }
}
