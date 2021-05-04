public class InsertionSort extends Sort {

    @Override
    public int[] sort(int[] array) {
        System.out.println("\n=== Insertion Sort ===");
        int length = array.length;
        int[] result = array.clone();

        long startTime = System.currentTimeMillis();

        for (int i = 1; i <= length - 1; i++) {
            int CurrentElement = result[i];
            int j = i - 1;
            while (j >= 0 && result[j] > CurrentElement) {
                result[j + 1] = result[j];
                j--;
            }
            result[j + 1] = CurrentElement;
        }

        long endTime = System.currentTimeMillis();

        measureTime(startTime, endTime);
        return result;
    }
}
