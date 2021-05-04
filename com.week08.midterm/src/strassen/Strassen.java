package strassen;

public class Strassen implements MatricesArithmetic {
    @Override
    public int make_2squareN(int power) {
        double N = Math.log(power) / Math.log(2);  // log2(N) function
        return (int) Math.pow(2, Math.ceil(N));  // power가 2의 제곱수가 아니면, power보다 큰 2의 제곱수로 만들어 return
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
        SquareMatrix A_11 = partition_Matrix(0, 0, N, A);
        SquareMatrix A_12 = partition_Matrix(0, N, N, A);
        SquareMatrix A_21 = partition_Matrix(N, 0, N, A);
        SquareMatrix A_22 = partition_Matrix(N, N, N, A);
        SquareMatrix B_11 = partition_Matrix(0, 0, N, B);
        SquareMatrix B_12 = partition_Matrix(0, N, N, B);
        SquareMatrix B_21 = partition_Matrix(N, 0, N, B);
        SquareMatrix B_22 = partition_Matrix(N, N, N, B);

        // recursive calls for multiplications to make 7 minor matrices: M1, M2, M3, M4, M5, M6, M7
        SquareMatrix M1 = Strassen_Matrices(subtract_Matrices(A_12, A_22), add_Matrices(B_21, B_22));  // M1 = (A_12 - A_22)(B_21 + B_22)
        SquareMatrix M2 = Strassen_Matrices(add_Matrices(A_11, A_22), add_Matrices(B_11, B_22));  // M2 = (A_11 + A_22)(B_11 + B_22)
        SquareMatrix M3 = Strassen_Matrices(subtract_Matrices(A_11, A_21), add_Matrices(B_11, B_12));  // M3 = (A_11 - A_21)(B_11 + B_12)
        SquareMatrix M4 = Strassen_Matrices(add_Matrices(A_11, A_12), B_22);  // M4 = (A_11 + A_12)B_22
        SquareMatrix M5 = Strassen_Matrices(A_11, subtract_Matrices(B_12, B_22));  // M5 = A_11(B_12 - B_22)
        SquareMatrix M6 = Strassen_Matrices(A_22, subtract_Matrices(B_21, B_11));  // M6 = A_22(B_21 - B_11)
        SquareMatrix M7 = Strassen_Matrices(add_Matrices(A_21, A_22), B_11);  // M7 = (A_21 + A_22)B_11

        // add minor matrices to make 4 cofactor matrices: C1, C2, C3, C4 
        SquareMatrix C_11 = add_Matrices(subtract_Matrices(add_Matrices(M1, M2), M4), M6);  // C_11 = M1 + M2 - M4 + M6
        SquareMatrix C_12 = add_Matrices(M4, M5);  // C_12 = M4 + M5
        SquareMatrix C_21 = add_Matrices(M6, M7);  // C_21 = M6 + M7
        SquareMatrix C_22 = subtract_Matrices(add_Matrices(subtract_Matrices(M2, M3), M5), M7);  // C_22 = M2 - M3 + M5 - M7

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
