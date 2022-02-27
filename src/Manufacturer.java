public class Manufacturer {
    private final CarShow CAR_SHOW;
    private final int CREATE_CAR_TIME = 10000;

    public Manufacturer(CarShow carShow) {
        this.CAR_SHOW = carShow;
    }

    public void createCar() {
        try {
            Thread.sleep(CREATE_CAR_TIME);
            System.out.printf("Manufacturer %s create a new car\n", Thread.currentThread().getName());
            CAR_SHOW.addCar();
        } catch (InterruptedException ignored) {
        }
    }
}