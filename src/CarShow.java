import java.util.ArrayList;
import java.util.List;

public class CarShow {
    private final int WAIT_TIME = 2500;
    private int buyerCount = 0;

    private final List<Car> STOCK = new ArrayList<>();
    private final Manufacturer MANUFACTURER = new Manufacturer(this);

    public synchronized void addCar() {
        STOCK.add(new Car());
        notify();
    }

    public synchronized void buyCar() {
        try {
            Thread.sleep(WAIT_TIME);
            buyerCount++;
            Thread.currentThread().setName("Buyer " + buyerCount);
            System.out.printf("%s enter at Car show\n", Thread.currentThread().getName());
            while (STOCK.size() == 0) {
                System.out.printf("No cars, %s waiting a car\n", Thread.currentThread().getName());
                new Thread(null, MANUFACTURER::createCar, "Jeep").start();
                wait();
            }
            System.out.printf("%s buy a new car\n", Thread.currentThread().getName());
        } catch (InterruptedException ignored) {
        }
        STOCK.remove(0);
    }
}