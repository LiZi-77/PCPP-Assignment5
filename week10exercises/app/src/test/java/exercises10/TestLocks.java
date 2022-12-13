package exercises10;

// JUnit testing imports
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.Arguments;

import static org.junit.jupiter.api.Assertions.*;

// Data structures imports
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.Collectors;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

// Concurrency imports
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicBoolean;

public class TestLocks {
    // The imports above are just for convenience, feel free add or remove imports
    
    // TODO: 10.2.5
    @Test
    public void TestLocksForSingleThread(){
        ReadWriteCASLock lock = new ReadWriteCASLock();

        try{
            assertTrue(lock.writerTryLock());
            assertFalse(lock.readerTryLock());
            assertFalse(lock.writerTryLock());

            lock.writerUnlock();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try{
            assertTrue(lock.readerTryLock());
            assertFalse(lock.writerTryLock());
            assertTrue(lock.readerTryLock());

            lock.readerUnlock();
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertThrows(Exception.class, ()->lock.writerUnlock());
        assertThrows(Exception.class, ()-> lock.readerUnlock());
    }

    // TODO: 10.2.6
    @Test
    public void TestLocksForParallel() throws BrokenBarrierException, InterruptedException {
        ReadWriteCASLock lock = new ReadWriteCASLock();
        int nThreads = 4;
        AtomicInteger Writers = new AtomicInteger(0);
        AtomicBoolean success = new AtomicBoolean(true);
        CyclicBarrier barrier = new CyclicBarrier(5);

        for (int i = 1; i < nThreads+1; i++) {
            new Thread(() -> {
                try {
                    barrier.await();
                    for (int j = 0; j < 100; j++) {
                        if (lock.writerTryLock()) {
                            assertTrue(Writers.incrementAndGet() <= 1);Writers.decrementAndGet();lock.writerUnlock();
                        };
                    }

                    barrier.await();
                } catch (Exception e) {
                    success.set(false);
                }
            }).start();
        }
        try {
            barrier.await();
            barrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        assertTrue(success.get() == true && Writers.get() == 0);
    }

}



