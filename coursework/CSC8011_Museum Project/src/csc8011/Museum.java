package csc8011;
import java.util.ArrayList;

public class Museum {
    ArrayList<Exhibit> exhibits = new ArrayList<>();
    private String name;

    public Museum() {
        this.name = "The Street Fighter Fan Museum";
    }

    //Methods to add and remove exhibit objects from the exhibits ArrayList//

    public boolean addExhibit(Exhibit i) {
        if (!exhibits.contains(i)) {
            exhibits.add(i);
        } else {
            System.out.println("This exhibit already exists.");
        }
        return true;
    }

    public boolean removeExhibit(Exhibit i) {
        return exhibits.remove(i);
    }

    //function getter and setter for Museum name//

    public String getMuseumName() {
        return name;
    }
    public void setMuseumName(String newName) {
        this.name = newName;
    }

    //function to the find the highest value of all objects in the arraylist, then returns that object//

    public String objectToString(int index) {
        String objectString =  (exhibits.get(index).getExhibitId())
                + ", " + (exhibits.get(index).getDescription())
                + ", " + (exhibits.get(index).getYearAcquired())
                + ", " + (exhibits.get(index).getValue());
        return objectString;
    }
    public String findHighestValue() {
        Integer length = exhibits.size();
        double max = 0.0;
        String exhibitString = "";

        for (int i = 0; i < length; i++) {
            if (exhibits.get(i).getValue() > max) {
                max = exhibits.get(i).getValue();
                exhibitString = objectToString(i);
            }
        }
        return exhibitString;
    }

    //function to find object representing the first item acquired//

    public String findFirstAdded() {
        Integer length = exhibits.size();
        Integer year = 9999;
        String firstString = "";

        for (int i = 0; i < length; i++) {
            if (exhibits.get(i).getYearAcquired() < year) {
                year = exhibits.get(i).getYearAcquired();
                firstString = objectToString(i);
            }
        }
        return firstString;
    }

    //function to find the average of all exhibit values//

    public Double findAverageValue() {
        Double sum = 0.00;
        Double average = 0.00;
        Integer length = exhibits.size();

        for (int i = 0; i < length; i++) {
            sum += exhibits.get(i).getValue();
        }
        average = sum/length;

        return average;
    }
}
