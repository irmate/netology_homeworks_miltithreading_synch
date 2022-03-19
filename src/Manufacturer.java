public class Manufacturer extends Thread {
    private final CarShow CAR_SHOW;
    private final int CREATE_CAR_TIME = 8000;

    public Manufacturer(CarShow carShow, String name) {
        super(name);
        this.CAR_SHOW = carShow;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(CREATE_CAR_TIME);
                System.out.printf("Manufacturer %s create a new car\n", getName());
                CAR_SHOW.addCar();
            }
        } catch (InterruptedException ignored) {
        }
    }
}