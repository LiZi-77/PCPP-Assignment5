package exercises10;
import java.util.concurrent.atomic.AtomicInteger;

public class CasHistogram implements Histogram{
    AtomicInteger[] counts;

    public CasHistogram(int span){
        this.counts = new AtomicInteger[span];
        for (int i = 0; i < span; i++) {
            counts[i] = new AtomicInteger(0);
        }
    }

    @Override
    public void increment(int bin) {
        AtomicInteger old;
        int oldValue, newValue;
        do {
            old = counts[bin];
            oldValue = old.get();
            newValue = oldValue + 1;
        }while(old.compareAndSet(newValue, oldValue));
    }

    @Override
    public int getCount(int bin) {
        return counts[bin].get();
    }

    @Override
    public int getSpan() {
        return counts.length;
    }

    @Override
    public int getAndClear(int bin) {
        AtomicInteger cur;
        int curValue;
        cur = counts[bin];
        curValue = cur.get();
        while(cur.compareAndSet(curValue, 0)){
            curValue = cur.get();
        }
        return curValue;
    }
}
