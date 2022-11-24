## Exercise 10

### 10.1

### 10.1.1

In `CasHistogram.java`

### 10.2.3

casHistogram with 1 threads     513333887.1 ns 20388251.75          2
casHistogram with 2 threads     316525607.7 ns 12255271.51          2
casHistogram with 3 threads     204417206.4 ns 10745856.85          2
casHistogram with 4 threads     163769259.1 ns 18462670.43          2
casHistogram with 5 threads     169471166.7 ns 10252507.90          2
casHistogram with 6 threads     155146059.6 ns 8068182.15          2
casHistogram with 7 threads     143111254.4 ns 3034507.68          2
casHistogram with 8 threads     137649523.8 ns 2940167.46          2
casHistogram with 9 threads     132120028.3 ns 2961491.49          2
casHistogram with 10 threads     136383764.6 ns 1998711.44          2
lock Histogram with 1 threads     488831117.0 ns 10027153.43          2
lock Histogram with 2 threads     323786167.7 ns 1300719.28          2
lock Histogram with 3 threads     215167376.2 ns 1347307.36          2
lock Histogram with 4 threads     170408587.7 ns 4235834.52          2
lock Histogram with 5 threads     152969822.2 ns 3908767.33          2
lock Histogram with 6 threads     146267970.3 ns 4096290.99          2
lock Histogram with 7 threads     141679706.2 ns 3483158.36          2
lock Histogram with 8 threads     142270447.3 ns 1596014.20          2
lock Histogram with 9 threads     139517651.9 ns 2839639.73          2
lock Histogram with 10 threads     133207833.3 ns 1935874.46          2

The CAS is faster. This is what I expected. In lock approach, all the thread are trying to get a single lock, but in CAS approach, we have a lot of atomic integer to get striping.



## 10.2

### 10.1

see `ReadWriteCASLock.java`

### 10.2

see `ReadWriteCASLock.java`

### 10.3

see `ReadWriteCASLock.java`

### 10.4

too difficult, cannot do this 