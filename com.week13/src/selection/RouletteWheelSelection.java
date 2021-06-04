package selection;

import problem.Solvable;

import java.util.Random;

public class RouletteWheelSelection implements Selectioner {
    @Override
    public double[] select(double[] x, Solvable p) {
        double[] y = new double[x.length];  // 리턴할 다음 세대의 후보해들
        double[] fit = new double[x.length];  // 각 후보해들의 적합도 계산 결과
        double[] cum = new double[x.length];  // 각 후보해들의 적합도 누적 값
        double sum = 0;  // 적합도의 합

        for (int i = 0; i < x.length; i++) {
            fit[i] = p.solve(x[i]);  // 각 후보해들의 적합도 평가
            sum += fit[i];  // 적합도 합 갱신
            cum[i] = sum;
        }

        Random r = new Random();
        // n개의 각 후보해들 중에서 n개의 우수한 부모해를 선택 (중복 허용)
        for (int i = 0; i < x.length; i++) {
            double q = sum * r.nextDouble();
            for (int j = 0; j < x.length; j++) {
                if (q < cum[j]) {
                    y[i] = x[i];
                    break;
                }
            }
        }

        System.out.print("[Selection] ");
        for (double a : y)
            System.out.printf("%.3f ", a);
        System.out.println(p.bestfit(y));
        return y;
    }
}
