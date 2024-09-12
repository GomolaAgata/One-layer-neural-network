import java.util.Arrays;
import java.util.List;

public class Language {
    private double[] letters;
    private String correctAnswer;

    public Language(double[] letters, String correctAnswer) {
        this.letters = letters;
        this.correctAnswer = correctAnswer;
        normalize();
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public double[] getLetters() {
        return letters;
    }

    private void normalize(){
        double squareSum = 0;

        for (double val : this.letters) {
            squareSum += Math.pow(val, 2);
        }
        double length = Math.sqrt(squareSum);
        double[] normalizedVector = new double[this.letters.length];
        for(int i =0;i< normalizedVector.length;i++){
            normalizedVector[i] = this.letters[i]/length;
        }
        this.letters = normalizedVector;
    }
    @Override
    public String toString() {
        return "Language{" +
                "letters=" + Arrays.toString(letters) +
                ", correctAnswer='" + correctAnswer + '\'' +
                '}';
    }
}
