import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class Test {
    private static final String GENES = "abcçdefgğhiıjklmnoöpqrsştuüvwxyzABCÇDEFGĞHIİJKLMNOÖPQRSŞTUÜVWXYZ1234567890 ";
    private static final String OPTIMUM = "Deep Learning 2022";
    private static final String OPTIMUM2 = "DeepLearning";
    private static final double MUTATION_PROB = 0.5;
    private static Random random = new Random();

    public static String createChromosome(String passwordChromosome){
        int chromosome_length = passwordChromosome.length();
        String str = "";
        for(int j=0; j < chromosome_length; j++){
            str += GENES.charAt(random.nextInt(GENES.length()));
        }
        return str;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<DNAGenerator> pop = new ArrayList<DNAGenerator>();
        int generation = 1;
        int popSize;
        int count = 0;
        while (count < 3){
            generation = 1;
            System.out.println(count + "/3");
            System.out.println("Please enter the population count that want to use: ");
            popSize = scanner.nextInt();
            pop.clear();
            long startTime = System.currentTimeMillis();
            for (int i = 0; i<popSize ; i++){
                DNAGenerator chromosome = new DNAGenerator(createChromosome(OPTIMUM2),OPTIMUM2);
                pop.add(chromosome);
            }
            Collections.sort(pop);
            while(pop.get(0).fitness != OPTIMUM2.length()){
                ArrayList<DNAGenerator> newPop = new ArrayList<DNAGenerator>();
                for (int i = 0 ; i<popSize ; i++ ){
                    newPop.add(pop.get(0).crossover(pop.get(1)));
                }
                System.out.println("Generation: "+generation+" and best chromosome: "+pop.get(0).chromosome);
                Collections.sort(newPop);
                pop = newPop;
                generation++;
            }
            long endTime = System.currentTimeMillis();
            System.out.println("Password cracked at "+generation+"."+"generation! "+"\n"+" Password: "+pop.get(0).chromosome+"\n"+" Time passed: "+(endTime-startTime)+"ms");
            count++;

        }
    }
}
