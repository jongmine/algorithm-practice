public class ShellSort extends Sort {

    @Override
    public int[] sort(int[] array) {
        System.out.println("\n=== Shell Sort ===");
        int length = array.length;
        int[] result = array.clone();

        long startTime = System.currentTimeMillis();

        for (int h = length / 3; h > 0; h = h / 3)
            for (int i = h; i <= length - 1; i++) {
                int CurrentElement = result[i];
                int j = i;
                while (j >= h && result[j - h] > CurrentElement) {
                    result[j] = result[j - h];
                    j = j - h;
                }
                result[j] = CurrentElement;
            }

        long endTime = System.currentTimeMillis();

        measureTime(startTime, endTime);
        return result;
    }
}
