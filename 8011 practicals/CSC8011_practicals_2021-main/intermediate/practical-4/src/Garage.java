import java.util.ArrayList;
import java.util.Arrays;

public class Garage {

    private String name;
    private String[] address = new String[2];
    private int carCapacity;

    private ArrayList<Car> carsInGarage;

    Garage(String name, String firstLineAddress, String postCode){
        this.name = name;
        this.address[0] = firstLineAddress;
        this.address[1] = postCode;
        this.carsInGarage = new ArrayList<>();
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

    public ArrayList<Car> getCarsInGarage() {
        return carsInGarage;
    }

    public String addressNicePrint(){
        return String.format("First Line of Address: %s\nPostcode: %s",
                address[0],address[1]);
    }


    @Override
    public String toString() {
        return "Garage{" +
                "name='" + name + '\'' +
                ", address=" + Arrays.toString(address) +
                ", carCapacity=" + carCapacity +
                ", carsInGarage=" + carsInGarage +
                '}';
    }
}
