package strassen;

public interface MatricesArithmetic {
    int make_2squareN(int N);

    void can_multiply(SquareMatrix A, SquareMatrix B);

    SquareMatrix Strassen_Matrices(SquareMatrix A, SquareMatrix B);

    SquareMatrix partition_Matrix(int start, int end, int N, SquareMatrix squareMatrix);

    SquareMatrix add_Matrices(SquareMatrix A, SquareMatrix B);

    SquareMatrix subtract_Matrices(SquareMatrix A, SquareMatrix B);

    SquareMatrix multiply_Matrices(SquareMatrix A, SquareMatrix B);

    SquareMatrix combine_Matrices(SquareMatrix A, SquareMatrix B, SquareMatrix C, SquareMatrix D);
}
