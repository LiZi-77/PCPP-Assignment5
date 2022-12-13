### Exercise 10 Resubmit

#### 10.1.2

you can see the code from TestHistogram.java

#### 10.1.3

```
casHistogram with 1 threads     691291747.2 ns 10363016.35          2
casHistogram with 2 threads     436056783.9 ns 11034406.46          2
casHistogram with 3 threads     395949743.1 ns 21778248.69          2
casHistogram with 4 threads     377236055.4 ns 14924866.03          2
casHistogram with 5 threads     374567529.2 ns 19865737.83          2
casHistogram with 6 threads     373083745.9 ns 19859680.18          2
casHistogram with 7 threads     372214013.9 ns 20157868.15          2
casHistogram with 8 threads     365258046.7 ns 12636533.41          2
casHistogram with 9 threads     372249522.0 ns 25453838.60          2
casHistogram with 10 threads     366212772.3 ns 26628835.88          2
lock Histogram with 1 threads     712171294.7 ns 48164811.18          2
lock Histogram with 2 threads     438469957.7 ns 19572365.28          2
lock Histogram with 3 threads     423770109.1 ns 63177378.69          2
lock Histogram with 4 threads     377024692.0 ns 20245068.24          2
lock Histogram with 5 threads     378864859.9 ns 24949587.43          2
lock Histogram with 6 threads     367284433.2 ns 17732803.29          2
lock Histogram with 7 threads     372287212.9 ns 19680545.09          2
lock Histogram with 8 threads     366376709.1 ns 15081542.63          2
lock Histogram with 9 threads     373204414.1 ns 19979055.58          2
lock Histogram with 10 threads     364336692.9 ns 19741598.65          2

```

See code from TestCASLockHistogram.java, CAS-based implementation performs better in most cases.