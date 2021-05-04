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
