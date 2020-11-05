package readability.text_analyzer;

import readability.text_analyzer.algorithms.*;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextAnalyzer {

    private String text;
    private final String fileName;
    private int characters;
    private int sentences;
    private int words;
    private int syllables;
    private int polysyllables;

    public TextAnalyzer(String fileName) {
        this.fileName = fileName;
    }

    public void calculateReadability() {
        readText(fileName);
        characters = countCharacters();
        sentences = countSentences();
        words = countWords();
        syllables = countSyllables();
        polysyllables = countPolysyllables();

        System.out.printf("Words: %d\n" +
                        "Sentences: %d\n" +
                        "Characters: %d\n" +
                        "Syllables: %d\n" +
                        "Polysyllables: %d\n" +
                        "Enter the score you want to calculate (ARI, FK, SMOG, CL, all):\n",
                words, sentences, characters, syllables, polysyllables);

        switch (new Scanner(System.in).nextLine()) {
            case "ARI":
                calculateIndexes(new ARI());
                break;
            case "FK":
                calculateIndexes(new FK());
                break;
            case "SMOG":
                calculateIndexes(new SMOG());
                break;
            case "CL":
                calculateIndexes(new CL());
                break;
            case "all":
                calculateIndexes(new ARI(), new FK(), new SMOG(), new CL());
        }

    }

    private void calculateIndexes(Algorithm...algorithms) {

        int years = 0;
        for (Algorithm al : algorithms) {
            years += al.calculateIndex(this);
        }

        System.out.printf("This text should be understood in average by %.2f year olds.",
                (double) years / algorithms.length);
    }

    private int countPolysyllables() {
        Pattern vocals = Pattern.compile("\\b\\w*[aeiouyAEIOUY]\\w+[aeiouyAEIOUY]\\w+[aeiouyAEIOUY]\\w*\\b");

        int vocalCounter = 0;
        Matcher m = vocals.matcher(text);
        while (m.find()) {
            vocalCounter++;
        }

        text = text.replaceAll("the", "");
        Pattern vocale = Pattern.compile("\\b[^aeiouyAEIOUY\\s]*[aeiouyAEIOUY]+[^aeiouyAEIOUY\\s]*[aeiouyAEIOUY]+[^aeiouyAEIOUY\\s]*e\\b");

        Matcher v = vocale.matcher(text);
        while (v.find()) {
            vocalCounter--;
        }
        return vocalCounter;
    }

    private int countSyllables() {
        Pattern vocals = Pattern.compile("([aeiouyAEIOUY][aeiouy]?)|the |you|\\d+[.,]?\\d*");

        int vocalCounter = 0;
        Matcher m = vocals.matcher(text);
        while (m.find()) {
            vocalCounter++;
        }

        text = text.replaceAll("the ", "");
        Pattern vocale = Pattern.compile("\\w+[^aeiouy]e\\b");

        Matcher v = vocale.matcher(text);
        while (v.find()) {
            vocalCounter--;
        }

        return vocalCounter;
    }




    private void readText (String fileName) {
        StringBuilder text = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(fileName)))) {
            while (reader.ready()) {
                text.append(reader.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.text = text.toString();
    }

    private int countSentences() {
        return text.split("[.!?]").length;
    }

    private int countWords() {
        return text.split("\\s").length;
    }

    private int countCharacters() {
        return text.replaceAll("\\s", "").length();
    }

    public int getCharacters() {
        return characters;
    }

    public int getSentences() {
        return sentences;
    }

    public int getWords() {
        return words;
    }

    public int getSyllables() {
        return syllables;
    }

    public int getPolysyllables() {
        return polysyllables;
    }
}
