public class BubbleSort extends Sort {

    @Override
    public int[] sort(int[] array) {
        System.out.println("\n=== Bubble Sort ===");
        int length = array.length;
        int[] result = array.clone();

        long startTime = System.currentTimeMillis();
        for (int i = 1; i <= length - 1; i++)
            for (int j = 1; j <= length - i; j++)
                if (result[j - 1] > result[j]) {
                    int temp = result[j - 1];
                    result[j - 1] = result[j];
                    result[j] = temp;
                }
        long endTime = System.currentTimeMillis();

        measureTime(startTime, endTime);
        return result;
    }
}
