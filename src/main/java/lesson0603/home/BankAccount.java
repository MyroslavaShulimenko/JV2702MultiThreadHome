package lesson0603.home;

import java.util.concurrent.atomic.AtomicInteger;

public class BankAccount {
    private static AtomicInteger sum = new AtomicInteger(0);


        public void deposit(int amount) {
            int res = sum.get();
            int old = res;
            res += amount;
            sum.compareAndSet(old, res);
        }
    public void withdraw(int amount) {
        int res = sum.get();
        int old = res;
        res -= amount;
        sum.compareAndSet(old, res);

    }
    public int getSum() {
        return sum.get();
    }
}
