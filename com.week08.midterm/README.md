# Strassen Algorithm

*2021학년도 1학기 컴퓨터알고리즘 중간고사*

> 이름: 최종민
>
> 학번: 202001677

## 행렬의 곱

행렬 $$\mathbf A$$ 와 행렬 $$\mathbf B$$ 가 다음과 같이 주어졌을 때,  

$$ \mathbf A = \begin{bmatrix} A_{11} & A_{12} \\ A_{21} & A_{22} \end{bmatrix}, \mathbf B = \begin{bmatrix} B_{11} & B_{12} \\ B_{21} & B_{22} \end{bmatrix}$$의 **행렬의 곱(matrix multiplication)**은 다음과 같다.
$$
\mathbf A \cdot \mathbf B = \mathbf C \begin{bmatrix} C_{11} & C_{12} \\ C_{21} & C_{22} \end{bmatrix}
$$
따라서 행렬 $$\mathbf C$$의 성분은 다음과 같이 구할 수 있다. 이 과정에서 8번의 곱셈과 4번의 덧셈을 해야한다.

$$
C_{11} = A_{11}B_{11} + A_{12}B_{21} \\

C_{12} = A_{11}B_{12} + A_{12}B_{22} \\

C_{21} = A_{21}B_{11} + A_{22}B_{21} \\

C_{22} = A_{21}B_{12} + A_{22}B_{22}
$$
위와 같은 방법으로 $$n \times n$$ 정방행렬의 곱셈을 할 경우, 알고리즘의 시간복잡도는 $$O(n^3)$$이다. 

그러나 **Strassen Algorithm**을 이용해 계산할 경우,  $$O(n^{2.807})$$로 수행할 수 있다.

## Strassen Algorithm(슈트라센 알고리즘)

$$n \times n$$ 크기를 갖는 정방행렬 $$\mathbf A$$ 와 $$\mathbf B$$ 가 다음과 같이 주어졌을 때,  두 행렬은 곱은 $$\mathbf C = \mathbf A \mathbf B$$로 나타낼 수 있고, $$ \mathbf A, \space \mathbf B, \space \mathbf C \in F^{2^n \times 2^n}$$이다.

만약 $$\mathbf A$$와 $$\mathbf B$$가 $$2^n \times 2^n$$꼴의 크기가 아니면, 모자라는 성분들을 0으로 채운다. 0으로 채운 행렬들은 연산이 끝난 후 필요한 부분만 잘라서 결과를 얻는다.

먼저, 행렬 $$\mathbf A, \mathbf B, \mathbf C$$를 같은 크기를 갖는 정방행렬 4개로 나눈다.
$$
\mathbf A = \begin{bmatrix} \mathbf  A_{11} &  \mathbf A_{12} \\ \mathbf A_{21} & \mathbf  A_{22} \end{bmatrix}, \mathbf B = \begin{bmatrix} \mathbf B_{11} & \mathbf B_{12} \\ \mathbf B_{21} & \mathbf B_{22} \end{bmatrix}, \mathbf C = \begin{bmatrix} \mathbf C_{11} & \mathbf C_{12} \\ \mathbf C_{21} & \mathbf C_{22} \end{bmatrix}
$$
행렬 $$\mathbf C$$의 성분들을 구하기 위해 다음의 행렬들을 7번의 곱셈과 10번의 덧셈을 함으로써  정의한다. 이 곱셈을 하는 과정에서 재귀호출이 사용된다. 

$$
\mathbf M_1 = (\mathbf A_{12} - \mathbf A_{22})(\mathbf B_{21} + \mathbf B_{22}) \\

\mathbf M_2 = (\mathbf A_{11} + \mathbf A_{22})(\mathbf B_{11} + \mathbf B_{22}) \\

\mathbf M_3 = (\mathbf A_{11} - \mathbf A_{21})(\mathbf B_{11} + \mathbf B_{12}) \\

\mathbf M_4 = (\mathbf A_{11} + \mathbf A_{12})\mathbf B_{22} \\

\mathbf M_5 = \mathbf A_{11}(\mathbf B_{12} - \mathbf B_{22}) \\

\mathbf M_6 = \mathbf A_{22}(\mathbf B_{21} - B_{11}) \\

\mathbf M_7 = (\mathbf A_{21} + \mathbf A_{22})\mathbf B_{11} \\
$$
위 연산들이 수행된 후, 8번의 덧셈 연산을 통해 행렬 $$\mathbf C$$ 의 성분인 행렬들을 구한다.
$$
\mathbf C_{11} = \mathbf M_1 + \mathbf M_2 - \mathbf M_4 + \mathbf M_6​ \\

