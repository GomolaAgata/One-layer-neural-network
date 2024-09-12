import java.util.ArrayList;
import java.util.List;

public class Net {
    List<Perceptron> classes;
    double[] toClassify;
    List<Language> languagesTest;
    List<Perceptron> activatedPerceptrons;

    public Net(List<Perceptron> classes, double[] toClassify, List<Language> languagesTest) {
        this.classes = classes;
        this.toClassify = toClassify;
        this.languagesTest = languagesTest;
    }
    public Net(List<Perceptron> classes,List<Language> languagesTest) {
        this.classes = classes;
        this.languagesTest = languagesTest;
    }

    public void getResult(){

       activatedPerceptrons = new ArrayList<>();
        double sum = 0;

        for (Perceptron perceptron : classes) {
            double dotProduct = perceptron.getDotProduct(toClassify);
            sum+=dotProduct;
            if (perceptron.activationFunction(toClassify) == 1) {
                activatedPerceptrons.add(perceptron);
            }
        }
        for(Perceptron perceptron: classes){
            double dotProduct = perceptron.getDotProduct(toClassify);
            System.out.println("Probability for "+perceptron.getFlag() + " "+dotProduct/sum*100+" %");
        }
        for(Perceptron perceptron: classes){
            double dotProduct = perceptron.getDotProduct(toClassify);
            System.out.println("Activation level for "+perceptron.getFlag() + " "+sigmoid(dotProduct)*100+" %");
        }
        System.out.println();

        if (activatedPerceptrons.size() > 1) {
            System.out.println("Multiple perceptrons activated");
            Perceptron maxDotProductPerceptron = null;
            double maxDotProduct = 0;

            for (Perceptron perceptron : activatedPerceptrons) {
                double dotProduct = perceptron.getDotProduct(toClassify);
                sum+=dotProduct;
                System.out.println("Dot product for " + perceptron.getFlag() + ": " + dotProduct);

                if (dotProduct > maxDotProduct) {
                    maxDotProduct = dotProduct;
                    maxDotProductPerceptron = perceptron;
                }
            }

            if (maxDotProductPerceptron != null) {
                System.out.println("Classified as: " + maxDotProductPerceptron.getFlag());
            } else {
                System.out.println("Unable to classify");

            }

        }else if(activatedPerceptrons.size()==1){
            System.out.println("Activated perceptron: "+activatedPerceptrons.get(0).getFlag());
        }

    }


public void trainPerceptron(){

    for (Language language : languagesTest) {
        do {
            activatedPerceptrons = new ArrayList<>();
            for (Perceptron perceptron : classes) {
                if (perceptron.activationFunction(language.getLetters()) == 1) {
                    activatedPerceptrons.add(perceptron);
                }
                if (activatedPerceptrons.size() > 1) {
                    for (Perceptron perceptron1 : classes) {
                        System.out.println(perceptron1);
                        perceptron1.train(languagesTest);
                    }
                }
            }
        } while (activatedPerceptrons.size() > 1);
    }
}
    private double sigmoid(double x) {
        return 1 / (1 + Math.exp(-x));
    }

}
