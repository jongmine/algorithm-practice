import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.print("배열 요소의 개수 입력: ");
        int[] array = new int[scanner.nextInt()];

        for (int i = 0; i < array.length; i++) array[i] = random.nextInt(100);  // 난수 삽입

        System.out.println("정렬 전 배열");
        for (int i : array) System.out.printf("%d ", i);
        System.out.println();

        BubbleSort bubbleSort = new BubbleSort();
        int[] bubble = bubbleSort.sort(array);
        for (int i : bubble) System.out.printf("%d ", i);

        SelectionSort selectionSort = new SelectionSort();
        int[] selection = selectionSort.sort(array);
        for (int i : selection) System.out.printf("%d ", i);

        InsertionSort insertionSort = new InsertionSort();
        int[] insertion = insertionSort.sort(array);
        for (int i : insertion) System.out.printf("%d ", i);

        ShellSort shellSort = new ShellSort();
        int[] shell = shellSort.sort(array);
        for (int i : shell) System.out.printf("%d ", i);
    }
}