\mathbf C_{12} = \mathbf M_4 + \mathbf M_5​ \\

\mathbf C_{21} = \mathbf M_6 + \mathbf M_7​ \\

\mathbf C_{22} = \mathbf M_2 - \mathbf M_3 + \mathbf M_5 - \mathbf  M_7
$$
 총 7번의 곱셈과 18번의 덧셈으로 수행할 수 있는데, 덧셈을 하는 것이 곱셈을 하는 것보다 더 적은 시간이 소요되므로, 일반적인 행렬의 곱 정의에 따라 계산하는 것보다 더 적은 연산횟수를 요구한다.

이 과정을 재귀적으로 반복할 경우, 연산은 총 $$7 \cdot n^{log_27} - 6 \cdot n^2$$번 이루어지므로,  시간복잡도는 $$T(n) = 7T(n/2) + O(n^2) = O(n^{log_27}) \approx O(n^{2.807})$$이다.

일반적인 정의에 의한 행렬의 곱보다 더 빠르게 연산이 가능하지만, 행렬의 성분이 실수일 때 **수치 안정성(numerical stability)**이 떨어진다는 단점이 있다.

## 코드 구현

이 프로그램은 ***Main.java***, ***Strassen.java***, ***MatricesArithmetic.java***, ***SquareMatrix.java***, ***Matrix.java***로 총 5개의 파일들로 구성되어 있다.

### *Main.java*

```java
package strassen;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Strassen strassen = new Strassen();

        System.out.println("     === Strassen Algorithm for Matrix Multiplication (A·B) ===");
        System.out.println("* 행렬은 모두 N×N 정방행렬이며, 행렬 A의 열 개수와 행렬 B의 행 개수가 같아야 함! *");

        System.out.print("정방행렬 A의 행과 열의 개수 N 입력: ");
        int A_size = scanner.nextInt();
        System.out.print("정방행렬 B의 행과 열의 개수 N 입력: ");
        int B_size = scanner.nextInt();

        long startTime = System.currentTimeMillis();
        SquareMatrix A = new SquareMatrix(strassen.make_2squareN(A_size));
        A.init_randomized(A_size);
        SquareMatrix B = new SquareMatrix(strassen.make_2squareN(B_size));
        B.init_randomized(B_size);

        strassen.can_multiply(A, B);
        SquareMatrix C = strassen.Strassen_Matrices(A, B);
        long endTime = System.currentTimeMillis();
        long processTime = endTime - startTime;

        System.out.println();
        System.out.println("# 행렬 A");
        A.print_Matrix(A_size);
        System.out.println("# 행렬 B");
        B.print_Matrix(B_size);
        System.out.println("\n# A·B = C");
        C.print_Matrix(A_size);

        System.out.println();
        System.out.printf("# 알고리즘 실행 시간: %d sec, %d ms", processTime / 1000, processTime % 1000);
    }
}
```

이 파일에서는 사용자로부터 입력 *N* 을 받아 *N×N* 크기의 정방행렬(square matrix) $$\mathbf A$$와 $$\mathbf B$$의 크기를 결정한다. 그러면 $$ \mathbf A \cdot \mathbf B$$의 결과인 행렬 $$\mathbf C$$를 계산하고 출력하며, 알고리즘 실행 시간까지 측정하여 출력하는 기능을 수행한다.

우선 사용자로 부터 입력을 받기 위해 `Scanner` 클래스를 이용해 객체 `scanner`를 선언한다. 그리고 Strassen Algorithm을 수행하기 위해 `Strassen` 클래스의 객체 `strassen`을 선언한다.

다음으로, 사용자로 부터 정수를 입력받아 각각 행렬의 크기를 결정하는 정수형 변수인 `A_size`와 `B_size`에 저장한다.

알고리즘 실행 시간 측정을 위해 `System.currentTimeMillis()` 메소드를 이용해 이 시점의 현재 시간을 `startTime`에 저장한다.

그리고 사용자로부터 입력받은 `A_size`와 `B_size`크기 만큼의 정방행렬인 `A`와 `B`를 각각 새로운 객체 변수인 `new SquareMatrix(strassen.make_2squareN())`로 생성한다. 만약에 두 행렬의 크기가 $$2^n \times 2^n$$가 아닌경우, 조건을 만족하도록 하기 위해 `strassen.make_2squareN()`를 먼저 입력에 들어가 조건을 만족하는지 판단 후, 그 크기만큼 행렬을 만들어준다. 

