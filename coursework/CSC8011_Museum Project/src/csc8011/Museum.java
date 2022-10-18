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

    //function getter for Museum name//

    public String getMuseumName() {
        return name;
    }

    //function to the find the highest value of all objects in the arraylist, then returns that object//

    public Object getHighestValue() {
        Integer length = exhibits.size();
        Exhibit highestValExhibit = new Exhibit(0,"default",0,0.00);
        double max = 0.0;

        for (Exhibit exhibit : exhibits) {
            if (exhibit.getValue() > max) {
                highestValExhibit = exhibit;
            }
        }
        return highestValExhibit;
    }

    //function to find object representing the first item acquired//
}
