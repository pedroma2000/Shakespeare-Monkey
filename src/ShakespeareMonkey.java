
/**
 * ShakespeareMonkey Class (main class)
 *
 *
 * @author Pedro Machado "pedroma2000"
 */
public class ShakespeareMonkey {

    final static int POPULATION_MAX = 100;
    final static double MUTATION_RATE = 0.01;
    final static double CROSSOVER_RATE = 0.95;
    final static int ELITISM = 3;

    final static String TARGET = "IA e a melhor UC do curso.";

    public static void main(String[] args) {
        int generation = 1;
        GeneticAlgorithm ga = new GeneticAlgorithm(POPULATION_MAX, MUTATION_RATE, CROSSOVER_RATE, ELITISM);

        Population population = ga.initPopulation(TARGET.length());
        ga.evalPopulation(population);

        while(!ga.isTerminationConditionMet(population)) {
            System.out.println("Gen (" + generation + ") best individual: " + population.getFittest(0).toString()
            + " Population Fitness: " + population.getPopulationFitness());

            population = ga.crossoverPopulation(population);
            population = ga.mutatePopulation(population);
            ga.evalPopulation(population);

            generation++;
        }

        System.out.println("Found a solution after " + generation + " generations.");
        System.out.println("Best solution: " + population.getFittest(0).toString());
    }
}
