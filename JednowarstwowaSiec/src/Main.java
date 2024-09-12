import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

TextConverter textConverter = new TextConverter();

        List<Language> languages = new ArrayList<>();
        List<Language> languagesTest = new ArrayList<>();
        List<Perceptron> classes = new ArrayList<>();
        classes.add(new Perceptron(languages, "Polish"));
        classes.add(new Perceptron(languages, "German"));
        classes.add(new Perceptron(languages, "French"));
        Net net;


        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter 'file' to input data from a file or 'input' to pass data directly: ");
        String input;
        do {
            input = scanner.nextLine();
            if (!input.equals("file") && !input.equals("input")) {
                System.out.println("Not recognized command. Please enter 'file' or 'input'.");
            }
        } while (!input.equals("file") && !input.equals("input"));

        if (input.equals("file")) {
            System.out.println("Pass file path");
            String str = scanner.nextLine();
            double[] testData = textConverter.convertText(str);
            double[] t = normalize(testData);
            net = new Net(classes,t, languagesTest);

        } else {

                System.out.println("Insert text: ");
                String text = scanner.nextLine();
                String res = textConverter.convertTex(text);
               double[] t = normalize(textConverter.countLetters(res));
                net = new Net(classes,t, languagesTest);
        }
        for(int i = 1; i< 11;i++) {
            languages.add(new Language(textConverter.convertText(String.format("src/Polski/polski%s.txt", i)), "Polish"));
        }
        for(int i = 1; i< 3;i++) {
            languagesTest.add(new Language(textConverter.convertText(String.format("src/testing/polski%s.txt", i)), "Polish"));
        }
        for(int i = 1; i< 11;i++) {
            languages.add(new Language(textConverter.convertText(String.format("src/Deutschlandski/deutschlandski%s.txt", i)), "German"));
        }
        for(int i = 1; i< 3;i++) {
            languagesTest.add(new Language(textConverter.convertText(String.format("src/testing/german%s.txt", i)), "German"));
        }
        for(int i = 1; i<11;i++) {
            languages.add(new Language(textConverter.convertText(String.format("src/Francuski/francuski%s.txt", i)), "French"));
        }
        for(int i = 9; i< 11;i++) {
            languagesTest.add(new Language(textConverter.convertText(String.format("src/testing/francuski%s.txt", i)), "French"));
        }

        for(Perceptron perceptron:classes){
            perceptron.train(languagesTest);
        }
        Net n = new Net(classes, languagesTest);
        n.trainPerceptron();
       net.getResult();

    }
    private static double[] normalize(double[] vector){
        double squareSum = 0;

        for (double val : vector) {
            squareSum += Math.pow(val, 2);
        }
        double length = Math.sqrt(squareSum);
        double[] normalizedVector = new double[vector.length];
        for(int i =0;i< normalizedVector.length;i++){
            normalizedVector[i] = vector[i]/length;
        }
       return normalizedVector;
    }

}