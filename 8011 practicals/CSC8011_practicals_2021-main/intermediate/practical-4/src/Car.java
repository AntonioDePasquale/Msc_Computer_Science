public class Car {

    private final String make;
    private final String model;
    private int age;

    Car(String make, String model, int age){
        this.make = make;
        this.model = model;
        this.age = age;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Car{" +
                "make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", age=" + age +
                '}';
    }
}
