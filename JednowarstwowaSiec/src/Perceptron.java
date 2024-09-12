import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Perceptron {

    private double[] weightVector;
    private double threshold;
    private List<Language> languages;
    String flag;

    public Perceptron(List<Language> languages, String flag) {
        this.languages = languages;
        this.flag = flag;
        threshold = 0;
        setInitialWeights();
    }

    private void setInitialWeights(){

        weightVector = new double[26];
        Random random = new Random();
        for(int i = 0; i < weightVector.length; i++){
            double randomValue = random.nextDouble();
            weightVector[i] = randomValue;
            normalizeWeights(weightVector);
        }

    }

    public void deltaRule(){

        for(Language language : languages) {
            double[] inputVector = language.getLetters();
            int result = activationFunction(inputVector);
            int correctAnswer = language.getCorrectAnswer().equals(flag) ? 1 : 0;

            for (int i = 0; i < this.weightVector.length; i++) {
                double ALPHA = 0.1;
                this.weightVector[i] = this.weightVector[i] + (correctAnswer - result) * inputVector[i] * ALPHA;
            }
            double BETA = 0.1;
            this.threshold = this.threshold + (result - correctAnswer) * BETA;

        }
    }
    public double getDotProduct(double[] inputVector){

        double dotProduct = 0;

        for(int i = 0; i < weightVector.length;i++){
            dotProduct += weightVector[i] * inputVector[i];
        }
        return dotProduct;
    }

    public int activationFunction(double[] inputVector){
        double dotProduct = getDotProduct(inputVector);
        if(dotProduct >= threshold){
           return 1;
        }else{
           return 0;
        }
    }
    public void train(List<Language> test) {
        int result;
        double accuracy;
        do{

        this.deltaRule();
        double correct = 0;
        for(Language language :test){
            result = this.activationFunction(language.getLetters());
            int fl = language.getCorrectAnswer().equals(flag) ? 1:0;
            //System.out.println(language + " classified as "+result+ " correct answer " + fl);
            if(result == fl){
                correct++;
            }
        }accuracy= correct /test.size();
            // System.out.println(accuracy);
        }while(accuracy!=1.0);
        //System.out.println("Training finished");
    }

    public String getFlag() {
        return flag;
    }

    private void normalizeWeights(double[] vector){
        double squareSu = 0;

        for (double val : vector) {
            squareSu += Math.pow(val, 2);
        }
        double length = Math.sqrt(squareSu);
        double[] normalizedVector = new double[vector.length];
        for(int i =0;i< normalizedVector.length;i++){
            normalizedVector[i] = vector[i]/length;
        }
        this.weightVector = normalizedVector;
    }

}
