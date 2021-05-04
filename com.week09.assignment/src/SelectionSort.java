public class SelectionSort extends Sort {

    @Override
    public int[] sort(int[] array) {
        System.out.println("\n=== Selection Sort ===");
        int length = array.length;
        int[] result = array.clone();

        long startTime = System.currentTimeMillis();
        for (int i = 0; i <= length - 2; i++) {
            int min = i;
            for (int j = i + 1; j <= length - 1; j++)
                if (result[j] < result[min])
                    min = j;
            int temp = result[i];
            result[i] = result[min];
            result[min] = temp;
        }
        long endTime = System.currentTimeMillis();

        measureTime(startTime, endTime);
        return result;
    }
}
