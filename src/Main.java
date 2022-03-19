import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        final ExecutorService threadPool = Executors.newFixedThreadPool(4);
        final CarShow carShow = new CarShow();
        final Thread manufacturer = new Manufacturer(carShow, "Jeep");

        manufacturer.setDaemon(true);
        manufacturer.start();

        for (int i = 0; i < 10; i++) {
            threadPool.submit(carShow::buyCar);
        }
        threadPool.shutdown();
    }
}