`init_randomized()` 메소드를 이용해 각 행렬의 성분에 임의의 난수를 발생해 넣어준다. 이 과정에서 사용자가 입력한 크기에 해당하는 부분만 난수가 들어가게 되고, 이를 초과하는 부분은 이미 *0*으로 초기화 되어있는 상황이 된다.

Strassen Algorithm을 연산하기 전, 행렬의 곱을 수행할 수 있는지 `strassen.can_multiply(A, B)` 메소로 판단하고, 이상이 없으면 계속 진행한다. `strassen.Strassen_Matrices(A, B)` 메소드로 이 알고리즘을 통해 행렬의 곱을 수행하고, 나온 결과를 행렬 `C`에 저장한다.

알고리즘 수행 후, `System.currentTimeMillis()` 메소드를 이용해 종료 시점의 현재 시간을 `endTime`에 저장한다. 그리고 실행 시간을 계산하기 위해 변수 `processTime`에 `endTime`과 `startTime`의 차를 계산하여 저장한다.

마지막으로, 행렬에 난수가 잘 생성되었고, 연산이 잘 되었는지 확인하기 위해 `print_Matrix()` 메소드를 이용해 행렬 `A`와 `B`, `C`를 출력한다. 그리고 측정한 알고리즘 실행 시간을 확인하기 위해 `processTime`을 출력한다.

### *Strassen.java*

