package csc8011;

//Constructor to create Exhibit objects for the Street Fighter game museum

public class Exhibit {
    private Integer exhibitId;
    private String description;
    private Integer yearAcquired;
    private Double value;

    public Exhibit(Integer id, String description, Integer year, Double value) {
        this.exhibitId = id;
        this.description = description;
        this.yearAcquired = year;
        this.value = value;
    }

//defined setter and getter methods for the Exhibit class constructor//

    public Integer getExhibitId() {
        return exhibitId;
    }

    public void setExhibitId(Integer id) {
        this.exhibitId = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getYearAcquired() {
        return yearAcquired;
    }

    public void setYearAcquired(Integer year) {
        this.yearAcquired = year;
    }

    public double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
