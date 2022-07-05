import java.util.*;

public class DNAGenerator implements Comparable {

    private static final String GENES = "abcçdefgğhiıjklmnoöprsştuüvyzqwxABCÇDEFGĞHIİJKLMNOÖPRSŞTUÜVYZQWX1234567890 ";
    private static final double MUTATION_PROB = 0.05;

    String chromosome;
    String passwordChromosome;
    int fitness;
    static Random random = new Random();

    public DNAGenerator(String chromosome, String passwordChromosome){
        this.passwordChromosome = passwordChromosome;
        this.chromosome = chromosome;
        this.fitness = calculate_fitness();
    }

    public char mutated_genes(){
        return GENES.charAt(random.nextInt(GENES.length()));
    }

    public int calculate_fitness(){
        int fitness = 0;
        for(int i=0; i<passwordChromosome.length(); i++){
            if (this.chromosome.charAt(i) == passwordChromosome.charAt(i)){
                fitness++;
            }
        }
        return fitness;
    }

    DNAGenerator crossover(DNAGenerator secondPart){
        String child_chromosome = "";
        for(int i=0; i< this.chromosome.length(); i++){
            double probabilty = random.nextDouble();
            if(probabilty < (1-MUTATION_PROB)/2){
                child_chromosome += this.chromosome.charAt(i);
            }
            else if(probabilty < (1-MUTATION_PROB)){
                child_chromosome += secondPart.chromosome.charAt(i);
            }
            else{
                child_chromosome += mutated_genes();
            }
        }
        return new DNAGenerator(child_chromosome,this.passwordChromosome);
    }

    @Override
    public int compareTo(Object o){
        int compare = ((DNAGenerator) o).fitness;

        return compare - this.fitness;
    }

}
