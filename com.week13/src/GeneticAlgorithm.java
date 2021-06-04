import crossover.Crossoverer;
import mutation.Mutationer;
import problem.Solvable;
import selection.Selectioner;

public class GeneticAlgorithm {
    private Selectioner s;
    private Crossoverer c;
    private Mutationer m;
    private Solvable p;
    double[] x;

    public GeneticAlgorithm(double[] x, Solvable p, Selectioner s, Crossoverer c, Mutationer m) {
        this.x = x;
        this.p = p;
        this.s = s;
        this.c = c;
        this.m = m;
    }

    public double[] run(int n) {
        for (int i = 0; i < n; i++)
            x = m.mutate(c.crossover(s.select(x, p)));
        return x;
    }
}
