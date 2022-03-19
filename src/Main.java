import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        final ExecutorService threadPool = Executors.newFixedThreadPool(4);
        CarShow carShow = new CarShow();
        Thread manufacturer = new Manufacturer(carShow, "Jeep");

        manufacturer.setDaemon(true);
        manufacturer.start();

        for (int i = 0; i < 10; i++) {
            threadPool.submit(carShow::buyCar);
        }
        threadPool.shutdown();
    }
}