```java
package strassen;

public class Strassen implements MatricesArithmetic {
 @Override
 public int make_2squareN(int power) {
  double N = Math.log(power) / Math.log(2);  // log2(N)
  return (int) Math.pow(2, Math.ceil(N));  // power가 2의 제곱수가 아니면, power보다 큰 2의 제곱수로 만들어 리턴 
 }

 @Override
 public void can_multiply(SquareMatrix A, SquareMatrix B) {
  if (A.size != B.size) {
   System.out.println("행렬 A의 열 개수와 행렬 B의 행 개수가 다릅니다.");
   System.exit(1);
  }
 }

 @Override
 public SquareMatrix Strassen_Matrices(SquareMatrix A, SquareMatrix B) {
  // 행렬의 크기가 임계점보다 작으면, 기존 행렬의 곱으로 연산
  if (A.size <= 2)
   return multiply_Matrices(A, B);
  int N = A.size / 2;  // divide matrices to 4

  // partition to 8 partition matrices: A_11, A_12, A_21, A_22
  SquareMatrix A_11 = this.partition_Matrix(0, 0, N, A);
  SquareMatrix A_12 = this.partition_Matrix(0, N, N, A);
  SquareMatrix A_21 = this.partition_Matrix(N, 0, N, A);
  SquareMatrix A_22 = this.partition_Matrix(N, N, N, A);
  SquareMatrix B_11 = this.partition_Matrix(0, 0, N, B);
  SquareMatrix B_12 = this.partition_Matrix(0, N, N, B);
  SquareMatrix B_21 = this.partition_Matrix(N, 0, N, B);
  SquareMatrix B_22 = this.partition_Matrix(N, N, N, B);

  // recursive calls for multiplications to make 7 minor matrices: M1, M2, M3, M4, M5, M6, M7
  // M1 = (A_12 - A_22)(B_21 + B_22)
  SquareMatrix M1 = Strassen_Matrices(subtract_Matrices(A_12, A_22), add_Matrices(B_21, B_22));
  // M2 = (A_11 + A_22)(B_11 + B_22)
  SquareMatrix M2 = Strassen_Matrices(add_Matrices(A_11, A_22), add_Matrices(B_11, B_22));
  // M3 = (A_11 - A_21)(B_11 + B_12)
  SquareMatrix M3 = Strassen_Matrices(subtract_Matrices(A_11, A_21), add_Matrices(B_11, B_12));
  // M4 = (A_11 + A_12)B_22
  SquareMatrix M4 = Strassen_Matrices(add_Matrices(A_11, A_12), B_22);
  // M5 = A_11(B_12 - B_22)
  SquareMatrix M5 = Strassen_Matrices(A_11, subtract_Matrices(B_12, B_22));
  // M6 = A_22(B_21 - B_11)
  SquareMatrix M6 = Strassen_Matrices(A_22, subtract_Matrices(B_21, B_11));
  // M7 = (A_21 + A_22)B_11
  SquareMatrix M7 = Strassen_Matrices(add_Matrices(A_21, A_22), B_11);

  // add minor matrices to make 4 cofactor matrices: C1, C2, C3, C4 
  // C_11 = M1 + M2 - M4 + M6
  SquareMatrix C_11 = add_Matrices(subtract_Matrices(add_Matrices(M1, M2), M4), M6);
  // C_12 = M4 + M5
  SquareMatrix C_12 = add_Matrices(M4, M5);
  // C_21 = M6 + M7
  SquareMatrix C_21 = add_Matrices(M6, M7);
  // C_22 = M2 - M3 + M5 - M7
  SquareMatrix C_22 = subtract_Matrices(add_Matrices(subtract_Matrices(M2, M3), M5), M7);

  return combine_Matrices(C_11, C_12, C_21, C_22);
 }

 @Override
 public SquareMatrix partition_Matrix(int start, int end, int N, SquareMatrix squareMatrix) {
  SquareMatrix partitioned = new SquareMatrix(N);
  for (int i = 0, x = start; i < N; i++, x++)
   for (int j = 0, y = end; j < N; j++, y++)
    partitioned.matrix[i][j] = squareMatrix.matrix[x][y];
  return partitioned;
 }

 @Override
 public SquareMatrix add_Matrices(SquareMatrix A, SquareMatrix B) {
  SquareMatrix result = new SquareMatrix(A.size);
  for (int i = 0; i < A.size; i++)
   for (int j = 0; j < A.size; j++)
    result.matrix[i][j] = A.matrix[i][j] + B.matrix[i][j];
  return result;
 }

 @Override
 public SquareMatrix subtract_Matrices(SquareMatrix A, SquareMatrix B) {
  SquareMatrix result = new SquareMatrix(A.size);
  for (int i = 0; i < A.size; i++)
   for (int j = 0; j < A.size; j++)
    result.matrix[i][j] = A.matrix[i][j] - B.matrix[i][j];
  return result;
 }

 @Override
 public SquareMatrix multiply_Matrices(SquareMatrix A, SquareMatrix B) {
  SquareMatrix result = new SquareMatrix(A.size);
  for (int i = 0; i < A.size; i++)
   for (int j = 0; j < A.size; j++)
    for (int k = 0; k < A.size; k++)
     result.matrix[i][j] += A.matrix[i][k] * B.matrix[k][j];
  return result;

 }

 @Override
 public SquareMatrix combine_Matrices(SquareMatrix A, SquareMatrix B, SquareMatrix C, SquareMatrix D) {
  int N = A.size;
  SquareMatrix result = new SquareMatrix(N * 2);
  for (int i = 0; i < N; i++)
   for (int j = 0; j < N; j++) {
    result.matrix[i][j] = A.matrix[i][j];
    result.matrix[i][j + N] = B.matrix[i][j];
    result.matrix[i + N][j] = C.matrix[i][j];
    result.matrix[i + N][j + N] = D.matrix[i][j];
   }
  return result;
 }
}
```

#### `public int make_2squareN(int power)`

이 메소드는 입력된 매개변수 `power`가 2의 제곱수가 아닐경우, 2의 제곱수를 만족하는 `power`보다 큰 정수를 만들어 리턴하는 기능을 수행한다. 이는 $$\log_2(x)$$함수를 이용해 판단할 수 있는데 `Math.log(power) / Math.log(2)`를 실수 `N`에 저장한다. 그리고 $$\lceil x \rceil$$함수 기능을 하는 `Math.ceil(N)`를 거쳐 다시 `Math.pow(2, Math.ceil(N))`로 제곱한다. 이 과정에서 결과는 실수이므로, `int`형으로 캐스팅해서 리턴하게 된다.

#### `public void can_multiply(SquareMatrix A, SquareMatrix B)`

이 메소드는 행렬의 곱의 연산 가능성을 확인한다. 이는 두 행렬 $$\mathbf A$$와 $$\mathbf B$$가 모두 정방행렬이므로, 두 행렬의 크기가 같으면 연산이 가능하다. 따라서 `A.size`와 `B.size`가 다를 경우, `System.exit(1)` 메소드를 이용해 프로그램을 강제 종료한다.

#### `public SquareMatrix Strassen_Matrices(SquareMatrix A, SquareMatrix B)`

