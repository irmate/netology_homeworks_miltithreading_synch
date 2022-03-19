import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CarShow {
    private final int WAIT_TIME = 2500;
    private int buyerCount = 0;

    private final List<Car> STOCK = new ArrayList<>();

    private Lock buyLock = new ReentrantLock(true);
    private Condition condition = buyLock.newCondition();

    public void addCar() {
        try {
            buyLock.lock();
            STOCK.add(new Car());
            condition.signal();
        } finally {
            buyLock.unlock();
        }
    }

    public void buyCar() {
        try {
            buyLock.lock();
            Thread.sleep(WAIT_TIME);
            buyerCount++;
            Thread.currentThread().setName("Buyer " + buyerCount);
            System.out.printf("%s enter at Car show\n", Thread.currentThread().getName());
            while (STOCK.size() == 0) {
                System.out.printf("No cars, %s waiting a car\n", Thread.currentThread().getName());
                condition.await();
            }
            System.out.printf("%s buy a new car\n", Thread.currentThread().getName());
        } catch (InterruptedException ignored) {
        } finally {
            buyLock.unlock();
        }
        STOCK.remove(0);
    }
}