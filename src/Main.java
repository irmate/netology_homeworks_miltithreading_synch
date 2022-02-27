import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        final ExecutorService threadPool = Executors.newFixedThreadPool(4);
        CarShow carShow = new CarShow();

        for (int i = 0; i < 10; i++) {
            threadPool.submit(carShow::buyCar);
        }
        threadPool.shutdown();
    }
}