이 메소드는 Strassen Algorithm을 수행하여 결과 행렬 $$\mathbf C$$를 리턴하는 기능을 수행한다. 이는 행렬곱셈을 수행하므로, 곱셈이 필요한 부분에서 재귀적으로 자신을 다시 호출해 수행한다. 우선 행렬의 크기가 2보다 작거나 같으면, 일반적인 행렬 곱셈의 방법으로 수행한다. 2보다 큰경우, 행렬 $$\mathbf A$$와 $$\mathbf B$$를 4등분 해서 minor 행렬 7개로 만든다. 그리고 minor 행렬 7개들로 다시 cofactor 행렬 4개로 만들어 이를 합쳐 리턴한다. 

먼저, `partition_to_Minor()` 메소드를 이용해 [수식 (3)](#Strassen-Algorithm(슈트라센-알고리즘))처럼 행렬 $$\mathbf A$$와 $$\mathbf B$$를 행렬 `A_11`, `A_12`, ..., `B_22`($$\mathbf A_{11}, \mathbf A_{12}, \cdots, \mathbf B_{22}$$)로 4등분한다. 그리고 [수식 (4)](#Strassen-Algorithm(슈트라센-알고리즘))와 같이 minor 행렬 `M_1`, `M_2`, ..., `M_7`($$\mathbf M_1, M_2, \cdots, M_7$$)을 만든다. 이 행렬들로 [수식 (5)](#Strassen-Algorithm(슈트라센-알고리즘))처럼 cofactor 행렬 `C_11`, `C_12`, `C_21`, `C_22`($$\mathbf C_{11}, \mathbf C_{12}, \mathbf C_{21}, \mathbf C_{22}$$)를 생성한다. 이 과정에서 행렬의 덧셈과 뺄셈을 하게되는데, 이는 각각 `add_Matrices()`, `subtract_Matrices()` 메소드를 이용해 수행한다.  `C_11`, `C_12`, `C_21`, `C_22`들을 이용해 [수식 (6)](#Strassen-Algorithm(슈트라센-알고리즘))과 같이 4등분된 행렬을 `combine_Matrices()` 메소드를 이용해 다시 합쳐준다. 마지막으로, 합쳐진 행렬을 리턴한다.

#### `public SquareMatrix partition_Matrix(int start, int end, int N, SquareMatrix squareMatrix)`

이 메소드는 `for`문을 이용해 정방행렬 `squareMatrix`를 4분할하는 기능을 수행한다. `start`와 `end`를 입력받아 시작 인덱스와 종료 인덱스로 사용하고, 새로운 정방행렬`partitioned`를 생성해 원행렬의 나눌 부분만 복사해 리턴한다.

#### `public SquareMatrix add_Matrices(SquareMatrix A, SquareMatrix B)`

이 메소드는 행렬 덧셈 $$\mathbf A + \mathbf B$$를 수행한다. `for`문을 이용해 각 행렬의 인덱스에 접근해 성분별로 수행하고 새로운 행렬 `result`에 복사하여 리턴한다.

#### `public SquareMatrix subtract_Matrices(SquareMatrix A, SquareMatrix B)`

이 메소드는 행렬 뺄셈 $$\mathbf A - \mathbf B$$를 수행한다. `for`문을 이용해 각 행렬의 인덱스에 접근해 성분별로 수행하고 새로운 행렬 `result`에 복사하여 리턴한다.

#### `public SquareMatrix multiply_Matrices(SquareMatrix A, SquareMatrix B)`

이 메소드는 일반적인 방법으로 행렬 곱셈 $$\mathbf A \mathbf B$$를 수행한다. `for`문을 이용해 각 행렬의 인덱스에 접근해 성분별로 수행하고 새로운 행렬 `result`에 복사하여 리턴한다.

#### `public SquareMatrix combine_Matrices(SquareMatrix A, SquareMatrix B, SquareMatrix C, SquareMatrix D)`

이 메소드는 4분할된 cofactor 행렬들을 다시 하나로 합치는 연산을 수행한다. 행렬의 크기를 cofactor 행렬의 크기의 2배로 설정하고, `for`문을 이용해 각 인덱스에 접근하여 값을 새로운 행렬 `result`에 복사하여 리턴한다. 

### *MatricesArithmetic.java*

```java
package strassen;

public interface MatricesArithmetic {
    int make_2squareN(int N);

    void can_multiply(SquareMatrix A, SquareMatrix B);

    SquareMatrix Strassen_Matrices(SquareMatrix A, SquareMatrix B);

    SquareMatrix partition_to_Minor(int start, int end, int N, SquareMatrix squareMatrix);

    SquareMatrix add_Matrices(SquareMatrix A, SquareMatrix B);

    SquareMatrix subtract_Matrices(SquareMatrix A, SquareMatrix B);

    SquareMatrix multiply_Matrices(SquareMatrix A, SquareMatrix B);

    SquareMatrix combine_Matrices(SquareMatrix A, SquareMatrix B, SquareMatrix C, SquareMatrix D);
}
```

이 파일은 행렬의 사칙연산을 수행하는 메소드들과, Strassen Algorithm에 필요한 메소드들의 원형이 정의되어 있는 인터페이스로 구성되어 있다.

### *SquareMatrix.java*

```java
package strassen;

import java.util.Random;

public class SquareMatrix implements Matrix {
    int size;
    int[][] matrix;

    public SquareMatrix(int N) {
        this.size = N;
        matrix = new int[size][size];
    }

    @Override
    public void init_randomized(int N) {
        Random random = new Random();
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                this.matrix[i][j] = random.nextInt(50);  // 0~50 사이의 임의의 난수 생성
    }

    @Override
    public void print_Matrix(int real_size) {
        // 실제 행렬의 크기만큼만 출력
        for (int i = 0; i < real_size; i++) {
            System.out.print("| ");
            for (int j = 0; j < real_size; j++)
                System.out.printf("%4d ", this.matrix[i][j]);
            System.out.print("|");
            System.out.println();
        }
    }
}
```

이 파일에는 `SquareMatrix` 클래스가 정의되어 정방행렬을 나타낸다. 2차원 배열 `matrix`로 행렬을 저장하고, `size`에는 $$n \times n$$ 정방행렬의 행의 개수(또는 열의 개수) $$n$$을 저장한다.

#### `public SquareMatrix(int N)`

이는 생성자로, 정수 `N`을 입력받아 이 행렬의 행의 개수(또는 열의 개수)인 `N`을 `size`에 저장한 후, $$n \times n$$ 정방행렬 `matrix[size][size]`를 만든다.

#### `public void init_randomized(int N)`

이 메소드는 `SquareMatrix` 객체의 `matrix`에 임의의 난수(0~50)를 저장하는 기능을 수행한다.

#### `public void print_Matrix(int real_size)`

이 메소드는 정방행렬 `matrix`를 `for`문을 이용해 각 인덱스에 접근하여 출력한다. Strassen Algorithm을 수행하는 과정에서 필요 없는 *0*이 들어간 경우, 출력을 하지 않기 위해 `real_size`를 받아와 실제 행렬의 크기만큼 인덱스에 접근해 출력한다.

### *Matrix.java*

```java
package strassen;

public interface Matrix {
    void init_randomized(int N);

    void print_Matrix(int real_size);
}
```

이 파일은 ***SquareMatrix.java*** 파일에 구현된 메소드들의 원형으로 구성된 인터페이스다.

## 실행 결과

<img src="E:\OneDrive - inu.ac.kr\gram17_windows10_backup\Documents\Codes\Java\Algorithm_Java\midterm\result_code.png" alt="result_code " style="zoom:30%;" />

예시로, 사용자로부터 행의 크기(또는 열의 크기)인 정수 5를 입력받았다.

이와 같이 $$5 \times 5$$ 정방 행렬 $$\mathbf A$$와 $$\mathbf B$$의 곱인 행렬 $$\mathbf C$$를 출력된 결과와 같이 얻을 수 있다.

## 성능 분석

이 알고리즘은 연산이 총 $$7 \cdot n^{log_27} - 6 \cdot n^2$$번 이루어지므로,  

시간복잡도는 $$T(n) = 7T(n/2) + O(n^2) = O(n^{log_27}) \approx O(n^{2.807})$$이다.

<img src="E:\github repositories\inu_algorithm\com.week08.midterm\t=n^log_2_7.png" style="zoom:25%;" />

<img src="E:\github repositories\inu_algorithm\com.week08.midterm\result.png" style="zoom:25%;" />

실제 이 알고리즘을 수행하여 점을 찍은 결과, $$O(n^{2.807})$$과 유사한 것을 확인할 수 있다.

---

## 참고 및 출처

- [Strassen algorithm - Wikipedia](https://en.wikipedia.org/wiki/Strassen_algorithm)

- [Weiss, Mark A. 2012. *Data Structures and Algorithm Analysis in Java, 3rd edition*. n.p.: Pearson.](https://www.pearson.com/store/p/data-structures-and-algorithm-analysis-in-java/P100001420183)