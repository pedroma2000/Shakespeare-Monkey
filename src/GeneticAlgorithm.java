/**
 * GeneticAlgorithm Class
 *
 * @author Pedro Machado "pedroma2000"
 */
public class GeneticAlgorithm {

    private int populationSize;
    private double mutationRate;
    private double crossoverRate;
    private int elitismCount;

    public GeneticAlgorithm(int populationSize, double mutationRate, double crossoverRate, int elitism) {
        this.populationSize = populationSize;
        this.mutationRate = mutationRate;
        this.crossoverRate = crossoverRate;
        this.elitismCount = elitism;
    }

    public Population initPopulation(int chromosomeLength) {
        Population population = new Population(this.populationSize, chromosomeLength);
        return population;
    }

    public double calcFitness(Individual individual) {
        int correctGenes = 0;

        char[] target = {'I', 'A', ' ', 'e', ' ', 'a', ' ', 'm', 'e', 'l', 'h', 'o', 'r', ' ', 'U', 'C', ' ', 'd', 'o', ' ', 'c', 'u', 'r', 's', 'o', '.'};

        for(int geneIndex = 0; geneIndex < individual.getChromosomeLength(); geneIndex++) {

            if(individual.getGene(geneIndex) == target[geneIndex]) {
                correctGenes += 1;
            }
        }

        double fitness = (double) correctGenes / individual.getChromosomeLength();

        individual.setFitness(fitness);

        return fitness;
    }

    public void evalPopulation(Population population) {
        double populationFitness = 0;

        for (Individual individual : population.getIndividuals()) {
            populationFitness += calcFitness(individual);
        }

        population.setPopulationFitness(populationFitness);
    }

    public boolean isTerminationConditionMet(Population population) {
        for (Individual individual : population.getIndividuals()) {
            if(individual.getFitness() == 1) {
                return true;
            }
        }

        return false;
    }

    public Individual selectParent(Population population) {

        Individual individuals[] = population.getIndividuals();

        double populationFitness = population.getPopulationFitness();
        double rouletteWheelPosition = Math.random() * populationFitness;

        double spinWheel = 0;
        for(Individual individual : individuals) {
            spinWheel += individual.getFitness();
            if(spinWheel >= rouletteWheelPosition) {
                return individual;
            }
        }
        return individuals[population.getPopulationSize() - 1];
    }

    public Population crossoverPopulation(Population population) {
        Population newPopulation = new Population(population.getPopulationSize());

        for(int populationIndex = 0; populationIndex < population.getPopulationSize(); populationIndex++) {
            Individual parentA = population.getFittest(populationIndex);


            if(this.crossoverRate > Math.random() && populationIndex > this.elitismCount) {
                Individual offspring = new Individual(parentA.getChromosomeLength());
                Individual parentB = selectParent(population);

                for(int geneIndex = 0; geneIndex < parentA.getChromosomeLength(); geneIndex++) {
                    if(0.5 > Math.random()) {
                        offspring.setGene(geneIndex, parentA.getGene(geneIndex));
                    } else {
                        offspring.setGene(geneIndex, parentB.getGene(geneIndex));
                    }
                }
                newPopulation.setIndividual(populationIndex, offspring);
            } else {
                newPopulation.setIndividual(populationIndex, parentA);
            }
        }
        return newPopulation;
    }

    public Population mutatePopulation(Population population) {
        Population newPopulation = new Population(this.populationSize);

        for(int populationIndex = 0; populationIndex < population.getPopulationSize(); populationIndex++) {
            Individual individual = population.getFittest(populationIndex);

            for(int geneIndex = 0; geneIndex < individual.getChromosomeLength(); geneIndex++) {
                if(populationIndex >= this.elitismCount) {
                    if(this.mutationRate > Math.random()) {
                        individual.setGene(geneIndex, individual.randomChar());
                    }
                }
            }

            newPopulation.setIndividual(populationIndex, individual);
        }

        return newPopulation;
    }
}
