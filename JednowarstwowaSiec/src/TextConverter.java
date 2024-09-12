import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class TextConverter {

    public double[] convertText(String filePath){

        StringBuilder result= new StringBuilder();

        try(BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)))){
            String line;
            String letters = "abcdefghijklmnopqrstuvwxyz";

            while((line = reader.readLine())!= null){

                String cleanLine = line.toLowerCase().replaceAll("[^" + letters + "]", " ");
                String[] words = cleanLine.split(" ");

                for (String word : words) {
                    result.append(word).append(" ");
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    return countLetters(String.valueOf(result));
    }
    public String convertTex(String text) {
        StringBuilder result = new StringBuilder();
        String letters = "abcdefghijklmnopqrstuvwxyz";
        String cleanLine = text.toLowerCase().replaceAll("[^" + letters + "]", " ");
            String[] words = cleanLine.split(" ");

            for (String word : words) {
                result.append(word).append(" ");
            }
        return result.toString();}




    public double[] countLetters(String text){

        String letters = "abcdefghijklmnopqrstuvwxyz";
        double[] vector = new double[letters.length()];
        Map<Character, Integer> counter = new HashMap<>();

        for(char c: letters.toCharArray()){
            counter.put(c, 0);
        }

        for(char c : text.toCharArray()){
            if(counter.containsKey(c)){
                counter.put(c, counter.get(c)+1);
            }
        }
        for (int i = 0; i< letters.length();i++){
            vector[i] = counter.get(letters.charAt(i));
            //System.out.print(vector[i]+", ");
        }
        //System.out.println();
    return vector;
    }

}
