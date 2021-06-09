# Simulated Annealing Algorithm을 이용한 Parameter Estimation

*2021학년도 1학기 컴퓨터알고리즘 기말고사*

> 이름: 최종민
>
> 학번: 202001677

## Simulated Annealing Algorithm

모의 담금질(Simulated Annealing)기법이란, 높은 온도에서 액체 상태인 물질이 온도가 점차 낮아지면서 결정체로 변하는 과정을 모방한 해 탐색 알고리즘이다.
이 알고리즘은 해를 반복해 개선함으로써, 현재 후보해의 이웃해를 임의로 찾는 방법으로 이루어진다.
이 과정에서 초기온도 , 냉각률, 반복 횟수에 따라 최적해의 값이 달라진다. 

### SimulatedAnnealing.java

```java
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class SimulatedAnnealing {
    private double temperature;
    private final double coolingFraction;
    private final int coolingSteps;
    private final double k;
    private final boolean findMinimum;
    ArrayList<Double> valueHistory;
    ArrayList<Double> candidateHistory;

    public SimulatedAnnealing(double initialTemperature, double coolingFraction, int coolingSteps, boolean findMinimum) {
        this.temperature = initialTemperature;
        this.coolingFraction = coolingFraction;
        this.coolingSteps = coolingSteps;
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
            int StepsPerTemperature = 1000;
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
        // x > y -> 최소값 구하기, x < y -> 최대값 구하기
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
```

이 객체는 초기온도 `temperature`, 냉각률 `coolingFraction`, 냉각횟수 `coolingSteps`, Boltzman 상수 `k`, 최솟값을 찾는지 여부를 알기 위한 `findMinimum` 필드가 있다.
그리고 해 탐색과정을 저장하기 위한 ArrayList `candidateHistory` 와 `valueHistory`가 있는데, 각각 다항식 x값과 f(x)값을 기록한다.

생성자 ` public SimulatedAnnealing(double initialTemperature, double coolingFraction, int coolingSteps, boolean findMinimum)`로 해탐색 결과에 영향을 주는 요소들을 받아오고, 기록하기 위한 ArrayList들도 생성한다.

`public void solve(Equation e, double upper, double lower)`는 해를 탐색하는 메소드이다.
난수를 발생시키기 위해 `Random` 클래스를 선언하고, 초기 후보해를 선언한다.
`candidate`는 후보해의 x값을, `candidateValue`는 그 f(x)값을 저장하는 변수다.
그리고 난수로 발생시켜 범위가 [`upper`, `lower`]인 후보해를 발생시키고, `calculateEq` 메소드로 f(x)값을 구해 후보해를 저장한다.

첫 번째 `for`문은 냉각횟수인 `coolingSteps`만큼 반복되며, `stepsPerTemperature`는 온도 `temperature`에 비례해서 두 번째 `for`문이 돌아가도록 임의로 온도에 20을 곱했다.
그리고 이웃해의 x값 `neighbor`와 f(x)값 `neighborValue`를 초기 후보해와 같은 방식으로 난수를 발생시켜 저장한다.

그 다음, `isNeighborBetter` 메소드로 이웃해가 현재 후보해보다 더 우수한 해인지 판별한다.
더 우수할 경우, if문 안의 값이 `true`가 되어 이웃해를 후보해로 선정한다.
그렇지 않을 경우, 확률 `acceptableCriteria`을 계산해서 이 확률에 따라 우수하지 않은데도 이웃해를 후보해로 저장한다.
이는 지역 최적해(local optimum)에 빠지지 않고 전역 최적해(global optimum)을 구하기 위함이다.
이 과정을 마친 후 현재 온도를 갱신해주는데, 임의로 정의한 냉각률 `coolingFraction`을 곱해서 값을 바꿔준다.
모든 for문을 마치면 탐색한 최적해가 `candidateHistory`와 `ValueHistory`에 저장되어 있을 것이다.

`public boolean isNeighborBetter(double x, double y, boolean findMinimum)` 메소드는 사용자가 구하려는 해가 최댓값인지 최솟값인지 정보를 받아와 적절한 어느해가 우수한 해인지 판별해주는 기능을 수행한다.

`public void getBestSolution(boolean findMinimum)` 메소드는 탐색한 기록 중 전역 최적해를 찾기위한 메소드로, 찾고난 후 그 값을 출력한다.
먼저 최솟값과 최댓값중 어떤 해를 찾는지 정보를 받아오고, 그에 맞게 `valueHistory`에서 최솟/최댓값을 `Collections.min`과 `Collections.max`를 이용하여 구한다.
구하고 나서 `candidateHistory`에서 그 값을 가지고 있는 x값을 찾아 x와 f(x)값을 출력한다.

## Curve Fitting

Curve Fitting이란, 어떤 n개의 데이터가 주어졌을 때, 이를 가장 적합하게 설명할 수 있는 다항식을 추정하는 과정이다.

데이터를 `y`, 추정하려는 m차 다항식을 `p(x) = b0 + b1*x + b2*x^2 + ... + bm*x^m` 을 설정한 후, 이 둘을 차를 구해 두 점의 거리를 구한다.
그러면 그 거리는 `|y - p(x)|`인데, 모의 담금질을 적용하기 위해서는 미분가능한 함수여야 하므로, 이를 제곱하여 평균 제곱 오차(MSE)를 이용한다.
그러면 `(y - p(x))^2`인데, n개의 데이터를 모두 더해주면 식은 `sum of (y - p(x))^2 from j=1 to n`이 되고 이를 에러(error)함수라고 한다.

MSE를 적용한 에러는 2차 다항식 형태로 나오게 되고, 이 다항식을 최소화 하면 에러값이 최소가 되므로, 더 정확한 추정을 할 수 있다.
여기서 이 에러함수의 최솟값을 구하기 위해 모의 담금질 기법을 이용한다.

> 여기서부터 수식이해가 부족해 더 이상 진행하지 못했습니다... 원래 계획은, 
> 1. Simulated Annealing Algorithm 구현 (완료)
> 
> 2. KOSPI 상장기업인 카카오의 20-06-07 ~ 21-06-06 기간동안 주간 종가 데이터를 수집해, 엑셀 파일로 정리 (완료)
> 
> 3. Curve Fitting 과정에서 에러 함수를 최소화 하는 과정에서 1번기법 사용 (실패)
> 
>> 1. 먼저 엑셀 파일을 불러오기 위해, poi 라이브러리를 사용.
>> 2. `ReadExcel.java`에서 날짜별 종가를 `Point2D` 객체를 불러와 인덱스를 `x`, 종가를 `y`에 저장.
>> 3. 그 point들을 ArrayList `stockPrice`에 순차대로 저장.
>> 4. `stockPrice`를 `CurveFitting.java`에 불러와서 데이터 처리.
> 
> NullPointerException이 발생했는데, 원인을 찾지 못해 더 이상 진행하지 못했습니다...

## 참고 및 출처

- Kreyszig, Erwin. Advanced Engineering Mathematics. n.p.: Wiley, 2011.

- Skiena, Steven S. The Algorithm Design Manual (Texts in Computer Science) 3rd ed. 2020 Edition. n.p.: Springer, 2020.
  
- 양성봉. 알기 쉬운 알고리즘. n.p.: 생능출판사, 2013.