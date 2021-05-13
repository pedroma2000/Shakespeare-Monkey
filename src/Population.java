import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

/**
 * Population Class
 *
 * @author Pedro Machado "pedroma2000"
 */
public class Population {

    private Individual[] population;
    private double populationFitness = -1;

    public Population(int populationSize) {
        this.population = new Individual[populationSize];
    }

    public Population(int populationSize, int chromosomeLength) {
        this.population = new Individual[populationSize];

        for (int x = 0; x < populationSize; x++) {
            Individual individual = new Individual(chromosomeLength);
            this.population[x] = individual;
        }
    }

    public Individual[] getIndividuals() {
        return this.population;
    }

    public Individual getFittest(int offset) {
        Arrays.sort(this.population, new Comparator<Individual>() {
            @Override
            public int compare(Individual o1, Individual o2) {
                if(o1.getFitness() > o2.getFitness()) {
                    return -1;
                } else if (o1.getFitness() < o2.getFitness()) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });

        return this.population[offset];
    }

    public void setPopulationFitness(double populationFitness) {
        this.populationFitness = populationFitness;
    }

    public double getPopulationFitness() {
        return populationFitness;
    }

    public int getPopulationSize() {
        return this.population.length;
    }

    public Individual setIndividual(int offset, Individual individual) {
        return population[offset] = individual;
    }

    public Individual getIndividual(int offset) {
        return this.population[offset];
    }

    public void shuffle() {
        Random rnd = new Random();

        for(int i = population.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i+1);
            Individual a = population[index];
            population[index] = population[i];
            population[i] = a;
        }
    }
}
