import java.io.FileWriter;
import java.io.IOException;

public class Customer implements Comparable<Customer> {

    /* Two Strings, one for the first name and one for the last name for the customer set to null by default */
    private String firstName;
    private String lastName;

    /* ArrayList to hold the activities the customer has tickets for (when adding to this ArrayList use the
    customerActivitiesAdd method outlined below to limit the number of activities that can be added to max 3 */
    private SortedArrayList<Activity> customerActivities = new SortedArrayList<>();

    /* Customer constructor*/
    /* Takes two parameters, one for the first name and one for the second name when a new customer is made */

    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /*Customer class getters and setters*/

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public SortedArrayList<Activity> getCustomersActivityList() {
        return customerActivities;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /*method to add to the customer activities arraylist that is limits the amount that can be added to 3"*/

    public void customerActivitiesAdd(Activity activity) {
        if (customerActivities.size() >= 3) {
            throw new IllegalArgumentException("A customer may only have a max of 3 activities");
        }
        customerActivities.add(activity);
    }

    /* Boolean method to return true if both customers have the same name and false otherwise
    * takes a parameter to differentiate from the default equals() */

    public boolean equals(Customer otherCustomer) {
        return (firstName.equals(otherCustomer.firstName)
                && lastName.equals(otherCustomer.lastName));
    }

    /* Method to replace toString() in the customer class, takes the first name and the last name adding a space
    to return a concatenated string of the full name */

    public String toString()
    {
        return firstName + " " + lastName;
    }

    public static void customerPrint(SortedArrayList<Customer> customer) {
        for (int i = 0; i < customer.size(); i++) {
            String fullName = customer.get(i).toString();
            System.out.println(fullName);
        }
        System.out.println("--------------------------------\n");
    }

    /* registered */

    public static Boolean registered(SortedArrayList<Customer> customerArray, String first, String last) {

        boolean regInfo = false;

        for (int i = 0; i < customerArray.size(); i++) {
            if (customerArray.get(i).getFirstName().equalsIgnoreCase(first)
                    && customerArray.get(i).getLastName().equalsIgnoreCase(last)) {
                regInfo = true;
                break;
            }
        }
        if (regInfo) {
            System.out.println("\nThe name entered is a registered customer\n");
            return true;
        } else {
            System.out.println("\nThe name entered is not a registered customer\n");
            return false;
        }
    }


    /* Update */

    public static void activityBuyUpdate(SortedArrayList<Customer> customer, Integer ticketsBought, String activityName, String firstName, String lastName) {

        for (int i = 0; i < customer.size(); i++) {
            SortedArrayList<Activity> activityList = customer.get(i).getCustomersActivityList();
            Activity tempActivity = new Activity(activityName, ticketsBought);

            if (customer.get(i).getFirstName().equalsIgnoreCase(firstName)
            && customer.get(i).getLastName().equalsIgnoreCase(lastName)) {


                if (activityList.size() == 0) {
                    activityList.insert(tempActivity);
                    System.out.println("The activity " + activityName + " has now been registered with customer " + firstName + " " + lastName);
                    System.out.println("--------------------------------");
                    Activity.activityPrint(activityList);
                    break;
                }
                if ((activityList.size() >=1) && (activityList.size() < 4)) {
                    for (int j = 0; j < activityList.size(); j++) {
                        if (activityList.get(j).getActivityName().equals(activityName)) {
                            System.out.println("That activity is already registered with customer " + firstName + " " + lastName);
                            Integer oldTicketNo = activityList.get(j).getTicketNo();
                            Integer newTicketNo = activityList.get(j).getTicketNo() + ticketsBought;
                            activityList.get(j).setTickets(newTicketNo);
                            System.out.println("The ticketsBought count has been updated to " + newTicketNo + " from " + oldTicketNo);
                            System.out.println("\n--------------------------------");
                            Activity.activityPrint(activityList);
                            break;
                        } else if (activityList.size() < 3 && !(activityList.get(j).getActivityName().equals(activityName))) {
                            activityList.insert(tempActivity);
                            System.out.println("The activity " + activityName + " has now with customer " + firstName + " " + lastName);
                            System.out.println("\n--------------------------------");
                            Activity.activityPrint(activityList);
                            break;
                        } else {
                            System.out.println("This customers activity list was unable to be updated and may be full");
                            System.out.println("The list of registered activities for this customer is shown below");
                            System.out.println("\n--------------------------------");
                            Activity.activityPrint(activityList);
                        }
                    }
                }
            }
        }
    }

    public static Boolean activityCancelUpdate(SortedArrayList<Customer> customer, Integer ticketsCancelled, String activityName, String firstName, String lastName) {

        Boolean cancelStatus = false;

        for (int i = 0; i < customer.size(); i++) {
            SortedArrayList<Activity> activityList = customer.get(i).getCustomersActivityList();

            if (customer.get(i).getFirstName().equalsIgnoreCase(firstName)
                    && customer.get(i).getLastName().equalsIgnoreCase(lastName)) {

                if (activityList.size() == 0) {
                    System.out.println("\nThis customer has no registered activities");
                    break;
                }
                else if (activityList.size() < 4) {
                    for (int j = 0; j < activityList.size(); j++) {
                        if (activityList.get(j).getActivityName().equals(activityName)) {
                            System.out.println("\n" + activityName + " is registered with customer " + firstName + " " + lastName);
                            Integer oldTicketNo = activityList.get(j).getTicketNo();
                            Integer newTicketNo = activityList.get(j).getTicketNo() - ticketsCancelled;
                            if (newTicketNo > 0) {
                                activityList.get(j).setTickets(newTicketNo);
                                System.out.println(ticketsCancelled + " tickets have been cancelled successfully. This customer now has " + newTicketNo + " tickets");
                                System.out.println("--------------------------------");
                                Activity.activityPrint(activityList);
                                cancelStatus = true;
                            } else if (newTicketNo == 0) {
                                activityList.remove(j);
                                System.out.println(ticketsCancelled + " tickets have been cancelled successfully");
                                System.out.println("There are no remaining tickets for " + activityName + " so the activity has been removed");
                                System.out.println("--------------------------------");
                                Activity.activityPrint(activityList);
                                cancelStatus = true;
                            }
                            else {
                                System.out.println("\nThis customer only has " + oldTicketNo + " tickets and cannot cancel " + ticketsCancelled + " tickets");
                                System.out.println("\n--------------------------------");
                                break;
                            }
                        }
                    }
                }
            }
        }
        return cancelStatus;
    }

    /* output method */



    /*method that overrides the compareTo method in the Comparable interface to compare customer last names */

    @Override
    public int compareTo(Customer customer) {
        if (this.getLastName().compareTo(customer.getLastName()) == 0) {
            return this.getFirstName().compareTo(customer.getFirstName());
        } else {
            return this.getLastName().compareTo(customer.getLastName());
        }
    }
}
