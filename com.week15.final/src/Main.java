public class Main {
    public static void main(String[] args) {
        SimulatedAnnealing sa = new SimulatedAnnealing(1, 0.998, 1000, true);
        sa.solve(x -> -x * x + 38 * x + 80, 0, 31);
        sa.getBestSolution(true);
    }
}
