import crossover.SimpleCrossOver;
import mutation.SimpleMutation;
import problem.SimpleProblem;
import problem.Solvable;
import selection.RouletteWheelSelection;

public class Main {
    public static void main(String[] args) {
        Solvable s = new SimpleProblem();
        double[] x = {1, 31, 24, 17, 4, 16, 3, 19, 20, 31, 15, 8};

        GeneticAlgorithm ga = new GeneticAlgorithm(x, s,
                new RouletteWheelSelection(),
                new SimpleCrossOver(),
                new SimpleMutation(0.1));
        double[] y = ga.run(1000);

        System.out.println(s.bestfit(y));
        System.out.println(s.solve(s.bestfit(y)));
    }
}
