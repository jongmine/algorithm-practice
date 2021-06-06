public class Main {
    public static void main(String[] args) {
        SimulatedAnnealing sa = new SimulatedAnnealing(1, 0.95, 100);
        sa.solve(new Problem() {
            @Override
            public double fit(double x) {
                return -x * x + 38 * x + 80;
            }

            @Override
            public boolean isNeighborBetter(double f0, double f1) {
                return f1 > f0;
            }
        }, 0, 31);

        System.out.println(sa.hist);
        // x=19, f(x)=441
    }
}
