import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class SimulatedAnnealing {
    private double temperature;
    private final int coolingSteps;
    private final double coolingFraction;
    private final double k;
    private final boolean findMinimum;
    ArrayList<Double> candidateHistory;
    ArrayList<Double> valueHistory;

    public SimulatedAnnealing(double initialTemperature, double coolingFraction, int coolingSteps, boolean findMinimum) {
        this.temperature = initialTemperature;
        this.coolingSteps = coolingSteps;
        this.coolingFraction = coolingFraction;
        this.k = 0.01;  // Boltzman's constant
        this.findMinimum = findMinimum;
        this.candidateHistory = new ArrayList<>();
        this.valueHistory = new ArrayList<>();
    }

    public void solve(Equation e, double upper, double lower) {
        Random random = new Random();
        double candidate = random.nextDouble() * (upper - lower) + lower;
        double candidateValue = e.calculateEq(candidate);
        candidateHistory.add(candidate);
        valueHistory.add(candidateValue);

        for (int i = 0; i < coolingSteps; i++) {
            int StepsPerTemperature = (int) temperature * 20;
            for (int j = 0; j < StepsPerTemperature; j++) {
                double neighbor = random.nextDouble() * (upper - lower) + lower;
                double neighborValue = e.calculateEq(neighbor);

                if (isNeighborBetter(candidateValue, neighborValue, findMinimum)) {
                    candidate = neighbor;
                    candidateValue = neighborValue;
                    candidateHistory.add(candidate);
                    valueHistory.add(candidateValue);
                } else {
                    double acceptableCriteria = Math.exp(-(neighborValue - candidateValue) / k * temperature);
                    if (acceptableCriteria > random.nextDouble()) {
                        candidate = neighbor;
                        candidateValue = neighborValue;
                        candidateHistory.add(candidate);
                        valueHistory.add(candidateValue);
                    }
                }
            }
            temperature *= coolingFraction;
        }
    }

    public boolean isNeighborBetter(double x, double y, boolean findMinimum) {
        // x > y -> 최솟값 구하기, x < y -> 최댓값 구하기
        if (findMinimum)
            return x > y;
        else
            return x < y;
    }

    public void getBestSolution(boolean findMinimum) {
        double x, y;
        if (findMinimum) {
            System.out.print("최소값: ");
            y = Collections.min(this.valueHistory);
        } else {
            System.out.print("최대값: ");
            y = Collections.max(this.valueHistory);
        }
        x = this.candidateHistory.get(this.valueHistory.indexOf(y));
        System.out.println("x = " + x + ", f(x) = " + y);
    }
}

interface Equation {
    double calculateEq(double x);
}

