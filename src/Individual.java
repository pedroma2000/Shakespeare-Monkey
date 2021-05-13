import java.util.Random;

/**
 * Individual Class
 *
 * @author Pedro Machado "pedroma2000"
 */
public class Individual {

    private char[] chromosome;
    private double fitness = -1;

    public Individual(char[] chromosome) {
        this.chromosome = chromosome;
    }

    public Individual(int chromosomeLength) {

        this.chromosome = new char[chromosomeLength];
        for(int gene = 0; gene < chromosomeLength; gene++) {

            Random rnd = new Random();
            char value = (char) (' ' + rnd.nextInt(95));

            this.setGene(gene, value);
        }
    }

    public char[] getChromosome() {
        return this.chromosome;
    }

    public int getChromosomeLength() {
        return this.chromosome.length;
    }

    public void setGene(int offset, char gene) {
        this.chromosome[offset] = gene;
    }

    public char getGene(int offset) {
        return this.chromosome[offset];
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    public double getFitness() {
        return this.fitness;
    }

    public char randomChar() {
        Random rnd = new Random();
        char value = (char) (' ' + rnd.nextInt(95));

        return value;
    }

    public String toString() {
        String output = "";
        for(int gene = 0; gene < this.chromosome.length; gene++) {
            output += this.chromosome[gene];
        }

        return output;
    }
}
