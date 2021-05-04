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
