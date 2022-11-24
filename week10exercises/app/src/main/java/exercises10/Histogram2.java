package exercises10;

public class Histogram2  implements  Histogram{
    private final int[] counts;  // add final
    private int total=0;

    public Histogram2(int span) {
        this.counts = new int[span];
    }

    public synchronized void increment(int bin) {
        counts[bin] = counts[bin] + 1;
        total++;
    }

    public synchronized int getCount(int bin) {
        return counts[bin];
    }

    public synchronized float getPercentage(int bin){
        return getCount(bin) / getTotal() * 100;
    }

    public int getSpan() {
        return counts.length;
    }

    @Override
    public synchronized int getAndClear(int bin) {
        int value = counts[bin];
        counts[bin] = 0;
        return value;
    }

    public synchronized int getTotal(){
        return total;
    }
}
