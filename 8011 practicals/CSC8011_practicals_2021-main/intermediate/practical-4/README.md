# Practical 4

## Practical 4.1

This practical is going to follow on from good class design and functionality
 using ArrayLists and Composition to create relationships between objects.
 
 
Take the following 2 classes

Garage.java
```java
import java.util.Arrays;

public class Garage {

    private String name;
    private String[] address = new String[2];
    private int carCapacity;
    private Car[] carsInGarage;

    Garage(String name, String firstLineAddress, String postCode){
        this.name = name;
        this.address[0] = firstLineAddress;
        this.address[1] = postCode;
        carsInGarage = new Car[carCapacity]; // initialised array with the
        // capacity of cars specified
    }

    public String[] getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCarCapacity() {
        return carCapacity;
    }

    public void setCarCapacity(int carCapacity) {
        this.carCapacity = carCapacity;
    }

    public String addressNicePrint(){
        return String.format("First Line of Address: %s\nPostcode: %s",
                address[0],address[1]);
    }

    public Car[] getCarsInGarage() {
        return carsInGarage;
    }

    @Override
    public String toString() {
        return "Garage{" +
                "name='" + name + '\'' +
                ", address=" + Arrays.toString(address) +
                ", carCapacity=" + carCapacity +
                ", carsInGarage=" + Arrays.toString(carsInGarage) +
                '}';
    }
}

```

Car.java

```java
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

```

These classes have populated fields and constructors. In the Garage class
, create a method that allows you to add a car to the `carsInGarage` ArrayList


## Practical 4.2

In the car class, modify the class so that the age of the cannot be a
 negative number, or greater than 30 years old. You may create a new method
  that checks the validity of the value.

## Practical 4.3

In the Garage class, create a new method that allows you to check if a Car
 object exists in the ArrayList. *Hint* carsInGarage.contains(Car Object);


## Practical 4.4

In the Garage class, create a method that allows you to filter all of the
 Cars that are older than a given age.

## Practical 4.5

In the Garage class, provide a method that neatly prints out all of the cars
 inside of the Garage.

