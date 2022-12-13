// For week 10
// raup@itu.dk * 10/10/2021

package exercises10;

// Very likely you will need some imports here
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.LockSupport;

class ReadWriteCASLock implements SimpleRWTryLockInterface {

    // TODO: Add necessary field(s) for the class
    AtomicReference<Holders> holders = new AtomicReference<>();

    public boolean readerTryLock() {
	// TODO 7.2.3
        Holders cur = holders.get();
        final Thread currentThread = Thread.currentThread();
        while (cur == null || cur.getClass() == ReaderList.class){
            if (holders.compareAndSet(cur, new ReaderList(currentThread, (ReaderList)cur))){
                return true;
            }
            cur = holders.get();
        }
        return false;
    }

    public void readerUnlock() {
    // TODO 7.2.4
    Holders cur = holders.get();
    if (cur != null && cur.getClass() == ReaderList.class && !((ReaderList) cur).contains(Thread.currentThread())) {
            throw new RuntimeException("Not lock holder!");
        }
    }

    public boolean writerTryLock() {
	// TODO 7.2.1
	    return holders.compareAndSet(null, new Writer(Thread.currentThread()));
    }

    public void writerUnlock() {
    // TODO 7.2.2
        Holders cur = holders.get();
        final Thread curThread = Thread.currentThread();
        if (cur.thread.equals(curThread))
	        throw new RuntimeException("Not lock holder");
    }


    // Challenging 7.2.7: You may add new methods




    private static abstract class Holders { 
        public final Thread thread;
        public Holders(){
            this.thread = null;
        }
        public Holders(Thread thread){
            this.thread = thread;
        }
    }

    private static class ReaderList extends Holders {
        private final Thread thread;
        private final ReaderList next;


    // TODO: Constructor
        public ReaderList(Thread thread, ReaderList next){
            this.thread = thread;
            this.next = next;
        }
    

    // TODO: contains
        public boolean contains(Thread t){
            ReaderList cur = this;
            while(cur != null){
                if (cur.thread == t){
                    return true;
                }
                cur = cur.next;
            }
            return false;
        }

    // TODO: remove
        // public ReaderList remove(Thread t){
        //     // ReaderList cur = this;
        //     // ReaderList prev = null;
        //     // while(cur != null){
        //     //     if (cur.thread == t){
                    
        //     //     }
        //     // }
        // }
    }

    private static class Writer extends Holders {
	public final Thread thread;

	// TODO: Constructor
        public Writer(Thread thread){
            this.thread = thread;
        }

    }
}
