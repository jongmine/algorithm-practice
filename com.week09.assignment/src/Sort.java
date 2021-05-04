public abstract class Sort implements Sortable {

    @Override
    public void measureTime(long start, long end) {
        System.out.println("수행시간: " + (end - start) + "ms");
    }
}

interface Sortable {
    int[] sort(int[] array);

    void measureTime(long start, long end);
}
