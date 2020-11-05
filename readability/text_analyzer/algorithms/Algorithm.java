package readability.text_analyzer.algorithms;


import readability.text_analyzer.TextAnalyzer;

public abstract class Algorithm {

    public abstract int calculateIndex(TextAnalyzer analyzer);


    protected String countAge(double score) {
        switch ((int) Math.ceil(score)) {
            case 1:
                return "6";
            case 2:
                return "7";
            case 3:
                return "9";
            case 4:
                return "10";
            case 5:
                return "11";
            case 6:
                return "12";
            case 7:
                return "13";
            case 8:
                return "14";
            case 9:
                return "15";
            case 10:
                return "16";
            case 11:
                return "17";
            case 12:
                return "18";
            case 13:
                return "24";
            case 14:
                return "25";
            default:
                return "26";
        }
    }
}
